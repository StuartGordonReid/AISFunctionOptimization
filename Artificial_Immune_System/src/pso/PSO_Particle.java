/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pso;

import optimization.OptimizationProblem;

/**
 *
 * @author stuart
 */
public class PSO_Particle {

    OptimizationProblem problem;

    double[] personalBest;
    double[] solution;

    double[] v;
    double c1 = 0.0;
    double c2 = 0.0;

    double max = 3.0;

    PSO_Particle(int dimension, OptimizationProblem p) {
        problem = p;
        solution = new double[dimension];
        for (int i = 0; i < solution.length; i++) {
            solution[i] = p.getLowerBound() + Math.random() * p.getUpperBound();
        }
        personalBest = solution;
    }

    public void moveParticle(double[] globalBest) {
        for (int i = 0; i < globalBest.length; i++) {

            double velocity = calculateVelocity(solution[i], globalBest[i], personalBest[i], i);
            solution[i] = solution[i] + velocity;

            if (solution[i] > problem.getUpperBound() || solution[i] < problem.getLowerBound()) {
                System.out.println("condition break");
                solution[i] = problem.getLowerBound() + Math.random() * problem.getUpperBound();
            }

            if (problem.getFitness(solution) > problem.getFitness(personalBest)) {
                personalBest = solution;
            }
        }
    }

    public double calculateVelocity(double current, double gbest, double pbest, int i) {
        double c1_v = pbest - current;
        double c2_v = gbest - current;
        v[i] = v[i] + c1 * (c1_v) + c2 * (c2_v);
        return v[i] > max ? max : v[i];
    }

    public double[] getPersonalBest() {
        return personalBest;
    }

    public double[] getSolution() {
        return solution;
    }
}
