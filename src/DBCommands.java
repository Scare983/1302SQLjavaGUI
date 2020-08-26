import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JTextPane;
public class DBCommands
{ 
 static	ArrayList <File> dir = new ArrayList<File>();
 static	ArrayList<File> tableArr = new ArrayList<File>();

 	String message;
 	JTextPane outputLog;
// DBCOmmands()  constructor
 	DBCommands() {//populate array with files already in the current directory
 		File folder = new File(System.getProperty("user.dir"));
 	    File[] listOfFiles = folder.listFiles();

 	  // Iterating array of files for printing name of all files present in the directory.
 	    for (int i = 0; i < listOfFiles.length; i++) {
 	    	if(listOfFiles[i].isDirectory()) {
 	    		dir.add(listOfFiles[i]);
		
 	    	}
 	    }
 	 //  folder = new File(System.getProperty("user.dir"));
 	  listOfFiles = folder.listFiles();
 		for(File k : dir) {
 			File[] files = new File(k.getName()).listFiles();
 			//If this pathname does not denote a directory, then listFiles() returns null. 

 			for (File file : files) {
 			    if (file.isFile()) {
 			      if(file.getName().contains(".tab")) {
 			    	  tableArr.add(file);
 			      }
 			       
 			    }
 			}
 			
 		}
 		
 	}
 	public DBCommands(JTextPane outputLog) {
 		this.outputLog = outputLog;
 		
 		
 	}
 	
//  CREATE DATABASE method
	
public  boolean CreateDatabase(String dbName )
{
   try
   {
     // ***** TEST CODE *****
     int x=9; // ******  REMOVE IT LATER
     // *********************
     
     // create File object for dbName
     File f = null;
     for(File temp : dir) {
    	 if(temp.getName().equals(dbName)) {
    		 throw new IOException(); //directory is already created
    	 }
     }
    
    f = new File(dbName);
     // call mkdir(); method to create directory
     f.mkdir() ;
     dir.add(f); //adds the file to list
     // return true; if it works
     
     this.message = "Database "+dbName+" created. \n" + f.getAbsolutePath();
     return true;
     
     
   }
   catch (Exception e)   // catch error
   {
     // return true; if it works
	   
     this.message = "Database "+dbName+" could not be created."+e.getMessage();
     return false;
   }
   // end catch
} // end createDatabase

//  DROP DATABASE method
public  boolean DropDatabase(String dbName)	 {
	try {
//	File root = new File("C:/" + dbName);
		File f = null;
		for(File root : dir) {
	
			if(root.getName().equals(dbName)) { // need to fix
				f = root;
			}
		}
		if (f == null) {
			throw new FileNotFoundException();
		}
/*for(File temp : tableArr) {
	if(temp.getParentFile().getName().equals(dbName)) {
		String tbWithouttab = temp.getName().substring(0, temp.getName().length()-4);
		DropTable(temp.getParentFile().getName(), tbWithouttab);
		tableArr.add(temp); //makes sure that the temp file is not empty
		this.message += "The above table was dropped to delete the directory";
	//	tableArr.rem
	}
}*/
	if(dir == null) {
		throw new FileNotFoundException();
	}
 	if(f != null) {
 		if(f.delete()) {
 		
	dir.remove(f);
	this.message = "Database " + dbName+ " removed \n" + f.getAbsolutePath();
	
	return true;
	}
 		else {
 			throw new Exception();
 		}
 	}
	throw new Exception();
		
	}catch (FileNotFoundException  e) {
	this.message = "Database "+ dbName+ " could not be found. "+e.getMessage();
	return false;
}
	catch(IOException e) {
	this.message = "You must delete all tables from desginated directory. " + e.getMessage() ;

	return false;
}
	catch(Exception e) {
		this.message = "Database "+ dbName+ " could not be removed. Contains tables. "+e.getMessage();
		return false;
	}
 
}

  //  CREATE TABLE method
public  boolean CreateTable(String dbName, String tbName) {
try {
	tbName += ".tab";
	File f = null;
	for(File temp : dir) {
		if(temp.getName().equals(dbName)) {
			f = temp;
			break;
		}
	}
	if(f == null) {
		throw new FileNotFoundException(); //means the database was not created
	}
	File l = null;
	
	for(File temp : tableArr) {
		
		if(temp.getAbsolutePath().equals(f.getAbsolutePath() + '/'+tbName)) {
			throw new Exception(); //means the array is already created
		}
	}
	

	
			l = new File(f, tbName);
		
			l.createNewFile() ;
			tableArr.add(l);
			this.message = "Table " + tbName+ " created \n" + l.getAbsolutePath();
			return true;
			
		
			
			
	
	

} 
catch(FileNotFoundException e) {
	this.message = "Directoy " + dbName + "could not be found. " + e.getMessage(); 
	return false;
}
catch(Exception e) {
	this.message = "Table "+tbName+ " could not be created. "+e.getMessage();
			return false;
}

	
}
  //  DROP TABLE method
public  boolean DropTable(String dbName, String tbName) {
	try {
	File f = null;
	
	for(File temp : dir) {
		if(temp.getName().equals(dbName)) {
			f = temp;
			break;
		}
	}
	File l = null;
	for(File temp : tableArr) {
		
	//	System.out.println(f.getAbsolutePath() + '\\' + tbName);
		String checking = temp.getAbsolutePath();
		if(checking.equals(f.getAbsolutePath() + '/' + tbName + ".tab")) {
			l = temp;
			break;
		}
	}
	if(l == null) {
		throw new FileNotFoundException();
	}
	
	if(l != null) {
		l.delete();
		tableArr.remove(l);
		this.message = "Table " + tbName+ " removed from \n" + l.getAbsolutePath();
		//System.out.println(l.getAbsolutePath());
		return true;
	}
	
	
	throw new Exception();
	}
	catch(FileNotFoundException e) {
		this.message = "Table " + dbName + "/" + tbName + ".tab not found. " + e.getMessage();
		return false;
	}
	catch(Exception e) {
		this.message = "Table "+tbName+ " could not be dropped. "+e.getMessage();
		return false;
	}
}
  //  INSERT method
	public boolean Insert(String append, String dbName, String tbName) {
		try {
			
			
			File f = null;
			
			for(File temp : dir) {
				String pls = temp.getAbsolutePath();
				
				if(temp.getName().equals(dbName)) {
					f = temp;
					break;
				}
			}
			if(f == null) {
				throw new FileNotFoundException();
			}
			File l = null;
			String pls = f.getAbsolutePath() + '/' + tbName + ".tab";
			for(File temp : tableArr) {
				
			//	System.out.println(f.getAbsolutePath() + '\\' + tbName);
				String checking = temp.getAbsolutePath();
				if(checking.equals(pls)) {
					l = temp;
					break;
				}
			}
			if(l == null) {
				
				throw new FileNotFoundException();
			}
			if(l != null) {
				FileWriter fw = new FileWriter(l, true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(append);
				bw.newLine();
				bw.close();
				this.message = "Inserted \"" + append + "\" into \n" + l.getAbsolutePath();
				return true;
			}
			throw new Exception();
			
		}
		catch(FileNotFoundException e) {
			this.message = "Table " + dbName + '/' + tbName + ".tab not found. " + e.getMessage();
			return false;
		}catch(Exception e) {
			this.message = "Could not insert " + append + e.getMessage(); 
			return false;
		}
	
	}

  //  SELECT method (without the where)
	public boolean Select(String dbName, String tbName, File statfile) {
		try  {
			File f = null;
		
		for(File temp : dir) {
			if(temp.getName().equals(dbName)) {
				f = temp;
				break;
			}
		}
		File l = null;
		String pls = f.getAbsolutePath() + '/' + tbName + ".tab";
		for(File temp : tableArr) {
			
		//	System.out.println(f.getAbsolutePath() + '\\' + tbName);
			String checking = temp.getAbsolutePath();
			if(checking.equals(pls)) {
				l = temp;
				break;
			}
		}
		if(l == null) {
			throw new FileNotFoundException();
		}
		
		if(l != null) {
			 Scanner infile = new Scanner(l);//need help with this it keeps skipping the while loop
			 if(infile.hasNextLine() == false) {
		 throw new IOException();
			 }
			
			 outputLog.setText(outputLog.getText() + "Contents for " + l.getAbsolutePath() +" are: \n");
			 while(infile.hasNextLine()) {
				 String output = infile.nextLine();
				 outputLog.setText("\n" + outputLog.getText() + " " + output + "\n");
					FileWriter fw = new FileWriter(statfile, true);//makes sure whatever is in statFile stays and is not deleted
					BufferedWriter bw = new BufferedWriter(fw);
					
					bw.write(output); //writes whatever is in File L into statfile
					bw.newLine();
					 bw.close();
			}
			
			 infile.close();
			 this.message = "The contents of " + l.getAbsolutePath() + " are shown in the output panel\n"  ;
			 return true;
			
		}
			
		throw new Exception();
		}
		catch(FileNotFoundException e) {
			this.message = "Table " + dbName + '/' + tbName + ".tab not found. " + e.getMessage();
			return false;
		}
		catch(IOException e) {
			this.message = "The DBUSER.txt contains no information on the first line. " + e.getMessage();
					return false;
		}
		catch(Exception e) {
			this.message = "Could not select from file." + e.getMessage();
			return false;
		}
	}
  //  SELECT method (with the where)  
	public boolean SelectCol(String dbName, String tbName, String looking, File statfile) {
		try  {
			File f = null;
		
		for(File temp : dir) {
			if(temp.getName().equals(dbName)) {
				f = temp;
				break;
			}
		}
		File l = null;
		String pls = f.getAbsolutePath() + '/' + tbName + ".tab";
		for(File temp : tableArr) {
			
		//	System.out.println(f.getAbsolutePath() + '\\' + tbName);
			String checking = temp.getAbsolutePath();
			if(checking.equals(pls)) {
				l = temp;
				break;
			}
		}
		if(l == null) {
			throw new FileNotFoundException();
		}
		
		
			if(l!= null) {
				int lineNumber = 0;
				 Scanner infile = new Scanner(l);//need help with this it keeps skipping the while loop
				boolean found = false;
				outputLog.setText(outputLog.getText() + "What you are looking for in " + l.getAbsolutePath() +" are: \n");
				 while(infile.hasNextLine()) {
					 lineNumber++;
					
					 	String fileLine = infile.nextLine();
			
					
						FileWriter fw = new FileWriter(statfile, true);//BufferedWriter bw = new BufferedWriter(fw);
						
						if(fileLine.contains(looking)) {
							 found = true;
							 outputLog.setText( outputLog.getText() + " " + fileLine + " on line number + " + lineNumber + "\n");
							fw.write(fileLine); //writes whatever is in File L into statfile
							fw.write("\n");
					
							//fw.close();
							//infile.close();
							this.message = "What you are looking for is shown in the output box and on line " + lineNumber + " of the file. \n" + l.getAbsolutePath();
							
						}
						fw.close();
						
						
				
			}
				 infile.close();
				
				if(found == true) {
					return true;
				}
				if(found == false ) {
					throw new Exception();
				}
				 //couldn't find word in file
		}
			throw new Exception();
		}
		catch(FileNotFoundException e) {
			this.message = "Table " + dbName + '/' + tbName + ".tab not found. " + e.getMessage();
			return false;
		}
		catch(Exception e) {
			this.message = "Row " + looking + " not found in table " + " could not be found" + e.getMessage();
					return false;
		}
	}
  public boolean Delete(String dbName, String tbName) {
	  try {
		  File f = null;
			
			for(File temp : dir) {
				if(temp.getName().equals(dbName)) {
					f = temp;
					break;
				}
			}
			if(f == null) {
				throw new FileNotFoundException();
			}
			File l = null;
			String pls = f.getAbsolutePath() + '/' + tbName + ".tab";
			for(File temp : tableArr) {
				
			//	System.out.println(f.getAbsolutePath() + '\\' + tbName);
				String checking = temp.getAbsolutePath();

				if(checking.equals(pls)) {
					l = temp;
					break;
				}
			
			}
			if(l == null) {
				throw new FileNotFoundException();
			}
		  if(l!= null) {
			  DropTable(dbName, tbName);
			 CreateTable(dbName, tbName);
			
			
			this.message = "Deleted content from " + tbName + " \n" + l.getAbsolutePath();
			return true;
			  
		  }
		  

		  throw new Exception();
	  }
	  catch(FileNotFoundException e ) {
		  this.message = "Table " + dbName + '/' + tbName + ".tab not found. " + e.getMessage();
		  return false;
	  }
	  catch(Exception e) {
		  this.message = "Could not delete table contents " + tbName + " " + e.getMessage();
		  return false;
	  }
	  
	  
	  
  }

/*public boolean DeleteCol(String dbName, String tbName, String looking, String message) {
	try {
		 File f = null;
			
			for(File temp : dir) {
				if(temp.getName().equals(dbName)) {
					f = temp;
					break;
				}
			}
			File l = null;
			for(File temp : tableArr) {
				
			//	System.out.println(f.getAbsolutePath() + '\\' + tbName);
				String checking = temp.getAbsolutePath();

				if(checking.equals(f.getAbsolutePath() + '\\' + tbName + ".tab")) {
					l = temp;
					break;
				}
			}
			File temp = new File();
			if(l != null) {
				 Scanner infile = new Scanner(l);//need help with this it keeps skipping the while loop
				 while(infile.hasNextLine()) {
						FileWriter fw = new FileWriter(statfile, true);//makes sure whatever is in statFile stays and is not deleted
						BufferedWriter bw = new BufferedWriter(fw);
						if(infile.nextLine().toString().contains(looking)) {
						}
				return true;
			}
			throw new Exception();
	}
}
	catch(Exception e) {
		this.message = "Could not delete Collumn";
		return false;
	}
}*/
  
  //  DELETE method (without the where)
  
  //  DELETE method (with the where)

  //  UPDATE method
  public String getMessage() {
	  return message;
  }


}
