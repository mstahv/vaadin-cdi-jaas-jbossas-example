package com.vaadin.cdi.example;


import com.vaadin.cdi.CDIUI;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import org.vaadin.maddon.label.Header;

/**
 * This UI is an example that has been secure with security-constraint rule
 * in web.xml
 */
@CDIUI("secure")
public class SecureUI extends UI {

    @Override
    public void init(VaadinRequest request) {
        setContent(new Header("UI secured with web.xml rule"));
    }

}
