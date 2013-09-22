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
public class Rosenbrock extends OptimizationProblem {

    public Rosenbrock(int d, String l) {
        super(d, l);
        upperBound = 10.0;
        lowerBound = -5.0;
    }

    @Override
    public double getFitness(double[] solution) {
        double fitness = 0.0;
        for (int i = 0; i < solution.length - 1; i++) {
            double square = solution[i] * solution[i];
            double squareOne = (solution[i] - 1) * (solution[i] - 1);
            fitness += 100 * Math.pow((square - solution[i + 1]), 2.0) + squareOne;
        }
        return fitness;
    }
}
