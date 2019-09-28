# NumbersLight
Projet réalisé en vue d'un poste de développeur chez Tapptic sur une période de 7 jours.

Le but est d'afficher une liste de nombre avec leur image ainsi que leurs détails dans un affichage dédié. En cas de tablette, les deux affichages doivent se partager l'écran en mode paysage sinon un deuxième écran est affiché. 

## Commentaires :

### Exigences respectées :
* Toutes les exigences fonctionnelles et techniques ont été lues et implémentées au mieux.
* Le projet est écrit en Java et Kotlin : en Java, toute les classes "Service" alors qu'en Kotlin tout ce qui se réfère à Android

### Exigences non respectées :
* Il a été demandé en supplément d'écrire des tests unitaires, malheureusement ce ne fut pas intégré dans la formation. 
Il est prévu que j'apprenne par moi même après la fin de la formation vue l'importance du sujet.
* Il a été demandé d'utiliser RxJava or il s'agit d'une librairie que je ne connais pas, j'ai décidé de me concentrer pour ce projet sur mes compétences actuels en Android
* Il a été demandé d'utiliser les injections de dépendances et les logs http, ces deux sujets dépassent actuellement mes connaissances.

### Hypothèses Faites : 

* Il nous a été demandé d'avoir une architecture similaire au MVP, j'ai donc employé l'architecture MVVM avec le databinding afin de faciliter l'écriture. 
De plus, j'ai utilisé le graphe de navigation pour la gestion des fragments. J'ai considéré ces librairies comme les librairies de support puisque intégré à Androidx.

* J'ai utilisé le patron d'Android Studio Master View Detail/Flow afin de décider du seuil entre téléphone et tablette à savoir une largeur de 900. 
Cependant, je n'ai pas utilisé ce patron pour construire l'application.

* Par commodité, la dimension des images ont une valeur fixe. J'ai concentré mes efforts pour l'application plutot que d'avoir un visuel propre. De fait, sur le visuel n'est pas adapté pour une résolution trop petite.
 

### Problèmes rencontrées : 

Afin de gérer les différents états : normal, en focus et selectionné, j'ai choisi les 3 couleurs primaires pour les distinguer au mieux.
J'ai rencontré des difficultés afin de gérer l'état focus, la solution trouvée fut d'obtenir l'état focus lors d'un premier toucher, l'état selectionné est obtenu lors d'un deuxième toucher.
Il en résulte qu'il nécessaire de clicquer deux fois sur un item pour provoquer la navigation.
