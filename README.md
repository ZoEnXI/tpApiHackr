# TP Api Hackr

L'application est déployé sur mon VPS.  
La doc (swagger et collection postman) se trouve dans le répertoire **docs**.

Pour l'utiliser il faut d'abord se connecter avec un compte admin ou user.
Puis générer le token d'authentification associé.

###User : 
login : userTest
Mdp : userTest
clientId : clientIdTpApiStandard
clientSecret : clientSecretTpApiStandard

###Admin :

login : adminTest
Mdp : adminTest
clientId : clientIdTpApiStandard
clientSecret : clientSecretTpApiStandard

## Faire tourner l'application en local:

### Prérequis :

- Java 17 (JDK)
- Un IDE :  IntelliJ IDEA est recommandé
- Le fichier de properties (envoyé par teams)

### Installation :

#### 1. Cloner le projet

```git clone git@github.com:ZoEnXI/tpApiHackr.git```

#### 2. Importer le projet dans votre IDE et parametrage :

Sur IntellIJ IDEA :
 
    - File > Open > tpApiHackr
    - File > Project Structure > Project > Project SDK > 17 (télécharger le JDK 17 si non installé)
    - File > Project Structure > Project > Project language level > SDK Default

    - Dans l'onglet maven (le M sur la barre à droite) > Reload all Maven projects
    - Dans l'onglet maven (le M sur la barre à droite) > tpApi > Lifecycle > clean
    - Dans l'onglet maven (le M sur la barre à droite) > tpApi > Lifecycle > install

Copier le fichier de properties dans le répertoire resources
    
    - Copier le fichier de properties dans le répertoire resources : src/main/resources

#### 3. Lancer l'application
    
    - src/main/java/mds/tp/api/hackr/TpApiApplication.java puis click droit > Run 'TpApiApplication.main()'

