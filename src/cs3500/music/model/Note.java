package cs3500.music.model;

/**
 * Class to represent a note to be arranged in a MidiClip and played by a MusicEditorMain.
 */
public class Note {

  private int startTime; // represents the 1/4 beat on which the note starts
  private ChromaticTone tone; // represents the tone at which the note is played
  private int duration; // represents the duration that the note will be played for (in measures)
  private int instrument;
  private int volume;

  /**
   * Constructor for a Note that takes in a tone and duration that characterize the note.
   *
   * @param tone Tone at which the note is to be played
   * @param duration Length of time for which the note is to be played (in measures)
   */
  public Note(int startTime, ChromaticTone tone, int duration, int instrument, int volume) {

    this.startTime = startTime;
    this.tone = tone;
    this.duration = duration;
    this.instrument = instrument;
    this.volume = volume;

  }

  /**
   * Outputs the tone of this note.
   * @return ChromaticTone
   */
  public ChromaticTone getTone() {

    return tone;

  }

  /**
   * Outputs the start time of the note
   * @return int representing the beat on which the note starts
   */
  public int getStartTime() {

    return startTime;

  }

  /**
   * Outputs the duration of the note
   *
   * @return int representing the duration of the note in beats
   */
  public int getDuration() {

    return duration;

  }

  /**
   * Outputs the Instrument of the note
   *
   * @return Instrument representing the instrument the note is played on
   */
  public int getInstrument() {

    return instrument;

  }

  /**
   * Outputs the volume of the note
   *
   * @return int representing the volume of the note
   */
  public int getVolume() {

    return volume;

  }

  /**
   * Returns true if the note should be playing (either starting or sustaining) on the given beat
   *
   * @param currentMeasure measure to check if the note is playing on
   * @return boolean representing playing state of the note
   */
  public boolean isSustaining(int currentMeasure) {

    return (currentMeasure >= startTime) && (currentMeasure < (startTime + duration));

  }

  /**
   * Changes the duration of the note to the given duration
   *
   * @param newDuration int representing the duration this note's duration should be changed to
   *                    in beats
   */
  public void alterDuration(int newDuration) {

    duration = newDuration;

  }

}
