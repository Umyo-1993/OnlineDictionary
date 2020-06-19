package com.example.onlinewordsmartapp;

public class GetSet {
    String wordname,wordmeaning,wordroot;

    public GetSet(String wordname, String wordmeaning, String wordroot) {
        this.wordname = wordname;
        this.wordmeaning = wordmeaning;
        this.wordroot = wordroot;
    }

    public String getWordname() {
        return wordname;
    }

    public void setWordname(String wordname) {
        this.wordname = wordname;
    }

    public String getWordmeaning() {
        return wordmeaning;
    }

    public void setWordmeaning(String wordmeaning) {
        this.wordmeaning = wordmeaning;
    }

    public String getWordroot() {
        return wordroot;
    }

    public void setWordroot(String wordroot) {
        this.wordroot = wordroot;
    }

    public GetSet()
    {

    }
}
