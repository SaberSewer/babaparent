package online.cangjie.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class RequestUtils {
    public static  String getCSESSIONID(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        System.out.println(cookies.length);
        if(null != cookies && cookies.length > 0){
            for (Cookie cookie : cookies){
                if("CJSESSIONID".equals(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }
        String csessionId = UUID.randomUUID().toString().replace("-", "");
        Cookie cookie = new Cookie("CJSESSIONID", csessionId);
        cookie.setMaxAge(-1);
        cookie.setPath("/");
        response.addCookie(cookie);
        return  csessionId;
    }
}
