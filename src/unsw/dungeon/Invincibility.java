package unsw.dungeon;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * 
 * Class Invincibility
 *
 */
public class Invincibility extends Entity {
	
	private int countdown;
	Player temp;
	
	/**
	 * Constructor Invincibility
	 * Set initial countdown to 6
	 * @param x Horizontal position of the Invincibility
	 * @param y Vertical position of the Invincibility
	 */
    public Invincibility(int x, int y) {
        super(x, y);
        //state = new Invincibility1(this, temp);
        setCountdown(6);
        
    }
    
    /**
     * Reduce countdown by one.
     */
    public void countdownInvincibility() {
    	//state.countdown();
    	if(countdown == 0) {
    		temp.setCarryOns(null);
    	} else {
    		countdown--;
    	}
    }
	public int getCountdown() {
		return countdown;
	}
	public void setCountdown(int countdown) {
		this.countdown = countdown;
	}
	
	/**
	 * 
	 * This method is when player interacts with Invincibility.
	 * If the player is not carrying anything, he will then carry this (Invincibility) entity,
	 * otherwise nothing happens because a player can only carry one item at a time.
	 * @param p This is the player
	 * @return Entity It returns either null or this entity (Invincibility)
	 */
	@Override
	public Entity interact(Player p) {
		if(p.getCarryOns() == null) {
			p.setCarryOns(this);
			p.setInvincibility(this);
			p.getDungeon().removeEntity(this);
			String musicFile = "./sounds/PickUp.wav";
			Media sound = new Media(new File(musicFile).toURI().toString());
			MediaPlayer mediaPlayer = new MediaPlayer(sound);
			mediaPlayer.play();
			return this;
		} else {
			System.out.println("Can't pickup invincibility, already carrying something");
			return null;
		}

	}
	@Override
	public boolean remove() {
		return true;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Invincibility, Countdown:"+this.countdown;
	}
	
}
