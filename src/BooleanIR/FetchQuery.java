/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BooleanIR;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Khizer Mehmood
 */
public class FetchQuery {

    public ArrayList<String> fetchQuery() throws FileNotFoundException {

        ArrayList<String> queries = new ArrayList<>();
        Scanner s = new Scanner(new File("BIR/QueryList.txt"));

        while (s.hasNext()) {
            queries.add(s.nextLine());
        }
        return queries;
    }

}
