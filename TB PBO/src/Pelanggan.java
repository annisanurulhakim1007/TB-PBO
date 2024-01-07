
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


//implementasi interface
public class Pelanggan implements Penitipan{
    public String noPenitipan;
    public String namaPemilik;
    public String noHP;
    public String namaKucing;
    public String ras;
    public String jenisKandang;
    public Integer biayaPenitipan;
    public Integer lamaPenitipan;
    public double totalBiayaSebelumDiskon;
    public double persentaseDiskon = 0.0;
    public double jumlahDiskon;
    public double totalBiayaSetelahDiskon;


    Scanner scanner = new Scanner(System.in);


    //constructor
    public Pelanggan(){
        noPenitipan = "007";
        namaPemilik = "Annisa Nurul Hakim";
        noHP = "081234567890";
        namaKucing = "Faza";
        ras = "Domestic";
        jenisKandang = "VIP Room";
        biayaPenitipan = 70000;
        lamaPenitipan = 3;
    }


    public void tanggal(){
        Date date = new Date();
        SimpleDateFormat tanggal = new SimpleDateFormat("EEEE, dd-MM-yyyy");
        System.out.println("Tanggal Transaksi\t: " + tanggal.format(date));
    }


    public void waktu(){
        Date time = new Date();
        SimpleDateFormat waktu = new SimpleDateFormat("HH:mm:ss");
        System.out.println("Waktu Transaksi\t\t: " + waktu.format(time));
    }
   
    @Override
    public void noPenitipan(){
        System.out.print("Masukkan No Penitipan\t: ");
        noPenitipan = scanner.next();
    }


    @Override
    public void namaPemilik(){
        System.out.print("Masukkan Nama Pemilik\t: ");
        namaPemilik = scanner.next();
    }


    @Override
    public void noHP(){
        System.out.print("Masukkan No HP\t\t: ");
        noHP = scanner.next();
    }


    @Override
    public void namaKucing(){
        System.out.print("Masukkan Nama Kucing\t: ");
        namaKucing = scanner.next();
    }


    @Override
    public void ras(){
        System.out.print("Masukkan Ras Kucing\t: ");
        ras = scanner.next();
    }


    @Override
    public void jenisKandang(){
        System.out.print("Masukkan Jenis Kandang\t: ");
        jenisKandang = scanner.next();
    }


    @Override
    public void biayaPenitipan(){
        System.out.print("Masukkan Biaya Penitipan\t: ");
        biayaPenitipan = scanner.nextInt();
    }


    @Override
    public void lamaPenitipan(){
        System.out.print("Masukkan Lama Penitipan\t: ");
        lamaPenitipan = scanner.nextInt();
    }


    @Override
    public void totalBiaya() {
        //proses matematika
        totalBiayaSebelumDiskon = biayaPenitipan * lamaPenitipan;


        if (totalBiayaSebelumDiskon > 200000 && totalBiayaSebelumDiskon < 300000) {
            persentaseDiskon = 0.05;
        }
        else if (totalBiayaSebelumDiskon >= 300000) {
            persentaseDiskon = 0.1;
        }


        jumlahDiskon = persentaseDiskon * totalBiayaSebelumDiskon;
        totalBiayaSetelahDiskon = totalBiayaSebelumDiskon - jumlahDiskon;
    }


    public double getTotalBiaya() {
        return totalBiayaSetelahDiskon;
    }
}