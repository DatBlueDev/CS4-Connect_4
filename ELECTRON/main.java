import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class mine {
    
	public static void main(String[] args) {
        
		
		// JPanel = a GUI component that functions as a container to hold other components
		ImageIcon icon = new ImageIcon("Right.gif");
        ImageIcon icons = new ImageIcon("Logo.gif");
        
		JLabel label = new JLabel();
        JLabel logo = new JLabel();
		JLabel username = new JLabel();

		label.setIcon(icon);
		label.setVerticalAlignment(JLabel.TOP);
		label.setHorizontalAlignment(JLabel.RIGHT);

		logo.setIcon(icons);
		logo.setVerticalAlignment(JLabel.TOP);
		logo.setHorizontalAlignment(JLabel.LEFT);

		JPanel greenPanel = new JPanel();
		greenPanel.setLayout(new FlowLayout());
        greenPanel.setBounds(1200,0,291,680);

		JPanel bluePanel = new JPanel();
		bluePanel.setLayout(new FlowLayout());
        bluePanel.setBounds(0,5,1200,680);
		bluePanel.setBackground(Color.BLACK);
		
		JLabel registername = new JLabel();
		registername.setText("Name to be registered: ");
		Font user = new Font("Courier", Font.BOLD, 20);
		registername.setFont(user);
		registername.setForeground(Color.WHITE);
		registername.setBounds(200,1000, 100, 60);

		JFrame frame = new JFrame("Blood Moon");
		bluePanel.add(registername);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setSize(1500, 680);
		frame.setVisible(true);	
        frame.setResizable(false);
		
		greenPanel.add(label);
		bluePanel.add(logo);
		frame.add(greenPanel);
		frame.add(bluePanel);
		

	}
}