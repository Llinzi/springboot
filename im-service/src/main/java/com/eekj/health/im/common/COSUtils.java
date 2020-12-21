package com.eekj.health.im.common;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.GetObjectRequest;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;

import java.io.File;
import java.util.Random;

/**
 * 腾讯对象存储工具类
 */
public class COSUtils {

    private static String bucketName = "linzi-1997-1301403648";

    private static String secretId = "1";

    private static String secretKey = "1";

    /**
     * https://linzi-1997-1301403648.cos-website.ap-chengdu.myqcloud.com/images/banner/1.jpg
     *  访问路径
     */
    private static String accessPath = "https://linzi-1997-1301403648.cos-website.ap-chengdu.myqcloud.com/";

    //初始化用户身份信息
    private static COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);

    // 2 设置bucket的区域, COS地域
    private static ClientConfig clientConfig = new ClientConfig(new Region("ap-chengdu"));

    /**
     * 上传文件
     * @param localFile 本地文件
     * @param pathPrefix 腾讯云对象存储的路径
     * @return
     * @throws CosClientException
     * @throws CosServiceException
     */
    public static String uploadFile(File localFile,String pathPrefix) throws CosClientException, CosServiceException {
        //生成cos客服端
        COSClient cosClient = new COSClient(cred,clientConfig);
        //文件名称
        String fileName = "";
        try {
            fileName = localFile.getName();
            String substring = fileName.substring(fileName.lastIndexOf("."));
            Random random = new Random();
            // 指定要上传到 COS 上的路径
            fileName = pathPrefix + random.nextInt(10000) + System.currentTimeMillis() + substring;
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, localFile);
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            return accessPath + fileName;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭客户端
            cosClient.shutdown();
        }
        return fileName;
    }

    /**
     * 下载文件
     * filePath : 文件路径
     * fileName : 文件名称
     */
    public static void downFile(String filePath,String fileName){
        //生成客户端
        COSClient cosClient = new COSClient(cred,clientConfig);
        //要下载的文件路径和名称
        String key = filePath + "/" + fileName;
        //指定要下载的文件所在存储路径
        File downLocalFile = new File("C:\\");
        //指定要下载的文件所在的 bucket 和 对象键
        GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName,key);
        ObjectMetadata objectMetadata = cosClient.getObject(getObjectRequest,downLocalFile);
    }

    /**
     *
     * @param key 删除的路径及名称
     * @throws CosClientException
     * @throws CosServiceException
     */
    public static void deleteFile(String key) throws CosClientException, CosServiceException{
        //添加客户端
        COSClient cosClient = new COSClient(cred,clientConfig);
        //指定要删除的 存储桶 和 路径及名称
        cosClient.deleteObject(bucketName,key);
        //关闭客户端
        cosClient.shutdown();
    }

}
