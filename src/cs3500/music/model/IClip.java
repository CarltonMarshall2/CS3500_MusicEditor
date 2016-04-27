package cs3500.music.model;

/**
 * Interface to represent a clip played by an IMusicEditor
 */
public interface IClip {

  /**
   * Returns a string that shows the clip and all of the notes it contains in a readable CLI format
   *
   * @return String of IClip state
   */
  String getClipState();

  /**
   * Adds a given note to the clips notes. In doing, it also removes overlap of any note that
   * occurs at the same time as the note to be added. If a note is added that starts at the same
   * time as a note currently in the clip, the old note is removed from the clip entirely. If
   * the note that is added has a duration that overlaps with an existing note's start time,
   * remove the existing note.
   *
   * @param note Note to be added
   */
  void addNote(Note note);

  /**
   * Returns the length of the clip as an int representing the number of 1/4 note beats in the clip
   *
   * @return length of the clip
   */
  int getClipLength();

}
