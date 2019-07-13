package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

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
    	int removedEntity;
    	
        switch (event.getCode()) {
        case UP:
            removedEntity = player.moveUp();
            System.out.println("removed : " + removedEntity);
            if(removedEntity != -1) squares.getChildren().remove(initialEntities.get(removedEntity));
            break;
        case DOWN:
            removedEntity = player.moveDown();
            System.out.println("removed : " + removedEntity);
            if(removedEntity != -1) squares.getChildren().remove(initialEntities.get(removedEntity));
            break;
        case LEFT:
        	removedEntity = player.moveLeft();
        	System.out.println("removed : " + removedEntity);
            if(removedEntity != -1) squares.getChildren().remove(initialEntities.get(removedEntity));
            break;
        case RIGHT:
        	removedEntity = player.moveRight();
        	System.out.println("removed : " + removedEntity);
            if(removedEntity != -1) squares.getChildren().remove(initialEntities.get(removedEntity));
            break;
        /*case ENTER:
        	removedEntity = player.killEnemy();
        	System.out.println("enemy to kill : " + removedEntity);
            if(removedEntity != -1) squares.getChildren().remove(initialEntities.get(removedEntity));
        	break;*/
        default:
            break;
        }

    }

}

