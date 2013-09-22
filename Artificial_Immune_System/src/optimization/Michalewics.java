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
public class Michalewics extends OptimizationProblem {

    /* In Matlab:
     function y = mich(x)
     n = 2; 
     m = 10;
     s = 0;
     for i = 1:n;
     s = s+sin(x(i))*(sin(i*x(i)^2/pi))^(2*m);
     end
     y = -s;
     */
    double m = 10;

    public Michalewics(int d, String l) {
        super(d, l);
        upperBound = Math.PI;
        lowerBound = 0.0;
    }

    @Override
    public double getFitness(double[] solution) {
        int n = solution.length;
        double s = 0.0;
        for (int i = 0; i < n; i++) {
            double v = i * solution[i];
            s = s + Math.sin(solution[i]) * Math.pow((Math.sin((v * v) / Math.PI)), 2 * m);
        }
        double fitness = -s;
        return fitness;
    }

}
