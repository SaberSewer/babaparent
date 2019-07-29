package online.cangjie.utils;


import org.apache.commons.io.FilenameUtils;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

public class FastDFSUtil {
    public static String uploadFileToDFS(byte []byets, String fileName, long size){
        String suffix = FilenameUtils.getExtension(fileName);
        String path = null;
        try {
            ClientGlobal.init("fdfs_client.properties");
            System.out.println(ClientGlobal.configInfo());
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageClient1 storageClient1 = new StorageClient1(trackerServer, null);
            NameValuePair[] nameValuePairs = new NameValuePair[3];
            nameValuePairs[0] = new NameValuePair("fileName", fileName);
            nameValuePairs[1] = new NameValuePair("fileExt", suffix);
            nameValuePairs[2] = new NameValuePair("fileSize", String.valueOf(size));
            path = storageClient1.upload_file1(byets, suffix, nameValuePairs);
            System.out.println(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  path;
    }
}
