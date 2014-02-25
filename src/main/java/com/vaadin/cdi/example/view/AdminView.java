package com.vaadin.cdi.example.view;

import com.vaadin.cdi.CDIView;
import com.vaadin.cdi.example.util.CounterService;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.New;
import javax.inject.Inject;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MVerticalLayout;

@CDIView(value = AdminView.VIEW_ID)
@RolesAllowed("users")
public class AdminView extends AbstractView {

    public static final String VIEW_ID = "admin";

    // UI scoped
    @Inject
    private CounterService counterService;

    // This is a separate instance, not the default UI scoped one
    @Inject
    @Dependent
    @New
    private CounterService privateCounter;

    private Label uiScopedCountLabel = new Label();
    private Label privateCountLabel = new Label();

    @Override
    protected Component buildContent() {
        VerticalLayout layout = new MVerticalLayout();
        
        layout.addComponent(new Header("This is only show for users in 'users' role"));

        uiScopedCountLabel.setSizeUndefined();
        layout.addComponent(uiScopedCountLabel);

        privateCountLabel.setSizeUndefined();
        layout.addComponent(privateCountLabel);

        Button incrementButton = new Button("Increment UI scoped");
        layout.addComponent(incrementButton);
        incrementButton.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                counterService.next();
                updateLabels();
            }
        });

        Button incrementButton2 = new Button("Increment private");
        layout.addComponent(incrementButton2);
        incrementButton2.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                privateCounter.next();
                updateLabels();
            }
        });

        updateLabels();

        return layout;
    }

    private void updateLabels() {
        uiScopedCountLabel.setValue("UI scoped counter = "
                + counterService.get());
        privateCountLabel.setValue("Private counter = " + privateCounter.get());
    }
}
