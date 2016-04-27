package cs3500.music.model;

import java.util.ArrayList;

/**
 * Enum containing the different notes that can be played in a chromatic scale.
 */
public enum ChromaticTone {

  C0("C0", 1),
  CSHARP0("C#0", 2),
  D0("D0", 3),
  DSHARP0("D#0", 4),
  E0("E0", 5),
  ESHARP0("E#0", 6),
  F("F0", 7),
  FSHARP0("F#0", 8),
  G0("G0", 9),
  GSHARP0("G#0", 10),
  A0("A0", 11),
  ASHARP0("A#0", 12),
  B0("B0", 13),
  BSHARP0("B#0", 14),
  C1("C1", 15),
  CSHARP1("C#1", 16),
  D1("D1", 17),
  DSHARP1("D#1", 18),
  E1("E1", 19),
  ESHARP1("E#1", 20),
  F1("F1", 21),
  FSHARP1("F#1", 22),
  G1("G1", 23),
  GSHARP1("G#1", 24),
  A1("A1", 25),
  ASHARP1("A#1", 26),
  B1("B1", 27),
  BSHARP1("B#1", 28),
  C2("C2", 29),
  CSHARP2("C#2", 30),
  D2("D2", 31),
  DSHARP2("D#2", 32),
  E2("E2", 33),
  ESHARP2("E#2", 34),
  F2("F2", 35),
  FSHARP2("F#2", 36),
  G2("G2", 37),
  GSHARP2("G#2", 38),
  A2("A2", 39),
  ASHARP2("A#2", 40),
  B2("B2", 41),
  BSHARP2("B#2", 42),
  C3("C3", 43),
  CSHARP3("C#3", 44),
  D3("D3", 45),
  DSHARP3("D#3", 46),
  E3("E3", 47),
  ESHARP3("E#3", 48),
  F3("F3", 49),
  FSHARP3("F#3", 50),
  G3("G3", 51),
  GSHARP3("G#3", 52),
  A3("A3", 53),
  ASHARP3("A#3", 54),
  B3("B3", 55),
  BSHARP3("B#3", 56),
  C4("C4", 57),
  CSHARP4("C#4", 58),
  D4("D4", 59),
  DSHARP4("D#4", 60),
  E4("E4", 61),
  ESHARP4("E#4", 62),
  F4("F4", 63),
  FSHARP4("F#4", 64),
  G4("G4", 65),
  GSHARP4("G#4", 66),
  A4("A4", 67),
  ASHARP4("A#4", 68),
  B4("B4", 69),
  BSHARP4("B#4", 70),
  C5("C5", 71),
  CSHARP5("C#5", 72),
  D5("D5", 73),
  DSHARP5("D#5", 74),
  E5("E5", 75),
  ESHARP5("E#5", 76),
  F5("F5", 77),
  FSHARP5("F#5", 78),
  G5("G5", 79),
  GSHARP5("G#5", 80),
  A5("A5", 81),
  ASHARP5("A#5", 82),
  B5("B5", 83),
  BSHARP5("B#5", 84),
  C6("C6", 85),
  CSHARP6("C#6", 86),
  D6("D6", 87),
  DSHARP6("D#6", 88),
  E6("E6", 89),
  ESHARP6("E#6", 90),
  F6("F6", 91),
  FSHARP6("F#6", 92),
  G6("G6", 93),
  GSHARP6("G#6", 94),
  A6("A6", 95),
  ASHARP6("A#6", 96),
  B6("B6", 97),
  BSHARP6("B#6", 98),
  C7("C7", 99),
  CSHARP7("C#7", 100),
  D7("D7", 101),
  DSHARP7("D#7", 102),
  E7("E7", 103),
  ESHARP7("E#7", 104),
  F7("F7", 105),
  FSHARP7("F#7", 106),
  G7("G7", 107),
  GSHARP7("G#7", 108),
  A7("A7", 109),
  ASHARP7("A#7", 110),
  B7("B7", 111),
  BSHARP7("B#7", 112),
  C8("C8", 113),
  CSHARP8("C#8", 114),
  D8("D8", 115),
  DSHARP8("D#8", 116),
  E8("E8", 117),
  ESHARP8("E#8", 118),
  F8("F8", 119),
  FSHARP8("F#8", 120),
  G8("G8", 121),
  GSHARP8("G#8", 122),
  A8("A8", 123),
  ASHARP8("A#8", 124),
  B8("B8", 125),
  BSHARP8("B#8", 126),
  C9("C9", 127),
  CSHARP9("C#9", 128),
  D9("D9", 129),
  DSHARP9("D#9", 130),
  E9("E9", 131),
  ESHARP9("E#9", 132),
  F9("F9", 133),
  FSHARP9("F#9", 134),
  G9("G9", 135),
  GSHARP9("G#9", 136),
  A9("A9", 137),
  ASHARP9("A#9", 138),
  B9("B9", 139),
  BSHARP9("B#9", 140),
  C10("C10", 141),
  CSHARP10("C#10", 142),
  D10("D10", 143),
  DSHARP10("D#10", 144),
  E10("E10", 145),
  ESHARP10("E#10", 146),
  F10("F10", 147),
  FSHARP10("F#10", 148),
  G10("G10", 149),
  GSHARP10("G#10", 150),
  A10("A10", 151),
  ASHARP10("A#10", 152),
  B10("B10", 153),
  BSHARP10("B#10", 154);

  public final String toneString;
  public final int toneRank;
  ChromaticTone(String toneString, int toneRank) {
    this.toneString = toneString;
    this.toneRank = toneRank;
  }

  /**
   * Gets the next ChromaticTone
   * @return
   */
  public ChromaticTone getNext() {
    return this.ordinal() < ChromaticTone.values().length - 1
            ? ChromaticTone.values()[this.ordinal() + 1]
            : ChromaticTone.C0;
  }

  /**
   * Returns an ArrayList<ChromaticTone> containing all of the tones between the two given tones
   * inclusively.
   *
   * @param lowTone the lower tone limit
   * @param highTone the upper tone limit
   * @return all tones between the two tones inclusively
   */
  public ArrayList<ChromaticTone> allInRange(ChromaticTone lowTone, ChromaticTone highTone) {

    ArrayList<ChromaticTone> rangeOfTones = new ArrayList<>();

    if (lowTone.toneRank > highTone.toneRank) {

      System.out.println("Low Tone must be entered first");

    } else if (lowTone.toneRank == highTone.toneRank) {

      rangeOfTones.add(lowTone);

      return rangeOfTones;

    }

    ChromaticTone currentTone = lowTone;

    while (!currentTone.equals(highTone)) {

      rangeOfTones.add(currentTone);

      currentTone = currentTone.getNext();

    }

    rangeOfTones.add(highTone);

    return rangeOfTones;

  }

  /**
   * Returns the tone of the given rank
   *
   * @param rank int
   * @return ChromaticTone
   */
  public ChromaticTone getToneByRank(int rank) {

    final int numberOfTones = ChromaticTone.values().length;

    ChromaticTone currentTone = ChromaticTone.C0;

    if (rank > numberOfTones || rank < 1) {

      throw new IllegalArgumentException("Rank must be in range of known tones");

    }

    ChromaticTone returnTone = ChromaticTone.C0;

    for (int i = 0; i < numberOfTones; i++) {

      if (currentTone.toneRank == rank) {

        returnTone = currentTone;

      }

      currentTone = currentTone.getNext();

    }

    return returnTone;

  }

}
