package unsw.dungeon;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EnemyRunAway implements AutoMoveAction {

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