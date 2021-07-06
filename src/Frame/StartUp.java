package Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StartUp extends JFrame{
	private JButton start;
	private JButton exit;
	private JLabel header, footer;
	
	public StartUp(){
		this.setTitle("Welcome To Our Project");
		this.setBounds(500, 200, 800, 600);
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initComponents();
	}
	
	private void initComponents(){
		this.header = new JLabel("Card Issue Management System");
		this.header.setBounds(180, 20, 600, 100);
		this.header.setFont(new Font("Arial", Font.BOLD, 30));
		this.add(this.header);
		
		this.footer = new JLabel("Project Done By LIV3D");
		this.footer.setBounds(320, 500, 250, 50);
		this.footer.setFont(new Font("Arial", Font.BOLD, 15));
		this.add(this.footer);
		
		this.start = new JButton("Start");
		this.exit = new JButton("Exit");
		this.start.setBounds(300, 200, 200, 50);
		this.exit.setBounds(300, 300, 200, 50);
		
		this.start.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				setVisible(false);
				loginform lf = new loginform();
				lf.setVisible(true);
			}
		});
		add(start);
		
		this.exit.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent ae){
	              int result;
	              result = JOptionPane.showConfirmDialog(null,"Are you sure you want to exit the program?", "Warning",
	                      JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
	              if(result == JOptionPane.YES_OPTION){
	                    System.exit(0);
	              }
	              else if (result == JOptionPane.NO_OPTION){
	                 
	              }
	         }
		});
		add(exit);
	}
}