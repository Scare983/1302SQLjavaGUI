import javax.swing.JPanel;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import java.awt.FlowLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import java.awt.Font;
import javax.swing.JTextArea;


import java.awt.Dimension;

import javax.swing.ButtonGroup;


public class Preferences extends JPanel {

	Preferences() {
		this.setSize(new Dimension(426, 300));
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		
		JLabel lblSelectFontFor = new JLabel("Select Font for Text:");
		add(lblSelectFontFor);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Helvetica");
		rdbtnNewRadioButton.setFont(new Font("HelveticaNeueLT Pro 55 Roman", Font.PLAIN, 11));
		add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Arial");
		rdbtnNewRadioButton_1.setFont(new Font("Arial", Font.PLAIN, 11));
		add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Times New Roman");
		rdbtnNewRadioButton_2.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		add(rdbtnNewRadioButton_2);
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnNewRadioButton);
		group.add(rdbtnNewRadioButton_1);
		group.add(rdbtnNewRadioButton_2);
		
		JLabel lblNewLabel = new JLabel("Auto-save script interval:  ");
		add(lblNewLabel);
		
		
	String  [] seconds = {"2", "4", "6", "8", "10", "12", "14", "16", "18"};
	int i =0;
	for(String a : seconds) {
		seconds[i] = a+ " seconds";
		i++;
	}
	JComboBox box = new JComboBox(seconds);
	box.setSelectedItem(4);
	add(box);
	
	JCheckBox chckbxSaveOnClose = new JCheckBox("Always Save Into FIle On Close");
	chckbxSaveOnClose.setSelected(true);
	add(chckbxSaveOnClose);
	
	JCheckBox chckbxNewCheckBox = new JCheckBox("Show Schema Contents in Schema Tree");
	chckbxNewCheckBox.setSelected(true);
	add(chckbxNewCheckBox);
	
	JCheckBox chckbxShowInteralSchema = new JCheckBox("Show Interal Schema");

	
	
	add(chckbxShowInteralSchema);
	//	JList list = new JList(listModel);
		//list.setVisibleRowCount(-1);
		///a/dd(list);
	JLabel label1 = new JLabel("DBMS Connection keep-alive interval(in seconds) ");
	JLabel label2 = new JLabel("DBMS Connection read time out(in seconds) ");
	JLabel label3 = new JLabel("DBMS Connection time out(in seconds) ");
	JPanel panel = new JPanel();
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JTextArea area1 = new JTextArea("600", 1, 20);
	JTextArea area2 = new JTextArea("600",1,20);
	JTextArea area3 = new JTextArea("600",1,20 );
		panel.add(label1);
		
		panel.add(area1);
		panel1.add(label2);
		panel1.add(area2);
		panel2.add(label3);
		panel2.add(area3);
		add(panel);
		add(panel1);
		add(panel2);
	}
	
	
	
	
}
