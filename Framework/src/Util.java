package etu1900.framework.util;

import etu1900.framework.Mapping;
import etu1900.framework.util.ModelView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.beans.PropertyEditorManager;
import java.beans.PropertyEditorSupport;
import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Util {


    public static void initSession(HttpSession session, ModelView mv){
        HashMap<String, Object> sessions = mv.getSessions();
            for(Entry mapentry : sessions.entrySet()){
                session.setAttribute((String)mapentry.getKey(),mapentry.getValue());
            } 
    }

    public static <T> T convert (String value, Class<T> type){
        PropertyEditorSupport editor = (PropertyEditorSupport) PropertyEditorManager.findEditor(type);
        editor.setAsText(value);
        return (T) editor.getValue();
    }

    public static Object[] castParameters(String[] params, Method m){
        Object[] rep = new Object[params.length];
        for (int i = 0; i < rep.length; i++) {
            Class<?>[] parametersTypes= m.getParameterTypes();
            if (parametersTypes[i] == int.class) rep[i]= Integer.valueOf(params[i]);
            else if (parametersTypes[i] == double.class) rep[i]= Double.valueOf(params[i]);
            else if (parametersTypes[i] == float.class) rep[i]= Float.valueOf(params[i]);
            else rep[i]= parametersTypes[i].cast(params[i]);
        }
        return rep;
    }


    public static String[] getParameters(Enumeration<String> values,HttpServletRequest request){
        ArrayList<String> list = new ArrayList<>();
        while (values.hasMoreElements()) {
            list.add(values.nextElement());
        }
        String [] nomparams = new String[list.size()];
        nomparams= list.toArray(nomparams);
        String[] rep = new String[list.size()];
        for (int i = 0; i < rep.length; i++) {
            rep[i] = request.getParameter(nomparams[i]); 
        }
        return rep;

    }

     public static void initSingletons(File f, HashMap<String, Object> singletons)throws Exception{
        ArrayList<String> tab= Util.getAllCLassName(f,new ArrayList<String>(), "");
        for (String item : tab){
            Class aClass = Class.forName(item);
            if (aClass.getAnnotations().length>0){
                if (aClass.getAnnotations()[0] instanceof Scope){
                    String value = ((Scope)(aClass.getAnnotations()[0])).value();
                    if (value.equalsIgnoreCase("singleton")){
                        Object obj= aClass.getConstructor().newInstance();
                        singletons.putIfAbsent(item, obj);
                    }
                }
            }
        }
    }


    public static void initHashMap(File f, HashMap<String, Mapping> mappingUrls)throws Exception{
        ArrayList<Class> tab= Util.getALlAnnotedClasse(f);
        for (Class aClass : tab){
            ArrayList<Method> methods = Util.getAllMethodAnnoted(aClass);
            for (Method method : methods) {
                for (Annotation annotation :method.getAnnotations()){
                    if (annotation instanceof Crud){
                        Mapping m = new Mapping(aClass.getName(),method.getName());
                        String key = ((Crud)(annotation)).url();
                        mappingUrls.putIfAbsent(key, m);
                    }
                }
            }
        }
    }


    public static ArrayList<Class> getALlAnnotedClasse(File f)throws Exception{
        ArrayList<Class> allclass= new ArrayList<Class>();
        ArrayList<String> allClasseName = Util.getAllCLassName(f,new ArrayList<String>(),"");
        for (String s : allClasseName) {
            Class c = Class.forName(s);
                allclass.add(c);
        }
        return allclass;
    }


    public static ArrayList<String> getAllCLassName(File f,ArrayList<String> tab,String pack){
        File[] files = f.listFiles();
        for(int i=0;i<files.length;i++){
            if (files[i].isFile()){
                String nomfile= files[i].getName().split("\\.")[0];
                tab.add(pack.concat(nomfile));
            }else{
                String dossier = pack+files[i].getName()+".";
                getAllCLassName(files[i],tab,dossier);
            }
        }
        return tab;
    }

    public static ArrayList<Method> getAllMethodAnnoted(Class classe){
        Method[] methods= classe.getDeclaredMethods();
        ArrayList<Method> rep = new ArrayList<Method>();
        for (Method method : methods){
            if (method.getAnnotations().length>0){
                rep.add(method);
            }
        }
        return rep;
    }

    public static String getURL(HttpServletRequest request){
        String url =request.getRequestURI();
        String[] tab = url.split("/");
        String rep ="";
        return "/"+tab[2];
    }


    public static ArrayList<Field> getAllAnnotedAttribut(Class classe){
        Field[] fields = classe.getDeclaredFields();
        ArrayList<Field> rep = new ArrayList<Field>();
        for (Field field : fields) {
            if (field.getAnnotations().length!=0){
                rep.add(field);
            }
        }
        return rep;
    }
}
