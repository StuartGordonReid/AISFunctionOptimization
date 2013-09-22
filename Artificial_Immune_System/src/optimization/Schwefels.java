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
public class Schwefels extends OptimizationProblem {

    /* In Matlab:
     function y = schw(x)
     n = 2;
     s = sum(-x.*sin(sqrt(abs(x))));
     y = 418.9829*n+s;
     */
    public Schwefels(int d, String l) {
        super(d, l);
        upperBound = 500.0;
        lowerBound = -500.0;
    }

    @Override
    public double getFitness(double[] solution) {
        double s = 0.0;
        int n = solution.length;
        for (int i = 0; i < n; i++) {
            s = s + (solution[i] * Math.sin(Math.sqrt(Math.abs(solution[i]))));
        }
        double fitness = 418.9829 * n + s;
        return fitness;
    }

}
