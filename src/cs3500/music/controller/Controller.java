
package cs3500.music.controller;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import cs3500.music.model.IMusicEditor;
import cs3500.music.view.GuiView;

import javax.swing.*;

public class Controller implements KeyListener {

  private IMusicEditor model;
  private GuiView view;
  Map<Character, Runnable> keyTypes;
  Map<Integer, Runnable> keyPresses;
  Map<Integer, Runnable> keyReleases;

  public Controller(IMusicEditor model, GuiView view) {
    this.model = model;
    this.view = view;
    startKeyboardListener();
    startMouseListener();
  }

  private void startMouseListener() {

  }

  public void startKeyboardListener() {

    keyTypes = new HashMap<>();
    keyPresses = new HashMap<>();
    keyReleases = new HashMap<>();

    keyPresses.put(KeyEvent.VK_SPACE, () -> this.view.playOrPause());
    keyPresses.put(KeyEvent.VK_N, () -> this.view.addNewNote());
    keyPresses.put(KeyEvent.VK_M, () -> this.view.moveExistingNote());
    keyPresses.put(KeyEvent.VK_D, () -> this.view.removeExistingNote());
    keyPresses.put(KeyEvent.VK_HOME, () -> this.view.goToStart());
    keyPresses.put(KeyEvent.VK_END, () -> this.view.goToEnd());
    // TODO
    // adds simple repeat
    keyPresses.put(KeyEvent.VK_R, () -> this.view.addSimpleRepeat());
    // adds complex repeat
    keyPresses.put(KeyEvent.VK_T, () -> this.view.addComplexRepeat());

    // TODO
    // adds simple repeat
    keyPresses.put(KeyEvent.VK_R, () -> this.view.addSimpleRepeat());
    // adds complex repeat
    keyPresses.put(KeyEvent.VK_T, () -> this.view.addComplexRepeat());

    this.view.addKeyListener(this);

  }

  // RUN IT
  public void start() {

    while (!this.view.clipIsOver()) {

      int delay = this.model.getTempo() / 1000;

      try {
        Thread.sleep(delay);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      this.view.play();

    }

  }

  @Override
  public void keyTyped(KeyEvent e) {

    System.out.println("KEY PRESSED");

    keyTypes.get(e.getKeyCode()).run();

  }

  @Override
  public void keyPressed(KeyEvent e) {

    System.out.println("KEY PRESSED " + String.valueOf(e));

    keyPresses.get(e.getKeyCode()).run();

  }

  @Override
  public void keyReleased(KeyEvent e) {

    keyReleases.get(e.getKeyCode()).run();

  }

}

