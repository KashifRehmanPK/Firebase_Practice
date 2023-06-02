package com.example.firebase.Fifteen;

public class model_file_info {

    String filename,fileurl;

    public model_file_info() {
    }

    public model_file_info(String filename, String fileurl) {
        this.filename = filename;
        this.fileurl = fileurl;
    }


    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }
}
