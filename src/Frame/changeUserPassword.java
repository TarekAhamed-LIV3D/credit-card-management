package Frame;

import com.mysql.jdbc.*;

import Database.connectForm;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.logging.*;
import javax.swing.*;

class changeUserPassword extends JFrame {

    private JLabel oldPassword,newPassword,confirmPassword;
    private JPasswordField oldPasswordText,newPasswordText,confirmPasswordText;
    private String userData,oldpass;
    private JButton passwordChange,back;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public changeUserPassword(String text){
    	this.setTitle("Reset User Password");
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(500, 200, 800, 600);
        this.setLayout(null);
        this.initComponents();
    }

    private void initComponents(){
        this.userData = loginform.userdata;
        
        this.oldPassword = new JLabel("Old Password");
        this.oldPassword.setBounds(500,150 , 180, 20);
        this.oldPassword.setOpaque(false);
        add(oldPassword);

        this.oldPasswordText = new JPasswordField("");
        this.oldPasswordText.setBounds(500,190, 200, 30);
        this.oldPasswordText.setOpaque(true);
        add(oldPasswordText);
        
        this.newPassword = new JLabel("New Password");
        this.newPassword.setBounds(500,250 , 180, 20);
        this.newPassword.setOpaque(false);
        add(newPassword);

        this.newPasswordText = new JPasswordField("");
        this.newPasswordText.setBounds(500,290, 200, 30);
        this.newPasswordText.setOpaque(true);
        add(newPasswordText);
        
        this.confirmPassword = new JLabel("Confirm Password");
        this.confirmPassword.setBounds(500,350 , 180, 20);
        this.confirmPassword.setOpaque(false);
        add(confirmPassword);

        this.confirmPasswordText = new JPasswordField("");
        this.confirmPasswordText.setBounds(500,390, 200, 30);
        this.confirmPasswordText.setOpaque(true);
        add(confirmPasswordText);
        
        this.passwordChange= new JButton("Reset Password");
        this.passwordChange.setBounds(500,450,200,40);
        this.passwordChange.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
             String query1 = "UPDATE `userinformation` SET `password`=? WHERE `userid`=?";
             String query2="SELECT `password` FROM `userinformation` WHERE `userid`=?";
             
             String oldInput = oldPasswordText.getText();
             String newInput = newPasswordText.getText();
             String ConfirmInput = confirmPasswordText.getText();
           
                try {
                    PreparedStatement ps2 = (PreparedStatement)connectForm.getConnection().prepareStatement(query2);
                    ps2.setString(1, userData);
                    rs = ps2.executeQuery();
                    rs.next();
                    oldpass=rs.getString(1);
                }
                catch (SQLException ex){
                    JOptionPane.showMessageDialog(null,"Database Connection Failure","Fix Database",JOptionPane.ERROR_MESSAGE);
                }
                catch(ClassNotFoundException e){
					e.printStackTrace();
				}
             	System.out.println(oldInput);
             try{
            	if(newInput.equals(ConfirmInput) && oldpass.equals(oldInput)){   
            		ps = (PreparedStatement)connectForm.getConnection().prepareStatement(query1);           
                	ps.setString(1, newInput);
                	ps.setString(2, userData);
                
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
        	catch(ClassNotFoundException e){
				e.printStackTrace();
				}
            }
        });  
        add(passwordChange);

        this.back= new JButton("Back");
        this.back.setBounds(280,500,100,40);
        this.back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                dispose();
                userPage up = new userPage("");
                up.setVisible(true);
                up.setLocationRelativeTo(null);
            }
        });
        add(back);
    }    
}
