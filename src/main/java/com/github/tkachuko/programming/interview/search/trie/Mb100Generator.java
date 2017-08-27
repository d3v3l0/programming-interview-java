package com.github.tkachuko.programming.interview.search.trie;

import java.io.*;
import java.util.UUID;

public class Mb100Generator {

    public static void main(String[] args) throws IOException {
        long targetSize = 10 * 1024 * 1024;

        int bytesInLine = 36;

        File targetFile = new File("/Users/kelebra/Documents/IdeaProjects/programming-interview-java/src/test/resources/files/10.mb.txt");

        if(!targetFile.exists()) targetFile.createNewFile();

        long lines = 0;

        BufferedWriter writer = new BufferedWriter(new FileWriter(targetFile));

        while (targetSize > (lines * bytesInLine)) {
            writer.write(UUID.randomUUID().toString());
            writer.write("\n");

            lines++;

            if((lines * bytesInLine * 2) % 1000 == 0)
                System.out.println("Bytes read: " + lines * bytesInLine + "/" + targetSize);
        }

        writer.flush();
        writer.close();
    }
}
