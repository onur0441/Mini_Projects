package depoProject;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class UrunIslemler {

    /*
    2-) methodlar olusturacagiz.
 *      urunTanimlama   ==>  urunun ismi, ureticisi ve birimi girilecek. id  alınacak.
 *
 *      urunListele     ==> tanimlanan urunler listelenecek. urunun adeti ve raf numarasi tanimlama yapilmadiysa default deger gorunsun.
 *
 *      urunGirisi      ==> giris yapmak istedigimiz urnunun id numarasi ile girecegiz.
 *
 *      urunuRafaKoy    ==> listeden urunu sececegiz ve id numarasina gore urunu rafa koyacagiz.
 *
 *      urunCikisi      ==> listeden urunu sececegiz ve urunun cikis yapcagiz. burada urun listesinden sadece miktarda degisiklik yapilacak.
 *                          urun adedi 0dan az olamaz. 0 olunca urun tanimlamasi silinmesin. sadece miktari 0 olsun.
 *      ===> yaptigimiz tum degisiklikler listede de gorunsun.
     */

    static Scanner input = new Scanner(System.in);
    Urunler urun =new Urunler();
    private Map<Integer, Urunler> urunListe= new HashMap<>();

    public void start() {

        int select= -1;
        do{
            try{
            System.out.println("*".repeat(50));
            System.out.println("Team 4 Deposuna Hoş Geldiniz!!!");
            System.out.println("Lütfen Yapmak istediğiniz işlemi seçin");
            System.out.println("Ürün Tanımlamak İçin      : 1");
            System.out.println("Ürün Listelemek İçin      : 2");
            System.out.println("Ürün Girişi İçin          : 3");
            System.out.println("Ürünü Rafa Koymak İçin    : 4");
            System.out.println("Ürün Çıkışı İçin          : 5");
            System.out.println("Sistemden Çıkmak İçin     : 0");
            System.out.println("*".repeat(50));
            select= input.nextInt();
            }catch (Exception e){
                System.out.println("Hatalı işlem yaptınız");
            }

            switch (select){
                case 1:
                    this.urunTanimlama();
                    break;
                case 2:
                    this.urunListele();
                    break;
                case 3:
                    this.urunGirisi();
                    break;
                case 4:
                    this.urunuRafaKoy();
                    break;
                case 5:
                    this.urunCikisi();
                    break;
                case 0:
                    System.out.println("İyi günler dileriz");
                    break;
                default:
                    System.out.println("Hatalı giriş yaptınız.");
            }



        }while (select!=0);



    }


    // urunTanimlama   ==>  urunun ismi, ureticisi ve birimi girilecek. id  alınacak.
    public void urunTanimlama() {
        input.nextLine();
        System.out.println("Lütfen ürün ismini giriniz");
        urun.setUrunIsmi(input.nextLine());

        System.out.println("Lütfen üretici ismini giriniz");
        urun.setUretici(input.nextLine());

        System.out.println("Lütfen ürün birimini giriniz");
        urun.setBirim(input.nextLine());

        urun.setMiktar(0);
        urun.setRaf("-");

        Urunler yeniUrun =new Urunler(urun.getUrunIsmi(), urun.getUretici(), urun.getMiktar(), urun.getBirim(), urun.getRaf());
        urunListe.put(urun.getId(),yeniUrun);

        urun.setId(urun.getId()+1);
        System.out.println("Ürün başarıyla tanımlandı.");
        System.out.println(urunListe);
    }



    // urunListele     ==> tanimlanan urunler listelenecek.
    // urunun adeti ve raf numarasi tanimlama yapilmadiysa default deger gorunsun.
    public void urunListele(){
        if (urunListe.isEmpty()) {
            System.out.println("Depoda kayıtlı ürün bulunmuyor.");
        } else {
            System.out.printf("%-5s %-20s %-20s %-20s %-20s %-20s \n","ID","Ürün İsmi","Üretici İsmi","Ürün Birimi","Ürün Miktarı","Ürün Raf");
            for (Map.Entry<Integer, Urunler> w : urunListe.entrySet()) {
                Urunler urun = w.getValue();
                int id = w.getKey();
                System.out.printf("%-5s %-20s %-20s %-20s %-20s %-20s \n", id, urun.getUrunIsmi(), urun.getUretici(), urun.getBirim(), urun.getMiktar(),urun.getRaf());
            }
        }

    }

    //urunGirisi      ==> giris yapmak istedigimiz urnunun id numarasi ile girecegiz.

    public void urunGirisi(){
        if (urunListe.isEmpty()) {
            System.out.println("Depoda kayıtlı ürün bulunmuyor.");
        } else {
            try {
                this.urunListele();
                System.out.println("Lütfen giriş yapmak istediğiniz ürünün id numarını giriniz");
                int girisId = input.nextInt();

                if (urunListe.keySet().contains(girisId)) {
                    System.out.println("İstediğiniz " + urunListe.get(girisId).getUrunIsmi() + " Miktarını Giriniz");

                    int yeniMiktar = input.nextInt();
                    int mevcutMiktar = urunListe.get(girisId).getMiktar();
                    if (yeniMiktar>0){
                        urunListe.get(girisId).setMiktar(mevcutMiktar + yeniMiktar);
                        System.out.println(urunListe.get(girisId).getUrunIsmi() + "Miktarı Güncellendi");
                    }else {
                        System.out.println("Miktar 0 dan büyük olmalı");
                    }


                } else {
                    System.out.println("Girmiş olduğunuz id ye ait ürünün tanımlaması yapılmamıştır.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Hatalı işlem yaptınız ");
                input.next();
            }
        }

    }

   //urunuRafaKoy    ==> listeden urunu sececegiz ve id numarasina gore urunu rafa koyacagiz.

    public void urunuRafaKoy(){
        this.urunListele();
        System.out.println("Lütfen giriş yapmak istediğiniz ürünün id numarını giriniz");
        int girisId = input.nextInt();
        input.nextLine();

        if (urunListe.keySet().contains(girisId)){
            System.out.println("Ürünü hangi rafa koymak istediğinizi yazın:");
            System.out.println("Raf A");
            System.out.println("Raf B");
            System.out.println("Raf C");
            System.out.println("Raf D");
            System.out.println("Raf E");

            String rafSecimi = input.nextLine();
            String rafAdi ="";
            switch (rafSecimi.toLowerCase()) {
                case "raf a":
                    rafAdi ="Raf A";
                    break;
                case "raf b":
                    rafAdi ="Raf B";
                    break;
                case "raf c":
                    rafAdi ="Raf C";
                    break;
                case "raf d":
                    rafAdi ="Raf D";
                    break;
                case "raf e":
                    rafAdi ="Raf E";
                    break;
                default:
                    System.out.println("Geçersiz raf seçimi. Ürün rafa konulamadı.");
                    return;
            }

            urunListe.get(girisId).setRaf(rafAdi);
            System.out.println("Ürün başarıyla " + rafAdi + " raflarına konuldu.");


        }else {
            System.out.println("Vermiş olduğunuz id ye ait ürünün tanımlaması yapılmamıştır.");
        }


    }

    //urunCikisi ==> listeden urunu sececegiz ve urunun cikis yapcagiz.
    //burada urun listesinden sadece miktarda degisiklik yapilacak.
    //urun adedi 0dan az olamaz. 0 olunca urun tanimlamasi silinmesin. sadece miktari 0 olsun.


    public void urunCikisi(){
        this.urunListele();
        boolean isValid = true;
        do {
            try{

                System.out.println("Lütfen çıkış yapmak istediğiniz ürünün id numarını giriniz");
                int girisId = input.nextInt();

                if (urunListe.keySet().contains(girisId)){
                    System.out.println( "Çıkış yapmak istediğiniz " + urunListe.get(girisId).getUrunIsmi() + " Miktarını Giriniz");
                    int cikisMiktar = input.nextInt();
                    int mevcutMiktar = urunListe.get(girisId).getMiktar();

                    if (cikisMiktar>0 && cikisMiktar <= mevcutMiktar){
                        urunListe.get(girisId).setMiktar(mevcutMiktar - cikisMiktar);
                        System.out.println("Ürün çıkışı yapıldı");
                        isValid =false;

                    }else {
                        System.out.println("Çıkış işlemi gerçekleştirilemedi");
                    }

                }else {
                    System.out.println("Vermiş olduğunuz id ye ait ürünün tanımlaması yapılmamıştır.");
                }
            }catch (Exception e){
                System.out.println("Ürün çıkışı işleminde bir hata oluştu:"+ e.getMessage());
                input.next();
            }
        }while (isValid);



    }
}
