/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BooleanIR;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Khizer Mehmood
 */
public class InvertedIndex {

    public Map<String, ArrayList<Integer>> InvertedIndexDictionary;

    public InvertedIndex() {
        InvertedIndexDictionary = new HashMap<String, ArrayList<Integer>>();
    }

    public InvertedIndex BuildInvertedIndex(StopWords stop, ArrayList<String> collection) throws FileNotFoundException {
        ArrayList<String> StopWordsList = stop.FetchStopWords();

        for (int i = 0; i < collection.size(); i++) {

            String s1 = stop.RemoveStopWords(collection.get(i), StopWordsList);
            String[] words = s1.split(" ");

            for (int j = 0; j < words.length; j++) {
                if (this.InvertedIndexDictionary.containsKey(words[j])) {

                    ArrayList<Integer> PostingList = this.InvertedIndexDictionary.get(words[j]);
                    PostingList.add(i + 1);
                    this.InvertedIndexDictionary.put(words[j], PostingList);

                } else {

                    ArrayList<Integer> PostingList = new ArrayList<Integer>();
                    PostingList.add(i + 1);
                    this.InvertedIndexDictionary.put(words[j], PostingList);
                }
            }
        }

        this.DeleteDuplicatePostings();

        Map<String, ArrayList<Integer>> temp = new TreeMap<String, ArrayList<Integer>>(InvertedIndexDictionary);
        InvertedIndexDictionary = temp;   // For Sorting of dictionary

        return this;

    }

    private void DeleteDuplicatePostings() {
        for (Map.Entry<String, ArrayList<Integer>> entry : InvertedIndexDictionary.entrySet()) {

            ArrayList<Integer> temp = new ArrayList<Integer>();

            for (int i = 0; i < entry.getValue().size(); i++) {

                if (!temp.contains(entry.getValue().get(i))) {
                    temp.add(entry.getValue().get(i));
                }
            }
            entry.setValue(temp);

            Collections.sort(entry.getValue());  // For sorting of each Posting list
        }
    }


    public void WriteInvertedIndexToFile() throws IOException {
        File file = new File("BIR/Inverted-Index.txt");

        if (!file.exists()) {
            file.createNewFile();
        }

        BufferedWriter buffer = new BufferedWriter(new FileWriter(file));

        for (Map.Entry<String, ArrayList<Integer>> entry : InvertedIndexDictionary.entrySet()) {

            buffer.write(entry.getKey() + "-" + entry.getValue().size() + "  --> ");
            for (int i = 0; i < entry.getValue().size(); ++i) {
                buffer.write(entry.getValue().get(i) + " ");
            }
            buffer.newLine();
        }

        buffer.flush();
        buffer.close();
    }

}