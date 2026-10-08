<%@ page import="java.sql.*" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Database Data</title>

    <style>
        table {
            border-collapse: collapse;
            width: 80%;
            margin: 20px auto;
            font-family: Arial, sans-serif;
        }

        table, th, td {
            border: 1px solid black;
        }

        th, td {
            padding: 10px;
            text-align: center;
        }

        th {
            background-color: #f2f2f2;
        }
    </style>
</head>

<body>

    <h1 style="text-align: center;">Data from Database</h1>

    <%
        String jdbcURL = "jdbc:mysql://localhost:3306/student";
        String dbUser = "root";
        String dbPassword = "ifet";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(
                jdbcURL,
                dbUser,
                dbPassword
            );

            String query = "SELECT * FROM stuDetails";

            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            out.println("<table>");

            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Name</th>");
            out.println("<th>Age</th>");
            out.println("</tr>");

            while (rs.next()) {

                out.println("<tr>");

                out.println(
                    "<td>" + rs.getInt("id") + "</td>"
                );

                out.println(
                    "<td>" + rs.getString("name") + "</td>"
                );

                out.println(
                    "<td>" + rs.getInt("age") + "</td>"
                );

                out.println("</tr>");
            }

            out.println("</table>");

        } catch (Exception e) {

            out.println(
                "<p style='color: red;'>Error: "
                + e.getMessage()
                + "</p>"
            );

        } finally {

            try {

                if (rs != null)
                    rs.close();

                if (stmt != null)
                    stmt.close();

                if (conn != null)
                    conn.close();

            } catch (SQLException e) {

                out.println(
                    "<p style='color: red;'>Error closing connection: "
                    + e.getMessage()
                    + "</p>"
                );
            }
        }
    %>

</body>
</html>