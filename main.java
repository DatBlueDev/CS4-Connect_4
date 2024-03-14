
import java.awt.*;
import javax.swing.*;
import javax.swing.Box;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class main extends JFrame{
	JButton play;
	JButton menu;
	JButton exit;
	JLabel title;
	JLabel displayField;
	ImageIcon image;

	JFrame window;
	JPanel textPanel;
	JLabel textLabel;
	Font blood;
		
	main(){
		super("BLOOD MOON");
		play = new JButton("PLAY");
		menu = new JButton("MENU");
		exit = new JButton("EXIT");
		title = new JLabel("Blood Moon");
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		panel.setBackground(Color.black);
		panel.add(play);
		panel.add(exit);
		this.add(panel, BorderLayout.EAST);
		panel.setPreferredSize(new Dimension(200, 50));
		
		play.setPreferredSize(new Dimension(150, 30));
		exit.setPreferredSize(new Dimension(150, 30));
		play.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		
		try{
			blood = Font.createFont(Font.TRUETYPE_FONT, new File("HtVelvetWhisperRegular-5y15v.ttf")).deriveFont(20f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("HtVelvetWhisperRegular-5y15v.ttf")));
		}
		catch(IOException | FontFormatException e){
			
		}
		
		play.setFont(blood);
		exit.setFont(blood);
		JPanel left = new JPanel();
		left.setPreferredSize(new Dimension(600, 100));
		left.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		left.setBackground(Color.BLACK);
		this.add(left, BorderLayout.WEST);
		title.setFont(blood);

		title.setPreferredSize(new Dimension(600, 590));
		
		try
		{
			image = new  ImageIcon(getClass().getResource("moon.gif"));
			displayField = new JLabel(image);
			left.add(displayField);
		}
		catch (Exception e){
			System.out.println("Image cannot be found!");
		}
		
		image = new ImageIcon(getClass().getResource("moon.gif"));
		
		exit.addActionListener(e -> {
			SwingUtilities.getWindowAncestor((Component)e.getSource()).dispose();
		});
		play.addActionListener(e -> {
			MDAA secondFrame = new MDAA();
			secondFrame.mainFunction();
			SwingUtilities.getWindowAncestor((Component)e.getSource()).dispose();

		});
		
		exit.setLocation(10, 300);
	}
	
	public static void main(String args[]){
		main hoho = new main();
		hoho.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		hoho.setSize(800, 680);
		hoho.setLocation(300,300);
		hoho.setVisible(true);
		hoho.getContentPane().setBackground(Color.gray);
		hoho.setResizable(false);
	}
	
}
