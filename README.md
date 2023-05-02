# Utilisation du framework
    - Ajouter dans la variable d'environnement "CLASSPATH" le servlet-api.jar
    - Ajouter le Framework.jar dans la variable d'environnement "CLASSPATH"

# Mode d'utilisation
    - il faut annoter les fonctions avec l'annotation @Crud(url='')
    - La Classe ModelView pourra être utiliser ayant 2 attributs: 
        . String view : le nom du fichier .jsp que vous allez créer
        . HashMap<String, Object> data: les data que vous voulez affichez dans le fichier  .jsp