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

    double[] pbestSolution;
    double[] solution;

    PSO_Particle(int dimension, OptimizationProblem p) {
        solution = new double[dimension];
        for (int i = 0; i < solution.length; i++) {
            solution[i] = p.getLowerBound() + Math.random() * p.getUpperBound();
        }
        pbestSolution = solution;
    }

    public void moveParticle(double[] globalBest) {
        
    }
    
    public double[] getPersonalBest() {
        return pbestSolution;
    }

    public double[] getSolution() {
        return solution;
    }
}
