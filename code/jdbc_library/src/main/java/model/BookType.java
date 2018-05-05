package model;

public enum BookType {

    COMICS("comics"), FANTASY("fantasy"), DEFAULT("default");

    private String type;

    BookType(String type) {
        this.type = type;
    }


    public static BookType parseType(String type) {
        if (type.equals(COMICS.type)) {
            return COMICS;
        } else if (type.equals(FANTASY.type)) {
            return FANTASY;
        } else {
            return DEFAULT;
        }
    }

    @Override
    public String toString() {
        return type;
    }
}
