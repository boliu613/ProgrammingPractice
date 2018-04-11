package songs;

//TODO: write this class

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;



public class Song {
	//instance variables
	private Note notes[];
	private String title;
	private String artist;

	/**
	 * populate your song’s array of notes by reading note data from the specified file.
	 * @param filename
	 */
	public Song(String filename){
		try {
			File file = new File(filename);
			Scanner scanner = new Scanner(file);
			
			this.title = scanner.nextLine();
			this.artist = scanner.nextLine();
			int numberOfNodes = Integer.parseInt(scanner.nextLine());
			this.notes = new Note[numberOfNodes];

			for (int i = 0; i < numberOfNodes; i++) {
				String line = scanner.nextLine();
				line = line.trim();
				String[] nodeInfo = line.split(" ");

				double duration = Double.parseDouble(nodeInfo[0]);
				Pitch pitch = Pitch.getValueOf(nodeInfo[1]);
				if (!pitch.equals(Pitch.getValueOf("R"))) {
					int octave = Integer.parseInt(nodeInfo[2]);
					Accidental accidental = Accidental.valueOf(nodeInfo[3]);
					boolean repeat = Boolean.parseBoolean(nodeInfo[4]);
					notes[i] = new Note(duration, pitch, octave, accidental, repeat);
				}
				else {
					boolean repeat = Boolean.getBoolean(nodeInfo[2]);
					notes[i] = new Note(duration, repeat);
				}						
			}
			scanner.close();
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Returns the title of the song
	 * @return
	 */
	public String getTitle(){
		return this.title;
	}
	
	/**
	 * Returns the artist
	 * @return
	 */
	public String getArtist(){
		return this.artist;
	}
	
	/**
	 * Return the total duration of the song in seconds
	 * @return
	 */
	public double getTotalDuration(){
		double totalDuration = 0;
		boolean repeat = false;
		
		for (Note note : notes) {
			totalDuration += note.getDuration();
			if (note.isRepeat()) {
				repeat = !repeat;
			}
			if (repeat || (!repeat && note.isRepeat())) {
				totalDuration += note.getDuration();
			}
		}
		return totalDuration;
	}
	
	/**
	 * play the song
	 */
	public void play(){
		boolean repeat = false;
		for (int i = 0; i < notes.length; i++) {
			if (notes[i].isRepeat()) {
				repeat = !repeat;
			}
			if (repeat && notes[i].isRepeat()) {
				for (int j = i; j < notes.length; j++) {
					notes[j].play();
					if ((j != i) && (repeat && notes[j].isRepeat())) {
						break;
					}
				}
			}
			notes[i].play();
		}
	}
	
	/**
	 * modify the state of the notes so that they are all exactly 1 octave lower in pitch than their current state
	 * Return true if this method lowered the octave, and false if any note(s) in the song are already down at octave 1
	 * @return
	 */
	public boolean octaveDown(){
		for (Note note : notes) {
			if (note.getOctave() == 1) {
				return false;
			}			
		}
		
		for (Note note : notes) {
			if (note.isRest()) {
				continue;
			}
			else {
				note.setOctave(note.getOctave()-1);
			}
		}
		return true;
	}
	
	/**
	 * raises the octave of every note by 1
	 * Return true if this method raised the octave, and false if any note(s) in the song are already at octave 10
	 * @return
	 */
	public boolean octaveUp(){
		for (Note note : notes) {
			if (note.getOctave() == 10) {
				return false;
			}			
		}
		
		for (Note note : notes) {
			if (note.isRest()) {
				continue;
			}
			else {
				note.setOctave(note.getOctave()+1);
			}
		}
		return true;
	}
	
	/**
	 * multiply the duration of each note in your song by the given ratio
	 * @param ratio
	 */
	public void changeTempo(double ratio){
		for (Note note : notes) {
			note.setDuration(note.getDuration()*ratio);
		}
	}
	
	/**
	 * reverse the order of the notes
	 */
	public void reverse(){
		for (int i = 0; i < notes.length/2; i++) {
			Note temp;
			temp = notes[i];
			notes[i] = notes[notes.length-1-i];
			notes[notes.length-1-i] = temp;
		}		
	}
	
	@Override
	public String toString() {
		String s = "";
		s += "Title: " + getTitle() + "\n";
		s += "Artist: " + getArtist() + "\n";
		s += "Duration: " + getTotalDuration() + "\n";
		s += "Notes: " + Arrays.toString(notes) + "\n";
		return s;
	}
	
	public static void main(String[] args) {
		Song song = new Song("birthday.txt");
		System.out.println(song.getTotalDuration());
		System.out.println(song.getArtist());
		System.out.println(song.getTitle());		
//		song.octaveDown();
//		song.octaveUp();
		song.changeTempo(0.1);
		System.out.println(song.getTotalDuration());
//		song.reverse();		
//		song.play();
		System.out.println(song.toString());	   
	}

}



