import javax.swing.border.Border;
import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;
public class register {
    
	static Font script;
	public static void mainFunction() {
        
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

		JButton newPlayer = new JButton("Go Back");
		newPlayer.setBounds(300, 435, 145, 28);
		newPlayer.setBorder(emptyBorder);
		newPlayer.setBackground(Color.WHITE);

		JButton loginB = new JButton("Log-in");
		loginB.setBounds(455, 435, 145, 28);
		loginB.setBorder(emptyBorder);
		loginB.setBackground(Color.WHITE);

		JTextField input = new JTextField();
		input.setBounds(300, 400, 200, 30);

		JFrame frame = new JFrame("Blood Moon");
		frame.add(registername);
		frame.add(usereg);
		frame.add(input);
		frame.add(submit);
		frame.add(newPlayer);
		frame.add(loginB);
		frame.add(random);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setSize(1500, 680);
		frame.setVisible(true);	
        frame.setResizable(false);
		
		greenPanel.add(label);
		frame.add(greenPanel);
		frame.add(bluePanel);
		
		newPlayer.addActionListener(e -> {
			main mainFrame = new main();
			mainFrame.mainFunction();
			SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();

		});
		submit.addActionListener(e -> {
			if (input.getText().length() >= 3 && input.getText().length() <= 16) {
			
				try {
					File file = new File("users.txt");
					if (!file.exists()) {
						file.createNewFile();
					}	
					String valueToAdd = input.getText().toUpperCase();
					// Check for existing users
					boolean foundValue = false;
					if (file.length() > 0) {
						try (Scanner scanner = new Scanner(file)) {
							while (scanner.hasNextLine()) {
								String line = scanner.nextLine().toUpperCase();
								if (line.equals(valueToAdd)) {
									foundValue = true;
									System.out.println("User Already Exists! User: " + valueToAdd);
    								JOptionPane.showMessageDialog(null, "User Already Exists.", "Information", JOptionPane.INFORMATION_MESSAGE);

									break;
								}
							}
						}
					}

					// If user doesn't exist
					if (!foundValue) {
						try (FileWriter writer = new FileWriter(file, true)) {
							writer.write(valueToAdd + System.lineSeparator()); // Add newline character
						}
						String subfolder = "users/";

						// Create the subfolder if it doesn't exist
						File folder = new File(subfolder);
						if (!folder.exists()) {
							folder.mkdirs(); // Create directories recursively
						}

						// Construct the full path with subfolder
						String filePath = subfolder + valueToAdd + ".txt";

						try (  // Use try-with-resources for automatic closing of resources
							FileWriter writer = new FileWriter(filePath)) {
							writer.write(valueToAdd + "\n");
							writer.write("0\n");
							writer.write("0\n");
						}
						System.out.println("File created: " + filePath);
						System.out.println("Value '" + valueToAdd + "' added to users.txt");
						JOptionPane.showMessageDialog(null, "Success! User Created", "Success", JOptionPane.INFORMATION_MESSAGE);

					}
				}
				catch(IOException er){

				}
			} 
			else
			{
    			JOptionPane.showMessageDialog(null, "Invalid Username length.", "Information", JOptionPane.INFORMATION_MESSAGE);

			}
		});
		loginB.addActionListener(e -> {
			login loginBFrame = new login();
			loginBFrame.mainFunction();
			SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();

		});
	}
}