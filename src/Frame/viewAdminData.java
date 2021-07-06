package Frame;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

public class viewAdminData extends JFrame {

    private JButton back;
    DefaultTableModel model = new DefaultTableModel();
    JTable jtbl = new JTable(model);

    public viewAdminData(String text){

    	setTitle("Admin Information");
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
            public void actionPerformed(ActionEvent ae) 
            {
                dispose();
                managerDataStore mds = null;
                mds = new managerDataStore("");
                mds.setVisible(true);
                mds.setLocationRelativeTo(null);
            }
        });
        add(back);
    	
        setLayout(new FlowLayout(FlowLayout.CENTER));           
        jtbl.getPreferredScrollableViewportSize();
        model.addColumn("Admin Name");
        model.addColumn("Father Name");
        model.addColumn("Mother Name");
        model.addColumn("ID");
        model.addColumn("DOB");
        model.addColumn("Blood Group");
        
        jtbl.getColumnModel().getColumn(0).setPreferredWidth(200);
        jtbl.getColumnModel().getColumn(1).setPreferredWidth(150);
        jtbl.getColumnModel().getColumn(2).setPreferredWidth(150);
        jtbl.getColumnModel().getColumn(3).setPreferredWidth(150);
        jtbl.getColumnModel().getColumn(4).setPreferredWidth(150);
        jtbl.getColumnModel().getColumn(5).setPreferredWidth(150);
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/cards", "root", "");
            PreparedStatement ps = c.prepareStatement("SELECT `adminname`, `fatherName`, `motherName`, `id`, `dateofbirth`, `bloodgroup` FROM `admininformation`");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)});
            }
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,"Database Connection Failure","Fix Database",JOptionPane.ERROR_MESSAGE);
        }
        JScrollPane pg = new JScrollPane(jtbl);
        add(pg);
        this.pack();
    }
     public static void main(String[] args) {
        viewAdminData vad = new viewAdminData("");
        vad.setVisible(true);
        vad.setLocationRelativeTo(null);
     }
 }