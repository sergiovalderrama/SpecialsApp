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

@WebServlet(name = "CustomerRegistration", urlPatterns = {"/CustomerRegistration"})
public class CustomerRegistration extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            request.getRequestDispatcher("WEB-INF/cregistration.jsp").forward(request, response);
        }else if (action.equals("verifycregistration")) {
            verifyCustomerRegistration(request, response);
        }
    }
    private void verifyCustomerRegistration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String username = request.getParameter("username").toLowerCase();
            String username2 = request.getParameter("username2").toLowerCase();
            String password = request.getParameter("password");
            String password2 = request.getParameter("password2");
            String email = request.getParameter("email").toLowerCase();
            String email2 = request.getParameter("email2").toLowerCase();
            UserVerification userVerify = new UserVerification();
            String message = userVerify.verifyUser(username, username2, password, password2, email, email2);
            if (!message.equals("valid")) {
                request.setAttribute("flash", message);
                request.getRequestDispatcher("WEB-INF/cregistration.jsp").forward(request, response);
            } else {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
                EntityManager em = emf.createEntityManager();
                Cuser cusername = new Cuser();
                cusername.setUsername(username);
                cusername.setPassword(password);
                cusername.setEmail(email);
                em.getTransaction().begin();
                em.persist(cusername);
                em.getTransaction().commit();
                em.close();
                request.setAttribute("flash", "Thank you for registering.");
                request.getRequestDispatcher("WEB-INF/clogin.jsp").forward(request, response);
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
