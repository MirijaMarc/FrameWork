package model;

import etu1900.framework.util.Crud;
import etu1900.framework.util.Model;

@Model(table = "Emp")
public class Emp {

    @Crud(url = "/emp-all")
    public void findALl(){

    }

    @Crud(url = "/emp-save")
    public void save(){

    }
}
