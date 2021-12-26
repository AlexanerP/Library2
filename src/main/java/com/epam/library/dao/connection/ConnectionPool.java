package com.epam.library.dao.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;


public enum ConnectionPool {
    INSTANCE;

    private static final Logger logger = LoggerFactory.getLogger(ConnectionPool.class);

    private BlockingQueue<ProxyConnection> freeConnections;
    private Queue<ProxyConnection> usedConnections;

    private static final String pathDateBaseProperties = "database.properties";
    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;
    private int DEFAULT_POOL_SIZE = 2;

    private static Properties properties = new Properties();

    static  {
        logger.info("Load properties for connection.");
        String driverName = null;
        try (InputStream inputStream = ConnectionPool.class.getClassLoader().getResourceAsStream(pathDateBaseProperties)) {
            properties.load(inputStream);
            driverName = (String) properties.get("driverDatabase");
            Class.forName(driverName);
            URL = properties.getProperty("url");
            USER = properties.getProperty("userName");
            PASSWORD = properties.getProperty("password");

            ConnectionPool.INSTANCE.createPool();
        } catch (ClassNotFoundException e) {
            logger.error("Class not found.");
            throw new ConnectionException("Class not found: " + pathDateBaseProperties, e);
        } catch (IOException e) {
            logger.error("Can't load properties file: File path - {}", pathDateBaseProperties);
            throw new ConnectionException("Can't load properties file: File path - " + pathDateBaseProperties, e);
        }
        logger.info("Connection settings upload complete");
    }

    ConnectionPool() {
        freeConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        usedConnections = new ArrayDeque<>();
    }

    private void createPool() {
        logger.info("Create connection pool.");
        for(int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                ProxyConnection proxy = new ProxyConnection(connection);
                freeConnections.offer(proxy);
            } catch (SQLException e) {
               logger.error("Error while creating Connection Pool.");
               throw new ConnectionException(e);
            }
        }
    }

    public Connection getConnection() throws ConnectionException {
        logger.info("Create connection.");
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            usedConnections.offer(connection);
        } catch (InterruptedException e) {
            logger.error("Unable to establish connection.");
            throw new ConnectionException("Unable to establish connection.", e);
        }
        logger.info("Creating a connection");
        return new ProxyConnection(connection);
    }


    public void releaseConnection(Connection connection) {
        logger.info("Check release connection start.");
        if (connection instanceof ProxyConnection) {
            usedConnections.remove(connection);
            freeConnections.offer((ProxyConnection) connection);
        } else {
            logger.error("Wrong connection is detected");
            throw new ConnectionException("Wrong connection is detected:" + connection.getClass() +
                    "should be ProxyConnection.class ");
        }
        logger.info("Check release connection finish.");
    }

    public void destroyPool() {
        logger.info("Start destruction of the pool.");
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnections.take().reallyCloseConnection();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Something wrong with current thread", e);
            } catch (SQLException sqlE) {
                logger.error("Exception in connection close method", sqlE);
                for (Throwable e : sqlE) {
                    logger.error(e.toString());
                }
            }
            deregisterDrivers();
            logger.info("End of pool destruction.");
        }
    }

    private void deregisterDrivers() {
//        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
//            try {
//                DriverManager.deregisterDriver(driver);
//            } catch (SQLException e) {
//                logger.error("Exception when deregistering driver " + driver + " " + e);
//            }
//        });

        logger.info("Start deregister Drivers.");
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            try {
                DriverManager.deregisterDriver(drivers.nextElement());
            } catch (SQLException sqlE) {
                logger.error("Driver deregistration did not occur.");
                for (Throwable e : sqlE) {
                    logger.error(e.toString());
                }
            }
        }
        logger.info("Finish deregister Drivers.");
    }
}
