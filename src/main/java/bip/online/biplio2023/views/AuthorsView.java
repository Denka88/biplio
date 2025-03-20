package bip.online.biplio2023.views;


import bip.online.biplio2023.entity.Author;
import bip.online.biplio2023.service.AuthorService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route("authors")
@PageTitle("Авторы")
public class AuthorsView extends VerticalLayout {

    private final AuthorService authorService;
    private final Grid<Author> grid;

    public AuthorsView(AuthorService authorService) {
        this.authorService = authorService;
        this.grid = new Grid<>(Author.class, false);
        
        setupGrid();
        updateGrid();
        
        add(grid);
    }

    private void setupGrid() {
        grid.addColumn(Author::getId).setHeader("ID").setSortable(true);
        grid.addColumn(Author::getName).setHeader("Имя").setSortable(true);
        grid.addColumn(Author::getSurName).setHeader("Фамилия").setSortable(true);
        grid.addColumn(Author::getLastName).setHeader("Отчество").setSortable(true);
        grid.addColumn(
                new ComponentRenderer<>(Button::new, (button, author) -> {
                    button.addThemeVariants(ButtonVariant.LUMO_ICON,
                            ButtonVariant.LUMO_ERROR,
                            ButtonVariant.LUMO_TERTIARY);
                    button.addClickListener(e -> {
                        authorService.deleteById(author.getId());
                        updateGrid();
                    });
                    button.setIcon(new Icon(VaadinIcon.TRASH));
                })).setHeader("Действие");
    }

    private void updateGrid() {
        List<Author> authors = authorService.findAllAuthors();
        grid.setItems(authors);
    }

}
