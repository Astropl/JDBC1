package model;

public class Book extends BaseModel {

    public static final String BOOK_TABLE = ".BOOKS";
    public static final String BOOK_ID_COLUMN = "BOOK_ID";
    public static final String AUTHOR_ID_COLUMN = "AUTHOR_ID";
    public static final String BOOK_NAME_COLUMN = "BOOK_NAME";
    public static final String BOOK_TYPE_COLUMN = "BOOK_TYPE";

    private String name;
    private String authorId;
    private BookType bookType;

    public Book(String id, String authorId, String name, BookType bookType) {
        super(id);
        this.name = name;
        this.authorId = authorId;
        this.bookType = bookType;
    }

    public String getName() {
        return name;
    }

    public String getAuthorId() {
        return authorId;
    }

    public BookType getBookType() {
        return bookType;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", authorId='" + authorId + '\'' +
                ", bookType=" + bookType +
                ", id='" + id + '\'' +
                '}';
    }
}
