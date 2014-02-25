# Vaadin CDI example app

This is example app for Vaadin CDI add-on. Check out sources into your IDE 
and play with it.

Notable things in this example app

 * See usage of CounterService in varios classes
 * Using CDI events in EventLogView
 * JAAS based login example in
    * Any std, security realm can be used like an LDAP service. Setup is most likely needed. For testing in GlassFish: open admin consele and add users to "file" realm with "Users" group.
    * LoginView -  view to login
    * AdminView - view secured with RolesAllowed annotation
    * SecureUI - UI secured with web.xml security-constraint rule
    * LoginUI



