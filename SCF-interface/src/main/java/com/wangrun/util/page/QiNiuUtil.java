/*package wr.com.utils;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.common.Zone;
import com.qiniu.storage.Configuration;
import com.qiniu.util.Auth;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
  

public class QiNiuUtil {
    //设置好账号的ACCESS_KEY和SECRET_KEY
    private static String ACCESS_KEY = "c7QgborwyYNobzTyb9JUWaLumdVq-qzPTFq9S7Ov";
    private static String SECRET_KEY = "hZv_OktenbLGw3v-FqKY60HhMlvYUKvGVj7k2nDt";
    //要上传的空间
    private static String bucketname = "chenshizhong";
    //上传到七牛后保存的文件名
    //上传文件的路径
//    String URL = "http://oh4yq40c2.bkt.clouddn.com/2.jpg";
    
    //密钥配置
    private static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

    ///////////////////////指定上传的Zone的信息//////////////////
    //第一种方式: 指定具体的要上传的zone
    //注：该具体指定的方式和以下自动识别的方式选择其一即可
    //要上传的空间(bucket)的存储区域为华东时
    // Zone z = Zone.zone0();
    //要上传的空间(bucket)的存储区域为华北时
    // Zone z = Zone.zone1();
    //要上传的空间(bucket)的存储区域为华南时
    // Zone z = Zone.zone2();

    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    private static String getUpToken() {
        return auth.uploadToken(bucketname);
    }
    
  //file:需要上传的文件，key：上传到七牛后保存的文件名
    public static String upload(File file,String fileName) throws IOException {
        UploadManager uploadManager = new UploadManager(new Configuration(Zone.autoZone()));
        try {
            //调用put方法上传
        	 Response res = uploadManager.put(file, fileName, getUpToken());
            //打印返回的信息
            System.out.println(res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }       
        }
        String URL = "http://oh4yq40c2.bkt.clouddn.com/"+fileName;
        String downloadRUL = auth.privateDownloadUrl(URL,3600);
        return downloadRUL;
    }
    
}

*/