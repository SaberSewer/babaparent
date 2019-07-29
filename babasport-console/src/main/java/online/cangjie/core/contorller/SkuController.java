package online.cangjie.core.contorller;

import cn.itcast.common.page.Pagination;
import online.cangjie.bean.Sku;
import online.cangjie.core.service.product.SkuService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "sku/")
public class SkuController {
    @Autowired
    private SkuService skuService;

    @RequestMapping(value = "sku_jumpSkuList")
    public String jumpSku(Model model, Sku sku, Integer pageNo){
        Pagination pagination = skuService.query(sku, pageNo);
        System.out.println(pagination.getList());
        model.addAttribute("pagination", pagination);
        return "sku/list";
    }

    @RequestMapping(value = "sku_update")
    @ResponseBody
    public String skuUpdate(Sku sku){
        System.out.println(sku);
        JSONObject json = new JSONObject();
       if(1 >= skuService.updateMPSUD(sku)){
           json.put("msg", 0);
       } else {
           json.put("msg", 1);
       }
        return json.toString();
    }
}
