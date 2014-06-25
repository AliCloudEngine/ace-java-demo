/**
 * 
 */
package ace.demo.websocket;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * @author lsk
 */
public class WebSocketServlet extends HttpServlet {

    private static final long serialVersionUID = 2881868780414955111L;

    /**
     * get the posted picture and send to every websocket session.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        Part part = request.getPart("picture");
        InputStream in = part.getInputStream();
        int b = in.read();
        while (b > -1) {
            
        }
    }

}
