package view;

import java.util.Map;

import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSession;

public class SessionUtil {
    public SessionUtil() {
        super();
    }
    
    
    //Save Data in Session
    public static void storeOnSession(String key, Object value) {
        HttpServletRequest request =
            (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession(false);
        session.setAttribute(key, value);

    }
    
    //Read Data from Session
    public static Object getFromSession(String key) {
        Map sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        return sessionMap.get(key);
    }

    //Remove Data from Session
    public static void removeFromSession(String key) {
        HttpServletRequest request =
            (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession(false);
        session.removeAttribute(key);
    }


}