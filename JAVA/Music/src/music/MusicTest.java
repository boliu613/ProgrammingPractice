package music;

import java.util.ArrayList;

import javax.swing.JApplet;

public class MusicTest extends JApplet

{
   
    ArrayList<Instrument> instruments;
    
    public void init() {
        
        instruments = new ArrayList<Instrument>();
     
        ElectricGuitar eg = new ElectricGuitar(this);
        instruments.add(eg);
        ElectricBassGuitar bg = new ElectricBassGuitar(this,"http://www.reocities.com/SouthBeach/8243/seinfeld_theme.mid");
        instruments.add(bg);
        Drums d = new Drums(this, "http://mididrumfiles.com/files/samples/Rock/Rock30.mid");
        instruments.add(d);
       
        for(Instrument instrument : instruments) {
            instrument.play();
        }
       
    }
}
