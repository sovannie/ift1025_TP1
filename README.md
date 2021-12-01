# ift1025_TP1
Correcteur de texte
    Programme JAVA ayant pour but de corriger le texte se trouvant dans un textArea en 
    cherchant chacun des mots s'y trouvant dans un dictionnaire chargé par l'utilisateur

Description des classes:

Executable
    Implémentation de l'interface graphique


InterfaceGraphique  (Classe principale)

   
    public IntructeurGraphique()  // [26-76] CONSTRUCTEUR
        Le constructeur IntructeurGraphique() place dans des Jpanel: 
         -le textArea contenant le texte à corriger,        [28-33]
         -le textArea qui va contenir les 5 mots proposés   [35-41] 
         -le menu principal avec 4 items                    [45-66]
                -Dictionnaire
                -Vérifier
                -Fichier -> Ouvrir
                -Fichier -> Save
         -et ajoute ces panels dans une fenêtre             [68-74]
         
         Menu: chacun des items du menu est relié à un ActionListener. Lorsque cliqué,
               l'évènement généré est capté et traité par la méthode actionPerformed()
   
    
   
   
    public void actionPerformed(ActionEvent e) //[78-136] Méthode qui traite les ActionPerformed 
       
        Contient 4 statements "if", un pour chacun des items du menu.
        Ainsi, dépendemment de la source du "click", un traitement différent est généré.
        
        -if Item dictionnaire selectionné: [84-97]
             une implémentation de la classe dictionnaire est créée
       
        -if Item vérifié selectionné:       [100-110]
             une implémentation de la classe TraitementTextArea est créée afin de 
             pouvoir utiliser sa méthode  vérifier() qui va surligner les mots non 
             reconnus 
         
        -if Item ouvrir selectionné:         [114-125]
            une implémentation de la classe TraiterFichier est créée afin de
            pouvoir utiliser sa méthode lireFichier() qui permet d'ouvrir un 
            fichier choisi par l'utilisateur et de placer sous forme de String 
            son contenu dans le textArea
            
        -if Item save selectionné:           [128-135]
            une implémentation de la classe TraiterFichier est créée afin de
            pouvoir utiliser sa méthode saveFichier() qui permet d'enregistrer
            le texte se trouvant dans textArea et de le placer dans un fichier
            au choix de l'utilisateur
            
     
     public void mouseClicked(MouseEvent e) //[140-194] 
            
         Méthode qui traite les mouseClicked (évènements générés quand l'utilisateur clique dans un des textArea)
         Contient 2 statement if, un pour chacun des textArea (textArea et suggestBox)
            
         -if Click dans textArea:  [144-159]
             Reconnait le mot selectionné et l'enregistre dans String "word"
             Implémentation de la classe TraitementTextArea afin d'utiliser sa méthode
             motsPlusProche(word) qui va trouver les 5 mots du dictionnaire le plus proche
             du mot selectionné et les placer dans le textArea "suggestBox"
         
         -if Click dans suggestBox:  [162-190]
             Reconnait le mot selectionné et l'enregistre dans un String "motChoisi"
             Remplace le mot "word" selectionné plus tôt par le "motChoisi"
 
             
                   
Comparateur

Dictionnaire

TextAreaHighlight

Tokenizer
TraitementTextArea
TraiterFichier
   
    



