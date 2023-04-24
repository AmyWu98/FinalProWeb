package tw.badminton.eeit58;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteadd")
public class deleteadd extends HttpServlet {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/eeit58group3";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "root";
	private Connection conn;

	public deleteadd() {
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
		String id = request.getParameter("id");

//		Enumeration<String> paramNames = request.getParameterNames();
//		while (paramNames.hasMoreElements()) {
//			String paramName = paramNames.nextElement();
//			String paramValue = request.getParameter(paramName);
//			System.out.println(paramName + ": " + paramValue);
//		}

		try {
			String sql = " DELETE FROM activity_add WHERE memberId = ? AND id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, memberId);
			statement.setString(2, id);
			int i = statement.executeUpdate();
			if (i == 1) {
				System.out.print("刪除成功");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().print("success");
			} else {
				System.out.println("請再試一次");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
