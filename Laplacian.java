import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Laplacian {

  private BufferedImage img;

  public Laplacian(BufferedImage img) {
    this.img = img;
  }

  public BufferedImage laplacian(BufferedImage img) {
    int width = img.getWidth();
    int height = img.getHeight();

    BufferedImage laplacianImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

    int[][] laplacianKernel = { { 1, 1, 1 }, { 1, -8, 1 }, { 1, 1, 1 } };

    for (int i = 0; i < width; i++) {// 原本陣列
      for (int j = 0; j < height; j++) {
        int sum = 0;
        for (int x = -1; x <= 1; x++) {// laplacian陣列
          for (int y = -1; y <= 1; y++) {
            int color = 0;
            if (i + x >= 0 && i + x < width && j + y >= 0 && j + y < height) {
              color = img.getRGB(i + x, j + y) & 0xFF; // 取這個原陣列元素的灰階值
            }
            sum += color * laplacianKernel[x + 1][y + 1];
          }
        }

        int after_color = 0;
        //絕對值
        sum = Math.abs(sum);
        //讓數值不會超過灰階值
        if (sum < 0) {
          after_color = 0;
        } else if (sum > 255) {
          after_color = 255;
        } else {
          after_color = sum;
        }
        int Pixel = new Color(after_color, after_color, after_color).getRGB();
        laplacianImage.setRGB(i, j, Pixel);
      }
    }
    return laplacianImage;
  }

  public BufferedImage toImage() {
    return laplacian(img);
  }
}