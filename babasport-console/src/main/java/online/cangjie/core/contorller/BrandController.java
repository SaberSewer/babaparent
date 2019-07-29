package online.cangjie.core.contorller;

import cn.itcast.common.page.Pagination;
import com.sun.istack.internal.NotNull;
import online.cangjie.bean.Brand;
import online.cangjie.core.service.product.BrandService;
import online.cangjie.utils.FastDFSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@Controller
@RequestMapping(value = "brand/")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @RequestMapping(value = "brand_list")
    public String brandList(Model model, Brand brand, Integer pageNo) {
        System.out.println(brand);
        Pagination pagination = brandService.query(brand, pageNo);
        if(brand.getIsDisplay() != null){
            model.addAttribute("display", brand.getIsDisplay());
        } else {
            model.addAttribute("display", 1);
        }
        model.addAttribute("name", brand.getName());
        model.addAttribute("pagination", pagination);
        return "brand/list";
    }

    @RequestMapping(value = "jumpAdd")
    public String jumpAdd() {
        return "brand/add";
    }

    @RequestMapping(value = "toEdit")
    public String toEidt(Model model, Long id) {
        Brand brand = brandService.queryOne(id);
        model.addAttribute("brand", brand);
        return "brand/edit";
    }

    @RequestMapping(value = "add")
    public String add(@RequestParam("file") MultipartFile multipartFile, Model model, Brand brand) {
        try {
            String path = FastDFSUtil.uploadFileToDFS(multipartFile.getBytes(), multipartFile.getOriginalFilename(), multipartFile.getSize());
            System.out.println(path);
            String prefix = "http://cangjie.online:8888/";
            brand.setImg_url(prefix + path);
            brandService.insertOne(brand);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return brandList(model, new Brand(), 0);
    }

    @RequestMapping(value = "update")
    public String update(@RequestParam("pic") MultipartFile multipartFile, Model model, Brand brand){
        if(multipartFile.getSize() > 0){
            try {
                String path = FastDFSUtil.uploadFileToDFS(multipartFile.getBytes(), multipartFile.getOriginalFilename(), multipartFile.getSize());
                System.out.println(path);
                String prefix = "http://cangjie.online:8888/";
                brand.setImg_url(prefix + path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        brandService.updateOne(brand);
        return brandList(model, new Brand(), 0);
    }

    @RequestMapping(value = "delete")
    public String delete(Long[] ids, Model model){
        brandService.delete(Arrays.asList(ids));
        return brandList(model, new Brand(), 0);
    }
}
