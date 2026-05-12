package com.libreria.edex.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("/login")
public class Inicio extends VerticalLayout {
    
    public Inicio() {
        Image logo = new Image("images/libreria-edex-logo.png", "Logo de la aplicación");
        logo.setWidth("300px");
        logo.getStyle().set("height", "auto"); 
        logo.getStyle().set("margin-bottom", "5px");

        TextField usuario = new TextField("Usuario *");
        usuario.setWidth("300px");

        PasswordField contrasena = new PasswordField("Contraseña *");
        contrasena.setWidth("300px");

        VerticalLayout campos = new VerticalLayout(usuario, contrasena);
        campos.setSpacing(true);
        campos.setPadding(false);
        campos.setAlignItems(FlexComponent.Alignment.CENTER);

        Button botonIngresar = new Button("Ingresar");
        Button botonRegistro = new Button("Registrarse");
        Anchor enlaceOlvido = new Anchor("#", "¿Olvidaste tu contraseña?");

        VerticalLayout acciones = new VerticalLayout(botonIngresar, enlaceOlvido, botonRegistro);
        acciones.setSpacing(true);
        acciones.setPadding(false);
        acciones.setAlignItems(FlexComponent.Alignment.CENTER);
        acciones.getStyle().set("margin-top", "20px"); // separación del bloque de campos

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
        setSizeFull();
        setAlignItems(FlexComponent.Alignment.CENTER);
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        add(formulario);

    }

}
