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


@WebServlet(name = "ViewSubscribedCustomers", urlPatterns = {"/ViewSubscribedCustomers"})
public class ViewSubscribedCustomers extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          getSubscribedCustomers(request);
          request.getRequestDispatcher("WEB-INF/viewbsubscribers.jsp").forward(request, response);
    }
    private void getSubscribedCustomers(HttpServletRequest request){
        Buser buser = (Buser)request.getSession().getAttribute("buser");
          EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
          EntityManager em = emf.createEntityManager();
          
          String query = "SELECT Cuser.username, Cuser.email "
                  + "FROM Cuser, Subscription WHERE Subscription.buserid =? "
                  + "AND Cuser.id = Subscription.cuserid";
          List<Object> customers = em.createNativeQuery(query)
                  .setParameter(1, buser.getId())
                  .getResultList();
          request.setAttribute("customers", customers);
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
    }// </editor-fold>

}
