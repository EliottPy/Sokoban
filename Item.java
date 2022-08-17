import java.util.Objects;

import be.uliege.boigelot.oop.sokoban.gui.*;

public class Item {
	
	 int x;
	 int y;
	 int oldX;
	 int oldY;

	Item(int x, int y, int oldX, int oldY){
		this.x=x;
		this.y=y;
		this.oldX=oldX;
		this.oldY=oldY;
	}/*We use an oldX and oldY attribute to modify the previous cell*/
	
	public void moove(int command, String[][] map, Box[] boxList) {
		this.oldX=x;
		this.oldY=y;
		if (command == SokobanGUI.UP) {
			if (!Objects.equals((map[this.x][this.y-1]),"W")){ /* Checks for a wall in the direction*/
				
				for (int i=0;i<boxList.length;i++) {
					if (boxList[i].x== this.x & boxList[i].y==this.y-1) {/*Checks for boxes in the way*/
						if (!boxList[i].canMoove(command, map, boxList)) {/*If the box in front can't move (because of a wall, an other box etc)*/
							return;/*If the box can't move, the player just stays in place*/
						}
					}
					
				}
				this.y--;
				return;
				
			}
		}
		else if (command == SokobanGUI.DOWN) {
			if (!Objects.equals((map[this.x][this.y+1]),"W")){ 
				for (int i=0;i<boxList.length;i++) {
					if (boxList[i].x== this.x & boxList[i].y==this.y+1) {
						if (!boxList[i].canMoove(command, map, boxList)) {
							return;
						}
					}
					

				}
				this.y++;
				return;
				
			}
		}
		else if (command == SokobanGUI.LEFT) {
			if (!Objects.equals((map[this.x-1][this.y]),"W")){ 
				for (int i=0;i<boxList.length;i++) {
					if (boxList[i].x== this.x-1 & boxList[i].y==this.y) {
						if (!boxList[i].canMoove(command, map, boxList)) {
							return;
						}
					}
					
				}
				this.x--;
				return;
				
			}
		}
		else if (command == SokobanGUI.RIGHT) {
			if (!Objects.equals((map[this.x+1][this.y]),"W")){ 
				
				for (int i=0;i<boxList.length;i++) {
					if (boxList[i].x== this.x+1 & boxList[i].y==this.y) {
						if (!boxList[i].canMoove(command, map, boxList)) {
							return;
						}
						
					}
					
					
				}
				
					this.x++;
					return;
				}
			}
		}

	}


