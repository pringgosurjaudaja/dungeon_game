package unsw.dungeon;

import java.io.File;
import java.util.List;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * 
 * class Bomb
 *
 */
public class Boulder extends Entity {
	

	/**
	 * Constructor Boulder
	 * @param x Horizontal position of the boulder
	 * @param y Vertical position of the boulder
	 */
    public Boulder(int x, int y) {
        super(x, y);
    }

    /**
     * Check if there is an entity other than switch in (paramX, paramY). If the entity is a switch, calls boulderSwitch() method from class Switch.
     * @param paramX This is the horizontal position
     * @param paramY This is the vertical position
     * @param listEntities This is the list of all entities in the dungeon
     * @return true if there is an entity other than switch in (paramX, paramY).
     */
    public boolean checkBoulder(int paramX , int paramY , List<Entity> listEntities){
    	for(Entity e : listEntities){
/*    		System.out.println("X: "+ this.getX());
    		System.out.println("Y: "+ this.getY());
    		System.out.println("Xi: "+ e.getX());
    		System.out.println("Yi: "+ e.getY());
    		System.out.println(e instanceof Switch);*/
    		if ((this.getX() + paramX == e.getX() && this.getY() + paramY == e.getY() && !(e instanceof Switch))){
    			return true;	// returns true if there IS an entity other than switch
    		}
    		else if((this.getX() + paramX == e.getX() && this.getY() + paramY == e.getY() && (e instanceof Switch))) {
    			((Switch) e).boulderSwitch();
    		}
    		else if((this.getX() == e.getX() && this.getY() == e.getY() && (e instanceof Switch)) ) {
    			System.out.println("SWITCH TRUE");
    		}
    	}
    	return false;
    }
    
    /**
     * Moves the boulder to (x,y).
     * @param x Horizontal distance to move
     * @param y Vertical distance to move
     */
    public void moveBoulder(int x, int y) {
    	this.y().set(getY()+y);
    	this.x().set(getX()+x);
    }
    
    /**
     * This method is when player interacts with Boulder.
     * If there is another entity other than switch at the other side of the boulder, boulder can't be moved.
     * If there is a switch or nothing on the other side of the boulder, player can move the boulder.
     * @param p This is the player.
     * @return null if nothing happens.
     * @return this (Boulder) entity if it is moved.
     */
	@Override
	public Entity interact(Player p) {
		if(p.getX() == this.getX()) {
			if(this.getY() == p.getY()-1) {
				if(checkBoulder(0,-1,p.getDungeon().getEntities())) {	// if there is an entity other than switch
					return null;	// can't move
				} else {
					moveBoulder(0,-1);
					String musicFile = "./sounds/Push.wav";
					Media sound = new Media(new File(musicFile).toURI().toString());
					MediaPlayer mediaPlayer = new MediaPlayer(sound);
					mediaPlayer.play();
					return this;
				}
			}
			if(this.getY() == p.getY()+1) {
				if(checkBoulder(0,1,p.getDungeon().getEntities())) {
					return null;
				} else {
					moveBoulder(0,1);
					String musicFile = "./sounds/Push.wav";
					Media sound = new Media(new File(musicFile).toURI().toString());
					MediaPlayer mediaPlayer = new MediaPlayer(sound);
					mediaPlayer.play();
					return this;
				}
			}
		} else if(p.getY() == this.getY()) {
			System.out.println("INSIDE Y");
			if(this.getX() == p.getX()-1) {
				if(checkBoulder(-1,0,p.getDungeon().getEntities())) {
					return null;
				} else {
					moveBoulder(-1,0);
					String musicFile = "./sounds/Push.wav";
					Media sound = new Media(new File(musicFile).toURI().toString());
					MediaPlayer mediaPlayer = new MediaPlayer(sound);
					mediaPlayer.play();
					return this;
				}
			}
			if(this.getX() == p.getX()+1) {
				if(checkBoulder(1,0,p.getDungeon().getEntities())) {
					return null;
				} else {
					moveBoulder(1,0);
					String musicFile = "./sounds/Push.wav";
					Media sound = new Media(new File(musicFile).toURI().toString());
					MediaPlayer mediaPlayer = new MediaPlayer(sound);
					mediaPlayer.play();
					return this;
				}
			}
		}
		
		return null;
	}
    

}
