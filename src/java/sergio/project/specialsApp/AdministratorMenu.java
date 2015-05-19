package sergio.project.specialsApp;

import java.io.IOException;
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

@WebServlet(name = "AdministratorMenu", urlPatterns = {"/AdministratorMenu"})
public class AdministratorMenu extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Administrator administrator = (Administrator) request.getSession().getAttribute("administrator");
        String action = request.getParameter("action");
        if (administrator == null) {
            request.setAttribute("flash", "Please login to access this option.");
            request.getRequestDispatcher("WEB-INF/alogin.jsp").forward(request, response);
        }
        if (action == null) {
            pendingBusers(request);
            request.getRequestDispatcher("WEB-INF/amenu.jsp").forward(request, response);
        } else if (action.equals("changestatus")) {
            changeBuserStatus(request);
        }else if (action.equals("bprofile")){
            getBuserProfile(request);
        }
        pendingBusers(request);
        request.getRequestDispatcher("WEB-INF/amenu.jsp").forward(request, response);
    }

    private void changeBuserStatus(HttpServletRequest request) {
        String status = request.getParameter("status");
        int businessid = Integer.parseInt(request.getParameter("bid"));
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Buser buser = (Buser) em.createNamedQuery("Buser.findById")
                .setParameter("id", businessid)
                .getSingleResult();
        buser.setStatus(status);
        em.getTransaction().commit();
        em.close();
    }
    private void getBuserProfile(HttpServletRequest request){
        int bid = Integer.parseInt(request.getParameter("bid"));
        try{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
        EntityManager em = emf.createEntityManager();
        String query = "SELECT * FROM Bprofile WHERE Bprofile.buserid =?";
        Object bprofile = em.createNativeQuery(query)
                .setParameter(1, bid)
                .getSingleResult();
        request.setAttribute("bprofile", bprofile);
        }catch(NoResultException nre){
            request.setAttribute("flash", "This user has not completed their profile.");
        }
    }

    private void pendingBusers(HttpServletRequest request) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
        EntityManager em = emf.createEntityManager();
        String status = "pending";
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
