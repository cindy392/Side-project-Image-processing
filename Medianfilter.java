import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Arrays;

public class Medianfilter {

  private BufferedImage img;

  public Medianfilter(BufferedImage img) {
    this.img = img;
  }

  public BufferedImage mdianfilter(BufferedImage img) {
    int width = img.getWidth();
    int height = img.getHeight();

    BufferedImage mdianfilterImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

    int[] nums = new int[9];

    for (int i = 0; i < width; i++) {// 原本圖片的陣列
      for (int j = 0; j < height; j++) {
        int n = 0;
        for (int x = -1; x <= 1; x++) {// 小陣列
          for (int y = -1; y <= 1; y++) {
            int color = 0;
            if (i + x >= 0 && i + x < width && j + y >= 0 && j + y < height) {
              color = img.getRGB(i + x, j + y) & 0xFF; // 取這個原陣列元素的灰階值
            }
            nums[n] = color;
            n++;
          }
        }
        Arrays.sort(nums);//將數值從小到大排序
        int mid = nums[4];//選最中間的值
        int after_color = mid;
        int Pixel = new Color(after_color, after_color, after_color).getRGB();
        mdianfilterImage.setRGB(i, j, Pixel);
      }
    }
    return mdianfilterImage;
  }

  public BufferedImage toImage() {
    return mdianfilter(img);
  }
}