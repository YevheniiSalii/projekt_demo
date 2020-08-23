package controler;

import model.Privilege;
import sun.security.ssl.SSLLogger;
import util.Constans;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

@WebFilter(filterName = "ProductFiltr", servletNames = {"ProductServlet"})
public class ProductFiltr implements Filter {


    private static final Logger LOGGER =Logger.getLogger(ProductFiltr.class.getSimpleName()) ;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(hasPrivilege((HttpServletRequest) servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            LOGGER.warning("SOMEONE TRIED TO GET ACCESS TO SECRET PAGE");
            servletResponse.setContentType(Constans.CONTENT_TYPE);
            HttpServletResponse response;
            PrintWriter writer = servletResponse.getWriter();
            writer.println("<h1 style=\"background-color:red;\">BRAK UPRAWIEN</h1>");
            servletRequest.getRequestDispatcher("/index.jsp").include(servletRequest, servletResponse);
        }
        }

    private boolean hasPrivilege(HttpServletRequest servletRequest) {
        HttpServletRequest httpServletRequest = servletRequest;
        HttpSession session = httpServletRequest.getSession();
        Object attribute = session.getAttribute(Constans.PRIVILEGE);


        return Privilege.ADMIN.equals(Privilege.ADMIN);

    }
}
