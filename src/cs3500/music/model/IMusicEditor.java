package cs3500.music.model;

import java.util.ArrayList;

/**
 * Interface to represent a music editor.
 */
public interface IMusicEditor {

  /**
   * This method should play the MidiClip that is currently active in the MusicEditorMain.
   * This method
   * uses the currentTime that is stored in the MidiClip and is simply a toggle for output.
   */
  void playOrPause();

  /**
   * Adds a note to the MusicEditorMain's clip at the given time (in 1/4 note beats) If this note
   * overlaps with an existing note, the note to be added should overwrite the previous note and
   * erase any of the existing note's duration that goes beyond the start of the note to be added.
   *
   * If there is a note being added but the currentlyActiveCLip doesn't exist, a new clip is
   * created and the note is added to that clip.
   *
   * @param note represents a Note to be added to the editor
   */
  void addNote(Note note);

  /**
   * Returns a string that shows the clip and all of the notes it contains in a readable CLI format
   * delineated by quarter measure intervals
   *
   * @return String of MidiClip
   */
  String getClipState();

  /**
   * Prints out a current representation of the currently active MidiClip.
   */
  void printClip();

  /**
   * Returns the length of the currently active clip in beats
   *
   * @return int representing the length of the active clip
   */
  int lengthOfActiveClip();

  /**
   * Returns the tempo of the song in bpm
   *
   * @return int representing tempo
   */
  int getTempo();

  /**
   * Returns a list of the notes that are starting on the current time in the active clip
   *
   * @return ArrayList<Note>
   */
  ArrayList<Note> getNotesStartingNow();

  /**
   * Increases the current time in the active clip by one
   */
  void incrementCurrentBeat();

  /**
   * Gets the current time in the clip
   *
   * @return the current time in the clip
   */
  int getCurrentBeat();

  /**
   * Gets an int representing the range of the highest and lowest tones in the active clip
   *
   * @return an int representing the range of the highest and lowest tones in the active clip
   */
  int getRangeOfClip();

  /**
   * Returns a list of all of the tones in between the lowest tone in the active clip and the
   * highest tone in the clip.
   *
   * @return ArrayList containing the ChromaticTone range in the active clip
   */
  ArrayList<ChromaticTone> getSortedTones();

  /**
   * Gets an ArrayList<Note></Note> of all the notes in the active clip
   *
   * @return an ArrayList<Note></Note> of all the notes in the active clip
   */
  ArrayList<Note> getAllNotes();
}
