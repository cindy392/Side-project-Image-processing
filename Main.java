import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class Image extends Thread {

    private String word;
    private Gray grayImage;

    public Image(String word,Gray grayImage) {
        this.word = word;
        this.grayImage = grayImage;
    }
    public void run() {
        if (word.equals("a")) {
          // 灰階轉成負片
          Negative negativeImage = new Negative(grayImage.toImage());
          // 保存負片影像
          saveImage(negativeImage.toImage(), "image/negative_image.jpg");
        }else if(word.equals("b")) {
          // Gamma<1
          Gamma_02 gammaImage_02 = new Gamma_02(grayImage.toImage());
          // 保存影像
          saveImage(gammaImage_02.toImage(), "image/gammaImage_0.2_image.jpg");

          // 胡椒鹽雜訊
          SaltPepper saltpepperImage = new SaltPepper(gammaImage_02.toImage());
          // 保存胡椒鹽雜訊影像
          saveImage(saltpepperImage.toImage(), "image/saltpepperImage_image.jpg");

          // 3*3 中值濾波器
          Medianfilter medianfilterImage = new Medianfilter(saltpepperImage.toImage());
          // 保存3*3 中值濾波器影像
          saveImage(medianfilterImage.toImage(), "image/medianfilter_image.jpg");
        }else if(word.equals("c")) {
          // 對比拉開
          Contrast contrastImage = new Contrast(grayImage.toImage());
          // 保存對比拉開影像
          saveImage(contrastImage.toImage(), "image/contrast_image.jpg");

          // Laplacian 邊緣偵測
          Laplacian laplacianImage = new Laplacian(contrastImage.toImage());
          // 保存Laplacian影像
          saveImage(laplacianImage.toImage(), "image/laplacian_image.jpg");

          // 3*3 最大值濾波器
          Maxfilter maxfilterImage = new Maxfilter(laplacianImage.toImage());
          // 保存3*3 最大值濾波器影像
          saveImage(maxfilterImage.toImage(), "image/maxfilter_image.jpg");
        }else{
          // Gamma>1
          Gamma_17 gammaImage_17 = new Gamma_17(grayImage.toImage());
          // 保存影像
          saveImage(gammaImage_17.toImage(), "image/gammaImage_1.7_image.jpg");

          // OTSU 二值化 (*Bonus)
          OTSU otsu = new OTSU(gammaImage_17.toImage());
          // 保存影像
          saveImage(otsu.toImage(), "image/otsu_image.jpg");
        }
    }
  // 保存图像
  private static void saveImage(BufferedImage image, String filePath) {
    try {
      ImageIO.write(image, "jpg", new File(filePath));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
public class Main{
    public static void main(String[] args) {
        // 输入和输出文件路径
        String inputFilePath = "image.jpg";

        // 讀取圖片
        BufferedImage inputImage = readImage(inputFilePath);

        // 轉換成灰階影像
        Gray grayImage = new Gray(inputImage);
        // 保存灰階影像
        saveImage(grayImage.toImage(), "image/gray_image.jpg");
      
          Image thread1 = new Image("a",grayImage);
          Image thread2 = new Image("b",grayImage);
          Image thread3 = new Image("c",grayImage);
          Image thread4 = new Image("d",grayImage);

        // 啟動執行緒
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        // 等待執行緒結束
        try{
          thread1.run();
          thread2.run();
          thread3.run();
          thread4.run();
        } catch (Exception e) {
          e.printStackTrace();
        }
    }
    // 读取图像
    private static BufferedImage readImage(String filePath) {
      BufferedImage image = null;
      try {
        image = ImageIO.read(new File(filePath));
      } catch (IOException e) {
        e.printStackTrace();
      }
      return image;
    }

    // 保存图像
    private static void saveImage(BufferedImage image, String filePath) {
      try {
        ImageIO.write(image, "jpg", new File(filePath));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
}



