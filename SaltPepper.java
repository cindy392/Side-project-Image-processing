import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Random;

public class SaltPepper {

  private BufferedImage img;

  public SaltPepper(BufferedImage img) {
    this.img = img;
  }

  public BufferedImage saltpepper(BufferedImage img) {
    int width = img.getWidth();
    int height = img.getHeight();

    BufferedImage saltpepperImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

    //隨機撒黑點跟白點
    Random random = new Random();
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int color = img.getRGB(x, y) & 0xFF; // 直接取灰階值
        int num = random.nextInt(10);
        int after_color = 0;
        if (num < 1){
          after_color = 0;//鹽
        }else if(num > 8){
          after_color = 255;//胡椒
        }else{
          after_color = color;//維持原樣
        }
        int Pixel = new Color(after_color, after_color, after_color).getRGB();
            saltpepperImage.setRGB(x, y, Pixel);
      }
    }
    return saltpepperImage;
  }

  public BufferedImage toImage() {
    return saltpepper(img);
  }
}