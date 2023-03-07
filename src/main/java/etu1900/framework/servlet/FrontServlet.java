package etu1900.framework.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

public class FrontServlet extends HttpServlet {
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
        String url =request.getRequestURI();
        String[] tab = url.split("/");
        for(int i=0;i<tab.length;i++){
            out.println(tab[i]);
        }
    }
}
