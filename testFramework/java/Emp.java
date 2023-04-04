package model;

import etu1900.framework.util.Crud;
import etu1900.framework.util.ModelView;


public class Emp {
    int id;
    String nom;

    public Emp(){}

    public Emp(int i, String n){
        this.id=i;
        this.nom=n;
    }

    public String getNom(){
        return this.nom;
    }

    public int getId(){
        return this.id;
    }

    public void setNom(String n){
        this.nom=n;
    }

    public void setId(int i){
        this.id=i;
    }

    @Crud(url = "/emp-all")
    public ModelView findALl(){
        ModelView mv = new ModelView();
        Emp[] listes= new Emp[5];
        listes[0]= new Emp(1,"Mirija");
        listes[1]= new Emp(2,"Toky");
        listes[2]= new Emp(3,"Mendrika");
        listes[3]= new Emp(4,"Tahiry");
        listes[4]= new Emp(5,"Jeremie");
        mv.setView("emp.jsp");
        mv.addItem("lst",listes);
        return mv;
        
    }

    @Crud(url = "/emp-save")
    public void save(){

    }
}
