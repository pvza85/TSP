/*By Payam Azad
 * Update: 11-Nov-2013
 */
package hbeda;

public class Problem 
{
    protected Parameters parameters;
    public String fileName;
    
    public Problem(String inputFile, Parameters parameters)
    {
        this.parameters = parameters; 
        fileName = inputFile;
        read(inputFile);
    }
    
    public void read(String inputFile){}
    
    //get a permutation and calculate the fitness of it
    public double evaluate(int[] permutation)
    {
        return 0.0;
    }
}
