package online.cangjie.core.controller;

import online.cangjie.bean.BuyerCart;
import online.cangjie.core.service.cob.SessionProvider;
import online.cangjie.core.service.solr.BuyerCartService;
import online.cangjie.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class BuyerCartController {
    @Autowired
    private SessionProvider sessionProvider;
    @Autowired
    private BuyerCartService buyerCartService;

    @RequestMapping(value = "/showCart")
    public String showCart(HttpServletRequest request, HttpServletResponse response, Model model, Long[] skuIds, Integer[] counts){
        String csessionid = RequestUtils.getCSESSIONID(request, response);
        String userName = sessionProvider.getAttibuteForUserName(csessionid);
        BuyerCart buyerCart = buyerCartService.queryProdctBySkuId(skuIds, counts);
        model.addAttribute("buyerCart", buyerCart);
        if(null != userName){
            return "cart";
        }
        //未登录用户的购物车查询

        return "cart";
    }

    @RequestMapping(value = "addCart")
    public String addCart(Long skuId, Integer count){

        return null;
    }
}
