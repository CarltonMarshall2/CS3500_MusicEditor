package cs3500.music.view;

import cs3500.music.model.IMusicEditor;
import cs3500.music.model.MusicEditor;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

/**
 * Created by spencerdodd on 4/6/16.
 */
public class CompositeView implements GuiView {

  IMusicEditor model;
  GuiView midiView;
  GuiView guiView;

  public CompositeView(IMusicEditor model) {

    this.model = model;
    this.midiView = new MidiViewImpl(model);
    this.guiView = new GuiViewFrame(model);

  }



  @Override
  public void play() {

    this.midiView.play();
    this.guiView.play();

  }

  @Override
  public void playOrPause() {

    System.out.println("Toggling Play or Pause");


    this.midiView.playOrPause();
    this.guiView.playOrPause();

  }

  @Override
  public void addNewNote() {

    this.midiView.addNewNote();
    this.guiView.addNewNote();

  }

  @Override
  public void moveExistingNote() {
    this.midiView.moveExistingNote();
    this.guiView.moveExistingNote();
  }

  @Override
  public void removeExistingNote() {
    this.midiView.removeExistingNote();
    this.guiView.removeExistingNote();
  }

  @Override
  public void goToStart() {

  }

  @Override
  public void goToEnd() {

  }

  @Override
  public boolean clipIsOver() {
    return midiView.clipIsOver();
  }

  @Override
  public void addKeyListener(KeyListener listener) {
    this.guiView.addKeyListener(listener);
  }

  @Override
  public void addActionListener(ActionListener listener) {

  }

}
