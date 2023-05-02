# Utilisation du framework
    - Ajouter dans la variable d'environnement "CLASSPATH" le servlet-api.jar
    - Ajouter le Framework.jar dans la variable d'environnement "CLASSPATH"

# Mode d'utilisation
    - il faut annoter les fonctions avec l'annotation @Crud(url='')

    - La Classe ModelView pourra être utiliser ayant 2 attributs: 
        . String view : le nom du fichier .jsp que vous allez créer
        . HashMap<String, Object> data: les data que vous voulez affichez dans le fichier  .jsp

    - La classe Model que tu crées doit avoir un constructeur vide

    - le fichier .xml doit contenir
        <servlet>
        <servlet-name>FrontServlet</servlet-name>
        <servlet-class>etu1900.framework.servlet.FrontServlet</servlet-class>
        </servlet>
        <servlet-mapping>
            <servlet-name>FrontServlet</servlet-name>
            <url-pattern>/</url-pattern>
        </servlet-mapping>
        
    - l'action avec un formulaire crée dans être de la forme /framework/nomMéthode