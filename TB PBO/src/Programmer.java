public class Programmer {
    public String judul;
    public String nama;
    public String nim;
    public String departemen;
    public String fakultas;
    public String universitas;
    public String tahun;


    //constructor
    public Programmer(){
        judul = "TUGAS BESAR PEMROGRAMAN BERORIENTASI OBJEK";
        nama = "Annisa Nurul Hakim";
        nim = "2211521007";
        departemen = "Departemen Sistem Informasi";
        fakultas = "Fakultas Teknologi Informasi";
        universitas = "Universitas Andalas";
        tahun = "TA 2023/2024";
    }


    public void toUpperCase() {
        judul = judul.toUpperCase();
        nama = nama.toUpperCase();
        nim = nim.toUpperCase();
        departemen = departemen.toUpperCase();
        fakultas = fakultas.toUpperCase();
        universitas = universitas.toUpperCase();
        tahun = tahun.toUpperCase();
    }


    public void display() {
        System.out.println("+---------------------------------------------+");
        System.out.printf("| %-43s |\n", judul);
        System.out.printf("| %-43s |\n", "");
        System.out.printf("| %-43s |\n", nama);
        System.out.printf("| %-43s |\n", nim);
        System.out.printf("| %-43s |\n", "");
        System.out.printf("| %-43s |\n", departemen);
        System.out.printf("| %-43s |\n", fakultas);
        System.out.printf("| %-43s |\n", universitas);
        System.out.printf("| %-43s |\n", tahun);
        System.out.println("+---------------------------------------------+");
    }
}
