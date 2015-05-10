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
import org.markdown4j.Markdown4jProcessor;

@WebServlet(name = "ReviewsOnBusiness", urlPatterns = {"/ReviewsOnBusiness"})
public class ReviewsOnBusiness extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bid = request.getParameter("bid");
        request.setAttribute("bid", bid);
        String action = request.getParameter("action");
        Cuser cuser = (Cuser) request.getSession().getAttribute("cuser");
        if (cuser == null) {
            request.setAttribute("flash", "Please login to your customer account to review/view reviews.");
            request.getRequestDispatcher("WEB-INF/clogin.jsp").forward(request, response);
        }
        if (action == null) {
            request.getRequestDispatcher("WEB-INF/creviews.jsp").forward(request, response);
        } else if (action.equals("reviews")) {
            postAReview(request, cuser);
        }
        getReviewsForBusiness(request);
        request.getRequestDispatcher("WEB-INF/creviews.jsp").forward(request, response);
    }
    private void getReviewsForBusiness(HttpServletRequest request){
        int bid = Integer.parseInt(request.getParameter("bid"));
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
        EntityManager em = emf.createEntityManager();
        Buser buser = (Buser)em.createNamedQuery("Buser.findById")
                .setParameter("id", bid)
                .getSingleResult();
        String profileQuery ="SELECT b FROM Bprofile b WHERE b.buserid =:buserid";
        Bprofile bprofile = (Bprofile)em.createQuery(profileQuery)
                .setParameter("buserid", buser)
                .getSingleResult();
        String query = "SELECT Review.post, Review.rdatetime, Review.cuserid, "
                + "Cuser.username, Cuser.id FROM Review, Cuser "
                + "WHERE Review.buserid =? AND Review.cuserid = Cuser.id ORDER BY Review.rdatetime DESC";
        List<Object> getReviews = em.createNativeQuery(query)
                .setParameter(1, bid)
                .getResultList();
        
        
        request.setAttribute("bReviews", getReviews);
        request.setAttribute("Bprofile", bprofile);
                
    }
    private void postAReview(HttpServletRequest request, Cuser cuser) throws IOException {
        try {
            String bid = request.getParameter("bid");
            int bidInteger = Integer.parseInt(bid);
            String creview = request.getParameter("creview");
            String md4jcustomerReview = new Markdown4jProcessor().process(creview);
            java.util.Date currentDate = new java.util.Date();
            
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
            EntityManager em = emf.createEntityManager();
            String query = "SELECT b FROM Buser b WHERE b.id =:id";
            Buser buser = (Buser) em.createQuery(query)
                    .setParameter("id", bidInteger)
                    .getSingleResult();
            Review review = new Review();
            review.setPost(md4jcustomerReview);
            review.setRdatetime(currentDate);
            review.setCuserid(cuser);
            review.setBuserid(buser);
            em.getTransaction().begin();
            em.persist(review);
            em.getTransaction().commit();
            em.close();
            request.setAttribute("flash", "Thank you for posting");
        } catch (NoResultException nre) {
            request.setAttribute("flash", nre);
        }
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
