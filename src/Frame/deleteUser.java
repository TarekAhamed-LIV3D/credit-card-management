package Frame;

import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import Database.*;

public class deleteUser extends JFrame{
	private JLabel userId;
	private JTextField userIdText;
	private JButton deleteUser, back;

	public deleteUser(String text){
		this.setTitle("User Remove");
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(500,200,800,600);
		this.setLayout(null);
		this.initComponents();
	}

	private void initComponents(){
		this.userId = new JLabel("User Id");
		this.userId.setBounds(500,150,180,20);
		this.userId.setOpaque(false);
		add(userId);

		this.userIdText = new JTextField("");
		this.userIdText.setBounds(500,190,200,30);
		this.userIdText.setOpaque(true);
		add(userIdText);

		this.deleteUser = new JButton("Delete");
		this.deleteUser.setBounds(500,240,100,40);
		this.deleteUser.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				String id = userIdText.getText();
				System.out.println(id);
				PreparedStatement ps;
				String query = "DELETE FROM `userinformation` WHERE `id` = ?";
				try{
					ps = (PreparedStatement) connectForm.getConnection().prepareStatement(query);
					ps.setString(1, id);

					if(ps.executeUpdate() > 0){
						JOptionPane.showMessageDialog(null, "Deleted");
					}
					else{
						JOptionPane.showMessageDialog(null, "Error");
					}
				}
				catch(SQLException ex){
					JOptionPane.showMessageDialog(null, JOptionPane.ERROR_MESSAGE);
				}
				catch(ClassNotFoundException e){
					e.printStackTrace();
				}
			}
		});
		add(deleteUser);

		this.back = new JButton("Back");
		this.back.setBounds(280,500,100,40);
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
}
