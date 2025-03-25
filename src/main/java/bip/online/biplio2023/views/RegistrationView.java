package bip.online.biplio2023.views;

import bip.online.biplio2023.dto.UserDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@PageTitle("Регистрация")
@Route("register")
@AnonymousAllowed
public class RegistrationView extends VerticalLayout {
    
    public RegistrationView() {
        
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setPadding(false);

        // Заголовок
        H1 header = new H1("Создать аккаунт");
        header.getStyle()
                .set("margin-bottom", "0")
                .set("color", "var(--lumo-primary-text-color)");

        // Форма
        FormLayout formLayout = new FormLayout();
        formLayout.setWidth("400px");
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1)
        );

        // Поля формы
        TextField username = new TextField("Логин");
        TextField name = new TextField("Имя");
        TextField surname = new TextField("Фамилия");
        PasswordField password = new PasswordField("Пароль");

        // Кнопка регистрации
        Button registerButton = new Button("Зарегистрироваться");
        registerButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        registerButton.setWidthFull();

        // Ссылка на вход
        Span loginLink = new Span("Уже есть аккаунт? Войти");
        loginLink.getStyle()
                .set("cursor", "pointer")
                .set("color", "var(--lumo-primary-color)");
        loginLink.addClickListener(e -> {
            getUI().ifPresent(ui -> ui.navigate("login"));
        });

        // Добавляем компоненты в форму
        formLayout.add(
                name, surname,
                username,
                password,
                registerButton,
                loginLink
        );
        
        add(header, formLayout);

        // Валидация
        Binder<UserDto> binder = new Binder<>(UserDto.class);

        // Валидация имени
        binder.forField(name)
                .bind(UserDto::getName, UserDto::setName);

        // Валидация фамилии
        binder.forField(surname)
                .bind(UserDto::getSurname, UserDto::setSurname);

        // Валидация пароля
        binder.forField(password)
                .bind(UserDto::getPassword, UserDto::setPassword);
        
        binder.forField(username)
                .bind(UserDto::getUsername, UserDto::setUsername);
        
        // Обработка регистрации
        registerButton.addClickListener(e -> {
            if (binder.validate().isOk()) {
                UserDto data = new UserDto();
                binder.writeBeanIfValid(data);

                // Здесь должна быть логика регистрации
                Notification.show("Регистрация успешна для: " + data.getUsername(), 3000, Notification.Position.MIDDLE);

                // Перенаправление после успешной регистрации
                getUI().ifPresent(ui -> ui.navigate(""));
            }
        });

        // Стилизация
        formLayout.getStyle()
                .set("background", "var(--lumo-base-color)")
                .set("border-radius", "var(--lumo-border-radius-l)")
                .set("box-shadow", "var(--lumo-box-shadow-m)")
                .set("padding", "var(--lumo-space-l)");

        // Добавляем все в основной layout
        add(header, formLayout);
    }
}