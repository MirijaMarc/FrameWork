package util;

import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;

public class Util {

    public static String getURL(HttpServletRequest request){
        String url =request.getRequestURI();
        String[] tab = url.split("/");
        String rep ="";
        for(int i=2;i<tab.length;i++){
            rep= rep.concat(tab[i]).concat("/");
        }
        return rep;
    }
}
