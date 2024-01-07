import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.sql.SQLException;


public class Cathouse {
    static Connection conn;
    static LocalDate date = LocalDate.now();
    static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMMM yyyy");
    static String tanggal = date.format(dateFormat);


    public String driver = "com.mysql.jdbc.Driver";
    public static String url = "jdbc:mysql://localhost:3307/cathouse";
    public static String uname = "root";
    public static String pass = "";


    public static boolean login (String username, String password){
        String validUsername = "faza.cathouse";
        String validPassword = "1007";
        return validUsername.equals(username) && validPassword.equals(password);
    }


    public static String captcha(){
        return "01M05f";
    }
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);


        boolean isValidLogin = false;
        boolean isValidCaptcha = false;


        String pilihan;
        boolean lanjutkan = true;


        Transaksi transaksi = new Transaksi();


        System.out.println("");
        Programmer mahasiswa = new Programmer();
        mahasiswa.display();
        System.out.println("\nUbah Data Menjadi toUpperCase :\n");
        mahasiswa.toUpperCase();
        mahasiswa.display();
        System.out.println("");


        //exception handling
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, uname, pass);
            System.out.println("MySQL JDBC Driver Berhasil Ditemukan!\n");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: MySQL JDBC Driver Tidak Berhasil Ditemukan!");
            System.exit(0);
        } catch (SQLException e) {
            System.err.println("Error: Gagal Melakukan Koneksi ke Database!");
        }
       
        //perulangan
        do {
            System.out.print("Username \t\t: ");
            String username = scanner.nextLine();


            System.out.print("Password \t\t: ");
            String password = scanner.nextLine();


            isValidLogin = login(username, password);
           
            if (!isValidLogin){
                System.out.println("Login Gagal. Silakan Coba Lagi!\n");
            }
        } while (!isValidLogin);


        //perulangan
        do {
            String expectedCaptcha = captcha();
            System.out.println("\nCAPTCHA \t\t: " + expectedCaptcha);


            System.out.print("Masukkan CAPTCHA \t: ");
            String enteredCaptcha = scanner.nextLine();


            isValidCaptcha = expectedCaptcha.equalsIgnoreCase(enteredCaptcha);


            if (!isValidCaptcha){
                System.out.println("CAPTCHA Salah. Silakan Coba Lagi!");
            }
        } while (!isValidCaptcha);


        System.out.println("\nLOGIN BERHASIL!\n");


        System.out.println("+=======================================+");
        System.out.println("|           SELAMAT DATANG DI           |");
        System.out.println("|             FAZA CATHOUSE             |");
        System.out.println("+---------------------------------------+");
        System.out.println("|            "+tanggal+"            |");
        System.out.println("+=======================================+");
        System.out.println("");


        //perulangan
        do {
            System.out.println("\n+---------------------------------------+");
            System.out.println("|              DAFTAR MENU              |");
            System.out.println("+---------------------------------------+");
            System.out.println("|     [1] Tambahkan Data Penitipan      |");
            System.out.println("|     [2] Tampilkan Data Penitipan      |");
            System.out.println("|     [3] Ubah Data Penitipan           |");
            System.out.println("|     [4] Hapus Data Penitipan          |");
            System.out.println("|     [5] Cari Data Penitipan           |");
            System.out.println("|     [6] Reset Data Penitipan          |");
            System.out.println("|     [7] Keluar                        |");
            System.out.println("+---------------------------------------+");


            System.out.print("\nMasukkan Pilihan Anda [1,2,3,4,5,6,7] : ");
            pilihan = scanner.next();


            //percabangan
            switch (pilihan){
                case "1" :
                    //collection framework: hashmap
                    Map<String, Map<String, Integer>> biayaPenitipan = new HashMap<>();


                    Map<String, Integer> standard = new HashMap<>();
                    standard.put("Domestic", 50000);
                    standard.put("Non Domestic", 80000);
                    biayaPenitipan.put("Standard Room", standard);


                    Map<String, Integer> deluxe = new HashMap<>();
                    deluxe.put("Domestic", 60000);
                    deluxe.put("Non Domestic", 90000);
                    biayaPenitipan.put("Deluxe Room", deluxe);


                    Map<String, Integer> vip = new HashMap<>();
                    vip.put("Domestic", 70000);
                    vip.put("Non Domestic", 100000);
                    biayaPenitipan.put("VIP Room", vip);
               
                    System.out.println("\n+-----------------------------------------------+");
                    System.out.printf("| %-15s | %-12s | %-12s |\n", "Jenis Ruangan", "Domestic", "Non Domestic");
                    System.out.println("+-----------------------------------------------+");


                    for (Map.Entry<String, Map<String, Integer>> entry : biayaPenitipan.entrySet()) {
                        String jenisRuangan = entry.getKey();
                        Map<String, Integer> harga = entry.getValue();


                        System.out.printf("| %-15s | %-12s | %-12s |\n", jenisRuangan, harga.get("Domestic"), harga.get("Non Domestic"));
                    }  


                    System.out.println("+-----------------------------------------------+");
                    System.out.println("\nUkuran HashMap Standard Room\t: " + standard.size());
                    System.out.println("Ukuran HashMap Deluxe Room\t: " + deluxe.size());
                    System.out.println("Ukuran HashMap VIP Room\t\t: " + vip.size());


                    transaksi.tambahData();
                    break;


                case "2" :
                    transaksi.lihatData();
                    break;


                case "3" :
                    transaksi.ubahData();
                    break;


                case "4" :
                    transaksi.hapusData();
                    break;


                case "5" :
                    transaksi.cariData();
                    break;


                case "6" :
                    transaksi.resetData();
                    break;


                case "7" :
                    System.out.println("+---------------------------------------+");
                    System.out.println("|         PROGRAM TELAH SELESAI         |");
                    System.out.println("+---------------------------------------+");
                    break;
                   
                default :
                    System.out.println("Maaf, Pilihan Anda Tidak Tersedia!");
                    break;
            }


            System.out.println("\nApakah Anda Masih Ingin Melajutkan? [y/n]");
            pilihan = scanner.next();
            lanjutkan = pilihan.equalsIgnoreCase("y");


        }


        while (lanjutkan);
            System.out.println("\n+=======================================+");
            System.out.println("|             TERIMA KASIH!             |");
            System.out.println("+=======================================+\n");
    }
}







