package sergio.project.specialsApp;

import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CustomerLogin", urlPatterns = {"/CustomerLogin"})
public class CustomerLogin extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String action = request.getParameter("action");
        if (action == null) {
            request.getRequestDispatcher("WEB-INF/clogin.jsp").forward(request, response);
        }else if (action.equals("verifyclogin")) {
            verifyCustomerLogin(request, response);
        }
    }
    private void verifyCustomerLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String username = request.getParameter("username").toLowerCase();
            String password = request.getParameter("password");
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
            EntityManager em = emf.createEntityManager();
            try {

                Cuser customer = (Cuser) em.createNamedQuery("Cuser.findByUsername")
                        .setParameter("username", username)
                        .getSingleResult();
                if (!customer.getPassword().equals(password)) {
                    request.setAttribute("flash", "Incorrect Username/Password combination. ");
                    request.getRequestDispatcher("WEB-INF/clogin.jsp").forward(request, response);
                }
                request.getSession().setAttribute("cuser", customer);
            } catch (NoResultException nre) {
                request.setAttribute("flash", "Incorrect Username/Password combinatioin");
                request.getRequestDispatcher("WEB-INF/clogin.jsp").forward(request, response);
            } catch (Exception e) {
                request.setAttribute("flash", e);
                request.getRequestDispatcher("WEB-INF/clogin.jsp").forward(request, response);
            }
            response.sendRedirect("home");
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
