import java.io.*;
import java.util.ArrayList;

public class Sudoku
 {
   public static char[] rawSudoku = new char[323]; // tableau de caractères utile pour traitement dans les méthodes

   private static final int tailleGrille = 9;

   public static int[][] grilleSudokuFormat = {
    {0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0} 
  };

   public static char[] Lecteur(String fichier) // Méthode pour convertir le flux fichier en un array de caracteres
   {
    try{
      FileReader lecteur = new FileReader(fichier);

      lecteur.read(rawSudoku);
      lecteur.close();

      for (int indice = 0; indice < rawSudoku.length;indice++)
      {
        if (indice % 4 == 0 || indice % 4 == 1)
        {
          if (Character.getNumericValue(rawSudoku[indice]) > 8 || (Character.isDigit(rawSudoku[indice]) != true))
          {
            System.out.println("La coordonnee " + rawSudoku[indice] + " indice " + indice + " est invalide dans le fichier: " + fichier);
            System.exit(1);
          }
        }
        else if (indice % 4 == 2)
        {
          if (Character.getNumericValue(rawSudoku[indice]) > 9 || (Character.isDigit(rawSudoku[indice]) != true))
          {
            System.out.println("La valeur " + rawSudoku[indice] + " indice " + indice + " est invalide dans le fichier: " + fichier);
            System.exit(1);
          }
        }
        else if (indice % 4 == 3)
        {
          if (rawSudoku[indice] != ' ')
          {
            System.out.println("Le format du fichier ne respecte pas le format attendu 'XYZ espace'");
            System.exit(1);
          }
          
        }
      }
    }

    catch (Exception exception)
    {
      System.out.println("le fichier n'a pu être ouvert");
      exception.printStackTrace();
      System.exit(1);
    }

    return rawSudoku;
   }

  

// séparation des valeurs provenant des données du fichier sudoku sous forme de tableau de caractères selon les coordonnées XYZ 
  public static ArrayList<Character> tableauX(char[] tableau)
  {
    ArrayList<Character> tableauX = new ArrayList<>();
    
    for (int i =  0;i < tableau.length;i++)
    {
      if (i % 4 == 0)
      {
        tableauX.add(tableau[i]);
      }
    }
    return tableauX;
  }

  public static ArrayList<Character> tableauY(char[] tableau)
  {
    ArrayList<Character> tableauY = new ArrayList<>();
    
    for (int i =  0;i < tableau.length;i++)
    {
      if (i % 4 == 1)
      {
        tableauY.add(tableau[i]);
      }
    }
    return tableauY;
  }

  public static ArrayList<Character> tableauZ(char[] tableau)
  {
    ArrayList<Character> tableauZ = new ArrayList<>();
    
    for (int i =  0;i < tableau.length;i++)
    {
      if (i % 4 == 2)
      {
        tableauZ.add(tableau[i]);
      }
    }
     return tableauZ;
  }
// construction du tableau à l'aide d'un format prédéfini, et des tableaux de coordonnees pour placement des chiffre dans la grille
  public static int[][] constructionGrille(int[][] grille, ArrayList<Character> coordonneeX, ArrayList<Character> coordonneeY, ArrayList<Character> valeurZ)
  {
    for (int indice = 0; indice < coordonneeX.size(); indice++)
    {
      grille[Character.getNumericValue(coordonneeX.get(indice))][Character.getNumericValue(coordonneeY.get(indice))] = Character.getNumericValue(valeurZ.get(indice));
    }
    return grille;
  }
// affichage de la grille à l'écran
  public static void afficherGrille(int[][] grille) 
  { 
    for (int ligne = 0; ligne < tailleGrille; ligne++) 
    {
      if (ligne % 3 == 0 && ligne != 0) 
      {
        System.out.println("---------------------");
      }
    
      for (int colonne = 0; colonne < tailleGrille; colonne++) 
      {
        if (colonne % 3 != 0 && colonne != 0) 
        {
          System.out.print("*");
        }
        if (colonne % 3 == 0 && colonne != 0) 
        {
          System.out.print("| |");
        }
        System.out.print(grille[ligne][colonne]);
      }
      System.out.println();
    }
  }
// vérification de la grille selon les règles du sudoku
  public static void verifierGrille(int[][] grille) 
  {
    int chiffreVerification = 0;
    for (int indiceX = 0; indiceX < tailleGrille; indiceX++)
     {
       for (int indiceY = 0; indiceY < tailleGrille; indiceY++)
       {
          chiffreVerification = grille[indiceX][indiceY];
          for (int y = indiceY + 1; y < tailleGrille; y++)
          {
            if (grille[indiceX][y] == chiffreVerification)
            {
            System.out.println("La grille n'est pas valide");
            System.exit(1);
            }
          }
          for (int x = indiceX + 1; x < tailleGrille; x++)
          {
            if (grille[x][indiceY] == chiffreVerification)
            {
            System.out.println("La grille n'est pas valide");
            System.exit(1);
            }
          }  
        }
    }
    System.out.println("La grille est valide!");
  }
// calcul de la transposee de la matrice en entree et affichage 
  public static int[][] matriceTransposee(int[][] grille)
  {
    int[][] grilleTransposee = {
      {0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0} 
    };

    for (int x = 0; x < tailleGrille; x++)
    {
      for (int y = 0; y < tailleGrille; y++)
      {
        grilleTransposee[x][y] = grille[y][x];
      }
    }

    return grilleTransposee;
  }

  // fonction main
  public static void main(String[] args)  
  {
    Lecteur("partie4.txt");

    ArrayList<Character> coordonneeX = tableauX(rawSudoku);
    ArrayList<Character> coordonneeY = tableauY(rawSudoku);
    ArrayList<Character> valeurZ = tableauZ(rawSudoku);
    
    int[][] grilleSudoku = constructionGrille(grilleSudokuFormat, coordonneeX, coordonneeY, valeurZ);

    afficherGrille(grilleSudoku);
    System.out.println();
    verifierGrille(grilleSudoku);
    System.out.println();
    int[][] transposee = matriceTransposee(grilleSudoku);
    afficherGrille(transposee);
    System.out.println();
    System.out.println("Voici la matrice transposée de la grille Sudoku en entrée");
  }
}