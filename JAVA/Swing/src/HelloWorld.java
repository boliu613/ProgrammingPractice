import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class HelloWorld extends JFrame{

	JFrame frame;
    public void makeGUI(){
        frame = new JFrame("final exam");
        frame.setLayout(new BorderLayout());
        JTextField t = new JTextField();
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(2,2));
        p2.add(new JButton("a"));
        p2.add(new JButton("c"));
        p2.add(new JButton("b"));
        p2.add(new JButton("d"));
        frame.add(t, BorderLayout.NORTH);
        frame.add(p2, BorderLayout.CENTER);
        //code to make everything sized, centered, visible.
        //feel free to ignore this part.
        frame.pack();
        frame.setVisible(true);
        frame.setSize(640,480);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2,
         dim.height/2-frame.getSize().height/2);
    }
    public static void main(String[] args) {
        new HelloWorld().makeGUI();
}
	
	//inner class
	class ButtonClikcListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// this is the code that gets run when button clikced
			System.out.println("button clicked");
		}
	}

}
