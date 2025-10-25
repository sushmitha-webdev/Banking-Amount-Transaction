package com.tap.p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Banking {
private static final Scanner scan = new Scanner(System.in);
private static final String url = "jdbc:mysql://localhost:3306/employee01";
private static final String name = "root";
private static final  String password = "Sushmitha@123";
private static Connection connection;
private static final String UPADATE_AMOUNT_QUERY1="UPDATE `employee` SET `salary` = `salary`+ ? WHERE `name`=?";
private static final String query = "SELECT salary FROM employee WHERE name = ?";

public static void main(String[] args) {
	try
	{
//    	1.LOAD THE DRIVER CLASS
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		
//		2.ESTABLISH THE CONNECTION TO THE DATABASE
		connection =DriverManager.getConnection(url, name, password);
		
        transaction();	
		
	}
	catch(ClassNotFoundException e)
	{
		e.printStackTrace();
	}
	catch(SQLException e)
	{
		e.printStackTrace();
	}
	catch(Exception e)
	{
		e.getMessage();		
	}
  
  }


private static void transaction() throws SQLException {
	  
	        connection.setAutoCommit(false);
	        
			System.out.println("Enter the sender's name:");
			String sender=scan.next();
			System.out.println("Enter the receiver's name:");
			String receiver=scan.next();
			System.out.println("Enter the amount:");
			int amount=scan.nextInt();
			
			int totalSenders=updateAmount(sender,-amount);
			int totalReceivers=updateAmount(receiver,amount);	
			
			
			//Check sender exists and get balance
			int senderBalance = getBalance(sender);
			if (senderBalance == -1) {
				System.err.println("User not found: " + sender);
				System.err.println("Transaction aborted.");
				return;
			}

			//Check receiver exists
			int receiverBalance = getBalance(receiver);
			if (receiverBalance == -1) {
				System.err.println("User not found: " + receiver);
				System.err.println("Transaction aborted.");
				return;
			}
			
			
			// Check if sender has enough balance
			if (senderBalance < amount) 
			{
				System.err.println("Insufficient balance! Transaction aborted.");
				return;
			}
			
			
			
			if (isConfrim(totalSenders,totalReceivers))
			{
				connection.commit();
				System.out.println("Transaction succesfull");
			}
			else
			{
				connection.rollback();
				System.err.println("Transaction failed!!!");
			}
}


private static boolean isConfrim(int totalSenders, int totalReceivers)
{
	System.out.println("Do you want to confirm this transaction? (yes/no)");
	String choice=scan.next();
	
	return choice.equalsIgnoreCase("yes") && totalSenders ==1 && totalReceivers ==1;
	
}

private static int updateAmount(String user, int amount) throws SQLException 
{
	PreparedStatement statement;
	int rowsAffected =0;
	try {
		statement=connection.prepareStatement(UPADATE_AMOUNT_QUERY1);
		statement.setInt(1,amount);
		statement.setString(2,user);
		rowsAffected =statement.executeUpdate();
	}
	catch(SQLException e)
	{
		e.printStackTrace();
		throw e;
	}
	return rowsAffected;
	
}

//Method to get current balance of a user
private static int getBalance(String user) throws SQLException 
{
	
	PreparedStatement stmt = connection.prepareStatement(query);
	stmt.setString(1, user);
	ResultSet rs = stmt.executeQuery();
	
	if (rs.next()) 
	{
		return rs.getInt("salary");
	} 
	else
	{
		return -1;
	}
}

}

