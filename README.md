# 1- Contraintes
    - Le nom de la fonction appelée par l'URL doit toujours se terminer par un .go
    - Si on veut créer un formulaire d'insertion d'une classe dans une base, le nom des input doit correspondre au nom de l'attribut de la classe.
    - le fichier framework.jar doit être ajouté au dépendances du projet ou aussi variable d'environnement
    - Si on veut créer une classe Model, on doit déclarer un constructeur vide

# 2- Utilisations
    - Il faut copie-coller le code ci-dessous dans le Web.xml

        <servlet>
            <servlet-name>FrontServlet</servlet-name>
            <servlet-class>etu1900.framework.servlet.FrontServlet</servlet-class>
            <init-param>
                <param-name>sessionName</param-name>
                <param-value>user</param-value>
            </init-param>

        </servlet>
        <servlet-mapping>
            <servlet-name>FrontServlet</servlet-name>
            <url-pattern>*.go</url-pattern>
        </servlet-mapping>

    -Vous pouvez configurer le nom de la session ici 

        <param-value>user</param-value>

    - On notera de l'annotation @Crud la fonction qu'on souhaite appeler grâce à l'URL.
    La fonction retourne toujours un ModelView qu'on initialisera une "view" qu'on reliera à un fichier .jsp qu'on créera.
    - on peut aussi retourner comme type de retour un objet si on le note avec l'annotation @API
    - on peut aussi annoter la fonction avec l'annotation @Auth pour que cette fonction ne soit disponible qu'avec un user spécifique
    

