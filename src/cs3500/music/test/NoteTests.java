package cs3500.music.test;

import cs3500.music.model.ChromaticTone;
import cs3500.music.model.Note;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the Note class.
 */
public class NoteTests {

  Note testNote1 = new Note(0, ChromaticTone.A0, 2, 1, 100);

  @Test
  public void getToneTest() {

    assertEquals(ChromaticTone.A0, testNote1.getTone());

  }

  @Test
  public void getStartTimeTest() {

    assertEquals(testNote1.getStartTime(), 0);

  }

  @Test
  public void getDurationTest() {

    assertEquals(testNote1.getDuration(), 2);

  }

  @Test
  public void isSustainingTest() {

    int currentMeasure = 0;

    assertTrue(testNote1.isSustaining(currentMeasure));

    currentMeasure++;

    assertTrue(testNote1.isSustaining(currentMeasure));

    currentMeasure++;

    assertFalse(testNote1.isSustaining(currentMeasure));

  }

  @Test
  public void alterDurationTest() {

    assertEquals(2, testNote1.getDuration());

    testNote1.alterDuration(5);

    assertEquals(5, testNote1.getDuration());

  }

}
