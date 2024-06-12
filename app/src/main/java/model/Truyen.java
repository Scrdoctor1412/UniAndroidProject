package model;

public class Truyen {
    private String matruyen;
    private String tentruyen;
    private int anhbia;
    private String noidung;
    private String tacgia;
    private String nxb;
    private String theloai;
    private int sochuong;

    private int luotxem;
    private int luotlike;

    public Truyen(String matruyen, String tentruyen, int anhbia, String noidung, String tacgia, String nxb, String theloai, int sochuong, int luotlike,int luotxem) {
        this.matruyen = matruyen;
        this.tentruyen = tentruyen;
        this.anhbia = anhbia;
        this.noidung = noidung;
        this.tacgia = tacgia;
        this.nxb = nxb;
        this.theloai = theloai;
        this.sochuong = sochuong;
        this.luotlike = luotlike;
        this.luotxem = luotxem;
    }

    public int getLuotxem() {
        return luotxem;
    }

    public void setLuotxem(int luotxem) {
        this.luotxem = luotxem;
    }

    public int getLuotlike() {
        return luotlike;
    }

    public void setLuotlike(int luotlike) {
        this.luotlike = luotlike;
    }

    public String getMatruyen() {
        return matruyen;
    }

    public void setMatruyen(String matruyen) {
        this.matruyen = matruyen;
    }

    public String getTentruyen() {
        return tentruyen;
    }

    public void setTentruyen(String tentruyen) {
        this.tentruyen = tentruyen;
    }

    public int getAnhbia() {
        return anhbia;
    }

    public void setAnhbia(int anhbia) {
        this.anhbia = anhbia;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public String getNxb() {
        return nxb;
    }

    public void setNxb(String nxb) {
        this.nxb = nxb;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }

    public int getSochuong() {
        return sochuong;
    }

    public void setSochuong(int sochuong) {
        this.sochuong = sochuong;
    }
}
