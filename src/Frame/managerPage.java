package Frame;

import java.awt.event.*;
import javax.swing.*;

public class managerPage extends JFrame{
	private JButton createAdmin,managerdata,deleteAdmin,deleteUser,addUser,back,updateManagerPassword;

	public managerPage(String text){
		this.setTitle("Manager");
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(500,200,800,600);
		this.setLayout(null);
		this.initComponents();
	}
	
	private void initComponents(){
		this.createAdmin = new JButton("Create Admin");
		this.createAdmin.setBounds(280, 10, 200, 50);
		this.createAdmin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				dispose();
				createAdmin ac = new createAdmin();
				ac.setVisible(true);
				ac.setLocationRelativeTo(null);
			}
		});
		add(createAdmin);

		this.deleteAdmin = new JButton("Delete Admin");
		this.deleteAdmin.setBounds(280, 100, 200, 50);
		this.deleteAdmin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				dispose();
				deleteAdmin ad = new deleteAdmin("");
				ad.setVisible(true);
				ad.setLocationRelativeTo(null);
			}
		});
		add(deleteAdmin);
		
		this.addUser = new JButton("Add User");
		this.addUser.setBounds(280,190,200,50);
		this.addUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				dispose();
				signPage sp = new signPage();
				sp.setVisible(true);
				sp.setLocationRelativeTo(null);
			}
		});
		add(addUser);

		this.deleteUser = new JButton("Delete User");
		this.deleteUser.setBounds(280,280,200,50);
		this.deleteUser.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				dispose();
				deleteUser mud = new deleteUser("");
				mud.setVisible(true);
				mud.setLocationRelativeTo(null);
			}
		});
		add(deleteUser);

		this.managerdata = new JButton("View All Data");
		this.managerdata.setBounds(280,370,200,50);
		this.managerdata.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				dispose();
				managerDataStore md = null;
				md = new managerDataStore("");
				md.setVisible(true);
				md.setLocationRelativeTo(null);
			}
		});
		add(managerdata);

		this.updateManagerPassword = new JButton ("Change Password");
		this.updateManagerPassword.setBounds(280,460,200,50);
		this.updateManagerPassword.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				dispose();
				changeManagerPassword cmp = new changeManagerPassword("");
				cmp.setVisible(true);
				cmp.setLocationRelativeTo(null);
			}
		});
		add(updateManagerPassword);

		this.back = new JButton("Log Out");
		this.back.setBounds(600,500,100,40);
		this.back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				dispose();
				loginform lf = new loginform();
				lf.setVisible(true);
				lf.setLocationRelativeTo(null);
			}
		});
		add(back);
	}
}
