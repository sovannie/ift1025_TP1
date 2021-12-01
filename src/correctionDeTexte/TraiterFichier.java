package correctionDeTexte;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.filechooser.*;


//Classe contenant méthodes pour ouvrir et sauvegarder des fichiers
public class TraiterFichier{
	
	JFileChooser filechooser = new JFileChooser();//crée un file chooser

    public String lireFichier() throws IOException {
		/*
		 * methode de lecture de fichiers
		 */

		 //ouvrir un browser pour aller chercher le fichier 		
		 int valueFile = filechooser.showOpenDialog(null); //choisir un fichier. retourne 0 si un fichier a été choisi et 1 si on en a pas choisi
         
		 String ligne = ""; //stockage de la ligne qui vient d'être lue
		 String texteLu = ""; // stockage de tout le texte lu dans le fichier selectionné
		 
		 if(valueFile == JFileChooser.APPROVE_OPTION) {//se lance uniquement si on a choisi un fichier c'est a dire si showOpendialog ou showSave dialog retourne 0
				File file = filechooser.getSelectedFile();//crée une instance du fichier         
				  
				//Pour lire un fichier, inspiré de l'exemple fourni par Jian-Yun Nie	
				BufferedReader reader = null;
					
				try {
					reader = new BufferedReader( new InputStreamReader(new FileInputStream(file), "UTF8"));
					   
				} catch (IOException e1) {
					System.exit(1);
				}
				
				try {
						ligne = reader.readLine();
				} catch (IOException e2) {		
						e2.printStackTrace();
				}
					
				while (ligne != null) {
						texteLu+=ligne+"\n";// a chaque fois qu'on lit une ligne on la rajoute a notre texte et on fait un retour a la ligne
					
					try {
						ligne = reader.readLine();
					
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}//fin de la boucle while			
								
		 }//fin du if
		 return texteLu;
    }//fin de la méthode lireFichier
    
    public void saveInAFile(String textToSave) {
    	/*
		 * methode qui sauvegarde "textToSave" dans un fichier
		 */
    			
			//ouvrir un browser pour aller chercher le fichier 
			int valueFile = filechooser.showSaveDialog(null); //on choisi un fichier dans lequel on va save le contenu
			
			if(valueFile == JFileChooser.APPROVE_OPTION) {//se lance uniquement si on a choisi un fichier c'est a dire si showSavedialog retourne 0
				
               File file = filechooser.getSelectedFile();//crée une instance du fichier
				
			    try {
                      Writer out = new BufferedWriter((new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)));
                      out.append(textToSave);// on ecrit le contenu de textToSave dans le fichier qu'on a choisi
				      out.close();
				    
				    } catch (IOException e1) {

				      e1.printStackTrace();
				    }
			}
    	
    }

}//fin de la classe TraiterFichier