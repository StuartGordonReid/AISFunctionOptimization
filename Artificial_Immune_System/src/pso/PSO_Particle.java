/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pso;

import java.util.Arrays;
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
    double c1 = 1.496180;
    double c2 = 1.496180;
    double w = 0.729844;

    double max = 3.0;

    PSO_Particle(int dimension, OptimizationProblem p) {
        problem = p;
        solution = new double[dimension];
        v = new double[dimension];
        for (int i = 0; i < solution.length; i++) {
            solution[i] = p.getLowerBound() + Math.random() * p.getUpperBound();
            v[i] = 0.05;
        }
        personalBest = solution;
    }

    public void moveParticle(double[] globalBest) {
        for (int i = 0; i < globalBest.length; i++) {
            double velocity = calculateVelocity(solution[i], globalBest[i], personalBest[i], i);
            solution[i] = solution[i] + velocity;
        }
        if (problem.getFitness(solution) > problem.getFitness(personalBest)) {
            personalBest = solution;
        }
        if (restart(solution, globalBest)) {
            for (int i = 0; i < solution.length; i++) {
                solution[i] = problem.getLowerBound() + Math.random() * problem.getUpperBound();
                v[i] = 0.05;
                //System.out.println("restart");
            }
        }
    }

    public boolean restart(double[] sol, double[] gbest) {
        double mx = 0.501;
        double mn = 0.499;
        double similarity = 0.0;

        for (int i = 0; i < sol.length; i++) {
            similarity += sol[i] / (sol[i] + gbest[i]);
        }

        similarity = similarity / gbest.length;
        if (similarity > mn && similarity < mx) {
            return true;
        } else {
            return false;
        }
    }

    public double calculateVelocity(double current, double gbest, double pbest, int i) {
        double c1_v = pbest - current;
        double c2_v = gbest - current;
        v[i] = (w * v[i]) + c1 * (c1_v) + c2 * (c2_v);
        return v[i];
    }

    public double[] getPersonalBest() {
        return personalBest;
    }

    public double[] getSolution() {
        return solution;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Arrays.hashCode(this.solution);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PSO_Particle other = (PSO_Particle) obj;
        if (!Arrays.equals(this.solution, other.solution)) {
            return false;
        }
        return true;
    }
}
