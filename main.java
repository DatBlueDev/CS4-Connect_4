
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
import java.io.FileReader;
import java.io.BufferedReader;

import java.util.*;

public class main extends JFrame {
	JButton play;
	JButton menu;
	JButton exit;
	JLabel title;
	JLabel displayField;
	ImageIcon image;

	JButton loginB;
	JButton registerB;

	JLabel player1Tag;
	JLabel player2Tag;

	JFrame window;
	JPanel textPanel;
	JLabel textLabel;
	Font blood;

	main() {
		super("BLOOD MOON");
		play = new JButton("PLAY");
		menu = new JButton("MENU");
		exit = new JButton("EXIT");
		title = new JLabel("Blood Moon");

		loginB = new JButton("LOGIN");
		registerB = new JButton("REGISTER");

		player1Tag = new JLabel("Player 1: ");
		player2Tag = new JLabel("Player 2: ");
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		panel.setBackground(Color.black);
		panel.add(play);
		panel.add(exit);

		panel.add(loginB);
		panel.add(registerB);

		panel.add(player1Tag);
		panel.add(player2Tag);
		this.add(panel, BorderLayout.EAST);
		panel.setPreferredSize(new Dimension(200, 50));

		play.setPreferredSize(new Dimension(150, 30));
		exit.setPreferredSize(new Dimension(150, 30));
		loginB.setPreferredSize(new Dimension(150, 30));
		registerB.setPreferredSize(new Dimension(150, 30));
		
		player1Tag.setPreferredSize(new Dimension(150, 30));
		player2Tag.setPreferredSize(new Dimension(150, 30));
		player1Tag.setForeground(Color.WHITE);
		player2Tag.setForeground(Color.WHITE);

		try (BufferedReader reader = new BufferedReader(new FileReader("session.txt"))) {
			String firstLine = reader.readLine();
			String secondLine = reader.readLine();

			if (firstLine != null) {
				player1Tag.setText("Player 1: " + firstLine);
			} else {
				System.out.println("File is empty or has less than one line.");
			}

			if (secondLine != null) {
				player2Tag.setText("Player 2: " + secondLine);
			}
			} catch (IOException e) {
				System.err.println("Error reading file: " + e.getMessage());
			}
		play.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

		try {
			blood = Font.createFont(Font.TRUETYPE_FONT, new File("HtVelvetWhisperRegular-5y15v.ttf")).deriveFont(20f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("HtVelvetWhisperRegular-5y15v.ttf")));
		} catch (IOException | FontFormatException e) {

		}

		play.setFont(blood);
		exit.setFont(blood);
		loginB.setFont(blood);
		registerB.setFont(blood);

		JPanel left = new JPanel();
		left.setPreferredSize(new Dimension(600, 100));
		left.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		left.setBackground(Color.BLACK);
		this.add(left, BorderLayout.WEST);
		title.setFont(blood);

		title.setPreferredSize(new Dimension(600, 590));


		try {
			image = new ImageIcon(getClass().getResource("moon.gif"));
			displayField = new JLabel(image);
			left.add(displayField);
		} catch (Exception e) {
			System.out.println("Image cannot be found!");
		}

		image = new ImageIcon(getClass().getResource("moon.gif"));

		exit.addActionListener(e -> {
			SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();
		});
		play.addActionListener(e -> {
			MDAA secondFrame = new MDAA();
			secondFrame.mainFunction();
			SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();

		});

		loginB.addActionListener(e -> {
			login loginFrame = new login();
			loginFrame.mainFunction();
			SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();		
		});
		registerB.addActionListener(e -> {
			register registerFrame = new register();
			registerFrame.mainFunction();
			SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();

		});

		exit.setLocation(10, 300);
	}

	public static void main(String args[]) {
		mainFunction();
	}
	public static void mainFunction(){
		main hoho = new main();
		hoho.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		hoho.setSize(800, 680);
		hoho.setLocation(0, 0);
		hoho.setVisible(true);
		hoho.getContentPane().setBackground(Color.gray);
		hoho.setResizable(false);
	}

}
