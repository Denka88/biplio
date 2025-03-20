package bip.online.biplio2023.views;

import bip.online.biplio2023.entity.City;
import bip.online.biplio2023.service.CityService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route("cities")
@PageTitle("Города")
public class CitiesView extends VerticalLayout {
    
    private final CityService cityService;

    public CitiesView(CityService cityService) {
        this.cityService = cityService;
        
        Grid<City> grid = new Grid<>(City.class, false);
        grid.addColumn(City::getId).setHeader("ID").setSortable(true);
        grid.addColumn(City::getTitle).setHeader("Название").setSortable(true);
        
        List<City> cities = cityService.findAllCities();
        grid.setItems(cities);
        
        add(grid);
    }
}
