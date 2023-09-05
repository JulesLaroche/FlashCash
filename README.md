FlashCash - Application de Gestion de Transfert d'Argent

FlashCash est une application permettant de gérer les transferts d'argent entre amis et avec sa banque. Cette application est développée en utilisant Java, Spring Boot, une base de données MySQL, Lombok, javax, Spring Security et Maven.

Fonctionnalités
Transfert d'Argent : Permet aux utilisateurs d'envoyer de l'argent à leurs amis ou à leur compte bancaire.

Gestion de Contacts : Offre la possibilité d'ajouter et de gérer des contacts pour faciliter les transferts.

Historique des Transactions : Enregistre et affiche l'historique des transactions.

Sécurité : Utilise Spring Security pour sécuriser l'application et authentifier les utilisateurs.

Compilation et Exécution
Pour compiler et exécuter l'application FlashCash, suivez ces étapes :

Clonage du Projet : Clonez ce dépôt sur votre ordinateur en utilisant la commande git clone [URL].

Configuration de la Base de Données : Configurez les paramètres de la base de données MySQL dans le fichier application.properties situé dans le dossier src/main/resources.

Compilation et Exécution : Ouvrez une invite de commande ou un terminal dans le répertoire du projet et exécutez la commande suivante pour compiler et exécuter l'application :

arduino
Copy code
mvn spring-boot:run
L'application sera accessible à l'adresse http://localhost:8080 dans votre navigateur web.

Structure du Projet
Le projet est organisé comme suit :

lua
Copy code
/
|-- src/
|   |-- main/
|   |   |-- java/
|   |   |   |-- com/
|   |   |   |   |-- flashcash/
|   |   |   |   |   |-- controllers/       # Contrôleurs de l'application
|   |   |   |   |   |-- models/            # Modèles de données
|   |   |   |   |   |-- repositories/      # Interfaces de repository
|   |   |   |   |   |-- services/          # Services de l'application
|   |   |   |   |   |-- FlashCashApplication.java   # Classe principale de l'application
|   |   |-- resources/
|   |   |   |-- application.properties     # Configuration de la base de données
|   |-- test/                               # Tests unitaires et d'intégration
|-- pom.xml                                # Fichier de configuration Maven
|-- README.md                             # Ce fichier README
Auteur
Jules Laroche
