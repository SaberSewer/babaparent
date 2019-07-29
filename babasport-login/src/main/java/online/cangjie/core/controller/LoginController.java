package online.cangjie.core.controller;

import com.alibaba.fastjson.JSONObject;
import online.cangjie.bean.Buyer;
import online.cangjie.core.service.cob.BuyerService;
import online.cangjie.core.service.cob.SessionProvider;
import online.cangjie.utils.RequestUtils;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;


@Controller
public class LoginController {
    @Autowired
    private BuyerService buyerService;
    @Autowired
    private SessionProvider sessionProvider;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Buyer buyer, String returnUrl, HttpServletRequest request, HttpServletResponse response, Model model){
        if(null != buyer.getUsername() && null != buyer.getPassword()){
            buyer.setPassword(encodePassword(buyer.getPassword()));
            Buyer b = buyerService.queryUserByUsernameAndId(buyer);
            if(b == null){
                model.addAttribute("error", "用户名或密码错误");
                return login();
            }
            sessionProvider.setAttributeForUserName(RequestUtils.getCSESSIONID(request, response), buyer.getUsername());
            return "redirect:" + returnUrl;
        }
        model.addAttribute("error", "用户名或密码不能为空");
        return login();
    }
    //4.2以上新特性
    @CrossOrigin(origins = "http://localhost:8084", methods = {RequestMethod.GET, RequestMethod.POST}, allowCredentials = "true", allowedHeaders = "-requested-with,content-type")
    @ResponseBody
    @RequestMapping(value = "/isLogin")
    public String isLogin(HttpServletRequest request, HttpServletResponse response){
        String csessionid = RequestUtils.getCSESSIONID(request, response);
        System.out.println("CJSESSION" + csessionid);
/*        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");*/
        Map<String, Object> map = new HashMap<>();
        if(null == csessionid){
            map.put("error", 1);
            return JSONObject.toJSONString(map);
        }
        String username = sessionProvider.getAttibuteForUserName(csessionid);
        System.out.println(username);
        if(null == username){
            map.put("error", -1);
            return JSONObject.toJSONString(map);
        }
        map.put("error", 0);
        map.put("name", username);
        return JSONObject.toJSONString(map);
    }

    public String encodePassword(String password){
        char[] chars = null;
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            byte[] digest = instance.digest(password.getBytes());
            chars = Hex.encodeHex(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return new String(chars);
    }

}
