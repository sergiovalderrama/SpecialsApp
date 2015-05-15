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

@WebServlet(name = "InsertCustomerProfile", urlPatterns = {"/InsertCustomerProfile"})
public class InsertCustomerProfile extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("disabled", "disabled");
            checkCustomerProfile(request);
        } else if (action.equals("insert")) {
            if (validateFnameLname(request)) {
                insertCustomerProfile(request);
                editCustomerProfile(request, response);
                checkCustomerProfile(request);
            }
            request.getRequestDispatcher("WEB-INF/cprofile.jsp").forward(request, response);
        } else if (action.equals("edit")) {
            request.setAttribute("disabled", null);
            checkCustomerProfile(request);

        }
        request.getRequestDispatcher("WEB-INF/cprofile.jsp").forward(request, response);
    }

    private boolean validateFnameLname(HttpServletRequest request) {
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        if (fname == null) {
            return false;
        } else if (lname == null) {
            return false;
        } else {
            return true;
        }
    }

    private boolean checkCustomerProfile(HttpServletRequest request) {
        try {
            Cuser cuser = (Cuser) request.getSession().getAttribute("cuser");
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
            EntityManager em = emf.createEntityManager();
            String query = "SELECT c FROM Cprofile c WHERE c.cuserid =:cuserid";
            Cprofile cprofile = (Cprofile) em.createQuery(query)
                    .setParameter("cuserid", cuser)
                    .getSingleResult();
            request.setAttribute("cprofile", cprofile);
        } catch (NoResultException nre) {
            request.setAttribute("disabled", null);
            return true;
        }
        return false;
    }

    private void insertCustomerProfile(HttpServletRequest request) {
        if (checkCustomerProfile(request)) {
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            Cuser cuser = (Cuser) request.getSession().getAttribute("cuser");
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
            EntityManager em = emf.createEntityManager();
            Cprofile cprofile = new Cprofile();
            cprofile.setFname(fname);
            cprofile.setLname(lname);
            cprofile.setCuserid(cuser);
            em.getTransaction().begin();
            em.merge(cprofile);
            em.getTransaction().commit();
            em.close();
        }
    }

    private void editCustomerProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            Cuser cuser = (Cuser) request.getSession().getAttribute("cuser");
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
            EntityManager em = emf.createEntityManager();
            String query = "SELECT c FROM Cprofile c WHERE c.cuserid =:cuserid";
            Cprofile cprofile = (Cprofile) em.createQuery(query)
                    .setParameter("cuserid", cuser)
                    .getSingleResult();
            cprofile.setFname(fname);
            cprofile.setLname(lname);
            em.getTransaction().begin();
            em.merge(cprofile);
            em.getTransaction().commit();
            em.close();
            request.setAttribute("disabled", "disabled");
        } catch (NoResultException nre) {
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
