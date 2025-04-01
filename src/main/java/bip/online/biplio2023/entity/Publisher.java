package bip.online.biplio2023.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.List;
import java.util.Locale;

@Schema(description = "Сущность издателя")
@Entity
@Table(name = "publishers")
public class Publisher {

    @Schema(description = "Уникальный идентификатор издателя", example = "63", accessMode = Schema.AccessMode.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Schema(description = "Навзание издателя")
    private String title;

    @Schema(description = "Город издателя")
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @JsonIgnore
    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
    private List<Book> books;

    public Publisher() {
    }

    public Publisher(Long id, String title, City city) {
        this.id = id;
        this.title = title;
        this.city = city;
    }

    public Publisher(Long id, String title, City city, List<Book> books) {
        this.id = id;
        this.title = title;
        this.city = city;
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
