package depoProject;

public class Urunler {

    // * 1-) burada oncelikle urunun tanimlamasi  yapilir.
    // id urunIsmi uretici miktar birim ve raf (6 adet fields mevcut)


    private int id =100;
    private String urunIsmi;
    private String uretici;
    private int miktar;
    private String birim;
    private String raf;

    public Urunler() {
    }

    public Urunler(String urunIsmi, String uretici, int miktar, String birim, String raf) {
        this.urunIsmi = urunIsmi;
        this.uretici = uretici;
        this.miktar = miktar;
        this.birim = birim;
        this.raf = raf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrunIsmi() {

        return urunIsmi.toLowerCase();
    }

    public void setUrunIsmi(String urunIsmi) {
        this.urunIsmi = urunIsmi;
    }

    public String getUretici() {
        return uretici.toLowerCase();
    }

    public void setUretici(String uretici) {
        this.uretici = uretici;
    }

    public int getMiktar() {

        return miktar;
    }

    public void setMiktar(int miktar) {

        this.miktar = miktar;

    }

    public String getBirim() {
        return birim.toLowerCase();
    }

    public void setBirim(String birim) {
        this.birim = birim;
    }

    public String getRaf() {
        return raf.toLowerCase();
    }

    public void setRaf(String raf) {
        this.raf = raf;
    }

    @Override
    public String toString() {
        return  " urunIsmi='" + urunIsmi + '\'' +
                ", uretici='" + uretici + '\'' +
                ", miktar=" + miktar +
                ", birim='" + birim + '\'' +
                ", raf='" + raf + '\'' +
                '}';
    }
}
