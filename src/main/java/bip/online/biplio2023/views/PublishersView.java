package bip.online.biplio2023.views;

import bip.online.biplio2023.entity.Publisher;
import bip.online.biplio2023.service.PublisherService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route("publishers")
@PageTitle("Издатели")
public class PublishersView extends VerticalLayout {
    
    private final PublisherService publisherService;


    public PublishersView(PublisherService publisherService) {
        this.publisherService = publisherService;
        
        Grid<Publisher> grid = new Grid<>(Publisher.class, false);
        grid.addColumn(Publisher::getId).setHeader("ID").setSortable(true);
        grid.addColumn(Publisher::getTitle).setHeader("Название").setSortable(true);
        grid.addColumn(Publisher::getCity).setHeader("Город").setSortable(true);

        List<Publisher> publishers = publisherService.findAllPublishers();
        grid.setItems(publishers);
        
        add(grid);
    }
}
