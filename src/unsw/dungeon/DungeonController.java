package unsw.dungeon;

import java.awt.Point;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import java.util.List;

import org.json.JSONObject;
import org.json.JSONArray;


import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;

    @FXML
    private GridPane inventory;
    @FXML
    private TextField treasureCounter;
    @FXML
    private TextField inventoryField;
    @FXML
    private TextField goalField;
    @FXML
    private TextField goaltextfield;

    private List<ImageView> initialEntities;

    private Player player;

    private Stage mainStage;
    private Dungeon dungeon;

    /**
     * Constructor DungeonController
     * @param dungeon This is the dungeon
     * @param initialEntities This is the ImageView list of the initial entities of the dungeon. 
     */
    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);

        ArrayList<BotAutoMove> botList = new ArrayList<BotAutoMove>();

        for (int i = 0 ; i < dungeon.getEntities().size() ; i++) {
    		Entity w = dungeon.getEntities().get(i);

    		if(w instanceof Enemy) {
    			BotAutoMove bot = new BotAutoMove(dungeon, player , w , 1000);
    			botList.add(bot);
    		} else if(w instanceof Hound) {
    			BotAutoMove bot = new BotAutoMove(dungeon, player , w , 800);
    			botList.add(bot);
    		}
        }
        for(BotAutoMove b : botList) {
        	b.start();
        }
    }

    /**
     * To initialize all the background image to be of /dirt_0_new.png
     */
    @FXML
    public void initialize() {
        Image ground = new Image("/dirt_0_new.png");
        //inventory.add(new ImageView(treasure), 1, 0);
        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight() + 3; y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);
        //goalField.setText(dungeon.getGoals());
        
        

        this.inventory.setLayoutY((dungeon.getHeight())*32);
        this.goaltextfield.setLayoutY((dungeon.getHeight())*32 + 40);
        this.goalField.setLayoutY((dungeon.getHeight())*32 + 40);
        goalField.setText(dungeon.getGoals());
    }

    /**
     * 6 cases: UP, DOWN, LEFT, RIGHT, SPACE, ENTER
     * If player presses UP, player will move up.
     * If player presses DOWN, player will move down.
     * If player presses LEFT, player will move left.
     * If player presses RIGHT, player will move right.
     * If player presses SPACE, player will drop their carry ons (keys, swords, bomb).
     * This method also checks if the player is dead and if the goals have been met.
     * If player presses ENTER, player will kill using sword.
     * @param event This is what is pressed by player from their keyboard
     */
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
        	if(b != null) dropItem(b);
            break;
        case ENTER:		// used to kill enemy with sword
        	if(player.getCarryOns() instanceof Sword) {
        		player.killEnemy();
        		removeItem();
        		//if(a != null) {
        		//	a.getImage().setImage(null);
        		//}
        	}
        	break;
        default:
            break;
        }
        updateStatus();
        System.out.println("PLAYER : "+ player.isDead());
        if(player.isDead()) {
        	gameOver();
        }
        // checking goals
        finish = dungeon.checkGoal(dungeon.getDungeonGoal());
        if(finish == true) {
        	System.out.println("The end, all goals have been reached.");
        	gameFinished();
        } else {
        	// goals haven't been reached
        }
    }

    /**
     * Remove items in the dungeon.getRemovedEntity()
     * If the remove item is of instance Treasure, it's going to start Thread for shine.png, which is just an
     * animation to show treasure has been collected
     */
    public void removeItem() {
    	for(Entity e : dungeon.getRemovedEntity()) {
    		if(e != null) {
    			if(e instanceof Treasure) {
    				if (e.getImage().getImage() != null) {
    					Thread th = new Thread((Treasure)(e));
    					th.start();
    					String tc = Integer.toString(player.getTreasure());
    					treasureCounter.setText(tc);
    				}
    			} else {
    				e.getImage().setImage(null);
    			}
    		}
    	}
    }

    /**
     * This updates the status of the status bar.
     */
    public void updateStatus() {
    	if(player.getCarryOns() != null) {
    		inventoryField.setText(player.getCarryOns().toString());
    	} else {
    		inventoryField.setText("None");
    	}
    }
    
    /**
     * This change locked door image to an opened one if it's status isOpen is true.
     */
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
    
    /**
     * This method drops entity in the dungeon in the current location (x,y) of the player.
     * This method only drops entity of instance key, sword and bomb.
     * @param e This is the entity to be dropped.
     */
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

    public void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}

    /**
     * This assigns image of the bomb according to their state.
     */
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
    				e.getImage().setImage(new Image("/bomb_lit_4.png"));
    			}
    			else if(((Bomb) e).getState() == ((Bomb) e).getPostExplosionBomb()) {
    				e.getImage().setImage(null);
    				dungeon.removeEntity(e);
    			}
    		}
    	}
    }

	/**
	 * This assigns image of the player when they are carrying invincibility potion according to thei countdown.
	 * @param i
	 */
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

    /**
     * Displays gameover.jpg image when the game ends.
     */
	public void gameOver() {
    	VBox screen = new VBox(mainStage.getHeight());
    	Image gameover = new Image("/gameover.jpg");
    	ImageView iv = new ImageView(gameover);
    	iv.setFitHeight(mainStage.getHeight());
    	iv.setFitWidth(mainStage.getWidth());
    	screen.getChildren().add(iv);
    	Scene overScene = new Scene(screen,mainStage.getWidth(),mainStage.getHeight());
    	mainStage.setScene(overScene);
    	
    	String musicFile = "./sounds/Game_Over.wav";
		Media sound = new Media(new File(musicFile).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
    }
	
	/**
	 * Displays gamefinish.jpg image when the game ends.
	 */
	public void gameFinished() {
		VBox screen = new VBox(mainStage.getHeight());
    	Image gamefinish = new Image("/gamefinish.jpg");
    	ImageView iv = new ImageView(gamefinish);
    	iv.setFitHeight(mainStage.getHeight());
    	iv.setFitWidth(mainStage.getWidth());
    	screen.getChildren().add(iv);
    	Scene overScene = new Scene(screen,mainStage.getWidth(),mainStage.getHeight());
    	mainStage.setScene(overScene);
    	
    	String musicFile = "./sounds/Win.wav";
		Media sound = new Media(new File(musicFile).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
	}
}
