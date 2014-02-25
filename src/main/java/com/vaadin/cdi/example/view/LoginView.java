package com.vaadin.cdi.example.view;

import com.vaadin.cdi.CDIView;
import com.vaadin.cdi.access.JaasAccessControl;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletException;
import org.vaadin.maddon.button.PrimaryButton;
import org.vaadin.maddon.layouts.MVerticalLayout;

/**
 * And example view to implement login with JAAS as CDIView
 * 
 * See also LoginUI/SecureUI
 * 
 */
@CDIView(value = LoginView.VIEW_ID)
public class LoginView extends AbstractView {

    public static final String VIEW_ID = "login";

    private final TextField user = new TextField("User");
    private final PasswordField pw = new PasswordField("Password");
    private final Button login = new PrimaryButton("Login");
    private final Button logout = new Button("Logout");

    @Inject
    JaasAccessControl service;

    @Override
    protected Component buildContent() {
        VerticalLayout verticalLayout = new MVerticalLayout();

        if (service.isUserSignedIn()) {
            verticalLayout.addComponent(new Label("Current user:" + service.getPrincipalName()));
            verticalLayout.addComponent(logout);
        } else {
            user.selectAll();
            verticalLayout.addComponents(user, pw, login);
        }

        return verticalLayout;
    }

    @PostConstruct
    void setup() {
        login.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                try {
                    JaasAccessControl.login(user.getValue(), pw.getValue());
                    Notification.show("Logged in!");
                    getUI().getNavigator().navigateTo(VIEW_ID);
                } catch (Exception ex) {
                    Notification.show("FAILED");
                    Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        logout.addClickListener(new ClickListener() {

                @Override
                public void buttonClick(ClickEvent event) {
                    try {
                        JaasAccessControl.logout();
                        getUI().getNavigator().navigateTo(VIEW_ID);
                    } catch (ServletException ex) {
                        Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
    }

}
