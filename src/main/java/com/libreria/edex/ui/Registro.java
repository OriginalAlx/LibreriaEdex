package com.libreria.edex.ui;

import com.libreria.edex.service.AuthService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("/registro")
public class Registro extends VerticalLayout {
private final AuthService authService;

    public Registro(AuthService authService) {
        this.authService = authService;

        TextField username = new TextField("Nombre de Usuario");
        PasswordField password = new PasswordField("Contraseña");
        PasswordField confirmPassword = new PasswordField("Confirmar Contraseña");
        Button btnRegistrar = new Button("Registrarse");

        btnRegistrar.addClickListener(e -> {
            if (!password.getValue().equals(confirmPassword.getValue())) {
                Notification.show("Las contraseñas no coinciden", 3000, Notification.Position.MIDDLE);
                return;
            }
            
            try {
                authService.registrarCliente(username.getValue(), password.getValue());
                Notification.show("Registro exitoso. Ahora puedes iniciar sesión.", 3000, Notification.Position.MIDDLE);
                // Redirigir al login
                UI.getCurrent().navigate("login");
            } catch (Exception ex) {
                Notification.show("Error: " + ex.getMessage(), 3000, Notification.Position.MIDDLE);
            }
        });

        add(new H2("Crear Cuenta de Cliente"), username, password, confirmPassword, btnRegistrar);
        setAlignItems(FlexComponent.Alignment.CENTER);
    }
}

