/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package algorithm;

import java.util.LinkedList;

/**
 *
 * @author stuart
 */
public class Data {

    LinkedList<LinkedList<Datum>> samples;
    LinkedList<Datum> data;

    Data() {
    }
    
    public void addDatum(Datum d) {
        data.add(d);
    }

    public Datum getDatum(int index) {
        return data.get(index);
    }

    public void writeToFile(String ouputFileName) {
    }
}
