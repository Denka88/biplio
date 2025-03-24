package bip.online.biplio2023.views;

import bip.online.biplio2023.entity.City;
import bip.online.biplio2023.service.CityService;
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

@Route("cities")
@PageTitle("Города")
public class CitiesView extends VerticalLayout {
    
    private final CityService cityService;
    private final Grid<City> grid;
    private final Button showFormButton;
    private final FormLayout formLayout;

    public CitiesView(CityService cityService) {
        this.cityService = cityService;
        this.grid = new Grid<>(City.class, false);
        this.showFormButton = new Button("Показать форму", new Icon(VaadinIcon.PLUS));
        this.formLayout = new FormLayout();

        setupGrid();
        updateGrid();

        setupAddCities();

        formLayout.setVisible(false);

        showFormButton.addClickListener(e -> {
            formLayout.setVisible(!formLayout.isVisible());
            showFormButton.setText(formLayout.isVisible() ? "Скрыть форму" : "Показать форму");
        });

        add(grid, formLayout, showFormButton);
    }

    private void setupGrid(){
        grid.addColumn(City::getId).setHeader("ID").setSortable(true);
        grid.addColumn(City::getTitle).setHeader("Название").setSortable(true);
        grid.addColumn(new ComponentRenderer<>(Button::new, (button,city) -> {
            button.addThemeVariants(ButtonVariant.LUMO_ICON,
                    ButtonVariant.LUMO_ERROR,
                    ButtonVariant.LUMO_TERTIARY);
            button.addClickListener(e -> {
                cityService.deleteById(city.getId());
                updateGrid();
            });
            button.setIcon(new Icon(VaadinIcon.TRASH));
        })).setHeader("Действие");
    }

    private void updateGrid(){
        List<City> cities = cityService.findAllCities();
        grid.setItems(cities);
    }

    private void setupAddCities(){
        TextField title = new TextField("Название города");

        title.setWidth("100%");

        formLayout.setWidth("400px");
        formLayout.setHeight("auto");

        formLayout.add(title);

        Button saveButton = new Button("Сохранить", e -> {
            City city = new City();
            city.setTitle(title.getValue());
            cityService.save(city);

            updateGrid();

            title.clear();
        });

        Button resetButton = new Button("Сбросить", e -> {
            title.clear();
        });

        grid.addCellFocusListener(e -> {
            title.setValue(e.getItem().map(City::getTitle).orElse("Не доступно"));
        });

        formLayout.add(saveButton, resetButton);
    }
}
