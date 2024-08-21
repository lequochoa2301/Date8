package mvc.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "book")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "Please enter book name.")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Please enter book author.")
    @Column(name = "author")
    private String author;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private CategoryEntity category;

    @OneToOne(cascade = {CascadeType.ALL})
    @PrimaryKeyJoinColumn
    private BookDetailsEntity bookDetails;

    public BookEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotEmpty(message = "Please enter book name.") String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "Please enter book name.") String name) {
        this.name = name;
    }

    public @NotEmpty(message = "Please enter book author.") String getAuthor() {
        return author;
    }

    public void setAuthor(@NotEmpty(message = "Please enter book author.") String author) {
        this.author = author;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public BookDetailsEntity getBookDetails() {
        return bookDetails;
    }

    public void setBookDetails(BookDetailsEntity bookDetails) {
        this.bookDetails = bookDetails;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", category name=" + category.getName() +
                ", ISBN=" + bookDetails.getIsbn() +
                ", Total page=" + bookDetails.getNumberOfPage() +
                ", Price=" + bookDetails.getPrice() +
                ", Publish date=" + bookDetails.getPublishDate() +
                '}';
    }
}