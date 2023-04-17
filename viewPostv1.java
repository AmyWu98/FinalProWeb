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

/**
 * Servlet implementation class test4
 */
@WebServlet("/viewPostv1")
public class viewPostv1 extends HttpServlet {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/eeit58group3";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "root";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONStringer js = new JSONStringer();
		JSONWriter jw = js.array();
		try {
		Class.forName("com.mysql.jdbc.Driver");	
		Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		String sql = "SELECT *, member.Icon FROM activity INNER JOIN member WHERE activity.host=member.id;";
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			String id = rs.getString("id");
			String activityTitle = rs.getString("activityTitle");
			String description = rs.getString("description");
			String activityTime = rs.getString("activityTime");
			String locationSub = rs.getString("location");
			String level = rs.getString("level");
			String icon = rs.getString("Icon");
			String memberName= rs.getString("memberName");
			String location = rs.getString("location");
			jw.object();
			jw.key("id").value(id);
			jw.key("activityTitle").value(activityTitle);
			jw.key("description").value(description);
			jw.key("activityTime").value(activityTime);
			jw.key("locationSub").value(locationSub.substring(0, 3));
			jw.key("level").value(level);
			jw.key("icon").value(icon);
			jw.key("memberName").value(memberName);
			jw.key("location").value(location);
			jw.endObject();
		}
		jw.endArray();
		conn.close();
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("application/json");
//		response.getWriter().write(jw.toString());
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("user", jw);
		request.getRequestDispatcher("/test3.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.print(e);
		}
	
	}
		
	}
