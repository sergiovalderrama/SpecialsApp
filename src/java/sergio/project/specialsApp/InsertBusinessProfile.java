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

@WebServlet(name = "InsertBusinessProfile", urlPatterns = {"/InsertBusinessProfile"})
public class InsertBusinessProfile extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            request.getRequestDispatcher("WEB-INF/brofile.jsp").forward(request, response);
        }
        if (action.equals("bprofile")) {
            String bname = request.getParameter("bname");
            String bphone = request.getParameter("bphone");
            String baddress = request.getParameter("baddress");
            String bwebsite = request.getParameter("bwebsite");
            Buser buser = (Buser) request.getSession().getAttribute("buser");
            if (buser == null) {
                response.sendRedirect("BusinessLogin");
            }
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
            EntityManager em = emf.createEntityManager();
            Bprofile bprofile = new Bprofile();
            bprofile.setBname(bname);
            bprofile.setPhone(bphone);
            bprofile.setAddress(baddress);
            bprofile.setWebsite(bwebsite);
            bprofile.setBuserid(buser);
            em.getTransaction().begin();
            em.merge(bprofile);
            em.getTransaction().commit();
            em.close();
            request.getRequestDispatcher("WEB-INF/bmenu.jsp").forward(request, response);
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
