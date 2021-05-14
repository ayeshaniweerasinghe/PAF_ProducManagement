package com;

import java.sql.*;

public class Product 
{

			//CONNECTION
			public Connection connect()
			{
					Connection con = null;

					try
					{
							Class.forName("com.mysql.jdbc.Driver");
							con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
			
					}
					catch(Exception e)
					{
							e.printStackTrace();
					}

					return con;
			}
			
			
			
			
			//READ
			public String readProduct()
			{
					String output = "";
					
					try
					{
							Connection con = connect();
							
							if (con == null)
							{
									return "Error while connecting to the database for reading.";
							}
							
							// Prepare the HTML table to be displayed
							output = "<table  class='table table-dark table-striped'><tr><th>Product Name</th>"
									+"<th>Product Description</th>"
									+ "<th>Product Manufacture</th>"
									+ "<th>Product Price</th>"
									+ "<th>Edit</th><th>Delete</th></tr>";

							String query = "select * from products";
							Statement stmt = con.createStatement();
							ResultSet rs = stmt.executeQuery(query);

							// iterate through the rows in the result set
							while (rs.next())
							{
									String productID = Integer.toString(rs.getInt("productID"));
									String productName = rs.getString("productName");
									String productDesc = rs.getString("productDesc");
									String productManu = rs.getString("productManu");
									String productPrice = Double.toString(rs.getDouble("productPrice"));
									

									// Add a row into the HTML table
									output += "<tr><td>" + productName + "</td>";
									output += "<td>" + productDesc + "</td>";
									output += "<td>" + productManu + "</td>";
									output += "<td>" + productPrice + "</td>"; 
				

									// buttons
									output += "<td><input name='btnUpdate' type='button' value='Edit' class='btnUpdate btn btn-secondary' data-productid='" + productID + "'></td>"
											+"<td><input name='btnRemove' type='button' value='Delete' class='btnRemove btn btn-danger' data-productid='" + productID + "'>" + "</td></tr>";
							}

							con.close();

							// Complete the HTML table
							output += "</table>";
					}
					catch (Exception e)
					{
							output = "Error while reading the products.";
							System.err.println(e.getMessage());
					}
					
					return output;
			}
			
			
			
			

			//INSERT
			public String insertProduct(String name, String desc, String manuf, String price)
			{
					String output = "";
					
					try
					{
							Connection con = connect();
							
							if (con == null)
							{
									return "Error while connecting to the database for inserting";
							}

							// create a prepared statement
							String query = " insert into products (`productID`,`productName`,`productDesc`,`productManu`,`productPrice`) values (?, ?, ?, ?, ?)";
							
							PreparedStatement preparedStmt = con.prepareStatement(query);

							// binding values
							preparedStmt.setInt(1, 0);
							preparedStmt.setString(2, name);
							preparedStmt.setString(3, desc);
							preparedStmt.setString(4, manuf);
							preparedStmt.setDouble(5, Double.parseDouble(price));

							//execute the statement
							preparedStmt.execute();
							con.close();

							String newProducts = readProduct();
							output = "{\"status\":\"success\", \"data\": \"" + newProducts + "\"}";
			
					}
					catch (Exception e)
					{
								output = "{\"status\":\"error\", \"data\":\"Error while inserting the product.\"}";
								System.err.println(e.getMessage());
					}
					
					return output;
			}
			

			
			//UPDATE
			public String updateProduct(String ID,String name, String desc, String manuf, String price)
			{
					String output = "";
					
					try
					{
							Connection con = connect();
							
							if (con == null)
							{
									return "Error while connecting to the database for updating";
							}

							// create a prepared statement
							String query = "UPDATE products SET productName=?, productDesc=?, productManu=?, productPrice=? WHERE productID=?";
							
							PreparedStatement preparedStmt = con.prepareStatement(query);

							// binding values
							preparedStmt.setString(1, name);
							preparedStmt.setString(2, desc);
							preparedStmt.setString(3, manuf);
							preparedStmt.setDouble(4, Double.parseDouble(price));
							preparedStmt.setInt(5, Integer.parseInt(ID));

							//execute the statement
							preparedStmt.executeUpdate();
							con.close();

							String newProducts = readProduct();
							output = "{\"status\":\"success\", \"data\": \"" + newProducts + "\"}";
			
			
					}
					catch (Exception e)
					{
							output = "{\"status\":\"error\", \"data\":\"Error while updating the product.\"}";
							System.err.println(e.getMessage());
					}
					
					return output;
			}
			
			

			//DELETE
			public String deleteProduct(String pID)
			{
					String output = "";
					
					try
					{
							Connection con = connect();
							
							if (con == null)
							{
									return "Error while connecting to the database for deleting";
							}

							// create a prepared statement
							String query = "DELETE from products where productID=?";
							
							PreparedStatement preparedStmt = con.prepareStatement(query);

							// binding values
							preparedStmt.setInt(1, Integer.parseInt(pID));

							//execute the statement
							preparedStmt.executeUpdate();
							con.close();

							String newProducts = readProduct();
							output = "{\"status\":\"success\", \"data\": \"" + newProducts + "\"}";
					}
					catch (Exception e)
					{
						output = "{\"status\":\"error\", \"data\":\"Error while deleting the product.\"}";
						System.err.println(e.getMessage());
					}
					
					return output;
			}

	
}
