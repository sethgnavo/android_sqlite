package com.alissa.newdatabase.model;

/**
 * Created by Seth-Pharès Gnavo on September 27, 2015.
 */

public class Participant {
    private int id;
    private String nom;
    private String prenom;

    public Participant() {

    }

    public Participant(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
