/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BooleanIR;

import java.util.ArrayList;

/**
 *
 * @author Khizer Mehmood
 */
public class PositionalPosting {
    public int termFrequency ;
    public ArrayList<Integer> documentNo;
    public ArrayList<ArrayList<Integer>> termPosition;
    
     public PositionalPosting ( ) {
        termFrequency = 0;
        documentNo = new ArrayList<Integer>();
        termPosition = new ArrayList<ArrayList<Integer>>();
    }
    
}
