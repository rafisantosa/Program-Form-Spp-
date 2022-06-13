package pbo_m13_520411352;

public class Mahasiswa {
    private String nama,nim,smt,prodi;
    private Integer angkatan,sks;

    //constructor Kosong
    public Mahasiswa() {
    }
    
    //Constructor Dengan Parameter
    public Mahasiswa(String nama, String nim, Integer angkatan,String smt, String prodi, Integer sks) {
        this.nama = nama;
        this.nim = nim;
        this.smt = smt;
        this.prodi = prodi;
        this.angkatan = angkatan;
        this.sks = sks;
    }
    
    public Mahasiswa(Integer angkatan){
        this.angkatan=angkatan;
    }
    //kode bars ke 22-44 merupakan Setter
    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public void setSmt(String smt) {
        this.smt = smt;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public void setAngkatan(Integer angkatan) {
        this.angkatan = angkatan;
    }

    public void setSks(Integer sks) {
        this.sks = sks;
    }
    
    //kode bars ke 47-69 merupakan Setter
    public String getNama() {
        return nama;
    }

    public String getNim() {
        return nim;
    }

    public String getSmt() {
        return smt;
    }

    public String getProdi() {
        return prodi;
    }

    public Integer getAngkatan() {
        return angkatan;
    }

    public Integer getSks() {
        return sks;
    }
    
    //Method untuk mencetak Info Mahasiswa
    public String cetakInfoMahasiswa(){
        //membentuk objek StringBuilder
        StringBuilder sb = new StringBuilder();
        sb.append("==============INFO MAHASISWA=============");
        sb.append("\nNama\t: ").append(getNama());
        sb.append("\nNIM\t: ").append(getNim());
        sb.append("\nAngkatan\t: ").append(getAngkatan());
        sb.append("\nSemester\t: ").append(getSmt());
        sb.append("\nPreogram Studi: ").append(getProdi());
        sb.append("\nJmlah SKS\t: ").append(getSks());
        sb.append("\n\n");
        
        return sb.toString();
        
    }
    //Method Untuk Menghitung SppTetap
    public String hitungSPPTetap(){
        //membentuk objek StringBuilder
        StringBuilder SPPT= new StringBuilder();
        
        //dellarasi Variabel
        int SPPTetap;
        int angkt=getAngkatan();
        
        //kondisi if dan switch case untuk menentukan prodi & angkatan
        if(getProdi().equals("Teknik Informatika")){
            //Switch case untuk menentukan angkatan berdasarkan nilai atribut angkatan
            switch(angkt){
                case 2018:
                    SPPTetap=2050000;
                    SPPT.append(SPPTetap);
                    break;
                case 2019:
                    SPPTetap=2050000;
                    SPPT.append(SPPTetap);
                    break;
                case 2020:
                    SPPTetap=2150000;
                    SPPT.append(SPPTetap);
                    break;
                case 2021:
                    SPPTetap=2250000;
                    SPPT.append(SPPTetap);
                    break;
            }
        
        }else if(getProdi().equals("Teknik Industri")){
            //Switch case untuk menentukan angkatan berdasarkan nilai atribut angkatan
            switch(angkt){
                case 2018:
                    SPPTetap=1650000;
                    SPPT.append(SPPTetap);
                    break;
                case 2019:
                    SPPTetap=1650000;
                    SPPT.append(SPPTetap);
                    break;
                case 2020:
                    SPPTetap=1700000;
                    SPPT.append(SPPTetap);
                    break;
                case 2021:
                    SPPTetap=1750000;
                    SPPT.append(SPPTetap);
                    break;
            }        
        }
        return SPPT.toString();
    }
    //method untuk menghitung SppVariabel
    public String hitungSPPVariabel(){
        //membentuk objek StringBuilder
        StringBuilder SPPV = new StringBuilder();
        //dekalrasi variabel 
        int angkt=getAngkatan();
        int sppvar;
        int sk=getSks();
         //kondisi if dan switch case untuk menentukan prodi & angkatan
        if (getProdi().equals("Teknik Informatika")){
            //Switch case untuk menentukan angkatan berdasarkan nilai atribut angkatan
            switch(angkt){
                case 2018:
                    sppvar= sk*240000;
                    SPPV.append(sppvar);
                    break;
                case 2019:
                    sppvar= sk*240000;
                    SPPV.append(sppvar);
                    break;
                case 2020:
                    sppvar= sk*245000;
                    SPPV.append(sppvar);
                    break;
                case 2021:
                    sppvar= sk*250000;
                    SPPV.append(sppvar); 
                    break;
            }
        }else if (getProdi().equals("Teknik Industri")){
            //Switch case untuk menentukan angkatan berdasarkan nilai atribut angkatan
            switch(angkt){
                case 2018:
                    sppvar= sk*170000;
                    SPPV.append(sppvar);
                    break;
                case 2019:
                    sppvar= sk*170000;
                    SPPV.append(sppvar);
                    break;
                case 2020:
                    sppvar= sk*180000;
                    SPPV.append(sppvar);
                    break;
                case 2021:
                    sppvar= sk*190000;
                    SPPV.append(sppvar); 
                    break;
            }
        }
        return SPPV.toString();
    }
    //method untuk menghitung total tagihan spp
    public String hitungTotalSPP(){
        //membentuk objek StringBuilder
        StringBuilder ttl=new StringBuilder();
        int total=Integer.parseInt(hitungSPPTetap())+Integer.parseInt(hitungSPPVariabel());
        ttl.append(total);
        return ttl.toString();
    }
    //method untuk mencetak data tagiab spp mahasiswa 
    public String CetakSPP(){
        //membentuk objek StringBuilder
        StringBuilder ctk= new StringBuilder();
        ctk.append("==========DATA TAGIHAN MAHASISWA===========");
        ctk.append("\nSPP Tetap\t: Rp.").append(hitungSPPTetap());
        ctk.append("\nSPP Variabel\t: RP.").append(hitungSPPVariabel());
        ctk.append("\nTotal Tagihan SPP Semester ini: Rp.").append(hitungTotalSPP());
        ctk.append("\n");
        return ctk.toString();
    }
    
  
}
