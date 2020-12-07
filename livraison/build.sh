#!/bin/bash

# On lance l'export en jar exécutable
cd src
chmod +x ./gradlew
./gradlew clean distZip

# On vide le contenue du dossier dist
rm -rf ../dist/*

# On copie le zip exporté dans dist et on dézip
cp jeuAssemblage/build/distributions/jeuAssemblage*.zip ../dist
cd ../dist
unzip $(ls *.zip)

# On copie les fichiers .jar à la racine de dist et on supprime le reste
mv jeuAssemblage*/lib/* .
rm *.zip jeuAssemblage*/ -r
