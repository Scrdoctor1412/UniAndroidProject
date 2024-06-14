package phdhtl.k63cntt1.nguyen.model;

public class Chapter {
    private String machuong;
    private int chuongso;
    private String chuongten;
    private String matruyen;

    public Chapter(String machuong, int chuongso, String chuongten, String matruyen) {
        this.machuong = machuong;
        this.chuongso = chuongso;
        this.chuongten = chuongten;
        this.matruyen = matruyen;
    }

    public String getMachuong() {
        return machuong;
    }

    public void setMachuong(String machuong) {
        this.machuong = machuong;
    }

    public int getChuongso() {
        return chuongso;
    }

    public void setChuongso(int chuongso) {
        this.chuongso = chuongso;
    }

    public String getChuongten() {
        return chuongten;
    }

    public void setChuongten(String chuongten) {
        this.chuongten = chuongten;
    }

    public String getMatruyen() {
        return matruyen;
    }

    public void setMatruyen(String matruyen) {
        this.matruyen = matruyen;
    }
}
