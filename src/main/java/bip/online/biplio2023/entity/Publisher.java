package bip.online.biplio2023.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "publishers")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @JsonIgnore
    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
    private List<Book> books;

    public Publisher() {
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
