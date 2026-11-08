/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nahuelcompany.gestiovideojocs.dto;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

/**
 * Representa un videojoc amb l'estoc independent per a cada plataforma.
 */
public class Game {

    private int id;
    private String title;
    private String genre;
    private Map<Platform, Integer> stock;

    public Game() {
        this.stock = new EnumMap<>(Platform.class);
    }

    public Game(int id, String title, String genre, Map<Platform, Integer> stock) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.stock = (stock != null) ? new EnumMap<>(stock) : new EnumMap<>(Platform.class);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Map<Platform, Integer> getStock() {
        return stock;
    }

    public void setStock(Map<Platform, Integer> stock) {
        this.stock = stock;
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
        Game other = (Game) obj;
        return id == other.id;
    }

    @Override
    public String toString() {
        return title + " (" + genre + ")";
    }
}
