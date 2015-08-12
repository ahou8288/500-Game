import java.awt.GridLayout;
import javax.swing.*;

public class GuiDisp {

    public static void FormHands(Hand[] hands) {
        final JFrame f = new JFrame("Players Hands");

        JPanel panel = new JPanel(new GridLayout(3, 3, 3, 3));
        
        int j=0;
        for (int i = 0; i < 9; i++) {
        	if (i%2==1){
        		System.out.printf(DisplayMethods.HandToString(hands[j]));
	            JLabel l = new JLabel("P"+j+"-"+DisplayMethods.HandToString(hands[j]), JLabel.CENTER);
	            l.setFont(l.getFont().deriveFont(14f));
	            panel.add(l);
	            j++;
        	}else{
	            JLabel l = new JLabel(" ", JLabel.CENTER);
	            l.setFont(l.getFont().deriveFont(14f));
	            panel.add(l);
        	}
        }

        f.setContentPane(panel);
        f.setSize(1100,100);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}