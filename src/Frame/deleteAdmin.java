package Frame;

import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import Database.*;

public class deleteAdmin extends JFrame{
	private JLabel adminId;
	private JTextField adminIdText;
	private JButton deleteAdmin,back;

	public deleteAdmin(String text){
		this.setTitle("Remove Admin");
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(500,200,800,600);
		this.setLayout(null);
		this.initComponents();
	}

	private void initComponents(){
		adminId = new JLabel("Admin Id");
		adminId.setBounds(430,190,200,30);
		adminId.setOpaque(false);
		add(adminId);

		adminIdText = new JTextField("");
		adminIdText.setBounds(500,190,200,30);
		adminIdText.setOpaque(true);
		add(adminIdText);

		this.deleteAdmin = new JButton("Delete");
		this.deleteAdmin.setBounds(500,240,100,40);
		this.deleteAdmin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				String id = adminIdText.getText();
				System.out.println(id);

				PreparedStatement ps;
				String query = "DELETE FROM `admininformation` WHERE `id` = ?";
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
					JOptionPane.showMessageDialog(null,JOptionPane.ERROR_MESSAGE);
				}
				catch(ClassNotFoundException e){
					e.printStackTrace();
				}
			}
		});
		add(deleteAdmin);

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
