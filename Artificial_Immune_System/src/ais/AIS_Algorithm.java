/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ais;

import algorithm.Algorithm;
import algorithm.Data;
import optimization.OptimizationProblem;
import simulator.Configuration;
import java.util.Random;
import pso.PSO_Particle;

/**
 *
 * @author stuart
 */
public class AIS_Algorithm extends Algorithm {

    ALC_Particle[] population;
    OptimizationProblem problem;
    double functionDecay = 100;
    Random randomGenerator;

    public AIS_Algorithm(Configuration c) {
        super(c);
        randomGenerator = new Random();
    }

    @Override
    public void initialize(OptimizationProblem p) {
        problem = p;
        population = new ALC_Particle[c.getPopulationSize()];
        for (int i = 0; i < c.getPopulationSize(); i++) {
            population[i] = new ALC_Particle(problem.getDimension(), p);
        }
    }

    public ALC_Particle iteration() {
        double averagePopulationFitness = averagePopulationFitness();
        do {
            for (int i = 0; i < population.length; i++) {
            }
        } while (averagePopulationFitness() < averagePopulationFitness);
        return population[getGlobalBestIndex()];
    }

    public double averagePopulationFitness() {
        double totalFitness = 0.0;
        for (int i = 0; i < population.length; i++) {
            totalFitness += problem.getFitness(population[i].getSolution());
        }
        return totalFitness / population.length;
    }

    public int getGlobalBestIndex() {
        int gbest = 0;
        double gbestScore = Double.MAX_VALUE;
        for (int i = 0; i < population.length; i++) {
            double score = problem.getFitness(population[i].getSolution());
            if (score < gbestScore && satisfiesConstraints(population[i].solution)) {
                gbest = i;
                gbestScore = score;
            }
        }
        return gbest;
    }

    public boolean satisfiesConstraints(double[] solution) {
        for (int i = 0; i < solution.length; i++) {
            if (solution[i] > problem.getUpperBound() || solution[i] < problem.getLowerBound()) {
                return false;
            }
        }
        return true;
    }

    public double randomGaussian() {
        return 0.0 + randomGenerator.nextGaussian() * 1.0;
    }

    public double mutationRate(double fitness) {
        return (1 / functionDecay) * Math.exp(fitness);
    }

    public ALC_Particle mutate(ALC_Particle particle) {
        double[] solution = particle.getSolution();
        double mutationRate = mutationRate(problem.getFitness(solution));
        for (int i = 0; i < solution.length; i++) {
            particle.solution[i] = particle.solution[i] + mutationRate * randomGaussian();
        }
        return particle;
    }

    @Override
    public Data optimize() {
        results = new Data(c);
        for (int i = 0; i < c.getSample(); i++) {
            for (int j = 0; j < c.getIterations(); j++) {
                ALC_Particle gbest = iteration();
                if (j % c.getResolution() == 0) {
                    //System.out.println(problem.getFitness(gbest.getSolution()));
                    results.addDatum(problem.getFitness(gbest.getSolution()));
                }
            }
            results.completeSample();
        }
        return results;
    }

    @Override
    public double objectiveFunction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
