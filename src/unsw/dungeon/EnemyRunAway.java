package unsw.dungeon;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Class EnemyRunAway
 *
 */
public class EnemyRunAway implements AutoMoveAction {

	/**
	 * Calls BFS() method. This returns point for the enemy to run away from player. 
	 * This is used when player is carrying sword, bomb or invincibility which could kill the enemy.
	 * @param listCoordinates	List coordinates of entities in the dungeon.
	 * @param source Point of the source
	 * @param dest	 Point of the dest
	 * @param width  This is the width of the dungeon
	 * @param height This is the height of the dungeon
	 * @return source This is the point for the enemy to move.
	 */
	public Point autoMove(ArrayList<Point> listCoordinates , Point source , Point dest , int width , int height) {
		// TODO Auto-generated method stub
		//destination would be the opposite of where the player is at
		Point nextMove = BFS(source, dest, listCoordinates , width , height);
		
		ArrayList<Point> listMove = new ArrayList<Point>();
		
		
		if(source.x < dest.x){
			listMove.add(new Point(-1 , 0));
		}
		else {
			listMove.add(new Point(1, 0));
		}
		
		
		if(source.y < dest.y){
			listMove.add(new Point(0 , -1));
		}
		else {
			listMove.add(new Point(0, 1));
		}
		
		if(source.x < dest.x){
			listMove.add(new Point(1 , 0));
		}
		else {
			listMove.add(new Point(-1, 0));
		}
		
		
		if(source.y < dest.y){
			listMove.add(new Point(0, 1));
		}
		else {
			listMove.add(new Point(0, -1));
		}
		
		
		
		int x = (nextMove.x - source.x) * -1;
		int y = (nextMove.y - source.y) * -1;
		
		Point newNextMove = new Point(x , y);
		Point oldNextMove = new Point(x*-1 , y*-1);
		
		
	/*	listMove.remove(newNextMove);
		listMove.add(0, newNextMove);
		
		
		listMove.remove(oldNextMove);
		listMove.add(oldNextMove);
		*/
		
		
		//check if there are obstacles
		for(int i = 0 ; i < listMove.size() ; i++){
			Point p = new Point(source.x + listMove.get(i).x  , source.y + listMove.get(i).y);
			
			if (listCoordinates.contains(p))continue;
			
			
			return p;
		}
		
		return source;
	}
	
	/**
	 * This is the Breath First Search Method to move from source to dest.
	 * @param source Point of the source
	 * @param dest	 Point of the dest
	 * @param obstacles Point of entities in the dungeon
	 * @param width	 This is the width of the dungeon
	 * @param height This is the height of the dungeon
	 * @return previous This is the point for enemy to move.
	 */
	public Point BFS(Point source , Point dest , ArrayList<Point> obstacles , int width , int height){ 
		
		int arrX[] = {0, 0, 1, -1};
		int arrY[] = {-1,1, 0, 0};
		
		ArrayList<Point> visited = new ArrayList<Point>();
		ArrayList<Point> queue = new ArrayList<Point>(); 
		
		Map<Point , Point> parent = new HashMap<Point , Point>();
		
		queue.add(source); 
		
		while (!queue.isEmpty()) {
			Point current = queue.remove(0);
			
			visited.add(current);
			
			if (current.equals(dest))break;
			
			for(int i = 0 ;i < arrX.length ; i++){
				Point newPoint = new Point(current.x + arrX[i] , current.y + arrY[i]);
				
				if (current.x + arrX[i] < 0 && current.x >= width)
					continue;
				else if (current.y + arrY[i] < 0 && current.y + arrY[i] >= height)
					continue;
				else if (obstacles.contains(newPoint))
					continue;
				else if(visited.contains(newPoint))
					continue;
				else{
					//format : current , parent
					parent.put(newPoint , current);
					queue.add(newPoint);
					
					
				}
			}
			
		}
		
		//retracking path
		Point curr = parent.get(dest);
		Point previous = null;
		while(curr != null && !curr.equals(source)){
			previous = curr;
			curr = parent.get(curr);
		}
		
		if(previous == null)previous = dest;
		
		return previous;
	}


	

}