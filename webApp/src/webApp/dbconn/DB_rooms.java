package webApp.dbconn;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB_rooms {
	    
	    	// Count total number of rooms in Hotel
		   public static int countTotalRooms(Connection conn) throws SQLException {
		 
		        String sql = "SELECT COUNT(*) FROM Room";
		 
		        Statement smt=conn.createStatement();
		  
		        ResultSet rs = smt.executeQuery(sql);
				
				if (rs.next()) {
					int count = rs.getInt(1);
					System.out.println(count);
					
					// conn.close();
					
					return count;
				}
				
				return 0;
				
				
				
		        }

}
