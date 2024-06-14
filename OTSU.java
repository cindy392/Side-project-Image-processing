import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Arrays;

public class OTSU {

  private BufferedImage img;

  public OTSU(BufferedImage img) {
    this.img = img;
  }

  public BufferedImage otsu(BufferedImage img) {
    int width = img.getWidth();
    int height = img.getHeight();

    BufferedImage otsuImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
    int sum = 0;

    for (int i = 0; i < width; i++) {// 原本陣列
      for (int j = 0; j < height; j++) {
        int color = img.getRGB(i, j) & 0xFF; // 直接取灰階值
        sum += color;
      }
    }
    int threshold = sum / (width * height);//取平均

    for (int x = 0; x < width; x++) {// 原本陣列
      for (int y = 0; y < height; y++) {
        int color = img.getRGB(x, y) & 0xFF; // 直接取灰階值
        int after_color = 0;
        if (color >= threshold) {
          after_color = 255;//白
        } else {
          after_color = 0;//黑
        }
        int Pixel = new Color(after_color, after_color, after_color).getRGB();
        otsuImage.setRGB(x, y, Pixel);
      }
    }
    return otsuImage;
  }

  public BufferedImage toImage() {
    return otsu(img);
  }
}