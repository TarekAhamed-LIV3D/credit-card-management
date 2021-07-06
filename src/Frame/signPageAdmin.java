package Frame;

import Database.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class signPageAdmin extends JFrame{
	private JLabel userName,dateofbirth,motherName,id,fatherName,bloodGroup,password;
	private JTextField userNamet,dateofbirtht,nationalIdt,motherNamet,fatherNamet,bloodGroupt,passwordt;
	private JButton update,back;
	private ResultSet rsCheck;

	public signPageAdmin(){
		this.setTitle("Add User");
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(500,200,800,600);
		this.setLayout(null);
		this.initComponents();
	}

	private void initComponents(){
		this.userName = new JLabel("User Name :");
        this.userName.setBounds(100, 100, 300, 40);
        this.userName.setOpaque(false);
        add(userName);
  
        this.fatherName = new JLabel("Father Name :");
        this.fatherName.setBounds(100, 150, 300, 40);
        this.fatherName.setOpaque(false);
        add(fatherName);

        this.motherName = new JLabel("Mother Name :");
        this.motherName.setBounds(100, 200, 300, 40);
        this.motherName.setOpaque(false);
        add(motherName);

        this.id = new JLabel("National ID :");
        this.id.setBounds(100, 250, 300, 40);
        this.id.setOpaque(false);
        add(id);

        this.dateofbirth = new JLabel("Date of Birth :");
        this.dateofbirth.setBounds(100, 300, 300, 40);
        this.dateofbirth.setOpaque(false);
        add(dateofbirth);

        this.bloodGroup = new JLabel("Blood Group :");
        this.bloodGroup.setBounds(100, 350, 300, 40);
        this.bloodGroup.setOpaque(false);
        add(bloodGroup);
        
        
        this.password = new JLabel("Password :");  
        this.password.setBounds(100, 400, 300, 40);
        this.password.setOpaque(false);
        add(password);

        this.userNamet = new JTextField("");
        this.userNamet.setBounds(220, 100, 200, 40);
        this.userNamet.setOpaque(true);
        add(userNamet);

        this.fatherNamet = new JTextField("");
        this.fatherNamet.setBounds(220, 150, 200, 40);
        this.fatherNamet.setOpaque(true);
        add(fatherNamet);

        this.motherNamet = new JTextField("");
        this.motherNamet.setBounds(220, 200, 200, 40);
        this.motherNamet.setOpaque(true);
        add(motherNamet);

        this.nationalIdt = new JTextField("");
        this.nationalIdt.setBounds(220, 250, 200, 40);
        this.nationalIdt.setOpaque(true);
        add(nationalIdt);

        this.dateofbirtht = new JTextField("yyyy-mm-dd");
        this.dateofbirtht.setBounds(220, 300, 200, 40);
        this.dateofbirtht.setOpaque(true);
        add(dateofbirtht);

        this.bloodGroupt = new JTextField("");
        this.bloodGroupt.setBounds(220, 350, 200, 40);
        this.bloodGroupt.setOpaque(true);
        add(bloodGroupt);
        
        this.passwordt = new JTextField("");
        this.passwordt.setBounds(220, 400, 200, 40);
        this.passwordt.setOpaque(true);
        add(passwordt);

        this.back =new JButton("BACK");
        this.back.setBounds(350,450,150,40);
        this.back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                dispose();
                adminPage mp = new adminPage("");
                mp.setVisible(true);
                mp.setLocationRelativeTo(null);
            }
        });
        add(back);

        this.update = new JButton("Update");
        this.update.setBounds(150, 450, 150, 40);
        this.update.addActionListener(new ActionListener(){

        	public void actionPerformed(ActionEvent ae){

        	String userName= userNamet.getText();
            String fatherName = fatherNamet.getText();
            String motherName = motherNamet.getText();
            String id = nationalIdt.getText();
            String dob = dateofbirtht.getText(); 
            String bg = bloodGroupt.getText();
            String pass = passwordt.getText();
             
            System.out.println(userName);
            System.out.println(fatherName);
            System.out.println(motherName);
            System.out.println(id);
            System.out.println(dob);
            System.out.println(bg);
            System.out.println(pass);

            PreparedStatement ps;
            String check= "(SELECT `valid` FROM userinformation WHERE `id`=?) UNION (SELECT `valid` FROM admininformation WHERE `id` =?) UNION (SELECT `valid` FROM managerinformation WHERE `Inputid`= ?)";
            String query="INSERT INTO `userinformation`(`username`, `fathername`,`mothername`,`id`,`dateofbirth`,`bloodgroup`,`password`) VALUES (?,?,?,?,?,?,?)";
            try{
            	PreparedStatement psc = connectForm.getConnection().prepareStatement(check);
            	psc.setString(1, id);
            	psc.setString(2, id);
            	psc.setString(3, id);
            	rsCheck = psc.executeQuery();
            	
            	ps = connectForm.getConnection().prepareStatement(query);
            	ps.setString(1, userName);
            	ps.setString(2, fatherName);
            	ps.setString(3, motherName);
            	ps.setString(4, id);
            	ps.setString(5, dob);
            	ps.setString(6, bg);
            	ps.setString(7, pass);

            	if(!rsCheck.isBeforeFirst()){
            		if(ps.executeUpdate() > 0){
            			JOptionPane.showMessageDialog(null, "Added");
            		}
            		else{
            			JOptionPane.showMessageDialog(null, "Try with another Id");
            		}
            	}
            	}
            	catch(SQLException ex){
            		JOptionPane.showMessageDialog(null, JOptionPane.WARNING_MESSAGE);
            	}
            catch(ClassNotFoundException e){
					e.printStackTrace();
				}
        	}
        });
        add(update);

	}
	public static void main(String[] args) {
		signPage sp = new signPage();
		sp.setVisible(true);
	}
}
