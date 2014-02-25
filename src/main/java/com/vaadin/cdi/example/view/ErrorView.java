package com.vaadin.cdi.example.view;

import com.vaadin.cdi.CDIView;
import com.vaadin.ui.Component;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MVerticalLayout;

@CDIView("error")
public class ErrorView extends AbstractView {

    @Override
    protected Component buildContent() {
        return new MVerticalLayout(new Header("View not found! Not logged in?"));
    }

}
