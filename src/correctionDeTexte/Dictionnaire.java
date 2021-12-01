package correctionDeTexte;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Dictionnaire {
	
	public String texteLu;
	public String[] mots;
	public HashSet<String> dictionnaire = new HashSet<>(); //liste de stockage des mots du dictionnaire
	public String[] dictionnaireTab;
	
	public Dictionnaire() throws IOException {
		/*
		 * methode qui enregistre un dictionnaire chargé dans un HashSet
		 */
		
		TraiterFichier file = new TraiterFichier(); //stockage du fichier contenant le dictionnaire
		texteLu = file.lireFichier(); //stockage du texte lu dans le fichier selectionné	
		mots = texteLu.split("\n"); //chaque mot se trouve sur une ligne dans le dictionnaire, alors on le decoupe a chaque changement de ligne
		
		//on parcourt la liste[] de mots et les stock dans un HashSet<>
		for (String mot : mots) {
			dictionnaire.add(mot);
		}
	
	
	}


}