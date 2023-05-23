package etu1900.framework.util; 

public class FileUpload {
    String name;
    byte[] file;

    
    public FileUpload() {
    }
    
    public FileUpload(String name,byte[] file) {
        this.name = name;
        this.file = file;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }


    
}
