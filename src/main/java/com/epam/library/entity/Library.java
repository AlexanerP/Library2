package com.epam.library.entity;

import java.io.Serializable;

public class Library implements Serializable {


    private int libraryId;
    private String city;
    private String street;

    public Library(String city) {
        this.city = city;
    }

    public Library() {
    }

    public int getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(int libraryId) {
        this.libraryId = libraryId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Library library = (Library) obj;

        if (libraryId != library.libraryId) {
            return false;
        }
        if (city != null ? !city.equals(library.city) : library.city != null) {
            return false;
        }
        return street != null ? street.equals(library.street) : library.street == null;
    }

    @Override
    public int hashCode() {
        int result = libraryId;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuffer line = new StringBuffer();
        line.append("Library{").append("libraryId='").append(libraryId)
                .append("', city='").append(city)
                .append("', street='").append(street).append('}');
        return line.toString();
    }
}
