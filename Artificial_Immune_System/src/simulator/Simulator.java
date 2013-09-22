/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simulator;

import ais.AIS_Algorithm;
import algorithm.Data;
import java.io.FileWriter;
import java.util.LinkedList;
import optimization.Ackleys;
import optimization.Michalewics;
import optimization.OptimizationProblem;
import optimization.Rosenbrock;
import optimization.Schwefels;
import pso.PSO_Algorithm;

/**
 *
 * @author stuart
 */
public class Simulator {

    String[] simulations = {
        "Rosenbrock dimension 2",
        "Rosenbrock dimension 5",
        "Rosenbrock dimension 10",
        "Rosenbrock dimension 20",
        "Michalewics dimension 2",
        "Michalewics dimension 5",
        "Michalewics dimension 10",
        "Michalewics dimension 20",
        "Schwefels dimension 2",
        "Schwefels dimension 5",
        "Schwefels dimension 10",
        "Schwefels dimension 20",};

    public Simulator() {
    }

    public void run() {
        //samples, iterations, resolution, population
        Configuration config = new Configuration(55, 2000, 5, 50);

        PSO_Algorithm pso = new PSO_Algorithm(config);
        AIS_Algorithm ais = new AIS_Algorithm(config);

        LinkedList<OptimizationProblem> problems = new LinkedList();
        problems.add(new Rosenbrock(2, simulations[0]));
        problems.add(new Rosenbrock(5, simulations[1]));
        problems.add(new Rosenbrock(10, simulations[2]));
        problems.add(new Rosenbrock(20, simulations[3]));

        problems.add(new Michalewics(2, simulations[4]));
        problems.add(new Michalewics(5, simulations[5]));
        problems.add(new Michalewics(10, simulations[6]));
        problems.add(new Michalewics(10, simulations[7]));

        problems.add(new Schwefels(2, simulations[8]));
        problems.add(new Schwefels(5, simulations[9]));
        problems.add(new Schwefels(10, simulations[10]));
        problems.add(new Schwefels(10, simulations[11]));

        LinkedList<Data> results = new LinkedList();
        for (int i = 0; i < problems.size(); i++) {
            String simulationLabel = "PSO Solving " + problems.get(i).getLabel();
            System.out.println(simulationLabel);
            pso.initialize(problems.get(i));
            results.add(pso.optimize());
            System.out.println("==============================================");
        }
        writeToFile("PSOResults.csv", results);
        /*
         for (int i = 0; i < problems.size(); i++) {
         String simulationLabel = "AIS Solving " + problems.get(i).getLabel();
         System.out.println(simulationLabel);
         ais.initialize(problems.get(i));
         Data results = ais.optimize();
         results.writeToFile(simulationLabel);
         System.out.println("==============================================");
         }*/
    }

    public void writeToFile(String fileName, LinkedList<Data> results) {
        try {
            FileWriter fw = new FileWriter(fileName);
            String lineOut = "Iter,";
            for (int i = 0; i < simulations.length; i++) {
                lineOut += simulations[i] + ",";
            }
            fw.write(lineOut + "\n");

            LinkedList<LinkedList<Double>> averages = new LinkedList();
            for (int i = 0; i < results.size(); i++) {
                averages.add(results.get(i).getAverage());
            }

            for (int j = 0; j < averages.get(0).size(); j++) {
                lineOut = "" + j + ",";
                for (int i = 0; i < averages.size(); i++) {
                    lineOut += averages.get(i).get(j) + ",";
                }
                fw.write(lineOut + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
