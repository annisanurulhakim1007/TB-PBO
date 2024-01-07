import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;


//inheritance
public class Transaksi extends Pelanggan{
    static Connection conn;
    public String room;
    public Integer pilihRas;
    public Integer pilihKandang;


    String url = "jdbc:mysql://localhost:3307/cathouse";
    String uname = "root";
    String pass = "";


    public Transaksi() throws SQLException, ClassNotFoundException {
        initDatabaseConnection();
    }


    private void initDatabaseConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url, uname, pass);
    }


    Date date = new Date();
    String tanggal = String.format("yyyy-MM-dd", date);
   
    @Override
    public void ras() {
        //perulangan
        while (true) {
            System.out.println("Masukkan Ras Kucing\t: ");
            //exception handling
            try {
                pilihRas = scanner.nextInt();
   
                // percabangan
                if (pilihRas == 1) {
                    ras = "Domestic";
                    break;
                } else if (pilihRas == 2) {
                    ras = "Non Domestic";
                    break;
                } else {
                    System.out.println("Maaf, Pilihan Ras Tidak Tersedia!\n");
                }
            }
           
            catch (InputMismatchException e) {
                System.out.println("Mohon Masukkan Angka yang Valid!");
                scanner.next();
            }
        }
    }


    @Override
    public void jenisKandang() {
        //perulangan
        while (true) {
            System.out.println("Masukkan Jenis Kandang\t: ");
            //exception handling


            try {
                pilihKandang = scanner.nextInt();
   
                // percabangan
                if (pilihKandang == 1) {
                    jenisKandang = "Standard Room";
                    break;
                } else if (pilihKandang == 2) {
                    jenisKandang = "Deluxe Room";
                    break;
                } else if (pilihKandang == 3) {
                    jenisKandang = "VIP Room";
                    break;
                } else {
                    System.out.println("Maaf, Pilihan Kandang Tidak Tersedia!\n");
                }
            }
           
            catch (InputMismatchException e) {
                System.out.println("Mohon Masukkan Angka yang Valid!");
                scanner.next();
            }
        }
    }


    @Override
    public void biayaPenitipan(){
        //percabangan
        if (pilihRas == 1 && pilihKandang == 1){
            biayaPenitipan = 50000;
        }
        else if (pilihRas == 1 && pilihKandang == 2) {
            biayaPenitipan = 60000;
        }
        else if (pilihRas == 1 && pilihKandang == 3) {
            biayaPenitipan = 70000;
        }
        else if (pilihRas == 2 && pilihKandang == 1) {
            biayaPenitipan = 80000;
        }
        else if (pilihRas == 2 && pilihKandang == 2) {
            biayaPenitipan = 90000;
        }
        else if (pilihRas == 2 && pilihKandang == 3) {
            biayaPenitipan = 100000;
        }
    }


    //create
    public void tambahData() throws SQLException, ClassNotFoundException {
        //exception handling
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, uname, pass);


            System.out.println("\n===============================================");
            System.out.println("             TAMBAH DATA PENITIPAN             ");
            System.out.println("===============================================");
            noPenitipan();
            namaPemilik();
            noHP();
            namaKucing();
            ras();
            jenisKandang();
            biayaPenitipan();
            lamaPenitipan();
            totalBiaya();


            System.out.println("\n=====================================================");
            System.out.println("                 RINGKASAN RESERVASI                 ");
            System.out.println("=====================================================");
            System.out.println("Nama Kucing\t\t: " + namaKucing);
            System.out.println("Ras Kucing\t\t: " + ras);
            System.out.println("Jenis Kandang\t\t: " + jenisKandang);
            System.out.println("Biaya Penitipan\t\t: Rp " + biayaPenitipan);
            System.out.println("Lama Penitipan\t\t: " + lamaPenitipan + " Hari");
            System.out.println("SubTotal\t\t: Rp " + totalBiayaSebelumDiskon);
            System.out.println("Diskon\t\t\t: " + (persentaseDiskon * 100) + "%");
            System.out.println("Jumlah Diskon\t\t: Rp " + jumlahDiskon);
            System.out.println("Total Biaya\t\t: Rp " + getTotalBiaya());
            tanggal();
            waktu();
            System.out.println("=====================================================");


            String sql =
                "INSERT INTO penitipan (no_penitipan, nama_pemilik, no_hp, nama_kucing, ras, jenis_kandang, biaya_penitipan, lama_penitipan, total_biaya)" +
                "VALUES ('"+noPenitipan+"', '"+namaPemilik+"', '"+noHP+"', '"+namaKucing+"', '"+ras+"', '"+jenisKandang+"', '"+biayaPenitipan+"', '"+lamaPenitipan+"', '"+getTotalBiaya()+"')";


            Statement statement = conn.createStatement();
            statement.execute(sql);
            System.out.println("\nData Berhasil Dimasukkan!");
        }


        catch (SQLException e) {
            System.err.println("\nTerjadi Kesalahan Memasukkan Data!");
        }


        catch (InputMismatchException e) {
            System.err.println("\nMohon Masukkan Data dengan Benar!");
        }
    }


    //read
    public void lihatData() throws SQLException, ClassNotFoundException {
       
        System.out.println("\n========================================================================================================================================================================");
        System.out.println("                                                                        TAMPILKAN DATA PENITIPAN                                                                        ");
        System.out.println("========================================================================================================================================================================");


        //exception handling
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, uname, pass);
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT*FROM penitipan");


            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-2s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |\n",
            "No", "NO PENITIPAN", "NAMA PEMILIK", "NO HP", "NAMA KUCING", "RAS", "JENIS KANDANG", "BIAYA PENITIPAN", "LAMA PENITIPAN", "TOTAL BIAYA");      
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");


            int i = 1;
            while (result.next()){
                noPenitipan = result.getString("no_penitipan");
                namaPemilik = result.getString("nama_pemilik");
                noHP = result.getString("no_hp");
                namaKucing = result.getString("nama_kucing");
                ras = result.getString("ras");
                jenisKandang = result.getString("jenis_kandang");
                biayaPenitipan = result.getInt("biaya_penitipan");
                lamaPenitipan = result.getInt("lama_penitipan");


                totalBiayaSebelumDiskon = 0.0;
                persentaseDiskon = 0.0;
                jumlahDiskon = 0.0;


                totalBiaya();


                System.out.printf("| %-2s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |\n",
                i, noPenitipan, namaPemilik, noHP, namaKucing, ras, jenisKandang, biayaPenitipan, lamaPenitipan, getTotalBiaya());
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");


                i++;
            }
        }


        catch (SQLException e) {
            System.err.println("Terjadi Kesalahan Menampilkan Data!");
            System.err.println(e.getMessage());
        }
    }


    //update
    public void ubahData() throws SQLException, ClassNotFoundException {
        System.out.println("\n===============================================");
        System.out.println("              UBAH DATA PENITIPAN              ");
        System.out.println("===============================================");
   
        Scanner input = new Scanner(System.in);
   
        //exception handling
        try {
            boolean dataDitemukan = false;
            lihatData();
   
            while (!dataDitemukan) {
                System.out.print("\nMasukkan No Penitipan yang Akan Diubah\t: ");
                String noPenitipanToUpdate = input.nextLine();
   
                String existenceQuery = "SELECT * FROM penitipan WHERE no_penitipan = '"+noPenitipanToUpdate+"'";
                Statement existenceStatement = conn.createStatement();
                ResultSet existenceResult = existenceStatement.executeQuery(existenceQuery);
   
                if (existenceResult.next()) {
                    dataDitemukan = true;
   
                    String sql = "SELECT * FROM penitipan WHERE no_penitipan = '"+noPenitipanToUpdate+"'";
                    Statement statement = conn.createStatement();
                    ResultSet result = statement.executeQuery(sql);
   
                    if (result.next()) {
                        System.out.print("Nama Pemilik ["+result.getString("nama_pemilik")+"]\t\t\t: ");
                        String newNamaPemilik = input.next();
                        input.nextLine();
   
                        sql = "UPDATE penitipan SET nama_pemilik = '"+newNamaPemilik+"' WHERE no_penitipan = '"+noPenitipanToUpdate+"'";
   
                        if (statement.executeUpdate(sql) > 0) {
                            System.out.println("\nData Berhasil Diperbarui!");
                        }
                    }
                    statement.close();
                }
               
                else {
                    System.out.println("\nMaaf, Data Tidak Ditemukan. Silakan Coba Lagi!");
                }
   
                existenceStatement.close();
            }


            lihatData();
        }
       
        catch (SQLException e){
            System.err.println("Terjadi Kesalahan Mengubah Data!");
            System.err.println(e.getMessage());
        }
    }        


    //delete
    public void hapusData() throws SQLException, ClassNotFoundException {
        System.out.println("\n===============================================");
        System.out.println("              HAPUS DATA PENITIPAN             ");
        System.out.println("===============================================");
   
        Scanner input = new Scanner(System.in);
   
        //exception handling
        try {
            boolean dataDitemukan = false;
   
            while (!dataDitemukan) {
                lihatData();
   
                System.out.print("\nMasukkan No Penitipan yang Akan Dihapus\t: ");
                String noPenitipanToDelete = input.nextLine();
   
                String sql = "DELETE FROM penitipan WHERE no_penitipan = '"+noPenitipanToDelete+"'";
                Statement statement = conn.createStatement();
   
                if (statement.executeUpdate(sql) > 0) {
                    System.out.println("\nData Berhasil Dihapus!");
                    dataDitemukan = true;
                }
               
                else {
                    System.out.println("\nMaaf, Data Tidak Ditemukan. Silakan Coba Lagi!");
                }
            }
        }
       
        catch (SQLException e) {
            System.out.println("Terjadi Kesalahan Menghapus Data!");
            System.err.println(e.getMessage());
        }
   
        lihatData();
    }
   
    public void cariData() throws SQLException, ClassNotFoundException {
        System.out.println("\n===============================================");
        System.out.println("               CARI DATA PENITIPAN             ");
        System.out.println("===============================================");
   
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url, uname, pass);
   
        Scanner input = new Scanner(System.in);
   
        boolean dataDitemukan = false;
   
        do {
            System.out.print("Masukkan No Penitipan\t: ");
   
            String data = input.nextLine();
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM penitipan WHERE no_penitipan LIKE '%" + data + "%'";
            ResultSet result = statement.executeQuery(sql);
   
            tanggal();
   
            //exception handling
            try {
                System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.printf("| %-3s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |\n",
                "No", "No Penitipan", "Nama Pemilik", "No HP", "Nama Kucing", "Ras", "Jenis Kandang", "Biaya Penitipan", "Lama Penitipan", "Total Biaya");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
   
                int i = 1;
   
                while (result.next()) {
                    dataDitemukan = true;
   
                    noPenitipan = result.getString("no_penitipan");
                    namaPemilik = result.getString("nama_pemilik");
                    noHP = result.getString("no_hp");
                    namaKucing = result.getString("nama_kucing");
                    ras = result.getString("ras");
                    jenisKandang = result.getString("jenis_kandang");
                    biayaPenitipan = result.getInt("biaya_penitipan");
                    lamaPenitipan = result.getInt("lama_penitipan");
   
                    totalBiayaSebelumDiskon = 0.0;
                    persentaseDiskon = 0.0;
                    jumlahDiskon = 0.0;
   
                    totalBiaya();
   
                    System.out.printf("| %-3s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |\n",
                    i, noPenitipan, namaPemilik, noHP, namaKucing, ras, jenisKandang, biayaPenitipan, lamaPenitipan, getTotalBiaya());
                    System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
   
                    i++;
                }
   
                if (!dataDitemukan) {
                    System.out.println("Maaf, Data Tidak Ditemukan. Silakan Coba Lagi!");
                }
               
                else {
                    System.out.println("Data Berhasil Dicari!");
                }
            }
           
            catch (SQLException e) {
                System.out.println("Terjadi Kesalahan Mencari Data!");
                System.err.println(e.getMessage());
            }
        }
       
        while (!dataDitemukan);
    }
   
    public void resetData() throws SQLException, ClassNotFoundException {
        //exception handling
        try {
            initDatabaseConnection();
            String sql = "DELETE FROM penitipan";
            Statement statement = conn.createStatement();
 
            System.out.println("\n===============================================");
            System.out.println("              RESET DATA PENITIPAN             ");
            System.out.println("===============================================");


            if (statement.executeUpdate(sql) > 0) {
                System.out.println("\nData Berhasil Direset!");
            }
        }
       
        catch (SQLException e) {
            System.out.println("Terjadi Kesalahan Mereset Data!");
            System.err.println(e.getMessage());
        }


        lihatData();
    }


    public void closeConnection() {
        //exception handling
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Koneksi Ditutup!");
            }
        }
       
        catch (SQLException e) {
            System.err.println("Terjadi Kesalahan Menutup Koneksi!");
            System.err.println(e.getMessage());
        }
    }
}

