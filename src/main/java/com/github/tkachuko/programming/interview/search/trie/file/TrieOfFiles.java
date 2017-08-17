package com.github.tkachuko.programming.interview.search.trie.file;

import com.github.tkachuko.programming.interview.search.trie.common.TrieSpec;
import com.github.tkachuko.programming.interview.search.trie.memory.Trie;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class TrieOfFiles implements TrieSpec {

    private static Trie LAST_USED_TRIE = null;
    private static char LAST_USED_KEY = '\u0000';

    private static final String SERIALIZED_TRIE_NAME_PATTERN = "%s.trie.bin";

    private final String baseDirectoryPath;

    public TrieOfFiles(String baseDirectoryPath) {
        this.baseDirectoryPath = baseDirectoryPath;
    }

    @Override
    public void insert(String word) {
        char key = word.charAt(0);

        Trie trie = readOrCreateTrie(key);
        trie.insert(word);
        writeTrie(key, trie);
    }

    @Override
    public boolean contains(String word) {
        char key = word.charAt(0);

        Trie trie = readOrCreateTrie(key);
        return trie.contains(word);
    }

    private Trie readOrCreateTrie(char key) {
        if (key == LAST_USED_KEY) return LAST_USED_TRIE;

        File file = serializedTrieFile(key);

        if (file.exists()) {
            try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file))) {
                Trie trie = (Trie) stream.readObject();
                updateCache(key, trie);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Trie trie = new Trie();
            writeTrie(trie, file);
            updateCache(key, trie);
        }
        return LAST_USED_TRIE;
    }

    private void writeTrie(Trie trie, File file) {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file))) {
            stream.writeObject(trie);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private void writeTrie(char key, Trie trie) {
        if (LAST_USED_KEY != key) {
            try (ObjectOutputStream stream =
                         new ObjectOutputStream(new FileOutputStream(serializedTrieFile(key)))) {
                stream.writeObject(trie);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private File serializedTrieFile(char key) {
        File file = new File(baseDirectoryPath, String.format(SERIALIZED_TRIE_NAME_PATTERN, key));
        file.deleteOnExit();
        return file;
    }

    private void updateCache(char key, Trie trie) {
        LAST_USED_TRIE = trie;
        LAST_USED_KEY = key;
    }

    public static void main(String[] args) throws IOException {
//        AtomicInteger currentLine = new AtomicInteger(0);
//
//        TrieOfFiles trie = new TrieOfFiles("/tmp/");
//        Files.lines(Paths.get("/Users/kelebra/Downloads/pwned-passwords-1.0.txt"))
//                .peek(l -> {
//                    int i = currentLine.incrementAndGet();
//                    if(i % 1000 == 0) System.out.println("Working on line # " + i);
//                })
//                .forEach(trie::insert);
//        System.out.println(trie.contains("aloha"));

        AtomicInteger currentLine = new AtomicInteger(0);

        Set<Character> alphabet =
                Files.lines(Paths.get("/Users/kelebra/Downloads/pwned-passwords-1.0.txt"))
                        .peek(l -> {
                            int i = currentLine.incrementAndGet();
                            if (i % 100000 == 0) System.out.println("Working on line # " + i);
                        })
                        .flatMap(line -> line.chars().mapToObj(c -> (char)c).distinct())
                        .collect(Collectors.toSet());
        System.out.println(alphabet);
    }
}
