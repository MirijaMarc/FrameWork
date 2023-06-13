package model;

import etu1900.framework.util.*;

@Scope(value = "singleton")
public class Etudiant {
    int id;
    String nom;

    public Etudiant(){}

    public Etudiant(int i, String n){
        setId(i);
        setNom(n);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public int getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }

}
