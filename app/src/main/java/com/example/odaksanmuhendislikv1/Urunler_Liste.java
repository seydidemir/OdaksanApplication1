package com.example.odaksanmuhendislikv1;

public class Urunler_Liste {

    private String urunAdi;
    private String urunFiyati;

    public Urunler_Liste()
    {

    }

    public Urunler_Liste(String urunAdi,String urunFiyati)
    {
        this.urunAdi=urunAdi;
        this.urunFiyati=urunFiyati;
    }

    public String getUrunAdi() {
        return urunAdi;
    }

    public String getUrunFiyati() {
        return urunFiyati;
    }
}
