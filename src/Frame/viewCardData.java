package Frame;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

public class viewCardData extends JFrame {

    private JButton back;
    DefaultTableModel model = new DefaultTableModel();
    JTable jtbl = new JTable(model);

    public viewCardData(String text){
    	this.setTitle("Card Information");
    	this.setResizable(false);
    	this.setVisible(true);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setBounds(500, 200, 800, 600);
    	this.setLayout(null);
    	this.initComponents();
    }

	private void initComponents(){
		
		this.back= new JButton("BACK");
        this.back.setBounds(480,500,100,40);
        this.back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
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
        model.addColumn("Card Type");
        model.addColumn("Card Id");
        model.addColumn("CCV");
        model.addColumn("Expiry Date");
        model.addColumn("Pin");
        
        jtbl.getColumnModel().getColumn(0).setPreferredWidth(4000);
        jtbl.getColumnModel().getColumn(1).setPreferredWidth(1000);
        jtbl.getColumnModel().getColumn(2).setPreferredWidth(1000);
        jtbl.getColumnModel().getColumn(3).setPreferredWidth(2000);
        jtbl.getColumnModel().getColumn(4).setPreferredWidth(1000);
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/cards", "root", "");
            PreparedStatement ps = c.prepareStatement("SELECT * FROM `cardinformation`");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)});
            }
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(null,"Database Connection Failure","Fix Database",JOptionPane.ERROR_MESSAGE);
        }
        JScrollPane pg = new JScrollPane(jtbl);
        add(pg);
        this.pack();
    }

    public static void main(String[] args){
        viewCardData vcd = new viewCardData("");
        vcd.setVisible(true);
        vcd.setLocationRelativeTo(null);
    }
 } 
