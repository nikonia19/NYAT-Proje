package SogutucuUygulamasi;

public class Kullanici {
    private String kullaniciAdi;
    private String sifre;
    private int yas;
    private String cinsiyet;

    public Kullanici(KullaniciBuilder builder) {
        this.kullaniciAdi = builder.kullaniciAdi;
        this.sifre = builder.sifre;
        this.yas=builder.yas;
        this.cinsiyet=builder.cinsiyet;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    @Override
    public String toString() {
        return "Kullanici {" +
                "kullaniciAdi='" + kullaniciAdi + '\'' +
                ", sifre='" + sifre + '\'' +
                ", yas=" + yas +
                ", cinsiyet='" + cinsiyet + '\'' +
                '}';
    }

    public static class KullaniciBuilder{

        private String kullaniciAdi;
        private String sifre;
        private int yas;
        private String cinsiyet;

        public KullaniciBuilder(String kullaniciAdi,String sifre){
            this.kullaniciAdi=kullaniciAdi;
            this.sifre=sifre;
        }

        public KullaniciBuilder yas(int yas){
            this.yas=yas;
            return this;
        }
        public KullaniciBuilder cinsiyet(String cinsiyet){
            this.cinsiyet=cinsiyet;
            return this;
        }
        public Kullanici build(){
            return new Kullanici(this);
        }
    }
}
