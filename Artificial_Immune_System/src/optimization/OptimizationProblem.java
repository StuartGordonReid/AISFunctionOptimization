/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package optimization;

/**
 *
 * @author stuart
 */
public abstract class OptimizationProblem {

        
    double upperBound = 1.0;
    double lowerBound = -1.0;
    
    private String label;
    private final int dimension;
    
    public OptimizationProblem(int d, String l) {
        dimension = d;
        label = l;
    }
    
    public abstract double getFitness(double[] solution);

    public int getDimension() {
        return dimension;
    }

    public String getLabel() {
        return label;
    }

    public double getUpperBound() {
        return upperBound;
    }

    public double getLowerBound() {
        return lowerBound;
    }
}
