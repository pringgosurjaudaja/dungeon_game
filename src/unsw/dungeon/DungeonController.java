package unsw.dungeon;

import java.util.ArrayList;

import java.util.List;

import org.json.JSONObject;
import org.json.JSONArray;


import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;

    @FXML
    private Pane inventory;
    private List<ImageView> initialEntities;

    private Player player;

    private Stage mainStage;
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
            invincibleState(player.getInvincibility());
            bombState();
            removeItem();
            loadDungeon();
            //if(w!= null) w.getImage().setImage(null);
            break;
        case DOWN:
        	player.moveDown();
        	invincibleState(player.getInvincibility());
        	bombState();
        	removeItem();
        	loadDungeon();
            //if(x!= null) x.getImage().setImage(null);
            break;
        case LEFT:
        	player.moveLeft();
        	invincibleState(player.getInvincibility());
        	bombState();
        	removeItem();
        	loadDungeon();
           // if(y!= null) y.getImage().setImage(null);
            break;
        case RIGHT:
        	player.moveRight();
        	invincibleState(player.getInvincibility());
        	bombState();
        	removeItem();
        	loadDungeon();
            //if(z!= null) z.getImage().setImage(null);
            break;
        case SPACE:		// used to drop carry_ons
        	//player.dropEntity(squares);
        	Entity b = player.dropEntity();
        	if(b!= null) dropItem(b);
            break;
        case ENTER:		// used to kill enemy with sword
        	if(player.getCarryOns() instanceof Sword) {
        		player.killEnemy();
        		removeItem();
        		//if(a != null) {
        		//	a.getImage().setImage(null);
        		//}
        	}
        /*	removedEntity = player.killEnemy();
        	System.out.println("enemy to kill : " + removedEntity);
            if(removedEntity != -1) squares.getChildren().remove(initialEntities.get(removedEntity));*/
        	break;
        default:
            break;
        }

        // checking goals
        finish = dungeon.checkGoal(dungeon.getDungeonGoal());
        if(finish == true) {
        	System.out.println("The end, all goals have been reached.");
        	gameOver();
        }else {
        	// goals haven't been reached
        }
    }
    
    public void removeItem() {
    	for(Entity e: dungeon.getRemovedEntity()) {
    		if(e != null)
    		e.getImage().setImage(null);
    	}
    }
    public void loadDungeon() {
    	for(Entity e: dungeon.getEntities()) {
    		if(e instanceof LockedDoor) {
    			if(((LockedDoor) e).isOpen()) {
    				System.out.println("RTSF");
    				e.getImage().setImage(new Image("/open_door.png"));
    			}
    		}
    	}
    }
    public void dropItem(Entity e) {
    	if(e instanceof Key) {
    		e.setImage(new ImageView("/key.png"));
    		squares.add(e.getImage(),player.getX(),player.getY());
    	}
    	else if(e instanceof Sword) {
    		e.setImage(new ImageView("/greatsword_1_new.png"));
    		squares.add(e.getImage(),player.getX(),player.getY());
    	}
    	else if(e instanceof Bomb) {
    		e.setImage(new ImageView("/bomb_unlit.png"));
    		squares.add(e.getImage(),player.getX(),player.getY());
    	}
    }
    
    public void bombState() {
    	for(Entity e: dungeon.getEntities()) {
    		if(e instanceof Bomb && ((Bomb) e).isLit()) {
    			if(((Bomb) e).getState() == ((Bomb) e).getLitBomb1()) {
    				e.getImage().setImage(new Image("/bomb_lit_1.png"));
    			}
    			else if(((Bomb) e).getState() == ((Bomb) e).getLitBomb2()) {
    				System.out.println(e);
    				e.getImage().setImage(new Image("/bomb_lit_2.png"));
    			}
    			else if(((Bomb) e).getState() == ((Bomb) e).getLitBomb3()) {
    				e.getImage().setImage(new Image("/bomb_lit_3.png"));
    			}
    			else if(((Bomb) e).getState() == ((Bomb) e).getExplodingBomb()) {
    				System.out.println("WFGWDEFEGF");
    				e.getImage().setImage(new Image("/bomb_lit_4.png"));
    			}
    			else if(((Bomb) e).getState() == ((Bomb) e).getPostExplosionBomb()) {
    				e.getImage().setImage(null);
    			}
    		}
    	}
    }
    
    public void invincibleState(Invincibility i) {
    	//System.out.println("PLAyeR INV: "+i);
    	if(i != null) {
	    	if(i.getCountdown() == 5) {
	    		player.getImage().setImage(new Image("/invincibility1.png"));
	    	}
	    	else if(i.getCountdown() == 4) {
	    		player.getImage().setImage(new Image("/invincibility2.png"));
	    	}	
	    	else if(i.getCountdown() == 3) {
	    		player.getImage().setImage(new Image("/invincibility3.png"));
	    	}
	    	else if(i.getCountdown() == 2) {
	    		player.getImage().setImage(new Image("/invincibility4.png"));
	    	}
	    	else if(i.getCountdown() == 1) {
	    		player.getImage().setImage(new Image("/invincibility5.png"));
	    	}
	    	else if(i.getCountdown() == 0) {
	    		player.getImage().setImage(new Image("human_new.png"));
	    	}
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

    public boolean checkPuzzle() {
    	if(dungeon.checkTreasureGoal()) return true;
    	return false;
    }

	public void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}
	public void gameOver() {
    	VBox screen = new VBox(mainStage.getHeight());
    	Image gameover = new Image("/gameover.jpg");
    	ImageView iv = new ImageView(gameover);
    	iv.setFitHeight(mainStage.getHeight());
    	iv.setFitWidth(mainStage.getWidth());
    	screen.getChildren().add(iv);
    	Scene overScene = new Scene(screen,mainStage.getWidth(),mainStage.getHeight());
    	mainStage.setScene(overScene);
    }
}
