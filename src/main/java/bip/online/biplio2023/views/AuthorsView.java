package bip.online.biplio2023.views;


import bip.online.biplio2023.entity.Author;
import bip.online.biplio2023.service.AuthorService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route("authors")
public class AuthorsView extends VerticalLayout {

    AuthorService authorService;

    public AuthorsView() {

        Grid<Author> grid = new Grid<>();
        grid.addColumn(Author::getName).setHeader("Имя");
        grid.addColumn(Author::getSurName).setHeader("Фамилия");
        grid.addColumn(Author::getLastName).setHeader("Отчество");
        List<Author> authors = authorService.findAllAuthors() ;


        grid.setItems(authors);
    }

}
