import java.util.Objects;

import be.uliege.boigelot.oop.sokoban.gui.SokobanGUI;

public class Box extends Item{
	
	boolean onPlate;
	
	Box(int x, int y,int oldX, int oldY, boolean onPlate){
		super(x,y, oldX, oldY);
		this.onPlate=onPlate;/*This boolean stores whether the box is on an activation cell or not*/
		
	}
	
	public boolean canMoove(int command, String[][] map, Box[] boxList) {
		
		this.moove(command, map, boxList);/*We try to make the box move*/
		if (this.x==this.oldX & this.y==this.oldY) {
			return false;
		}
		else {
			if (Objects.equals(map[this.x][this.y],"1")) {/*If the box has moved, we check for an activation cell under it*/
				this.onPlate=true;
				
			}
			return true;
			
		}
		
	}
	
	public void moove(int command, String[][] map, Box[] boxList) {
		this.oldX=x;
		this.oldY=y;
		if (!this.onPlate) {	/*If we aren't yet on an activation cell*/
		if (command == SokobanGUI.UP) {
			if (!Objects.equals((map[this.x][this.y-1]),"W")){	/*We check for walls*/
				for (int i=0;i<boxList.length;i++) {	/*And then if a box is standing there*/
					if (boxList[i].x==this.x & boxList[i].y==this.y-1) {
						return;
					}
				}
				y--;
			}
		}
		else if (command == SokobanGUI.DOWN) {
			if (!Objects.equals((map[this.x][this.y+1]),"W")){
				for (int i=0;i<boxList.length;i++) {
					if (boxList[i].x==this.x & boxList[i].y==this.y+1) {
						return;
					}
				}
				y++;
			}
		}
		else if (command == SokobanGUI.LEFT) {
			if (!Objects.equals((map[this.x-1][this.y]),"W")){
				for (int i=0;i<boxList.length;i++) {
					if (boxList[i].x==this.x-1 & boxList[i].y==this.y) {
						return;
					}
				}
				x--;
			}
		}
		else if (command == SokobanGUI.RIGHT) {
			if (!Objects.equals((map[this.x+1][this.y]),"W")){
				for (int i=0;i<boxList.length;i++) {
					if (boxList[i].x==this.x+1 & boxList[i].y==this.y) {
						return;
					}
				}
				x++;
			}
		}
		
	}
	}
	
	
}