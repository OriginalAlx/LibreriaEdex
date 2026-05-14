package com.libreria.edex.ui;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("catalogo")
public class CatalogoView extends VerticalLayout {
    public CatalogoView() {
        add(new H1("Catálogo de Útiles Escolares"));
        add(new Paragraph("Bienvenido al sistema."));
    }
}

