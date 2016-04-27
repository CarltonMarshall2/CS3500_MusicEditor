package cs3500.music.model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Music editor that edits and plays MidiClips
 */
public class MusicEditor implements IMusicEditor {

  private ArrayList<MidiClip> clips;
  private int activeClip;
  private PlayState playState;
  private Repeat repeat;

  /**
   * Constructor for a new MusicEditorMain that takes no parameters.
   */
  public MusicEditor() {

    this.clips = new ArrayList<>();
    this.activeClip = 0;
    this.playState = PlayState.PAUSE;
    this.repeat = null;

  }

  ////////////////////////////////////////////////////////////////////// INTERFACE METHODS ////////
  /////////////////////////////////////////////////////////////////////////////////////////////////

  /**
   * Toggles the play state of the editor, changing it from play to pause or vice versa. These
   * states are mutually exclusive and one should be active at all times.
   */
  @Override
  public void playOrPause() {

    if (playState == PlayState.PAUSE) {

      playState = PlayState.PLAY;

    } else {

      playState = PlayState.PAUSE;

    }

  }

  /**
   * Adds a new note at the given time (in measures) to the currently active clip.
   *
   * @param note to be added
   */
  @Override
  public void addNote(Note note) {

    if (activeClip < clips.size()) {

      clips.get(activeClip).addNote(note);

    } else {

      throw new IllegalArgumentException("Cannot add note. Current active clip does not exist");

    }

  }

  /**
   * Returns a string of a current representation of the currently active MidiClip.
   */
  @Override
  public String getClipState() {

    return clips.get(activeClip).getClipState();

  }

  /**
   * Prints out a current representation of the currently active MidiClip.
   */
  @Override
  public void printClip() {

    System.out.println(clips.get(activeClip).getClipState());

  }

  ////////////////////////////////////////////////////////////////////////// CLASS METHODS ////////
  /////////////////////////////////////////////////////////////////////////////////////////////////

  /**
   * Adds a given MidiClip to the editor's clips.
   *
   * @param clip MidiClip to add
   */
  public void addClip(MidiClip clip) {

    clips.add(clip);

  }

  /**
   * Returns the length of the currently active clip in beats
   *
   * @return int representing the length of the active clip
   */
  public int lengthOfActiveClip() {

    return clips.get(activeClip).getClipLength();

  }

  /**
   * Returns the play state of the editor
   *
   * @return boolean representing the play state of the editor
   */
  public PlayState getPlayState() {

    return playState;

  }

  /**
   * Get the tempo of the active clip
   *
   * @return the tempo of the active clip
   */
  public int getTempo() {

    return clips.get(activeClip).getTempo();

  }

  /**
   * Returns an ArrayList<Note></Note> of the notes starting at the current time until the end of
   * the active clip
   *
   * @return an ArrayList<Note></Note> of the notes starting at the current time until the end of
   * the active clip
   */
  public ArrayList<Note> getNotesStartingNow() {

    ArrayList<Note> notesStartingNow = clips.get(activeClip).getNotesStartingNow();

    return notesStartingNow;
  }

  /**
   * Increases the current time in the active clip by one
   * TODO add in repeat implementation here
   */
  public void incrementCurrentBeat() {

    MidiClip currentClip = clips.get(activeClip);
    int currentBeat = currentClip.getCurrentBeat();

    // what to do if there is a repeat and it is actionable on this beat
    if (repeat != null) {

      currentClip.setCurrentBeat(repeat.getNextBeat(currentBeat));

    } else { // otherwise, increment the current beat

      currentClip.setCurrentBeat(currentBeat += 1);
    }

  }

  /**
   * Sets the model's repeat to the given repeat.
   * TODO add in repeat implementation here
   *
   * @param newRepeat the repeat to be added
   */
  @Override
  public void setRepeat(Repeat newRepeat) {

    Objects.requireNonNull(newRepeat);

    repeat = newRepeat;

  }

  /**
   * Gets the current time in the clip
   *
   * @return the current time in the clip
   */
  public int getCurrentBeat() {

    return clips.get(activeClip).getCurrentBeat();

  }

  /**
   * Returns a list of all of the tones in between the lowest tone in the active clip and the
   * highest tone in the clip.
   *
   * @return ArrayList containing the ChromaticTone range in the active clip
   */
  @Override
  public ArrayList<ChromaticTone> getSortedTones() {
    return clips.get(activeClip).getAllSortedTones();
  }

  /**
   * Gets an ArrayList<Note></Note> of all the notes in the active clip
   *
   * @return an ArrayList<Note></Note> of all the notes in the active clip
   */
  @Override
  public ArrayList<Note> getAllNotes() {
    return clips.get(activeClip).getNotes();
  }

  /**
   * Gets an int representing the range of the highest and lowest tones in the active clip
   *
   * @return an int representing the range of the highest and lowest tones in the active clip
   */
  @Override
  public int getRangeOfClip() {

    return clips.get(activeClip).getAllSortedTones().size();

  }

  // TODO
  @Override
  public Repeat getRepeat() {
    return repeat;
  }

}
