
import java.io.BufferedWriter;
import java.io.File;
import java.util.Scanner;
import javax.swing.JTextPane;
import java.io.FileWriter;

public class Database

{ 
	public String command;
	File newFile;
	public Database(File f, JTextPane successLog, JTextPane errorLog, JTextPane output)
   {
      // *** declare the DBUSER commands file
    
      // ** create empty error log file
      // ** create empty status log file
    
      try
      {
    
    	  
      // declare the DBcomm.getMessage() string 
    
      // open dbuser file by creating a file object
    	 
      Scanner infile = new Scanner(f);
      
      // declare the status log file
      File statfile = new File("status.txt");	
      statfile.createNewFile();
      // declare the error log file
      File errfile = new File("error.txt");
      errfile.createNewFile();
      //  *** create an instance of the DBCommands class
      DBCommands DBcomm = new DBCommands(output);
    
       newFile = new File("stores everything on nextLine.txt");
      newFile.createNewFile();
      FileWriter fw = new FileWriter(newFile, true);
      BufferedWriter out = new BufferedWriter(fw);
    try {  while(infile.hasNextLine()) { //makes a space 
    	   command = infile.nextLine();
    	  
    	  for(int i = 0;i< command.length(); i++) {
    	  if(command.charAt(i) == ';') {
    	
    		out.write(command.substring(0,i));
    		out.newLine();
    		  command = command.substring(i+1).trim();
    		  i=0;
    		  
    		 
    	  }
    	}
    
      }}catch(Exception e) {
    	  System.out.println("Ran out of lines to test for ;");
      }
    out.close();
    infile.close();
    infile = new Scanner(newFile);
    //  ***  Loop until EOF DBUSER file
     // PrintWriter statpw = new PrintWriter(statfile);
    //  PrintWriter errpw = new PrintWriter(errfile.getAbsolutePath());
  
     // BufferedWriter statpw = new BufferedWriter(statfw);
     // BufferedWriter errpw.write = new BufferedWriter(errpw.write);
     
	  while (infile.hasNextLine()) 
	  {
		  
		   FileWriter statpw = new FileWriter(statfile, true);
		      FileWriter errpw = new FileWriter(errfile, true);
//		 DBcomm.CreateDatabase("hello");
		 
	//	 DBcomm.CreateTable("hello", "student");
	//	 System.out.println(DBcomm.Insert("\"Hello\"", "hello", "student"));
	//	// System.out.println(DBcomm.Delete("college", "student"));
	//	 System.out.println(DBcomm.CreateDatabase("names"));
	//	 System.out.println(DBcomm.DropTable("hello", "student"));
	//	 System.out.println(DBcomm.CreateTable("noWhat", "working?"));
		// DBcomm.Select("college", "student", statfile, DBcomm.getMessage());
		 
		 
	    //  ***  read and parse each command string
	     command = infile.nextLine();
	    // String command = nextline()
	    //  ***  Finish this conditionals to test each command string
	    if (command.substring(0, 15).equalsIgnoreCase("CREATE DATABASE"))
	    {
		  // finish call to create database method pass database name
		  if (DBcomm.CreateDatabase(command.substring(16)))
		  {
			  // write success status DBcomm.getMessage() as a record to status file
			
			//  successLog.insertIcon(new ImageIcon("C:\\Users\\Scare983\\workspace\\NewAge\\src\\Images\\Status Icon.png"));
		
	           
			  successLog.setText(successLog.getText() + "\n" + DBcomm.getMessage());
			
			  statpw.write((DBcomm.getMessage()));
			  statpw.write("\n");
			  statpw.write("\n");
			  statpw.close();
		//	  statpw.newLine();
			  
		  }
		  else
		  {
			  // write failure error DBcomm.getMessage() as a record to error file
			  errorLog.setText(errorLog.getText()  + "\n" + DBcomm.getMessage());
			
					  errpw.write(DBcomm.getMessage());
					  errpw.write("\n");
					  errpw.write("(" + command + ")");
					  errpw.write("\n");
						 errpw.write("\n");
					  errpw.close();
				//	  errpw.write.newLine();
					   
		  }
	    }//end of database creation
	    
	    
	    
	    else if(command.substring(0, 13).equalsIgnoreCase("DROP DATABASE")) {
	    	// finish call to drop database method pass database name\
	    	
	    		if(DBcomm.DropDatabase(command.substring(14))) {
	    			 successLog.setText(successLog.getText()  + "\n" + DBcomm.getMessage());
	    			statpw.write(DBcomm.getMessage());
	    	//		statpw.newLine();
	    			statpw.write("\n");
	    			  statpw.write("\n");
	    			statpw.close();
	    		//	System.out.println("database dropped");	
	    	}
	    		 // write failure error DBcomm.getMessage() as a record to error file
	    		else {
	    			errorLog.setText(errorLog.getText()  + "\n" + DBcomm.getMessage());
	    			errpw.write(DBcomm.getMessage());
	    			errpw.write("\n");
	    			 errpw.write("(" + command + ")");
	    			 errpw.write("\n");
	    			 errpw.write("\n");
	    			errpw.close();
	    		//	errpw.write.newLine();
	    			 
	    		}
	    }
	    
	    else if(command.substring(0,12).equalsIgnoreCase("CREATE TABLE")) {
	    	
	    	if(DBcomm.CreateTable(command.substring(13, command.indexOf('.') ), command.substring(command.indexOf('.') +1 ))) {
	    		 successLog.setText(successLog.getText()  + "\n" + DBcomm.getMessage());
	    		statpw.write(DBcomm.getMessage());
	    		statpw.write("\n");
	    		  statpw.write("\n");
	    	//	statpw.newLine();
	    		statpw.close();
	    		 
	    		
	    	}
	    	else {
	    		errorLog.setText(errorLog.getText()  + "\n" + DBcomm.getMessage());
	    		errpw.write(DBcomm.getMessage());
	    		errpw.write("\n");
	    		 errpw.write("(" + command + ")");
	    		 errpw.write("\n");
	    		 errpw.write("\n");
	    		errpw.close();
	    		//errpw.write.newLine();
	    		 
	    	}
	    	
	    	
	    }
	    
	    
	    
	    else if(command.substring(0, 10).equalsIgnoreCase("DROP TABLE")) {
	    	if(DBcomm.DropTable(command.substring(11, command.indexOf('.') ), command.substring(command.indexOf('.') +1 ))) {
	    		 successLog.setText(successLog.getText()  + "\n" + DBcomm.getMessage());
	    		statpw.write(DBcomm.getMessage());
	    		statpw.write("\n");
	    		  statpw.write("\n");
	    		statpw.close();
	    	//	statpw.newLine();
	    		 
	    	}
	    	else {
	    		errorLog.setText(errorLog.getText()  + "\n" + DBcomm.getMessage());
	    		errpw.write(DBcomm.getMessage());
	    		errpw.write("\n");
	    		 errpw.write("(" + command + ")");
	    		errpw.write("\n");
	    		 errpw.write("\n");
	    		errpw.close();
	    	//	errpw.write.newLine();
	    		 
	    	}
	    }
	    
	    else if(command.substring(0, 6).equalsIgnoreCase("INSERT")) {
	    	
	    	//int firstIndex = command.indexOf(34);
	    	
	    	//String temp = command.substring(0, command.indexOf(firstIndex)) + command.substring(firstIndex +1);
	    	//String temp = command.substring(0, 7 ) + command.substring(8);
	    	boolean INTO = false;
	    	int indexOfI = 0;
	    	 for(int i = 7; i < command.length(); i ++) {
	    		 if(command.substring(i, i+4).equalsIgnoreCase("INTO")) {
	    			 INTO = true;
	    			 indexOfI = i;
	    			 break;
	    		 }
	    	 }
	    	
	    	 
	    	
	    	String user = command.substring(8, indexOfI-2);
	    	String dbName = command.substring(indexOfI + 5, command.indexOf('.'));
	    	String tbName = command.substring(command.indexOf('.') +1 );
	    	
	    if(!INTO) {
	    
	    	errpw.write("Add keyword INTO Correctly" + DBcomm.getMessage() );
	    	errorLog.setText(errorLog.getText()  + "\n" + DBcomm.getMessage());
	    	errpw.write("\n");
	    	 errpw.write("(" + command + ")");
	    	 errpw.write("\n");
	    	 errpw.write("\n");
	    	errpw.close();
    	//	errpw.write.newLine();
    		 
	    }
	    else if(DBcomm.Insert(user, dbName, tbName)) {
	    	 successLog.setText(successLog.getText()  + "\n" + DBcomm.getMessage());
	    	statpw.write(DBcomm.getMessage());
	    	statpw.write("\n");
	    	  statpw.write("\n");
	    	statpw.close();
    	//	statpw.newLine();
    		 
	    	
	    	}
	    	else {
	    		errorLog.setText(errorLog.getText()  + "\n" + DBcomm.getMessage());
	    		errpw.write(DBcomm.getMessage());
	    		errpw.write("\n");
	    		 errpw.write("(" + command + ")");
	    		 errpw.write("\n");
	    		 errpw.write("\n");
	    		errpw.close();
	    	//	errpw.write.newLine();
	    		 
	    	}
	    }
	    else if(command.substring(0,13).equalsIgnoreCase("SELECT * FROM") && !command.contains(" WHERE COLUMN ")) {
	    	if(DBcomm.Select(command.substring(14, command.indexOf('.')), command.substring(command.indexOf('.') +1), statfile)) {
	    		 successLog.setText(successLog.getText());//  + "\n" + DBcomm.getMessage());
	    		statpw.write(DBcomm.getMessage());
	    		statpw.write("\n");
	    		  statpw.write("\n");
	    		statpw.close();
	    	//	statpw.newLine();
	    		 
	    	}
	    	else {
	    		errorLog.setText(errorLog.getText()  + "\n" + DBcomm.getMessage());
	    		errpw.write(DBcomm.getMessage());
	    		errpw.write("\n");
	    		 errpw.write("(" + command + ")");
	    		 errpw.write("\n");
	    		 errpw.write("\n");
	    		errpw.close();
	    	//	errpw.write.newLine();
	    		 
	    	}
	    	
	    	
	    	
	    	
	    	
	    	
	    }
	  
	    else if(command.substring(0,13).equalsIgnoreCase("SELECT * FROM") && command.contains(" WHERE COLUMN ")){
	    	int indexOfI = 0;
	    	boolean WHERE = false;
	    	 for(int i = 7; i < command.length(); i ++) {
	    		 if(command.substring(i, i+12).equalsIgnoreCase("WHERE COLUMN")) {
	    			 WHERE = true;
	    			 indexOfI = i;
	    			 break;
	    		 }
	    	 }
	   
	    	 String dbName = command.substring(14, command.indexOf('.'));
	    	 String tbName =  command.substring(command.indexOf('.') +1, indexOfI-1);
	    	 String looking = command.substring(indexOfI+16, command.length()-1);
	    if(DBcomm.SelectCol(dbName,tbName,looking , statfile)) {
	    	 successLog.setText(successLog.getText()  + "\n" + DBcomm.getMessage());
	    	statpw.write(DBcomm.getMessage());
	    	statpw.write("\n");
	    	  statpw.write("\n");
	    	statpw.close();
    	//	statpw.newLine();
    		 
	    	}
	    else {
	    ///	successLog.insertIcon ( new ImageIcon ( "/path/to/image.png" ) );///fix
	    	errorLog.setText(errorLog.getText()  + "\n" + DBcomm.getMessage());
	    	errpw.write(DBcomm.getMessage());
	    	errpw.write("\n");
	    	 errpw.write("(" + command + ")");
	    	errpw.write("\n");
	   	 errpw.write("\n");
	    	errpw.close();
    	//errpw.write.newLine();
    		 
	    }
	    
	    }
	    else if(command.substring(0, 11).equalsIgnoreCase("DELETE FROM") && (!command.contains("WHERE COLUMN"))) {
	    	if(DBcomm.Delete(command.substring(12, command.indexOf('.') ), command.substring(command.indexOf('.') +1 ))) {
	    		 successLog.setText(successLog.getText()  + "\n" + DBcomm.getMessage());
	    		statpw.write(DBcomm.getMessage());
	    		statpw.write("\n");
	    		  statpw.write("\n");
	    		statpw.close();
	    	//	statpw.newLine();
	    		 
	    		
	    	}
	    	else {
	    		errorLog.setText(errorLog.getText()  + "\n" + DBcomm.getMessage());
	    		errpw.write(DBcomm.getMessage());
	    		errpw.write("\n");
	    		 errpw.write("(" + command + ")");
	    		 errpw.write("\n");
	    		 errpw.write("\n");
	    		errpw.close();
	    	//	errpw.write.newLine();
	    		 
	    	}
	    }
	 /*    else if(command.substring(0, 11).equalsIgnoreCase("DELETE FROM") && (command.contains("WHERE COLUMN"))) {
	    	String looking = command.substring(command.indexOf('"') +1 , 8 );
	    	if(DBcomm.DeleteCol(command.substring(12, command.indexOf('.') ), command.substring(command.indexOf('.') +1 , command.length()-1), looking, DBcomm.getMessage())){
	    	statpw.write(DBcomm.getMessage());
    		statpw.newLine();
    		 
    		
    	}
    	else {
    		errpw.write(DBcomm.getMessage());
    		errpw.write.newLine();
    		 
    	}
	  }*/
	    ///LAST STATEMENT, END ALL Of ELSE STATEMENTS
	    else {
			errpw.write("Could not read command: ");
    		errpw.write("\n");
    		errpw.write("(" + command + ")");
    		errpw.write("\n");
    		errpw.write("\n");
    		errpw.close();
    	
    		errorLog.setText(errorLog.getText()  + "\n" + "Could not Recognize command  and therefore was not excecuted:"  + command  );
	    	
	    }
	   
	  }// end while
	
	 FileWriter writer = new FileWriter(newFile, false);//makes it so the file with all the lines is delted and reused when the run command is used
	  writer.write("");
        writer.close();
       newFile.delete();
        
	    //  create conditionals for all database commands and methods
	    // else if DROP DATABASE
		// else if CREATE TABLE
		// else if DROP TABLE
		// else if ....
	  }//  ***  catch any IO errors
      catch(Exception e)
      {
         System.out.println("Problem with file "+f.getName()+", error="+e.getMessage());
         //is an error
         errorLog.setText(errorLog.getText() + "\nCommand is not real:  " + command);
         try {
         FileWriter writer = new FileWriter(newFile, false);//makes it so the file with all the lines is delted and reused when the run command is used
   	  writer.write("");
           writer.close();
         }catch(Exception z) {
        	 System.out.println("Could not delete file contentes in database");
         }
        
      }
      
      }
	
}