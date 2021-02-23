package org.izumi.controller;

import groovy.util.logging.Slf4j;
import org.izumi.dao.ZhaoMapper;
import org.izumi.model.ReturnCitySN;
import org.izumi.model.Zhao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author izumi
 * @date 2020-07-30 08:15
 */
@Controller
@Slf4j
public class IndexController {

//    @Autowired(required = false)
//    ZhaoMapper zhaoMapper;
    private  final  static Logger logger = LoggerFactory.getLogger(IndexController.class);

    /**上传地址*/
    @Value("${file.upload.path}")
    //D://User/Java/springboot/src/main/resources/static/img/
    private String path;

    @RequestMapping("/home")
    public String home(HttpServletRequest request) {
        String ip = null;
        try {
            ip = request.getHeader("x-forwarded-for");
            if (request == null) {
                return "unknown";
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("X-Forwarded-For");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("X-Real-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
                if (ip.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ip = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ip != null && ip.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ip.indexOf(",") > 0) {
                    ip = ip.substring(0, ip.indexOf(","));
                }
            }
        } catch (Exception e) {
            ip="";
        }
        // ipAddress = this.getRequest().getRemoteAddr();

//        System.out.println(request.getRemoteHost());
//        System.out.println(ip);
//        System.out.println("----------------------------------------------------------------------------");
//        System.out.println(request.getHeader("Origin"));
//        System.out.println("----------------------------------------------------------------------------");
//        System.out.println(request.getHeader("Host"));

//        Zhao z=new Zhao();
//        z.setzName("zz");
//        z.setzId(22);
//        z.setzMoney(22);
//        zhaoMapper.insert(z);
        return "index";
    }
    @RequestMapping(value = "/ip",method = RequestMethod.POST)
    @ResponseBody
    public String getIP(@RequestBody ReturnCitySN returnCitySN){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info(df.format(new Date())+returnCitySN);
        System.out.println(df.format(new Date())+returnCitySN);
        return "success";
    }

    @RequestMapping(value = "/imgRAR",method = RequestMethod.POST)
    public String imgRAR(float qality,@RequestParam("file") MultipartFile file) {
        File filePath = new File(path);
        File f = new File(path+"1.jpg");
        f.delete();
        if(!filePath.exists() && !filePath.isDirectory()){
            System.out.println("目录不存在，创建目录" + filePath);
            filePath.mkdir();
        }
        //获取原始文件名称（包括格式）
        String originalFileName = file.getOriginalFilename();
        System.out.println("原始文件名称" + originalFileName);

        //获取文件类型，以最后一个‘.’为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        System.out.println("文件类型" + type);

        //获取文件名称（不包含格式）
        String name = originalFileName.substring(0,originalFileName.lastIndexOf("."));

        //设置文件新名称：当前事件+文件名称（不包含格式）
//        Date d = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//        String date = sdf.format(d);
//        String fileName = date + name + "." + type;
//        System.out.println("新文件名称" +fileName);
        String fileName = "1.jpg";

        //在指定路径下创建文件
        File targetFile = new File(path,fileName);

        //将文件保存到服务器指定位置
        try{
            file.transferTo(targetFile);
            System.out.println("上传成功");
        }catch (IOException e){
            System.out.println("上传失败");
            e.printStackTrace();
        }
        return "index";
    }

    @RequestMapping("/2048")
    public String game2048(){
        return "game/2048/index";
    }

    @RequestMapping("/mine")
    public String gamemine(){
        return "game/mine/mine";
    }

}
