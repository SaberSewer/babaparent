package online.cangjie.core.service.staticpage;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.Map;

public class StaticPageServiceImpl implements StaticPageService, ServletContextAware {
    private Configuration configuration;
    private ServletContext servletContext;

    public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer) {
        this.configuration = freeMarkerConfigurer.getConfiguration();
    }

    public void productStaticPage(Map<String, Object> map, String id){
        String path = getPath("/html/product/" + id + ".html");
        File file = new File(path);
        File parentFile = file.getParentFile();
        if(parentFile.exists()){
            parentFile.mkdirs();
        }
        try(Writer out = new OutputStreamWriter(new FileOutputStream(path), "UTF-8")) {
            Template template = configuration.getTemplate("product.html");
            template.process(map, out);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }


    private String getPath(String name){
        return servletContext.getRealPath(name);
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
