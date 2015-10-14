package hbeda;

public class Solver03 extends Solver
{
    int[] path1;
    int[] path2;
    double res1, res2;
    Individual solution;
    
    public Solver03(Problem problem)
    {  
        super(problem);
    }
    
    @Override
    public void execute()  
    {
        nearestNeighbor heuristic1 = new nearestNeighbor((TSP)problem);
        solution = heuristic1.run1();
        TwoOPT localSearch = new TwoOPT(solution, (TSP)problem);
        solution = localSearch.run();
        //System.out.println(solution.toString());
        
        
        //MST heuristic2 = new MST((TSP)problem);
        //path2 = heuristic2.run();
        //res2 = heuristic2.evaluate(path2);
        
        //print();
    }
    
    public void print()
    {
        System.out.printf("neares neibor: %f \nMST: %f \n\n\n", res1, res2);
    }
    
    
}
