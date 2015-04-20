package sergio.project.specialsApp;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "home";
        }
        String destination = null;
        switch (action) {
            case "home":
                destination = homePage(request);
                break;
            case "binfo":
                destination = bInformation(request);
                break;
            case "about":
                destination = "about";
                break;
            case "blogin":
                destination = "blogin";
                break;
             case "verifyblogin":
                destination = verifyBLogin(request);
                break;    
            case "bregistration":
                destination = "bregistration";
                break;
            case "verifybregistration":
                destination = verifyBRegistration(request);
                break;
            case "cregistration":
                destination = "cregistration";
                break;
            case "verifycregistration":
                destination = verifyCRegistration(request);
                break;
            case "bmenu":
                destination = bMenu(request);
                break;
            case "bpost":
                destination = bPost(request);
                break;
            case "verifybpost":
                destination = verifyBPost(request);
                break;
            case "blogout":
                destination = bLogout(request);
                break;
            case "bprofile":
                destination = bProfile(request);
                break;
            case "delspecial":
                destination = delSpecial(request);
                break;
        }
        request.getRequestDispatcher("WEB-INF/" + destination +".jsp").forward(request, response);
    }
    private String homePage(HttpServletRequest request){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNativeQuery("SELECT Bprofile.bname, Bprofile.id, Specials.sdate,"
                + " Specials.stime, Specials.stime2, Specials.stype, Specials.special, Specials.price"
                + " FROM Bprofile,Specials WHERE Bprofile.buserid= Specials.buserid");
        Object  special = (Object)query.getResultList();
        request.setAttribute("scontent", special);
        return "index";
    }
    
    private String bInformation(HttpServletRequest request){
        int pid = Integer.parseInt(request.getParameter("pid"));
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
        EntityManager em = emf.createEntityManager();
        Bprofile bprofile = (Bprofile)em.createNamedQuery("Bprofile.findById")
                .setParameter("id", pid).getSingleResult();
        request.setAttribute("bprofile", bprofile);
                
        return "binformation";
    }

    private String verifyBRegistration(HttpServletRequest request) throws ServletException {
        String username = request.getParameter("username").toLowerCase();
        String username2 = request.getParameter("username2").toLowerCase();
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String email = request.getParameter("email").toLowerCase();
        String email2 = request.getParameter("email2").toLowerCase();
        String message = verifyUser(username, username2, password, password2, email, email2);
        if (!message.equals("valid")) {
            request.setAttribute("flash", message);
            return "bregistration";
        }
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
        EntityManager em = emf.createEntityManager();
        Buser busername = new Buser();
        busername.setUsername(username);
        busername.setPassword(password);
        busername.setEmail(email);
        busername.setStatus("pending");
        em.getTransaction().begin();
        em.persist(busername);
        em.merge(busername);
        em.getTransaction().commit();
        em.close();
        request.setAttribute("flash", "Registration was successful.");
        return "bregistration";

    }
    private String verifyCRegistration(HttpServletRequest request) throws ServletException {
        String username = request.getParameter("username").toLowerCase();
        String username2 = request.getParameter("username2").toLowerCase();
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String email = request.getParameter("email").toLowerCase();
        String email2 = request.getParameter("email2").toLowerCase();
        String message = verifyUser(username, username2, password, password2, email, email2);
        if (!message.equals("valid")) {
            request.setAttribute("flash", message);
            return "cregistration";
        }
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
        EntityManager em = emf.createEntityManager();
        Cuser cusername = new Cuser();
        cusername.setUsername(username);
        cusername.setPassword(password);
        cusername.setPassword(password);
        em.getTransaction().begin();
        em.persist(cusername);
        em.merge(cusername);
        em.getTransaction().commit();
        em.close();
        request.setAttribute("flash", "Registration was successful.");
        return "cregistration";

    }
    private String verifyBLogin(HttpServletRequest request) {
        String username = request.getParameter("username").toLowerCase();
        String password = request.getParameter("password");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
        EntityManager em = emf.createEntityManager();
        try {
            
            Buser buser = (Buser) em.createNamedQuery("Buser.findByUsername")
                    .setParameter("username", username)
                    .getSingleResult();
            if (!buser.getPassword().equals(password)) {
                request.setAttribute("flash", "Incorrect Username/Password combination. ");
                return "blogin";
            }
            if (buser.getStatus().equals("pending")) {
                request.setAttribute("flash","Your membership has not been approved yet.");
                return "blogin";
            }
            request.getSession().setAttribute("buser", buser);
            try {
                Query query = em.createNativeQuery("SELECT id from BPROFILE WHERE buserid=?")
                        .setParameter(1, buser.getId());
                int value = (int)query.getSingleResult();
            } catch (NoResultException nre) {
                request.setAttribute("flash", "Please complete your profile to access the menu.");
                return "bprofile";
            }
            return "bmenu";
        } catch(NoResultException nre){
            request.setAttribute("flash","Incorrect Username/Password combinatioin");
            return "blogin";
        }
        catch (Exception e) {
            request.setAttribute("flash", e);
            return "blogin";
        }

    }
    private String bPost(HttpServletRequest request){
        Buser buser = (Buser) request.getSession().getAttribute("buser");
        if(buser == null){
            return "blogin";
        }
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
        EntityManager em = emf.createEntityManager();
        List<Specials> slist = em.createNamedQuery("Specials.findByBuserid")
                .setParameter("buserid", buser).getResultList();
        request.setAttribute("specials", slist);
        return "bpost";
    }
    private String bMenu(HttpServletRequest request){
        if(verifyBSession(request)){
            return "blogin";
        }
        return "bmenu";
    }
    private String bLogout(HttpServletRequest request) {
        request.getSession().removeAttribute("buser");
        return "index";
    }
    private String bProfile(HttpServletRequest request){
        String bname = request.getParameter("bname");
        String bphone = request.getParameter("bphone");
        String baddress = request.getParameter("baddress");
        String bwebsite = request.getParameter("bwebsite");
        Buser buser = (Buser)request.getSession().getAttribute("buser");
        if(buser == null) return "blogin";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
        EntityManager em = emf.createEntityManager();
        Bprofile bprofile = new Bprofile();
        bprofile.setBname(bname);
        bprofile.setPhone(bphone);
        bprofile.setAddress(baddress);
        bprofile.setWebsite(bwebsite);
        bprofile.setBuserid(buser);
        em.getTransaction().begin();
        em.persist(bprofile);
        em.merge(bprofile);
        em.getTransaction().commit();
        em.close();
        return "bmenu"; 
    }
    private String verifyUser(String username, String username2, String password,
            String password2, String email, String email2) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
        EntityManager em = emf.createEntityManager();
        List<Buser> blist = em.createNamedQuery("Buser.findAll").getResultList();
        List<Cuser> clist = em.createNamedQuery("Cuser.findAll").getResultList();
        List<Administrator> alist = em.createNamedQuery("Administrator.findAll").getResultList();
        for (int i = 0; i < blist.size(); i++) {
            if (blist.get(i).getUsername().equals(username)) {
                return "Username is already in use.";
            }
        }
        for (int i = 0; i < clist.size(); i++) {
            if (clist.get(i).getUsername().equals(username)) {
                return "Username is already in use.";
            }
        }
        for (int i = 0; i < alist.size(); i++) {
            if (alist.get(i).getUsername().equals(username)) {
                return "Username is already in use.";
            }
        }
        if (username.length() < 4 || username.length() > 20) {
            return "Username must be between 4 and 20 characters.";
        }
        if (password.length() < 4 || password.length() > 10) {
            return "Password must be between 4 and 10 characters.";
        }
        if (!username.equals(username2)) {
            return "Username does not match.";
        }
        if (!password.equals(password2)) {
            return "Password does not match.";
        }
        if (!email.equals(email2)) {
            return "E-mail does not match";
        }

        return "valid";
    }
    private String verifyBPost(HttpServletRequest request){
        Buser buser = (Buser)request.getSession().getAttribute("buser");
        if(buser==null)return "blogin";
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        String time2 = request.getParameter("time2");
        String type = request.getParameter("type");
        String price = request.getParameter("price");
        String special = request.getParameter("special");
        SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdftime = new SimpleDateFormat("HH:mm");
        java.util.Date judate = null;
        try {
            judate = sdfdate.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
        EntityManager em = emf.createEntityManager();
        Specials postspecial = new Specials();
        postspecial.setSdate(judate);
        postspecial.setStime(time);
        postspecial.setStime2(time2);
        postspecial.setPrice(price);
        postspecial.setSpecial(special);
        postspecial.setStype(type);
        postspecial.setBuserid(buser);
        em.getTransaction().begin();
        em.persist(postspecial);
        em.merge(postspecial);
        em.getTransaction().commit();
       List<Specials> slist = em.createNamedQuery("Specials.findByBuserid")
                .setParameter("buserid", buser).getResultList();
        request.setAttribute("specials", slist);
        em.close();
        }catch(ConstraintViolationException cve){
            request.setAttribute("flash", cve);
        }
        return "bpost";
    }
    private String delSpecial(HttpServletRequest request){
        if(verifyBSession(request))return "blogin";
        int sid = Integer.parseInt(request.getParameter("delsbutton"));
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
        EntityManager em = emf.createEntityManager();
        Specials special = (Specials)em.createNamedQuery("Specials.findById")
                .setParameter("id",sid)
                .getSingleResult();
        em.getTransaction().begin();
        em.remove(em.merge(special));
        em.getTransaction().commit();
        request.getSession().setAttribute("specials", postedSpecials(request));
        return "bpost";
    }
    private List postedSpecials(HttpServletRequest request) {
        Buser buser = (Buser) request.getSession().getAttribute("buser");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
        EntityManager em = emf.createEntityManager();
        List<Specials> slist = em.createNamedQuery("Specials.findByBuserid")
                .setParameter("buserid", buser).getResultList();
        return slist;
    }
    private boolean verifyBSession(HttpServletRequest request) {
        Buser buser = (Buser) request.getSession().getAttribute("buser");
        if (buser == null) {
            return true;
        } else {
            return false;
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
