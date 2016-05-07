TEAM 1 - 5322 - Spring 2016 - Mav Appoint
WAR file name : MavAppoint5322_S16_Team1

1.	Run the mavappoint.sql database script in your database.

2.	Download the war file attached with the mail.

3.	Copy paste the war file in the <Apache Tomcat Deplyment Directory>/webapps

4.	Run <Apache Tomcat Deplyment Directory>/bin/startup.bat or <Apache Tomcat Deplyment Directory>/bin/startup.sh

5.	Stop the server by running <Apache Tomcat Deplyment Directory>/bin/shutdown.bat or <Apache Tomcat Deplyment Directory>/bin/shutdown.sh

6.	Open the mavappoint.properties file inside the folder <Apache Tomcat Deplyment Directory>/webapps/MavAppoint5322_S16_Team1/WEB-INF/lib

7.	Change the following properties according to your specifications:
	(MANDATORY and needs to be changed)
		MYSQL_USER = <user name of your mysql user>
		MYSQL_PASSWORD = <password of your mysql user>
	
	(OPTIONAL)
		EMAIL_USERNAME = <email id from which emails need to be sent to users>
		EMAIL_PASSWORD = <password for your above email account>
		APP_URL = <application url based on your server and context path>
		APP_CONTEXT = <context path is exactly the same as your war file name>
		
8.	Run <Apache Tomcat Deplyment Directory>/bin/startup.bat or <Apache Tomcat Deplyment Directory>/bin/startup.sh

9.	Your url is http://localhost:8080/MavAppoint5322_S16_Team1		
	(If you haven't changed the war file name. If you have, then the url becomes http://<hostname>:<apache_port>/<context_path>)
	
Sample USERS created:
	Admin	Username	: aadikulkarni91@gmail.com
			Password 	: password
			
	Advisor	Username	: akaadi3@gmail.com
			Password 	: password
	
	Student Username	: aaditya.kulkarni@mavs.uta.edu
			Password 	: password