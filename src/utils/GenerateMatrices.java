package utils;

import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.lists.VmList;
import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.List;
import java.util.Random;

public class GenerateMatrices {
    private static double[][] commMatrix, execMatrix, tec, ect, vmallocation;// 
    private File commFile = new File("CommunicationTimeMatrix.txt");
    private File execFile = new File("ExecutionTimeMatrix.txt");
    private File tecFile = new File("TotalExecutionCostMatrix.txt");
    private File ectFile = new File("ExpectedCompetedTime.txt");
    private File vmallocationFile =new File("Vmallocate.txt");
    private String filePath = "C:\\Users\\Prayush Mangal\\Downloads\\cloudsim-task-scheduling-master\\cloudsim-task-scheduling-master\\cloudlets.txt";

    public GenerateMatrices() {
        commMatrix = new double[Constants.NO_OF_TASKS][Constants.NO_OF_VMS];
        execMatrix = new double[Constants.NO_OF_TASKS][Constants.NO_OF_VMS];
        tec = new double[Constants.NO_OF_TASKS][Constants.NO_OF_VMS];
        ect = new double[Constants.NO_OF_TASKS][Constants.NO_OF_VMS];
        vmallocation= new double[Constants.NO_OF_TASKS][Constants.NO_OF_VMS];
        try {
            if (commFile.exists() && execFile.exists()) {
                readCostMatrix();
            } else {
                initCostMatrix();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //åŸºäºŽå·²æœ‰è™šæ‹Ÿæœºåˆ—è¡¨çš„å�„çŸ©é˜µåˆ�å§‹åŒ–
    public GenerateMatrices(List<Vm> vmlist){
        this.vmlist = vmlist;


        commMatrix = new double[Constants.NO_OF_TASKS][Constants.NO_OF_VMS];
        execMatrix = new double[Constants.NO_OF_TASKS][Constants.NO_OF_VMS];
        tec = new double[Constants.NO_OF_TASKS][Constants.NO_OF_VMS];
        ect = new double[Constants.NO_OF_TASKS][Constants.NO_OF_VMS];
        vmallocation= new double[Constants.NO_OF_TASKS][Constants.NO_OF_VMS];
        try {
            if (commFile.exists() && execFile.exists()) {
                readCostMatrix();
            } else {
                initCostMatrix(filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initCostMatrix() throws IOException {
        System.out.println("Initializing new Matrices...");
        BufferedWriter commBufferedWriter = new BufferedWriter(new FileWriter(commFile));
        BufferedWriter execBufferedWriter = new BufferedWriter(new FileWriter(execFile));
        BufferedWriter tecBufferedWriter = new BufferedWriter(new FileWriter(tecFile));
        BufferedWriter ectBufferedWriter = new BufferedWriter(new FileWriter(ectFile));
        BufferedWriter vmallocationBufferedWriter = new BufferedWriter(new FileWriter(vmallocationFile));

        for (int i = 0; i < Constants.NO_OF_TASKS; i++) {
            for (int j = 0; j < Constants.NO_OF_VMS; j++) {
                commMatrix[i][j] = Math.random() * 600 + 20;
                execMatrix[i][j] = Math.random() * 500 + 10;
                tec[i][j]=Math.random() * 500 + 10;
                ect[i][j]=Math.random() * 500 + 10;
                vmallocation[i][j]=Math.random() * 500 + 10;
                commBufferedWriter.write(String.valueOf(commMatrix[i][j]) + ' ');
                execBufferedWriter.write(String.valueOf(execMatrix[i][j]) + ' ');
                tecBufferedWriter.write(String.valueOf(tec[i][j]) + ' ');
                ectBufferedWriter.write(String.valueOf(ect[i][j]) + ' ');
                vmallocationBufferedWriter.write(String.valueOf(vmallocation[i][j]) + ' ');
            }
            commBufferedWriter.write('\n');
            execBufferedWriter.write('\n');
            tecBufferedWriter.write('\n');
            ectBufferedWriter.write('\n');
            vmallocationBufferedWriter.write('\n');
        }
        commBufferedWriter.close();
        execBufferedWriter.close();
        tecBufferedWriter.close();
        ectBufferedWriter.close();
        vmallocationBufferedWriter.close();
    }

    private void readCostMatrix() throws IOException {
        System.out.println("Reading the Matrices...");
        BufferedReader commBufferedReader = new BufferedReader(new FileReader(commFile));

        int i = 0, j = 0;
        do {
            String line = commBufferedReader.readLine();
            for (String num : line.split(" ")) {
                commMatrix[i][j++] = new Double(num);
            }
            ++i;
            j = 0;
        } while (commBufferedReader.ready());


        BufferedReader execBufferedReader = new BufferedReader(new FileReader(execFile));

        i = j = 0;
        do {
            String line = execBufferedReader.readLine();
            for (String num : line.split(" ")) {
                execMatrix[i][j++] = new Double(num);
            }
            ++i;
            j = 0;
        } while (execBufferedReader.ready());
    
    
    
    	BufferedReader tecBufferedReader = new BufferedReader(new FileReader(tecFile));

    	i = j = 0;
    	do {
    		String line = tecBufferedReader.readLine();
    		for (String num : line.split(" ")) {
    			tec[i][j++] = new Double(num);
    		}
    		++i;
    		j = 0;
    	} while (tecBufferedReader.ready());
    	
    	BufferedReader ectBufferedReader = new BufferedReader(new FileReader(ectFile));

    	i = j = 0;
    	do {
    		String line = ectBufferedReader.readLine();
    		for (String num : line.split(" ")) {
    			ect[i][j++] = new Double(num);
    		}
    		++i;
    		j = 0;
    	} while (ectBufferedReader.ready());
    	
    	BufferedReader vmallocationBufferedReader = new BufferedReader(new FileReader(vmallocationFile));

    	i = j = 0;
    	do {
    		String line = vmallocationBufferedReader.readLine();
    		for (String num : line.split(" ")) {
    			vmallocation[i][j++] = new Double(num);
    		}
    		++i;
    		j = 0;
    	} while (vmallocationBufferedReader.ready());	
	}
    
    

    public static double[][] getCommMatrix() {
        return commMatrix;
    }
    public static double[][] getTecMatrix() {
        return tec;
    }
    public static double[][] getEctMatrix() {
        return ect;
    }
    public static double[][] getVmallocationMatrix() {
        return vmallocation;
    }

    public static double[][] getExecMatrix() {
        return execMatrix;
    }

    //ä¸€èˆ¬publicæ–¹æ³•
    public double[][] getcommMatrix() {
        return commMatrix;
    }
    public double[][] gettecMatrix() {
        return tec;
    }
    public double[][] getvmallocationMatrix() {
        return vmallocation;
    }
    public double[][] getectMatrix() {
        return ect;
    }

    public double[][] getexecMatrix(){
        return execMatrix;
    }

    //åŸºäºŽæ ·æœ¬ç”Ÿæˆ�ä»»åŠ¡åˆ�å§‹åŒ–çŸ©é˜µçš„æ–¹æ³•
    private void initCostMatrix(String filePath) throws IOException
    {
        @SuppressWarnings("resource")
        BufferedReader br= new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
        String data = null;
        int index=0;
        double[] LengthGroup = new double[Constants.NO_OF_TASKS];
        while ((data = br.readLine()) != null)
        {
            System.out.println(data);
            String[] taskLength=data.split("\t");//tasklength[i]æ˜¯ä»»åŠ¡æ‰§è¡Œçš„è€—è´¹ï¼ˆæŒ‡ä»¤æ•°é‡�ï¼‰
            for(int j=0;j<taskLength.length;j++){
                LengthGroup[index+j] = Double.parseDouble(taskLength[j]);
                if((index+j+1)==Constants.NO_OF_TASKS)
                {
                    br.close();
                    initMatrix(LengthGroup);
                    return;
                }
            }
            //20 cloudlets each line in the file cloudlets.txt.
            index+=taskLength.length;
        }
    }
    

    private void initMatrix(double[] LengthGroup) throws IOException
    {
        System.out.println("Initializing new Matrices...");
        BufferedWriter commBufferedWriter = new BufferedWriter(new FileWriter(commFile));
        BufferedWriter execBufferedWriter = new BufferedWriter(new FileWriter(execFile));
        BufferedWriter tecBufferedWriter = new BufferedWriter(new FileWriter(tecFile));
        BufferedWriter ectBufferedWriter = new BufferedWriter(new FileWriter(ectFile));
        BufferedWriter vmallocationBufferedWriter = new BufferedWriter(new FileWriter(vmallocationFile));
        Random rm = new Random();
        for (int i = 0; i < Constants.NO_OF_TASKS; i++) {
            HashMap<Integer, Double> h1 = new HashMap<Integer, Double>();
            HashMap<Integer, Double> h2 = new HashMap<Integer, Double>();
            HashMap<Integer, Double> h3 = new HashMap<Integer, Double>();
            for (int j = 0; j < Constants.NO_OF_VMS; j++) {
                commMatrix[i][j] = Calculator.div(VmList.getById(vmlist,j).getSize(),VmList.getById(vmlist, j).getBw());
                execMatrix[i][j] = Calculator.div(LengthGroup[rm.nextInt(Constants.NO_OF_TASKS)],VmList.getById(vmlist, j).getMips());
                tec[i][j]=(execMatrix[i][j])*Math.random()+(commMatrix[i][j])*Math.random();
                ect[i][j]=execMatrix[i][j];
                vmallocation[i][j]=VmList.getById(vmlist, j).getMips();
                h1.put(j, tec[i][j]);
                h2.put(j, ect[i][j]);
                h3.put(j, vmallocation[i][j]);
//                execMatrix[i][j] = Calculator.div(LengthGroup[i],VmList.getById(vmlist, j).getHost().getTotalAllocatedMipsForVm(VmList.getById(vmlist, j)));
                commBufferedWriter.write(String.valueOf(commMatrix[i][j]) + ' ');
                execBufferedWriter.write(String.valueOf(execMatrix[i][j]) + ' ');     
                
            }
            Map<Integer, Double> hm1 = GFG.sortByValue(h1);
            Map<Integer, Double> hm2 = GFG.sortByValue(h2);
            Map<Integer, Double> hm3 = gfg1.sortByValue(h3);
            int k=1;
            for (Map.Entry<Integer, Double> en : hm1.entrySet()) {
            	tec[i][en.getKey()]=k;
            	k++;
            }
       
            k=1;
            for (Map.Entry<Integer, Double> en : hm2.entrySet()) {
            	ect[i][en.getKey()]=k;
            	k++;
            }
            k=1;
            for (Map.Entry<Integer, Double> en : hm3.entrySet()) {
            	vmallocation[i][en.getKey()]=k;
            	k++;
            }
            
            for (int j = 0; j < Constants.NO_OF_VMS; j++) {
            	tecBufferedWriter.write(String.valueOf(tec[i][j]) + ' ');
            	ectBufferedWriter.write(String.valueOf(ect[i][j]) + ' ');
            	vmallocationBufferedWriter.write(String.valueOf(vmallocation[i][j]) + ' ');
           }
            
            commBufferedWriter.write('\n');
            execBufferedWriter.write('\n');
            tecBufferedWriter.write('\n');
            ectBufferedWriter.write('\n');
            vmallocationBufferedWriter.write('\n');    
        }
        commBufferedWriter.close();
        execBufferedWriter.close();
        tecBufferedWriter.close();
        ectBufferedWriter.close();
        vmallocationBufferedWriter.close();
    }

    private List<Vm> vmlist;
    public void SetVmList(List<Vm> vmlist)
    {
        this.vmlist = vmlist;
    }

    public List<Vm> GetVmList()
    {
        return vmlist;
    }
}
