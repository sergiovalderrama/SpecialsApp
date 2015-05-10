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

@WebServlet(name = "BusinessInformation", urlPatterns = {"/BusinessInformation"})
public class BusinessInformation extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            getBusinessProfile(request);
        } else if (action.equals("subscribe")) {
            subscribeToBusiness(request);
        } else if (action.equals("rate")) {
            rateBusiness(request);
        }
        getBusinessProfile(request);
        request.getRequestDispatcher("WEB-INF/binformation.jsp").forward(request, response);
    }
    
    private void getBusinessProfile(HttpServletRequest request) {
        int pid = Integer.parseInt(request.getParameter("pid"));
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
        EntityManager em = emf.createEntityManager();
        Bprofile bprofile = (Bprofile) em.createNamedQuery("Bprofile.findById")
                .setParameter("id", pid).getSingleResult();
        request.setAttribute("bprofile", bprofile);
        
        String query="SELECT b.rating FROM Brating b WHERE b.buserid=:buserid";
        List<Integer> ratingList = em.createQuery(query)
                .setParameter("buserid", bprofile.getBuserid())
                .getResultList();
        double sum = 0;
        for (Integer ratingList1 : ratingList) {
            sum += ratingList1;
        }
        Double average = sum/ratingList.size();
        String averageRating = "averageRating" +java.lang.Math.round(average);
        request.setAttribute(averageRating, "checked");
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
        } else if (!checkForUniqueSubscriptions(buser, cuser)) {
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
    }

    private void rateBusiness(HttpServletRequest request) {
        Cuser cuser = (Cuser) request.getSession().getAttribute("cuser");
        int bid = Integer.parseInt(request.getParameter("bid"));
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
        EntityManager em = emf.createEntityManager();
        Buser buser = (Buser)em.createNamedQuery("Buser.findById")
                .setParameter("id", bid)
                .getSingleResult();
        int currentRating = 0;//take out
        int bratingID = 0;
        if (cuser == null) {
            request.setAttribute("flash", "Please login to your customer account to rate.");
            getBusinessProfile(request);
        } else{
            try{
            String query ="Select b FROM Brating b WHERE b.cuserid =:cuserid AND b.buserid =:buserid";
            Brating checkRating = (Brating)em.createQuery(query)
                    .setParameter("cuserid", cuser)
                    .setParameter("buserid", buser)
                    .getSingleResult();
            currentRating = checkRating.getRating();//take out
            bratingID = checkRating.getId();
            }catch(NoResultException nre){
            int rating = Integer.parseInt(request.getParameter("rating"));
            Brating brating = new Brating();
            brating.setCuserid(cuser);
            brating.setBuserid(buser);
            brating.setRating(rating);
             em.getTransaction().begin();
                em.persist(brating);
                em.getTransaction().commit();
                em.close();
            }
            int rating = Integer.parseInt(request.getParameter("rating"));
            Brating overrideRating = (Brating)em.createNamedQuery("Brating.findById")
                    .setParameter("id", bratingID)
                    .getSingleResult();
            overrideRating.setRating(rating);
            em.getTransaction().begin();
            em.merge(overrideRating);
            em.getTransaction().commit();
            String star = "star" + rating;
            request.setAttribute(star, "checked");
        }
    }
    private boolean checkForUniqueSubscriptions(Buser buser, Cuser cuser) {
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
