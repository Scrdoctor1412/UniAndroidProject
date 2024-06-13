package phdhtl.k63cntt1.nguyen.model;

public class Author {
    private String id;
    private String name;
    private String introducing;

    public Author() {
        this.id = "Dummyid";
        this.name = "DummyName";
        this.introducing = "DummyIntroducing";
    }

    public Author(String id, String name, String introducing) {
        this.id = id;
        this.name = name;
        this.introducing = introducing;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroducing() {
        return introducing;
    }

    public void setIntroducing(String introducing) {
        this.introducing = introducing;
    }
}
