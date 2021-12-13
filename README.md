# pyramide-resto-app-back
# back-end

Dans src/main/ressources créer un fichier application.properties dont la structure est la suivante

server.port 1998 # port d'écoute du serveur, à changer si vous le souhaitez
spring.datasource.url=jdbc:mysql://<<adresse de votre base de données >>:<<port>>/restaurant?serverTimezone=CET
spring.datasource.username=<<utilisateur base de données >>
spring.datasource.password=<<mot de passe>>
spring.jpa.show-sql=true


spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL8Dialect

spring.jpa.hibernate.ddl-auto = update

spring.servlet.multipart.enabled=true
spring.servlet.multipart.max-file-size=1GB
spring.servlet.multipart.max-request-size=2GB

