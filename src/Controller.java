import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.Insets;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultEditorKit;
import javax.swing.tree.DefaultTreeModel;

import org.omg.CORBA.portable.InputStream;



import java.awt.GridLayout;
import java.awt.Image;


import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.Button;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.KeyEvent;
public class Controller  {
	JFrame frame;
	JMenuBar menuBar;
	JMenu menu;
	private JMenu mnFile;
	private JMenuItem openScript;
	private JMenuItem runScript;
	private JMenuItem saveScipt;
	private JMenu mnEdit;
	private JMenuItem mntmCopy;
	private JMenuItem mntmPaste;
	private JMenuItem mntmPreferences;
	private JMenu mnHelp;
	private JMenuItem mntmAbout;
	private JTree tree;
	private JButton sweepButton, btnRunAll;
	private JPanel bigPanel;
	private JScrollPane SQLTextPane, successScrollPane, bigPanelScroll;
	private GroupLayout groupLayout;
	private JTextArea sqlTextArea, saveFile;
	private JScrollPane scrollPane, errorScrollPane;
	JFrame newFrame;
	 
	//private JTextArea txtrOutputFromCommands;
	private JTextPane successText, errorText, txtrOutputFromCommands;
	private JButton runOne, saveButton, cancelButton, openButton;
	public 	File f = new File("tempUsers.txt");
	
	FileWriter writer;
	private JLabel label;
	private JLabel label_1;
	
	
	public Controller() {
		
		frame = new JFrame("My SQL Workbench - Project 2");
	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	   //  frame.setBounds(0,0,screenSize.width, screenSize.height);
		//frame.setSize(1000, 1000);
		
		
		
		
		createAndAddMenuOptions();
		createAndAddTree();
		//creates the scolling panels which will be used within the methods
		 SQLTextPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		 SQLTextPane.setName("SQL Text");
		 
		 
		 successScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		 
		 scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		 bigPanelScroll = new JScrollPane();
		
		 bigPanel = new JPanel();
		
		 bigPanelScroll.setViewportView(bigPanel);
		 
		  errorScrollPane = new JScrollPane();
		 
			createImageButtons();
			 CreateErrorTextArea();
			 CreateSuccessTextArea();

		
		//createScrollBarForTextField();
		 //contains the formating of all the panels which will then be added to the frame
//layouting
		 groupLayout = new GroupLayout(bigPanel);
		 groupLayout.setHorizontalGroup(
		 	groupLayout.createParallelGroup(Alignment.LEADING)
		 		.addGroup(groupLayout.createSequentialGroup()
		 			.addContainerGap()
		 			.addComponent(tree, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
		 			.addPreferredGap(ComponentPlacement.RELATED)
		 			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
		 				.addComponent(SQLTextPane, GroupLayout.DEFAULT_SIZE, 1686, Short.MAX_VALUE)
		 				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1686, Short.MAX_VALUE)
		 				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
		 					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
		 						.addComponent(label)
		 						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
		 					.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
		 					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
		 						.addComponent(errorScrollPane, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 1618, GroupLayout.PREFERRED_SIZE)
		 						.addComponent(successScrollPane, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 1618, GroupLayout.PREFERRED_SIZE))))
		 			.addGap(76))
		 );
		 groupLayout.setVerticalGroup(
		 	groupLayout.createParallelGroup(Alignment.LEADING)
		 		.addGroup(groupLayout.createSequentialGroup()
		 			.addContainerGap()
		 			.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
		 				.addComponent(tree, GroupLayout.DEFAULT_SIZE, 1010, Short.MAX_VALUE)
		 				.addGroup(groupLayout.createSequentialGroup()
		 					.addComponent(SQLTextPane, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE)
		 					.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
		 					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 388, GroupLayout.PREFERRED_SIZE)
		 					.addPreferredGap(ComponentPlacement.RELATED)
		 					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
		 						.addGroup(groupLayout.createSequentialGroup()
		 							.addGap(21)
		 							.addComponent(successScrollPane, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
		 							.addGap(8)
		 							.addComponent(errorScrollPane, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
		 						.addGroup(groupLayout.createSequentialGroup()
		 							.addGap(54)
		 							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
		 							.addGap(52)
		 							.addComponent(label, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)))
		 					.addGap(28)))
		 			.addContainerGap())
		 );
		 
		
		 
		 
	
		 

		 
		 
		
		
		 
		
		
	
		
	
		// bigPanelScroll.setViewportView(bigPanel);
		CreateSQLTextField();//has to be after the scroll pane is created
	
		 CreateOutputTextArea();
		
			
			
			frame.getContentPane().add(bigPanelScroll);
		frame.pack();
		//frame.  setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
		
	}
	public void CreateOutputTextArea() {
		txtrOutputFromCommands  = new JTextPane();
		txtrOutputFromCommands.setEditable(false);
		txtrOutputFromCommands.setText("Output:\n");
		scrollPane.setViewportView(txtrOutputFromCommands);
		
	}
public void CreateErrorTextArea() {
	 label = new JLabel("");
	// label.setIcon(new ImageIcon(Controller.class.getResource("/Images/Error Icon.png")));
	  errorText = new JTextPane();
	  errorText.setText("Error Log");
	  errorText.setEditable(false);
	 errorScrollPane.setViewportView(errorText);
}


public void CreateSuccessTextArea() {
	 label_1 = new JLabel("");
//	 label_1.setIcon(new ImageIcon(Controller.class.getResource("/Images/Status Icon.png")));
	successText = new JTextPane();
	successText.setText("Success Log");
	successText.setEditable(false);
	successScrollPane.setViewportView(successText);
}	





	public void createAndAddTree() {
		tree = new JTree();
	
		
		
		
		tree.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		
		
			
		
			
			
	}
	public void CreateSQLTextField(){
		sqlTextArea = new JTextArea();
		sqlTextArea.setWrapStyleWord(true);
		sqlTextArea.setLineWrap(true);
		sqlTextArea.setEditable(true);
	
		SQLTextPane.setViewportView(sqlTextArea);
		
			bigPanel.setLayout(groupLayout);
		
		
		
	}
	public void createImageButtons() { //////////////Creates Buttons on the menu barwith actionlistners
		
		try {
			  
			 //// // adds the run all button
			  
		     btnRunAll = new JButton();
		     btnRunAll.setIcon(new ImageIcon(Controller.class.getResource("/Images/1 command.png")));
		   System.out.println( btnRunAll.getIcon().toString());
		     btnRunAll.setToolTipText("Run all SQL Commands in textbox");
		     btnRunAll.setPreferredSize(new Dimension(9, 33));

		  //  URL url = tester.class.getResource("images/img.png");
		  //  ImageIcon ii = new ImageIcon(url);
		    //btnRunAll.setPreferredSize(new Dimension(1, 2));
		  //  btnRunAll.setMargin(new Insets(0, 0, 0, 0));
		    menuBar.add(btnRunAll);
		    btnRunAll.addActionListener(new MenuListener());
	 
		 
		 
		 
		  ////////////adds the run 1 button
		  runOne = new JButton();
		  runOne.setToolTipText("Run Highlighted Portion");
		  runOne.setPreferredSize(new Dimension(9, 33));
		  runOne.setMargin(new Insets(0, 0, 0, 0));
		  runOne.setIcon(new ImageIcon(Controller.class.getResource("/Images/1 command.png")));
		  menuBar.add(runOne);
		  runOne.addActionListener(new MenuListener());
			  
		
		  //////adds the sweep icon
		  sweepButton = new JButton();
			  sweepButton.setLocation(new Point(66, 0));
			  sweepButton.setMargin(new Insets(2, 6, 2, 6));
			  sweepButton.setToolTipText("Removes SQL text commands");
			//  sweepButton.setPreferredSize(new Dimension(1, 2));
			  
		  sweepButton.setIcon(new ImageIcon(Controller.class.getResource("/Images/sweep.png")));//fix image
		  
	
		menuBar.add(sweepButton);
		//frame.setJMenuBar(menuBar);
		sweepButton.addActionListener(new MenuListener());
		
		
		
		}catch(Exception e) {
			System.out.println("Image was not found.");
		}
	}
	
	public void createAndAddMenuOptions() {
		
		
		menuBar = new JMenuBar();
		menuBar.setMinimumSize(new Dimension(0, 1));
		menuBar.setMaximumSize(new Dimension(0, 1));
		frame.setJMenuBar(menuBar);
		menu = new JMenu("Menu");
		menuBar.add(menu); //creates menu on menubar
		
		
		
		//////////////////////////everything under file
		mnFile = new JMenu("File");
		menu.add(mnFile);
		
		openScript = new JMenuItem("Open SQL script");
		mnFile.add(openScript);
		
		openScript.addActionListener(new MenuListener());
		runScript = new JMenuItem("Run SQL script");
		mnFile.add(runScript);
		
		runScript.addActionListener(new MenuListener());
		saveScipt = new JMenuItem("Save SQL script");
		mnFile.add(saveScipt);
		saveScipt.addActionListener(new MenuListener());
		
		
		///////////everything under edit bar
		
		mnEdit = new JMenu("Edit");
		menu.add(mnEdit);
		
		mntmCopy = new JMenuItem( new DefaultEditorKit.CopyAction()); //uses the already created copy method
		mntmCopy.setText("Copy");
		mntmCopy.setMnemonic(KeyEvent.VK_COPY);
		mnEdit.add(mntmCopy);
		//mntmCopy.addActionListener(new MenuListener());
		
		
		mntmPaste = new JMenuItem(new DefaultEditorKit.PasteAction());
		mntmPaste.setText("Paste");
		
		mntmPaste.setMnemonic(KeyEvent.VK_PASTE);
		mnEdit.add(mntmPaste);
	//	mntmPaste.addActionListener(new MenuListener());
		
		
		mntmPreferences = new JMenuItem("Preferences");
		mnEdit.add(mntmPreferences);
		mntmPreferences.addActionListener(new MenuListener());
		
		/////////////////////////////Help 
		
		mnHelp = new JMenu("Help");
		menu.add(mnHelp);
		
		
		mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
	
		
		
		
		
		


		
	
		mntmAbout.addActionListener(new MenuListener());
		
		
	}
	
	

	public static void main(String [] args) {
		DBCommands command = new DBCommands();//populates the static array's  in DBCommands with the directory when it i
		Controller setup = new Controller();	


	}
	
	
	
	
	
	
	private class MenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == openScript) {
				FileNameExtensionFilter filter = new FileNameExtensionFilter(".sql", ".txt");
				JFileChooser chooser = new JFileChooser();
				 chooser.setFileFilter(filter);
				 int returnVal = chooser.showOpenDialog(frame);
				    if(returnVal == JFileChooser.APPROVE_OPTION) {
				        System.out.println("You chose to open this file: " +
				             chooser.getSelectedFile().getName());
				        
				    	String file = chooser.getSelectedFile().getName();
						f = new File(file);
						try {
						Scanner infile = new Scanner(f);
						txtrOutputFromCommands.setText("");
						while(infile.hasNextLine()) {
							String line = infile.nextLine();
							sqlTextArea.setText(sqlTextArea.getText() + line);
						}
						}catch(Exception x) {
							System.out.println("error opening");
				    }
			}
		}
						/*JPanel container = new JPanel(new BorderLayout());
						JPanel savePane = new JPanel();
						 openButton = new JButton("Open");
						 cancelButton = new JButton("Cancel");
						 openButton.addActionListener(new ButtonListener());
						cancelButton.addActionListener(new ButtonListener());
						 saveFile = new JTextArea(1, 30);
						JLabel saveLabel = new JLabel("Insert filename to open:");
						
						savePane.add(saveLabel);
						savePane.add(saveFile);
						savePane.add(openButton);
						savePane.add(cancelButton);
					
						
						savePane.setSize(new Dimension(500, 500));
						newFrame = new JFrame();
						 newFrame.setSize(375,150);
						newFrame.setTitle("Open File");
						newFrame.getContentPane().add(savePane);
						
						newFrame.setVisible(true);*/
						
						
					
				    
				  
		
			
			if(e.getSource() == runScript) {//WORKIGN!
				
				//File f = new File("tempUsers.txt");
				try {
					
				f.createNewFile();
				FileWriter writer = new FileWriter(f, false);
				//writer.write(""); //clears the memory before use.
				writer.write(sqlTextArea.getText());
				writer.close();
				}catch(Exception z) {
					System.out.println("cannot create the f file.");
				}
				Database data = new Database(f,successText, errorText, txtrOutputFromCommands );
				
				
			
			}
			if(e.getSource() == saveScipt) {//gotta get the name of the file from user. and create string input which will then be used with the file.
				 newFrame = new JFrame();
				 newFrame.setSize(375,150);
				JPanel container = new JPanel(new BorderLayout());
				JPanel savePane = new JPanel();
				 saveButton = new JButton("Save");
				 cancelButton = new JButton("Cancel");
				 saveButton.addActionListener(new ButtonListener());
				cancelButton.addActionListener(new ButtonListener());
				 saveFile = new JTextArea(1, 30);
				JLabel saveLabel = new JLabel("Insert filename to save to:");
				
				savePane.add(saveLabel);
				savePane.add(saveFile);
				savePane.add(saveButton);
				savePane.add(cancelButton);
			
				
				savePane.setSize(new Dimension(300, 300));
				newFrame.setTitle("Save File");
				newFrame.getContentPane().add(savePane);
				
				newFrame.setVisible(true);
			
				
			
			}
			

			if(e.getSource() == mntmPreferences) {
				JFrame newFrame = new JFrame();
				newFrame.setSize(550, 300);
				newFrame.getContentPane().add(new Preferences());
				newFrame.setResizable(false);
			//	newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				
				newFrame.setVisible(true);
				
			
				

				
				
				
				
				
			}
			if(e.getSource() == mntmAbout) {
			
				
				JOptionPane about = new JOptionPane();
				
				JOptionPane.showMessageDialog(about, "This project is being used to test our \nunderstanding and knowledge of GUI interactions.\n	Created By Kevin Linnane");

			
				
				//frame.getContentPane().add(dialog);
			}
			if(e.getSource() == sweepButton) {//working
				sqlTextArea.setText("");
			}
			if(e.getSource() == btnRunAll) { //eoworking
			
				try {
					txtrOutputFromCommands.setText("Output:\n");
				f.createNewFile();
				 writer = new FileWriter(f, false);
				//writer.write(""); //clears the memory before use.
				writer.write(sqlTextArea.getText());
				writer.close();
				Database data = new Database(f,successText, errorText, txtrOutputFromCommands );
				writer.write("");
				writer.close();
				}catch(Exception z) {
					System.out.println("cannot create the f file.");
				}
				
			
			
			}
			if(e.getSource() == runOne) {
				
				String highlightedArea = sqlTextArea.getSelectedText();
				
				try {
					txtrOutputFromCommands.setText("Output:\n");
				f.createNewFile();
				 writer = new FileWriter(f, false);
				//writer.write(""); //clears the memory before use.
				writer.write(highlightedArea.trim());
				writer.close();
				writer.write("");
				writer.close();
				}catch(Exception z) {
					System.out.println("cannot create the f file.");
				}
				//Database data = new Database(f,successText, txtrOutputFromCommands );
	
				
			}
		}
		
	}
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == saveButton) {
				try {
					f = new File(saveFile.getText()+ ".sql");
					f.createNewFile();
					 writer = new FileWriter(f, false);
					//writer.write(""); //clears the memory before use.
					writer.write(sqlTextArea.getText());
					writer.close();
					newFrame.dispose();
					}catch(Exception z) {
						System.out.println("cannot create the f file.");
					}
			}
			if(e.getSource() == cancelButton) {
				newFrame.dispose();
			}
	if(e.getSource() == openButton) {
		String file = saveFile.getText();
				f = new File(saveFile.getText()+".sql");
				try {
				Scanner infile = new Scanner(f);
				txtrOutputFromCommands.setText("");
				while(infile.hasNextLine()) {
					String line = infile.nextLine();
					sqlTextArea.setText(sqlTextArea.getText() + line);
				}
				newFrame.dispose();
				
				}catch(Exception x) {
					System.out.println("File does not exist.  Cannot use Scanner with it, or isnt sql.");
				}
			}
		}
		
	}
}
