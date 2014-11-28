package session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class check
 */

public class check extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public check() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		boolean reset = false;
		if (request.getParameter("reset") != null
				&& request.getParameter("reset").equalsIgnoreCase("true")) {
			reset = true;
		}

		HttpSession session = request.getSession();
		if (reset) {
			session.setAttribute("RequestNum", 0);
		} else {
			Integer number = (Integer) session.getAttribute("RequestNum");
			if (null == number) {
				number = 0;
			}
			number = number.intValue() + 1;
			session.setAttribute("RequestNum", number);
		}
		Integer requestNum = (Integer) session.getAttribute("RequestNum");
		String outputBody = "Session request number::" + requestNum;

		response.getOutputStream().write(outputBody.getBytes());
		response.setStatus(200);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
