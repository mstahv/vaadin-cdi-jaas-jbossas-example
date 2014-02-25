package com.vaadin.cdi.example;

import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.access.JaasAccessControl;
import com.vaadin.cdi.example.view.LoginView;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.maddon.button.PrimaryButton;
import org.vaadin.maddon.layouts.MVerticalLayout;

/**
 * This UI is showed if user tries to access UI secured by a web.xml security 
 * constraint.
 */
@CDIUI(value = "login")
public class LoginUI extends UI {

    private final TextField user = new TextField("User");
    private final PasswordField pw = new PasswordField("Password");
    private final Button login = new PrimaryButton("Login");

    @Inject
    JaasAccessControl service;

    @PostConstruct
    void setup() {
        login.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    JaasAccessControl.login(user.getValue(), pw.getValue());
                    // Reload the page to get the actually accessed UI
                    Page page = Page.getCurrent();
                    page.setLocation(page.getLocation());
                } catch (Exception ex) {
                    Notification.show("FAILED");
                    Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @Override
    public void init(VaadinRequest request) {
        setContent(new MVerticalLayout(user, pw, login));
        user.selectAll();
    }

}
