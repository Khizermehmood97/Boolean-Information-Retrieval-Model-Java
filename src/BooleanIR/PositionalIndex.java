/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BooleanIR;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Khizer Mehmood
 */
public class PositionalIndex {

    public Map<String, PositionalPosting> PositionalIndexDictionary;

    public PositionalIndex() {
        PositionalIndexDictionary = new HashMap<String, PositionalPosting>();
    }

    public PositionalIndex BuildPositionalIndex(StopWords stop, ArrayList<String> collection) throws FileNotFoundException {
        ArrayList<String> StopWordsList = stop.FetchStopWords();

        for (int i = 0; i < collection.size(); i++) {

            String s1 = stop.RemoveStopWords(collection.get(i), StopWordsList);
            String[] words = s1.split(" ");

            for (int j = 0; j < words.length; j++) {

                if (this.PositionalIndexDictionary.containsKey(words[j])) {

                    PositionalPosting temp = this.PositionalIndexDictionary.get(words[j]);
                    temp.termFrequency++;

                    if (temp.documentNo.contains(i + 1)) {
                        int flag = 0;
                        for (int k = 0; k < temp.documentNo.size(); k++) {
                            if (temp.documentNo.get(k) == i + 1) {
                                temp.termPosition.get(k).add(j + 1);
                                this.PositionalIndexDictionary.put(words[j], temp);
                                flag = 1;
                            }
                            if (flag == 1) {
                                break;
                            }
                        }
                    } else {
                        temp.documentNo.add(i + 1);
                        temp.termPosition.add(new ArrayList<Integer>());
                        temp.termPosition.get(temp.termPosition.size() - 1).add(j + 1);
                        this.PositionalIndexDictionary.put(words[j], temp);
                    }
                } else {

                    PositionalPosting temp = new PositionalPosting();
                    temp.termFrequency++;
                    temp.documentNo.add(i + 1);

                    temp.termPosition.add(new ArrayList<Integer>());
                    temp.termPosition.get(0).add(j + 1);

                    this.PositionalIndexDictionary.put(words[j], temp);
                }
            }

        }
        Map<String, PositionalPosting> temp = new TreeMap<String, PositionalPosting>(PositionalIndexDictionary);
        PositionalIndexDictionary = temp;   // For Sorting of dictionary

        return this;
    }
    
    public void WritePositionalIndexToFile ( ) throws IOException {
        File file = new File("BIR/Positional-Index.txt");
        
        if ( !file.exists() ) {
            file.createNewFile();
        }
            
        BufferedWriter buffer = new BufferedWriter(new FileWriter(file));
    
        for ( Map.Entry<String, PositionalPosting> entry : PositionalIndexDictionary.entrySet() ) {
             
                buffer.write("[Term: " + entry.getKey() + ", Term Frequency: " + entry.getValue().termFrequency);
                buffer.newLine();
                for ( int i=0; i<entry.getValue().documentNo.size(); i++ ) {
                    buffer.write("Doc" + entry.getValue().documentNo.get(i) + ": ");
                    for ( int j=0; j<entry.getValue().termPosition.get(i).size(); j++ ){
                        buffer.write(entry.getValue().termPosition.get(i).get(j) + " ");
                    }
                    buffer.newLine();
                }
                   
                buffer.write("]"); 
                buffer.newLine();
        }
        buffer.flush();
        buffer.close();
    }
}
