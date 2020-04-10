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
public class StopWords {

    public String RemoveStopWords(String text, ArrayList<String> list) {
        String temp = "";
        String[] s = text.split(" ");
        for (int i = 0; i < s.length; i++) {
            if (!(list.contains(s[i]))) {  // check stopwords from stopwords list to given document
                temp = temp + s[i] + " "; // append only non stopwords to the returned string
            }
        }
        return temp;
    }

    public ArrayList<String> FetchStopWords() throws FileNotFoundException {
       
        ArrayList<String> words = new ArrayList<>();
        Scanner s = new Scanner(new File("BIR/Stopword-List.txt"));

        while (s.hasNext()) {
            words.add(s.nextLine());
        }
        return words;
    }
}
