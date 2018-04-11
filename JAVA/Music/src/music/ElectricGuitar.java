package music;

import javax.swing.JApplet;

public class ElectricGuitar extends StringedInstrument {

    public ElectricGuitar(JApplet applet) {
        this(applet,"http://www.reocities.com/SouthBeach/8243/Terminator_Theme.mid");
    }

    public ElectricGuitar(JApplet applet, String address) {
        super(applet, address);
        this.name = "Guitar";
        this.numberOfStrings = 6;

    }

    public ElectricGuitar(JApplet applet, String address, int numberOfStrings) {
        super(applet, address);
        this.name = "Guitar";
        this.numberOfStrings = numberOfStrings;
    }

    @Override
    public void play() {
        System.out.println("An electric " + numberOfStrings + "-string " + name
                + " is rocking!");
        song.play();
    }
}
