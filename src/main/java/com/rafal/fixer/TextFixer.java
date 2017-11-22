package com.rafal.fixer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextFixer {

    private Stream<String> fileStream;
    private HashMap<String, String> replaceMap;
    private String fixedText;

    public TextFixer() {
        replaceMap = new HashMap<>();
        replaceMap.put("¹", "ą");
        replaceMap.put("ê", "ę");
        replaceMap.put("³", "ł");
        replaceMap.put("¿", "ż");
        replaceMap.put("Ÿ", "ź");
        replaceMap.put("æ", "ć");
        replaceMap.put("œ", "ś");
    }

    public void openFile(String absolutePath) throws IOException {
        File file = new File(absolutePath);
        fileStream = Files.lines(Paths.get(file.getPath()));
    }

    public void fixeText() throws UncheckedIOException {
        fixedText = fileStream.map(this::replaceChars).collect(Collectors.joining());
    }

    public void saveFixedFile(String absolutePath) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(absolutePath);
        out.print(fixedText);
        out.close();
    }

    private String replaceChars(String text) {
        for (Map.Entry entry : replaceMap.entrySet()) {
            text = text.replaceAll(entry.getKey().toString(), entry.getValue().toString());
        }
        return text + "\n";
    }
}
