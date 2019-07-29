package online.cangjie.core.controller;


import online.cangjie.core.service.solr.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "list")
    public String search(Model model, String keyWord, Integer pageNo, Long brandId, String price) {
        Map<String, Object> map = searchService.searchProduct(keyWord, pageNo, brandId, price);
        model.addAttribute("productList", map.get("pagination"));
        model.addAttribute("brands", searchService.queryBrandsFromRedis());
        model.addAttribute("keyWord", keyWord);
        model.addAttribute("brandId", brandId);
        return "search";
    }
}
