package pw.lumm.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.web.bind.annotation.RequestParam;

public class FastDFSClient {

	private TrackerClient trackerClient = null;
	private TrackerServer trackerServer = null;
	private StorageServer storageServer = null;
	private StorageClient1 storageClient = null;
	
	public FastDFSClient(String conf) throws Exception {
		if (conf.contains("classpath:")) {
			conf = conf.replace("classpath:", this.getClass().getResource("/").getPath());
		}
		ClientGlobal.init(conf);
		trackerClient = new TrackerClient();
		trackerServer = trackerClient.getConnection();
		storageServer = null;
		storageClient = new StorageClient1(trackerServer, storageServer);
	}
	public String uploadFile(String fileName, String extName, NameValuePair[] metas) throws Exception {
		String result = storageClient.upload_file1(fileName, extName, metas);
		return result;
	}
	
	public String uploadFile(String fileName) throws Exception {
		return uploadFile(fileName, null, null);
	}
	
	public String uploadFile(String fileName, String extName) throws Exception {
		return uploadFile(fileName, extName, null);
	}

	public String uploadFile(byte[] fileContent, String extName, NameValuePair[] metas) throws Exception {
		
		String result = storageClient.upload_file1(fileContent, extName, metas);
		return result;
	}
	
	public String uploadFile(byte[] fileContent) throws Exception {
		return uploadFile(fileContent, null, null);
	}
	
	public String uploadFile(byte[] fileContent, String extName) throws Exception {
		return uploadFile(fileContent, extName, null);
	}
	  /** 
     * 删除文件 
     * @param group 组名 如：group1 
     * @param storagePath 不带组名的路径名称 如：M00/00/00/wKgRsVjtwpSAXGwkAAAweEAzRjw471.jpg 
     * @return -1失败,0成功 
     */  
    public Integer delete_file(String group ,String storagePath){  
        int result=-1;  
        try {  
            result = storageClient.delete_file(group, storagePath);  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
         return  result;  
    }  
    /** 
     *  
     * @param storagePath  文件的全部路径 如：group1/M00/00/00/wKgRsVjtwpSAXGwkAAAweEAzRjw471.jpg 
     * @return -1失败,0成功 
     * @throws IOException 
     * @throws Exception 
     */  
    public Integer delete_file(String storagePath){  
        int result=-1;  
        try {  
            result = storageClient.delete_file1(storagePath);  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return  result;  
    }
	public void downFile(String filename) {
		try {
			int i = filename.indexOf("/",21);
			String group = filename.substring(i+1,i+7);
			String file = filename.substring(i+7);
			int j = filename.lastIndexOf(".");
			String type = filename.substring(j);
			TrackerClient tracker = new TrackerClient();
			byte[] b = storageClient.download_file(group, file);
			System.out.println(b);
			IOUtils.write(b, new FileOutputStream("D:/"+UUID.randomUUID().toString()+type));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
