package Frame;

import java.awt.event.*;
import javax.swing.*;
import java.util.logging.*;
import java.sql.*;
import Database.*;

public class changeAdminPassword extends JFrame{
	private JLabel oldPassword,newPassword,confirmPassword;
	private JPasswordField oldPasswordText,newPasswordText,confirmPasswordText;
	private String adminData;
	private JButton passwordReset,back;
	private PreparedStatement ps;
	private String oPassword;
	ResultSet rs,rs2;

	public changeAdminPassword(String text){
		this.setTitle("Change Admin Password");
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(500,200,800,600);
		this.setLayout(null);
		this.initComponents();
	}
	private void initComponents(){
		this.adminData = loginform.admindata;

		this.oldPassword = new JLabel("Old Password");
		this.oldPassword.setBounds(500,150,180,20);
		this.oldPassword.setOpaque(false);
		add(oldPassword);

		this.oldPasswordText = new JPasswordField("");
		this.oldPasswordText.setBounds(500,190,200,30);
		this.oldPasswordText.setOpaque(true);
		add(oldPasswordText);

		this.newPassword = new JLabel("New Password");
		this.newPassword.setBounds(500,250,180,20);
		this.newPassword.setOpaque(false);
		add(newPassword);

		this.newPasswordText = new JPasswordField("");
		this.newPasswordText.setBounds(500,290,200,30);
		this.newPasswordText.setOpaque(true);
		add(newPasswordText);

		this.confirmPassword = new JLabel("Confirm Password");
		this.confirmPassword.setBounds(500,350,180,20);
		this.confirmPassword.setOpaque(false);
		add(confirmPassword);

		this.confirmPasswordText = new JPasswordField("");
		this.confirmPasswordText.setBounds(500,390,200,30);
		this.confirmPasswordText.setOpaque(true);
		add(confirmPasswordText);

		this.passwordReset = new JButton("Reset Password");
        this.passwordReset.setBounds(500,450,200,40);
        this.passwordReset.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){ 
             String query = "UPDATE `admininformation` SET `password` =? WHERE `adminid`=?";
             String query2 = "SELECT `password` FROM `admininformation` WHERE `adminid`=?";
             
             String oldInput = oldPasswordText.getText();
             String newInput = newPasswordText.getText();
             String confirmInput = confirmPasswordText.getText();
           
                try{
                    PreparedStatement ps = (PreparedStatement)connectForm.getConnection().prepareStatement(query2);
                    ps.setString(1, adminData);
                    rs = ps.executeQuery();
                    rs.next();
                    oPassword = rs.getString(1);
                	}

                catch(SQLException ex){
                    Logger.getLogger(changeManagerPassword.class.getName()).log(Level.SEVERE, null, ex);
                	} 
                catch (ClassNotFoundException e){
					e.printStackTrace();
				}

             		System.out.println(oldInput);

        		try{
            		if(newInput.equals(confirmInput) && oPassword.equals(oldInput)){
            	   		ps = (PreparedStatement) connectForm.getConnection().prepareStatement(query);           
                		ps.setString(1, newInput);
                		ps.setString(2, adminData);
            		}

            	else{
                 	JOptionPane.showMessageDialog(null, "Password Mismatch");
            		}

            	if(ps.executeUpdate() >0){
                	JOptionPane.showMessageDialog(null, "Password Rested");
            		}

            	else{
                	JOptionPane.showMessageDialog(null, "Reset Error");
            		}
            	}

            	catch(SQLException ex){
             		JOptionPane.showMessageDialog(null,"Database Connection Failure","Fix Database",JOptionPane.ERROR_MESSAGE);
        		}
        		catch (ClassNotFoundException e){
					e.printStackTrace();
				}
        	}
     	});
        add(passwordReset);

     	this.back = new JButton("Back");
     	this.back.setBounds(280,500,100,40);
     	this.back.addActionListener(new ActionListener(){
     		public void actionPerformed(ActionEvent ae){
     			dispose();
     			adminPage ap = new adminPage("");
     			ap.setVisible(true);
     			ap.setLocationRelativeTo(null);
     		}
     	});
     	add(back);
	}
}
