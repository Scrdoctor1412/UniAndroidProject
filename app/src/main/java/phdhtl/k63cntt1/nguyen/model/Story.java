package phdhtl.k63cntt1.nguyen.model;

public class Story {
    private String matruyen;
    private String tentruyen;
    private String anhbia;
    private String noidung;
    private String theloai;
    private int sochuong;
    private int luotxem;
    private int luotlike;
    private String manxb;
    private String matg;


    public Story(String matruyen, String tentruyen, String anhbia, String noidung, String theloai, int sochuong, int luotxem, int luotlike, String manxb, String matg) {
        this.matruyen = matruyen;
        this.tentruyen = tentruyen;
        this.anhbia = anhbia;
        this.noidung = noidung;
        this.theloai = theloai;
        this.sochuong = sochuong;
        this.luotxem = luotxem;
        this.luotlike = luotlike;
        this.manxb = manxb;
        this.matg = matg;
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

    public String getAnhbia() {
        return anhbia;
    }

    public void setAnhbia(String anhbia) {
        this.anhbia = anhbia;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
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
    public String getManxb() {
        return manxb;
    }

    public void setManxb(String manxb) {
        this.manxb = manxb;
    }

    public String getMatg() {
        return matg;
    }

    public void setMatg(String matg) {
        this.matg = matg;
    }
}
