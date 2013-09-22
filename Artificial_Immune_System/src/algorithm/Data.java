/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package algorithm;

import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.LinkedList;
import simulator.Configuration;

/**
 *
 * @author stuart
 */
public class Data {

    LinkedList<LinkedList<Double>> samples;
    LinkedList<Double> data;
    Configuration c;
    DecimalFormat df = new DecimalFormat("#####0.0#############################");

    public Data(Configuration config) {
        data = new LinkedList();
        samples = new LinkedList();
        c = config;
    }

    public void completeSample() {
        samples.add(data);
        data = new LinkedList();
    }

    public void addDatum(double d) {
        data.add(d);
    }

    public double getDatum(int index) {
        return data.get(index);
    }

    public void writeToFile(String outputFileName) {
        try {
            FileWriter fw = new FileWriter(outputFileName);
            for (int j = 0; j < (int) (c.getIterations() / c.getResolution()); j++) {
                String lineOut = "";
                for (int i = 0; i < c.getSample(); i++) {
                    try {
                        lineOut += samples.get(i).get(j) + ",";
                    } catch (Exception ex) {
                        lineOut += "NAN" + ",";
                    }
                }
                fw.write(lineOut + "\n");
                fw.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LinkedList<Double> getAverage() {
        LinkedList<Double> average = new LinkedList();
        for (int j = 0; j < (int) (c.getIterations() / c.getResolution()); j++) {
            double total = 0.0;
            for (int i = 0; i < c.getSample(); i++) {
                total += samples.get(i).get(j);
            }
            average.add(total / c.getSample());
        }
        return average;
    }
}
