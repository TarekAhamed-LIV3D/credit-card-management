package Frame;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import Database.*;

public class userVerify extends JFrame{
	private ResultSet rs;
	private JButton back,updateCard;
	private JTextField cardInput;
	static String cardId;
	DefaultTableModel model = new DefaultTableModel();
	JTable jtbl = new JTable(model);

	public userVerify(String text){
		this.setTitle("Update Issued Card");
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(500, 200, 800, 600);
        this.setLayout(null);
        this.initComponents();
	}

	private void initComponents(){
		this.back = new JButton("BACK");
		this.back.setBounds(480,500,100,40);
		this.back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                dispose();
                adminPage ap = new adminPage("");
                ap.setVisible(true);
                ap.setLocationRelativeTo(null);
            }
        });
		add(back);
		
		this.cardInput = new JTextField("INPUT CARD ID");
		this.cardInput.setBounds(500,500,200,50);
        add(cardInput);

        this.updateCard =new JButton("Update");
        this.updateCard.setBounds(480,500,100,40);
        this.updateCard.setOpaque(true);
		this.updateCard.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
               String query = "SELECT `cardid` FROM `cardinformation` WHERE `cardid`=?";
               String card = cardInput.getText();
               System.out.println(card);
               try {
                    PreparedStatement ps = (PreparedStatement)connectForm.getConnection().prepareStatement(query);
                    ps.setString(1, card);
                    rs = ps.executeQuery();
                    if(rs.next()){
                    	cardId = rs.getString(1); 
                    	dispose();
                    	updateCard uc = new updateCard("");
                    	uc.setVisible(true);
                    	uc.setLocationRelativeTo(null);
                    	}
                    else{
                    	JOptionPane.showMessageDialog(null,"Card not Detected");     
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
			add(updateCard);

        	setLayout(new FlowLayout(FlowLayout.CENTER));
        	jtbl.getPreferredScrollableViewportSize();
        	model.addColumn("Card Type");
        	model.addColumn("Card Id");
        	model.addColumn("CCV");
        	model.addColumn("Expiry Date");
        	model.addColumn("Pin");
        	model.addColumn("Id Trace");

        	jtbl.getColumnModel().getColumn(0).setPreferredWidth(300);
        	jtbl.getColumnModel().getColumn(1).setPreferredWidth(100);
        	jtbl.getColumnModel().getColumn(2).setPreferredWidth(100);
        	jtbl.getColumnModel().getColumn(3).setPreferredWidth(200);
        	jtbl.getColumnModel().getColumn(4).setPreferredWidth(100);
        	jtbl.getColumnModel().getColumn(5).setPreferredWidth(100);

        	try{
            	Class.forName("com.mysql.jdbc.Driver");
            	Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/cards", "root", "");
            	PreparedStatement ps = c.prepareStatement("SELECT * FROM `cardinformation`");
            	ResultSet rs = ps.executeQuery();
            	while(rs.next()){
                	model.addRow(new Object[]{rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)});
            		}
        	}
        	catch(Exception e){
            	JOptionPane.showMessageDialog(null,"Database Connection Failure","Fix Database",JOptionPane.ERROR_MESSAGE);
        		}
        	JScrollPane page = new JScrollPane(jtbl);
        	add(page);
        	this.pack();

	}

	public static void main(String[] args){
        userVerify uv = new  userVerify("");
        uv.setVisible(true);
        uv.setLocationRelativeTo(null);
    }
}
