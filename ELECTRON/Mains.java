import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mains {
    
	public static void main(String[] args) {
        

		// JPanel = a GUI component that functions as a container to hold other components
		ImageIcon icon = new ImageIcon("Vampires.gif");
        ImageIcon icons = new ImageIcon("Logo.png");
        
		
		JLabel label = new JLabel();
        JLabel logo = new JLabel();
        JLabel username = new JLabel();
		username.setText("Username 1:");
		username.setBounds(1290,200,200,200);
		
		Font user = new Font("Courier", Font.BOLD, 20);
        Font users = new Font("Courier", Font.ITALIC, 20);
		username.setFont(user);

        JLabel user2 = new JLabel();
		user2.setText("Username 2: ");
		user2.setBounds(1290, 310, 200, 200);
		user2.setFont(user);

        JTextField user2input = new JTextField(20);
		user2input.setBounds(1255,435,180,30);
		user2input.setFont(users);

        JTextField userinput = new JTextField(20);
		userinput.setBounds(1255,325,180,30);
		userinput.setFont(users);

        JButton button = new JButton("Register now!");
		button.setBounds(1255, 500, 180, 30);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		button.setBorder(emptyBorder);
		button.setBackground(Color.white);

        JButton submit = new JButton("Submit now");
		submit.setBounds(1255, 540, 180, 30);
		submit.setBorder(emptyBorder);
		submit.setBackground(Color.white);

        logo.setIcon(icons);
        logo.setBounds(100, 0, 20, 20);
        logo.setVerticalAlignment(JLabel.TOP);
		logo.setHorizontalAlignment(JLabel.RIGHT);


		label.setIcon(icon);
		label.setVerticalAlignment(JLabel.TOP);
		label.setHorizontalAlignment(JLabel.LEFT);

		//label.setBounds(100, 100, 75, 75);
		
		JPanel bluePanel = new JPanel();
		bluePanel.setBounds(-10, -10, 1209, 680);
		bluePanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
		
		JPanel greenPanel = new JPanel();
		greenPanel.setLayout(new FlowLayout());
        greenPanel.setBounds(1200,0,291,280);


		
		JFrame frame = new JFrame("Blood Moon");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setSize(1500, 680);
		frame.setVisible(true);	
        frame.setResizable(false);
		bluePanel.add(label);
        greenPanel.add(logo);
		frame.add(bluePanel);
		frame.add(greenPanel);
        frame.add(username);
        frame.add(userinput);
        frame.add(user2);
        frame.add(user2input);
        frame.add(button);
        frame.add(submit);
	}
}