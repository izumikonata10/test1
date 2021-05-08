import org.izumi.utils.ImageCompressUtil;

import java.io.File;
import java.text.DecimalFormat;
import java.util.UUID;

/**
 * @auther izumi
 * @date 2020-08-22 09:28
 */
public class test {
    public static void main(String[] args) throws Exception{
//        double a= 123.123123123;
//        DecimalFormat df = new DecimalFormat("#.00");
//        System.out.println(df.format(a));

        float a = (float) 0.5;
        File file = new File("D:\\User\\Java\\Desktop\\年会表彰照片");
        File[] files = file.listFiles();
        for(File f : files){
//            ImageCompressUtil.compressPictureByQality(f,a);
            ImageCompressUtil.reduceImg(f,f.getPath());
        }
    }

}
