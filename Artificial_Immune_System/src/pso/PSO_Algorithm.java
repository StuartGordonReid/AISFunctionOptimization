/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pso;

import algorithm.Algorithm;
import algorithm.Data;
import optimization.OptimizationProblem;
import simulator.Configuration;

/**
 *
 * @author stuart
 */
public class PSO_Algorithm extends Algorithm {

    PSO_Particle[] population;
    OptimizationProblem problem;

    public PSO_Algorithm(Configuration c) {
        super(c);
    }

    @Override
    public void initialize(OptimizationProblem p) {
        problem = p;
        population = new PSO_Particle[c.getPopulationSize()];
        for (int i = 0; i < c.getPopulationSize(); i++) {
            population[i] = new PSO_Particle(problem.getDimension(), p);
        }
    }

    public PSO_Particle iteration() {

        return null;
    }

    public int getGlobalBestIndex() {
        int gbest = 0;
        double gbestScore = Double.MAX_VALUE;
        for (int i = 0; i < population.length; i++) {
            if (problem.getFitness(population[i].getSolution()) < gbestScore) {
                gbest = i;
            }
        }
        return gbest;
    }

    public void updatePersonalBest() {
        
    }

    @Override
    public Data optimize() {
        Data results = null;
        for (int i = 0; i < c.getSample(); i++) {
            for (int j = 0; j < c.getIterations(); j++) {
                PSO_Particle gbest = iteration();
                System.out.println(problem.getFitness(gbest.getSolution()));
            }
        }
        return results;
    }

    @Override
    public double objectiveFunction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
