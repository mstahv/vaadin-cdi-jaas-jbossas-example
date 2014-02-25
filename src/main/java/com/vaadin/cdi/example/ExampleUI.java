package com.vaadin.cdi.example;

import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.cdi.example.util.CounterService;
import com.vaadin.cdi.example.view.ErrorView;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.navigator.ViewProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@CDIUI
public class ExampleUI extends UI {

    @Inject
    private CDIViewProvider viewProvider;

    @Inject
    private CounterService counter;
    
    @Inject
    private ErrorView errorView;

    @Override
    public void init(VaadinRequest request) {
        setSizeFull();

        VerticalLayout navigatorLayout = new VerticalLayout();
        navigatorLayout.setSizeFull();

        Navigator navigator = new Navigator(ExampleUI.this, navigatorLayout);
        navigator.addProvider(viewProvider);
        navigator.setErrorView(errorView);

        setContent(navigatorLayout);
    }
    
}
