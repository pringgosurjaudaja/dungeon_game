package unsw.dungeon;

public class Bomb extends Entity {

	BombState unlitBomb;
	BombState litBomb1;
	BombState litBomb2;
	BombState litBomb3;
	BombState explodingBomb;
	BombState state = unlitBomb;
	BombState postExplosionBomb;
	private int countdown;
	public Bomb(int x, int y) {
		super(x, y);
		unlitBomb = new UnlitBomb(this);
		litBomb1 = new LitBomb1(this);
		litBomb2 = new LitBomb2(this);
		litBomb3 = new LitBomb3(this);
		explodingBomb = new ExplodingBomb(this);
		postExplosionBomb = new PostExplosionBomb(this);
		setCountdown(4);
		this.state = unlitBomb;
	}
	public int getCountdown() {
		return countdown;
	}
	public void setCountdown(int countdown) {
		this.countdown = countdown;
	}
	public void countDownBomb() {
		state.countdown();
		state.explode();
		countdown--;
	}
	public BombState getUnlitBomb() {
		return unlitBomb;
	}
	public void setUnlitBomb(BombState unlitBomb) {
		this.unlitBomb = unlitBomb;
	}
	public BombState getLitBomb1() {
		return litBomb1;
	}
	public void setLitBomb1(BombState litBomb1) {
		this.litBomb1 = litBomb1;
	}
	public BombState getLitBomb2() {
		return litBomb2;
	}
	public void setLitBomb2(BombState litBomb2) {
		this.litBomb2 = litBomb2;
	}
	public BombState getLitBomb3() {
		return litBomb3;
	}
	public void setLitBomb3(BombState litBomb3) {
		this.litBomb3 = litBomb3;
	}
	public BombState getExplodingBomb() {
		return explodingBomb;
	}
	public void setExplodingBomb(BombState explodingBomb) {
		this.explodingBomb = explodingBomb;
	}
	public BombState getState() {
		return state;
	}
	public void setState(BombState state) {
		this.state = state;
	}
	public boolean isLit() {
		if(state == unlitBomb) {
			return false;
		}
		return true;
	}
	public boolean exploded() {
		if(state == explodingBomb) {
			return true;
		}
		return false;
	}
	public boolean postExplode() {
		if(state == postExplosionBomb) return true;
		return false;
	}
	public BombState getPostExplosionBomb() {
		return postExplosionBomb;
	}
	public void setPostExplosionBomb(BombState postExplosionBomb) {
		this.postExplosionBomb = postExplosionBomb;
	}
	@Override
	public Entity interact(Player p) {
		if(p.getCarryOns()== null) {
			p.setCarryOns(this);
			p.getDungeon().removeEntity(this);
			return this;
		}
		else {
			System.out.println("Couldn't pick up bomb, already carrying something");
			return null;
		}
	}
	@Override
	public Entity drop() {
		countDownBomb();
		return this;
	}
	@Override
	public boolean remove() {
		return true;
	}
	
}
