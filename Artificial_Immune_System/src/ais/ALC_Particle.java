/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ais;

import optimization.OptimizationProblem;

/**
 *
 * @author stuart
 */
public class ALC_Particle {

    OptimizationProblem problem;
    double[] solution;

    public ALC_Particle(int dimension, OptimizationProblem p) {
        problem = p;
        solution = new double[dimension];
        for (int i = 0; i < solution.length; i++) {
            solution[i] = p.getLowerBound() + Math.random() * p.getUpperBound();
        }
    }

    public double[] getSolution() {
        return solution;
    }

    public ALC_Particle createClone() {
        return null;
    }
}
