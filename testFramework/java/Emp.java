package model;

import etu1900.framework.util.Crud;
import etu1900.framework.util.FileUpload;
import etu1900.framework.util.ModelView;
import etu1900.framework.util.Scope;

@Scope(value = "singleton")
public class Emp {
    int id;
    String nom;
    String prenom;
    String datenaissance;
    FileUpload cv;

    public Emp(int id, String nom, String prenom, String datenaissance) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.datenaissance = datenaissance;
    }

    public Emp(){}

    public Emp(String nom, String prenom, String datenaissance, FileUpload cv) {
        this.nom = nom;
        this.prenom = prenom;
        this.datenaissance = datenaissance;
        this.cv = cv;
    }

    public Emp(String n, String p, String dtn){
        setNom(n);
        setPrenom(p);
        setDatenaissance(dtn);
    }

    public Emp(String n){
        this.nom=n;
    }

    public FileUpload getCv() {
        return cv;
    }

    public void setCv(FileUpload cv) {
        this.cv = cv;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Crud(url = "/emp-all.go")
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

    @Crud(url = "/emp-save.go")
    public ModelView save(){
        ModelView mv =new ModelView();
        Emp emp= new Emp(this.nom, this.prenom,this.datenaissance,this.cv);
        emp.setId(1);
        mv.setView("save.jsp");
        mv.addItem("emp",emp);
        return mv;
    }

    @Crud(url = "/findById.go")
    public ModelView findById(int id){
        ModelView mv = new ModelView();
        Emp result = new Emp(id, "mirija", "marc", "30/11/23");
        mv.setView("save.jsp");
        mv.addItem("emp", result);
        return mv;
    }


}
