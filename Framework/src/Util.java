package etu1900.framework.util;

import etu1900.framework.Mapping;
import jakarta.servlet.http.HttpServletRequest;

import java.beans.PropertyEditorManager;
import java.beans.PropertyEditorSupport;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

public class Util {


    public static Object[] castParameters(String[] params, Method m){
        Object[] rep = new Object[params.length];
        for (int i = 0; i < rep.length; i++) {
            Class<?>[] parametersTypes= m.getParameterTypes();
            rep[i] = convert(params[i], parametersTypes[i]);
        }
        return rep;
    }

    
    public static <T> T convert (String value, Class<T> type){
        PropertyEditorSupport editor = (PropertyEditorSupport) PropertyEditorManager.findEditor(type);
        editor.setAsText(value);
        return (T) editor.getValue();
    }


    public static String[] getParameters(Enumeration<String> values,HttpServletRequest request){
        ArrayList<String> list = new ArrayList<>();
        while (values.hasMoreElements()) {
            list.add(values.nextElement());
        }
        String[] rep = new String[list.size()];
        for (int i = 0; i < rep.length; i++) {
            rep[i] = request.getParameter(list.get(i)); 
        }
        return rep;
    }


    public static void initHashMap(File f, HashMap<String, Mapping> mappingUrls)throws Exception{
        ArrayList<Class> tab= Util.getALlAnnotedClasse(f);
        for (Class aClass : tab){
            ArrayList<Method> methods = Util.getAllMethodAnnoted(aClass);
            for (Method method : methods) {
                if (method.getAnnotations()[0] instanceof Crud){
                    Mapping m = new Mapping(aClass.getName(),method.getName());
                    String key = ((Crud)(method.getAnnotations()[0])).url();
                    mappingUrls.putIfAbsent(key, m);
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


    public static ArrayList<String>getAllCLassName(File f,ArrayList<String> tab,String pack){
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
