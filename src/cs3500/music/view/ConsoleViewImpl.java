package cs3500.music.view;

import cs3500.music.model.IMusicEditor;

/**
 * View implementation for console output
 */
public class ConsoleViewImpl implements View {

  IMusicEditor model;

  ConsoleViewImpl(IMusicEditor model) {

    this.model = model;

  }

  /**
   * Prints the clip state to the console
   */
  public void play() {

    System.out.print(model.getClipState());

  }

}
