/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simulator;

import ais.AIS_Algorithm;
import algorithm.Data;
import java.util.LinkedList;
import optimization.Ackleys;
import optimization.Michalewics;
import optimization.OptimizationProblem;
import optimization.Schwefels;
import pso.PSO_Algorithm;

/**
 *
 * @author stuart
 */
public class Simulator {

    Simulator() {
    }

    public void run() {
        //samples, iterations, resolution, population
        Configuration config = new Configuration(55, 2000, 20, 50);

        PSO_Algorithm pso = new PSO_Algorithm(config);
        AIS_Algorithm ais = new AIS_Algorithm(config);

        LinkedList<OptimizationProblem> problems = new LinkedList();
        problems.add(new Ackleys(2, "Ackley dimension 2"));
        problems.add(new Ackleys(5, "Ackley dimension 5"));
        problems.add(new Ackleys(10, "Ackley dimension 10"));

        problems.add(new Michalewics(2, "Michalewics dimension 2"));
        problems.add(new Michalewics(5, "Michalewics dimension 5"));
        problems.add(new Michalewics(10, "Michalewics dimension 10"));

        problems.add(new Schwefels(2, "Schwefels dimension 2"));
        problems.add(new Schwefels(5, "Schwefels dimension 5"));
        problems.add(new Schwefels(10, "Schwefels dimension 10"));

        for (int i = 0; i < problems.size(); i++) {
            String simulationLabel = "PSO Solving " + problems.get(i).getLabel();
            System.out.println(simulationLabel);
            pso.initialize(problems.get(i));
            Data results = pso.optimize();
            results.writeToFile(simulationLabel);
            System.out.println("==============================================");
        }

        for (int i = 0; i < problems.size(); i++) {
            String simulationLabel = "AIS Solving " + problems.get(i).getLabel();
            System.out.println(simulationLabel);
            ais.initialize(problems.get(i));
            Data results = ais.optimize();
            results.writeToFile(simulationLabel);
            System.out.println("==============================================");
        }
    }
}
