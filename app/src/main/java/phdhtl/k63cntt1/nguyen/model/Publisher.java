package phdhtl.k63cntt1.nguyen.model;

public class Publisher {
    private String manxb;
    private String tennxb;
    private String gioithieu;
    private String imgdaidien;

    public Publisher(String manxb, String tennxb, String gioithieu, String imgdaidien) {
        this.manxb = manxb;
        this.tennxb = tennxb;
        this.gioithieu = gioithieu;
        this.imgdaidien = imgdaidien;
    }

    public String getManxb() {
        return manxb;
    }

    public void setManxb(String manxb) {
        this.manxb = manxb;
    }

    public String getTennxb() {
        return tennxb;
    }

    public void setTennxb(String tennxb) {
        this.tennxb = tennxb;
    }

    public String getGioithieu() {
        return gioithieu;
    }

    public void setGioithieu(String gioithieu) {
        this.gioithieu = gioithieu;
    }

    public String getImgdaidien() {
        return imgdaidien;
    }

    public void setImgdaidien(String imgdaidien) {
        this.imgdaidien = imgdaidien;
    }
}
