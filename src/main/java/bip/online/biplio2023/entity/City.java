package bip.online.biplio2023.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.List;

@Schema(description = "Сущность города")
@Entity
@Table(name = "cities")
public class City {

    @Schema(description = "Уникальный идентификатор города", example = "53453", accessMode = Schema.AccessMode.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Название города")
    private String title;

    @JsonIgnore
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<Publisher> publisher;

    public City() {

    }

    public City(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public City(Long id, String title, List<Publisher> publisher) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
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

    public List<Publisher> getPublisher() {
        return publisher;
    }

    public void setPublisher(List<Publisher> publisher) {
        this.publisher = publisher;
    }
}
