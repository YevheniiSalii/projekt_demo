package controler;

import model.Privilege;
import util.Constans;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "LoginServlet", urlPatterns = {"/logowanie"})
public class LoginServlet extends HttpServlet {

    public static final String ADMIN_PASSWORD = "admin";
    public static final String  ADMIN_NICKNAME = "admin";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/logowanie.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (isAdmin(req)){
            setPrivilege(req);
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }else {
            resp.setContentType(Constans.CONTENT_TYPE);
            resp.getWriter().println("<h1 style=\"background-color:red;\">BLAD LOGOWANIA SPROBUJ PONOWIE</h1>");
            req.getRequestDispatcher("/logowanie.jsp").include(req, resp);

        }

    }

    private void setPrivilege(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.setAttribute(Constans.PRIVILEGE, Privilege.ADMIN);
    }

    private  boolean isAdmin(HttpServletRequest req){
        Optional<String> nickname = Optional.ofNullable(req.getParameter(Constans.NICKNAME));
        Optional<String> password = Optional.ofNullable(req.getParameter(Constans.PASSWORD));
        Boolean correctNickname = nickname.map(ADMIN_NICKNAME::equals).orElse(false);
        Boolean correctPassword = password.map(ADMIN_PASSWORD::equals).orElse(false);
       return   correctNickname&&correctPassword;
    }

}
