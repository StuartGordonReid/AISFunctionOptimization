/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package algorithm;

import optimization.OptimizationProblem;
import simulator.Configuration;

/**
 *
 * @author stuart
 */
public abstract class Algorithm {

    public Configuration c;
    public Data results;

    public Algorithm(Configuration con) {
        c = con;
    }

    public abstract void initialize(OptimizationProblem problem);

    public abstract Data optimize();

    public abstract double objectiveFunction();
}
