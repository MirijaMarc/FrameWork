package etu1900.framework.servlet;

import etu1900.framework.Mapping;
import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;
import etu1900.framework.util.Model;
import etu1900.framework.util.Auth;
import etu1900.framework.util.ModelView;
import etu1900.framework.util.FileUpload;
import etu1900.framework.util.Util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Map.Entry;

import com.google.gson.Gson;

import java.util.*;


@MultipartConfig
public class FrontServlet extends HttpServlet {
    HashMap<String, Mapping> MappingUrls =new HashMap<String,Mapping>();
    HashMap<String, Object> singletons= new HashMap<String, Object>();


    @Override
    public void init() throws ServletException {
        super.init();
        try {
            ClassLoader loader = getServletContext().getClassLoader();
            URI uri =loader.getResource("").toURI();
            File f = new File(uri);
            Util.initHashMap(f,MappingUrls);
            Util.initSingletons(f, singletons);
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
            String contentType = request.getContentType();
            PrintWriter out = response.getWriter();
            String key = Util.getURL(request);
            HttpSession session = request.getSession();
            boolean permission = false;

            if(MappingUrls.containsKey(key)){
                Mapping map = MappingUrls.get(key);
                Class load = Class.forName(map.getClassName());
                Object obj=null;
                if (singletons.get(map.getClassName())!=null){
                    obj= singletons.get(map.getClassName());
                }
                else{
                    obj= load.getConstructor().newInstance();
                }
                Field[] attributs = load.getDeclaredFields();
                Enumeration<String> parameterNames = request.getParameterNames();
                String[] params = Util.getParameters(parameterNames, request);
                System.out.println(params.length);
                Method[] methods = load.getDeclaredMethods();
                ModelView mv = new ModelView();
                for(Field attribut : attributs){
                    if (attribut.getType() == FileUpload.class && contentType!=null && contentType.startsWith("multipart/form-data" )){
                        attribut.setAccessible(true);
                        Part p = request.getPart(attribut.getName());
                        InputStream input = p.getInputStream();
                        String fileName = Paths.get(p.getSubmittedFileName()).getFileName().toString(); 
                        byte[] bytes = input.readAllBytes();
                        FileUpload f = new FileUpload(fileName,bytes);
                        attribut.set(obj, f);
                    }
                    if(request.getParameter(attribut.getName())!=null){
                        attribut.setAccessible(true);
                        String value = request.getParameter(attribut.getName());
                        attribut.set(obj, Util.convert(value, attribut.getType()));
                    }  
                }
                for (Method method : methods) {
                    if (method.getName().equals(map.getMethod())){
                        Auth auth = method.getAnnotation(Auth.class);
                        if (auth== null){
                            permission = true;
                        }
                        else{
                            String profil= auth.profil(); 
                            if(session.getAttribute(getInitParameter("sessionName")) != null){
                                if(profil.equals("")){
                                    permission = true;
                                }
                                else if(session.getAttribute(getInitParameter("sessionName")).equals(profil)){
                                    permission = true;
                                }
                            }else{
                                if(profil.equals("")){
                                    permission = true;
                                }
                            }
                        }
                        if (method.getParameterTypes().length>0){
                            if (!permission){
                                throw new Exception("Acces non autorisée");
                            }
                            Object[] parametres = Util.castParameters(params, method);
                            mv = (ModelView)(method.invoke(obj, parametres));
                            Util.initSession(session, mv);
                        }
                        else{
                            System.out.println("miditra");
                            if (!permission){
                                throw new Exception("Acces non autorisée");
                            }
                            mv = (ModelView)(method.invoke(obj));
                            Util.initSession(session, mv);
                        }
                    }
                }

                if (mv.isJson()){
                    Gson gson = new Gson();
                    HashMap<String, Object> data = mv.getData();
                    request.setAttribute(mv.getJsonName(), gson.toJson(data));
                    response.setContentType("application/json");
                }else{
                    HashMap<String, Object> data = mv.getData();
                    for(Entry mapentry : data.entrySet()){
                        request.setAttribute((String)mapentry.getKey(),mapentry.getValue());
                    } 
                }
                
                RequestDispatcher dispatch = request.getRequestDispatcher(mv.getView());
                dispatch.forward(request,response);
                
                
            }
        } catch (Exception e) {
            PrintWriter out = response.getWriter();
            e.printStackTrace();
            out.print(e);
        }
       

    }

}
