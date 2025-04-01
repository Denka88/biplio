package bip.online.biplio2023.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Schema(description = "Сущность книги")
@Entity
@Table(name = "books")
public class Book {

    @Schema(description = "Уникальный идентификатор книги", example = "123", accessMode = Schema.AccessMode.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Автор книги")
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Schema(description = "Издатель книги")
    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @Schema(description = "Жанр книги")
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @Schema(description = "Год издания книги")
    private String year;

    public Book() {
    }

    public Book(Long id, Author author, Publisher publisher, Genre genre, String year) {
        this.id = id;
        this.author = author;
        this.publisher = publisher;
        this.genre = genre;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
