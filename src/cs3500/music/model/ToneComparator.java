package cs3500.music.model;

import java.util.Comparator;

/**
 * Class for comparing tones.
 */
public class ToneComparator implements Comparator<ChromaticTone> {

  /**
   * Allows for the sorting of ChromaticTone by their ranks. Returns > 0 if t1 is higher ranked
   * than t2. Returns < 0 if t1 is lower ranked than t2.
   *
   * @param t1 ChromaticTone to be compared
   * @param t2 ChromaticTone to be compared
   * @return int representing the comparison between the two given ChromaticTone
   */
  @Override
  public int compare(ChromaticTone t1, ChromaticTone t2) {

    return t1.toneRank - t2.toneRank;

  }

}
