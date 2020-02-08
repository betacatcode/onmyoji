package com.ruin.util;

import java.io.*;

/**
 * @author ruin
 * @date 2020/2/4-11:32
 */
public class MyFile {

    public static void main(String[] args) throws IOException {

        String basePath="D:\\御魂";
        String []list=new File(basePath).list();

        BufferedWriter writer=new BufferedWriter(new FileWriter("src/main/resources/soul/name.txt"));
        for(String s:list) {
            System.out.println(s);
            String []ss=s.split("\\.");
            writer.write(ss[0]+"\n");
        }
        writer.close();
    }
}
