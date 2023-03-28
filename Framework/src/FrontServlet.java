package etu1900.framework.servlet;

import etu1900.framework.Mapping;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import etu1900.framework.util.ModelView;
import etu1900.framework.util.Util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;

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
                ModelView mv = (ModelView)(load.getDeclaredMethod(map.getMethod()).invoke(obj));
                RequestDispatcher dispatch = request.getRequestDispatcher(mv.getView());
                dispatch.forward(request,response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       

    }

}
