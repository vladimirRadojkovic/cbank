FROM tomcat:9.0-jdk8

# Remove existing Tomcat webapps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the WAR file to the webapps directory - deploy as ROOT.war for root context
COPY target/cbank.war /usr/local/tomcat/webapps/ROOT.war

# Create directory for context configuration and copy the context.xml
RUN mkdir -p /usr/local/tomcat/conf/Catalina/localhost/
COPY src/main/resources/context.xml /usr/local/tomcat/conf/Catalina/localhost/ROOT.xml

# Make sure Tomcat can write to the work directory
RUN chmod -R 755 /usr/local/tomcat/work

EXPOSE 8080

CMD ["catalina.sh", "run"] 