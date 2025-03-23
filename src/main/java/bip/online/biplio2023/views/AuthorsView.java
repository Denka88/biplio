package bip.online.biplio2023.views;


import bip.online.biplio2023.entity.Author;
import bip.online.biplio2023.service.AuthorService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.CellFocusEvent;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route("authors")
@PageTitle("Авторы")
public class AuthorsView extends VerticalLayout {

    private final AuthorService authorService;
    private final Grid<Author> grid;
    private final Button showFormButton;
    private final FormLayout formLayout;

    public AuthorsView(AuthorService authorService) {
        this.authorService = authorService;
        this.formLayout = new FormLayout();
        this.showFormButton = new Button("Показать форму", new Icon(VaadinIcon.PLUS));
        this.grid = new Grid<>(Author.class, false);
        
        setupGrid();
        updateGrid();

        setupAddAuthor();
        
        formLayout.setVisible(false);

        showFormButton.addClickListener(e -> {
            formLayout.setVisible(!formLayout.isVisible());
            showFormButton.setText(formLayout.isVisible() ? "Скрыть форму" : "Показать форму");
        });

        add(grid, formLayout, showFormButton);
    }

    private void setupGrid() {
        grid.setClassName("force-focus-outline");
        
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

    private List<Author> updateGrid() {
        List<Author> authors = authorService.findAllAuthors();
        grid.setItems(authors);
        
        return authors;
    }

    private void setupAddAuthor(){
        TextField nameField = new TextField("Имя");
        TextField lastNameField = new TextField("Отчество");
        TextField surNameField = new TextField("Фамилия");
        
        nameField.setWidth("100%");
        lastNameField.setWidth("100%");
        surNameField.setWidth("100%");
        
        formLayout.setWidth("400px");
        formLayout.setHeight("auto");
        
        formLayout.add(nameField, lastNameField, surNameField);

        Button saveButton = new Button("Сохранить", e -> {
            Author author = new Author();
            author.setName(nameField.getValue());
            author.setLastName(lastNameField.getValue());
            author.setSurName(surNameField.getValue());
            authorService.save(author);

            updateGrid();
            
            nameField.clear();
            lastNameField.clear();
            surNameField.clear();
        });
        
        Button resetButton = new Button("Сбросить", e -> {
            nameField.clear();
            lastNameField.clear();
            surNameField.clear();
        });

        grid.addCellFocusListener(e -> {
            nameField.setValue(e.getItem().map(Author::getName).orElse("Не доступно"));
            lastNameField.setValue(e.getItem().map(Author::getLastName).orElse("Не доступно"));
            surNameField.setValue(e.getItem().map(Author::getSurName).orElse("Не доступно"));
            }
        );

        formLayout.add(saveButton, resetButton);
    }
}
