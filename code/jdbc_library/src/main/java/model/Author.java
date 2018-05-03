package model;

public class Author extends BaseModel {

    public static final String AUTHOR_TABLE = ".AUTHORS";
    public static final String AUTHOR_ID_COLUMN = "AUTHOR_ID";
    public static final String FIRST_NAME_COLUMN = "FIRST_NAME";

    private String firstName;

    public Author(String id, String firstName) {
        super(id);
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }
}
