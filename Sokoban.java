import java.util.*;

import be.uliege.boigelot.oop.sokoban.gui.*;

public class Sokoban extends SokobanGUI{
	/* Get the type for each visual component of the game*/
	
	Sokoban(int x, int y) throws SokobanError{
		super(x, y);
	}
	
	public static void main(String[] arg) throws SokobanError {
		
		final String[][] mapSokoban0 = {{"0","0","0","0","0","0","0","0","0","0","0","0"},
										{"0","0","0","0","0","0","0","0","0","0","0","0"},
										{"0","0","W","W","W","W","W","W","W","0","0","0"},
										{"0","0","W","0","0","0","0","0","W","0","0","0"},
										{"0","0","W","0","1","0","0","0","W","0","0","0"},
										{"0","0","W","0","0","B","0","0","W","0","0","0"},
										{"0","0","W","0","0","0","P","0","W","0","0","0"},
										{"0","0","W","0","B","1","0","0","W","0","0","0"},
										{"0","0","W","W","W","W","W","W","W","0","0","0"},
										{"0","0","0","0","0","0","0","0","0","0","0","0"},
										{"0","0","0","0","0","0","0","0","0","0","0","0"},
										{"0","0","0","0","0","0","0","0","0","0","0","0"}};
		
		final String[][] mapSokoban1= {{"0","0","0","0","0","0","0","0","0","0","0","0"},
									   {"0","0","0","0","0","0","0","0","0","0","0","0"},
									   {"0","W","W","W","W","W","W","W","W","0","0","0"},
									   {"0","W","0","B","0","1","W","1","W","0","0","0"},
									   {"0","W","0","0","W","W","W","P","W","W","0","0"},
									   {"0","W","0","0","0","W","0","B","0","W","0","0"},
									   {"0","W","1","B","1","B","B","0","0","W","0","0"},
									   {"0","W","0","B","0","0","1","0","0","W","0","0"},
									   {"0","W","0","1","W","W","W","W","W","W","0","0"},
									   {"0","W","W","W","W","0","0","0","0","0","0","0"},
									   {"0","0","0","0","0","0","0","0","0","0","0","0"},
									   {"0","0","0","0","0","0","0","0","0","0","0","0"}};
		
		final String[][] mapSokoban2 = {{"0","0","0","0","0","0","0","0","0","0","0","0"},
										{"W","W","W","W","W","0","0","0","0","0","0","0"},
										{"W","T1","P","0","W","W","W","W","W","W","W","0"},
										{"W","0","B","0","0","0","0","0","B","0","W","0"},
										{"W","W","B","W","W","W","W","1","W","0","W","0"},
										{"W","0","0","0","0","0","1","1","1","0","W","0"},
										{"W","0","B","0","B","0","W","1","W","W","W","0"},
										{"W","W","W","W","W","0","0","0","0","W","0","0"},
										{"0","0","0","0","W","W","0","0","T2","W","0","0"},
										{"0","0","0","0","W","W","W","W","W","W","0","0"},
										{"0","0","0","0","0","0","0","0","0","0","0","0"},
										{"0","0","0","0","0","0","0","0","0","0","0","0"}};
		
		String[][] currentMap = mapSokoban0;
		
		int Xm = currentMap[0].length;
		int Ym = currentMap.length;
		
		/*Initialization of the Player and the window*/
		Item Player = new Item(0,0,0,0);
		Sokoban Window = new Sokoban(Xm, Ym);
		
		
		int wallType = Window.loadImage("wall.png");	
		int playerType = Window.loadImage("player.png");
		int boxType = Window.loadImage("box.png");
		int cellType = Window.loadImage("cell.png");
		int boxActivatedType = Window.loadImage("box_on_plate.png");
		
		/*By activation-cell we point to the cells where the boxes are supposed to go*/
		int activationType = Window.loadImage("activation-cell.png");
		
		/*We create our array of boxes, as well as create the window to be displayed*/
		Box[] boxList= Window.loadMap(currentMap, Player,wallType, playerType, boxType, cellType, activationType);
		
		int mapIndex=0;/*This will be used to switch between levels*/
		
		Window.show();
		
		
		while(mapIndex<3) {/*As long as we their are levels to be played*/
			Player.moove(Window.getEvent(), currentMap, boxList);
			checkPlace(Player, Window,currentMap, boxList,wallType, playerType, boxType, cellType, activationType, boxActivatedType);
			System.out.println(boxList[0].onPlate);
			System.out.println(boxList[1].onPlate);
			System.out.println("");
			Window.show();
		
		if (getScore(boxList)==boxList.length) {/*If the level is completed*/
			mapIndex++;/*The index changes, and we change the map displayed*/
			switch(mapIndex) {
			case 1:
				currentMap=mapSokoban1;
				break;
			case 2:
				currentMap=mapSokoban2;
				break;
			default:
				currentMap=mapSokoban0;
				
			}
			
			boxList=Window.loadMap(currentMap, Player, wallType, playerType, boxType, cellType, activationType);/*Loads the new map*/
			Window.show();
		}
		
		}
		Window.setCell(Player.x, Player.y, cellType);/*The player disappears to indicate the end of the game*/
		Window.show();
		
	}
	/*This function is in charge of the display of the different objects*/
	public static void checkPlace(Item Player,SokobanGUI Window, String[][] map, Box[] boxList, int wType, int pType, int bType, int cType,int aType, int boxActivatedType) throws SokobanError {
		for (int k=0;k<boxList.length;k++) {/*We make sure each box is correctly displayed*/
			if (boxList[k].oldX!=boxList[k].x | boxList[k].oldY!=boxList[k].y) {
				Window.setCell(boxList[k].x, boxList[k].y, bType);
				
				if(Objects.equals(map[boxList[k].oldX][boxList[k].oldY],"1")) {
					Window.setCell(boxList[k].oldX, boxList[k].oldY, aType);
				}
				else {

					Window.setCell(boxList[k].oldX, boxList[k].oldY, cType);
				}
				
				
			}
			if (boxList[k].onPlate) {
				Window.setCell(boxList[k].x, boxList[k].y, boxActivatedType);
			}
		}
		if (Player.oldX!= Player.x|Player.oldY!=Player.y) {/* if the player has moved*/
			if (Objects.equals(map[Player.oldX][Player.oldY],"1")){
				Window.setCell(Player.oldX, Player.oldY, aType);/* add a cell where he was, depending on the type of cell*/
			}
			else {
				Window.setCell(Player.oldX, Player.oldY, cType);  
			}
			
		}
		Window.setCell(Player.x, Player.y, pType); /* add the player image where he really is*/
		
	}
	
	/*This function converts 2 coordinates (x,y) into a type of cell*/
	public static int getType(int x, int y, String[][] map, int wType, int pType, int bType, int cType, int aType) {
		String a = map[x][y];
		if (Objects.equals(a,"W")) {
			return wType;
		}
		else if (Objects.equals(a,"P")) {
			return pType;
		}
		else if (Objects.equals(a,"B")) {
			return bType;
		}
		else if (Objects.equals(a,"1")) {
			return aType;
		}
		else { 
			return cType;
		}
	}
	/*This function takes the array of boxes as an argument, and gives back the amount of them already on an activation cell*/
	public static int getScore(Box[] boxList) {
		int score=0;
		for(int i=0;i<boxList.length;i++) {
			if (boxList[i].onPlate) {
				score++;
			}
		}
		return score;
	}
	
	public Box[] loadMap(String[][] map, Item player, int wallType, int playerType, int boxType, int cellType, int activationType) throws SokobanError {
		int j=0;/*This local variable will store the number of boxes in the map*/
		List<Integer> boxCoordinates = new LinkedList<Integer>();/*This list is used to store boxes'coordinates, to later create the array boxList*/
		int Xm = map[0].length;
		int Ym = map.length;
		for (int y=0;y<Ym;y++) {
			for (int x=0;x<Xm;x++) {
				int type = getType(x, y, map, wallType, playerType, boxType, cellType, activationType);
				this.setCell(x, y, type);
				if (type==playerType) {/*This is where the Player is truly initialized*/
					player.x=x;
					player.y=y;
					player.oldX=x;
					player.oldY=y;
				}
				if (type==boxType) {
					boxCoordinates.add(x);
					boxCoordinates.add(y);
					j++;
					
				}
			}
		}
		Box[] boxList = new Box[j];
		for(int i=0;i<j;i++) {
			int x= boxCoordinates.get(2*i);
			int y= boxCoordinates.get(2*i+1);
			boxList[i]= new Box(x,y,x,y,false);
		}
		return boxList;
	}

}