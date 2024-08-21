package mvc.entity;

import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bookdetails")
public class BookDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "price")
    private int price;

    @Column(name = "numberOfPage")
    private int numberOfPage;

    @Column(name = "publishDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishDate;


    @OneToOne(mappedBy = "bookDetails")
    private BookEntity book;

    public BookDetailsEntity() {

    }

    public int getId() {
        return id;
    }

    public BookDetailsEntity setId(int id) {
        this.id = id;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public BookDetailsEntity setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public BookDetailsEntity setPrice(int price) {
        this.price = price;
        return this;
    }

    public int getNumberOfPage() {
        return numberOfPage;
    }

    public BookDetailsEntity setNumberOfPage(int numberOfPage) {
        this.numberOfPage = numberOfPage;
        return this;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public BookDetailsEntity setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
        return this;
    }

    public BookEntity getBook() {
        return book;
    }

    public BookDetailsEntity setBook(BookEntity book) {
        this.book = book;
        return this;
    }
}
