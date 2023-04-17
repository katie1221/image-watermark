package com.example.imagewatermark.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 图片替换背景色
 * @author qzz
 * @date 2023/4/14
 */
public class ImageChange {
    public static void main(String[] args) throws IOException {
        // 读取原图片信息 得到文件（本地图片）
        File srcImgFile = new File("F:/image/s.png");
        BufferedImage  bufferedImage = ImageIO.read(srcImgFile);
        //获取图片的宽
        int srcImgWidth = bufferedImage.getWidth(null);
        //获取图片的高
        int srcImgHeight = bufferedImage.getHeight(null);

        changeImgBackgroundColor(bufferedImage,srcImgWidth,srcImgHeight);

        //待存储的地址
        String tarImgPath="F:/image/t.png";
        // 输出图片
        FileOutputStream outImgStream = new FileOutputStream(tarImgPath);
        ImageIO.write(bufferedImage, "png", outImgStream);
        outImgStream.flush();
        outImgStream.close();

    }

    /**
     * 替换图片背景色(图片背景为纯色背景)
     * @param bufferedImage
     * @param imageWidth
     * @param imageHeight
     */
    public static void changeImgBackgroundColor(BufferedImage bufferedImage, int imageWidth, int imageHeight){
        for (int i = 0; i < imageWidth; i++) {
            for (int j = 0; j < imageHeight; j++) {
                int pixel = bufferedImage.getRGB(i, j);
                int rgbR = (pixel & 0xff0000) >> 16;
                int rgbG = (pixel & 0xff00) >> 8;
                int rgbB = (pixel & 0xff);
                System.out.println("rgbR:" + rgbR + ",rgbG:" + rgbG+ ",rgbB" + rgbB);
                //240这个值，比电子章的背景色的RGB值稍微小一点。可以上下浮动多试试这个值，调一调哪个阈值最好
                if(rgbR > 76 && rgbG > 127 && rgbB > 48){
                //将图片中大于240的色值，设为白色
                    bufferedImage.setRGB(i, j, new Color(255,255,255).getRGB());
                }

            }

        }
    }
}
