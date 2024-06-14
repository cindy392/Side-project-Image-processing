import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Contrast {

  private BufferedImage img;

  public Contrast(BufferedImage img) {
    this.img = img;
  }

  public BufferedImage contrast(BufferedImage img) {
    int width = img.getWidth();
    int height = img.getHeight();

    BufferedImage contrastImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

    // 設定對比拉開的閾值，這裡假設為20和230
    int minThreshold = 20;
    int maxThreshold = 230;

    // 對每個像素進行對比拉開
    for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
            int color = img.getRGB(i, j);//像素點的整數表示形式的顏色值
            // 將數字轉換成RGB
            int r = (color >> 16) & 0xff;// 提取出的紅色分量值
            int g = (color >> 8) & 0xff;
            int b = color & 0xff;

            // 對每個通道進行對比拉開
            r = (r - minThreshold) * 255 / (maxThreshold - minThreshold);
            g = (g - minThreshold) * 255 / (maxThreshold - minThreshold);
            b = (b - minThreshold) * 255 / (maxThreshold - minThreshold);

            // 確保像素值在0和255之間
            //讓數值不會超過灰階值
            int red = Math.max(0, Math.min(255, r));
            int green = Math.max(0, Math.min(255, g));
            int blue = Math.max(0, Math.min(255, b));

            int Pixel = new Color(red, green, blue).getRGB();
            contrastImage.setRGB(i, j, Pixel);
        }
    }
    return contrastImage;
  }

  public BufferedImage toImage() {
    return contrast(img);
  }
}