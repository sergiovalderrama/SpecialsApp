package sergio.project.specialsApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AdministratorApprovedBusers", urlPatterns = {"/AdministratorApprovedBusers"})
public class AdministratorApprovedBusers extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     Administrator administrator = (Administrator) request.getSession().getAttribute("administrator");
        String action = request.getParameter("action");
        if (administrator == null) {
            request.setAttribute("flash", "Please login to access this option.");
            request.getRequestDispatcher("WEB-INF/alogin.jsp").forward(request, response);
        }
        if (action == null) {
            approvedBusers(request);
            request.getRequestDispatcher("WEB-INF/adminapprovedbusers.jsp").forward(request, response);
        } else if (action.equals("changestatus")) {
            AdministratorMenu change = new AdministratorMenu();
            change.changeBuserStatus(request);
        }else if (action.equals("bprofile")){
            AdministratorMenu bprofile = new AdministratorMenu();
            bprofile.getBuserProfile(request);
        }
        approvedBusers(request);
        request.getRequestDispatcher("WEB-INF/adminapprovedbusers.jsp").forward(request, response);
    }
    private void approvedBusers(HttpServletRequest request){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
        EntityManager em = emf.createEntityManager();
        String status = "approved";
        List<Buser> buser = em.createNamedQuery("Buser.findByStatus")
                .setParameter("status", status)
                .getResultList();
        request.setAttribute("pending", buser);
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
