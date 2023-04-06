import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class ImageEditor 
{
    private ImageEditor(){};

    public static BufferedImage add(BufferedImage firstBufferedImageParameter, BufferedImage secondBufferedImageParameter)
    {
        BufferedImage bufferedImage = ImageEditor.duplicateBufferedImage(firstBufferedImageParameter);
        int sizeX = bufferedImage.getWidth();
        int sizeY = bufferedImage.getHeight();
        for(int y = 0; y < sizeY; y++)
        {
            for(int x = 0; x < sizeX; x++)
            {
                int firstRGB = firstBufferedImageParameter.getRGB(x, y);
                Color firstColor = new Color(firstRGB);
                int secondRGB = secondBufferedImageParameter.getRGB(x, y);
                Color secondColor = new Color(secondRGB);
                double firstRed = ImageEditor.toZeroOne(firstColor.getRed());
                double secondRed = ImageEditor.toZeroOne(secondColor.getRed());
                double firstGreen = ImageEditor.toZeroOne(firstColor.getGreen());
                double secondGreen = ImageEditor.toZeroOne(secondColor.getGreen());
                double firstBlue = ImageEditor.toZeroOne(firstColor.getBlue());
                double secondBlue = ImageEditor.toZeroOne(secondColor.getBlue());

                int red = ImageEditor.fromZeroOne(firstRed + secondRed);
                int green = ImageEditor.fromZeroOne(firstGreen + secondGreen);
                int blue = ImageEditor.fromZeroOne(firstBlue + secondBlue);

                Color newColor = new Color(red, green, blue);
                int rgb = newColor.getRGB();
                bufferedImage.setRGB(x, y, rgb);   
            }
        }
        return bufferedImage;
    }

    public static BufferedImage substract(BufferedImage firstBufferedImageParameter, BufferedImage secondBufferedImageParameter)
    {
        BufferedImage bufferedImage = ImageEditor.duplicateBufferedImage(firstBufferedImageParameter);
        int sizeX = bufferedImage.getWidth();
        int sizeY = bufferedImage.getHeight();
        for(int y = 0; y < sizeY; y++)
        {
            for(int x = 0; x < sizeX; x++)
            {
                int firstRGB = firstBufferedImageParameter.getRGB(x, y);
                Color firstColor = new Color(firstRGB);
                int secondRGB = secondBufferedImageParameter.getRGB(x, y);
                Color secondColor = new Color(secondRGB);
                double firstRed = ImageEditor.toZeroOne(firstColor.getRed());
                double secondRed = ImageEditor.toZeroOne(secondColor.getRed());
                double firstGreen = ImageEditor.toZeroOne(firstColor.getGreen());
                double secondGreen = ImageEditor.toZeroOne(secondColor.getGreen());
                double firstBlue = ImageEditor.toZeroOne(firstColor.getBlue());
                double secondBlue = ImageEditor.toZeroOne(secondColor.getBlue());

                int red = ImageEditor.fromZeroOne(firstRed - secondRed);
                int green = ImageEditor.fromZeroOne(firstGreen - secondGreen);
                int blue = ImageEditor.fromZeroOne(firstBlue - secondBlue);

                Color newColor = new Color(red, green, blue);
                int rgb = newColor.getRGB();
                bufferedImage.setRGB(x, y, rgb);   
            }
        }
        return bufferedImage;
    }

    public static BufferedImage multiply(BufferedImage firstBufferedImageParameter, BufferedImage secondBufferedImageParameter)
    {
        BufferedImage bufferedImage = ImageEditor.duplicateBufferedImage(firstBufferedImageParameter);
        int sizeX = bufferedImage.getWidth();
        int sizeY = bufferedImage.getHeight();
        for(int y = 0; y < sizeY; y++)
        {
            for(int x = 0; x < sizeX; x++)
            {
                int firstRGB = firstBufferedImageParameter.getRGB(x, y);
                Color firstColor = new Color(firstRGB);
                int secondRGB = secondBufferedImageParameter.getRGB(x, y);
                Color secondColor = new Color(secondRGB);
                double firstRed = ImageEditor.toZeroOne(firstColor.getRed());
                double secondRed = ImageEditor.toZeroOne(secondColor.getRed());
                double firstGreen = ImageEditor.toZeroOne(firstColor.getGreen());
                double secondGreen = ImageEditor.toZeroOne(secondColor.getGreen());
                double firstBlue = ImageEditor.toZeroOne(firstColor.getBlue());
                double secondBlue = ImageEditor.toZeroOne(secondColor.getBlue());

                int red = ImageEditor.fromZeroOne(firstRed * secondRed);
                int green = ImageEditor.fromZeroOne(firstGreen * secondGreen);
                int blue = ImageEditor.fromZeroOne(firstBlue * secondBlue);

                Color newColor = new Color(red, green, blue);
                int rgb = newColor.getRGB();
                bufferedImage.setRGB(x, y, rgb);   
            }
        }
        return bufferedImage;
    }

    public static BufferedImage divide(BufferedImage firstBufferedImageParameter, BufferedImage secondBufferedImageParameter)
    {
        BufferedImage bufferedImage = ImageEditor.duplicateBufferedImage(firstBufferedImageParameter);
        int sizeX = bufferedImage.getWidth();
        int sizeY = bufferedImage.getHeight();
        for(int y = 0; y < sizeY; y++)
        {
            for(int x = 0; x < sizeX; x++)
            {
                int firstRGB = firstBufferedImageParameter.getRGB(x, y);
                Color firstColor = new Color(firstRGB);
                int secondRGB = secondBufferedImageParameter.getRGB(x, y);
                Color secondColor = new Color(secondRGB);
                double firstRed = ImageEditor.toZeroOne(firstColor.getRed());
                double secondRed = ImageEditor.toZeroOne(secondColor.getRed());
                double firstGreen = ImageEditor.toZeroOne(firstColor.getGreen());
                double secondGreen = ImageEditor.toZeroOne(secondColor.getGreen());
                double firstBlue = ImageEditor.toZeroOne(firstColor.getBlue());
                double secondBlue = ImageEditor.toZeroOne(secondColor.getBlue());

                int red = ImageEditor.fromZeroOne(firstRed / ImageEditor.clampValueForDivision(secondRed));
                int green = ImageEditor.fromZeroOne(firstGreen / ImageEditor.clampValueForDivision(secondGreen));
                int blue = ImageEditor.fromZeroOne(firstBlue / ImageEditor.clampValueForDivision(secondBlue));

                Color newColor = new Color(red, green, blue);
                int rgb = newColor.getRGB();
                bufferedImage.setRGB(x, y, rgb);   
            }
        }
        return bufferedImage;
    }

    //PRIVATE

    private static BufferedImage duplicateBufferedImage(BufferedImage bufferedImageParameter) 
    {
        ColorModel colorModel = bufferedImageParameter.getColorModel();
        boolean isAlphaPremultiplied = colorModel.isAlphaPremultiplied();
        WritableRaster raster = bufferedImageParameter.copyData(null);
        return new BufferedImage(colorModel, raster, isAlphaPremultiplied, null);
    }

    private static double clampValueForDivision(double valueParameter)
    {
        return Math.max(1.0 / 255.0, Math.min(1, valueParameter));
    }

    private static double toZeroOne(int valueParameter)
    {
        double value = (valueParameter * 1.0) / 255.0;
        return value;
    }

    private static int fromZeroOne(double valueParameter)
    {
        int value = (int)(valueParameter * 255);
        return Math.max(0, Math.min(255, value));
    }
}


