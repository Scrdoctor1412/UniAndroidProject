package phdhtl.k63cntt1.nguyen.model;

public class Type_Story {
    private String matl;
    private String matruyen;

    public Type_Story(String matl, String matruyen) {
        this.matl = matl;
        this.matruyen = matruyen;
    }

    public String getMatl() {
        return matl;
    }

    public void setMatl(String matl) {
        this.matl = matl;
    }

    public String getMatruyen() {
        return matruyen;
    }

    public void setMatruyen(String matruyen) {
        this.matruyen = matruyen;
    }
}
