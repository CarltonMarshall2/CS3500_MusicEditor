package cs3500.music.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.sound.midi.*;

import cs3500.music.model.*;

/**
 * A MIDI view for our MusicEditors. This converts our MusicEditors and their data into audio
 * playback of the notes contained in the Editors.
 */
public class MidiViewImpl implements GuiView {

  private IMusicEditor model;
  private Synthesizer synth;
  private Receiver receiver;
  private int tempo;
  private Enum playState;

  public MidiViewImpl(IMusicEditor model) {
    try {
      this.model = model;
      this.tempo = model.getTempo();
      this.synth = MidiSystem.getSynthesizer();
      this.receiver = synth.getReceiver();
      this.synth.open();
      this.playState = PlayState.PLAY;
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }

  /**
   * Relevant classes and methods from the javax.sound.midi library:
   * <ul>
   * <li>{@link MidiSystem#getSynthesizer()}</li>
   * <li>{@link Synthesizer}
   * <ul>
   * <li>{@link Synthesizer#open()}</li>
   * <li>{@link Synthesizer#getReceiver()}</li>
   * <li>{@link Synthesizer#getChannels()}</li>
   * </ul>
   * </li>
   * <li>{@link Receiver}
   * <ul>
   * <li>{@link Receiver#send(MidiMessage, long)}</li>
   * <li>{@link Receiver#close()}</li>
   * </ul>
   * </li>
   * <li>{@link MidiMessage}</li>
   * <li>{@link ShortMessage}</li>
   * <li>{@link MidiChannel}
   * <ul>
   * <li>{@link MidiChannel#getProgram()}</li>
   * <li>{@link MidiChannel#programChange(int)}</li>
   * </ul>
   * </li>
   * </ul>
   *
   * @see <a href="https://en.wikipedia.org/wiki/General_MIDI">
   * https://en.wikipedia.org/wiki/General_MIDI
   * </a>
   */

  public void playCurrentNotes() {

    ArrayList<Note> notesToPlay = this.getNotesStartingNow();

    try {

      for (Note n : notesToPlay) {

        playNote(n, n.getInstrument());

      }

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public void playNote(Note note, int channel) throws InvalidMidiDataException,
          InterruptedException {

    System.out.println("Playing note: " + String.valueOf(note.getTone()) + " on channel: " +
            String.valueOf(channel));

    int noteTone = note.getTone().toneRank;
    int noteDuration = tempo * note.getDuration();
    int noteVolume = note.getVolume();

    MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, channel, noteTone, noteVolume);
    MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, channel, noteTone, noteVolume);

    // start playing the note
    receiver.send(start, synth.getMicrosecondPosition());
    receiver.send(stop, synth.getMicrosecondPosition() + noteDuration);
  }

  /**
   * Returns the duration of the longest sustaining note in the given list of notes
   *
   * @param notes ArrayList of notes to be searched
   * @return int duration in beats
   */
  public int getLongestNoteDuration(ArrayList<Note> notes) {

    int longestDuration = 0;

    for (Note n : notes) {

      if (n.getDuration() > longestDuration) {

        longestDuration = n.getDuration();

      }

    }

    return longestDuration;

  }

  /**
   * Returns an ArrayList of the notes that start on the given model's current time
   *
   * @return ArrayList<Note>
   */
  public ArrayList<Note> getNotesStartingNow() {

    return model.getNotesStartingNow();

  }

  @Override
  public void playOrPause() {

    if (this.playState == PlayState.PAUSE) {

      this.playState = PlayState.PLAY;

    } else {

      this.playState = PlayState.PAUSE;

    }

  }

  public void clipOver() {

      receiver.close();

      System.out.println("Clip complete.");

  }

  @Override
  public boolean clipIsOver() {

    if (this.model.getCurrentBeat() < this.model.lengthOfActiveClip()) {

      return false;

    } else {

      return true;

    }

  }

  @Override
  public void addKeyListener(KeyListener listener) {

  }

  @Override
  public void addActionListener(ActionListener listener) {

  }

  @Override
  public void play() {

    if (this.playState == PlayState.PLAY) {

      System.out.println("Current beat: " + model.getCurrentBeat());

      this.playCurrentNotes();

      model.incrementCurrentBeat();

    } else {

      System.out.println("Paused ...");

    }

  }

  @Override
  public void addNewNote() {

  }

  @Override
  public void moveExistingNote() {

  }

  @Override
  public void removeExistingNote() {

  }

  @Override
  public void goToStart() {

  }

  @Override
  public void goToEnd() {

  }

  public void setReceiver(Receiver mockReceiver) {

    this.receiver = mockReceiver;

  }

  public Receiver getReceiver() {

    return receiver;

  }

}