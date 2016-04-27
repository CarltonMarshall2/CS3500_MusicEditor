package cs3500.music.test;

import cs3500.music.model.ChromaticTone;
import cs3500.music.model.ToneComparator;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link cs3500.music.model.ToneComparator}
 */
public class ToneComparatorTests {

  @Test
  public void testCompare() {

    ToneComparator testComparator = new ToneComparator();

    assertTrue(testComparator.compare(ChromaticTone.A1, ChromaticTone.A2) < 0);
    assertTrue(testComparator.compare(ChromaticTone.A4, ChromaticTone.A2) > 0);
    assertTrue(testComparator.compare(ChromaticTone.A4, ChromaticTone.A4) == 0);

  }

}
