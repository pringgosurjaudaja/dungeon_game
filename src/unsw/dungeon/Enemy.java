package unsw.dungeon;

import java.awt.Point;
import java.util.ArrayList;

public class Enemy extends Entity implements AutoMoveAction{

	public Enemy(int x, int y) {
		super(x, y);
	}

	private void moveUp() {
		y().set(getY() - 1); // move up
	}

	private void moveDown() {
		y().set(getY() + 1); // move up
	}

	private void moveRight(){
		x().set(getX() + 1);
	}
	

	private void moveLeft(){
		x().set(getX() - 1);
	}

	@Override
	public void autoMove(ArrayList<Point> listCoordinates) {
		// TODO Auto-generated method stub
		
	}

}
