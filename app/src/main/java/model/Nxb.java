package model;

public class Nxb {
    private String manxb;
    private String tennxb;
    private String gioithieu;
    private int imgdaidien;

    public Nxb(String manxb, String tennxb, String gioithieu, int imgdaidien) {
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

    public int getImgdaidien() {
        return imgdaidien;
    }

    public void setImgdaidien(int imgdaidien) {
        this.imgdaidien = imgdaidien;
    }
}
