package cs3500.music.view;

import cs3500.music.model.IMusicEditor;

/**
 * Factory for creating views with a method that takes in a string to describe the view with
 * which to view the model.
 */
public class ViewFactory {

  public static View createView(IMusicEditor model, String viewType) throws
          IllegalArgumentException {

    View returnView = new MidiViewImpl(model);

    if (viewType.equals("console")) {

      returnView = new ConsoleViewImpl(model);

    } else if (viewType.equals("midi")) {

      returnView = new MidiViewImpl(model);

    } else if (viewType.equals("gui")) {

      returnView = new GuiViewFrame(model);

    } else if (viewType.equals("composite")) {

      returnView = new CompositeView(model);

    } else {

      throw new IllegalArgumentException("Please enter a valid view option. (" + viewType +
              ") is not a valid option.");

    }

    return returnView;

  }

}
