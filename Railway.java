import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;  

public class Railway {
	
	public static int chooseTable(String table) {
		if (table.equals("Train_schedule")) {
			return 1; 
		} else if (table.equals("Employee")) {
			return 2; 
		} else if (table.equals("Work_on")) {
			return 3; 
		} else if (table.equals("Customer")) {
			return 4; 
		} else if (table.equals("Ticket_purchase")) {
			return 5; 
		} else {
			return -1; 
		}
		
	}

	public static void main(String[] args) {
		
		System.out.println("L – List: lists all records in a specified table\n" + 
				"A – Add: add a record to a specified table\n" + 
				"D – Delete: deletes a record in a specified table\n" + 
				"S – Search: search from a specified table\n" + 
				"V – View: create a new view\n" +
				"T – Trigger: create a new trigger\n" + 
				"X – Exit: exit application\n"); 
		
		// Interactive Part
		while (true) {
			System.out.println("Enter a character: "); 
			
			InputStreamReader is = new InputStreamReader(System.in); 
			BufferedReader in = new BufferedReader(is); 
			
			String input = ""; 
			try {
				input = in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			//////////////////////////////////////////////////////////////////////////////////////////////////// if user inputs L
			if (input.equals("L")) {

				int t; 
				String table = ""; 
				
				while(true) { // until they enter a correct table
					System.out.println("Enter table: ");  // get the desired table name
					
					InputStreamReader is2 = new InputStreamReader(System.in); 
					BufferedReader in2 = new BufferedReader(is2);
					
					try {
						table = in2.readLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					
					t = chooseTable(table); 
					
					if(t == -1) {
						System.out.println("No such table exists");
					} else {
						break; 
					}
				}
				
				
				try {			  
					//1. Get a connection to database
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Database_final_project","root","Eba0899!");  
					
					//2. Create a statement 
					Statement stmt = con.createStatement();  
					
					//3. Execute SQL query
					ResultSet rs = stmt.executeQuery("select * from " + table);  
					
					//4. Process the result set
					if (t == 1) {
						while(rs.next()) {  
							System.out.println(rs.getInt("train_id") + ", " + rs.getString("from_des") + ", " + 
											   rs.getString("to_des") + ", " + rs.getTime("start_time") + ", " + 
											   rs.getTime("arrival_time") + ", " + rs.getDate("column_date"));    
						}	
					} else if (t == 2) {
						while(rs.next()) {  
							System.out.println(rs.getInt("eid") + ", " + rs.getInt("SSN") + ", " + 
											   rs.getString("Fname") + ", " + rs.getString("MI") + ", " + 
											   rs.getString("Lname") + ", " + rs.getInt("age") + ", " + 
											   rs.getInt("salary") + ", " + rs.getString("job"));    
						}	
					} else if (t == 3) {
						while(rs.next()) {  
							System.out.println(rs.getInt("train_id") + ", " + rs.getInt("eid"));    
						}	
					} else if (t == 4) {
						while(rs.next()) {  
							System.out.println(rs.getInt("cid") + ", " + rs.getString("Fname") + ", " + 
											   rs.getString("MI") + ", " + rs.getString("Lname"));    
						}	
					} else if (t == 5) {
						while(rs.next()) {  
							System.out.println(rs.getInt("cid") + ", " + rs.getInt("train_id") + ", " + 
											   rs.getString("class") + ", " + rs.getTimestamp("date_time") + ", " + 
											   rs.getString("method") + ", " + rs.getString("used"));    
						}	
					}
				
				} 
				catch (Exception exc)
				{
					System.out.println(exc);
				}
				
			} //////////////////////////////////////////////////////////////////////////////////////////////////// if user inputs A
			else if (input.equals("A"))
			{
				
				int t; 
				String table = ""; 
				
				while(true) { // until they enter a correct table
					System.out.println("Enter table: ");  // get the desired table name
					
					InputStreamReader is2 = new InputStreamReader(System.in); 
					BufferedReader in2 = new BufferedReader(is2);
					
					try {
						table = in2.readLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					
					t = chooseTable(table); 
					
					if(t == -1) {
						System.out.println("No such table exists");
					} else {
						break; 
					}
				}
				
				try {			  
					//1. Get a connection to database
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Database_final_project","root","Eba0899!");  
					
					//2. Create a statement 
					Statement stmt = con.createStatement();  
					
					//3. Execute SQL query
					String sqlE = "insert into "; 
					
					if(t == 1) {     ///////////////////////// insert into Train_schedule
						sqlE = sqlE + "Train_schedule values ("; 
						
						String train_id = null; 
						String from_des = null; 
						String to_des = null; 
						String start_time = null; 
						String arrival_time = null;
						String date = null; 
						
						String[] column_names = {"train_id", "from_des", "to_des", "start_time", "arrival_time", "date"}; 
						String[] column_vars = {train_id, from_des, to_des, start_time, arrival_time, date};
						
						for (int i = 0; i < column_names.length; i++) { // get input
							System.out.println("Enter " + column_names[i]); 
							
							InputStreamReader isA = new InputStreamReader(System.in); 
							BufferedReader inA = new BufferedReader(isA);
							
							try {
								column_vars[i] = inA.readLine();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
						}
						
						for(int k = 0; k < column_vars.length; k++) {
							sqlE = sqlE + column_vars[k]; 
							if (k != column_vars.length-1) {
								sqlE = sqlE + ","; 
							} else {
								sqlE = sqlE + ")";
							}
						}
						
						
						
					} else if (t == 2) {   ///////////////////////// insert into Employee
						sqlE = sqlE + "Employee values ("; 
						
						String eid = null; 
						String SSN = null; 
						String Fname = null; 
						String MI = null;  
						String Lname = null;
						String age = null;
						String salary = null;
						String job = null;
						
						String[] column_names = {"eid", "SSN", "Fname", "MI", "Lname", "age", "salary", "job"};  
						String[] column_vars = {eid, SSN, Fname, MI, Lname, age, salary, job};
						
						for (int i = 0; i < column_names.length; i++) { // get input
							System.out.println("Enter " + column_names[i]); 
							
							InputStreamReader isA = new InputStreamReader(System.in); 
							BufferedReader inA = new BufferedReader(isA);
							
							try {
								column_vars[i] = inA.readLine();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
						}
						
						for(int k = 0; k < column_vars.length; k++) {
							sqlE = sqlE + column_vars[k]; 
							if (k != column_vars.length-1) {
								sqlE = sqlE + ","; 
							} else {
								sqlE = sqlE + ")";
							}
						}
					} else if (t == 3) {   ///////////////////////// insert into Work_on
						sqlE = sqlE + "Work_on values ("; 
						
						String train_id = null; 
						String eid = null; 
					
						String[] column_names = {"train_id", "eid"};  
						String[] column_vars = {train_id, eid}; 
						
						for (int i = 0; i < column_names.length; i++) { // get input
							System.out.println("Enter " + column_names[i]); 
							
							InputStreamReader isA = new InputStreamReader(System.in); 
							BufferedReader inA = new BufferedReader(isA);
							
							try {
								column_vars[i] = inA.readLine();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
						}
						
						for(int k = 0; k < column_vars.length; k++) {
							sqlE = sqlE + column_vars[k]; 
							if (k != column_vars.length-1) {
								sqlE = sqlE + ","; 
							} else {
								sqlE = sqlE + ")";
							}
						}
					} else if (t == 4) {	///////////////////////// insert into Customer
						sqlE = sqlE + "Customer values ("; 
						
						String cid = null; 
						String Fname = null; 
						String MI = null;  
						String Lname = null;
						
						String[] column_names = {"cid", "Fname", "MI", "Lname"};   
						String[] column_vars = {cid, Fname, MI, Lname}; 
						
						for (int i = 0; i < column_names.length; i++) { // get input
							System.out.println("Enter " + column_names[i]); 
							
							InputStreamReader isA = new InputStreamReader(System.in); 
							BufferedReader inA = new BufferedReader(isA);
							
							try {
								column_vars[i] = inA.readLine();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
						}
						
						for(int k = 0; k < column_vars.length; k++) {
							sqlE = sqlE + column_vars[k]; 
							if (k != column_vars.length-1) {
								sqlE = sqlE + ","; 
							} else {
								sqlE = sqlE + ")";
							}
						}
					} else if (t == 5) { 	///////////////////////// insert into Ticket_purchase
						sqlE = sqlE + "Ticket_purchase values ("; 
						
						String cid = null; 
						String train_id = null; 
						String class1 = null; 
						String datetime = null;  
						String method = null;
						String used = null;
						
						String[] column_names = {"cid", "train_id", "class1", "datetime", "method", "used"};  
						String[] column_vars = {cid, train_id, class1, datetime, method, used};
						
						for (int i = 0; i < column_names.length; i++) { // get input
							System.out.println("Enter " + column_names[i]); 
							
							InputStreamReader isA = new InputStreamReader(System.in); 
							BufferedReader inA = new BufferedReader(isA);
							
							try {
								column_vars[i] = inA.readLine();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
						}
						
						for(int k = 0; k < column_vars.length; k++) {
							sqlE = sqlE + column_vars[k]; 
							if (k != column_vars.length-1) {
								sqlE = sqlE + ","; 
							} else {
								sqlE = sqlE + ")";
							}
						}
					}
						
					stmt.executeUpdate(sqlE);  
					
					System.out.println("Insert complete.");
				
				} 
				catch (Exception exc)
				{
					System.out.println(exc);
				}
				
			}  ///////////////////////////////////////////////////////////////////// User inputs D
			else if(input.equals("D")) 
			{

				int t; 
				String table = ""; 
				
				while(true) { // until they enter a correct table
					System.out.println("Enter table: ");  // get the desired table name
					
					InputStreamReader is2 = new InputStreamReader(System.in); 
					BufferedReader in2 = new BufferedReader(is2);
					
					try {
						table = in2.readLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					
					t = chooseTable(table); 
					
					if(t == -1) {
						System.out.println("No such table exists");
					} else {
						break; 
					}
				} // end while
				
				
				try {			  
					//1. Get a connection to database
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Database_final_project","root","Eba0899!");  
					
					//2. Create a statement 
					Statement stmt = con.createStatement();  
					
					//3. Execute SQL query
					String sqlE = "delete from "; 
					
					if(t == 1) {     ///////////////////////// delete from Train_schedule
						sqlE = sqlE + "Train_schedule where "; 
						
						String train_id = null; 
						String from_des = null; 
						String to_des = null; 
						String start_time = null; 
						String arrival_time = null;
						String date = null; 
						
						String[] column_names = {"train_id", "from_des", "to_des", "start_time", "arrival_time", "date"}; 
						String[] column_vars = {train_id, from_des, to_des, start_time, arrival_time, date};
						
						for (int i = 0; i < column_names.length; i++) { // get input
							System.out.println("Enter " + column_names[i]); 
							
							InputStreamReader isA = new InputStreamReader(System.in); 
							BufferedReader inA = new BufferedReader(isA);
							
							try {
								column_vars[i] = inA.readLine();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
						}
						
						sqlE = sqlE + "train_id = " + train_id + " and from_des = '" + from_des + 
							   "' and to_des = '" + to_des + "' and start_time = " + start_time + 
							   " and arrival_time = " + arrival_time + " and date = " + date;  
												
					} else if (t == 2) {   ///////////////////////// delete from Employee
						sqlE = sqlE + "Employee where "; 
						
						String eid = null; 
						String SSN = null; 
						String Fname = null; 
						String MI = null;  
						String Lname = null;
						String age = null;
						String salary = null;
						String job = null;
						
						String[] column_names = {"eid", "SSN", "Fname", "MI", "Lname", "age", "salary", "job"};  
						String[] column_vars = {eid, SSN, Fname, MI, Lname, age, salary, job};
						
						for (int i = 0; i < column_names.length; i++) { // get input
							System.out.println("Enter " + column_names[i]); 
							
							InputStreamReader isA = new InputStreamReader(System.in); 
							BufferedReader inA = new BufferedReader(isA);
							
							try {
								column_vars[i] = inA.readLine();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
						}
						
						sqlE = sqlE + "eid = " + eid + " and SSN = " + SSN + 
								   " and Fname = '" + Fname + "' and MI = '" + MI + 
								   "' and Lname = '" + Lname + "' and age = " + age + 
								   " and salary = " + salary + " and job = '" + job + "'";  
						
					} else if (t == 3) {   ///////////////////////// insert into Work_on
						sqlE = sqlE + "Work_on where "; 
						
						String train_id = null; 
						String eid = null; 
					
						String[] column_names = {"train_id", "eid"};  
						String[] column_vars = {train_id, eid}; 
						
						for (int i = 0; i < column_names.length; i++) { // get input
							System.out.println("Enter " + column_names[i]); 
							
							InputStreamReader isA = new InputStreamReader(System.in); 
							BufferedReader inA = new BufferedReader(isA);
							
							try {
								column_vars[i] = inA.readLine();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
						}
						
						sqlE = sqlE + "train_id = " + train_id + " and eid = " + eid;  
						
					} else if (t == 4) {	///////////////////////// insert into Customer
						sqlE = sqlE + "Customer where "; 
						
						String cid = null; 
						String Fname = null; 
						String MI = null;  
						String Lname = null;
						
						String[] column_names = {"cid", "Fname", "MI", "Lname"};   
						String[] column_vars = {cid, Fname, MI, Lname}; 
						
						for (int i = 0; i < column_names.length; i++) { // get input
							System.out.println("Enter " + column_names[i]); 
							
							InputStreamReader isA = new InputStreamReader(System.in); 
							BufferedReader inA = new BufferedReader(isA);
							
							try {
								column_vars[i] = inA.readLine();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
						}
						
						sqlE = sqlE + "cid = " + cid + " and Fname = '" + Fname + "' and MI = '" + MI + 
								   "' and Lname = '" + Lname + "'"; 
										
					} else if (t == 5) { 	///////////////////////// insert into Ticket_purchase
						sqlE = sqlE + "Ticket_purchase where "; 
						
						String cid = null; 
						String train_id = null; 
						String class1 = null; 
						String datetime = null;  
						String method = null;
						String used = null;
						
						String[] column_names = {"cid", "train_id", "class1", "datetime", "method", "used"};  
						String[] column_vars = {cid, train_id, class1, datetime, method, used};
						
						for (int i = 0; i < column_names.length; i++) { // get input
							System.out.println("Enter " + column_names[i]); 
							
							InputStreamReader isA = new InputStreamReader(System.in); 
							BufferedReader inA = new BufferedReader(isA);
							
							try {
								column_vars[i] = inA.readLine();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
						}
						
						sqlE = sqlE + "cid = " + cid + " and train_id = " + train_id + " and class = '" + class1 + 
								   "' and date_time = " + datetime + " and method = '" + method + "' and used = '" 
								   + used + "' "; 
					}
						
					stmt.executeUpdate(sqlE);  
					
					System.out.println("Delete complete.");
				
				} 
				catch (Exception exc)
				{
					System.out.println(exc);
				}
			}	//////////////////////////////////////////////////////////////////////////////////////////////////////////// user inputs S 
			else if(input.equals("S")) {

				System.out.println("Enter search query: "); 
				
				InputStreamReader isS = new InputStreamReader(System.in); 
				BufferedReader inS = new BufferedReader(isS); 
				
				String search = ""; 
				try {
					search = inS.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
				try {			  
					//1. Get a connection to database
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Database_final_project","root","Eba0899!");  
					
					//2. Create a statement 
					Statement stmt = con.createStatement();  
					
					//3. Execute SQL query
					stmt.executeQuery(search);  
									
				} 
				catch (Exception exc)
				{
					System.out.println(exc);
				}
				
				
			} /////////////////////////////////////////////////////////////////////// user inputs V - View
			else if(input.equals("V")) {
				
				System.out.println("Enter view: "); 
				
				InputStreamReader isV = new InputStreamReader(System.in); 
				BufferedReader inV = new BufferedReader(isV); 
				
				String view = ""; 
				try {
					view = inV.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
				try {			  
					//1. Get a connection to database
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Database_final_project","root","Eba0899!");  
					
					//2. Create a statement 
					Statement stmt = con.createStatement();  
					
					//3. Execute SQL query
					stmt.executeUpdate(view);  
									
				} 
				catch (Exception exc)
				{
					System.out.println(exc);
				}
				
			}
			else if (input.equals("T")){ ////////////////////////////////////////////////////////////////////// user inputs T - Trigeer
				System.out.println("Enter trigger: "); 
				
				InputStreamReader isT = new InputStreamReader(System.in); 
				BufferedReader inT = new BufferedReader(isT); 
				
				String trigger = ""; 
				try {
					trigger = inT.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
				try {			  
					//1. Get a connection to database
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Database_final_project","root","Eba0899!");  
					
					//2. Create a statement 
					Statement stmt = con.createStatement();  
					
					//3. Execute SQL query
					stmt.executeUpdate(trigger);  
					
				} 
				catch (Exception exc)
				{
					System.out.println(exc);
				}
			}  
			//////////////////////////////////////////////////////////////////////// user inputs X
			else if (input.equals("X")) {
				break; 
			} //////////////////////////////////////////////////////////////////////// user input invalid char
			else {
				System.out.println("Invalid input. Please enter one of the above options." ); 
			}
		
		
		}// end of while loop
		
		
	 
		
	} // main
} // class



