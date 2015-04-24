package sergio.project.specialsApp;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "BusinessRegistration", urlPatterns = {"/BusinessRegistration"})
public class BusinessRegistration extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            request.getRequestDispatcher("WEB-INF/bregistration.jsp").forward(request, response);
        }
        if (action.equals("verifybregistration")) {
            String username = request.getParameter("username").toLowerCase();
            String username2 = request.getParameter("username2").toLowerCase();
            String password = request.getParameter("password");
            String password2 = request.getParameter("password2");
            String email = request.getParameter("email").toLowerCase();
            String email2 = request.getParameter("email2").toLowerCase();
            UserVerification verifyUser = new UserVerification();
            String message = verifyUser.verifyUser(username, username2, password, password2, email, email2);
            if (!message.equals("valid")) {
                request.setAttribute("flash", message);
                request.getRequestDispatcher("WEB-INF/bregistration.jsp").forward(request, response);
            } else {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
                EntityManager em = emf.createEntityManager();
                Buser busername = new Buser();
                busername.setUsername(username);
                busername.setPassword(password);
                busername.setEmail(email);
                busername.setStatus("pending");
                em.getTransaction().begin();
                em.persist(busername);
                em.getTransaction().commit();
                em.close();
                request.setAttribute("flash", "Thank you for registering.");
                request.getRequestDispatcher("WEB-INF/bregistration.jsp").forward(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
