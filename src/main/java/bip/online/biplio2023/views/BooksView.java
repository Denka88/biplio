package bip.online.biplio2023.views;

import bip.online.biplio2023.entity.Book;
import bip.online.biplio2023.service.BookService;
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

@Route("books")
@PageTitle("Книги")
public class BooksView extends VerticalLayout {
    
    private final BookService bookService;
    private final Grid<Book> grid;
    
    public BooksView(BookService bookService) {
        this.bookService = bookService;
        this.grid = new Grid<>(Book.class, false);
        
        setupGrid();
        updateGrid();
        
        add(grid);
    }
    
    private void setupGrid(){
        grid.addColumn(Book::getId).setHeader("ID").setSortable(true);
        grid.addColumn(Book::getYear).setHeader("Год").setSortable(true);
        grid.addColumn(Book::getAuthor).setHeader("Автор").setSortable(true);
        grid.addColumn(Book::getGenre).setHeader("Жанр").setSortable(true);
        grid.addColumn(Book::getPublisher).setHeader("Издатель").setSortable(true);
        grid.addColumn(
                new ComponentRenderer<>(Button::new, (button, book) -> {
                    button.addThemeVariants(ButtonVariant.LUMO_ICON,
                            ButtonVariant.LUMO_ERROR,
                            ButtonVariant.LUMO_TERTIARY);
                    button.addClickListener(e -> {
                        bookService.deleteById(book.getId());
                        updateGrid();
                    });
                    button.setIcon(new Icon(VaadinIcon.TRASH));
                })).setHeader("Действие");
    }
    
    private void updateGrid(){
        List<Book> books = bookService.findAllBooks();
        grid.setItems(books);
    }
    
}
