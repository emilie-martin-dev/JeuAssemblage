# Gradle

Ce projet utilisant Gradle, ce README a pour but d'expliquer les commandes basiques. Toute les commandes se font dans le dossier src/.

## Avant de démarrer

Pour utiliser Gradle il faut commencer par lui donner les droits d'execution : 

```sh
chmod +x ./gradlew
```

## Démarrer le projet

Pour démarrer le projet utilisez la commande suivante : 

```sh
./gradlew jeuAssemblage:run
```

## Javadoc

Pour obtenir la javadoc utilisez la commande suivante : 

```sh
./gradlew javadoc
```

La javadoc de chaque projet sera écrite dans le dossier `XXX/build/docs/javadoc`. XXX étant le nom du projet.

## Obtenir l'exécutable

Pour obtenir les fichiers .jar utilisez la commande suivante : 

```sh
./gradlew distZip
```
Le résultat sera disponible dans un zip disponible dans le dossier `jeuAssemblage/build/distributions`

Dans ce zip, seul le dossier "lib" est interessant.
