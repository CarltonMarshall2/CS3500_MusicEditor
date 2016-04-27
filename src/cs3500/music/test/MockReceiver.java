package cs3500.music.test;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * Receiver mock for testing
 */
public class MockReceiver implements Receiver {

  int tempo;
  ArrayList<ArrayList> messages = new ArrayList<>();

  MockReceiver(int tempo) {
    this.tempo = tempo;
  }

  @Override
  public void send(MidiMessage message, long timeStamp) {

    byte[] msg = message.getMessage();

    ArrayList messageList = new ArrayList();
    messageList.add(msg[0]);
    messageList.add(msg[1]);
    messageList.add(msg[2]);
    messageList.add(timeStamp);

    messages.add(messageList);

  }

  @Override
  public void close() {
    //
  }

  public String getTxtOutput() {

    String receiverOutput = "tempo " + String.valueOf(tempo) + "\n";

    for (int i = 0; i < messages.size(); i += 2) {

      ArrayList messageStart = messages.get(i);
      ArrayList messageEnd = messages.get(i+1);

      String messageOutputString = "note ";

      String startBeat = String.valueOf((Long) (messageStart.get(3)) / tempo);
      String endBeat = String.valueOf((Long) (messageEnd.get(3)) / tempo);
      String tone = String.valueOf(messageStart.get(1));
      String intensity = String.valueOf(messageStart.get(2));

      messageOutputString += String.valueOf(startBeat) + " " + String.valueOf(endBeat) +
              " " + "1" + " " + String.valueOf(tone) + " " + String.valueOf(intensity);

      messageOutputString += "\n";

      receiverOutput += messageOutputString;
    }

    return receiverOutput;

  }

}
