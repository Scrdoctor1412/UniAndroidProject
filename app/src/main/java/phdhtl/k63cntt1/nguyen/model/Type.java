package phdhtl.k63cntt1.nguyen.model;

public class Type {
    private String matl;
    private String tentl;
    private String noidungtl;

    public Type(String matl, String tentl, String noidungtl) {
        this.matl = matl;
        this.tentl = tentl;
        this.noidungtl = noidungtl;
    }

    public String getMatl() {
        return matl;
    }

    public void setMatl(String matl) {
        this.matl = matl;
    }

    public String getTentl() {
        return tentl;
    }

    public void setTentl(String tentl) {
        this.tentl = tentl;
    }

    public String getNoidungtl() {
        return noidungtl;
    }

    public void setNoidungtl(String noidungtl) {
        this.noidungtl = noidungtl;
    }
}
