package phdhtl.k63cntt1.nguyen.model;

public class Story {
    private String matruyen;
    private String tentruyen;
    private String anhbia;
    private String noidung;
    private String theloai;
    private String nxb;
    private String tacgia;
    private int luotxem;
    private int luotlike;


    public Story(String matruyen, String tentruyen, String noidung, String anhbia, String theloai, String nxb, String tacgia, int luotxem, int luotlike) {
        this.matruyen = matruyen;
        this.tentruyen = tentruyen;
        this.anhbia = anhbia;
        this.noidung = noidung;
        this.theloai = theloai;
        this.luotxem = luotxem;
        this.luotlike = luotlike;
        this.nxb = nxb;
        this.tacgia = tacgia;
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

    public String getNxb() {
        return nxb;
    }

    public void setNxb(String nxb) {
        this.nxb = nxb;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }
}
