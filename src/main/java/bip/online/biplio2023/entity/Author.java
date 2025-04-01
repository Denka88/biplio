package bip.online.biplio2023.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.List;


@Schema(description = "Сущность автора")
@Entity
@Table(name = "authors")
public class Author {

    @Schema(description = "Уникальный идентификатор автора", example = "43", accessMode = Schema.AccessMode.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Отчество автора", example = "Иванович")
    private String lastName;
    @Schema(description = "Имя автора", example = "Иван")
    private String name;
    @Schema(description = "Фамилия автора", example = "Иванов")
    private String surName;

    @JsonIgnore
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> books;

    public Author() {
    }

    public Author(Long id, String lastName, String name, String surName) {
        this.id = id;
        this.lastName = lastName;
        this.name = name;
        this.surName = surName;
    }

    public Author(Long id, String lastName, String name, String surName, List<Book> books) {
        this.id = id;
        this.lastName = lastName;
        this.name = name;
        this.surName = surName;
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
