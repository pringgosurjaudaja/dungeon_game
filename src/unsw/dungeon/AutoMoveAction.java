package unsw.dungeon;

import java.awt.Point;
import java.util.ArrayList;

public interface AutoMoveAction {
	public Point autoMove(ArrayList<Point> listCoordinates , Point source , Point dest , int width , int height);

}
