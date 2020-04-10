/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BooleanIR;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Khizer Mehmood
 */
public class Demo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here

//         Tokenizer t1 = new Tokenizer();
//        String s1;
//        s1 = new String(Files.readAllBytes(Paths.get("BIR/ShortStories/35.txt")));
//        s1 = t1.Tokenization(s1);                      // story 4
//        System.out.println(s1);
        ArrayList<String> collection = new ArrayList<String>();
        FetchData fd = new FetchData();
        InvertedIndex i_index = new InvertedIndex();
        PositionalIndex p_index = new PositionalIndex();
        StopWords stop = new StopWords();
        QueryProcess query = new QueryProcess();

        collection = fd.FetchDocuments();
        i_index = i_index.BuildInvertedIndex(stop, collection);
        p_index = p_index.BuildPositionalIndex(stop, collection);

        File i_file = new File("BIR/Inverted-Index.txt");
        if (!i_file.exists()) {
            i_index.WriteInvertedIndexToFile();
        }

        File p_file = new File("BIR/Positional-Index.txt");
        if (!p_file.exists()) {
            p_index.WritePositionalIndexToFile();
        }

        System.out.println("Enter:\tB for Boolean Query\tP for Proximity Query:");
        Scanner s = new Scanner(System.in);
        char c = s.next().charAt(0);

        if (c == 'B' || c == 'b') {
            String test;
            System.out.println("Enter Boolean Query: ");  //example : king AND queen
            Scanner sc = new Scanner(System.in);
            test = sc.nextLine();
            ArrayList<Integer> result = query.BooleanQuery(test, i_index);
            System.out.println("\nBoolean Query Result: ");
            System.out.println(result.size() + " Documents Retrieved : " + result);
        } else if (c == 'P' || c == 'p') {

            System.out.println("Enter Proximity Query: ");  // example : 'anton AND chekhov /1'
            Scanner sc = new Scanner(System.in);
            String temp = sc.nextLine();
            ArrayList<Integer> result = query.ProximityQuery(temp, collection);
            System.out.println("Proximity Query Result: ");
            System.out.println(result.size() + " Documents Retrieved : " + result);
        }

    }
}
