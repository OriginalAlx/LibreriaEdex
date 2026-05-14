package com.libreria.edex.ui;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;

@Route("catalogo")
public class CatalogoView extends VerticalLayout implements BeforeEnterObserver {
    
    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        // Verificar si el usuario está autenticado
        if (getUI().isPresent() && getUI().get().getSession() != null) {
            var session = getUI().get().getSession();
            if (session.getAttribute("user") == null) {
                // No autenticado, redirigir a login
                event.rerouteTo("login");
                return;
            }
        }
        
        add(new H1("Catálogo de Útiles Escolares"));
        add(new Paragraph("Bienvenido al sistema."));
    }
}

