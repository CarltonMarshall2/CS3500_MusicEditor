package cs3500.music.view;


import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import cs3500.music.model.ChromaticTone;
import cs3500.music.model.IMusicEditor;
import cs3500.music.model.Note;

/**
 * A GUI view panel that draws a representation of the Music Editor on the screen
 */
public class ConcreteGuiViewPanel extends JPanel {
  private IMusicEditor model;

  int WIDTH = 100;
  int HEIGHT = 25;
  int X_MARGIN;
  int Y_MARGIN;

  int currentBeat;
  int length;
  int range;

  List<ChromaticTone> tones;
  HashMap<ChromaticTone, Integer> map;
  List<Note> notes;
  private int maxWidth;

  // the constructor
  ConcreteGuiViewPanel(IMusicEditor model, int width, int height, int X_MARGIN, int Y_MARGIN) {
    this.model = model;
    this.length = model.lengthOfActiveClip() * 4;
    this.range = model.getRangeOfClip();
    this.tones = model.getSortedTones();
    this.map = new HashMap<ChromaticTone, Integer>();
    this.X_MARGIN = X_MARGIN;
    this.Y_MARGIN = Y_MARGIN;
    this.HEIGHT = (int) ((height - (Y_MARGIN * 2)) / tones.size());
    this.WIDTH = (int) ((width - X_MARGIN) / 16.8);
/*    this.HEIGHT = (int) ((height - (Y_MARGIN * 2)) / tones.size());
    this.WIDTH = (int) ((width - X_MARGIN) / range);*/
    this.currentBeat = model.getCurrentBeat();
    //this.currentBeat = 50;
    this.maxWidth = (int) ((width - X_MARGIN) / 16.8);

    this.setPreferredSize(new Dimension(this.model.getRangeOfClip() * 4 * WIDTH, this.getHeight()));


    int counter = tones.size() - 1;
    for (ChromaticTone c : tones) {
      map.put(c, counter);
      counter--;
    }
  }

  /**
   * Paints the various components of the Music Editor onto the JPanel
   *
   * @param g the Graphics class for all graphics contexts that allow the application to draw
   */
  @Override
  public void paintComponent(Graphics g) {
    // Handle the default painting
    super.paintComponent(g);

    // paints the music measures
    Graphics2D g1 = (Graphics2D) g;
    g1.setPaint(Color.black);

    for (int i = 0; i < length; i++) {
      for (int x = 0; x < range; x++) {
        g1.drawRect(((WIDTH * i) + X_MARGIN), ((HEIGHT * x) + Y_MARGIN), WIDTH, HEIGHT);
      }
    }

    // paints the notes
    Graphics2D g2 = (Graphics2D) g;
    notes = model.getAllNotes();

    for (Note n : notes) {
      if (n.getDuration() > 1) {
        g2.setPaint(Color.BLACK);
        g2.fillRect((n.getStartTime() * (WIDTH / 4)) + X_MARGIN,
                Y_MARGIN + map.get(n.getTone()) * HEIGHT,
                WIDTH / 4,
                HEIGHT);

        for (int w = 1; w < n.getDuration(); w++) {
          g2.setPaint(Color.GREEN);
          g2.fillRect(((WIDTH / 4) * n.getStartTime()) + (w * (WIDTH / 4)) + X_MARGIN,
                  Y_MARGIN + map.get(n.getTone()) * HEIGHT,
                  WIDTH / 4,
                  HEIGHT);
        }
      } else {
        for (int w = 1; w < n.getDuration(); w++) {
          g2.setPaint(Color.GREEN);
          g2.fillRect(((WIDTH / 4) * n.getStartTime()) + (w * (WIDTH / 4)) + X_MARGIN,
                  Y_MARGIN + map.get(n.getTone()) * HEIGHT,
                  WIDTH / 4,
                  HEIGHT);
        }
      }
    }

        // paints the length
    Graphics2D g3 = (Graphics2D) g;
    g3.setPaint(Color.black);

    for (int i = 0; i <= length; i += 4) {
      g.drawString(Integer.toString(i * 4), (i * WIDTH) + X_MARGIN, 15);
    }

    // paints the pitches
    Graphics2D g4 = (Graphics2D) g;
    g4.setPaint(Color.black);

    int yCoord = Y_MARGIN + ((HEIGHT * 2) / 3);
    ArrayList<ChromaticTone> sortedTones = model.getSortedTones();
    for (int i = sortedTones.size() - 1; i >= 0; i--) {
      ChromaticTone c = sortedTones.get(i);
      g.drawString(c.toString(), 5, yCoord);
      yCoord += HEIGHT;
    }

    // paints the red indicator bar
    Graphics2D g5 = (Graphics2D) g;
    g5.setStroke(new BasicStroke(3));
    g5.setPaint(Color.red);
    g5.drawLine((currentBeat * WIDTH / 4) + X_MARGIN,
            Y_MARGIN,
            (currentBeat * WIDTH / 4) + X_MARGIN,
            20 + tones.size() * HEIGHT);

  }




/*  public void deleteNoteAtBeat(int noteTone, int noteStartBeat, int noteDuration,
                               int instrumentType, int noteVolume) {
    this.model.addNote(new Note(noteStartBeat, noteTone, instrumentType, 0, 0));
  }*/

  public void deleteNoteAtBeat(int noteTone, int noteStartBeat, int instrumentType) {
    this.model.addNote(new Note(noteStartBeat, ChromaticTone.A0.getToneByRank(noteTone),
            instrumentType, 0, 0));
  }

  public void addNoteAtBeat(int noteTone, int noteStartBeat, int noteDuration,
                            int instrumentType, int noteVolume) {
    this.model.addNote(new Note(noteStartBeat, ChromaticTone.A0.getToneByRank(noteTone),
            noteDuration, instrumentType,
            noteVolume));
  }

  public void setCurrentBeat(int currentBeat) {
    this.currentBeat = currentBeat;
  }

  public int getMaxWidth() {
    return maxWidth;
  }

/*  public void resetRange() {

  }*/
}