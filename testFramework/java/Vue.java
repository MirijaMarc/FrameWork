package model;
import etu1900.framework.util.Crud;
import etu1900.framework.util.ModelView;

public class Vue {
    
    @Crud(url="/mirija")
    public ModelView getJSP(){
        return new ModelView("hello.jsp");
    }
}
