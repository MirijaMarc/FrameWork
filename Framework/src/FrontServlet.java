package etu1900.framework.servlet;

import etu1900.framework.Mapping;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
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
        PrintWriter out = response.getWriter();
        ClassLoader cloader = getServletContext().getClassLoader();
        String path = cloader.getResource("/").getPath();
        out.println(path);

    }

}
