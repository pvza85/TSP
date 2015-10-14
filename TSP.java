/* Author: Payam Azad
 * 4-October-2013
 * Update: 11-Nov-2013
 */
package hbeda;

import java.io.File;
import java.util.Scanner;

public class TSP extends Problem
{    
    public int destinationCount;
    public double[][] Destinations;
    
    public TSP(String inputFile, Parameters parameters)
    {
        super(inputFile, parameters);  
    }
    
    @Override
    public void read(String inputFile)
    {     
        Scanner input;
        
        try
        {
            input = new Scanner(new File(inputFile));
            
            destinationCount = input.nextInt();
            parameters.chromosomeLength = destinationCount;
            Destinations = new double[destinationCount][2];
            
            for(int i = 0; i < destinationCount; i++)
            {
                Destinations[i][0] = input.nextDouble();
                Destinations[i][1] = input.nextDouble();
            }
        }
        catch(Exception e)
        {
            System.out.println("Error in Reading input File!");
        }
        
        //set the best population and maximum generation as I think
        parameters.populationSize = 80; //2 * destinationCount;
        parameters.maxGeneration = 25 * destinationCount;
    }
    
    //TODO make it as Exception
    //get a permutation of destinations and calculate it's fitness(distance to travel)
    @Override
    public double evaluate(int[] permutation)
    {
        double res = 0.0;
        
        //if(true)
        if(checkAnswer(permutation))
        {
            int i;
            for(i = 0; i < destinationCount; i++)
            {  
                res += distance(permutation[i], permutation[((i+1)%destinationCount)]);
            }
            
            return res;
        }
        
        return Double.POSITIVE_INFINITY;
    }
    
    //find distance between two destinations
    private double distance(int a, int b)
    {
        return Math.sqrt(Math.pow(Destinations[a][0] - Destinations[b][0], 2) + Math.pow(Destinations[a][1] - Destinations[b][1], 2));
    }
    
    //TODO make it as Exception
    private boolean checkAnswer(int[] permutation)
    {
        if(permutation.length != destinationCount)
        {
            System.out.println("Larger permutation");
            return false;
        }
        else 
        {
            int[] check = new int[destinationCount];
            for(int i = 0; i < destinationCount; i++)
            {
                if(permutation[i] < 0 || permutation[i] >= destinationCount)
                {
                    System.out.println("Permuation member " + i+1 + " is out of destination count!");
                    return false;
                }
                check[permutation[i]]++;
            } 
            
            for(int i = 0; i < destinationCount; i++)
            {
                if(check[i] > 1)
                {
                    System.out.println("destination " + (i+1) + " is visited more than once!");
                    return false;
                }
                /*else if(check[i] == 0)
                {
                    System.out.println("destination " + (i+1) + " is never visited!");
                    return false;
                }*/
            }
        }
        
        return true;
    }
}
