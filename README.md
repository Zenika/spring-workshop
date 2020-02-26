Détails du modèle dans le [wiki](https://github.com/Zenika/Formation--Modele/wiki)

Documentation du [framework](https://github.com/Zenika/zenika-formation-framework)

En cas de problème avec le modèle, ouvrir une issue sur [le framework](https://github.com/Zenika/zenika-formation-framework).


## Supports PDF et Live

Les supports peuvent être obtenus à cette adresse : https://formation-kotlin-dot-zen-formations.appspot.com/ [![CircleCI](https://circleci.com/gh/Zenika/formation-kotlin/tree/master.svg?style=svg&&circle-token=1d57996d4d20b7c3e4f084efec9dd75228ce8a1c)](https://circleci.com/gh/Zenika/formation-kotlin/tree/master)


## Organisation

``` shell
├── CahierExercices       # Contient les enoncés des TP
│   ├── Cahier.md         # Le fichier peut être découpé en autant de fichier que de TP
│   ├── parts.json        # Contient la liste des fichiers des énoncés
│   ├── README.md         # Explication détaillé de cette section
│   └── ressources        # Contient les images utilisées dans les énoncés des TP
├── Exercices             # Contient des starters dans TP ainsi que les corrections des TP
├── Installation          # Contient les éléments logiciels à fournir aux stagaires
│   └── README.md         # Explication détaillé de cette section
├── PLAN_en.md            # Plan commercial en anglais (affiché sur le site public des formations)
├── PLAN.md               # Plan commercial (affiché sur le site public des formations)
├── README.md             # ce fichier (ne pas effacer son contenu)
├── REFERENCES.md         # Références utiles pour donner ou préparer la formation (destiné aux formateurs)
├── run.sh                # Permet lancer la formation avec Docker sous Linux ou Windows (Git Bash)
├── SessionsNotes         # Contient l'ensemble des notes faites par les formateurs après chaque cession (organisation des TP, timing, ...)
│   └── README.md         # Explication détaillé de cette section
└── Slides                # Contient l'ensemble des slides
    ├── <num>_<slide>.md  # Contenu des slides
    ├── ...
    ├── README.md         # Explication détaillé de cette section
    ├── ressources        # Contient les images et autres éléments utilisés dans les slides
    └── slides.json       # Contient la liste des fichiers des slides
```

*Note : Chaque répertoire contient un fichier `README.md` précisant son usage*


## Utilisation avec Node.js

Pour générer et afficher la formation, il est nécessaire d'avoir `node` et `npm` d'installé (voir [node.js](http://nodejs.org)).

Installation de Node.js :

- https://nodejs.org/en/download
- https://nodejs.org/en/download/package-manager/

Exécuter la commande suivante :

```shell
npm install
```

Pour afficher les slides, exécuter la commande suivante :

```shell
grunt
```

Pour générer les `.pdf` des slides :

```shell
grunt pdf
```


## Utilisation avec Docker

Pour ceux qui veulent se passer de l'installation de `node` et `npm`, il est possible d'utiliser [Docker](https://www.docker.com).

Installation de Docker :

- Linux : https://docs.docker.com/linux/step_one/
- Mac : https://docs.docker.com/mac/step_one/
- Windows : https://docs.docker.com/windows/step_one/

Lancer une des commandes suivantes :

```shell
./run.sh dev      # pour afficher les slides
./run.sh prod     # pour afficher la page de garde
./run.sh pdf      # pour générer les `.pdf` des slides
./run.sh pdf-labs # pour générer les `.pdf` des slides
./run.sh clean    # pour terminer le conteneur Docker
```

Il est également possible de combiner les commandes :

```shell
./run.sh clean pdf prod
```

Par défaut, le _fournisseur_ de démon docker est sélectionné de manière automatique (docker-machine, boot2docker ou natif). Pour forcer un fournisseur en particulier (si vous êtes sous linux mais que vous avez aussi docker-machine par exemple), vous pouvez positionner la variable d'environnement `Z_DOCKER_PROVIDER` qui peut prendre les valeurs `machine|boot2docker|native`.

Exemple sous bash: `Z_DOCKER_PROVIDER=native ./run.sh dev`

Exemple sous fish: `env Z_DOCKER_PROVIDER=boot2docker ./run.sh dev`

### Windows

Afin de pouvoir éxécuter un script `run.sh`, il faut disposer d'un émulateur de shell Unix-like : soit Git Bash (recommandé et fournit avec Git) soit Cygwin.

Si lors du lancement du `run.sh` vous êtes confrontés à l'erreur :

```shell
Loading "Gruntfile.js" tasks...ERROR
>> Error: Cannot find module '/data/Gruntfile.js'
Warning: Task "pdf" not found. Use --force to continue.

Aborted due to warnings.
```

- vous pouvez forcer localement la valeur de la variable `MSYS_NO_PATHCONV` à `1` lors de chacun de vos appels à `run.sh` : `MSYS_NO_PATHCONV=1 ./run.sh pdf`
- vous pouvez forcer globalement la valeur de la variable `MSYS_NO_PATHCONV` à `1` dans votre `~/.bashrc` et utiliser `run.sh` normalement

## Raccourcis clavier

- `Alt + clic`: Zoom
- `Haut`, `Bas`, `Gauche`, `Droite`: Navigation
- `f`: Mode plein écran
- `s`: Afficher les notes du formateur
- `o`: Activer/désactiver le mode aperçu
- `b`: Activer/désactiver le mode écran noir
- `r`: Activer/désactiver le mode télécommande
- `w`: Changer le ratio d'aspect (~4:3, 16:9, 16:10)
- `Echap`: Quitter le mode plein écran, ou activer/désactiver le mode aperçu

https://github.com/hakimel/reveal.js/wiki/Keyboard-Shortcuts


## Mode télécommande

Certaines télécommandes de présentation utilisent les raccourcis `Gauche` et `Droite` pour naviguer entre les diapositives et ne permettent pas de changer ce comportement.
Par défaut cela navigue entre les têtes de chapitre uniquement. Ce n'est pas très pratique... Le mode télécommande permet de remédier à cela.
Une fois le mode télécommande activé les raccourcis de clavier sont changés: `Gauche` et `Haut` affichent la diapositive précédente, `Bas` et `Droite` la dispositive suivante.


## Troubleshooting

En cas de problème lors de l'installation ou de l'utilisation, consultez la page dédié dans le wiki du Framework : [Troubleshooting](https://github.com/Zenika/zenika-formation-framework/wiki/Troubleshooting).

## Aide à la relecture

Pour que votre support soit orthographiquement irréprochable, demandez un coup de main à l'équipe [Orthographe](https://github.com/orgs/Zenika/teams/orthographe-checkers) !

## Intégration Continue

Chaque formation dispose d'une version web (disponible uniquement via un compte @zenika.com).
Le mini-site de la formation contient les slides live, les slides PDF, le cahier d'exercice PDF, un lien vers le dépôt GitHub de la formation ainsi qu'un lien vers le catalogue de formation.

Ainsi il est important de bien remplir le fichier `package.json` avec

- `name` : sous la forme `formation-*` avec seulement des lettres minuscules et des tirets
- `description` : le nom de la formation de la forme `Formation *`
- `homepage` : le lien vers la page du catalogue correspondante (e.g. http://www.zenika.com/formation-angularjs.html)
- `repository.url` : l'URL vers ce repository

### Branching model

`master` is automatically deployed on `https://{name from package.json}-dot-zen-formations.appspot.com/` on every push.

You may create branches on this repository. It is recommended not to use a fork for transparency and to encourage collaboration. Open PRs for your branches to convey the scope and context of the change, even if the work is in progress. Use precise names for your branches and delete them once they have been merged to avoid confusion. If you need branches for your own personnal use, please use a fork.

### Serveur d'IC

Le build et le déploiement sont réalisés par [CircleCI](https://circleci.com).
À ce titre, un fichier `circle.yml` est présent à la racine de ce projet.
Il n'y a, à priori, aucune raison d'éditer ce fichier à l'initialisation, mais une
mise à jour du framework de formation peut requérir une mise à jour de ce fichier.
Elle est alors décrite dans le changelog du framework de formation.
