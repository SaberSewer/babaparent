package online.cangjie.core.contorller;

import cn.itcast.common.page.Pagination;
import online.cangjie.bean.Product;
import online.cangjie.core.service.product.ProductService;
import online.cangjie.utils.FastDFSUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping(value = "product/")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "product_main")
    public String product_main() {
        return "frame/product_main";
    }

    @RequestMapping(value = "product_left")
    public String product_left() {
        return "frame/product_left";
    }

    @RequestMapping(value = "product_jumpAdd")
    public String product_jump(Model model) {
        model.addAttribute("brand", productService.queryAllBrand());
        model.addAttribute("color", productService.queryAllColor());
        return "product/add";
    }

    @RequestMapping(value = "product_list")
    public String list(Model model, Product product, Integer pageNo) {
        productService.queryListAndPage(product, pageNo);
        if (pageNo == null) {
            pageNo = 1;
        }
        Pagination pagination = productService.queryListAndPage(product, pageNo);
        System.out.println(pagination.getList());
        if (product.getIsCommend() != null) {
            model.addAttribute("display", product.getIsCommend());
        } else {
            model.addAttribute("display", 1);
        }
        model.addAttribute("name", product.getName());
        model.addAttribute("pagination", pagination);
        model.addAttribute("brand", productService.queryAllBrand());
        return "product/list";
    }

    @RequestMapping(value = "product_add")
    public String productAdd(@RequestParam("file") MultipartFile[] multipartFiles, Product product, Model model) throws Exception {
        System.out.println(product);
        String[] path = uploadFile(multipartFiles);
        product.setImgUrl(Arrays.toString(path).replace("[", "").replace("]", ""));
        productService.insertOneProdcut(product);
        return list(model, new Product(), 0);
    }

    @RequestMapping(value = "product_eidt_upload")
    @ResponseBody
    public String product_eidt_upload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        MultipartRequest multipartRequest = (MultipartRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        Set<Map.Entry<String, MultipartFile>> entries = fileMap.entrySet();
        MultipartFile[] multipartFiles = new MultipartFile[entries.size()];
        Iterator<Map.Entry<String, MultipartFile>> iterator = entries.iterator();
        int i = 0;
        while(iterator.hasNext() && i <entries.size()){
            Map.Entry<String, MultipartFile> entry = iterator.next();
            multipartFiles[i] = entry.getValue();
        }
        String[] path = uploadFile(multipartFiles);
        JSONObject json = new JSONObject();
        json.put("url", path[0]);
        json.put("error", 0);
        response.setContentType("application/json;charset=utf-8");
        return json.toString();
    }

    @RequestMapping(value = "product_edit_isShow")
    public String product_edit_isShow(Integer[] ids, Model model, Integer type) throws Exception {
        List<Integer> list = Arrays.asList(ids);
        productService.updateProductIsShow(list, type);
        return list(model, new Product(), 0);
    }

    @RequestMapping(value = "product_delete")
    public String product_delete(Model model, Long[] ids) throws Exception {
        List<Long> list = Arrays.asList(ids);
        productService.deleteProductByIds(list);
        return list(model, new Product(), 0);
    }

    protected String[] uploadFile(MultipartFile[] multipartFiles) throws IOException {
        String[] path = new String[multipartFiles.length];
        for (int i = 0; i < multipartFiles.length; i++) {
            if (multipartFiles[i].getSize() == 0) {
                break;
            }
            path[i] = "http://cangjie.online:8888/" + FastDFSUtil.uploadFileToDFS(multipartFiles[i].getBytes(), multipartFiles[i].getOriginalFilename(), multipartFiles[i].getSize());
        }
        return path;
    }
}
