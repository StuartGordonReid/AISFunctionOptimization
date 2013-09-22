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
public class Ackleys extends OptimizationProblem {

    /* In matlab:
     function y = ackley(x)
     n = 2;
     a = 20; b = 0.2; c = 2*pi;
     s1 = 0; s2 = 0;
     for i=1:n;
     s1 = s1+x(i)^2;
     s2 = s2+cos(c*x(i));
     end
     y = -a*exp(-b*sqrt(1/n*s1))-exp(1/n*s2)+a+exp(1);
     */
    double a = 20;
    double b = 0.2;
    double c = 2 * Math.PI;

    public Ackleys(int d, String l) {
        super(d, l);
        upperBound = 30.0;
        lowerBound = -15.0;
    }

    @Override
    public double getFitness(double[] solution) {
        int n = solution.length;
        double s1 = 0.0;
        double s2 = 0.0;

        for (int i = 0; i < n; i++) {
            s1 = s1 + (solution[i] * solution[i]);
            s2 = s2 + Math.cos(c * solution[i]);
        }

        double fitness = -a * Math.exp(-b * Math.sqrt(1 / (n * s1))) - Math.exp(1 / (n * s2)) + a + Math.exp(1.0);
        //System.out.println(fitness + "," + s1 + "," + s2);
        return fitness;
    }
}
