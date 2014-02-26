# Vaadin+JAAS example that works with JBoss

#### Note, that this example is broken on Wildfly and TomEE. Works with  JBoss AS 7.1 and Glassfish. Broken servers seem to be having issue forwarding unauthenticated POST requests. Forwarded POST request done by Vaadin client side initialization somehow becomes GET request when it arrives to VaadinServlet -> FAIL.  Possible [workaround is to use 307 redirect](https://github.com/mstahv/vaadin-cdi-jaas-jbossas-example/compare/workaround?expand=1&w=1).

##How it all works:

* JBoss has an authentication real called "ApplicationRealm" available by default. Add users with group "users" to it with a tool from jboss directory: ./bin/add-user.sh. Restart jboss if currently running.
* Deploy this app to your test server
	* Try Vaadin UI from "/secure/", should instead show LoginUI
	* "Admin view" should not be found 
* After login with the credientials from first step, things should just work.
* (OPTIONAL) Switch to custom security realm like LDAP or DB based authentication


