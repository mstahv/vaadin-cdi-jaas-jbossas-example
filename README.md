# Vaadin+JAAS example that works with JBoss

### Note, you must use JBoss AS 7.1 instead of latest WildFly. With it there is an obscurce bug when redirecting unauthenticated requests. Redirected POST request done by Vaadin client side somehow becomes GET request when it arrives to VaadinServlet -> FAIL


##How it all works:

* JBoss has an authentication real called "ApplicationRealm" available by default. Add users with group "users" to it with a tool from jboss directory: ./bin/add-user.sh. Restart jboss if currently running.
* Deploy this app to your test server
	* Try Vaadin UI from "/secure/", should instead show LoginUI
	* "Admin view" should not be found 
* After login with the credientials from first step, things should just work.
* (OPTIONAL) Switch to custom security realm like LDAP or DB based authentication


