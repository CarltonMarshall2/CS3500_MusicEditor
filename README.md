// Music Editor hw07
// CS3500
// Spencer Dodd and Carlton Marshall

INTRODUCTION
------------------------------------------------------------------------------
We have constructed a music editor in the classic Model-View-
Controller architecture.  The editor encompasses four unique
views: a textual view, a visual view, an audible view and composite view.

The textual view renders the piece of music as a String. This view includes 
a column of numbers representing the beats, a sequence of columns 
representing each pitch and each note-head represented as "  X  " 
and each note-sustain represented as "  |  ".

The visual view renders the piece of music as a GUI.  In this view, higher
pitches are higher vertically, time increases to the right, each row is a 
pitch and beat numbers are marked across the top of the screen. The start
of a note is drawn black and the remaining beats of the note are drawn green.

The audible view renders the piece of music as audible sounds using MIDI.
In this view, each note in a piece of music is played in an audible tone.

The composite view is the full music editor. This view renders both the MIDI
view and GUI view at the same time to simulate a full on music editor. 

OUR DESIGN
------------------------------------------------------------------------------
The core of our design follows the MVC architecture.

Our model directly handles all of the data, logic and rules of our music
editor.  We utilize two interfaces in our model: IMusicEditor (which 
represents a music editor) and IClip (which represents a clip played by
an IMusicEditor). Our ChromaticTone class represents all 154 possible tones,
while the Note class represents a note in a clip.  Our MIDI clip class 
represents a MIDI clip that will contain a score / arrangement of music in it. 
Our Music Editor class represents a Music editor that edits and plays MidiClips.

As stated above, we have implemented four views: a textual view, a visual view, 
audible view and a composite view.  We have a View interface that represents 
the view for the music editor. We also GuiView which is a sub-interface of View,
and all of our views implement this.  

The interface has one method, "play", that runs the view.  Our
ConsoleViewImpl class represents the textual view.  The ConcreteGuiViewPanel
and GuiViewFrame class represent the visual view.  The GuiViewFrame class
represents the JFrame using the Swing library.  Similarly, the ConcreteGuiViewPanel
represents the JPanel using the Swing library.  The MidiViewImpl represents the 
audible view.  This converts our MusicEditors and their data into audio playback
of the notes contained in the Editors.  We also implemented a ViewFactory class
for creating views with a method that takes in a string to describe the view with
which to view the model.  Our main class, "Music Editor" includes our main method 
which serves as the entry point for the application. Enter in the file name
and view type (gui, midi, console, composite) desired to run from the 
command line.

In our util folder, we have our builders and readers.  CompositionBuilder is a 
builder of compositions.  Since we do not know in advance what the name of the 
main type is for a model, we parameterize this builder interface by an unknown 
type. Our MidiBuilder class builds a MidiClip from a file.  Our MusicReader class
is a helper to read music data and construct a music composition from it.

All of our tests are included in the test folder.   

CHANGES TO OUR DESIGN SINCE HW06
------------------------------------------------------------------------------
Aside from minor methods that we added to classes such as MusicEditor or MidiClip,
our biggest change that we made was adding the controller and composite view.
Our controller implements KeyListener which allows the user to interact with
the program through the keyboard. 
Key:
____________________
SPACE - play or pause
N - add new note
M - move existing note
D - remove existing note
Addiitionaly, in the pop-up dialog box, the user may use the mouse to select
"ok" or "cancel."

We also implemented scroll bars into the frame, which allow the user to
scroll throughout the frame and see the full song.


THE NAMES OF THE MYSTERY SONGS
------------------------------------------------------------------------------
mystery 1: Mario Theme Song
mystery 2: 
mystery 3: Portal Soundtrack - Still Alive
