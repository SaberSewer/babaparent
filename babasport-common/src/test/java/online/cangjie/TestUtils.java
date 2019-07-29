package online.cangjie;

import online.cangjie.utils.FastDFSUtil;
import org.junit.Test;

import java.io.*;

public class TestUtils {
    @Test
    public void testUtils(){
        try(InputStream in = new FileInputStream(new File("/home/cangjie/Desktop/bilibili.png"));) {
            byte[] bytes = new byte[in.available()];
            while(in.read() != -1){
                in.read(bytes);
            }
            String path = FastDFSUtil.uploadFileToDFS(bytes,"bilibili.png", bytes.length);
            System.out.println(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
