package Frame;

import java.awt.event.*;
import javax.swing.*;

public class adminPage extends JFrame{
	private JButton viewUserData,addUser,back,verifyUser,userDelete,changePassword;

	public adminPage(String text){
		this.setTitle("Admin");
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(500,200,800,600);
		this.setLayout(null);
		this.initComponents();		
	}

	private void initComponents(){
		this.back = new JButton("Log Out");
		this.back.setBounds(500,450,200,50);
		this.back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				dispose();
				loginform lf = new loginform();
				lf.setVisible(true);
				lf.setLocationRelativeTo(null);
			}
		});
		add(back);

		this.viewUserData = new JButton("Show DataBase of Users");
		this.viewUserData.setBounds(100,250,200,50);
		this.viewUserData.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				dispose();
				viewUserDataAdmin vud = new viewUserDataAdmin("");
				vud.setVisible(true);
				vud.setLocationRelativeTo(null);
			}
		});
		add(viewUserData);
		
		this.changePassword = new JButton("Reset Password");
		this.changePassword.setBounds(500,350,200,50);
		this.changePassword.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				dispose();
				changeAdminPassword cap = new changeAdminPassword("");
				cap.setVisible(true);
				cap.setLocationRelativeTo(null);
			}
		});
		add(changePassword);

		this.addUser = new JButton("Add User");
		this.addUser.setBounds(500,150,200,50);
		this.addUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				dispose();
				signPageAdmin spa = new signPageAdmin();
				spa.setVisible(true);
				spa.setLocationRelativeTo(null);
			}
		});
		add(addUser);
		
		this.userDelete = new JButton("Delete User");
		this.userDelete.setBounds(500,250,200,50);
		this.userDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				dispose();
				deleteUserAdmin dua = new deleteUserAdmin("");
				dua.setVisible(true);
				dua.setLocationRelativeTo(null);
			}
		});
		add(userDelete);

		this.verifyUser = new JButton("Confirm Issue Card");
		this.verifyUser.setBounds(500,50,200,50);
		this.verifyUser.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				dispose();
				userVerify uv = new userVerify("");
				uv.setVisible(true);
				uv.setLocationRelativeTo(null);
			}
		});
		add(verifyUser);
	}

	public static void main(String[] args){
		loginform lf = new loginform();
		lf.setVisible(true);
	}
}