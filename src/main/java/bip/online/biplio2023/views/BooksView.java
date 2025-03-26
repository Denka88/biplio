package bip.online.biplio2023.views;

import bip.online.biplio2023.entity.Author;
import bip.online.biplio2023.entity.Book;
import bip.online.biplio2023.entity.Genre;
import bip.online.biplio2023.entity.Publisher;
import bip.online.biplio2023.service.AuthorService;
import bip.online.biplio2023.service.BookService;
import bip.online.biplio2023.service.GenreService;
import bip.online.biplio2023.service.PublisherService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
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

@Route("books")
@PageTitle("Книги")
@PermitAll
public class BooksView extends VerticalLayout {
    
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final PublisherService publisherService;
    
    
    private final Grid<Book> grid;
    private final Button showFormButton;
    private final FormLayout formLayout;
    
    public BooksView(BookService bookService, AuthorService authorService, GenreService genreService, PublisherService publisherService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
        this.publisherService = publisherService;

        this.grid = new Grid<>(Book.class, false);
        this.showFormButton = new Button("Показать форму", new Icon(VaadinIcon.ANGLE_RIGHT));
        this.formLayout = new FormLayout();
        
        setupGrid();
        updateGrid();
        
        setupAddBook();

        formLayout.setVisible(false);

        showFormButton.addClickListener(e -> {
            formLayout.setVisible(!formLayout.isVisible());
            showFormButton.setText(formLayout.isVisible() ? "Скрыть форму" : "Показать форму");
            showFormButton.setIcon(formLayout.isVisible() ? new Icon(VaadinIcon.ANGLE_DOWN) : new Icon(VaadinIcon.ANGLE_RIGHT));
        });

        add(grid, showFormButton, formLayout);
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
    
    private void setupAddBook(){
        TextField id = new TextField("ID");
        TextField year = new TextField("Год выпуска книги");

        ComboBox<Author> authorBox = new ComboBox<>("Автор");
        authorBox.setAllowCustomValue(false);
        authorBox.setItems(authorService.findAllAuthors());
        authorBox.setItemLabelGenerator(Author::toString);
        
        ComboBox<Genre> genreBox = new ComboBox<>("Жанр");
        genreBox.setAllowCustomValue(false);
        genreBox.setItems(genreService.findAllGenres());
        genreBox.setItemLabelGenerator(Genre::getTitle);
        
        ComboBox<Publisher> publisherBox = new ComboBox<>("Издатель");
        publisherBox.setAllowCustomValue(false);
        publisherBox.setItems(publisherService.findAllPublishers());
        publisherBox.setItemLabelGenerator(Publisher::getTitle);
        
        id.setReadOnly(true);
        id.setVisible(false);
        
        id.setWidth("100%");
        year.setWidth("100%");
        authorBox.setWidth("100%");
        genreBox.setWidth("100%");
        publisherBox.setWidth("100%");
        
        formLayout.setWidth("400px");
        formLayout.setHeight("auto");
        
        Button saveButton = new Button("Сохранить", e -> {
           
           if(!id.isEmpty()){
               Book updateBook = bookService.findById(Long.valueOf(id.getValue())).orElse(null);
               updateBook.setYear(year.getValue());
               updateBook.setAuthor(authorBox.getValue());
               updateBook.setGenre(genreBox.getValue());
               updateBook.setPublisher(publisherBox.getValue());
               bookService.update(updateBook);
           }
           else {
               Book book = new Book();
               book.setYear(year.getValue());
               book.setAuthor(authorBox.getValue());
               book.setGenre(genreBox.getValue());
               book.setPublisher(publisherBox.getValue());
               bookService.save(book);
           }
           
           updateGrid();
           
           id.clear();
           year.clear();
           authorBox.clear();
           genreBox.clear();
           publisherBox.clear();
        });
        
        Button resetButton = new Button("Сбросить", e -> {
            id.clear();
            year.clear();
            authorBox.clear();
            genreBox.clear();
            publisherBox.clear();
        });
        
        grid.addCellFocusListener(e -> {
            id.setValue(String.valueOf(e.getItem().map(Book::getId).orElse(null)));
            year.setValue(e.getItem().map(Book::getYear).orElse("Не доступно"));
            authorBox.setValue(e.getItem().map(Book::getAuthor).orElse(null));
            genreBox.setValue(e.getItem().map(Book::getGenre).orElse(null));
            publisherBox.setValue(e.getItem().map(Book::getPublisher).orElse(null));
        });
        
        formLayout.add(
                id,
                year,
                authorBox,
                genreBox,
                publisherBox,
                
                saveButton,
                resetButton
        );
    }
    
}
