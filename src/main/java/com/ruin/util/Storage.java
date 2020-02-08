package com.ruin.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ruin
 * @date 2020/2/4-11:20
 */
public class Storage {

    public static List<String> souls = new ArrayList<>();

    static {
        FileReader fileReader;
        BufferedReader bufferedReader;
        try {
            fileReader = new FileReader("src/main/resources/soul/name.txt");
            bufferedReader = new BufferedReader(fileReader);
            String str = null;
            while (true) {
                str = bufferedReader.readLine();
                if (str == null)
                    break;
                souls.add(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
