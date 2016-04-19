package toc;

import java.util.Random;
import java.util.Scanner;
import java.io.IOException;

public class Commons {
	public static final int NUM_ITERATIONS = 20;
	public static final long SEED = 123;
	
	
	public static void main(String[] args) throws IOException{
		Random rand = new Random(SEED);
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter a population size: ");
		int populationSize = in.nextInt();
		
		// Creating the population
		Agent[] agents = new Agent[populationSize];
		for (int i = 0; i < populationSize; i++){
			agents[i] = new Agent(rand, i+1);
		}
		
		// Running through each Iteration
		for (int i = 0; i < NUM_ITERATIONS; i++){
			System.out.println("Iteration: " + (i + 1));
			partA(agents);
			partB(agents);
			printData(agents);
		}
	}
	
	public static void partA(Agent[] agents){
		for (Agent agent: agents){
			if(agent.getPersonality() == true){
				// if agent is selfish add 5 points to happiness
				agent.adjustHappiness(5.0);
			} else {
				// if agent is altruistic add -2 points to happiness
				agent.adjustHappiness(-2.0);
			}
		}
	}
	
	public static void partB(Agent[] agents){
		int govScore;
		int numSelfish = 0;
		int numAltruistic = 0;
		
		// get the number of selfish and altruistic agents
		for (Agent agent: agents){
			if (agent.getDecision() == true){
				numSelfish++;
			} else {
				numAltruistic++;
			}
		}
		
		if (numSelfish > numAltruistic){
			// government score == NEGATIVE number equal to the net number of selfish agents
			govScore = 0 - (numSelfish - numAltruistic);
		} else if (numAltruistic > numSelfish){
			// government score == POSITIVE number equal to the net number of altruistic agents
			govScore = (numAltruistic - numSelfish);
		} else {
			// government score == 0
			govScore = 0;
		}
		
		System.out.println("Government Score: " + govScore);
		
		// add the government score to each agents happiness and decide punishment
		for (Agent agent: agents){
			agent.adjustHappiness((double)govScore);
			// determine punishment
			if (agent.getDecision() == true){
				int punishment = punish(govScore);
				agent.adjustHappiness((double)punishment);
			}
		}
	}
	
	public static int punish(int govScore){
		double d = Math.random();
		
		if (govScore > 0){
			if (d <= 0.1){
				return -50;
			}
		}
		
		if (govScore < 0){
			if (d <= 0.5){
				return -75;
			}
		}
		
		return 0;
		
	}
	
	public static void printData(Agent[] agents){
		for (Agent agent: agents){
			System.out.println("\tAgent #" + agent.getIdentity());
			if (agent.getDecision() == true){
				System.out.println("\t\tPersonality: Selfish");
			} else {
				System.out.println("\t\tPersonality: Altruistic");
			}
			System.out.println("\t\tHappiness level: " + agent.getHappiness());
		}
	}
	
}
