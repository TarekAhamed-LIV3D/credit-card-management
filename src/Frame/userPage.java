package Frame;


import java.awt.event.*;
import javax.swing.*;

public class userPage extends JFrame{
	private JButton addDocument,storage,back,changePassword;

	public userPage(String text){
		this.setTitle("User");
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(500, 200, 800, 600);
		this.setLayout(null);
		this.initComponents();
	}

	private void initComponents(){
        this.addDocument = new JButton("Add Information");
        this.addDocument.setBounds(500,150,200,40);
        this.addDocument.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				dispose();
				newInformation ni = new newInformation();
				ni.setVisible(true);
				ni.setLocationRelativeTo(null);
			}
		});
		add(addDocument);

		this.storage = new JButton("View All Documents");
		this.storage.setBounds(500,200,200,40);
		this.storage.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                dispose();
                allDocument ad = new allDocument("");
                ad.setVisible(true);
                ad.setLocationRelativeTo(null);
            }
        });
        add(storage);

        this.back=new JButton("LOG OUT");
        this.back.setBounds(500,350,200,40);
        this.back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                dispose();
                loginform lf = new loginform();
                lf.setVisible(true);
                lf.setLocationRelativeTo(null);
            }
        });
        add(back);

        this.changePassword = new JButton("Change Password");
        this.changePassword.setBounds(500,250 , 200, 50);
        this.changePassword.addActionListener(new ActionListener(){
		 public void actionPerformed(ActionEvent ae) 
            {
                dispose();
                changeUserPassword cup = new changeUserPassword("");
                cup.setVisible(true);
                cup.setLocationRelativeTo(null);
            }
        });
        add(changePassword);

	}

	public static void main(String[] args) {
		userPage up = new userPage("");
		up.setVisible(true);
	}
}
