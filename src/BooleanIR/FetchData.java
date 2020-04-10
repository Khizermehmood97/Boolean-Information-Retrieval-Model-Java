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
public class FetchData {

    public ArrayList<String> FetchDocuments() throws FileNotFoundException, IOException {
        String s1;
        Tokenizer t1 = new Tokenizer();
        ArrayList<String> collection = new ArrayList<String>();

        for (int i = 1; i <= 3; i++) {
            String path = "BIR/ShortStories/" + i + ".txt";     // stories from 1-3
            collection.add(FetchAndTokenize(new File(path)));
        }

        s1 = new String(Files.readAllBytes(Paths.get("BIR/ShortStories/4.txt")));
        s1 = t1.Tokenization(s1);                      // story 4
        collection.add(s1);

        for (int i = 5; i <= 29; i++) {
            String path = "BIR/ShortStories/" + i + ".txt";    // stories from 5-29
            collection.add(FetchAndTokenize(new File(path)));
        }

        s1 = new String(Files.readAllBytes(Paths.get("BIR/ShortStories/30.txt")));
        s1 = t1.Tokenization(s1);                   // story 30
        collection.add(s1);

        s1 = new String(Files.readAllBytes(Paths.get("BIR/ShortStories/31.txt")));
        s1 = t1.Tokenization(s1);                 // story 31
        collection.add(s1);

        for (int i = 32; i <= 45; i++) {
            String path = "BIR/ShortStories/" + i + ".txt";    // stories from 32-45
            collection.add(FetchAndTokenize(new File(path)));
        }
        s1 = new String(Files.readAllBytes(Paths.get("BIR/ShortStories/46.txt")));
        s1 = t1.Tokenization(s1);                  // story 46
        collection.add(s1);

        for (int i = 47; i <= 50; i++) {
            String path = "BIR/ShortStories/" + i + ".txt";   // stories from 47-50
            collection.add(FetchAndTokenize(new File(path)));
        }

        return collection;
        //   System.out.println(collection);

    }

    public String FetchAndTokenize(File file) throws FileNotFoundException {
        String temp = "";
        Scanner s = new Scanner(file);
        Tokenizer t = new Tokenizer();

        temp = s.useDelimiter("\\A").next();

        temp = t.Tokenization(temp);

        return temp;
    }

}
