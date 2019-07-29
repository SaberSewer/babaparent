package online.cangjie.core.controller;

import com.alibaba.fastjson.JSONObject;
import online.cangjie.core.service.cms.CmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/product/")
public class ProductController {
    @Autowired
    private CmsService cmsService;

    @RequestMapping(value = "detail")
    public String toProduct(Long id, Model model){
        model.addAttribute("product", cmsService.queryProductByProductId(id));
        model.addAttribute("sku" ,cmsService.querySkuByProductId(id));
        return "product";
    }

    @RequestMapping(value = "getSize")
    @ResponseBody
    public Object getSize(Long colorId, Long productId){
       String json = JSONObject.toJSONString(cmsService.querySkuSizeByProductIdAndColorId(productId, colorId));

        return json;
    }

    @RequestMapping(value = "getProductPrice")
    @ResponseBody
    public String getProductPrice(Long colorId, Long productId, String size){
        Map map = new HashMap<>();
        map.put("price", cmsService.querySkuPriceByProductIdAndColorIdAndSize(productId, colorId, size));
        return JSONObject.toJSONString(map);
    }
}
