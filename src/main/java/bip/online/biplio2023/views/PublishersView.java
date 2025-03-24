package bip.online.biplio2023.views;

import bip.online.biplio2023.entity.City;
import bip.online.biplio2023.entity.Publisher;
import bip.online.biplio2023.service.CityService;
import bip.online.biplio2023.service.PublisherService;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.button.Button;
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

import java.util.List;

@Route("publishers")
@PageTitle("Издатели")
public class PublishersView extends VerticalLayout {
    
    private final PublisherService publisherService;
    private final CityService cityService;
    
    private final Grid<Publisher> grid;
    private final Button showFormButton;
    private final FormLayout formLayout;


    public PublishersView(PublisherService publisherService, CityService cityService) {
        this.publisherService = publisherService;
        this.cityService = cityService;
        
        this.grid = new Grid<>(Publisher.class, false);;
        this.showFormButton = new Button("Показать форму", new Icon(VaadinIcon.ANGLE_RIGHT));
        this.formLayout = new FormLayout();
        
        setupGrid();
        updateGrid();
        
        setupAddPublisher();
        
        formLayout.setVisible(false);

        showFormButton.addClickListener(e -> {
            formLayout.setVisible(!formLayout.isVisible());
            showFormButton.setText(formLayout.isVisible() ? "Скрыть форму" : "Показать форму");
            showFormButton.setIcon(formLayout.isVisible() ? new Icon(VaadinIcon.ANGLE_DOWN) : new Icon(VaadinIcon.ANGLE_RIGHT));
        });
        
        add(grid, showFormButton, formLayout);
    }

    private void setupGrid(){
        grid.addColumn(Publisher::getId).setHeader("ID").setSortable(true);
        grid.addColumn(Publisher::getTitle).setHeader("Название").setSortable(true);
        grid.addColumn(Publisher::getCity).setHeader("Город").setSortable(true);
        grid.addColumn(
                new ComponentRenderer<>(Button::new, (button, publisher) -> {
                    button.addThemeVariants(ButtonVariant.LUMO_ICON,
                            ButtonVariant.LUMO_ERROR,
                            ButtonVariant.LUMO_TERTIARY);
                    button.addClickListener(e -> {
                        publisherService.deleteById(publisher.getId());
                        updateGrid();
                    });
                    button.setIcon(new Icon(VaadinIcon.TRASH));
                })).setHeader("Действие");
    }

    private void updateGrid() {
        List<Publisher> publishers = publisherService.findAllPublishers();
        grid.setItems(publishers);
    }
    
    private void setupAddPublisher(){
        TextField id = new TextField("ID");
        TextField title = new TextField("Название издателя");
        
        ComboBox<City> citiesBox = new ComboBox<>("Город");
        citiesBox.setAllowCustomValue(false);
        citiesBox.setItems(cityService.findAllCities());
        citiesBox.setItemLabelGenerator(City::getTitle);

        id.setReadOnly(true);
        
        id.setWidth("100%");
        title.setWidth("100%");
        citiesBox.setWidth("100%");

        formLayout.setWidth("400px");
        formLayout.setHeight("auto");

        formLayout.add();
        
        Button saveButton = new Button("Сохранить", e -> {
            
            if(!id.isEmpty()){
                Publisher updatePublisher = publisherService.findById(Long.valueOf(id.getValue())).orElse(null);
                updatePublisher.setTitle(title.getValue());
                updatePublisher.setCity(citiesBox.getValue());
                publisherService.update(updatePublisher);
            }
            else {
                Publisher publisher = new Publisher();
                publisher.setTitle(title.getValue());
                publisher.setCity(citiesBox.getValue());
                publisherService.save(publisher);
            }
            
            updateGrid();
            
            id.clear();
            title.clear();
            citiesBox.clear();
        });
        
        Button resetButton = new Button("Сбросить", e -> {
            id.clear();
            title.clear();
            citiesBox.clear();
        });
        
        grid.addCellFocusListener(e -> {
            id.setValue(String.valueOf(e.getItem().map(Publisher::getId).orElse(null)));
            title.setValue(e.getItem().map(Publisher::getTitle).orElse("Не доступно"));
            citiesBox.setValue(e.getItem().map(Publisher::getCity).orElse(null));
        });
        
        formLayout.add(
                id,
                title,
                citiesBox,
        
                saveButton,
                resetButton
        );
    }
}
