package phdhtl.k63cntt1.nguyen.model;

public class User {
    private String id;
    private String userName;
    private String email;
    private String passWord;
    private String athGoogleId;
    private int authType;
    private int level;

    public User() {
        this.id = "dummyID";
        this.userName = "dummyName";
        this.email = "dummyEmail";
        this.passWord = "dummyPW";
        this.athGoogleId = "dummyGGID";
        this.authType = 0;
        this.level = 0;
    }

    public User(String id, String userName, String email, String passWord, String athGoogleId, int authType, int level) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.passWord = passWord;
        this.athGoogleId = athGoogleId;
        this.authType = authType;
        this.level = level;
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

    public String getAthGoogleId() {
        return athGoogleId;
    }

    public void setAthGoogleId(String athGoogleId) {
        this.athGoogleId = athGoogleId;
    }

    public int getAuthType() {
        return authType;
    }

    public void setAuthType(int authType) {
        this.authType = authType;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
