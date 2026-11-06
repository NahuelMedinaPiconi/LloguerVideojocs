/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nahuelcompany.gestiovideojocs.dto;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Representa un lloguer d'un joc, en una plataforma concreta, per part d'un usuari.
 */
public class Rental {

    private int id;
    private Client client;
    private Game game;
    private Platform platform;
    private LocalDate startDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private boolean returned;

    public Rental() {
    }

    public Rental(int id, Client client, Game game, Platform platform,
                  LocalDate startDate, LocalDate dueDate) {
        this.id = id;
        this.client = client;
        this.game = game;
        this.platform = platform;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.returnDate = null;
        this.returned = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
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
        Rental other = (Rental) obj;
        return id == other.id;
    }

    @Override
    public String toString() {
        return "Rental{id=" + id
                + ", client=" + (client != null ? client.getName() : null)
                + ", game=" + (game != null ? game.getTitle() : null)
                + ", platform=" + platform
                + ", startDate=" + startDate
                + ", dueDate=" + dueDate
                + ", returnDate=" + returnDate
                + ", returned=" + returned + "}";
    }
    
    public String[] toStringArray() {
        String[] s = new String[5];
        s[0] = client != null ? client.getName() : "-";
        s[1] = game != null ? game.getTitle() : "-";
        s[2] = platform.toString();
        s[3] = startDate.toString();
        s[4] = dueDate.toString();
        s[5] = dueDate.isAfter(LocalDate.now()) && returned == false ? "verd" : "vermell";
        return s;
    }
}