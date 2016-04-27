package cs3500.music.model;

import cs3500.music.util.CompositionBuilder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
/**
 * Class to represent a MIDI clip that will contain a score / arrangement of music in it.
 */
public class MidiClip implements IClip {

  // represents the current time in beats. I.e. 0 = first beat, 11 = 12th beat
  private int currentTime;
  // represents the notes to be played. The key value represents the time (in measures) at which
  // the notes stored in the value ArrayList<Note> should be played.
  private ArrayList<Note> notes;
  // tempo of the piece in microseconds per beat
  private int tempo;

  /**
   * Constructor for a MidiClip that takes in no parameters.
   */
  public MidiClip() {

    this.currentTime = 0; // initialize MidiClip to the beginning of the clip
    this.notes = new ArrayList<Note>();
    this.tempo = 111600; // stub tempo

  }

  /**
   * Returns a string that shows the clip and all of the notes it contains in a readable CLI format
   * delineated by quarter measure intervals
   *
   * @return String of MidiClip
   */
  @Override
  public String getClipState() {

    String clipState = "";

    ArrayList<ChromaticTone> allSortedTones = this.getAllSortedTones();

    // add a 5 character section for each tone in the clip
    for (ChromaticTone tone : allSortedTones) {

      clipState += "  " + String.format("%-3s", tone.toneString);

    }

    clipState += "\n";

    ArrayList<ArrayList<String>> blankGrid = this.getBlankGrid(
            this.getClipLength(),
            allSortedTones.size() - 1);

    for (int i = 0; i <= this.getClipLength(); i++) {

      for (int j = 0; j < allSortedTones.size(); j++) {

        for (Note n : notes) {

          // if note starts on beat (i) in tone (j), add an X
          if (n.getTone().equals(allSortedTones.get(j)) && n.getStartTime() == i) {

            blankGrid.get(i).set(j, "  X  ");

          } else if (n.getTone().equals(allSortedTones.get(j)) && n.isSustaining(i)) {

            blankGrid.get(i).set(j, "  |  ");

          }

        }

      }

    }

    // creates a string from the arrayList of strings
    for (ArrayList<String> row : blankGrid) {

      for (String s : row) {

        clipState += s;

      }

      clipState += "\n";

    }

    // adds the line numbers to the return string
    clipState = this.addLineNumbers(clipState);

    return clipState;

  }

  /**
   * Adds line numbers to the output string that shows the current state of the MidiClip. The line
   * numbers correspond to a beat in the clip.
   *
   * @param currentClipState String state of the clip without the line numbers
   * @return String state of the clip with line numbers added (measure numbers)
   */
  public String addLineNumbers(String currentClipState) {

    String returnClipState = "";
    int lengthOfLongestLineNumber = String.format("%s", this.getClipLength()).length();

    // add line numbers to the String
    String[] clipLines = currentClipState.split("\n");

    // remove the line of tones
    String tones = clipLines[0];

    for (int i = 1; i < clipLines.length - 1; i++) {

      String currentLine = clipLines[i];

      returnClipState += String.format("%s", i - 1);

      int lengthOfCurrentLineNumber = String.format("%s", i - 1).length();

      // add space padding
      for (int k = 0; k < lengthOfLongestLineNumber - lengthOfCurrentLineNumber; k++) {

        returnClipState += " ";

      }

      returnClipState += currentLine + "\n";

    }

    // get padding for the tone row
    String tonePadding = "";

    for (int l = 0; l < lengthOfLongestLineNumber; l++) {

      tonePadding += " ";

    }

    returnClipState = tonePadding + tones + "\n" + returnClipState;

    return returnClipState;

  }

  /**
   * Returns a list of all of the tones in between the lowest tone in the clip and the highest tone
   * in the clip.
   *
   * @return ArrayList containing the ChromaticTone range in the clip
   */
  public ArrayList<ChromaticTone> getAllSortedTones() {

    // list of all of the tones in the clip in sorted order
    ArrayList<ChromaticTone> sortedTones = this.getAllTones();
    Collections.sort(sortedTones);

    ChromaticTone lowestTone = sortedTones.get(0);
    ChromaticTone highestTone = sortedTones.get(sortedTones.size() - 1);

    return ChromaticTone.C0.allInRange(lowestTone, highestTone);

  }

  /**
   * Returns a 'blank' grid of ArrayList<ArrayList<String>> that is i rows of j size filled with
   * blank strings of 5 characters. This is used as the base for printing the state of the clip.
   *
   * @param i number of rows in the grid
   * @param j number of strings in each row of the grid
   * @return ArrayList<ArrayList<String>> of "     "
   */
  public ArrayList<ArrayList<String>> getBlankGrid(int i, int j) {

    ArrayList<ArrayList<String>> blankGrid = new ArrayList<>();

    for (int k = 0; k <= i; k++) {

      ArrayList<String> currentRow = new ArrayList<>();

      for (int l = 0; l <= j; l++) {

        currentRow.add("     ");

      }

      blankGrid.add(currentRow);

    }

    return blankGrid;

  }

  /**
   * Returns an ArrayList of all the tones in the MidiClip
   *
   * @return an ArrayList of ChromaticTone
   */
  public ArrayList<ChromaticTone> getAllTones() {

    ArrayList<ChromaticTone> allTones = new ArrayList<>();

    for (Note note : notes) {

      ChromaticTone currentTone = note.getTone();

      if (!allTones.contains(currentTone)) {

        allTones.add(currentTone);

      }

    }

    return allTones;

  }

  /**
   * Sets the tempo of the piece to the given int
   *
   * @param tempo The speed, in microseconds per beat
   */
  public void setTempo(int tempo) {

    this.tempo = tempo;

  }

  /**
   * Adds a given note to the clips notes. In doing, it also removes overlap of any note that
   * occurs at the same time as the note to be added. If a note is added that starts at the same
   * time as a note currently in the clip, the old note is removed from the clip entirely. If
   * the note that is added has a duration that overlaps with an existing note's start time,
   * remove the existing note.
   *
   * @param note Note to be added
   */
  @Override
  public void addNote(Note note) {

    ArrayList<Note> notesToRemove = new ArrayList<>();

    for (Note oldNote : notes) {

      // if the note to be added is of the same tone of an existing note, look for conflict
      if (note.getTone().equals(oldNote.getTone())) {

        // if the new note starts at the same time as a currently existing note, remove the existing
        if (note.getStartTime() == oldNote.getStartTime()) {

          notesToRemove.add(oldNote);

        }

        // if the old note's duration overlaps with the current note, remove the old note's
        // duration that overlaps
        if (oldNote.isSustaining(note.getStartTime())) {

          // cut the oldNote duration to the beginning of new note
          oldNote.alterDuration(note.getStartTime());

        }

        // if the new note's duration overlaps with an old note, remove the old note
        if (note.isSustaining(oldNote.getStartTime())) {

          notesToRemove.add(oldNote);

        }

      }

    }

    // remove notes that need to be removed
    for (Note n : notesToRemove) {

      notes.remove(n);

    }

    notes.add(note);

  }

  /**
   * Returns the length of the clip as an int representing the number of 1/4 note beats in the clip
   *
   * @return length of the clip
   */
  @Override
  public int getClipLength() {

    int lastBeatPlayed = 0;

    for (Note note : notes) {

      int endOfNote = note.getStartTime() + note.getDuration();

      if (endOfNote > lastBeatPlayed) {

        lastBeatPlayed = endOfNote;

      }

    }

    return lastBeatPlayed;

  }

  /**
   * Returns the number of notes in the clip
   *
   * @return int of notes
   */
  public int getNumberOfNotes() {

    return notes.size();

  }

  /**
   * Returns an ArrayList<Note></Note> of the notes starting at the current time until the end
   * of the clip
   *
   * @return an ArrayList<Note></Note> of the notes starting at the current time until the end
   * of the clip
   */
  public ArrayList<Note> getNotesStartingNow() {

    ArrayList<Note> notesStartingNow = new ArrayList<>();

    for (Note n : notes) {

      if (n.getStartTime() == currentTime) {

        notesStartingNow.add(n);

      }

    }

    return notesStartingNow;

  }

  /**
   * Gets the current tempo of the clip
   *
   * @return the tempo of the clip
   */
  public int getTempo() {

    return tempo;

  }

  /**
   * Increases the current time in the clip by one
   */
  public void setCurrentBeat(int newBeat) {

    currentTime = newBeat;

  }

  /**
   * Gets the current time in the clip
   *
   * @return the current time in the clip
   */
  public int getCurrentBeat() {

    return this.currentTime;

  }

  /**
   * Gets an ArrayList<Note></Note> of all the notes in the clip
   *
   * @return an ArrayList<Note></Note> of all the notes in the clip
   */
  public ArrayList<Note> getNotes() {

    return notes;

  }

}
