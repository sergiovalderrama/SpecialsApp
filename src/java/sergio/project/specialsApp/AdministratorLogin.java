package sergio.project.specialsApp;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AdministratorLogin", urlPatterns = {"/AdministratorLogin"})
public class AdministratorLogin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            request.getRequestDispatcher("WEB-INF/alogin.jsp").forward(request, response);
        }
        if (action.equals("verifyalogin")) {
            verifyAdministratorLogin(request, response);
        }
    }

    private void verifyAdministratorLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username").toLowerCase();
        String password = request.getParameter("password");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
        EntityManager em = emf.createEntityManager();
        try {

            Administrator administrator = (Administrator) em.createNamedQuery("Administrator.findByUsername")
                    .setParameter("username", username)
                    .getSingleResult();
            if (!administrator.getPassword().equals(password)) {
                request.setAttribute("flash", "Incorrect Username/Password combination. ");
                request.getRequestDispatcher("WEB-INF/alogin.jsp").forward(request, response);
            }
            request.getSession().setAttribute("administrator", administrator);
            response.sendRedirect("AdministratorMenu");
        } catch (NoResultException nre) {
            request.setAttribute("flash", "Incorrect Username/Password combinatioin");
            request.getRequestDispatcher("WEB-INF/alogin.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("flash", e);
            request.getRequestDispatcher("WEB-INF/alogin.jsp").forward(request, response);
        }
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
