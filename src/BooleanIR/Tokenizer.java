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
public class Tokenizer {

    public String Tokenization(String text) {
        String[] temp = text.split("\n| |-"); // regular expression 
        String tokenized = "";

        for (int i = 0; i < temp.length; i++) {
            temp[i] = temp[i].toLowerCase().replaceAll("[^a-zA-Z0-9]", "");  // converting into lowercase and removing special characters
            tokenized = tokenized + temp[i] + " ";
        }

        return tokenized;
    }

}
