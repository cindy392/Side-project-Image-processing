import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Negative {

  private BufferedImage img;

  public Negative(BufferedImage img) {
    this.img = img;
  }

  public BufferedImage negative(BufferedImage img) {
    int width = img.getWidth();
    int height = img.getHeight();

    BufferedImage negativeImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int color = img.getRGB(x, y) & 0xFF; // 直接取灰階值
        // 將數字轉換成RGB
        int after_color = 255 - color;
        if (after_color > 255) {
          after_color = 255;
        } else if (after_color < 0) {
          after_color = 0;
        }
        int Pixel = new Color(after_color, after_color, after_color).getRGB();
        negativeImage.setRGB(x, y, Pixel);
      }
    }
    return negativeImage;
  }

  public BufferedImage toImage() {
    return negative(img);
  }
}