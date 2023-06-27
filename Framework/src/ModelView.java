package etu1900.framework.util;
import java.util.HashMap;
import java.util.Map;


public class ModelView{
    String view;
    HashMap<String, Object> data= new HashMap<String, Object>(); 
    HashMap<String, Object> sessions = new HashMap<String, Object>();

    public ModelView() {
    }
    public ModelView(String view) {
        this.view = view;
    }
    public String getView() {
        return view;
    }
    public void setView(String view) {
        this.view = view;
    }

    public HashMap<String, Object> getSessions() {
        return sessions;
    }

    public void setSessions(HashMap<String, Object> sessions) {
        this.sessions = sessions;
    }

    public HashMap<String, Object> getData() {
        return data;
    }
    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

    public void addItem(String key,Object value){
        this.data.putIfAbsent(key,value);
    }

    public void addSession(String key, Object value){
        this.sessions.putIfAbsent(key, value);
    }

    
}