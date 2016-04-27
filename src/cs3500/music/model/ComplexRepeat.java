package cs3500.music.model;

import java.util.ArrayList;

/**
 * Class to represent a Complex Repeat with multiple endings and repeats defined
 */
public class ComplexRepeat implements Repeat {

  ArrayList<SimpleRepeat> repeats;
  ArrayList<Integer> beginningBeatsOfEndings;
  ArrayList<ArrayList<Integer>> endings;
  ArrayList<Boolean> endingPlayed;
  int currentRepeat;

  public ComplexRepeat(ArrayList<SimpleRepeat> repeats,
                       ArrayList<Integer> beginningBeatsOfEndings) {

    this.repeats = repeats;
    this.beginningBeatsOfEndings = beginningBeatsOfEndings;
    this.endings = new ArrayList<>();
    this.currentRepeat = 0;
    this.endingPlayed = new ArrayList<>();

    // populate the endings
    for (int i = 0; i < repeats.size(); i++) {

      ArrayList<Integer> currentEnding = new ArrayList<>();
      currentEnding.add(beginningBeatsOfEndings.get(i)); // add the start of the ending
      currentEnding.add(repeats.get(i).getStartBeat()); // add the end of the ending
      endings.add(currentEnding); // add to endings

      // create an 'endingPlayed' entry = false for each ending
      endingPlayed.add(false);

    }
  }

  @Override
  public String getType() {
    return "Complex";
  }

  /**
   * The meat method for this class. takes in the current beat and updates the info of the repeat,
   * as well as returning the appropriate beat for the clip due to the state of the repeat.
   * @return int new beat for the model
   */
  @Override
  public int getNextBeat(int currentBeat) {

    System.out.println("\nGetting COMPLEX next beat");
    System.out.println("CURRENT REPEAT: " + currentRepeat);
    System.out.println("REPEATS SIZE: " + repeats.size());

    int returnBeat = 1;

    if (currentRepeat < repeats.size()) {

      System.out.println("REPEATS LEFT TO USE");

      SimpleRepeat workingRepeat = repeats.get(currentRepeat);
      ArrayList<Integer> workingEnding = endings.get(currentRepeat);

      // if the repeat initiates on the given beat and it has been repeated less times than it
      // should be repeated
      if (currentBeat == workingRepeat.getStartBeat() &&
              workingRepeat.getCurrentCycle() < workingRepeat.getNumberOfCycles()) {

        System.out.println("REPEATING");

        returnBeat = workingRepeat.getNextBeat(currentBeat);
        // update the cycle count for the current repeat
        workingRepeat.updateCurrentCycle();
        // update this endingPlayed value to true so that it is skipped from now on
        endingPlayed.set(currentRepeat, true);
      }
      // if the repeat has been repeated as many times as it should have been, and the current
      // beat is the beginning of the first ending, skip to the repeat start (skipping the ending)
      // and play the second ending.
      else if (currentBeat == workingEnding.get(0) && endingPlayed.get(currentRepeat)) {

        System.out.println("HITTING WORKING ENDING");

        returnBeat = workingEnding.get(1);

        // update the working repeat to the next repeat
        currentRepeat++;

      }
      // if the current beat is the beginning of any of the previously played endings, skip the
      // previously played ending
      else if (beatIsAtPlayedEndingBeginning(currentBeat)) {

        returnBeat = endOfPreviouslyPlayedEnding(currentBeat);

      }

      else { // all conditions failed

        System.out.println("UPDATING NORMALLY");

        returnBeat = currentBeat += 1;

      }

    }
    else { // if we have finished using all of our repeats

      System.out.println("UPDATING NORMALLY");

      returnBeat = currentBeat += 1;

    }

    return returnBeat;

  }

  @Override
  public ArrayList<ArrayList<Integer>> getRepeatTimings() {
    ArrayList<ArrayList<Integer>> repeatTimings = new ArrayList<>();

    for (SimpleRepeat s : repeats) {

      repeatTimings.add(s.getRepeatTimings().get(0));

    }
    return repeatTimings;
  }

  // returns true if the given beat is the beginning of a previously played ending
  private boolean beatIsAtPlayedEndingBeginning(int currentBeat) {

    boolean atEndingBeginning = false;

    ArrayList<ArrayList<Integer>> playedEnding = new ArrayList<>();

    for (int i = 0; i < endingPlayed.size(); i++) {

      if (endingPlayed.get(i)) {

        playedEnding.add(endings.get(i));

      }

    }

    for (ArrayList<Integer> ending : playedEnding) {

      if (currentBeat == ending.get(0)) {

        atEndingBeginning = true;

      }

    }

    return atEndingBeginning;

  }

  // returns the integer of the beat that is at the end of a previously played ending beginning
  // on the given beat
  private int endOfPreviouslyPlayedEnding(int currentBeat) {

    int endOfEnding = 0;

    ArrayList<ArrayList<Integer>> playedEnding = new ArrayList<>();

    for (int i = 0; i < endingPlayed.size(); i++) {

      if (endingPlayed.get(i)) {

        playedEnding.add(endings.get(i));

      }

    }

    for (ArrayList<Integer> ending : playedEnding) {

      if (currentBeat == ending.get(0)) {

        endOfEnding = ending.get(1);

      }

    }

    return endOfEnding;

  }

}
