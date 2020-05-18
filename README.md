# projetJavaS6

# Bugs rencontrés
- Error:Kotlin: The Kotlin standard library is not found in the module graph. Please ensure you have the 'requires kotlin.stdlib' clause in your module definition :
  faire Build > Rebuild Project
  

# Marche à suivre pour utiliser l'application :

  # 1ère méthode : 
  - Récupérer le fichier zip contenant toutes les ressources du projet.
  - Dézipper le projet sur votre bureau.
  - Dans ce projet, ouvrir le dossier executable.
  - Dans ce dossier se trouve un fichier bat s'appelant launcher.bat
  - Executer ce fichier en cliquant deux fois dessus.
  - L'application va ensuite se lancer.
  
  
  # 2ème méthode (en cas de soucis avec l'executable jar) : 
  - Récupérer le fichier zip contenant toutes les ressources du projet.
  - Dézipper le projet sur votre bureau.
  - Véfifier que le projet est bien présent dans le dossier "source"
  - Importer ce projet sur un IDE comme IntelliJ ou EclipseJAVA
  - Pour faire fonctionner correctement ce projet avec cette méthode il faut modifier les liens d'importation des csv.
  - Pour cela allez dans src/main/java/org/app/usine.java 
  - Allez dans la méthode csvToElement() et remplacez la ligne Scanner sc = new Scanner(new File("../ressources/elements.csv")); par Scanner sc = new Scanner(new File("./src/main/resources/org/csvFiles/elements.csv"));
  - Dans la même méthode remplacez la ligne Scanner sc2 = new Scanner(new File("../ressources/prix.csv")); par Scanner sc2 = new Scanner(new File("../ressources/prix.csv"));
  - Ensuite allez dans la méthode csvToChaine() et remplacez la ligne try (Scanner sc = new Scanner(new File("../ressources/" + FILENAME + ".csv"))) par try (Scanner sc = new Scanner(new File("./src/main/resources/org/csvFiles/" + FILENAME + ".csv")))
  - Ensuite allez dans la méthode csvToPersonnel() remplacez la ligne Scanner sc2 = new Scanner(new File("../ressources/Personnels.csv")); par Scanner sc2 = new Scanner(new File("./src/main/resources/org/csvFiles/Personnels.csv"));
  - Déplacer vous dans l'arborescence "projetJavaS6\src\main\java\org\example"
  - Faire un clic-droit sur le fichier App
  - Cliquer sur l'option Run App.main()
  - L'application va ensuite se lancer.
