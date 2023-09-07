package kata.Development.books.entity;

public class DevelopmentBook {
    private Integer id;

    private String title;

    private String author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public DevelopmentBook (Integer id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }
}
