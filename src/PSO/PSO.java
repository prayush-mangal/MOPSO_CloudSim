package PSO;

import net.sourceforge.jswarm_pso.Swarm;
import utils.Constants;

public class PSO {
    private static Swarm swarm;
    private static SchedulerParticle particles[];
    private static SchedulerFitnessFunction ff = new SchedulerFitnessFunction();

    public PSO() {
        initParticles();
    }


    public double[] run() {
        swarm = new Swarm(Constants.POPULATION_SIZE, new SchedulerParticle(), ff);

        swarm.setMinPosition(0);
        swarm.setMaxPosition(Constants.NO_OF_VMS - 1);
        swarm.setMaxMinVelocity(0.5);
        swarm.setParticles(particles);
        swarm.setParticleUpdate(new SchedulerParticleUpdate(new SchedulerParticle()));

        for (int i = 0; i < Constants.NO_OF_Iterations; i++) {
            swarm.evolve();
            if (i%10 == 0) {
                System.out.printf("Global best fitness at iteration (%d): %f\n", i, (swarm.getBestFitness())[0]);
                System.out.printf("Global best particle at iteration (%d): %d\n", i, (swarm.getBestParticleIndex())[0]);
                
            }
//            swarm.setInertia();
        }
        System.out.println( "\nBest makespan: " + ff.calcMakespan(swarm.getBestPosition()));
        System.out.println( "\nTotal Energy Consumption: " + ff.calcEnergyConsumption(swarm.getBestPosition()));
       

        
        SchedulerParticle[] bestParticle= new SchedulerParticle[Constants.NO_OF_TASKS];
        for(int i=0;i<Constants.NO_OF_TASKS;i++)
        	bestParticle[i]= (SchedulerParticle) swarm.getBestParticle(i);      
        System.out.println((bestParticle[0]).toString());
        return swarm.getBestPosition();
    }

    private static void initParticles() {
        particles = new SchedulerParticle[Constants.POPULATION_SIZE];
        for (int i = 0; i < Constants.POPULATION_SIZE; ++i)
            particles[i] = new SchedulerParticle();
    }

    public void printBestFitness() {
        System.out.println("\nBest fitness value: " + swarm.getBestFitness() +
                           "\nBest makespan: " + ff.calcMakespan(swarm.getBestPosition()));
    }
}