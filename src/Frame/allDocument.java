package Frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

public class allDocument extends JFrame {
    ResultSet rs;
    private JButton back;
    DefaultTableModel model = new DefaultTableModel();
    JTable jtbl = new JTable(model);
    String idno = loginform.userdata;
    
    public allDocument(String text){
    	setTitle("View Document");
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 800, 600);
        setLayout(null);
        initComponents();
    }

   	private void initComponents(){
   		
        this.back= new JButton("BACK"); 
        this.back.setBounds(480,500,100,40);
        this.back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                dispose();
                userPage up = new userPage("");
                up.setVisible(true);
                up.setLocationRelativeTo(null);
            }
        });
        add(back);
   		
    	setLayout(new FlowLayout(FlowLayout.CENTER));           
        jtbl.getPreferredScrollableViewportSize();
        model.addColumn("Card Type");
        model.addColumn("Card ID");
        model.addColumn("ccv");
        model.addColumn("expirydate");
        model.addColumn("pin");

        jtbl.getColumnModel().getColumn(0).setPreferredWidth(4000);
        jtbl.getColumnModel().getColumn(1).setPreferredWidth(1000);
        jtbl.getColumnModel().getColumn(2).setPreferredWidth(1000);
        jtbl.getColumnModel().getColumn(3).setPreferredWidth(2000);
        jtbl.getColumnModel().getColumn(4).setPreferredWidth(1000);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/cards", "root", "");
            PreparedStatement ps = c.prepareStatement("SELECT * FROM `cardinformation` WHERE `userid` = ? ");
            ps.setString(1, idno);           
            System.out.println(idno);           
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
          
                model.addRow(new Object[]{rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)});
            
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Database Connection Failure","Fix Database",JOptionPane.ERROR_MESSAGE);
        }
        JScrollPane pg = new JScrollPane(jtbl);
        add(pg);
        this.pack();
    }
   	
     public static void main(String[] args) {
        allDocument ad = new allDocument("");
        ad.setVisible(true);
        ad.setLocationRelativeTo(null);
    }   
 } 