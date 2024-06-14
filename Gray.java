import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Gray {

  private BufferedImage img;

  public Gray(BufferedImage img) {
    this.img = img;
  }

  public BufferedImage gray(BufferedImage img) {
    int width = img.getWidth();
    int height = img.getHeight();

    //存儲灰階圖像，每個像素點用8個位元組（1個位元組）表示
    BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int color = img.getRGB(x, y);//像素點的整數表示形式的顏色值
        // 將數字轉換成RGB
        int r = (color >> 16) & 0xff;// 提取出的紅色分量值
        int g = (color >> 8) & 0xff;
        int b = color & 0xff;
        // 算出灰階值，使用加權法
        int gray = (int) (0.299 * r + 0.587 * g + 0.114 * b);

        //創建了一個新的 Color 物件，該物件代表灰階顏色
        int grayPixel = new Color(gray, gray, gray).getRGB();
        grayImage.setRGB(x, y, grayPixel);// 建立新的灰階影像（grayImage）中的特定位置（x, y）設置灰度像素值
      }
    }
    return grayImage;
  }

  public BufferedImage toImage() {
    return gray(img);
  }
}