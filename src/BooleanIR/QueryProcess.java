/*
 * To change this license header, choose License Headers index1 Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template index1 the editor.
 */
package BooleanIR;

import java.util.ArrayList;

/**
 *
 * @author Khizer Mehmood
 */
public class QueryProcess {

    public ArrayList<Integer> BooleanQuery(String query, InvertedIndex i) {
        ArrayList<Integer> toReturn = new ArrayList<Integer>();
        String[] q = query.split(" ");

        if (q.length > 0) {
            int start = 2;

            if ("NOT".equals(q[0])) {
                toReturn = i.InvertedIndexDictionary.get(q[1]);
                ArrayList<Integer> temp = new ArrayList<Integer>();
                for (int j = 1; j <= 50; j++) {
                    if (!(toReturn.contains(j))) {
                        temp.add(j);
                    }
                }
                toReturn = temp;
                start = 3;

            } else {
                toReturn = i.InvertedIndexDictionary.get(q[0]);
            }

            for (int j = start; j < q.length; j++) {

                if ("AND".equals(q[j - 1])) {
                    if (!("NOT".equals(q[j]))) {
                        toReturn.retainAll(i.InvertedIndexDictionary.get(q[j]));

                    } else {
                        ArrayList<Integer> temp = new ArrayList<Integer>();
                        for (int k = 0; k < 50; k++) {
                            temp.add(j + 1);
                        }
                        if (i.InvertedIndexDictionary.containsKey(j + 1)) {
                            temp.removeAll(i.InvertedIndexDictionary.get(j + 1));
                            toReturn.retainAll(temp);
                            j = j + 1;
                        }
                    }
                } else if ("OR".equals(q[j - 1])) {
                    if (!("NOT".equals(q[j]))) {
                        toReturn.addAll(i.InvertedIndexDictionary.get(q[j]));
                    } else {
                        ArrayList<Integer> temp = new ArrayList<Integer>();
                        for (int k = 0; k < 50; k++) {
                            temp.add(j + 1);
                        }
                        if (i.InvertedIndexDictionary.containsKey(j + 1)) {
                            temp.removeAll(i.InvertedIndexDictionary.get(j + 1));
                            toReturn.addAll(temp);
                            j = j + 1;
                        }
                    }

                }
                j++;
            }
        } else {
            System.out.println("Invalid Query");
        }

        ArrayList<Integer> NoDuplicate = new ArrayList<Integer>();    // Deletes duplicate document numbers from toReturn
        for (int a = 0; a < toReturn.size(); a++) {
            if (!NoDuplicate.contains(toReturn.get(a))) {
                NoDuplicate.add(toReturn.get(a));
            }
        }

        return NoDuplicate;
    }

    public ArrayList<Integer> ProximityQuery(String query, ArrayList<String> collection) {
        String[] terms = query.split(" ");

        ArrayList<Integer> toReturn = new ArrayList<Integer>();

        if (terms.length > 0) {
            String reg = "(.*)" + terms[0];
            for (int i=2; i < terms.length; i++) {
                String termPosition = terms[i+1];
                termPosition = termPosition.substring(1);
                for (int j = 1; j < Integer.parseInt(termPosition); ++j) {
                    reg += "\\s(\\S+)";
                }
                reg = reg + "\\s";  reg = reg +terms[i];  reg = reg + "(.*)";
                ++i;
                i++;
            }

            for (int i = 0; i < collection.size(); ++i) {
                if (collection.get(i).matches(reg)) {
                    toReturn.add(i + 1);
                }
            }
            FindIndex(collection, toReturn, reg, 0);
        }

        return toReturn;
    }

    private void FindIndex(ArrayList<String> collection, ArrayList<Integer> list, String reg, int I) {
        String regexTemp = reg;
        int index = regexTemp.indexOf("(\\S+)");

        if (index != -1) {
            String newStr = regexTemp.substring(0, index) + regexTemp.substring(index + 5, regexTemp.length());

            if (newStr.contains("\\s\\s")) {
                int index1 = newStr.indexOf("\\s\\s");
                newStr = newStr.substring(0, index1) + newStr.substring(index1 + 2, newStr.length());
            }

            for (int i = 0; i < collection.size(); i++) {
                if (collection.get(i).matches(newStr)) {
                    list.add(i+1);
                }
            }

            FindIndex(collection, list, newStr, I + 1);
        }
    }

}
