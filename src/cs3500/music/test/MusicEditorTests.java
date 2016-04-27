package cs3500.music.test;

import cs3500.music.model.*;

import java.util.ArrayList;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link cs3500.music.model.MusicEditor}
 */
public class MusicEditorTests {

  @Test
  public void testGetClipState() {

    ArrayList<Note> maryHadALittleLamb = new ArrayList<>();

    Note testG30 = new Note(0, ChromaticTone.G3, 7, 1, 100); maryHadALittleLamb.add(testG30);
    Note testG38 = new Note(8, ChromaticTone.G3, 7, 1, 100); maryHadALittleLamb.add(testG38);
    Note testG316 = new Note(16, ChromaticTone.G3, 8, 1, 100); maryHadALittleLamb.add(testG316);
    Note testG324 = new Note(24, ChromaticTone.G3, 2, 1, 100); maryHadALittleLamb.add(testG324);
    Note testG332 = new Note(32, ChromaticTone.G3, 8, 1, 100); maryHadALittleLamb.add(testG332);
    Note testG340 = new Note(40, ChromaticTone.G3, 8, 1, 100); maryHadALittleLamb.add(testG340);
    Note testG348 = new Note(48, ChromaticTone.G3, 8, 1, 100); maryHadALittleLamb.add(testG348);
    Note testE356 = new Note(56, ChromaticTone.E3, 8, 1, 100); maryHadALittleLamb.add(testE356);

    Note testC44 = new Note(4, ChromaticTone.C4, 2, 1, 100); maryHadALittleLamb.add(testC44);
    Note testC436 = new Note(36, ChromaticTone.C4, 2, 1, 100); maryHadALittleLamb.add(testC436);
    Note testC456 = new Note(56, ChromaticTone.C4, 8, 1, 100); maryHadALittleLamb.add(testC456);

    Note testD42 = new Note(2, ChromaticTone.D4, 2, 1, 100); maryHadALittleLamb.add(testD42);
    Note testD46 = new Note(6, ChromaticTone.D4, 2, 1, 100); maryHadALittleLamb.add(testD46);
    Note testD416 = new Note(16, ChromaticTone.D4, 2, 1, 100); maryHadALittleLamb.add(testD416);
    Note testD418 = new Note(18, ChromaticTone.D4, 2, 1, 100); maryHadALittleLamb.add(testD418);
    Note testD420 = new Note(20, ChromaticTone.D4, 2, 1, 100); maryHadALittleLamb.add(testD420);
    Note testD434 = new Note(34, ChromaticTone.D4, 2, 1, 100); maryHadALittleLamb.add(testD434);
    Note testD438 = new Note(38, ChromaticTone.D4, 2, 1, 100); maryHadALittleLamb.add(testD438);
    Note testD448 = new Note(48, ChromaticTone.D4, 2, 1, 100); maryHadALittleLamb.add(testD448);
    Note testD450 = new Note(50, ChromaticTone.D4, 2, 1, 100); maryHadALittleLamb.add(testD450);
    Note testD454 = new Note(54, ChromaticTone.D4, 2, 1, 100); maryHadALittleLamb.add(testD454);

    Note testE40 = new Note(0, ChromaticTone.E4, 2, 1, 100); maryHadALittleLamb.add(testE40);
    Note testE48 = new Note(8, ChromaticTone.E4, 2, 1, 100); maryHadALittleLamb.add(testE48);
    Note testE410 = new Note(10, ChromaticTone.E4, 2, 1, 100); maryHadALittleLamb.add(testE410);
    Note testE412 = new Note(12, ChromaticTone.E4, 3, 1, 100); maryHadALittleLamb.add(testE412);
    Note testE424 = new Note(24, ChromaticTone.E4, 2, 1, 100); maryHadALittleLamb.add(testE424);
    Note testE432 = new Note(32, ChromaticTone.E4, 2, 1, 100); maryHadALittleLamb.add(testE432);
    Note testE440 = new Note(40, ChromaticTone.E4, 2, 1, 100); maryHadALittleLamb.add(testE440);
    Note testE442 = new Note(42, ChromaticTone.E4, 2, 1, 100); maryHadALittleLamb.add(testE442);
    Note testE444 = new Note(44, ChromaticTone.E4, 2, 1, 100); maryHadALittleLamb.add(testE444);
    Note testE446 = new Note(46, ChromaticTone.E4, 2, 1, 100); maryHadALittleLamb.add(testE446);
    Note testE452 = new Note(52, ChromaticTone.E4, 2, 1, 100); maryHadALittleLamb.add(testE452);

    Note testG426 = new Note(26, ChromaticTone.G4, 2, 1, 100); maryHadALittleLamb.add(testG426);
    Note testG428 = new Note(28, ChromaticTone.G4, 4, 1, 100); maryHadALittleLamb.add(testG428);

    MidiClip testClip = new MidiClip();

    for (Note n : maryHadALittleLamb) {

      testClip.addNote(n);

    }

    MusicEditor testEditor = new MusicEditor();
    testEditor.addClip(testClip);
    assertEquals(testEditor.getClipState().split("\n").length, testEditor.lengthOfActiveClip() + 1);

  }

  @Test
  public void testPlayOrPause() {

    MusicEditor testEditor = new MusicEditor();

    assertEquals(PlayState.PAUSE, testEditor.getPlayState());

    testEditor.playOrPause();

    assertEquals(PlayState.PLAY, testEditor.getPlayState());

    testEditor.playOrPause();

    assertEquals(PlayState.PAUSE, testEditor.getPlayState());

  }

  @Test
  public void testGetLengthOfClip() {

    Note testNote = new Note(0, ChromaticTone.A0, 4, 1, 100);
    MidiClip testClip = new MidiClip();
    testClip.addNote(testNote);
    MusicEditor testEditor = new MusicEditor();
    testEditor.addClip(testClip);

    assertEquals(4, testEditor.lengthOfActiveClip());

  }

  @Test
  public void testPrintClip() {

    ArrayList<Note> maryHadALittleLamb = new ArrayList<>();

    Note testG30 = new Note(0, ChromaticTone.G3, 7, 1, 100); maryHadALittleLamb.add(testG30);
    Note testG38 = new Note(8, ChromaticTone.G3, 7, 1, 100); maryHadALittleLamb.add(testG38);
    Note testG316 = new Note(16, ChromaticTone.G3, 8, 1, 100); maryHadALittleLamb.add(testG316);
    Note testG324 = new Note(24, ChromaticTone.G3, 2, 1, 100); maryHadALittleLamb.add(testG324);
    Note testG332 = new Note(32, ChromaticTone.G3, 8, 1, 100); maryHadALittleLamb.add(testG332);
    Note testG340 = new Note(40, ChromaticTone.G3, 8, 1, 100); maryHadALittleLamb.add(testG340);
    Note testG348 = new Note(48, ChromaticTone.G3, 8, 1, 100); maryHadALittleLamb.add(testG348);
    Note testE356 = new Note(56, ChromaticTone.E3, 8, 1, 100); maryHadALittleLamb.add(testE356);

    Note testC44 = new Note(4, ChromaticTone.C4, 2, 1, 100); maryHadALittleLamb.add(testC44);
    Note testC436 = new Note(36, ChromaticTone.C4, 2, 1, 100); maryHadALittleLamb.add(testC436);
    Note testC456 = new Note(56, ChromaticTone.C4, 8, 1, 100); maryHadALittleLamb.add(testC456);

    Note testD42 = new Note(2, ChromaticTone.D4, 2, 1, 100); maryHadALittleLamb.add(testD42);
    Note testD46 = new Note(6, ChromaticTone.D4, 2, 1, 100); maryHadALittleLamb.add(testD46);
    Note testD416 = new Note(16, ChromaticTone.D4, 2, 1, 100); maryHadALittleLamb.add(testD416);
    Note testD418 = new Note(18, ChromaticTone.D4, 2, 1, 100); maryHadALittleLamb.add(testD418);
    Note testD420 = new Note(20, ChromaticTone.D4, 2, 1, 100); maryHadALittleLamb.add(testD420);
    Note testD434 = new Note(34, ChromaticTone.D4, 2, 1, 100); maryHadALittleLamb.add(testD434);
    Note testD438 = new Note(38, ChromaticTone.D4, 2, 1, 100); maryHadALittleLamb.add(testD438);
    Note testD448 = new Note(48, ChromaticTone.D4, 2, 1, 100); maryHadALittleLamb.add(testD448);
    Note testD450 = new Note(50, ChromaticTone.D4, 2, 1, 100); maryHadALittleLamb.add(testD450);
    Note testD454 = new Note(54, ChromaticTone.D4, 2, 1, 100); maryHadALittleLamb.add(testD454);

    Note testE40 = new Note(0, ChromaticTone.E4, 2, 1, 100); maryHadALittleLamb.add(testE40);
    Note testE48 = new Note(8, ChromaticTone.E4, 2, 1, 100); maryHadALittleLamb.add(testE48);
    Note testE410 = new Note(10, ChromaticTone.E4, 2, 1, 100); maryHadALittleLamb.add(testE410);
    Note testE412 = new Note(12, ChromaticTone.E4, 3, 1, 100); maryHadALittleLamb.add(testE412);
    Note testE424 = new Note(24, ChromaticTone.E4, 2, 1, 100); maryHadALittleLamb.add(testE424);
    Note testE432 = new Note(32, ChromaticTone.E4, 2, 1, 100); maryHadALittleLamb.add(testE432);
    Note testE440 = new Note(40, ChromaticTone.E4, 2, 1, 100); maryHadALittleLamb.add(testE440);
    Note testE442 = new Note(42, ChromaticTone.E4, 2, 1, 100); maryHadALittleLamb.add(testE442);
    Note testE444 = new Note(44, ChromaticTone.E4, 2, 1, 100); maryHadALittleLamb.add(testE444);
    Note testE446 = new Note(46, ChromaticTone.E4, 2, 1, 100); maryHadALittleLamb.add(testE446);
    Note testE452 = new Note(52, ChromaticTone.E4, 2, 1, 100); maryHadALittleLamb.add(testE452);

    Note testG426 = new Note(26, ChromaticTone.G4, 2, 1, 100); maryHadALittleLamb.add(testG426);
    Note testG428 = new Note(28, ChromaticTone.G4, 4, 1, 100); maryHadALittleLamb.add(testG428);

    MidiClip testClip = new MidiClip();

    for (Note n : maryHadALittleLamb) {

      testClip.addNote(n);

    }

    String maryHadALittleLambString = "    E3   E#3  F3   F#3  G3   G#3  A3   A#3  B3 " +
            "  B#3  C4   C#4  D4   D#4  E4   E#4  F4   F#4  G4 \n" +
            "0                       X                                               " +
            "  X                      \n" +
            "1                       |                                              " +
            "   |                      \n" +
            "2                       |                                       X       " +
            "                         \n" +
            "3                       |                                       |       " +
            "                         \n" +
            "4                       |                             X                 " +
            "                         \n" +
            "5                       |                             |                  " +
            "                        \n" +
            "6                       |                                       X          " +
            "                      \n" +
            "7                                                               |          " +
            "                      \n" +
            "8                       X                                                 X" +
            "                      \n" +
            "9                       |                                                 |" +
            "                      \n" +
            "10                      |                                                 X " +
            "" +
            "                     \n" +
            "11                      |                                                 |" +
            "                      \n" +
            "12                      |                                                 X" +
            "                      \n" +
            "13                      |                                                 |" +
            "                      \n" +
            "14                      |                                                 |" +
            "                      \n" +
            "15                                                                         " +
            "                      \n" +
            "16                      X                                       X          " +
            "                      \n" +
            "17                      |                                       |          " +
            "                      \n" +
            "18                      |                                       X          " +
            "                      \n" +
            "19                      |                                       |          " +
            "                      \n" +
            "20                      |                                       X          " +
            "                      \n" +
            "21                      |                                       |           " +
            "                     \n" +
            "22                      |                                                  " +
            "                      \n" +
            "23                      |                                                  " +
            "                      \n" +
            "24                      X                                                 X" +
            "                      \n" +
            "25                      |                                                 |" +
            "                      \n" +
            "26                                                                         " +
            "                   X  \n" +
            "27                                                                         " +
            "                   |  \n" +
            "28                                                                         " +
            "                   X  \n" +
            "29                                                                         " +
            "                   |  \n" +
            "30                                                                         " +
            "                   |  \n" +
            "31                                                                         " +
            "                   |  \n" +
            "32                      X                                                 X" +
            "                      \n" +
            "33                      |                                                 |" +
            "                      \n" +
            "34                      |                                       X          " +
            "                      \n" +
            "35                      |                                       |          " +
            "                      \n" +
            "36                      |                             X                    " +
            "                      \n" +
            "37                      |                             |                    " +
            "                      \n" +
            "38                      |                                       X          " +
            "                      \n" +
            "39                      |                                       |          " +
            "                      \n" +
            "40                      X                                                 X" +
            "                      \n" +
            "41                      |                                                 |" +
            "                      \n" +
            "42                      |                                                 X" +
            "                      \n" +
            "43                      |                                                 |  " +
            "                    \n" +
            "44                      |                                                 X  " +
            "                    \n" +
            "45                      |                                                 |  " +
            "                    \n" +
            "46                      |                                                 X  " +
            "                    \n" +
            "47                      |                                                 |  " +
            "                    \n" +
            "48                      X                                       X            " +
            "                    \n" +
            "49                      |                                       |            " +
            "                    \n" +
            "50                      |                                       X            " +
            "                    \n" +
            "51                      |                                       |            " +
            "                    \n" +
            "52                      |                                                 X  " +
            "                    \n" +
            "53                      |                                                 |  " +
            "                    \n" +
            "54                      |                                       X            " +
            "                    \n" +
            "55                      |                                       |            " +
            "                    \n" +
            "56  X                                                 X                      " +
            "                    \n" +
            "57  |                                                 |                      " +
            "                    \n" +
            "58  |                                                 |                      " +
            "                    \n" +
            "59  |                                                 |                      " +
            "                    \n" +
            "60  |                                                 |                      " +
            "                    \n" +
            "61  |                                                 |                      " +
            "                    \n" +
            "62  |                                                 |                      " +
            "                    \n" +
            "63  |                                                 |                      " +
            "                    \n";

    MusicEditor testEditor = new MusicEditor();
    testEditor.addClip(testClip);
    assertEquals(testEditor.getClipState(), maryHadALittleLambString);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddNoteException() {

    Note testNote = new Note(0, ChromaticTone.A0, 4, 1, 100);
    MusicEditor testEditor = new MusicEditor();

    testEditor.addNote(testNote);

  }

  @Test
  public void testAddNote() {

    Note testNote = new Note(0, ChromaticTone.A0, 4, 1, 100);
    MidiClip testClip = new MidiClip();
    MusicEditor testEditor = new MusicEditor();
    testEditor.addClip(testClip);

    testEditor.addNote(testNote);

    assertEquals(4, testEditor.lengthOfActiveClip());

  }

}
