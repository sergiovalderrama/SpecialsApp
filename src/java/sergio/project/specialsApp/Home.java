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
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Home extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
        EntityManager em = emf.createEntityManager();
        if (action == null) {

            List<Specials> specials = em.createQuery("SELECT b.bname, b.id, "
                    + "s.sdate, s.stime, s.stime2, s.stype, s.price, s.special FROM Bprofile b, Specials s "
                    + "WHERE b.buserid = s.buserid AND s.sdate >= CURRENT_DATE ORDER BY s.sdate, s.stime")
                    .getResultList();
            request.setAttribute("specials", specials);

            request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
        }
        if (action.equals("sortbydate")) {
            String sortingDate = request.getParameter("sortingdate");
            String specialType = request.getParameter("type");
            SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date juSortDate = null;
            try {
                juSortDate = sdfdate.parse(sortingDate);
            } catch (ParseException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (!specialType.equals("All")) {
                
                List<Specials> sortedDateList2 = em.createQuery("SELECT b.bname, b.id, "
                        + "s.sdate, s.stime, s.stime2, s.stype, s.price, s.special FROM Bprofile b, Specials s "
                        + "WHERE b.buserid = s.buserid "
                        + "and s.sdate =:sdate and s.stype =:stype ORDER BY s.stime")
                        .setParameter("sdate", juSortDate)
                        .setParameter("stype", specialType)
                        .getResultList();
                request.setAttribute("specials", sortedDateList2);
                request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
            }
            List<Specials> sortedDateList = em.createQuery("SELECT b.bname, b.id, "
                    + "s.sdate, s.stime, s.stime2, s.stype, s.price, s.special FROM Bprofile b, Specials s "
                    + "WHERE b.buserid = s.buserid "
                    + "and s.sdate =:sdate ORDER BY s.stime")
                    .setParameter("sdate", juSortDate)
                    .getResultList();
            request.setAttribute("specials", sortedDateList);
            request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);

        }
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
