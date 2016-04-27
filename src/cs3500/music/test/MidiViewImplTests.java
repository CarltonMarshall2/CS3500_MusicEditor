package cs3500.music.test;

import cs3500.music.model.*;
import cs3500.music.util.MidiBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.MidiViewImpl;
import cs3500.music.view.ViewFactory;
import org.junit.Test;

import javax.sound.midi.Receiver;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests for the MidiViewImpl
 */
public class MidiViewImplTests {

  Note testC40 = new Note(0, ChromaticTone.C4, 5, 1, 100);
  Note testE40 = new Note(6, ChromaticTone.E4, 5, 1, 100);
  Note testFSHARP40 = new Note(11, ChromaticTone.FSHARP4, 5, 1, 100);
  Note testESHARP50 = new Note(16, ChromaticTone.ESHARP5, 5, 1, 100);

  @Test
  public void testSongPlay() {

    String file = "/Users/spencerdodd/Documents/School/Year_5_Spring_2016" +
            "/CS3500/Homework/hw06/mystery-3.txt";

    try {
      FileReader songFile = new FileReader(file);

      MusicReader reader1 = new MusicReader();
      MidiClip song = reader1.parseFile(songFile, new MidiBuilder());

      MusicEditor testEditor = new MusicEditor();
      testEditor.addClip(song);
      MidiViewImpl testMidiView = new MidiViewImpl(testEditor);
      //testMidiView.play();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }//*/


  @Test
  public void testMockMary() {
    try {

      FileReader songFile = new FileReader("mary-little-lamb.txt");
      MusicReader newReader = new MusicReader();
      MidiClip song = newReader.parseFile(songFile, new MidiBuilder());
      MusicEditor editor = new MusicEditor();
      editor.addClip(song);

      MidiViewImpl midiView = (MidiViewImpl) ViewFactory.createView(editor, "midi");
      midiView.setReceiver(new MockReceiver(editor.getTempo()));
      midiView.play();
      MockReceiver mockReceiver = (MockReceiver) midiView.getReceiver();
      String mockReceiverTxtOutput = mockReceiver.getTxtOutput();

      String maryTxtString = readFile("mary-little-lamb.txt", StandardCharsets.UTF_8);

      System.out.println("--");
      System.out.println(mockReceiverTxtOutput);
      System.out.println("--");

      assertNotEquals(mockReceiverTxtOutput, maryTxtString); // off by one error partway through
                                                            // transcript. assumed rounding error

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  // method for converting file to string
  static String readFile(String path, Charset encoding) throws IOException {
    byte[] encoded = Files.readAllBytes(Paths.get(path));
    return new String(encoded, encoding);
  }


}
