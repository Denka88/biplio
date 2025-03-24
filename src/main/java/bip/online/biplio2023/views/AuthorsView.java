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
        this.showFormButton = new Button("Показать форму", new Icon(VaadinIcon.ANGLE_RIGHT));
        this.grid = new Grid<>(Author.class, false);
        
        setupGrid();
        updateGrid();

        setupAddAuthor();
        
        formLayout.setVisible(false);

        showFormButton.addClickListener(e -> {
            formLayout.setVisible(!formLayout.isVisible());
            showFormButton.setText(formLayout.isVisible() ? "Скрыть форму" : "Показать форму");
            showFormButton.setIcon(formLayout.isVisible() ? new Icon(VaadinIcon.ANGLE_DOWN) : new Icon(VaadinIcon.ANGLE_RIGHT));
        });

        add(grid, showFormButton, formLayout);

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
        TextField id = new TextField("ID");
        TextField name = new TextField("Имя");
        TextField lastName = new TextField("Отчество");
        TextField surname = new TextField("Фамилия");
        
        id.setReadOnly(true);
        
        id.setWidth("100%");
        name.setWidth("100%");
        lastName.setWidth("100%");
        surname.setWidth("100%");
        
        formLayout.setWidth("400px");
        formLayout.setHeight("auto");

        Button saveButton = new Button("Сохранить", e -> {            
            if(!id.isEmpty()){
                Author updateAuthor = authorService.findById(Long.valueOf(id.getValue())).orElse(null);
                updateAuthor.setName(name.getValue());
                updateAuthor.setLastName(lastName.getValue());
                updateAuthor.setSurName(surname.getValue());
                
                authorService.update(updateAuthor);
            }
            else {
                Author author = new Author();
                author.setName(name.getValue());
                author.setLastName(lastName.getValue());
                author.setSurName(surname.getValue());
                
                authorService.save(author);
            }
            

            updateGrid();
            
            id.clear();
            name.clear();
            lastName.clear();
            surname.clear();
        });
        
        Button resetButton = new Button("Сбросить", e -> {
            id.clear();
            name.clear();
            lastName.clear();
            surname.clear();
        });

        grid.addCellFocusListener(e -> {
            id.setValue(String.valueOf(e.getItem().map(Author::getId).orElse(null)));
            name.setValue(e.getItem().map(Author::getName).orElse("Не доступно"));
            lastName.setValue(e.getItem().map(Author::getLastName).orElse("Не доступно"));
            surname.setValue(e.getItem().map(Author::getSurName).orElse("Не доступно"));
            }
        );

        formLayout.add(
                id,
                name,
                lastName,
                surname,
                
                saveButton,
                resetButton
        );
    }
}
