package Frame;

import Database.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

public class loginform extends JFrame {
	private JButton login,back;
	private JLabel username,passwordl;
	private JCheckBox showPassword;
	private JTextField userinput;
	private JPasswordField passwordf;
	protected static String admindata, userdata, managerdata;
	
	public loginform() {
		this.setTitle("Login Form");
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(500, 200, 800, 600);
		this.setLayout(null);
		this.initComponents();
	}
	private void initComponents(){
		this.login = new JButton("Login");
		this.login.setBounds(500,360,80,40);
		this.add(login);

		this.back = new JButton ("Exit");
		this.back.setBounds(600,360,80,40);
		this.add(back);

		this.showPassword = new JCheckBox("Show Password");
		this.showPassword.setBounds(498,325,200,30);
		this.showPassword.setToolTipText("Show Password");
		this.showPassword.setOpaque(false);
		this.add(showPassword);
		
		this.username = new JLabel("User Id");
		this.username.setBounds(500, 150, 200, 50);
		this.username.setOpaque(false);
		this.add(username);

		this.userinput = new JTextField("");
		this.userinput.setBounds(500, 190, 200, 30);
		this.userinput.setOpaque(false);
		this.add(userinput);

		this.passwordl = new JLabel("Password");
		this.passwordl.setBounds(500, 250, 200, 50);
		this.passwordl.setOpaque(false);
		this.add(passwordl);

		this.passwordf = new JPasswordField("");
		this.passwordf.setBounds(500, 290, 200, 30);
		this.passwordf.setOpaque(false);
		this.add(passwordf);

		this.login.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				
				String id = userinput.getText();
				String password = String.valueOf(passwordf.getPassword());

				String check = "(SELECT `type` FROM userinformation WHERE `id` = ?) UNION (SELECT `type` FROM admininformation WHERE `id` = ?) UNION (SELECT `type` FROM managerinformation WHERE `Inputid` = ?)";

				String userQ = "SELECT * FROM `userinformation` WHERE `id` =? AND `password` =?";
				String userQ2 = "SELECT `userid` FROM `userinformation` WHERE `id`=?";

				String adminQ = "SELECT * FROM `admininformation` WHERE `id` =? AND `password` =?";
				String adminQ2 = "SELECT `adminid` FROM `admininformation` WHERE `id`=?";

				String managerQ = "SELECT * FROM `managerinformation` WHERE `Inputid` =? AND `password` =?";
				String managerQ2 = "SELECT `managerid` FROM `managerinformation` WHERE `Inputid`=?";

				try{
				ResultSet admin1,admin2,rsType,user,user2,manager1,manager2;
                int type;
                
                PreparedStatement psType= connectForm.getConnection().prepareStatement(check);
                psType.setString(1, id);
                psType.setString(2, id);
                psType.setString(3, id);
                rsType= psType.executeQuery();      
                rsType.next();
                type= rsType.getInt(1);         
                                    
                PreparedStatement psUser=connectForm.getConnection().prepareStatement(userQ);
                PreparedStatement psUser2=connectForm.getConnection().prepareStatement(userQ2);
                psUser.setString(1, id);
                psUser.setString(2, password);
                psUser2.setString(1, id);    
                
                user=psUser.executeQuery();
                user2=psUser2.executeQuery();
                psUser2.closeOnCompletion();
                psUser.closeOnCompletion();
                
                PreparedStatement ps = connectForm.getConnection().prepareStatement(adminQ);
                PreparedStatement ps2 = connectForm.getConnection().prepareStatement(adminQ2);

                ps.setString(1, id);
                ps.setString(2, password);
                ps2.setString(1, id);
                
                admin1 = ps.executeQuery();
                admin2=ps2.executeQuery();
                

                PreparedStatement psmanager = connectForm.getConnection().prepareStatement(managerQ);
                PreparedStatement psmanager2 = connectForm.getConnection().prepareStatement(managerQ2);
        
                psmanager.setString(1, id);
                psmanager.setString(2, password);
                psmanager2.setString(1, id);

                manager1 = psmanager.executeQuery();
                manager2 = psmanager2.executeQuery();


					switch (type){
						case 1:
						if(manager1.next()){
							JOptionPane.showMessageDialog(null, "Logged into Manager Account");
							managerPage mp = new managerPage("");
							mp.setVisible(true);
							mp.setLocationRelativeTo(null);
							dispose();
							manager2.next();
							managerdata = manager2.getString(1);
							System.out.println(managerdata);
						}
						else{
							JOptionPane.showConfirmDialog(null, "Worng Password. Please Try Again");
						}
							break;

						case 2:
						if(admin1.next()){
							JOptionPane.showMessageDialog(null, "Logged into Admin Account");
							adminPage ap = new adminPage("");
							ap.setVisible(true);
							ap.setLocationRelativeTo(null);
							dispose();
							admin2.next();
							admindata = admin2.getString(1);
							System.out.println(admindata);
						}
						else{
							JOptionPane.showConfirmDialog(null,"Worng Password. Please Try Again");
						}
						break;

						case 3:
						if(user.next()){
							JOptionPane.showMessageDialog(null, "Logged into User Account");
							userPage up = new userPage("");
							up.setVisible(true);
							up.setLocationRelativeTo(null);
							dispose();
							user2.next();
							userdata = user2.getString(1);
							System.out.println(userdata);
						}
						else{
							JOptionPane.showConfirmDialog(null, "Worng Password. Please Try Again");
						}
						break;
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

		this.showPassword.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				if(showPassword.isSelected()){
					passwordf.setEchoChar((char)0);
				}
				else{
					passwordf.setEchoChar('*');
				}
			}
		});

		this.back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				dispose();
				StartUp su = new StartUp();
				su.setVisible(true);
				su.setLocationRelativeTo(null);
				}
			});
	}

	public static void main(String[] args){
		loginform lf = new loginform();
		lf.setVisible(true);
	}
	
}