package cu.uci.cegel.security.config;

import cu.uci.cegel.security.utils.IPAddressValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {
    @Autowired
    private IPAddressValidator ipAddressValidator;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        final HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Origin, Authorization, X-Requested-With, Content-Type");
        response.setHeader("Access-Control-Expose-Headers", "X-sigipApp-badrequest, X-sigipApp-success, X-sigipApp-info, X-sigipApp-error, Content-Disposition, X-Content-Type-Options, X-FRAME-OPTIONS");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("X-Frame-Options", "DENY");

        if (ipAddressValidator.validate(((HttpServletRequest) req).getHeader("host").split(":")[0])) {
            response.setHeader("Access-Control-Allow-Origin", (((HttpServletRequest) req).getHeader("origin")));
        }
        if ("OPTIONS".equalsIgnoreCase(((HttpServletRequest) req).getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            if (((HttpServletRequest) req).getRequestURI().contains("upload")) {
                response.setHeader("Access-Control-Allow-Headers", "Origin, Authorization, X-Requested-With, Content-Type, multipart/form-data, X-Content-Type-Options,X-FRAME-OPTIONS");
            }
            chain.doFilter(req, res);
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig config) {
    }
}
