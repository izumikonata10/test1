package org.izumi.controller;

import org.izumi.dao.ZhaoMapper;
import org.izumi.model.Zhao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author izumi
 * @date 2020-07-30 08:15
 */
@Controller
public class IndexController {

//    @Autowired(required = false)
//    ZhaoMapper zhaoMapper;

    /**上传地址*/
    @Value("${file.upload.path}")
    //D://User/Java/springboot/src/main/resources/static/img/
    private String path;

    @RequestMapping("/home")
    public String home() {
//        Zhao z=new Zhao();
//        z.setzName("zz");
//        z.setzId(22);
//        z.setzMoney(22);
//        zhaoMapper.insert(z);
        return "index";
    }

    @RequestMapping("/imgRAR")
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

}
