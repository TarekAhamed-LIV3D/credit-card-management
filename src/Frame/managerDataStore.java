package Frame;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import Database.*;

public class managerDataStore extends JFrame{
	private JTable managerTable;
	private ResultSet rs;
	private JButton back,viewCardData,viewAdminData,viewUserData;

	public managerDataStore(String text){
		this.setTitle("DataBase");
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(500,200,800,600);
		this.setLayout(null);
		this.initComponents();
	}

	private void initComponents(){
		this.viewCardData = new JButton("Show DataBase of Cards");
		this.viewCardData.setBounds(500,190,200,50);
		this.viewCardData.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				dispose();
				viewCardData vcd = new viewCardData("");
				vcd.setVisible(true);
				vcd.setLocationRelativeTo(null);
			}
		});
		add(viewCardData);

		this.viewAdminData = new JButton("Show DataBase of Admins");
		this.viewAdminData.setBounds(500,280,200,50);
		this.viewAdminData.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				dispose();
				viewAdminData vad = new viewAdminData("");
				vad.setVisible(true);
				vad.setLocationRelativeTo(null);
			}
		});
		add(viewAdminData);

		this.viewUserData = new JButton("Show DataBase of Users");
		this.viewUserData.setBounds(500,370,200,50);
		this.viewUserData.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				dispose();
				viewUserData vud = new viewUserData("");
				vud.setVisible(true);
				vud.setLocationRelativeTo(null);
			}
		});
		add(viewUserData);

		this.back = new JButton("Back");
		this.back.setBounds(500,460,200,50);
		this.back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				dispose();
				managerPage mp = new managerPage("");
				mp.setVisible(true);
				mp.setLocationRelativeTo(null);
			}
		});
		add(back);
	}

	public static void main(String[] args) {
		managerDataStore mds = new managerDataStore("");
		mds.setVisible(true);
	}
}