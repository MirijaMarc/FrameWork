package model;

import etu1900.framework.util.Crud;
import etu1900.framework.util.ModelView;



public class Emp {
    String nom;
    String prenom;
    String datenaissance;

    public Emp(){}

    public Emp(String n){
        this.nom=n;
    }
    
    public Emp(String n, String p, String dtn){
        setNom(n);
        setPrenom(p);
        setDatenaissance(dtn);
    }

    public String getNom(){
        return this.nom;
    }

    public String getDatenaissance() {
        return datenaissance;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setDatenaissance(String datenaissance) {
        this.datenaissance = datenaissance;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }


    public void setNom(String n){
        this.nom=n;
    }

    @Crud(url = "/emp-all")
    public ModelView findALl(){
        ModelView mv = new ModelView();
        Emp[] listes= new Emp[5];
        listes[0]= new Emp("Mirija");
        listes[1]= new Emp("Toky");
        listes[2]= new Emp("Mendrika");
        listes[3]= new Emp("Tahiry");
        listes[4]= new Emp("Jeremie");
        mv.setView("emp.jsp");
        mv.addItem("lst",listes);
        return mv;
    }

    @Crud(url = "/emp-save")
    public ModelView save(){
        ModelView mv =new ModelView();
        Emp emp= new Emp(this.nom, this.prenom,this.datenaissance);
        mv.setView("save.jsp");
        mv.addItem("emp",emp);
        return mv;
    }

}
