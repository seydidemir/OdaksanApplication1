package com.example.odaksanmuhendislikv1;

public class Tüm_Teklifler extends TeklifID {
    String teklifler ,status;;

    public  Tüm_Teklifler(){


    }


    public Tüm_Teklifler(String teklifler, String status) {
        this.teklifler = teklifler;
        this.status = status;
    }



    public String getTeklifler() {
        return teklifler;
    }

    public void setTeklifler(String teklifler) {
        this.teklifler = teklifler;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
