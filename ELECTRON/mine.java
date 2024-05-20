import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.io.File;

public class mine {
    
	static Font script;
	public static void main(String[] args) {
        
		try{
			script = Font.createFont(Font.TRUETYPE_FONT, new File("VerifiedScript-RpjRE.ttf")).deriveFont(120f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("VerifiedScript-RpjRE.ttf")));
		} catch(IOException | FontFormatException e){

		}
		// JPanel = a GUI component that functions as a container to hold other components
		ImageIcon icon = new ImageIcon("Right.gif");
		ImageIcon claw = new ImageIcon("Claw.png");
        
		JLabel label = new JLabel();
		JLabel clawz = new JLabel();

		clawz.setIcon(claw);
		clawz.setBounds(0,0, 500, 500);

		label.setIcon(icon);
		label.setVerticalAlignment(JLabel.TOP);
		label.setHorizontalAlignment(JLabel.RIGHT);

		JPanel random = new JPanel();
		random.setBounds(370, 100, 500, 500);
		random.add(clawz);

		JPanel greenPanel = new JPanel();
		greenPanel.setLayout(new FlowLayout());
        greenPanel.setBounds(1200,0,291,680);

		JPanel bluePanel = new JPanel();
		bluePanel.setLayout(new FlowLayout());
        bluePanel.setBounds(0,5,1200,680);
		
		Font user = new Font("Courier", Font.BOLD, 20);

		JLabel registername = new JLabel();
		registername.setText("Registration");
		registername.setFont(script);
		registername.setForeground(Color.BLACK);
		registername.setBounds(310, 100, 700, 250);

		JLabel usereg = new JLabel();
		usereg.setText("Enter the name to be registered: ");
		usereg.setFont(user);
		usereg.setBounds(300, 270, 400, 200);

		Border emptyBorder = BorderFactory.createEmptyBorder();

		JButton submit = new JButton("Submit");
		submit.setBounds(510, 400, 90, 28);
		submit.setBorder(emptyBorder);
		submit.setBackground(Color.WHITE);

		JButton newPlayer = new JButton("Register again");
		newPlayer.setBounds(300, 435, 145, 28);
		newPlayer.setBorder(emptyBorder);
		newPlayer.setBackground(Color.WHITE);

		JButton login = new JButton("Log-in");
		login.setBounds(455, 435, 145, 28);
		login.setBorder(emptyBorder);
		login.setBackground(Color.WHITE);

		JTextField input = new JTextField();
		input.setBounds(300, 400, 200, 30);

		JFrame frame = new JFrame("Blood Moon");
		frame.add(registername);
		frame.add(usereg);
		frame.add(input);
		frame.add(submit);
		frame.add(newPlayer);
		frame.add(login);
		frame.add(random);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setSize(1500, 680);
		frame.setVisible(true);	
        frame.setResizable(false);
		
		greenPanel.add(label);
		frame.add(greenPanel);
		frame.add(bluePanel);
		

	}
}