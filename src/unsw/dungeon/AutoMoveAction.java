package unsw.dungeon;

import java.awt.Point;
import java.util.ArrayList;

/**
 * 
 * This is an interface used for EnemyChase and EnemyRun
 *
 */
public interface AutoMoveAction {
	public Point autoMove(ArrayList<Point> listCoordinates , Point source , Point dest , int width , int height);

}
