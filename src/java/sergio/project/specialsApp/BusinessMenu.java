package sergio.project.specialsApp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "BusinessMenu", urlPatterns = {"/BusinessMenu"})
public class BusinessMenu extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Buser buser = (Buser) request.getSession().getAttribute("buser");
        if (buser == null) {
            request.setAttribute("flash", "Please login to access this option.");
            request.getRequestDispatcher("WEB-INF/blogin.jsp").forward(request, response);
        }
        request.getRequestDispatcher("WEB-INF/bmenu.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
