package cs3500.music.view;

import cs3500.music.model.IMusicEditor;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.*;


/**
 * A GUI view frame using Swing
 */
public class GuiViewFrame extends javax.swing.JFrame implements GuiView {//implements
// YourViewInterfaceHere {

  private int WIDTH = 1280;
  private int HEIGHT = 720;
  private int X_MARGIN = 70;
  private int Y_MARGIN = 20;
  //private JPanel displayPanel; // You may want to refine this to a subtype of JPanel
  private ConcreteGuiViewPanel displayPanel; // You may want to refine this to a subtype of JPanel
  private IMusicEditor model;
  JScrollPane scrollPane;


  /**
   * Creates new GuiView
   */
  public GuiViewFrame(IMusicEditor model) {
    this.displayPanel = new ConcreteGuiViewPanel(model, WIDTH, HEIGHT, X_MARGIN, Y_MARGIN);
    //this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    //this.getContentPane().add(displayPanel);
    //this.pack();
    this.model = model;

    // TODO fix

    System.out.println(String.valueOf(displayPanel.getPreferredSize().getWidth()));
    System.out.println(String.valueOf(displayPanel.getPreferredSize().getHeight()));

    this.scrollPane = new JScrollPane(this.displayPanel);
    this.scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    this.scrollPane.setViewportView(displayPanel);
    this.scrollPane.getHorizontalScrollBar().setUnitIncrement(10);
    Dimension scrollDimension = new Dimension(WIDTH, HEIGHT);
    this.scrollPane.setPreferredSize(scrollDimension);
    this.getContentPane().add(this.scrollPane, BorderLayout.CENTER); //*/

    this.pack();
    this.setVisible(true);
  }

  /**
   * Gets the Dimension of the JFrame
   *
   * @return the Dimension of the JFrame
   */
  @Override
  public Dimension getPreferredSize() {
    return new Dimension(WIDTH, HEIGHT);
  }

  /**
   * Changes the JFrame height to the given newHeight
   *
   * @param newHeight the new height that the JFrame will be changed to
   */
  public void changeHeight(int newHeight) {

    HEIGHT = newHeight;

  }

  /**
   * Changes the JFrame width to the given newWidth
   *
   * @param newWidth the new width that the JFrame will be changed to
   */
  public void changeWidth(int newWidth) {

    HEIGHT = newWidth;

  }

  @Override
  public void playOrPause() {

  }

  @Override
  public void addNewNote() {
    String tone = JOptionPane.showInputDialog(null, "<html>Enter a Chromatic Tone: ",
            JOptionPane.QUESTION_MESSAGE);
    String startBeat = JOptionPane.showInputDialog(null, "<html>Enter a starting beat: ",
            JOptionPane.QUESTION_MESSAGE);
    String duration = JOptionPane.showInputDialog(null, "<html>Enter a note duration: ",
            JOptionPane.QUESTION_MESSAGE);
    String volume = JOptionPane.showInputDialog(null, "<html>Enter a volume: ",
            JOptionPane.QUESTION_MESSAGE);
    String instrument = JOptionPane.showInputDialog(null, "<html>Enter an instrument number: ",
            JOptionPane.QUESTION_MESSAGE);

    int noteVolume = 0;
    int instrumentType = 0;
    int noteDuration = 0;
    int noteTone = 0;
    int noteStartBeat = 0;

    try {
      noteVolume = Integer.parseInt(volume);
      instrumentType = Integer.parseInt(instrument);
      noteDuration = Integer.parseInt(duration);
      noteStartBeat = Integer.parseInt(startBeat);
      noteTone = Integer.parseInt(tone);
    } catch (NumberFormatException e) {
      System.out.println("Invalid input");

    }

    this.displayPanel.addNoteAtBeat(noteTone, noteStartBeat, noteDuration, instrumentType,
            noteVolume);
    update();
  }

  @Override
  public void moveExistingNote() {
    this.removeExistingNote();
    this.addNewNote();
  }

  //@Override
  public void removeExistingNote() {
    String tone = JOptionPane.showInputDialog(null, "<html>Enter a Chromatic Tone: ",
            JOptionPane.QUESTION_MESSAGE);
    String startBeat = JOptionPane.showInputDialog(null, "<html>Enter a starting beat: ",
            JOptionPane.QUESTION_MESSAGE);
/*    String duration = JOptionPane.showInputDialog(null, "<html>Enter a note duration: ",
            JOptionPane.QUESTION_MESSAGE);
    String volume = JOptionPane.showInputDialog(null, "<html>Enter a volume: ",
            JOptionPane.QUESTION_MESSAGE);*/
    String instrument = JOptionPane.showInputDialog(null, "<html>Enter an instrument number: ",
            JOptionPane.QUESTION_MESSAGE);

    //int noteVolume = 0;
    int instrumentType = 0;
    //int noteDuration = 0;
    int noteTone = 0;
    int noteStartBeat = 0;

    try {
      //noteVolume = Integer.parseInt(volume);
      instrumentType = Integer.parseInt(instrument);
      //noteDuration = Integer.parseInt(duration);
      noteStartBeat = Integer.parseInt(startBeat);
      noteTone = Integer.parseInt(tone);
    } catch (NumberFormatException e) {
      System.out.println("Invalid input");

    }

    this.displayPanel.deleteNoteAtBeat(noteTone, noteStartBeat, instrumentType);
    update();
  }


  @Override
  public void goToStart() {
    this.scrollPane.getHorizontalScrollBar().setValue(0);
  }

  @Override
  public void goToEnd() {
    this.scrollPane.getHorizontalScrollBar().setValue(this.scrollPane.getHorizontalScrollBar()
            .getMaximum());
  }

  @Override
  public boolean clipIsOver() {
    return false;
  }

  @Override
  public void addActionListener(ActionListener listener) {

  }

  public void play() {

    int beat = this.model.getCurrentBeat();

    this.displayPanel.setCurrentBeat(beat);
    if (this.getWidth() < this.WIDTH) {
      this.scrollRight();
    }
    this.repaint();
  }

  //@Override
  public void update() {
    //this.displayPanel.resetRange();
    this.repaint();
  }

  //@Override
  public void scrollRight() {
    int loc = this.scrollPane.getHorizontalScrollBar().getValue();
    //this.scrollPane.getHorizontalScrollBar().setValue(loc + 200);// TODO UNCOMMENT FOR MOVEMENT
  }

  //@Override
  public void scrollLeft() {
    int loc = this.scrollPane.getHorizontalScrollBar().getValue();

    this.scrollPane.getHorizontalScrollBar().setValue(loc - 200);
  }

  public int getWidth() {

    return (this.model.getCurrentBeat() * WIDTH / 4) + X_MARGIN;

  }

  @Override
  public void removeKeyListener(KeyListener k) {
    this.displayPanel.removeKeyListener(k);
  }

  @Override
  public void removeMouseListener(MouseListener m) {
    this.displayPanel.removeMouseListener(m);
  }

  //@Override
  public void setKeyListener(KeyListener k) {
    this.addKeyListener(k);
  }

  //@Override
  public void setMouseListener(MouseListener m) {
    this.displayPanel.addMouseListener(m);
  }

  // TODO
  @Override
  public void addSimpleRepeat() {
    String repeatStartBeat = JOptionPane.showInputDialog(null, "<html>Enter a simple repeat " +
                    "start beat: ",
            JOptionPane.QUESTION_MESSAGE);
    String repeatDestinationBeat = JOptionPane.showInputDialog(null, "<html>Enter a simple " +
                    "repeat destination beat: ",
            JOptionPane.QUESTION_MESSAGE);

    int clipRepeatStartBeat = 0;
    int clipRepeatDestinationBeat = 0;


    try {
      clipRepeatStartBeat = Integer.parseInt(repeatStartBeat);
      clipRepeatDestinationBeat = Integer.parseInt(repeatDestinationBeat);

    } catch (NumberFormatException e) {
      System.out.println("Invalid input");

    }

    this.displayPanel.addSimpleRepeat(clipRepeatStartBeat, clipRepeatDestinationBeat);
    update();
  }

  // TODO
  @Override
  public void addComplexRepeat() {
  /*String repeatStartBeat = JOptionPane.showInputDialog(null, "<html>Enter a simple repeat " +
                  "start beat: ",
          JOptionPane.QUESTION_MESSAGE);
  String repeatDestinationBeat = JOptionPane.showInputDialog(null, "<html>Enter a simple " +
                  "repeat destination beat: ",
          JOptionPane.QUESTION_MESSAGE);

  int clipRepeatStartBeat = 0;
  int clipRepeatDestinationBeat = 0;


  try {
    clipRepeatStartBeat = Integer.parseInt(repeatStartBeat);
    clipRepeatDestinationBeat = Integer.parseInt(repeatDestinationBeat);

  } catch (NumberFormatException e) {
    System.out.println("Invalid input");

  }

  this.displayPanel.addSimpleRepeat(clipRepeatStartBeat, clipRepeatDestinationBeat);
  update();*/
  }
}