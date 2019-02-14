package com.krista.java.test.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

/**
 * QrCodeUtils
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2019/2/14 9:27
 */
public class QrCodeUtils {
    private static final String CHARSET = "utf-8";
    /**
     * 二维码尺寸
     */
    private static final int QRCODE_SIZE = 300;
    /**
     * LOGO宽度
     */
    private static final int LOGO_WIDTH = 60;
    /**
     * LOGO高度
     */
    private static final int LOGO_HEIGHT = 60;

    /**
     * creatRrCode 生成二维码(内嵌logo)
     *
     * @param content  二维码的内容
     * @param logoPath logo路径
     * @return base64格式的字符串
     * @author dw_wangdonghong
     * @date 2019/2/14 9:31
     */
    public static String creatRrCode(String content, String logoPath) throws Exception {
        BufferedImage image = createImage(content, logoPath, false);

        return imageBase64(image);
    }

    /**
     * 图片进行base64
     */
    private static String imageBase64(BufferedImage image) throws IOException {
        // 1、读取文件转换为字节数组
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //转换成png格式的IO流
        ImageIO.write(image, "png", out);
        byte[] bytes = out.toByteArray();

        // 2、将字节数组转为二进制
        BASE64Encoder encoder = new BASE64Encoder();

        return encoder.encodeBuffer(bytes).trim();
    }

    private static BufferedImage createImage(String content, String logoPath, boolean needCompress) throws Exception {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE,
                hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        if (logoPath == null || "".equals(logoPath)) {
            return image;
        }
        // 插入图片
        QrCodeUtils.insertImage(image, logoPath, needCompress);
        return image;
    }

    /**
     * 插入LOGO
     *
     * @param source       二维码图片
     * @param logoPath     LOGO图片地址
     * @param needCompress 是否压缩
     * @throws Exception
     */
    private static void insertImage(BufferedImage source, String logoPath, boolean needCompress) throws Exception {
        File file = new File(logoPath);
        if (!file.exists()) {
            throw new Exception("logo file not found.");
        }
        Image src = ImageIO.read(new File(logoPath));
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        // 压缩LOGO
        if (needCompress) {
            if (width > LOGO_WIDTH) {
                width = LOGO_WIDTH;
            }
            if (height > LOGO_HEIGHT) {
                height = LOGO_HEIGHT;
            }
            Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            // 绘制缩小后的图
            g.drawImage(image, 0, 0, null);
            g.dispose();
            src = image;
        }
        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (QRCODE_SIZE - width) / 2;
        int y = (QRCODE_SIZE - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }

    /**
     * creatRrCode 生成二维码
     *
     * @param contents 二维码的内容
     * @param width    宽
     * @param height   高
     * @return base64格式的字符串
     * @author dw_wangdonghong
     * @date 2019/2/14 9:31
     */
    public static String creatRrCode(String contents, int width, int height) {
        String binary = null;
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(
                    contents, BarcodeFormat.QR_CODE, width, height, hints);
            BufferedImage image = toBufferedImage(bitMatrix);

            // 图片转成base64格式
            binary = imageBase64(image);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }

        return binary;
    }

    /**
     * image流数据处理
     *
     * @author ianly
     */
    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        return image;
    }


    public static void main(String[] args) throws Exception {
        // QrCodeUtils.creatRrCode("https://blog.csdn.net/ianly1233", 200, 200);
        String logoPath = "D:\\mine\\github\\mm\\miniprogram\\doctor\\assert\\images\\logo@2x2@2x.png";
        //  creatRrCode("123",logoPath);
        String binary = QrCodeUtils.creatRrCode("123", 200, 200);
        System.out.println(binary);
    }
}
