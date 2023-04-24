package tw.badminton.eeit58;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONStringer;
import org.json.JSONWriter;

@WebServlet("/deleteActivity")
public class deleteActivity extends HttpServlet {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/eeit58group3";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "root";
	private Connection conn;

	public deleteActivity() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");

		try {
			String sql1 = " DELETE FROM activity_add WHERE id = ? ";
			PreparedStatement statement1 = conn.prepareStatement(sql1);
			statement1.setString(1,id);
			int o = statement1.executeUpdate();
			if(o > 0) {
			String sql2 = " DELETE FROM activity WHERE id = ? ";
			PreparedStatement statement2 = conn.prepareStatement(sql2);
			statement2.setString(1,id);
			int i= statement2.executeUpdate();
			if(i == 1){
	            System.out.println("已取消");
	            response.setCharacterEncoding("UTF-8");
				response.getWriter().print("success");
	        }
	        else if(i==0)
	        {
	            System.out.println("請再試一次1");
	        }
			}else{
	            System.out.println("請再試一次2");
	        }
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
