package unsw.dungeon;

import java.util.ArrayList;

import java.util.List;

import org.json.JSONObject;
import org.json.JSONArray;


import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;

    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);

        BotAutoMove bot = new BotAutoMove(dungeon, player);
        bot.start();
    }

    @FXML
    public void initialize() {
        Image ground = new Image("/dirt_0_new.png");

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);

    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
    	Boolean finish = false;

        switch (event.getCode()) {
        case UP:
            player.moveUp();
            break;
        case DOWN:
        	player.moveDown();
            break;
        case LEFT:
        	player.moveLeft();
            break;
        case RIGHT:
        	player.moveRight();
            break;
        case SPACE:		// used to drop carry_ons
        	// NOT DONE
        	System.out.println("in1");
        	player.dropEntity(squares);
        	System.out.println("in8");

/*        	removedEntity = player.dropEntity();
        	System.out.println("removed : " + removedEntity);
        	if(removedEntity == -2) {
        		Image keyImage = new Image("key.png");
        	//	squares.getChildren().add(new ImageView(keyImage));
        		squares.add(new ImageView(keyImage), player.getX(), player.getY());
        	}
            if(removedEntity != -1) squares.getChildren().remove(initialEntities.get(removedEntity));
           */
            break;
        /*case ENTER:
        	removedEntity = player.killEnemy();
        	System.out.println("enemy to kill : " + removedEntity);
            if(removedEntity != -1) squares.getChildren().remove(initialEntities.get(removedEntity));
        	break;*/
        default:
            break;
        }

        // checking goals
        finish = checkGoals(dungeon, dungeon.getGoals());
        if(finish == true) {
        	System.out.println("The end, all goals have been reached.");
        }else {
        	// goals haven't been reached
        }
    }

    // check if all the goals have been met
    public boolean checkGoals(Dungeon dungeon, JSONArray subgoals) {
    	if (subgoals == null) return false;
    	Boolean finish = false;		// not all goals have been met.
/*    	String goal = json.getString("goal");
    	JSONArray subgoals = new JSONArray();
    	if(goal == "AND") {
    		subgoals = json.getJSONArray("subgoals");
    	} else {
    		subgoals.put(goal);
    	}*/

    	for (int i = 0 ; i < subgoals.length() ; i++) {
    		if(subgoals.get(i) == "boulders") { // check if all switches have boulders on them
    	    	for (Entity s : dungeon.getEntities()) {
    	        	if(s instanceof Switch) {
    	        		int temp = 0;
    	                for (Entity b : dungeon.getEntities()) {
    	                	if(b instanceof Boulder) {
    	                		if(b.getX() == s.getX() && b.getY() == s.getY()) {
    	                			temp = 1;	// there is boulder on this switch
    	                			break;
    	                		}
    	                	}
    	                }
    	                if(temp == 0) {	// no boulder on this switch
    	                	System.out.println("No boulder on this switch");
    	                	// TO DO
    	               		return false;
    	               	}
    	        	}
    	        }
    	        System.out.println("All switches have been pressed down by boulders.");
    	       	finish = true;
    	       	// TO DO
    	       	break;
    		} else if(subgoals.get(i) == "exit") {
    	       	// TO DO
    	       	break;
            } else if(subgoals.get(i) == "enemies") {
   	        	// TO DO
   	        	break;
   	        } else if(subgoals.get(i) == "treasure") {
   	        	// TO DO
   	        	break;
    	    }

    	}

        return finish; // TO DO

    }

}
