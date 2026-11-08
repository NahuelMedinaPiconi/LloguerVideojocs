/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nahuelcompany.gestiovideojocs.dto;

import java.time.LocalDate;
import java.util.Objects;

public class Client {

    private int id;
    private String name;
    private String nationalId;
    private String phone;
    private LocalDate registrationDate;

    public Client() {
    }

    public Client(int id,String name, String nationalId, String phone, LocalDate registrationDate) {
        this.id = id;
        this.name = name;
        this.nationalId = nationalId;
        this.phone = phone;
        this.registrationDate = registrationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Client other = (Client) obj;
        return id == other.id;
    }

    @Override
    public String toString() {
        return name + " (" + nationalId + ")";
    }
}