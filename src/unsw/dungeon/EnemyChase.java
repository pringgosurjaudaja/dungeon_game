package unsw.dungeon;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EnemyChase implements AutoMoveAction {

/*	@Override
	public Point autoMove(ArrayList<Point> listCoordinates , Point source , Point dest , int width , int height) {
		// TODO Auto-generated method stub
		return BFS(source, dest, listCoordinates , width , height);
	}*/
	
	
	@Override
	public Point autoMove(ArrayList<Point> listCoordinates , Point source , Point dest , int width , int height) {
		// TODO Auto-generated method stub

		//check if player == obstacles when player drops item
		listCoordinates.remove(dest);
		
		return BFS(source, dest, listCoordinates , width , height);
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
		if(curr != source){
			while(curr != null && !curr.equals(source)){
				previous = curr;
				curr = parent.get(curr);
			}
		}else previous = dest;
		
		
		//if(previous == null)previous = dest;
		
		return previous;
	}
}
