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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Home extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || action.equals("viewall")) {
            getAllSpecials(request, response);
        }else if (action.equals("sortbydate")) {
            sortByDate(request, response);
        }
    }

    private void getAllSpecials(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
        EntityManager em = emf.createEntityManager();
        List<Specials> specials = em.createQuery("SELECT b.picture, b.buserid, b.bname, b.id, "
                + "s.sdate, s.stime, s.stime2, s.stype, s.special FROM Bprofile b, Specials s "
                + "WHERE b.buserid = s.buserid AND s.sdate >= CURRENT_DATE ORDER BY s.sdate, s.stime")
                .getResultList();
        request.setAttribute("specials", specials);
        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }

    private void sortByDate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
        EntityManager em = emf.createEntityManager();
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
            List<Specials> sortedDateList2 = em.createQuery("SELECT b.picture, b.buserid, b.bname, b.id, "
                    + "s.sdate, s.stime, s.stime2, s.stype, s.special FROM Bprofile b, Specials s "
                    + "WHERE b.buserid = s.buserid "
                    + "and s.sdate =:sdate and s.stype =:stype ORDER BY s.stime")
                    .setParameter("sdate", juSortDate)
                    .setParameter("stype", specialType)
                    .getResultList();
            request.setAttribute("specials", sortedDateList2);
            request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
        }
        List<Specials> sortedDateList = em.createQuery("SELECT b.picture, b.buserid, b.bname, b.id, "
                + "s.sdate, s.stime, s.stime2, s.stype, s.special FROM Bprofile b, Specials s "
                + "WHERE b.buserid = s.buserid "
                + "and s.sdate =:sdate ORDER BY s.stime")
                .setParameter("sdate", juSortDate)
                .getResultList();
        request.setAttribute("specials", sortedDateList);
        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
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
