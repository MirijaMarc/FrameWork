package model;
import etu1900.framework.util.Crud;
import etu1900.framework.util.ModelView;

public class Vue {
    
    @Crud(url="/mirija")
    public ModelView getJSP(){
        return new ModelView("hello.jsp");
    }

    @Crud(url="/formulaire")
    public ModelView getFormulaire(){
        return new ModelView("formulaire.jsp");
    }

    @Crud(url="/recherche")
    public ModelView recherche(){
        return new ModelView("recherche.jsp");
    }


}
