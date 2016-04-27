package cs3500.music.model;

import java.util.ArrayList;

/**
 * Class to represent a Simple repeat
 */
public class SimpleRepeat implements Repeat {

  private int startBeat;
  private int destinationBeat;
  private int numberOfCycles;
  private int currentCycle;

  public SimpleRepeat(int startBeat, int destinationBeat) {

    if (destinationBeat >= startBeat) {

      throw new IllegalArgumentException(
              "Destination beat of repeat cannot be on or after repeat start beat.");
    }

    this.startBeat = startBeat;
    this.destinationBeat = destinationBeat;
    this.numberOfCycles = 1; // standard for simple (one repeat)
    this.currentCycle = 0; // standard for simple (one repeat)

  }

  @Override
  public String getType() {
    return "Simple";
  }

  public int getStartBeat() {
    return startBeat;
  }
  public int getDestinationBeat() {
    return destinationBeat;
  }
  public int getCurrentCycle() {
    return currentCycle;
  }
  public int getNumberOfCycles() {
    return numberOfCycles;
  }
  public void updateCurrentCycle() {
    currentCycle++;
  }

  /**
   * The meat method for this class. takes in the current beat and updates the info of the repeat,
   * as well as returning the appropriate beat for the clip due to the state of the repeat.
   * @return int new beat for the model
   */
  @Override
  public int getNextBeat(int currentBeat) {

    System.out.println("Getting SIMPLE next beat");

    int returnBeat;

    if (currentBeat == startBeat && currentCycle < numberOfCycles) {

      System.out.println("\nREPEATING\n");

      // set the returnBeat to the destination beat of the repeat
      returnBeat = destinationBeat;
      // update the number of cycles
      currentCycle++;

    } else {

      returnBeat = currentBeat += 1;

    }
    System.out.println("DEST BEAT: " + destinationBeat);
    System.out.println("START BEAT: " + startBeat);
    System.out.println("NEW BEAT: " + returnBeat);
    return returnBeat;

  }

  @Override
  public ArrayList<ArrayList<Integer>> getRepeatTimings() {
    ArrayList<ArrayList<Integer>> repeatTimings = new ArrayList<>();
    ArrayList<Integer> currentRepeatTiming = new ArrayList<>();
    currentRepeatTiming.add(startBeat);
    currentRepeatTiming.add(destinationBeat);
    currentRepeatTiming.add(-1);
    repeatTimings.add(currentRepeatTiming);

    return repeatTimings;
  }

}
