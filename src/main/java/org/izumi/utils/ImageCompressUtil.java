package org.izumi.utils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.*;

/**
 * @auther izumi
 * @date 2020-08-06 14:25
 */
public class ImageCompressUtil {

    static Logger logger = LoggerFactory.getLogger(ImageCompressUtil.class);

    public static File compressPictureByQality(File file,float qality) throws IOException {

        BufferedImage src = null;
        FileOutputStream out = null;
        ImageWriter imgWrier;
        ImageWriteParam imgWriteParams;
        logger.debug("开始设定压缩图片参数");
        // 指定写图片的方式为 jpg
        imgWrier = ImageIO.getImageWritersByFormatName("jpg").next();
        imgWriteParams = new javax.imageio.plugins.jpeg.JPEGImageWriteParam(
                null);
        // 要使用压缩，必须指定压缩方式为MODE_EXPLICIT
        imgWriteParams.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        // 这里指定压缩的程度，参数qality是取值0~1范围内，
        imgWriteParams.setCompressionQuality(qality);
        imgWriteParams.setProgressiveMode(ImageWriteParam.MODE_DISABLED);
        ColorModel colorModel =ImageIO.read(file).getColorModel();// ColorModel.getRGBdefault();
        // 指定压缩时使用的色彩模式
//        imgWriteParams.setDestinationType(new javax.imageio.ImageTypeSpecifier(
//                colorModel, colorModel.createCompatibleSampleModel(16, 16)));
        imgWriteParams.setDestinationType(new javax.imageio.ImageTypeSpecifier(
                colorModel, colorModel.createCompatibleSampleModel(16, 16)));

        logger.debug("结束设定压缩图片参数");
        if (!file.exists()) {
            logger.error("Not Found Img File,文件不存在");
            throw new FileNotFoundException("Not Found Img File,文件不存在");
        } else {
            logger.debug("图片转换前大小"+file.length()+"字节");
            src = ImageIO.read(file);
            out = new FileOutputStream(file);

            imgWrier.reset();
            // 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何
            // OutputStream构造
            imgWrier.setOutput(ImageIO.createImageOutputStream(out));
            // 调用write方法，就可以向输入流写图片
            imgWrier.write(null, new IIOImage(src, null, null),
                    imgWriteParams);
            out.flush();
            out.close();
            logger.debug("图片转换后大小"+file.length()+"字节");
            return file;
        }
    }

    /**
     * 指定图片宽度和高度和压缩比例对图片进行压缩
     *
     * @param srcfile 源图片文件
     * @param imgdist 目标图片地址
     */
    public static void reduceImg(File srcfile, String imgdist) {
        try {
            // 检查图片文件是否存在
            if (!srcfile.exists()) {
                System.out.println("文件不存在");
            }
            int[] results = getImgWidthHeight(srcfile);

            int widthdist = results[0];
            int heightdist = results[1];
            // 开始读取文件并进行压缩
            Image src = ImageIO.read(srcfile);

            // 构造一个类型为预定义图像类型之一的 BufferedImage
            BufferedImage tag = new BufferedImage( widthdist,  heightdist, BufferedImage.TYPE_INT_RGB);

            // 这边是压缩的模式设置
            tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist, Image.SCALE_SMOOTH), 0, 0, null);

            //创建文件输出流
            FileOutputStream out = new FileOutputStream(imgdist);
            //将图片按JPEG压缩，保存到out中
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(tag);
            //关闭文件输出流
            out.close();
        } catch (Exception ef) {
            ef.printStackTrace();
        }
    }

    /**
     * 获取图片宽度和高度
     *
     * @param file 图片路径
     * @return 返回图片的宽度
     */
    public static int[] getImgWidthHeight(File file) {
        InputStream is = null;
        BufferedImage src = null;
        int result[] = { 0, 0 };
        try {
            // 获得文件输入流
            is = new FileInputStream(file);
            // 从流里将图片写入缓冲图片区
            src = ImageIO.read(is);
            // 得到源图片宽
            result[0] =src.getWidth(null);
            // 得到源图片高
            result[1] =src.getHeight(null);
            is.close();  //关闭输入流
        } catch (Exception ef) {
            ef.printStackTrace();
        }

        return result;
    }
}
