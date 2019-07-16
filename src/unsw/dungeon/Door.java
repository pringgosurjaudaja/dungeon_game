package unsw.dungeon;


public interface Door {

    public void openDoorUsingKey();
    
    public void changeState(Entity d);	// change state from locked to unlocked
    
}
