package com.libreria.edex.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinServletRequest;
import com.vaadin.flow.component.UI;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

@Route(value = "login", layout = null)
public class Inicio extends VerticalLayout {
    
    public Inicio() {
        // Verificar autenticación de forma segura
        try {
            VaadinServletRequest request = (VaadinServletRequest) VaadinService.getCurrentRequest();
            if (request != null && request.getUserPrincipal() != null) {
                UI.getCurrent().navigate("catalogo");
                return;
            }
        } catch (Exception e) {
            // Si no hay contexto de request, continuar mostrando el login
        }

        setSizeFull();
        setAlignItems(FlexComponent.Alignment.CENTER);
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        Image logo = new Image("images/libreria-edex-logo.png", "Logo de la aplicación");
        logo.setWidth("300px");
        logo.getStyle().set("height", "auto").set("margin-bottom", "5px");

        TextField usuario = new TextField("Usuario *");
        usuario.setWidth("300px");
        usuario.getElement().setAttribute("autocomplete", "username");

        PasswordField contrasena = new PasswordField("Contraseña *");
        contrasena.setWidth("300px");
        contrasena.getElement().setAttribute("autocomplete", "current-password");

        VerticalLayout campos = new VerticalLayout(usuario, contrasena);
        campos.setSpacing(true);
        campos.setPadding(false);
        campos.setAlignItems(FlexComponent.Alignment.CENTER);

        Button botonIngresar = new Button("Ingresar");
        Button botonRegistro = new Button("Registrarse");
        
        botonRegistro.addClickListener(e -> UI.getCurrent().navigate("registro"));
        
        Anchor enlaceOlvido = new Anchor("#", "¿Olvidaste tu contraseña?");

        VerticalLayout acciones = new VerticalLayout(botonIngresar, enlaceOlvido, botonRegistro);
        acciones.setSpacing(true);
        acciones.setPadding(false);
        acciones.setAlignItems(FlexComponent.Alignment.CENTER);
        acciones.getStyle().set("margin-top", "20px"); 

        VerticalLayout formulario = new VerticalLayout(logo, campos, acciones);
        formulario.setAlignItems(FlexComponent.Alignment.CENTER);
        formulario.setSpacing(true);
        formulario.getStyle()
                .set("border", "1px solid #e0e0e0")
                .set("border-radius", "16px")
                .set("padding", "30px")
                .set("box-shadow", "0 6px 18px rgba(0,0,0,0.12)")
                .set("background-color", "#ffffff")
                .set("max-width", "400px")
                .set("width", "100%");
        
        add(formulario);

        botonIngresar.addClickListener(e -> {
            String user = usuario.getValue();
            String pass = contrasena.getValue();

            if (user.isEmpty() || pass.isEmpty()) {
                Notification.show("Por favor ingrese usuario y contraseña");
                return;
            }

            try {
                // Obtener el request HTTP actual
                VaadinServletRequest vaadinRequest = (VaadinServletRequest) VaadinService.getCurrentRequest();
                HttpServletRequest httpReq = vaadinRequest.getHttpServletRequest();
                
                // Autenticar con Spring Security
                httpReq.login(user, pass);
                
                // Guardar el usuario en la sesión de Vaadin
                UI.getCurrent().getSession().setAttribute("user", user);
                
                Notification.show("Bienvenido " + user);
                
                // Redirigir al catálogo
                UI.getCurrent().navigate("catalogo"); 
                
            } catch (ServletException ex) {
                System.out.println(">>> [LOGIN FALLIDO] Credenciales incorrectas para: " + user);
                Notification.show("Usuario o contraseña incorrectos");
                contrasena.clear();
                usuario.focus();
            }
        });
    }
}