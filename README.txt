# FuretAzancoth_cpoa_projet

##Répartition des tâches

Azancoth Valentin: 60%
Furet Alexandre : 40%

##Bilan des fonctionnalités

Partie fonctionnelle:

- Persistance : Il est possible de changer de persistance pour Client, Catégorie, Produit. Par manque de temps, et pour pouvoir avoir un changement de persistance fonctionnel, nous avons mis la choicebox(qui permet de changer de persistance) dans chaque Fiches contenant la table view.
- Affichage des données : Les données s'affichent pour chaques modèles dans les deux persistances excepté pour Commande qui est en liste mémoire seulement.
- Ajouter/Modifier/Supprimer : Fonctionnel pour Client, Produit et Commande. Seule la méthode supprimer fonctionne pour Ligne_commande.
- Doublons :  On ne peut pas ajouter de doublons.
- Détails : On peut visualiser le détail d'un client, d'un produit et d'une commande (qui est ligne_commande). On n'a pas jugé nécessaire de faire le détail d'une catégorie car il n'y avait que 2-3 colonnes. Pour ce faire, il suffit de double cliquer sur la ligne souhaitée.
- Suppression : Demande de confirmation de la suppression.
- Fiche Ajouter/Modifier : Les erreurs de données saisies sont gérées et déclarées à l'utilisateur. On ne peut pas valider tant que les données ne sont pas correctes. La modalité des fiches est en "APPLICATION_MODALE". Ce qui empêche l'utilisateur d'interagir avec l'application principale tant que la fiche n'est pas fermée.
- Boutons : Quand un élément est sélectionné dans le tableau, le bouton ajouter est désactivé et les boutons modifier et supprimer sont activés. A contrario, si rien n'est sélectionné ou qu'on fait perdre la sélection en cliquant sur une ligne vide, le bouton ajouter s'active et les deux autres redeviennent inactifs.

Partie non fonctionnelle (ou en partie):

- Suppression en cascade : La suppression en cascade n'est pas gérée, mais un début a été tenté pour catégorie.
- Ajouter/Modifier pour Ligne_Commande : Tentative non aboutie.
- Changement de persistance pour Commande.
- Affichage de la quantité de produits commandés.
- Suppression de toutes les lignes de commandes quand on supprime une commande donnée
- Recherche : Toutes les recherches ne fonctionnent qu'a l'initialisation de l'application (on sait pourquoi, mais on a pas eu le temps de corriger).
On peut rechercher un client par nom; prénom; nom suivit du prénom ou prénom suivit du nom. 
La recherche d'un produit par nom fonctionne, mais pas par nom de catégorie (cela fonctionne si on rentre l'id de la catégorie). On peut rechercher des commandes seulement par l'id du client.
Visuels : L'affichage du visuel pour Produit ne change pas d'un produit à l'autre.

Autre:

Durant le projet, un membre a eu des complications du à la crise sanitaire. Cependant, nous avons tout fait pour terminer le project dans les temps.
