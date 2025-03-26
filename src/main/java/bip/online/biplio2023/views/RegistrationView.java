package bip.online.biplio2023.views;

import bip.online.biplio2023.dto.UserDto;
import bip.online.biplio2023.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Регистрация")
@Route(value = "register", autoLayout = false)
@AnonymousAllowed
public class RegistrationView extends VerticalLayout {
    
    private final UserService userService;

    public RegistrationView(UserService userService) {
        this.userService = userService;

        FormLayout formLayout = new FormLayout();
        
        TextField name = new TextField("Имя");
        TextField surname = new TextField("Фамилия");
        TextField username = new TextField("Имя пользователя");
        PasswordField password = new PasswordField("Пароль");
        
        name.setWidth("100%");
        surname.setWidth("100%");
        username.setWidth("100%");
        password.setWidth("100%");
        
        formLayout.setWidth("500px");
        formLayout.setHeight("auto");


        Button registerButton = new Button("Зарегистрироваться");
        
        formLayout.add(
                name,
                surname,
                username,
                password,
                registerButton
        );

        FlexLayout centerWrapper = new FlexLayout(formLayout);
        centerWrapper.setSizeFull();
        centerWrapper.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        centerWrapper.setAlignItems(FlexComponent.Alignment.CENTER);

        add(centerWrapper);

        Binder<UserDto> binder = new Binder<>(UserDto.class);
        binder.forField(name)
                .asRequired("Поле обязательно для заполнения")
                .bind(UserDto::getName, UserDto::setName);

        binder.forField(surname)
                .asRequired("Поле обязательно для заполнения")
                .bind(UserDto::getSurname, UserDto::setSurname);

        binder.forField(username)
                .asRequired("Введите имя пользователя")
                .bind(UserDto::getUsername, UserDto::setUsername);

        binder.forField(password)
                .asRequired("Введите пароль")
                .withValidator(pass -> pass.length() >= 6, "Пароль должен содержать минимум 6 символов")
                .bind(UserDto::getPassword, UserDto::setPassword);

        registerButton.addClickListener(e -> {
            UserDto data = new UserDto();
            if (binder.writeBeanIfValid(data)) {
                userService.save(data);
                Notification.show("Регистрация успешна для: " + data.getUsername(),
                        3000, Notification.Position.MIDDLE);
                getUI().ifPresent(ui -> ui.navigate("/login"));
            }
        });
        
        
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }
}