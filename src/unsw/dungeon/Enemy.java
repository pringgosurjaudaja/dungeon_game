/*package unsw.dungeon;

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

    	for (Point p : listCoordinates) {
    		if(p.y == getY() - 1 && p.x == getX()){
    			moveUp();
    		}
    		if(p.y == getY() + 1 && p.x == getX()){
    			moveDown();
    		}
    		if(p.x == getX() - 1 && p.y == getY()){
    			moveLeft();
    		}
    		if(p.x == getX() + 1 && p.y == getY()){
    			moveRight();
    		}

    	}

	}

}
*/

/*
package unsw.dungeon;

import java.awt.Point;
import java.util.ArrayList;

public class Enemy extends Entity implements AutoMoveAction{

	public Enemy(int x, int y) {
		super(x, y);
	}

	private void move(int x , int y) {
		y().set(getY() + y);
		x().set(getX() + x);

	}


	@Override
	public void autoMove(ArrayList<Point> listCoordinates) {
		// TODO Auto-generated method stub

		ArrayList<Point> points = new ArrayList<Point>();

		points.add(new Point(0, 1));
		points.add(new Point(0, -1));
		points.add(new Point(1, 0));
		points.add(new Point(-1, 0));

		while(true){

			int contain = 0;
			int random = (int) (Math.random() * 4);
			//check if there are another entity;
			for(Point p : listCoordinates) {
				if(p.x == points.get(random).x && p.y == points.get(random).y) contain = 1;
			}
//			if(!listCoordinates.contains(points.get(random))){
			if(contain == 0) {
				move(points.get(random).x, points.get(random).y);
				break;
			}
		}
	}

}
*/

package unsw.dungeon;

public class Enemy extends Entity {

	public Enemy(int x, int y) {
		super(x, y);
	}
}
