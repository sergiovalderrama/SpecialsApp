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

@WebServlet(name = "AdministratorDisabledBusers", urlPatterns = {"/AdministratorDisabledBusers"})
public class AdministratorDisabledBusers extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Administrator administrator = (Administrator) request.getSession().getAttribute("administrator");
        String action = request.getParameter("action");
        if (administrator == null) {
            request.setAttribute("flash", "Please login to access this option.");
            request.getRequestDispatcher("WEB-INF/admindisabledbusers.jsp").forward(request, response);
        }
        if (action == null) {
            disabledBusinessUsers(request);
            request.getRequestDispatcher("WEB-INF/admindisabledbusers.jsp").forward(request, response);
        } else if (action.equals("changestatus")) {
            AdministratorMenu status = new AdministratorMenu();
            status.changeBuserStatus(request);
        }else if (action.equals("bprofile")){
            AdministratorMenu profile = new AdministratorMenu();
            profile.getBuserProfile(request);
        }
        disabledBusinessUsers(request);
        request.getRequestDispatcher("WEB-INF/admindisabledbusers.jsp").forward(request, response);
    }
    private void disabledBusinessUsers(HttpServletRequest request){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
        EntityManager em = emf.createEntityManager();
        String status = "disabled";
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
