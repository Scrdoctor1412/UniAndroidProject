package phdhtl.k63cntt1.nguyen.model;

public class ChapterImage {
    private String mahinhanhchuong;
    private String hinhanh;
    private String machuong;

    public ChapterImage(String mahinhanhchuong, String hinhanh, String machuong) {
        this.mahinhanhchuong = mahinhanhchuong;
        this.hinhanh = hinhanh;
        this.machuong = machuong;
    }

    public String getMahinhanhchuong() {
        return mahinhanhchuong;
    }

    public void setMahinhanhchuong(String mahinhanhchuong) {
        this.mahinhanhchuong = mahinhanhchuong;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getMachuong() {
        return machuong;
    }

    public void setMachuong(String machuong) {
        this.machuong = machuong;
    }
}
