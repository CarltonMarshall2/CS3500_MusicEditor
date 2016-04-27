package cs3500.music.model;

/**
 * Interface for a repeat to be held by the model
 */
public interface Repeat {

  String getType();
  int getNextBeat(int currentBeat);

}
