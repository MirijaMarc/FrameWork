package model;
import etu1900.framework.util.Auth;
import etu1900.framework.util.Crud;
import etu1900.framework.util.ModelView;

public class Vue {
    
    @Crud(url="/mirija.go")
    public ModelView getJSP(){
        return new ModelView("hello.jsp");
    }

    @Crud(url="/formulaire.go")
    @Auth(profil = "")
    public ModelView getFormulaire(){
        return new ModelView("formulaire.jsp");
    }

    @Crud(url="/recherche.go")
    public ModelView recherche(){
        return new ModelView("recherche.jsp");
    }

    @Crud(url="/upload.go")
    public ModelView upload(){
        return new ModelView("test.jsp");
    }

    @Crud(url="/login.go")
    public ModelView login(){
        ModelView mv = new ModelView("login.jsp");
        mv.addSession("user","Mirija");
        return mv;

    }

   


}
