package cs3500.music;

import cs3500.music.controller.Controller;
import cs3500.music.model.ComplexRepeat;
import cs3500.music.model.MidiClip;
import cs3500.music.model.Repeat;
import cs3500.music.model.SimpleRepeat;
import cs3500.music.util.MidiBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.GuiView;
import cs3500.music.view.View;
import cs3500.music.view.ViewFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Main
 */
public class MusicEditorMain {

  public static void main(String[] args) {

    String filePath = "";
    String viewType = "";

    int index = 0;

    for (String s : args) {

      if (index == 0) {

        filePath = s;

      } else if (index == 1) {

        viewType = s;

      }

      index++;

    }

    if (filePath == "" && viewType == "") {

      filePath = "mary-little-lamb.txt";
      viewType = "composite";

    }

    // create the model from the file path
    try {

      FileReader songFileData = new FileReader(filePath);

      MusicReader songFileReader = new MusicReader();
      MidiClip song = songFileReader.parseFile(songFileData, new MidiBuilder());
      cs3500.music.model.MusicEditor editor = new cs3500.music.model.MusicEditor();
      editor.addClip(song);

      // TODO Uncomment for test of simple repeats
      /*
      Repeat simpleRepeat = new SimpleRepeat(8, 1);
      editor.setRepeat(simpleRepeat);
      */
      // TODO Uncomment for test of complex repeats
      //
      SimpleRepeat simpleRepeat1 = new SimpleRepeat(24, 0);
      SimpleRepeat simpleRepeat2 = new SimpleRepeat(48, 0);
      ArrayList<SimpleRepeat> simples = new ArrayList<>();
      ArrayList<Integer> endings = new ArrayList<>();
      simples.add(simpleRepeat1);
      endings.add(16);
      simples.add(simpleRepeat2);
      endings.add(40);
      Repeat complexRepeat = new ComplexRepeat(simples, endings);
      editor.setRepeat(complexRepeat);
      //*/



      // implement the view
      View currentView = ViewFactory.createView(editor, viewType);

      // start the show
      if (viewType == "composite") {
        Controller controller = new Controller(editor, (GuiView) currentView);
        controller.start();
      } else {

        currentView.play();

      }

    } catch (FileNotFoundException f) {

      System.out.println("Please enter a valid file location.");

    }

  }
}