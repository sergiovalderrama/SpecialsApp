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

@WebServlet(name = "BusinessPost", urlPatterns = {"/BusinessPost"})
public class BusinessPost extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Buser buser = (Buser) request.getSession().getAttribute("buser");
        if (buser == null) {
            request.getRequestDispatcher("WEB-INF/blogin.jsp").forward(request, response);
        }
        String action = request.getParameter("action");
        if (action == null || action.equals("bpost")) {
            request.setAttribute("specials", postedSpecials(request, buser));
            request.getRequestDispatcher("WEB-INF/bpost.jsp").forward(request, response);
        }
        if (action.equals("verifybpost")) {
            String date = request.getParameter("date");
            String time = request.getParameter("time");
            String time2 = request.getParameter("time2");
            String type = request.getParameter("type");
            String price = request.getParameter("price");
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
                postspecial.setPrice(price);
                postspecial.setSpecial(special);
                postspecial.setStype(type);
                postspecial.setBuserid(buser);
                em.getTransaction().begin();
                em.persist(postspecial);
                em.getTransaction().commit();
                em.close();
            } catch (ConstraintViolationException cve) {
                request.setAttribute("flash", cve);
            }
            request.setAttribute("specials", postedSpecials(request, buser));
            request.getRequestDispatcher("WEB-INF/bpost.jsp").forward(request, response);
        }
        if (action.equals("delspecial")) {
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
            request.setAttribute("specials", postedSpecials(request,buser));
            request.getRequestDispatcher("WEB-INF/bpost.jsp").forward(request, response);
        }
        if(action.equals("sortbydate")){
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
            request.getRequestDispatcher("WEB-INF/bpost.jsp").forward(request, response);
        }
        if(action.equals("all")){
            request.setAttribute("specials", postedSpecials(request,buser));
            request.getRequestDispatcher("WEB-INF/bpost.jsp").forward(request, response);
        }
        if (action.equals("bmenu")) {
            response.sendRedirect("BusinessMenu");
        }
    }
   
    private List postedSpecials(HttpServletRequest request, Buser buser) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
        EntityManager em = emf.createEntityManager();
        List<Specials> slist = em.createNamedQuery("Specials.findByBuserid")
                .setParameter("buserid", buser).getResultList();
        return slist;
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
