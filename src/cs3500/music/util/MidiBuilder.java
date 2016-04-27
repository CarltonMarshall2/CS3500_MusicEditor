package cs3500.music.util;

/**
 * Class to create MidiClips from txt files
 */

import cs3500.music.model.ChromaticTone;
import cs3500.music.model.MidiClip;
import cs3500.music.model.Note;

import java.util.ArrayList;

/**
 * Builds a MidiClip
 */
public final class MidiBuilder implements CompositionBuilder<MidiClip> {

  int tempo = 0;
  ArrayList<Note> notes = new ArrayList<>();

  /**
   * Adds a new note to the piece
   *
   * @param start      The start time of the note, in beats
   * @param end        The end time of the note, in beats
   * @param instrument The instrument number (to be interpreted by MIDI)
   * @param pitch      The pitch (in the range [0, 127], where 60 represents C4, the middle-C on a
   *                   piano)
   * @param volume     The volume (in the range [0, 127])
   */
  public CompositionBuilder<MidiClip> addNote(int start, int end, int instrument, int pitch, int
          volume) {

    int duration = end - start;
    ChromaticTone tone = ChromaticTone.C0.getToneByRank(pitch);

    Note noteToAdd = new Note(start, tone, duration, instrument - 1, volume);

    notes.add(noteToAdd);

    return this;

  }

  /**
   * Constructs an actual MIDI composition, given the notes that have been added
   *
   * @return The new MIDI composition
   */
  public MidiClip build() {

    MidiClip returnMidi = new MidiClip();

    // add the notes in the builder to the midi clip
    for (Note n : notes) {

      returnMidi.addNote(n);

    }

    // change the midi tempo to the tempo
    returnMidi.setTempo(tempo);

    return returnMidi;

  }

  /**
   * Sets the tempo of the piece
   *
   * @param tempo The speed, in microseconds per beat
   * @return This builder
   */
  public CompositionBuilder<MidiClip> setTempo(int tempo) {

    this.tempo = tempo;

    return this;

  }

}
