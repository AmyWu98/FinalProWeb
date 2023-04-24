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

import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONWriter;

@WebServlet("/viewcalendarHost")
public class viewcalendarHost extends HttpServlet {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/eeit58group3";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "root";
	private Connection conn;

	public viewcalendarHost() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
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
		String memberId = request.getParameter("memberId");
		JSONStringer js = new JSONStringer();
		JSONWriter jw = js.array();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			String sql = "SELECT * FROM activity WHERE host = ?";
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, memberId);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
					String id = rs.getString("id");
					String activityTitle = rs.getString("activityTitle");
					String activityTime = rs.getString("activityTime");
					String location = rs.getString("location");
					String fee = rs.getString("fee");
					String postTime = rs.getString("postTime");
					String contact = rs.getString("contact");
					String participation = rs.getString("participation");
					String description = rs.getString("description");
					String reservation = rs.getString("reservation");
					jw.object();
					jw.key("id").value(id);
					jw.key("activityTitle").value(activityTitle);
					jw.key("activityTime").value(activityTime);
					jw.key("location").value(location);
					jw.key("fee").value(fee);
					jw.key("postTime").value(postTime);
					jw.key("contact").value(contact);
					jw.key("participation").value(participation);
					jw.key("description").value(description);
					jw.key("reservation").value(reservation);
					jw.endObject();
				}			
			jw.endArray();
//			System.out.print(jw);
		} catch (Exception e) {
			e.printStackTrace();
		}

//		request.setCharacterEncoding("UTF-8");
//		request.setAttribute("user", jw);
//		request.getRequestDispatcher("/calendarall.jsp").forward(request, response);
		
//		response.getWriter().write(jw.toString());
//		System.out.println(jw);
//		System.out.println("---------");
//		System.out.println(jw.getClass());
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(jw);

	}

}
