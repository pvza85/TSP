package hbeda;

import java.util.Random;

public class TwoOPT 
{
    Individual current;
    TSP problem;
    
    public TwoOPT(Individual input, TSP problem)
    {
        current = input;
        this.problem = problem;
    }
    
    public Individual run()
    {
        int[] temp = current.chromosome;
        boolean change = false;
        double currentFitness = evaluate(temp);
        System.out.println(currentFitness);
        double best = currentFitness;
        int[] Best = new int[temp.length];
        for(int i = 0; i < temp.length; i++)
            Best[i] = temp[i];
        
        Random rand = new Random(System.currentTimeMillis());
        
        int counter = 0;
        int x = temp.length;
        int n = temp.length;
        int i, j;
        double randomWalk = 0;
        
        /*counter = 0;
            change = false;
            outerloop:
            
            
            
        for(int counter1 = n-1; counter1 >=0; counter1--)
            for(int counter2 = n-1; counter2 >= 0; counter2--)
            {
                i = counter1;//rand.nextInt(n);
                j = counter2;//rand.nextInt(n);
                int a = temp[j];
                temp[j] = temp[((i+1) % temp.length)];
                temp[((i+1) % temp.length)] = a;

                double t = evaluate(temp);
                if(t < currentFitness )
                {
                    System.out.println(t);
                    currentFitness = t;
                    change = true;
                    best = currentFitness;
                    for(int k = 0; k < temp.length; k++)
                        Best[k] = temp[k];
                    if(++counter > x)
                    {
                        break outerloop;
                    }
                }
                else if(rand.nextDouble() < randomWalk)
                {
                    System.out.println(t);
                    currentFitness = t;
                    change = true;
                    best = currentFitness;
                    for(int k = 0; k < temp.length; k++)
                        Best[k] = temp[k];
                    randomWalk /= 10;
                    break outerloop;
                }


                temp[((i+1) % temp.length)] = temp[j];
                temp[j] = a;
            }
        for(int k= 0; k < temp.length; k++)
            temp[k] = Best[k];*/

        x = 3;
        
        do
        {
            counter = 0;
            change = false;
            outerloop:
            
            
            
            for(int counter1 = n-1; counter1 >=0; counter1--)
                for(int counter2 = counter1; counter2 >= 0; counter2--)
                {
                    i = counter1;//rand.nextInt(n);
                    j = counter2;//rand.nextInt(n);
                    int a = temp[j];
                    temp[j] = temp[((i+1) % temp.length)];
                    temp[((i+1) % temp.length)] = a;
                    
                    //int[] temptemp = new int[temp.length];
                    //for(int k = 0; k < temp.length; k++)
                        //temptemp[k] = temp[k];
                    
                    //swap(temptemp, counter1, counter2);
                    
                    double t = evaluate(temp);
                    if(t < currentFitness )
                    {
                        System.out.println(t);
                        currentFitness = t;
                        change = true;
                        best = currentFitness;
                        for(int k = 0; k < temp.length; k++)
                            Best[k] = temp[k];
                        if(++counter > x)
                        {
                            break outerloop;
                        }
                    }
                    else if(rand.nextDouble() < randomWalk)
                    {
                        System.out.println(t);
                        currentFitness = t;
                        change = true;
                        best = currentFitness;
                        for(int k = 0; k < temp.length; k++)
                            Best[k] = temp[k];
                        randomWalk /= 10;
                        break outerloop;
                    }
                    
                    
                    temp[((i+1) % temp.length)] = temp[j];
                    temp[j] = a;
                }
            for(int k= 0; k < temp.length; k++)
                temp[k] = Best[k];
        }while(change && counter < 100000);
                
        System.out.println("best = " + best);
        return current; 
    }
    
    private void swap(int[] in, int i, int j)
    {
       
        //int a = in[j];
        //in[j] = temp[((i+1) % temp.length)];
        //in[((i+1) % temp.length)] = a;
        
        int[] out = new int[in.length];
        
        for(; i > j && j >= 0 && i < in.length; i++,j--)
        {
            int t = in[i];
            in[i] = in[j];
            in[j] = t;
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
    private double distance(int a, int b)
    {
        return Math.sqrt(Math.pow(problem.Destinations[a][0] - problem.Destinations[b][0], 2) + Math.pow(problem.Destinations[a][1] - problem.Destinations[b][1], 2));
    }
}
