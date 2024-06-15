package phdhtl.k63cntt1.nguyen.model;

public class User {
    private String id;
    private String userName;
    private String email;
    private String passWord;
    private int level;
    private String imgdaidien;

    public User() {
        this.id = "dummyID";
        this.userName = "dummyName";
        this.email = "dummyEmail";
        this.passWord = "dummyPW";
        this.level = 0;
        this.imgdaidien = "";
    }

    public User(String id, String userName, String email, String passWord,  String imgdaidien, int level) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.passWord = passWord;
        this.level = level;
        this.imgdaidien = imgdaidien;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getImgdaidien() {
        return imgdaidien;
    }

    public void setImgdaidien(String imgdaidien) {
        this.imgdaidien = imgdaidien;
    }
}
