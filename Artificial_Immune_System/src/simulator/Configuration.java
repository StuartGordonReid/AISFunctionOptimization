/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simulator;

/**
 *
 * @author stuart
 */
public class Configuration {

    private final int sample, iterations, resolution;
    private final int populationSize;

    Configuration(int s, int i, int r, int p) {
        sample = s;
        iterations = i;
        resolution = r;
        populationSize = p;
    }

    public int getSample() {
        return sample;
    }

    public int getIterations() {
        return iterations;
    }

    public int getResolution() {
        return resolution;
    }

    public int getPopulationSize() {
        return populationSize;
    }
}
