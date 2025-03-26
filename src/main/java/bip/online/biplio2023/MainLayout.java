package bip.online.biplio2023;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.Layout;
import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.annotation.security.PermitAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


@Layout
@PermitAll
public class MainLayout extends AppLayout {

    private static final Logger log = LoggerFactory.getLogger(MainLayout.class);

    public MainLayout() {
        DrawerToggle toggle = new DrawerToggle();

        H1 title = new H1("<Библиотека>");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");

        HorizontalLayout navbarRight = new HorizontalLayout();
        navbarRight.setAlignItems(FlexComponent.Alignment.CENTER);
        navbarRight.getStyle().set("margin-left", "auto");

        Button logout = new Button("Выйти", e -> {
            
        });
        logout.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
        logout.getStyle().set("margin-right", "5px");
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()
                && !(authentication.getPrincipal() instanceof String)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            Span username = new Span(userDetails.getUsername());
            username.getStyle()
                    .set("font-size", "var(--lumo-font-size-m)")
                    .set("padding", "0 1rem");

            navbarRight.add(username);
        }
        
        navbarRight.add(logout);

        SideNav nav = getSideNav();
        Scroller scroller = new Scroller(nav);
        scroller.setClassName(LumoUtility.Padding.SMALL);

        addToDrawer(scroller);

        addToNavbar(toggle, title, navbarRight);

    }

    private SideNav getSideNav() {
        SideNav sideNav = new SideNav();
        sideNav.addItem(
                new SideNavItem("Авторы", "/authors",VaadinIcon.USERS.create()),
                new SideNavItem("Книги", "/books", VaadinIcon.BOOK.create()),
                new SideNavItem("Города", "/cities", VaadinIcon.MAP_MARKER.create()),
                new SideNavItem("Жанры", "/genres", VaadinIcon.CLIPBOARD_TEXT.create()),
                new SideNavItem("Издатели", "/publishers", VaadinIcon.GROUP.create())
        );
        return sideNav;
    }
}