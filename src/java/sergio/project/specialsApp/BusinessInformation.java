package sergio.project.specialsApp;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "BusinessInformation", urlPatterns = {"/BusinessInformation"})
public class BusinessInformation extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            getBusinessProfile(request);
        } else if (action.equals("subscribe")) {
            subscribeToBusiness(request);
        }
        request.getRequestDispatcher("WEB-INF/binformation.jsp").forward(request, response);
    }

    private void getBusinessProfile(HttpServletRequest request) {
        int pid = Integer.parseInt(request.getParameter("pid"));
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
        EntityManager em = emf.createEntityManager();
        Bprofile bprofile = (Bprofile) em.createNamedQuery("Bprofile.findById")
                .setParameter("id", pid).getSingleResult();
        request.setAttribute("bprofile", bprofile);
    }

    private void subscribeToBusiness(HttpServletRequest request) {
        Cuser cuser = (Cuser) request.getSession().getAttribute("cuser");
        int buserid = Integer.parseInt(request.getParameter("bid"));
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
        EntityManager em = emf.createEntityManager();
        Buser buser = (Buser) em.createNamedQuery("Buser.findById")
                .setParameter("id", buserid)
                .getSingleResult();
        if (cuser == null) {
            request.setAttribute("flash", "Please login to your customer account to subscribe.");
            getBusinessProfile(request);
        } else if (!checkForUniqueSubscriptions(request, buser, cuser)) {
            request.setAttribute("flash", "You have already subscribed to this Business");
            getBusinessProfile(request);
        } else {
            Subscription subscribe = new Subscription();
            subscribe.setCuserid(cuser);
            subscribe.setBuserid(buser);
            em.getTransaction().begin();
            em.merge(subscribe);
            em.getTransaction().commit();
            em.close();
            request.setAttribute("flash", "You are now subscribed.");
        }
        getBusinessProfile(request);
    }

    private boolean checkForUniqueSubscriptions(HttpServletRequest request, Buser buser, Cuser cuser) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
        EntityManager em = emf.createEntityManager();
        String query = "SELECT s FROM Subscription s WHERE s.buserid =:buserid AND s.cuserid =:cuserid";
        try {
            Subscription subscription = (Subscription) em.createQuery(query)
                    .setParameter("buserid", buser)
                    .setParameter("cuserid", cuser)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return true;
        }

        return false;
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
