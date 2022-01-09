package com.epam.library.service.impl;

import com.epam.library.dao.DAOException;
import com.epam.library.dao.DAOFactory;
import com.epam.library.dao.UserDAO;
import com.epam.library.entity.User;
import com.epam.library.entity.UserRole;
import com.epam.library.entity.UserStatus;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import com.epam.library.service.ServiceValidator;
import com.epam.library.service.UserService;
import com.epam.library.utill.Chiper.Cipher;
import com.epam.library.utill.UtilFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public Optional<User> verification(String email, String password) throws ServiceException {
        try {
            UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
            Cipher cipher = UtilFactory.getInstance().getCipher();
            String passwordCipher = cipher.getCipherString(password.trim());
            return userDAO.getUserByEmailAndPassword(email.trim(), passwordCipher);
        }catch (DAOException e) {
            logger.error("Error in services during user verification.");
            throw new ServiceException("Error in services during user verification.", e);
        }
    }

    @Override
    public int create(String email, String password, String secondName, String lastName) throws ServiceException {
        try {
            UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
            Cipher cipher = UtilFactory.getInstance().getCipher();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (validator.isEmail(email.trim())) {
                if (validator.isPassword(password.trim()) && validator.isLength(secondName)
                        && validator.isLength(lastName) && validator.isEmail(email.trim())) {
                    String passwordCipher = cipher.getCipherString(password.trim());
                    User user = new User();
                    user.setPassword(passwordCipher);
                    user.setEmail(email);
                    user.setSecondName(secondName);
                    user.setLastName(lastName);
                    user.setStatus(UserStatus.ACTIVE);
                    user.setRole(UserRole.USER);
                    user.setCountViolations(0);
                    userDAO.create(user);
                    return 1;
                } else {
                    return 2;
//                throw new ServiceException("Invalid values during registration.");
                }
            } else {
                return 3;
            }
        }catch (DAOException e) {
            logger.error("Error in services during registration.");
            throw new ServiceException("Error in services during registration.", e);
        }
    }

    @Override
    public List<User> getUsers() throws ServiceException {
        try {
            UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
            return userDAO.getUsers();
        }catch (DAOException e) {
            logger.error("Services error when getting a list of all users.");
            throw new ServiceException("Services error when getting a list of all users.", e);
        }
    }

    @Override
    public long showCountByStatus(String status) throws ServiceException {
        try {
            UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
            if (status != null) {
                if (status.equalsIgnoreCase(UserStatus.ACTIVE.name())
                        || status.equalsIgnoreCase(UserStatus.BLOCKED.name())
                        || status.equalsIgnoreCase(UserStatus.DELETE.name())) {
                    return userDAO.getCountByStatus(UserStatus.valueOf(status.toUpperCase()));
                } else {
                    throw new ServiceException("Unknown user status.");
                }
            } else {
                throw new ServiceException("The value is empty.");
            }
        }catch (DAOException e) {
            logger.error("Error in services when getting the number of users by status.");
            throw new ServiceException("Error in services when getting the number of users by status.", e);
        }
    }

    @Override
    public List<User> showUserByStatus(String status) throws ServiceException {
        try {
            UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
            List<User> users;
            if (status.equalsIgnoreCase(UserStatus.ACTIVE.name()) || status.equalsIgnoreCase(UserStatus.BLOCKED.name())
                || status.equalsIgnoreCase(UserStatus.DELETE.name())) {
                users = userDAO.getUsersByStatus(UserStatus.valueOf(status.toUpperCase()));
                return users;
            } else {
                throw new ServiceException("Unknown user status.");
            }
        }catch (DAOException e) {
            logger.error("Error in services when getting the number of users by status.");
            throw new ServiceException("Error in services when getting the number of users by status.", e);
        }
    }

    @Override
    public List<User> showUserByRole(String role) throws ServiceException {
        try {
            UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
            List<User> users;
            if (role.equalsIgnoreCase(UserRole.USER.name()) || role.equalsIgnoreCase(UserRole.ADMIN.name())
                    || role.equalsIgnoreCase(UserRole.MANAGER.name())) {
                users = userDAO.getUsersByRole(UserRole.valueOf(role.toUpperCase()));
                return users;
            } else {
                throw new ServiceException("Unknown user role.");
            }
        }catch (DAOException e) {
            logger.error("Error in services when getting the number of users by role.");
            throw new ServiceException("Error in services when getting the number of users by role.", e);
        }
    }

    @Override
    public List<User> showUserByEmail(String email) throws ServiceException {
        try {
            UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (validator.isEmail(email.trim())) {
                return userDAO.getUserByEmail(email.trim());
            }else {
                throw new ServiceException("Invalid email value.");
            }
        }catch (DAOException e) {
            logger.error("Error in services when fetching users by email");
            throw new ServiceException("Error in services when fetching users by email", e);
        }
    }

    @Override
    public Optional<User> showUserById(String userId) throws ServiceException {
        try {
            UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (validator.isNumber(userId.trim())) {
                return userDAO.getUserById(Long.parseLong(userId.trim()));
            } else {
                throw new ServiceException("Trying to get a user by an ID that is not a number.");
            }
        }catch (DAOException e) {
            logger.error("Error in services when getting users by ID.");
            throw new ServiceException("Error in services when getting users by ID.", e);
        }
    }

    @Override
    public boolean update(String email, String secondName, String lastName, String userId) throws ServiceException {
        try {
            UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (validator.isLengthForUpdate(secondName) && validator.isLengthForUpdate(lastName)
                    && validator.isLengthForUpdate(email.trim()) && validator.isLengthForUpdate(userId)) {
                Optional<User> optionalUser = userDAO.getUserById(Long.parseLong(userId.trim()));
                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    user.setEmail(email != "" ? email : optionalUser.get().getEmail());
                    user.setSecondName(secondName != "" ? secondName : optionalUser.get().getSecondName());
                    user.setLastName(lastName != "" ? lastName : optionalUser.get().getLastName());
                    int result =  userDAO.update(user);
                    if (result == 1) {
                        return true;
                    }
                } else {
                    return false;
//                    throw new ServiceException("User by ID not found");
                }
            } else {
                return false;
//                throw new ServiceException("Invalid values.");
            }
        }catch (DAOException e) {
            logger.error("An error occurred while updating the user in services.", e);
            throw new ServiceException("An error occurred while updating the user in services.");
        }
        return false;
    }

    @Override
    public boolean updateStatus(String userId, String status) throws ServiceException {
        try {
            UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (validator.isNumber(userId)) {
                if (status.equalsIgnoreCase(UserStatus.ACTIVE.name()) || status.equalsIgnoreCase(UserStatus.BLOCKED.name())
                    || status.equalsIgnoreCase(UserStatus.DELETE.name())) {
                    Optional<User> optionalUser = userDAO.getUserById(Long.parseLong(userId.trim()));
                    if (optionalUser.isPresent()) {
                        User user = optionalUser.get();
                        user.setStatus(UserStatus.valueOf(status.toUpperCase()));
                        int result = userDAO.update(user);
                        if (result == 1) {
                            return true;
                        }
                    } else {
                        throw new ServiceException("User by ID not found");
                    }

                } else {
                    throw new ServiceException("Unknown user status.");
                }
            }else {
                throw new ServiceException("Invalid ID value.");
            }

        }catch (DAOException e) {
            logger.error("Error in status update services.");
            throw new ServiceException("Error in status update services.", e);
        }
        return false;
    }

    @Override
    public boolean updatePassword(String newPassword, String email, String oldPassword) throws ServiceException {
        try {
System.out.println("updatePassword");
            UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
            Cipher cipher = UtilFactory.getInstance().getCipher();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (validator.isPassword(newPassword) && validator.isPassword(oldPassword)) {
                String oldPasswordCipher = cipher.getCipherString(oldPassword.trim());
                Optional<User> optionalUser = verification(email, oldPasswordCipher);
                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    user.setPassword(cipher.getCipherString(newPassword));
                    userDAO.updatePassword(user);
                    return true;
                } else {
                    throw new ServiceException("User not found");
                }
            } else {
                throw new ServiceException("Invalid values for newPassword.");
            }
    }catch (DAOException e) {
        logger.error("Error in services during registration.");
        throw new ServiceException("Error in services during registration.", e);
    }
    }

    @Override
    public boolean updateRole(String userId, String role) throws ServiceException {
        try {
            UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (validator.isNumber(userId)) {
                if (role.equalsIgnoreCase(UserRole.USER.name()) || role.equalsIgnoreCase(UserRole.ADMIN.name())
                        || role.equalsIgnoreCase(UserRole.MANAGER.name())) {
                    Optional<User> optionalUser = userDAO.getUserById(Long.parseLong(userId.trim()));
                    if (optionalUser.isPresent()) {
                        User user = optionalUser.get();
                        user.setRole(UserRole.valueOf(role.toUpperCase()));
                        int result = userDAO.update(user);
                        if (result == 1) {
                            return true;
                        }
                    } else {
                        throw new ServiceException("User by ID not found");
                    }

                } else {
                    throw new ServiceException("Unknown user role.");
                }
            }else {
                throw new ServiceException("Invalid ID value.");
            }

        }catch (DAOException e) {
            logger.error("Error in status update services.");
            throw new ServiceException("Error in status update services.", e);
        }
        return false;
    }
}
