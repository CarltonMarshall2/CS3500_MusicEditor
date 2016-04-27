package cs3500.music.test;

import cs3500.music.model.ChromaticTone;
import cs3500.music.model.MidiClip;
import cs3500.music.model.Note;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link cs3500.music.model.MidiClip}
 */
public class MidiClipTests {

  Note testNote1 = new Note(0, ChromaticTone.A0, 4, 1, 100);
  Note testNote1a = new Note(0, ChromaticTone.A0, 2, 1, 100);
  Note testNote2 = new Note(0, ChromaticTone.A0, 10, 1, 100);
  Note testNote3 = new Note(2, ChromaticTone.A0, 4, 1, 100);

  Note testNoteC4 = new Note(0, ChromaticTone.C4, 4, 1, 100);
  Note testNoteE4 = new Note(0, ChromaticTone.E4, 4, 1, 100);
  Note testNoteG4 = new Note(0, ChromaticTone.G4, 4, 1, 100);

  @Test
  public void testAddNote() {

    MidiClip testClip = new MidiClip();

    testClip.addNote(testNote1);

    assertEquals(1, testClip.getNumberOfNotes());
    assertEquals(4, testClip.getClipLength());

    // adding note that starts at the same time as the current note replaces the current note
    testClip.addNote(testNote1a);

    assertEquals(1, testClip.getNumberOfNotes());
    assertEquals(2, testClip.getClipLength());

    // adding a note that is of the same tone as a current note but starts after the current note
    // and starts in the duration of the current note will cut the length of the current note to
    // the size of the added clip
    MidiClip testClip2 = new MidiClip();

    testClip.addNote(testNote2);
    assertEquals(1, testClip.getNumberOfNotes());
    assertEquals(10, testClip.getClipLength());

    testClip.addNote(testNote3);
    assertEquals(2, testClip.getNumberOfNotes());
    assertEquals(6, testClip.getClipLength());

  }

  @Test
  public void testGetAllTones() {

    MidiClip testClip = new MidiClip();

    testClip.addNote(testNoteC4);
    testClip.addNote(testNoteE4);
    testClip.addNote(testNoteG4);

    ArrayList<ChromaticTone> tones = new ArrayList<>();
    tones.add(ChromaticTone.C4);
    tones.add(ChromaticTone.E4);
    tones.add(ChromaticTone.G4);

    Assert.assertEquals(tones, testClip.getAllTones());

  }

  @Test
  public void testGetAllSortedTones() {

    MidiClip testClip = new MidiClip();

    testClip.addNote(testNoteC4);
    testClip.addNote(testNoteE4);

    ArrayList<ChromaticTone> allTones = new ArrayList<>();
    allTones.add(ChromaticTone.C4);
    allTones.add(ChromaticTone.CSHARP4);
    allTones.add(ChromaticTone.D4);
    allTones.add(ChromaticTone.DSHARP4);
    allTones.add(ChromaticTone.E4);

    Assert.assertEquals(allTones, testClip.getAllSortedTones());

  }

  @Test
  public void testGetBlankGrid() {

    MidiClip testClip = new MidiClip();

    ArrayList<ArrayList<String>> testBlankGrid = testClip.getBlankGrid(3, 3);

    assertEquals(4, testBlankGrid.size());
    assertEquals(4, testBlankGrid.get(0).size());

  }

  @Test
  public void testGetClipState() {

    MidiClip testClip = new MidiClip();

    testClip.addNote(testNoteC4);
    testClip.addNote(testNoteE4);
    testClip.addNote(testNoteG4);

    String clipState = testClip.getClipState();

    // the number of lines is equal to the last beat in the last note that plays in the clip + 1
    // line for the tone labels
    assertEquals(5, clipState.split("\n").length);

    // the number of characters across is equal to the number of tones between the lowest and
    // highest tones + the line number pad
    int numberOfTones = testClip.getAllSortedTones().size();
    int lineNumberPadding = String.format("%s", testClip.getClipLength()).length();
    assertEquals(numberOfTones * 5 + lineNumberPadding, clipState.split("\n")[0].length());

  }

}
