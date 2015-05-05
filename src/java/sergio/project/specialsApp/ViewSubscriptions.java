package sergio.project.specialsApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ViewSubscriptions", urlPatterns = {"/ViewSubscriptions"})
public class ViewSubscriptions extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            viewBusinessSubscriptions(request);
        }else if (action.equals("unsubscribe")){
            unsubscribeBusiness(request);
        }
        request.getRequestDispatcher("WEB-INF/cviewsubscriptions.jsp").forward(request, response);
    }

    private void viewBusinessSubscriptions(HttpServletRequest request) {
        Cuser cuser = (Cuser) request.getSession().getAttribute("cuser");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
        EntityManager em = emf.createEntityManager();
        String query = "SELECT b.buserid, b.bname, s.id, b.id FROM Bprofile b, Subscription s "
                + "WHERE s.cuserid =:cuserid AND b.buserid = s.buserid";
        List<Subscription> sublist = em.createQuery(query)
                .setParameter("cuserid", cuser)
                .getResultList();
        request.setAttribute("sublist", sublist);
    }
    private void unsubscribeBusiness(HttpServletRequest request){
        int subscriptionid= Integer.parseInt(request.getParameter("subscriptionid"));
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
        EntityManager em = emf.createEntityManager();
        Subscription subscription = (Subscription)em.createNamedQuery("Subscription.findById")
                .setParameter("id", subscriptionid)
                .getSingleResult();
        em.getTransaction().begin();
        em.remove(em.merge(subscription));
        em.getTransaction().commit();
        em.close();
        viewBusinessSubscriptions(request);
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
