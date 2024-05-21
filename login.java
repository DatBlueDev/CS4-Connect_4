
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

public class login {
    
	public static void mainFunction() {
        

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

        JButton backToMain = new JButton("Go Back");
		backToMain.setBounds(1255, 580, 180, 30);
		backToMain.setBorder(emptyBorder);
		backToMain.setBackground(Color.white);

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
		frame.add(backToMain);
		backToMain.addActionListener(e -> {
			main mainFrame = new main();
			main.mainFunction();
			SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();

		});
		submit.addActionListener(e -> {
			try {
				// Create a File object
				File file = new File("session.txt");

				// Check if the file doesn't exist, then create it
				if (!file.exists()) {
					file.createNewFile();
				}

				String user1String = userinput.getText().toUpperCase();
				String user2String = user2input.getText().toUpperCase();

				FileWriter writer = new FileWriter(file, false);
				BufferedWriter bw = new BufferedWriter(writer);
				File usersFile = new File("users.txt");
				if (!usersFile.exists()) {
					usersFile.createNewFile();
				}	
				
				// Check for existing users
				boolean foundValue = false;
				int count = 0;
				if (usersFile.length() > 0) {
					try (Scanner scanner = new Scanner(usersFile)) {
						while (scanner.hasNextLine()) {
							String line = scanner.nextLine();
							if (line.equals(user1String) || line.equals(user2String)) {
								foundValue = true;
								System.out.println("User Already Exists! User: " + line);
								count +=1;
							}
						}
					}
				}
				if (count == 2){
					bw.write(user1String + System.lineSeparator());
					bw.write(user2String + System.lineSeparator());

					// Close the BufferedWriter
					bw.close();

					System.out.println("Successfully wrote to " + "session.txt");
				    JOptionPane.showMessageDialog(null,"Users successfully logged in as players 1 and 2.", "Information", JOptionPane.INFORMATION_MESSAGE);

				}
				else{
					System.out.println("User/s Not Registered!!");
				    JOptionPane.showMessageDialog(null, "User not registered! Please Register.", "Information", JOptionPane.INFORMATION_MESSAGE);

				}

			} catch (IOException er) {
				System.err.println("Error writing to file: " + er.getMessage());
			}
		});
		button.addActionListener(e -> {
			register registerBFrame = new register();
			registerBFrame.mainFunction();
			SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();

		});
	}
}