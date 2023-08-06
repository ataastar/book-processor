package hu.astar;

import org.junit.jupiter.api.Test;

class ProcessorByCharacterTest {

  @Test
  public void processTest() {
    ProcessorByCharacter processor = new ProcessorByCharacter();
    String s = "The Texan\n\r";
    s +=" It was love at first sight.\n\r";
    s +="2\n\r";
    s +=" ‘The first time Yossarian saw the chaplain he fell madly in love with him?’\n\r";
    s +=" ‘Yossarian,’ was in the hospital with a pain in his liver that fell just short of being jaundice.\n\r";
    s +=" Yossarian was in the hospital with a pain in his liver that fell just short of being jaundice. valami\n\r";
    s +="The doctors were puzzled by the fact that it wasn’t quite jaundice! If it became jaundice they\n\r";
    s +="could treat it. If it didn’t become jaundice and went away they could discharge him. But this\n\r";
    s +="just being short of jaundice all A.B.C. the time confused them.\n\r";
    s +="clear, stoppered jar on the floor. When the jar on the floor was full, the jar feeding his elbow\n\r";
    s +="was empty, and the two were simply switched quickly so that the stuff could drip back into\n\r";
    s +="dfass.";
    for (char c : s.toCharArray()) {
      processor.detectSentences(c);
    }

    //Collections.sort(processor.sentences);
    for (String sentence : processor.sentences) {
      System.out.println(sentence);
    }

  }
}
