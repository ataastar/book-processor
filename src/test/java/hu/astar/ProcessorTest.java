package hu.astar;

import org.junit.jupiter.api.Test;

class ProcessorTest {

  @Test
  public void processTest() {
    Processor processor = new Processor();
    processor.detectSentences("The Texan");
    processor.detectSentences(" It was love at first sight.");
    processor.detectSentences("2");
    processor.detectSentences(" ‘The first time Yossarian saw the chaplain he fell madly in love with him?’");
    processor.detectSentences(" ‘Yossarian,’ was in the hospital with a pain in his liver that fell just short of being jaundice.");
    processor.detectSentences(" Yossarian was in the hospital with a pain in his liver that fell just short of being jaundice. valami");
    processor.detectSentences("The doctors were puzzled by the fact that it wasn’t quite jaundice! If it became jaundice they");
    processor.detectSentences("could treat it. If it didn’t become jaundice and went away they could discharge him. But this");
    processor.detectSentences("just being short of jaundice all A.B.C. the time confused them.");
    processor.detectSentences("clear, stoppered jar on the floor. When the jar on the floor was full, the jar feeding his elbow");
    processor.detectSentences("was empty, and the two were simply switched quickly so that the stuff could drip back into");
    processor.detectSentences("dfass.");
    //Collections.sort(processor.sentences);
    for (String sentence : processor.sentences) {
      System.out.println(sentence);
    }

  }
}
