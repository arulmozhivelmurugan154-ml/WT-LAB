import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SessionHandlingServlet")
public class SessionHandlingServlet extends HttpServlet {

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        // Get username from the form
        String username = request.getParameter("username");

        // Default username
        if (username == null || username.isEmpty()) {
            username = "Guest";
        }

        // 1. URL Rewriting
        String urlRewritingLink =
                "SessionHandlingServlet?username=" + username;

        // 2. Hidden Form Field
        String hiddenForm =
                "<form action='SessionHandlingServlet' method='post'>"
                + "<input type='hidden' name='username' value='"
                + username + "'>"
                + "<button type='submit'>"
                + "Submit with Hidden Field"
                + "</button>"
                + "</form>";

        // 3. Cookies
        Cookie userCookie = new Cookie("username", username);
        userCookie.setMaxAge(3600); // Expires in 1 hour
        response.addCookie(userCookie);

        // 4. HTTP Session
        HttpSession session = request.getSession();

        session.setAttribute("username", username);

        // Response Output
        out.println("<html>");
        out.println("<body>");

        out.println("<h1>Welcome, " + username + "</h1>");

        out.println("<h2>Session Handling Demonstration</h2>");

        out.println("<ul>");

        // URL Rewriting
        out.println(
            "<li><a href='" + urlRewritingLink
            + "'>URL Rewriting</a></li>"
        );

        // Hidden Form
        out.println("<li>" + hiddenForm + "</li>");

        // Cookie
        out.println(
            "<li>Cookie Set: "
            + userCookie.getValue()
            + "</li>"
        );

        out.println("</ul>");

        // Session Details
        out.println("<h3>Session Details:</h3>");

        out.println(
            "<p>Session ID: "
            + session.getId()
            + "</p>"
        );

        out.println(
            "<p>Username in session: "
            + session.getAttribute("username")
            + "</p>"
        );

        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        doPost(request, response);
    }
}