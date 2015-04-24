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
                if (buser.getStatus().equals("pending")) {
                    request.setAttribute("flash", "Your membership has not been approved yet.");
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
                request.getRequestDispatcher("WEB-INF/bmenu.jsp").forward(request, response);
            } catch (NoResultException nre) {
                request.setAttribute("flash", "Incorrect Username/Password combinatioin");
                request.getRequestDispatcher("WEB-INF/blogin.jsp").forward(request, response);
            } catch (Exception e) {
                request.setAttribute("flash", e);
                request.getRequestDispatcher("WEB-INF/blogin.jsp").forward(request, response);
            }
            request.getRequestDispatcher("WEB-INF/blogin.jsp").forward(request, response);
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
