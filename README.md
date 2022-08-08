# Sokoban
Sokoban game project started in the context of the object oriented class in ULiège

----------------------------------Sokoban Projet Eliott Py--------------------------------


This game is composed of 3 Java classes :

Sokoban :
    This is where the 'main' function running the game is located, along with some 
    methods to run everything.

    - checkPlace -> This static method checks the position of the Player and the 
      boxes, and changes the display accordingly.

    - getType -> It converts coordinates on our map to a type of cell. It is mainly 
    used to create the display of a new map.

    - getScore -> This function returns the number of boxes on an activation cell 
     (i.e. the player's score)

    - loadMap -> This class method iterates on each part of the map and uses the setCell
     method. It also creates and returns the array containing the boxes, as well as 
     locates correctly the player before any moovements are performed. The goal of this 
     method was to be called for each new map the user could play on.

Item :
    The 'Item' class is the class of the Player object, as well as the parent class to the 
    Box class. An instance of the class is defined by it's coordinates as well as the 
    coordinates where it was before the last moovment.
    
    - moove -> This is the only method of the class. It takes as an input 'getEvent', and
     changes the (past and present) coordinates of the Player. In order to do that it 
     checks for walls and boxes in the direction wanted, and "communicates" with the potential 
     box facing the player.

Box :
    This class inherits the 4 coordinates from Item, and has an aditional boolean for whether
    the box is on an activation cell or not.

    - moove -> The Box class also has a 'moove' method, but with simpler interactions with
     other boxes. Unfortunatly it doesn't benefit from its parent method.

     - canMoove -> This method first uses 'moove' and then compares its position with the 
      coordinates of the box one movement ago. It then returns whether the box has moved or 
      not. It also checks for an activation plate under the box (and changes the attribute
      accordingly).


The file 'sokoban-gui.jar'contains the SokobanGUI class (used in Sokoban.java). It is in charge of 
displaying the game. The functions will be detailled in a future update of the README file (as its
functioning was detailed in the subject).
