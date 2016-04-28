package cs3500.music.view;


import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import cs3500.music.model.ChromaticTone;
import cs3500.music.model.ComplexRepeat;
import cs3500.music.model.IMusicEditor;
import cs3500.music.model.Note;
import cs3500.music.model.SimpleRepeat;

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


    this.setPreferredSize(new Dimension(this.model.getRangeOfClip() * 4 * WIDTH, this.getHeight
            ()));


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
      }
      else {
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

    if (model.getRepeat() == null) {


    }
    else if (model.getRepeat().getType().equals("Simple")) {
      this.drawSimpleRepeat(g);
    }
    else if (model.getRepeat().getType().equals("Complex")) {
      this.drawComplexRepeat(g);
    }
  }

  void drawSimpleRepeat(Graphics g) {

    // draw where the 'open' repeat starts ||:
    Graphics2D g6 = (Graphics2D) g;
    g6.setStroke(new BasicStroke(4));
    g6.setPaint(Color.blue);
    g6.drawLine(((model.getRepeat().getRepeatTimings().get(0).get(1)) * WIDTH / 4) + X_MARGIN,
            Y_MARGIN,
            ((model.getRepeat().getRepeatTimings().get(0).get(1)) * WIDTH / 4) + X_MARGIN,
            ((20 + tones.size()) * HEIGHT));

    // draw line where the 'closed' repeat starts :||
    Graphics2D g7 = (Graphics2D) g;
    g7.setStroke(new BasicStroke(4));
    g7.setPaint(Color.cyan);
    g7.drawLine(((model.getRepeat().getRepeatTimings().get(0).get(0)) * WIDTH / 4) + X_MARGIN,
            Y_MARGIN,
            ((model.getRepeat().getRepeatTimings().get(0).get(0)) * WIDTH / 4) + X_MARGIN,
            ((20 + tones.size()) * HEIGHT));

    //System.out.println(model.getRepeat().getRepeatTimings().get(0).get(0));

  }

  // TODO
  void drawComplexRepeat(Graphics g) {
    Graphics2D g8 = (Graphics2D) g;
    g8.setStroke(new BasicStroke(4));
    g8.setPaint(Color.blue);
    for (int i = 0; i < model.getRepeat().getRepeatTimings().size(); i++) {
      g8.drawLine(((model.getRepeat().getRepeatTimings().get(i).get(1)) * WIDTH / 4) + X_MARGIN,
              Y_MARGIN,
              ((model.getRepeat().getRepeatTimings().get(i).get(1)) * WIDTH / 4) + X_MARGIN,
              ((20 + tones.size()) * HEIGHT));
    }


    Graphics2D g9 = (Graphics2D) g;
    g9.setStroke(new BasicStroke(4));
    g9.setPaint(Color.cyan);
    for (int i = 0; i < model.getRepeat().getRepeatTimings().size(); i++) {
      g9.drawLine(((model.getRepeat().getRepeatTimings().get(i).get(0)) * WIDTH / 4) + X_MARGIN,
              Y_MARGIN,
              ((model.getRepeat().getRepeatTimings().get(i).get(0)) * WIDTH / 4) + X_MARGIN,
              ((20 + tones.size()) * HEIGHT));
    }

    Graphics2D g10 = (Graphics2D) g;
    g10.setStroke(new BasicStroke(4));
    g10.setPaint(Color.yellow);
    for (int i = 0; i < model.getRepeat().getRepeatTimings().size(); i++) {
      g10.drawLine(((model.getRepeat().getRepeatTimings().get(i).get(2)) * WIDTH / 4) + X_MARGIN,
              Y_MARGIN,
              ((model.getRepeat().getRepeatTimings().get(i).get(2)) * WIDTH / 4) + X_MARGIN,
              ((20 + tones.size()) * HEIGHT));
    }

    System.out.println("start beat");
    System.out.println(model.getRepeat().getRepeatTimings().get(0).get(0));
    System.out.println("destination beat");
    System.out.println(model.getRepeat().getRepeatTimings().get(0).get(1));
    System.out.println("endings?");
    System.out.println(model.getRepeat().getRepeatTimings().get(0).get(2));
    System.out.println("real endings?");
    System.out.println(model.getRepeat().getRepeatTimings());
  }

  public void addSimpleRepeat(int clipRepeatStartBeat, int clipRepeatDestinationBeat) {
    this.model.setRepeat(new SimpleRepeat(clipRepeatStartBeat, clipRepeatDestinationBeat));
  }

  // TODO
  public void addComplexRepeat(ArrayList<SimpleRepeat> repeats,
                               ArrayList<Integer> beginningBeatsOfEndings) {
    this.model.setRepeat(new ComplexRepeat(repeats, beginningBeatsOfEndings));
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