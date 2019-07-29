package online.cangjie.core.message;

import online.cangjie.core.service.solr.SearchService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.List;

public class CustomMessageListener implements MessageListener {
    @Autowired
    private SearchService searchService;

    @Override
    public void onMessage(Message message) {
        try {
            List<Integer> list = (List<Integer>) message.getObjectProperty("ids");
            Integer type = (Integer) message.getObjectProperty("type");
            searchService.insertProductToSolr(list, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
