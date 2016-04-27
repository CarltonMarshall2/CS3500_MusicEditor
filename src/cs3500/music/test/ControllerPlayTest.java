package cs3500.music.test;

import cs3500.music.controller.Controller;
import cs3500.music.model.MidiClip;
import cs3500.music.model.MusicEditor;
import cs3500.music.util.MidiBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.CompositeView;
import cs3500.music.view.ConcreteGuiViewPanel;
import cs3500.music.view.GuiView;
import cs3500.music.view.MidiViewImpl;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Controller to run the Model with both Midi and Gui views
 */
public class ControllerPlayTest {

  String file = "/Users/spencerdodd/Documents/School/Year_5_Spring_2016" +
          "/CS3500/Homework/hw06/mystery-2.txt";

  @Test
  public void testSongPlay() {

    try {
      FileReader songFile = new FileReader(file);

      MusicReader reader1 = new MusicReader();
      MidiClip song = reader1.parseFile(songFile, new MidiBuilder());

      MusicEditor testEditor = new MusicEditor();
      testEditor.addClip(song);
      GuiView testCompositeView = new CompositeView(testEditor);

      Controller testController = new Controller(testEditor, testCompositeView);
      testController.start();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

}
