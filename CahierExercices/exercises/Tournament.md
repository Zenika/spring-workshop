<div class="pb"></div>

# LAB X - Classes 


## Création d'un tour de tournoi à élimination directe

OK - Créer une classe de test nommée **TournamentRoundGeneratorTest**

OK - Créer le test pour vérifer que le tour contiendra 1 match pour 2 joueurs

OK - Créer les classes du modèle nécessaires pour implémenter la méthode de génération d'un tour

OK - Créer le test pour vérifier que le tour contient  2 matchs pour 4 joueurs

- Modifier l'implémentation précédente pour créer les matches de façon aléatoire

## Vérifier que le nombre de joueur est toujours une puissance de 2 supérieur ou égal à 2 et inférieure ou égale à 32

OK - Créer la classe de test pour tester l'algorithme à mettre en place

OK - Faire porter cette vérification dans la classe Tournament contenant la liste des joueurs lors de sa construction

## Implémenter une méthode main

- Créer une liste de joueurs (Joueur1, Joueur2,...)

- Afficher la liste des matches du premier tour sous la forme *- Player1 0 - 0 Player2* (en testant bien entendu)

## Définir le nom d'un round (ex: final, semifinals...)

OK - Créer une enum pour définir le nom des différents tour possible

OK - Ajouter le nom en propriété d'un tour et qu'il s'initialize automatiquement en fonction du nombre de match

# Ajouter le score d'un match

- Créer une fonction pour mettre à jour le score d'un match (immutable)

# Déterminer le vainqueur d'un match

- Créer une méthode qui a un match donné donne le vainqueur pour le tour suivant

# Générer les tours suivants et afficher le tableau du tournoi

