package sergio.project.specialsApp;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "BusinessLogin", urlPatterns = {"/BusinessLogin"})
public class BusinessLogin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            request.getRequestDispatcher("WEB-INF/blogin.jsp").forward(request, response);
        }
        if (action.equals("verifyblogin")) {
            verifyBusinessLogin(request, response);
        }
    }

    private void verifyBusinessLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username").toLowerCase();
        String password = request.getParameter("password");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
        EntityManager em = emf.createEntityManager();
        try {

            Buser buser = (Buser) em.createNamedQuery("Buser.findByUsername")
                    .setParameter("username", username)
                    .getSingleResult();
            if (!buser.getPassword().equals(password)) {
                request.setAttribute("flash", "Incorrect Username/Password combination. ");
                request.getRequestDispatcher("WEB-INF/blogin.jsp").forward(request, response);
            }
            request.getSession().setAttribute("buser", buser);
            try {
                Query query = em.createNativeQuery("SELECT id from BPROFILE WHERE buserid=?")
                        .setParameter(1, buser.getId());
                int value = (int) query.getSingleResult();
            } catch (NoResultException nre) {
                request.setAttribute("flash", "Please complete your profile to access the menu.");
                request.getRequestDispatcher("WEB-INF/bprofile.jsp").forward(request, response);
            }
            if (buser.getStatus().equals("pending")) {
                request.setAttribute("flash", "Your membership will be approved within two business days of registration.");
                request.getRequestDispatcher("WEB-INF/blogin.jsp").forward(request, response);
            }
            response.sendRedirect("BusinessPost");
        } catch (NoResultException nre) {
            request.setAttribute("flash", "Incorrect Username/Password combinatioin");
            request.getRequestDispatcher("WEB-INF/blogin.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("flash", e);
            request.getRequestDispatcher("WEB-INF/blogin.jsp").forward(request, response);
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
