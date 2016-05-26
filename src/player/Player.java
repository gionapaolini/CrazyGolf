package player;

import ObjectsGolf.Ball;
import ObjectsGolf.PutHole;

public abstract class Player {
	
	public Ball ball;
	public PutHole hole;
	protected byte strokes;
	//byte score; for levels
	
	public Player(Ball b, PutHole p ){
		ball = b;
		hole = p;
		strokes = 0;
		//score = 0;
	}
	
	public abstract void shot();
	
	public byte getStrokes(){
		return strokes;
	}

}
