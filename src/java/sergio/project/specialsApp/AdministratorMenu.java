package sergio.project.specialsApp;

import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AdministratorMenu", urlPatterns = {"/AdministratorMenu"})
public class AdministratorMenu extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       Administrator administrator =(Administrator) request.getSession().getAttribute("administrator");
       String action = request.getParameter("action");
       if(administrator == null){
           request.setAttribute("flash", "Please login to access this option.");
           request.getRequestDispatcher("WEB-INF/alogin.jsp").forward(request, response);
       }
       if(action == null){
           pendingBusers(request);
           request.getRequestDispatcher("WEB-INF/amenu.jsp").forward(request, response);
       }
       if(action.equals("changestatus")){
           String status = request.getParameter("status");
           int businessid = Integer.parseInt(request.getParameter("bid"));
           EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
           EntityManager em = emf.createEntityManager();
           em.getTransaction().begin();
           Buser buser = (Buser) em.createNamedQuery("Buser.findById")
                   .setParameter("id", businessid)
                   .getSingleResult();
           buser.setStatus(status);
         
           
           //em.persist(buser);
           em.getTransaction().commit();
           em.close();
           pendingBusers(request);
           request.getRequestDispatcher("WEB-INF/amenu.jsp").forward(request, response);
       }
      
       pendingBusers(request);
       request.getRequestDispatcher("WEB-INF/amenu.jsp").forward(request, response);
    }
    private void pendingBusers(HttpServletRequest request){
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
       EntityManager em = emf.createEntityManager();
       String status="pending";
       List<Buser> buser = em.createNamedQuery("Buser.findByStatus")
                        .setParameter("status", status)
                        .getResultList();
       request.setAttribute("pending", buser);
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
