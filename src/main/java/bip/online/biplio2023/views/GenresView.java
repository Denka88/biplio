package bip.online.biplio2023.views;

import bip.online.biplio2023.entity.Genre;
import bip.online.biplio2023.service.GenreService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

import java.util.List;

@Route("genres")
@PageTitle("Жанры")
@PermitAll
public class GenresView extends VerticalLayout {
    
    private final GenreService genreService;
    private final Grid<Genre> grid;
    private final Button showFormButton;
    private final FormLayout formLayout;

    public GenresView(GenreService genreService) {
        this.genreService = genreService;
        this.grid = new Grid<>(Genre.class, false);
        this.showFormButton = new Button("Показать форму", new Icon(VaadinIcon.ANGLE_RIGHT));
        this.formLayout = new FormLayout();

        setupGrid();
        updateGrid();

        setupAddGenres();

        formLayout.setVisible(false);

        showFormButton.addClickListener(e -> {
            formLayout.setVisible(!formLayout.isVisible());
            showFormButton.setText(formLayout.isVisible() ? "Скрыть форму" : "Показать форму");
            showFormButton.setIcon(formLayout.isVisible() ? new Icon(VaadinIcon.ANGLE_DOWN) : new Icon(VaadinIcon.ANGLE_RIGHT));
        });

        add(grid, showFormButton, formLayout);

    }

    private void setupGrid(){
        grid.addColumn(Genre::getId).setHeader("ID").setSortable(true);
        grid.addColumn(Genre::getTitle).setHeader("Название").setSortable(true);
        grid.addColumn(
                new ComponentRenderer<>(Button::new, (button, genre) -> {
                    button.addThemeVariants(ButtonVariant.LUMO_ICON,
                            ButtonVariant.LUMO_ERROR,
                            ButtonVariant.LUMO_TERTIARY);
                    button.addClickListener(e -> {
                        genreService.deleteById(genre.getId());
                        updateGrid();
                    });
                    button.setIcon(new Icon(VaadinIcon.TRASH));
                })).setHeader("Действие");
    }

    private void updateGrid(){
        List<Genre> genres = genreService.findAllGenres();
        grid.setItems(genres);
    }

    private void setupAddGenres(){
        TextField id = new TextField("ID");
        TextField title = new TextField("Название жанра");

        id.setReadOnly(true);
        id.setVisible(false);
        
        id.setWidth("100%");
        title.setWidth("100%");

        formLayout.setWidth("400px");
        formLayout.setHeight("auto");

        Button saveButton = new Button("Сохранить", e -> {
            
            if (!id.isEmpty()) {
                Genre updateGenre = genreService.findById(Long.valueOf(id.getValue())).orElse(null);
                updateGenre.setTitle(title.getValue());
                genreService.update(updateGenre);
            }
            else {
                Genre genre = new Genre();
                genre.setTitle(title.getValue());
                genreService.save(genre);
            }

            updateGrid();

            id.clear();
            title.clear();
        });

        Button resetButton = new Button("Сбросить", e -> {
            id.clear();
            title.clear();
        });

        grid.addCellFocusListener(e -> {
            id.setValue(String.valueOf(e.getItem().map(Genre::getId).orElse(null)));
            title.setValue(e.getItem().map(Genre::getTitle).orElse("Не доступно"));
        });

        formLayout.add(
                id,
                title,
        
                saveButton,
                resetButton
        );
    }
}
