package songs;

/*
 * Music Player
 *
 * This instructor-provided file implements the graphical user interface (GUI)
 * for the Music Player program and allows you to test the behavior
 * of your Song class.
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MusicPlayer implements ActionListener, StdAudio.AudioEventListener {

	// instance variables
	private Song song;
	private boolean playing; // whether a song is currently playing
	private JFrame frame;
	private JFileChooser fileChooser;
	private JTextField tempoText;
	private JSlider currentTimeSlider;

	//these are the two labels that indicate time
	// to the right of the slider
	private JLabel currentTimeLabel;
	private JLabel totalTimeLabel;

	//this the label that says 'welcome to the music player'
	private JLabel statusLabel; 

	//buttons
	private JButton play, stop, load, reverse, up, down, change;

	//panels
	JPanel p1, p2, p3, p4, p5;
	/*
	 * Creates the music player GUI window and graphical components.
	 */
	public MusicPlayer() {
		song = null;
		createComponents();
		doLayout();
		StdAudio.addAudioEventListener(this);


		frame.setVisible(true);
	}

	/*
	 * Called when the user interacts with graphical components, such as
	 * clicking on a button.
	 */
	public void actionPerformed(ActionEvent event) {
		String cmd = event.getActionCommand();

		System.out.println(cmd);
		if (cmd.equals("Play")) {
			playSong();
		} else if (cmd.equals("Pause")) {
			StdAudio.setPaused(!StdAudio.isPaused());
		} else if (cmd == "Stop") {
			StdAudio.setMute(true);
			StdAudio.setPaused(false);
		} else if (cmd == "Load") {
			try {
				loadFile();
			} catch (IOException ioe) {
				System.out.println("not able to load from the file");
			}
		} else if (cmd == "Reverse") {
			song.reverse(); 
		} else if (cmd == "Up") {
			song.octaveUp();
		} else if (cmd == "Down") {
			song.octaveDown();
		} else if (cmd == "Change Tempo") {
			song.changeTempo(Double.parseDouble(tempoText.getText()));
			updateTotalTime();
		}
	}

	/*
	 * Called when audio events occur in the StdAudio library. We use this to
	 * set the displayed current time in the slider.
	 */
	public void onAudioEvent(StdAudio.AudioEvent event) {
		// update current time
		if (event.getType() == StdAudio.AudioEvent.Type.PLAY
				|| event.getType() == StdAudio.AudioEvent.Type.STOP) {
			setCurrentTime(getCurrentTime() + event.getDuration());
		}
	}

	/*
	 * Sets up the graphical components in the window and event listeners.
	 */
	private void createComponents() {
		//TODO - create all your components here.
		
		frame = new JFrame();
		
		fileChooser = new JFileChooser();
		
		tempoText = new JTextField();
		tempoText.setPreferredSize(new Dimension(50, 20));
		tempoText.setHorizontalAlignment(JTextField.CENTER);
		
		currentTimeSlider = new JSlider();

		//labels 
		currentTimeLabel = new JLabel("00000.00 /");
		totalTimeLabel = new JLabel("00000.00 sec");
		statusLabel = new JLabel("Welcome to the music player!", SwingConstants.CENTER); 
		statusLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		statusLabel.setForeground(Color.BLUE);

		//buttons
		play = new JButton("Play");
		play.addActionListener(this);
		stop = new JButton("Stop");
		stop.addActionListener(this);
		load = new JButton("Load");
		load.addActionListener(this);
		reverse = new JButton("Reverse");
		reverse.addActionListener(this);
		up = new JButton("Up");
		up.addActionListener(this);
		down = new JButton("Down");
		down.addActionListener(this);
		change = new JButton("Change Tempo");
		change.addActionListener(this);

		//panels
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p4 = new JPanel();
		p5 = new JPanel();

		doEnabling();
	}

	/*
	 * Sets whether every button, slider, spinner, etc. should be currently
	 * enabled, based on the current state of whether a song has been loaded and
	 * whether or not it is currently playing. This is done to prevent the user
	 * from doing actions at inappropriate times such as clicking play while the
	 * song is already playing, etc.
	 */
	private void doEnabling() {
		//TODO - figure out which buttons need to enabled
		if (playing) {
			fileChooser.setEnabled(false);
			tempoText.setEnabled(false);
			currentTimeSlider.setEnabled(false);
			currentTimeLabel.setEnabled(true);
			totalTimeLabel.setEnabled(true);     
			statusLabel.setEnabled(true); 
			play.setEnabled(false);
			stop.setEnabled(true);
			load.setEnabled(false);
			reverse.setEnabled(false);
			up.setEnabled(false);
			down.setEnabled(false);
			change.setEnabled(false);
		}
		else{
			fileChooser.setEnabled(true);
			tempoText.setEnabled(true);
			currentTimeSlider.setEnabled(true);
			currentTimeLabel.setEnabled(true);
			totalTimeLabel.setEnabled(true);     
			statusLabel.setEnabled(true); 
			play.setEnabled(true);
			stop.setEnabled(true);
			load.setEnabled(true);
			reverse.setEnabled(true);
			up.setEnabled(true);
			down.setEnabled(true);
			change.setEnabled(true);
		}

	}

	/*
	 * Performs layout of the components within the graphical window. 
	 * Also make the window a certain size and put it in the center of the screen.
	 */
	private void doLayout() {
		//TODO - figure out how to layout the components
		frame.setMinimumSize(new Dimension(640, 240));        
		frame.pack();
		frame.setLocationRelativeTo(null);  
		frame.setLayout(new GridLayout(5, 1));

		p1.setLayout(new FlowLayout());
		p1.add(statusLabel);

		p2.setLayout(new FlowLayout());
		p2.add(currentTimeSlider);
		p2.add(currentTimeLabel);
		p2.add(totalTimeLabel);   

		p3.setLayout(new FlowLayout());
		p3.add(play);
		p3.add(stop);
		p3.add(load);

		p4.setLayout(new FlowLayout());
		p4.add(reverse);
		p4.add(up);
		p4.add(down);

		p5.add(tempoText);
		p5.add(change);

		frame.add(p1);
		frame.add(p2);
		frame.add(p3);
		frame.add(p4);
		frame.add(p5);
	}

	/*
	 * Returns the estimated current time within the overall song, in seconds.
	 */
	private double getCurrentTime() {
		String timeStr = currentTimeLabel.getText();
		timeStr = timeStr.replace(" /", "");
		try {
			return Double.parseDouble(timeStr);
		} catch (NumberFormatException nfe) {
			return 0.0;
		}
	}

	/*
	 * Pops up a file-choosing window for the user to select a song file to be
	 * loaded. If the user chooses a file, a Song object is created and used
	 * to represent that song.
	 */
	private void loadFile() throws IOException {
		if (fileChooser.showOpenDialog(frame) != JFileChooser.APPROVE_OPTION) {
			return;
		}
		File selected = fileChooser.getSelectedFile();
		if (selected == null) {
			return;
		}
		statusLabel.setText("Current song: " + selected.getName());
		String filename = selected.getAbsolutePath();
		System.out.println("Loading song from " + selected.getName() + " ...");

		//TODO - create a song from the file
		song = new Song(filename);

		tempoText.setText("1.0");
		setCurrentTime(0.0);
		updateTotalTime();
		System.out.println("Loading complete.");
		System.out.println("Song: " + song);
		doEnabling();
	}

	/*
	 * Initiates the playing of the current song in a separate thread (so
	 * that it does not lock up the GUI). 
	 * You do not need to change this method.
	 * It will not compile until you make your Song class.
	 */
	private void playSong() {
		if (song != null) {
			setCurrentTime(0.0);
			Thread playThread = new Thread(new Runnable() {
				public void run() {
					StdAudio.setMute(false);
					playing = true;
					doEnabling();
					String title = song.getTitle();
					String artist = song.getArtist();
					double duration = song.getTotalDuration();
					System.out.println("Playing \"" + title + "\", by "
							+ artist + " (" + duration + " sec)");
					song.play();
					System.out.println("Playing complete.");
					playing = false;
					doEnabling();
				}
			});
			playThread.start();
		}
	}

	/*
	 * Sets the current time display slider/label to show the given time in
	 * seconds. Bounded to the song's total duration as reported by the song.
	 */
	private void setCurrentTime(double time) {
		double total = song.getTotalDuration();
		time = Math.max(0, Math.min(total, time));
		currentTimeLabel.setText(String.format("%08.2f /", time));
		currentTimeSlider.setValue((int) (100 * time / total));
	}

	/*
	 * Updates the total time label on the screen to the current total duration.
	 */
	private void updateTotalTime() {
		//TODO - fill this
		double total = song.getTotalDuration();
		totalTimeLabel.setText(String.format("%08.2f sec", total));
	}
}
