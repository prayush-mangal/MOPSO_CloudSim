package net.sourceforge.jswarm_pso;

import utils.GenerateMatrices;
import utils.Constants;
/**
 * Base Fitness Function
 */
public abstract class FitnessFunction {

	
	boolean maximize;


	/** Default constructor */
	public FitnessFunction() {
		this.maximize = false; 
	}

	
	public FitnessFunction(boolean maximize) {
		this.maximize = maximize;
	}

	//-------------------------------------------------------------------------
	// Methods
	//-------------------------------------------------------------------------
	
	/**
	 * Evaluates a particles at a given position
	 
	 * 
	 * @param position : Particle's position
	 * @return Fitness function for a particle
	 */
	public abstract double[] evaluate(double position[]);
	
	
	
	
	
	/**
	 * Evaluates a particles fitness
	 * @param particle : Particle to evaluate
	 * @return Fitness function for a particle
	 * fitness function here depends on task execution cost(tec),expected completed time(ect)and vm allocation
	 */
	public double[] evaluate(Particle particle) {
		double position[] = particle.getPosition();
		double fit[]=evaluate(position);
		particle.setFitness(fit,maximize);
		return fit;
	}

	
	public boolean isMaximize() {
		return maximize;
	}

	public void setMaximize(boolean maximize) {
		this.maximize = maximize;
	}
}