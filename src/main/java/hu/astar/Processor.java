package hu.astar;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class Processor {

  private StringBuilder currentPart = new StringBuilder();
  protected List<String> sentences = new ArrayList<>();
  Pattern numbersPattern = Pattern.compile("\\d+");

  public static void main(String[] args) {
    try {
      long start = System.currentTimeMillis();
      List<String> allLines = Files.readAllLines(Paths.get(args[0]));
      Processor processor = new Processor();
      for (String line : allLines) {
        processor.detectSentences(line);
        //System.out.println(line);
      }
      System.out.println(System.currentTimeMillis() - start);
      System.out.println("******************");
      Collections.sort(processor.sentences);
      for (String sentence : processor.sentences) {
        System.out.println(sentence);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  protected void detectSentences(String parameterLine) {
    String line = parameterLine.replaceAll(" ‘", " ");
    line = line.replaceAll(",’", ",").replaceAll("\\?’", "?").replaceAll("\\.’", ".").replaceAll("!’", "!");
    if (!currentPart.isEmpty() && line.length() > 0 && Character.isWhitespace(line.charAt(0))) {
      sentences.add(currentPart.toString().trim());
      currentPart = new StringBuilder();
    }
    if (!currentPart.isEmpty()) {
      currentPart.append(" ");
    }
    for (int i = containsSentenceEnd(line); i >= 0; i = containsSentenceEnd(line)) {
      //while (line.indexOf('.') >= 0) {
      String substring = line.substring(0, i);
      if (isLastCharacterCapital(substring)) {
        currentPart.append(substring).append('.');
        line = line.substring(i + 1);
        continue;
      }
      currentPart.append(substring);
      sentences.add(currentPart.toString().trim());
      currentPart = new StringBuilder();
      line = line.substring(i + 1);
    }
    if (!line.isBlank() && !containsOnlyNumbers(line)) {
      currentPart.append(line);
    }
  }

  private boolean containsOnlyNumbers(String word) {
    return numbersPattern.matcher(word).matches();
  }

  private boolean isLastCharacterCapital(String word) {
    return Character.isUpperCase(word.charAt(word.length() - 1));
  }

  private int containsSentenceEnd(String word) {
    int dotIndexOf = word.indexOf('.');
    int aIndexOf = word.indexOf('!');
    int bIndexOf = word.indexOf('?');
    if (dotIndexOf == -1 && aIndexOf == -1 && bIndexOf == -1) {
      return -1;
    }
    List<Integer> ints = new ArrayList<>();
    ints.add(dotIndexOf);
    ints.add(aIndexOf);
    ints.add(bIndexOf);
    int min = 1000;
    for (Integer anInt : ints) {
      if (anInt != -1 && anInt < min) {
        min = anInt;
      }
    }
    return min;
  }
}
