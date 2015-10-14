
package hbeda;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;

public class nearestNeighbor 
{
    TSP problem;
    int[] result;
    
    public nearestNeighbor(TSP p)
    {
        problem = p;
        result = new int[problem.destinationCount];
    }
    public int[] run()
    {
        long startTime = System.currentTimeMillis();
        
        LinkedList list = new LinkedList();
        for(int j = 1; j < problem.destinationCount; j++)
            list.add(j);
            
        result[0] = 0;
        for(int i = 1; i < problem.destinationCount; i++)
        {
            int nearest = 0;
            double nearestDist = Double.MAX_VALUE;
            for(int j = 0; j < list.size(); j++)
            {
                int source = result[i-1];
                int dest = (int) list.get(j);
                //int index = j;
                double dist = distance(source, dest);
                
                if(nearestDist > dist)
                {
                    nearestDist = dist; 
                    nearest = dest;
                }
            }
            result[i] = nearest;
            list.remove(list.indexOf(nearest));
        }
        
        long runningTime = System.currentTimeMillis() - startTime;
        
        //log into result
        //log(runningTime); 
        
        /*for(int i = 0; i < result.length; i++)
            System.out.printf("%d ", result[i]);*/
        
        return result;
    }
    
    public Individual run1()
    {
        return new Individual(run());
    }
    private double distance(int a, int b)
    {
        return Math.sqrt(Math.pow(problem.Destinations[a][0] - problem.Destinations[b][0], 2) + Math.pow(problem.Destinations[a][1] - problem.Destinations[b][1], 2));
    }
    
    public void log(long time)
    {
        int timeInSeconds = (int)time/1000;
        try
        {
            File simpleOutput = new File("simple_output.out");
            if(!simpleOutput.exists()){simpleOutput.createNewFile();}
            
            //File completeOutput = new File("/results/complete_" + problem.fileName + ".out");
            //if(!completeOutput.exists()){completeOutput.createNewFile();}
            
            FileWriter simpleWritter = new FileWriter(simpleOutput.getName(),true);
            BufferedWriter bufferWritter = new BufferedWriter(simpleWritter);
            
            String data = "#for file: " + problem.fileName + "\n";
            data += String.format("solved in time: %d seconds \n", timeInSeconds);
            data += String.format("best result was: %f\n", evaluate(result));
            for(int i = 0; i < result.length; i++)
                data += String.format("%d -> ", result[i]);
            data += "\n\n\n";
            
            bufferWritter.write(data);

            bufferWritter.close();
        }
        catch(Exception e)
        {
            System.out.println("Error in output!");
        }
    }
    
    public double evaluate(int[] permutation)
    {
        double res = 0.0;
        
        if(true)//checkAnswer(permutation))
        {
            int i;
            for(i = 0; i < permutation.length; i++)
            {  
                res += distance(permutation[i], permutation[((i+1)%permutation.length)]);
            }
            
            return res;
        }
        
        return Double.POSITIVE_INFINITY;
    }
}
