import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.Buffer;
import java.sql.SQLOutput;
import java.util.*;

public class Main {
    public static void getPixelValues(BufferedImage InputImage){
        int height = InputImage.getHeight();
        int width = InputImage.getWidth();
        for(int i =0 ;i<height;i++){
            for(int j =0;j<width;j++){
                //System.out.print(InputImage.getRGB(j,i));
                Color pixel = new Color(InputImage.getRGB(j, i));
                System.out.print("("+pixel.getRed()+" "+pixel.getBlue()+" "+ pixel.getGreen()+")");
            }
            System.out.println();
        }
    }
    public static BufferedImage GrayScale(BufferedImage InputImage){
        int height = InputImage.getHeight();
        int width = InputImage.getWidth();
        BufferedImage OutputImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        for(int i =0;i<height;i++){
            for(int j =0;j<width;j++){
                Color pixel = new Color(InputImage.getRGB(j,i));
                int red = pixel.getRed();
                int blue = pixel.getBlue();
                int green = pixel.getGreen();
                int avg = (red+blue+green)/3;
                red = avg;
                blue = avg;
                green = avg;
                Color NewPixel = new Color(red , green , blue);
                OutputImage.setRGB(j,i,NewPixel.getRGB());            }
        }
        return OutputImage;
    }
    public static BufferedImage ChangeBrightness(BufferedImage InputImage,int increase ){
        int height = InputImage.getHeight();
        int width = InputImage.getWidth();
        BufferedImage OutputImage = new BufferedImage(width , height , BufferedImage.TYPE_3BYTE_BGR);
        for(int i =0;i<height;i++){
            for(int j =0;j<width;j++){
                Color pixel = new Color(InputImage.getRGB(j,i));
                int red = pixel.getRed();
                int blue = pixel.getBlue();
                int green = pixel.getGreen();
                red = red + (red*increase)/100 ;
                blue = blue +(blue*increase)/100;
                green = green + (green*increase)/100;
                if(red>255) red =255;
                if(green>255) green=255;
                if(blue>255) blue=255;
                if(red<0) red =0;
                if(green<0) green=0;
                if(blue<0) blue=0;
                Color NewPixel = new Color(red , green , blue);
                OutputImage.setRGB(j,i,NewPixel.getRGB());

            }
        }
        return OutputImage;

    }
    public static BufferedImage RotateImage(BufferedImage InputImage){
        int height = InputImage.getHeight();
        int width = InputImage.getWidth();

        int[][] pixel = new int[height][width];
        for(int i =0;i<height;i++){
            for(int j =0;j<width;j++){
                pixel[i][j]=InputImage.getRGB(j,i);
            }
        }
        int [][] rotatedPixel = new int[width][height];
        for(int i =0;i<height;i++){
            for(int j=0;j<width;j++){
                rotatedPixel[j][height-1-i]=pixel[i][j];
            }
        }
        BufferedImage OutputImage = new BufferedImage(height,width,BufferedImage.TYPE_INT_RGB);
        for(int i =0;i<width;i++){
            for(int j =0;j<height;j++){
                OutputImage.setRGB(j, i, rotatedPixel[i][j]);
            }
        }
        return OutputImage;

    }

    public static BufferedImage InvertHorizontally(BufferedImage InputImage){

        int height = InputImage.getHeight();
        int width = InputImage.getWidth();

        int[][] pixel = new int[height][width];
        for(int i =0;i<height;i++){
            for(int j =0;j<width;j++){
                pixel[i][j]=InputImage.getRGB(j,i);
            }
        }
        for(int i =0;i<height;i++){
            for(int j =0;j<width/2;j++){
                int temp = pixel[i][j];
                pixel[i][j]=pixel[i][width-1-j];
                pixel[i][width-1-j]=temp;
            }
        }
        BufferedImage OutputImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        for(int i =0;i<height;i++){
            for(int j =0;j<width;j++){
                OutputImage.setRGB(j, i, pixel[i][j]);
            }
        }
        return OutputImage;
    }
    public static BufferedImage InvertVertically(BufferedImage InputImage) {

        int height = InputImage.getHeight();
        int width = InputImage.getWidth();

        int[][] pixel = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                pixel[i][j] = InputImage.getRGB(j, i);
            }
        }
        for (int i = 0; i < height/2; i++) {
            for (int j = 0; j < width; j++) {
                int temp = pixel[i][j];
                pixel[i][j] = pixel[height - 1 - i][j];
                pixel[height - 1 - i][j] = temp;
            }
        }
        BufferedImage OutputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                OutputImage.setRGB(j, i, pixel[i][j]);
            }
        }
        return OutputImage;
    }

    public static BufferedImage GreenScale(BufferedImage InputImage) {
        int height = InputImage.getHeight();
        int width = InputImage.getWidth();
        BufferedImage OutputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color pixel = new Color(InputImage.getRGB(j, i));
                int red = pixel.getRed();
                int blue = pixel.getBlue();
                int green = pixel.getGreen();
                green = (int) (green * 0.5 + green);
                if(green>255) green=255;

                Color NewPixel = new Color(red, green, blue);
                OutputImage.setRGB(j, i, NewPixel.getRGB());
            }
        }
        return OutputImage;
    }
    public static BufferedImage RedScale(BufferedImage InputImage) {
        int height = InputImage.getHeight();
        int width = InputImage.getWidth();
        BufferedImage OutputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color pixel = new Color(InputImage.getRGB(j, i));
                int red = pixel.getRed();
                int blue = pixel.getBlue();
                int green = pixel.getGreen();
                red = (int) (red * 0.25 + red);
                if (red > 255) red = 255;

                Color NewPixel = new Color(red, green, blue);
                OutputImage.setRGB(j, i, NewPixel.getRGB());
            }
        }
        return OutputImage;
    }

    public static BufferedImage BlueScale(BufferedImage InputImage) {
        int height = InputImage.getHeight();
        int width = InputImage.getWidth();
        BufferedImage OutputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color pixel = new Color(InputImage.getRGB(j, i));
                int red = pixel.getRed();
                int blue = pixel.getBlue();
                int green = pixel.getGreen();
                blue = (int) (blue * 0.75 + blue);
                if (blue > 255) blue = 255;

                Color NewPixel = new Color(red, green, blue);
                OutputImage.setRGB(j, i, NewPixel.getRGB());
            }
        }
        return OutputImage;
    }
    public static BufferedImage BlurImage(BufferedImage InputImage,int blrAmount){
        int height = InputImage.getHeight();
        int width = InputImage.getWidth();
        BufferedImage OutputImage = new BufferedImage(width, height , BufferedImage.TYPE_INT_RGB);
        for(int i =0; i<height; i+=blrAmount){
            for(int j =0; j<width; j+=blrAmount){
                int sumOfGreen =0,sumOfBlue=0,sumOfRed=0,sumOfAlpha=0;
                int count=0;
                for( int k =i; k < i+blrAmount; k++){
                    for ( int l = j ; l < j+blrAmount ; l++){
                        if( k < height && l <width) {
                            Color pixel = new Color(InputImage.getRGB(l,k));
                            sumOfRed+= pixel.getRed();
                            sumOfGreen+=pixel.getGreen();
                            sumOfBlue+= pixel.getBlue();
                            sumOfAlpha+= pixel.getAlpha() * 0;
                            count++;
                        }
                    }
                }
                sumOfRed=sumOfRed/count;
                sumOfGreen=sumOfGreen/count;
                sumOfBlue=sumOfBlue/count;
                sumOfAlpha=sumOfAlpha/count;
                for( int k =i; k<i+blrAmount ;k++){
                    for( int l =j ; l< j+blrAmount ; l++){
                        if(k<height && l<width) {
                            Color newPixel = new Color(sumOfRed,sumOfGreen,sumOfBlue,sumOfAlpha);
                            OutputImage.setRGB(l,k,newPixel.getRGB());
                        }
                    }
                }
            }
        }
        return OutputImage;

    }

    public static void main(String[] args){
        Scanner sc = new Scanner (System.in);
        System.out.println("Enter File Name : ");
        String pathName=sc.nextLine();
        File ImageFile = new File(pathName);
        System.out.println("Press 1 to convert image to Gray Scale.");
        System.out.println("Press 2 to rotate image by 90 Degree.");
        System.out.println("Press 3 to invert image Horizontally.");
        System.out.println("Press 4 to invert image Vertically.");
        System.out.println("Press 5 to Change Brightness of Image .");
        System.out.println("Press 6 to convert image to green scale.");
        System.out.println("Press 7 to convert image to red scale.");
        System.out.println("Press 8 to convert image to blue scale.");
        System.out.println("Press 9 to blur the image.");

        int input = sc.nextInt();

        try {

            BufferedImage InputImage = ImageIO.read(ImageFile);

            switch(input) {

                //to get pixel values
                /*getPixelValues(InputImage);*/

                //to get gray scale image
                case 1:
                    BufferedImage GrayImage = GrayScale(InputImage);

                    System.out.println("Enter Output Image File Name : ");
                    sc.nextLine();
                    String Image_name1 = sc.nextLine();
                    System.out.println("Saving file to image name "+Image_name1);
                    File GrayScaleImage = new File(Image_name1);
                    ImageIO.write(GrayImage, "jpg", GrayScaleImage);
                    System.out.println("Image Successfully converted to Gray Scale! : " + Image_name1);
                    break;


                //rotate the image
                case 2:
                    BufferedImage RotatedImage = RotateImage(InputImage);

                    System.out.println("Enter Output Image File Name : ");
                    sc.nextLine();
                    String Image_name2 = sc.nextLine();
                    System.out.println("Saving file to image name "+Image_name2);
                    File RotatedImageFile = new File(Image_name2);
                    ImageIO.write(RotatedImage,"jpg",RotatedImageFile);
                    System.out.println("Image Rotated by 90 Degrees ! : "+Image_name2);

                    break;

                //to horizontally Invert the image
                case 3:
                    BufferedImage HInvertedImage = InvertHorizontally(InputImage);

                    System.out.println("Enter Output Image File Name : ");
                    sc.nextLine();
                    String Image_name3 = sc.nextLine();
                    System.out.println("Saving file to image name "+Image_name3);
                    File HorizontallyInvertedImageFile = new File(Image_name3);
                    ImageIO.write(HInvertedImage,"jpg",HorizontallyInvertedImageFile);
                    System.out.println("Image Horizontally Inverted ! : "+Image_name3);

                    break;

                //to vertically Invert the image
                case 4:

                BufferedImage VInvertedImage = InvertVertically(InputImage);

                System.out.println("Enter Output Image File Name : ");
                sc.nextLine();
                String Image_name4 = sc.nextLine();
                System.out.println("Saving file to image name "+Image_name4);
                File VerticallyInvertedImageFile = new File(Image_name4);
                ImageIO.write(VInvertedImage,"jpg",VerticallyInvertedImageFile);
                System.out.println("Image Vertically Inverted ! : "+Image_name4);

                break;

                //change the brightness;
                case 5:
                    System.out.println("Enter the Value by which you want to increase the Brightness : ");
                    int inc = sc.nextInt();
                    BufferedImage ChangedBrightness = ChangeBrightness(InputImage, inc);
                    System.out.println("Enter Output Image File Name : ");
                    sc.nextLine();
                    String Image_name5 = sc.nextLine();
                    File ChangedBrightnessImage = new File(Image_name5);
                    ImageIO.write(ChangedBrightness, "jpg", ChangedBrightnessImage);
                    System.out.println("Brightness of Image Successfully increased by "+ inc +" : " + Image_name5);
                    break;

                //to green scale the image
                case 6 :

                    BufferedImage GreenedImage = GreenScale(InputImage);
                    System.out.println("Enter Output Image File Name : ");
                    sc.nextLine();
                    String Image_name6 = sc.nextLine();
                    System.out.println("Saving file to image name "+Image_name6);
                    File GreenedImageFile = new File(Image_name6);
                    ImageIO.write(GreenedImage,"jpg",GreenedImageFile);
                    System.out.println("Image Green Scale Done ! : "+Image_name6);

                    break;

                case 7 :

                    BufferedImage RededImage = RedScale(InputImage);
                    System.out.println("Enter Output Image File Name : ");
                    sc.nextLine();
                    String Image_name7 = sc.nextLine();
                    System.out.println("Saving file to image name "+Image_name7);
                    File RededImageFile = new File(Image_name7);
                    ImageIO.write(RededImage,"jpg",RededImageFile);
                    System.out.println("Image Red Scale Done ! : "+Image_name7);

                    break;

                case 8 :

                    BufferedImage BluedImage = BlueScale(InputImage);
                    System.out.println("Enter Output Image File Name : ");
                    sc.nextLine();
                    String Image_name8 = sc.nextLine();
                    System.out.println("Saving file to image name "+Image_name8);
                    File BluedImageFile = new File(Image_name8);
                    ImageIO.write(BluedImage,"jpg",BluedImageFile);
                    System.out.println("Image Blue Scale Done ! : "+Image_name8);

                    break;

                case 9:

                    System.out.println("Enter the amount of blur : ");
                    int blrAmount=sc.nextInt();
                    BufferedImage BlurredImage = BlurImage(InputImage,blrAmount);
                    System.out.println("Enter Output Image File Name: ");
                    sc.nextLine();
                    String Image_name9 =sc.nextLine();
                    System.out.println("Saving file to image name " + Image_name9);
                    File BlurredImageFile = new File(Image_name9);
                    ImageIO.write(BlurredImage,"jpg",BlurredImageFile);
                    System.out.println("Image Blurred SuccessFully ! : "+Image_name9);

                    break;

                default:
                    System.out.println("Invalid Option Selected");
                    break;


            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

//Project has ended