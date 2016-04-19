package toc;

import java.util.Random;

public class Agent {
	private int personality;
	private int identity;
	private double happiness;
	private boolean decision;
	
	// Constructor
	public Agent(Random rand, int id){
		personality = (rand.nextInt() % 3);
		identity = id;
		happiness = 0;
	}
	
	public int getIdentity(){
		return identity;
	}
	
	public double getHappiness(){
		return happiness;
	}
	
	public boolean getDecision(){
		return decision;
	}
	
	public boolean getPersonality(){
		int randPersonality;
		Random rand = new Random();
		
		if (personality == 0){
			// 0 == always selfish
			decision = true;
		} else if (personality == 1){
			// 1 == always altruistic
			decision = false;
		} else {
			// 2 == can be either selfish or altruistic
			randPersonality = (rand.nextInt() % 2);
			if (randPersonality == 0){
				// selfish
				decision = true;
			} else {
				//altruistic
				decision = false;
			}
		}
		return decision;
	}
	
	public void adjustHappiness(double points){
		happiness += points;
	}
	
}
