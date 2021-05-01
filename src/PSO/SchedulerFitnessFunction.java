package PSO;

import net.sourceforge.jswarm_pso.FitnessFunction;
import utils.Constants;
import utils.GenerateMatrices;

public class SchedulerFitnessFunction extends FitnessFunction {
    private static double[][] execMatrix, commMatrix,tec,ect,vmallocation;

    SchedulerFitnessFunction() {
        super(false);
        commMatrix = GenerateMatrices.getCommMatrix();
        execMatrix = GenerateMatrices.getExecMatrix();
        tec = GenerateMatrices.getTecMatrix();
        ect = GenerateMatrices.getEctMatrix();
        vmallocation = GenerateMatrices.getVmallocationMatrix();
        
    }

    @Override
    public double[] evaluate(double[] position) {
    	double fit[];
    	fit=new double[Constants.NO_OF_TASKS];
		for(int i=0;i<Constants.NO_OF_TASKS;i++) {
			int dcid = (int) position[i];
			fit[i] = (tec[i][dcid]+ect[i][dcid]+vmallocation[i][dcid]);
		}
		return fit;
    }

    private double calcTotalTime(double[] position) {
        double totalCost = 0;
        for (int i = 0; i < Constants.NO_OF_TASKS; i++) {
            int dcId = (int) position[i];
            totalCost += execMatrix[i][dcId] + commMatrix[i][dcId];
        }
        return totalCost;
    }

    public double calcMakespan(double[] position) {
        double makespan = 0;
        double[] dcWorkingTime = new double[Constants.NO_OF_VMS];

        for (int i = 0; i < Constants.NO_OF_TASKS; i++) {
            int dcId = (int) position[i];
            if (dcWorkingTime[dcId] != 0) --dcWorkingTime[dcId];
            dcWorkingTime[dcId] += execMatrix[i][dcId] + commMatrix[i][dcId];
            makespan = Math.max(makespan, dcWorkingTime[dcId]);
        }
        return makespan;
    }
    
    public double calcEnergyConsumption(double[] position) {
    	double[] energyConsumptionPerCpu = new double[Constants.NO_OF_CPU];
    	double ans=0;
    	
    	for (int i = 0; i < Constants.NO_OF_CPU; i++) {
    		double Uj = 0;
    		for (int j = 0; j < Constants.NO_OF_TASKS; j++) {
    			int dcId = (int) position[j];
    			double Cj = (Constants.C_Base / 3600.0) * (i+1) * (execMatrix[j][dcId] + commMatrix[j][dcId]) + Constants.C_Trans;
    			Uj += Cj;
    		}
    		Uj = (Uj / Constants.NO_OF_CPU) * 100;
    		int pmax = 30;
    		int pmin = 20;
    		double energyConsumption = (pmax - pmin) * Uj + pmin;
    		energyConsumptionPerCpu[i] = energyConsumption;
    		ans+=energyConsumption;
    	}
        return ans;
    }
    

    public  double calcLoadCost(double[] position) {
        double utilization = 0.0;
        for (int i = 0; i < Constants.NO_OF_TASKS; i++) {
            int dcId = (int) position[i];
            double time = execMatrix[i][dcId];

        }
        return utilization;
    }
}