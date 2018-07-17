package service;

import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UploadService {
    public BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }
        image = new ImageIcon(image).getImage();
        BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment
                .getLocalGraphicsEnvironment();
        try {
            int transparency = Transparency.OPAQUE;
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(image.getWidth(null),
                    image.getHeight(null), transparency);
        } catch (HeadlessException e) {
            // The system does not have a screen
        }
        if (bimage == null) {
            // Create a buffered image using the default color model
            int type = BufferedImage.TYPE_INT_RGB;
            bimage = new BufferedImage(image.getWidth(null),
                    image.getHeight(null), type);
        }
        // Copy image to buffered image
        Graphics g = bimage.createGraphics();
        // Paint the image onto the buffered image
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return bimage;
    }



    //保存裁剪图片
    public  void SaveCutImage (String base64, String address)   //将图片保存到tomcat目录和项目工作目录下
    {
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b = new byte[0];//转码得到图片byte数组
        try {
            b = decoder.decodeBuffer(base64);

        } catch (IOException e) {
            e.printStackTrace();
        }




        try {
            Image imageTookit = Toolkit.getDefaultToolkit().createImage(b);
            BufferedImage bi = toBufferedImage(imageTookit);
            File w2 = new File(address);
            if (!w2.exists()){
                w2.mkdir();
            }
            //File ww=new File(workAddress);
            ImageIO.write(bi, "png", w2);
            //ImageIO.write(bi, "png", ww);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
