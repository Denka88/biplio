package bip.online.biplio2023.views;

import bip.online.biplio2023.entity.Publisher;
import bip.online.biplio2023.service.PublisherService;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route("publishers")
@PageTitle("Издатели")
public class PublishersView extends VerticalLayout {
    
    private final PublisherService publisherService;
    private final Grid<Publisher> grid;


    public PublishersView(PublisherService publisherService) {
        this.publisherService = publisherService;
        this.grid = new Grid<>(Publisher.class, false);;

        setupGrid();
        updateGrid();

        add(grid);
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
}
