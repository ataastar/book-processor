package hu.astar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class ProcessorByCharacter {

  private StringBuilder currentPart = new StringBuilder();
  protected List<String> sentences = new ArrayList<>();
  private char previousChar;
  private StringBuilder currentLine = new StringBuilder();
  Pattern numbersPattern = Pattern.compile("\\d+");

  public static void main(String[] args) {
    try {
      long start = System.currentTimeMillis();
      ProcessorByCharacter processor = new ProcessorByCharacter();
      try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
        int c;
        while ((c = reader.read()) != -1) {
          processor.detectSentences((char) c);
        }
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

  protected void detectSentences(char c) {
    switch (c) {
      case '.':
        if (Character.isUpperCase(previousChar)) {
          currentPart.append(c);
          break;
        }
        // warning it goes toward
      case '?':
      case '!':
        addToSentence();
        break;
      case (char) 10:
        //System.out.println(currentLine);
        if (containsOnlyNumbers(currentLine.toString().trim())) {
          currentLine = new StringBuilder();
          currentPart.deleteCharAt(currentPart.length() - 1);
          return; // skip the page number, no need to do anything else
        } else {
          currentPart.append(" ");
        }
        currentLine = new StringBuilder();
      case (char) 13:
        return;
      case ' ':
        if (currentLine.length() == 0 && currentPart.length() > 1) { // new paragraph, maybe the current part is a whole sentence
          addToSentence();
        } else {
          currentPart.append(c);
        }
        break;
      case '‘':
        return;
      case '’':
        if (isQuoteEndChar(previousChar)) {
          return;
        }
        // warning it goes toward
      default:
        currentPart.append(c);
    }
    currentLine.append(c);
    previousChar = c;
  }

  private void addToSentence() {
    sentences.add(currentPart.toString().trim());
    currentPart = new StringBuilder();
  }

  private boolean isQuoteEndChar(char c) {
    return c == '.' || c == '!' || c == '?' || c == ',';
  }

  private boolean containsOnlyNumbers(String word) {
    return numbersPattern.matcher(word).matches();
  }
}
