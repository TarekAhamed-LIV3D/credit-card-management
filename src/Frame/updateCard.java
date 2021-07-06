package Frame;

import Database.connectForm;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

class updateCard extends JFrame {
    JLabel Card_Id, ccv, expiry_date, pin,TracedID;
    JTextField Card_IdText, ccvText, expiry_dateText, pinText;
    JButton Update,Back;
    PreparedStatement ps;
    String C_ID = userVerify.cardId;
    public updateCard(String text){

    	setTitle("Card Number " +C_ID);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 800, 600);
        setLayout(null);
    	initComponents();

    }

    private void initComponents() {

         Card_Id= new JLabel("CARD ID :");
        Card_Id.setBounds(100, 150, 300, 40);
        Card_Id.setOpaque(false);
        add(Card_Id);

        ccv = new JLabel("CCV :");
        ccv.setBounds(100, 200, 300, 40);
        ccv.setOpaque(false);
        add(ccv);

        expiry_date = new JLabel("Expiry Date:");
        expiry_date.setBounds(100, 250, 300, 40);
        expiry_date.setOpaque(false);
        add(expiry_date);


        pin = new JLabel("pin :");
        pin.setBounds(100, 300, 300, 40);
        pin.setOpaque(false);
        add(pin);

        TracedID = new JLabel(C_ID);
        TracedID.setBounds(220, 150, 200, 40);
        TracedID.setOpaque(false);
        add(TracedID);

        ccvText = new JTextField("");
        ccvText.setBounds(220, 200, 200, 40);
        ccvText.setOpaque(true);
        add(ccvText);

        expiry_dateText = new JTextField("yyyy-mm-dd");
        expiry_dateText.setBounds(220, 250, 200, 40);
        expiry_dateText.setOpaque(true);
        add(expiry_dateText);

        pinText = new JTextField("");
        pinText.setBounds(220, 300, 200, 40);
        pinText.setOpaque(true);
        add(pinText);


        JButton Back =new JButton("BACK");
        Back.setBounds(350,450,150,40);
        add(Back);
        Back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) 
            {
                dispose();
                userVerify UL = new userVerify("");
                UL.setVisible(true);
                UL.setLocationRelativeTo(null);
            }
        });
        
        Update =new JButton("Update");
        Update.setBounds(150,450,150,40);
        add(Update);
        
        Update.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) 
            {
             
                    String Card_Id = C_ID;
                    String ccv = ccvText.getText();
                    String expiry_date = expiry_dateText.getText();
                    String pin = pinText.getText();
                    
                    
                
                    System.out.println(Card_Id);
                    System.out.println(ccv);
                    System.out.println(expiry_date);
                    System.out.println(pin);
                    System.out.println(C_ID);
                    
                    String query = "UPDATE `cardinformation` SET `cardid`=?,`ccv`=?,`expireddate`=?,`pin`=? WHERE `cardid` =?";
                try{
                    ps = (PreparedStatement)connectForm.getConnection().prepareStatement(query);
       
                    ps.setString(1,Card_Id);
                    ps.setString(2,ccv);
                    ps.setString(3,expiry_date);
                    ps.setString(4,pin);
                    ps.setString(5,C_ID);
                    
                    if(ps.executeUpdate() >0)
                    {
                        JOptionPane.showMessageDialog(null,"Card Updated");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Card not Updated");
                    }
                   }
                catch(SQLException ex)
                {
                    JOptionPane.showMessageDialog(null,"Format Error","Fix Entry",JOptionPane.WARNING_MESSAGE);
                } catch (ClassNotFoundException e){
					e.printStackTrace();
				}
            }
        });   
    }

    public static void main(String[] args) {
        updateCard S = new updateCard("");
        S.setVisible(true);
    }

}
