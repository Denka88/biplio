package bip.online.biplio2023;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.SvgIcon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.Layout;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.server.menu.MenuConfiguration;
import com.vaadin.flow.server.menu.MenuEntry;
import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.annotation.security.PermitAll;

import java.util.List;

@Layout
@PermitAll
public class MainLayout extends AppLayout {

    public MainLayout() {
        DrawerToggle toggle = new DrawerToggle();

        H1 title = new H1("<Библиотека>");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");

        SideNav nav = getSideNav();

        Scroller scroller = new Scroller(nav);
        scroller.setClassName(LumoUtility.Padding.SMALL);

        addToDrawer(scroller);
        addToNavbar(toggle, title);
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