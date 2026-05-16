FROM tomcat:10.1-jre17-temurin-jammi

RUN rm -rf /usr/local/tomcat/webapps/*


COPY target/sistema-reservas.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]