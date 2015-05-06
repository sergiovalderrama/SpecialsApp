package sergio.project.specialsApp;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import org.markdown4j.Markdown4jProcessor;

@WebServlet(name = "BusinessPost", urlPatterns = {"/BusinessPost"})
public class BusinessPost extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Buser buser = (Buser) request.getSession().getAttribute("buser");
        if (buser == null) {
            allPostedSpecials(request, response, buser);
            request.getRequestDispatcher("WEB-INF/blogin.jsp").forward(request, response);
        }
        String action = request.getParameter("action");
        if (action == null || action.equals("bpost")) {
            request.setAttribute("specials", postedSpecials(buser));
        }else if (action.equals("verifybpost")) {
            verifyNewPost(request, buser);
        }else if (action.equals("delspecial")) {
            deleteSelectedSpecial(request, buser);
        }else if(action.equals("sortbydate")){
           sortSpecialsByDate(request, response, buser);
        }else if(action.equals("all")){
            allPostedSpecials(request, response, buser);
        }
       request.getRequestDispatcher("WEB-INF/bpost.jsp").forward(request, response);
    }
   private void verifyNewPost(HttpServletRequest request, Buser buser) throws IOException{
       String date = request.getParameter("date");
            String time = request.getParameter("time");
            String time2 = request.getParameter("time2");
            String type = request.getParameter("type");
            String special = request.getParameter("special");
            SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdftime = new SimpleDateFormat("HH:mm");
            java.util.Date judate = null;
            java.util.Date jutime = null;
            java.util.Date jutime2 = null;
             try {
                judate = sdfdate.parse(date);
                jutime = sdftime.parse(time);
                jutime2 = sdftime.parse(time2);
            } catch (ParseException ex) {
                Logger.getLogger(BusinessPost.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
                EntityManager em = emf.createEntityManager();
                Specials postspecial = new Specials();
                postspecial.setSdate(judate);
                postspecial.setStime(jutime);
                postspecial.setStime2(jutime2);
                String md4jSpecial = new Markdown4jProcessor().process(special);
                postspecial.setSpecial(md4jSpecial);
                postspecial.setStype(type);
                postspecial.setBuserid(buser);
                em.getTransaction().begin();
                em.persist(postspecial);
                em.getTransaction().commit();
                em.close();
            } catch (ConstraintViolationException cve) {
                request.setAttribute("flash", cve);
            }
            request.setAttribute("specials", postedSpecials(buser));
   }
   private void deleteSelectedSpecial(HttpServletRequest request, Buser buser){
       int sid = Integer.parseInt(request.getParameter("delsbutton"));
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
            EntityManager em = emf.createEntityManager();
            Specials special = (Specials) em.createNamedQuery("Specials.findById")
                    .setParameter("id", sid)
                    .getSingleResult();
            em.getTransaction().begin();
            em.remove(em.merge(special));
            em.getTransaction().commit();
            em.close();
            request.setAttribute("specials", postedSpecials(buser));
   }
   private void sortSpecialsByDate(HttpServletRequest request, HttpServletResponse response, Buser buser){
        String sortingDate = request.getParameter("sortingdate");
            SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date juSortDate = null;
            try {
                juSortDate = sdfdate.parse(sortingDate);
            } catch (ParseException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
            EntityManager em = emf.createEntityManager();
            List<Specials> sortedDateList = em.createQuery("SELECT s FROM Specials s "
                    + "WHERE s.sdate = :sdate AND s.buserid = :buserid ORDER BY s.sdate, s.stime")
                    .setParameter("sdate", juSortDate)
                    .setParameter("buserid", buser)
                    .getResultList();
            request.setAttribute("specials", sortedDateList);
   }
   private void allPostedSpecials(HttpServletRequest request, HttpServletResponse response, Buser buser){
       request.setAttribute("specials", postedSpecials(buser));
   }
    private List postedSpecials(Buser buser) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
        EntityManager em = emf.createEntityManager();
        List<Specials> slist = em.createNamedQuery("Specials.findByBuserid")
                .setParameter("buserid", buser).getResultList();
        return slist;
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
