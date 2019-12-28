package com.example.odaksanmuhendislikv1;

public class Kullanicilar_Liste {


        private String departmanTxt;
        private String kullaniciAdi;
        private String kullaniciTel;

        public Kullanicilar_Liste()
        {

        }

        public Kullanicilar_Liste(String departmanTxt, String kullaniciAdi, String kullaniciTel)
        {
            this.departmanTxt=departmanTxt;
            this.kullaniciAdi=kullaniciAdi;
            this.kullaniciTel=kullaniciTel;
        }

        public String getDepartmanTxt() {
            return departmanTxt;
        }

        public String getKullaniciAdi() {
            return kullaniciAdi;
        }

        public String getKullaniciTel() {
            return kullaniciTel;
        }


}


