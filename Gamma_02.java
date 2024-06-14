import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Gamma_02 {

  private BufferedImage img;

  public Gamma_02(BufferedImage img) {
    this.img = img;
  }

  public BufferedImage gamma(BufferedImage img) {
    int width = img.getWidth();
    int height = img.getHeight();

    BufferedImage gammaImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

    // [(p(i,j)-min/max-min)^gamma]*255
    // p(i,j)為像素點之值，min為圖片中像素之最小值，max為圖片中像素之最大值
    double gamma = 0.2;// gamma < 1
    int max = 0;
    int min = 10000;
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int color = img.getRGB(x, y) & 0xFF; // 直接取灰階值
        if (color > max) {
          max = color;
        }
        if (color < min) {
          min = color;
        }
      }
    }

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int color = img.getRGB(x, y) & 0xFF; // 直接取灰階值
        // 將數字轉換成RGB
        double gamma_color = Math.pow((double)(color - min) / (max - min),gamma) * 255;
        int after_color = (int)gamma_color;
        int Pixel = new Color(after_color, after_color, after_color).getRGB();
          gammaImage.setRGB(x, y, Pixel);
      }
    }
    return gammaImage;
  }

  public BufferedImage toImage() {
    return gamma(img);
  }
}