package model;

public class Book extends BaseModel {

    public static final String BOOK_TABLE = ".BOOKS";
    public static final String BOOK_ID_COLUMN = "BOOK_ID";
    public static final String BOOK_NAME_COLUMN = "BOOK_NAME";

    private String name;

    public Book(String id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
