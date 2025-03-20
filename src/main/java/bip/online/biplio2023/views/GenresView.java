package bip.online.biplio2023.views;

import bip.online.biplio2023.entity.Genre;
import bip.online.biplio2023.service.GenreService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route("genres")
@PageTitle("Жанры")
public class GenresView extends VerticalLayout {
    
    private final GenreService genreService;

    public GenresView(GenreService genreService) {
        this.genreService = genreService;
        
        Grid<Genre> grid = new Grid<>(Genre.class, false);
        grid.addColumn(Genre::getId).setHeader("ID").setSortable(true);
        grid.addColumn(Genre::getTitle).setHeader("Название").setSortable(true);

        List<Genre> genres = genreService.findAllGenres();
        grid.setItems(genres);
        
        add(grid);
    }
}
