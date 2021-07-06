package Frame;

import com.mysql.jdbc.*;

import Database.connectForm;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.swing.*;

class newInformation extends JFrame {  
    private JComboBox cardList;
    private String[] cardStrings = { "EBL Visa Classic Credit Card", "EBL Visa Gold Credit Card", "EBL Visa Platinum Credit", "Mastercard Titanium Credit", "Mastercard World Credit","EBL Diners Club " };
    private JTextField cardSelectionText;
    private JLabel cardlist,cardselect;
    private JButton update,back;
    private PreparedStatement ps,ps2,ps3,ps4;
    private ResultSet rs2,rs3;
    private String data;
    private int i,k;
    
    public newInformation(){
    	this.setTitle("Issued Card");
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(500, 200, 800, 600);
        this.setLayout(null);
        this.initComponents();
        this.data = loginform.userdata;
    }

    private void initComponents(){
        
        this.cardList = new JComboBox(cardStrings);
        this.cardList.setBounds(200,100,300,40);
        this.cardList.addActionListener(new ActionListener(){   
                public void actionPerformed(ActionEvent ae){           
                cardSelectionText.setText(cardList.getSelectedItem().toString());
            }        
        });        
        add(cardList);
        
        this.cardlist = new JLabel("Available:");
        this.cardlist.setBounds(100,100 , 300, 40);
        this.cardlist.setOpaque(false);
        add(cardlist);
        
        this.cardselect = new JLabel("Card Select:");
        this.cardselect.setBounds(100,200 , 300, 40);
        this.cardselect.setOpaque(false);
        add(cardselect);
        
        this.cardSelectionText =new JTextField("");
        this.cardSelectionText.setBounds(200,200 , 300, 40);
        this.cardSelectionText.setOpaque(true);
        add(cardSelectionText);
        
        this.update =new JButton("Issue");
        this.update.setBounds(200,350,150,40);
        this.update.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){ 
               	String cardType = cardSelectionText.getText();
               	String query = "INSERT INTO `cardinformation`(`cardtype`,`userid`) VALUES (?,?)";
               
                try{
                    ps = (PreparedStatement)connectForm.getConnection().prepareStatement(query);
                    ps.setString(1, cardType);
                    ps.setString(2, data);
                    
                    if(ps.executeUpdate() >0)
                    {
                        JOptionPane.showMessageDialog(null,"Card added");
                        
                        String query1 = "SELECT MAX(`cardid`) FROM `cardinformation`";
                        ps2 = (PreparedStatement)connectForm.getConnection().prepareStatement(query1);
                        rs2 = ps2.executeQuery();
                        rs2.next();
                        i = rs2.getInt(1);
                        System.out.println(i);
                        i++;
                        System.out.println(i);
                        
                        String query3 = "SELECT MAX(`cardnumber`) FROM `cardinformation`";
                        ps4 = (PreparedStatement)connectForm.getConnection().prepareStatement(query3);
                        rs3 = ps4.executeQuery();
                        rs3.next();
                        k = rs3.getInt(1);
                        
                        
                        String query2 = "UPDATE `cardinformation` SET `cardid`=? WHERE `cardnumber` =?";
                        ps3 = (PreparedStatement)connectForm.getConnection().prepareStatement(query2);
                        ps3.setInt(1,i);
                        ps3.setInt(2,k);
                        ps3.executeUpdate();
                        System.out.println(i);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"User not Added");
                    }
                   }
                catch(SQLException ex)
                {
                    JOptionPane.showMessageDialog(null,"Database Connection Failure","Fix Database",JOptionPane.ERROR_MESSAGE);
                }
                catch(ClassNotFoundException e){
					e.printStackTrace();
				}
            }
            
        });
        add(update);

        this.back = new JButton("BACK");
        this.back.setBounds(380,350,150,40);
        this.back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) 
            {
                dispose();
                userPage up = new userPage("");
                up.setVisible(true);
                up.setLocationRelativeTo(null);
            }
        });
        add(back);

    }
    public static void main(String[] args){
        newInformation ni = new newInformation();
        ni.setVisible(true);
    }

}
