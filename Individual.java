/* @author Payam Azad    July 2013
 * Update: 11-Nov-2013
 */
package hbeda;

public class Individual 
{
    int chromosomeLength;
    int[] chromosome;
    Problem problem;
    double fitness;
    
    public Individual(int chromosomeLength, Problem problem)
    {
        chromosome = new int[chromosomeLength];
        this.problem = problem;
    }
    public Individual(int chromosomeLength)
    {
        chromosome = new int[chromosomeLength];
        evaluate();
    }
    
    public Individual(int[] chromosome)
    {
        this.chromosome = chromosome;
        this.problem = problem;
        //evaluate();
        
    }
    
    public double evaluate()
    {
        fitness = problem.evaluate(chromosome);
        //problem.
        return fitness;
    }
    
    public String toString()
    {
        return "heh";
    }
}
