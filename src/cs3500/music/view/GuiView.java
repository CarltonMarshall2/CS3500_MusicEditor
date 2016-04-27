package cs3500.music.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

/**
 * Created by spencerdodd on 4/6/16.
 */
public interface GuiView extends View {

  void playOrPause();
  void addNewNote();
  void moveExistingNote();
  void removeExistingNote();
  void goToStart();
  void goToEnd();
  boolean clipIsOver();
  void addKeyListener(KeyListener listener);
  void addActionListener(ActionListener listener);
}
