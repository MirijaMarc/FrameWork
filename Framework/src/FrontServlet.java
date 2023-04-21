package etu1900.framework.servlet;

import etu1900.framework.Mapping;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import etu1900.framework.util.Model;
import etu1900.framework.util.ModelView;
import etu1900.framework.util.Util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map.Entry;

public class FrontServlet extends HttpServlet {
    HashMap<String, Mapping> MappingUrls =new HashMap<String,Mapping>();


    @Override
    public void init() throws ServletException {
        super.init();
        try {
            ClassLoader loader = getServletContext().getClassLoader();
            URI uri =loader.getResource("").toURI();
            System.out.println(uri);
            File f = new File(uri);
            Util.initHashMap(f,MappingUrls);
            System.out.println(MappingUrls.isEmpty());
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    public void processRequest(HttpServletRequest request,  HttpServletResponse response)throws IOException{
        try {
            PrintWriter out = response.getWriter();
            String key = Util.getURL(request);
            if(MappingUrls.containsKey(key)){
                Mapping map = MappingUrls.get(key);
                Class load = Class.forName(map.getClassName());
                Object obj = load.getConstructor().newInstance();
                Field[] attributs = load.getDeclaredFields();
                out.println("midira");
                for(Field attribut : attributs){
                    String texte = attribut.getName();
                    String resultat = texte.substring(0, 1).toUpperCase() + texte.substring(1).toLowerCase();
                    if(request.getParameter(attribut.getName())!=null){
                        Field nomField=load.getDeclaredField(attribut.getName());
                        nomField.setAccessible(true);
                        nomField.set(obj, request.getParameter(attribut.getName()));
                    }
                }
                ModelView mv = (ModelView)(load.getDeclaredMethod(map.getMethod()).invoke(obj));
                HashMap<String, Object> data = mv.getData();
                for(Entry mapentry : data.entrySet()){
                    request.setAttribute((String)mapentry.getKey(),mapentry.getValue());
                }
                RequestDispatcher dispatch = request.getRequestDispatcher(mv.getView());
                dispatch.forward(request,response);
                
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       

    }

}
