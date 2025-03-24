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

import java.util.List;

@Route("genres")
@PageTitle("Жанры")
public class GenresView extends VerticalLayout {
    
    private final GenreService genreService;
    private final Grid<Genre> grid;
    private final Button showFormButton;
    private final FormLayout formLayout;

    public GenresView(GenreService genreService) {
        this.genreService = genreService;
        this.grid = new Grid<>(Genre.class, false);
        this.showFormButton = new Button("Показать форму", new Icon(VaadinIcon.PLUS));
        this.formLayout = new FormLayout();

        setupGrid();
        updateGrid();

        setupAddGenres();

        formLayout.setVisible(false);

        showFormButton.addClickListener(e -> {
            formLayout.setVisible(!formLayout.isVisible());
            showFormButton.setText(formLayout.isVisible() ? "Скрыть форму" : "Показать форму");
        });
        
        add(grid, formLayout, showFormButton);
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
        TextField title = new TextField("Название жанра");

        title.setWidth("100%");

        formLayout.setWidth("400px");
        formLayout.setHeight("auto");

        formLayout.add(title);

        Button saveButton = new Button("Сохранить", e -> {
            Genre genre = new Genre();
            genre.setTitle(title.getValue());
            genreService.save(genre);

            updateGrid();

            title.clear();
        });

        Button resetButton = new Button("Сбросить", e -> {
            title.clear();
        });

        grid.addCellFocusListener(e -> {
            title.setValue(e.getItem().map(Genre::getTitle).orElse("Не доступно"));
        });

        formLayout.add(saveButton, resetButton);
    }
}
