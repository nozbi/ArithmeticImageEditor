import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.*;
import javax.swing.border.CompoundBorder;


public class App 
{
    private JLabel firstSourceImageLabel;
    private JLabel secondSourceImageLabel;
    private JLabel resultImageLabel;
    private JMenu resultImageMenu;
    private JMenu EditMenu; 

    public static void main(String[] args) throws Exception 
    {
        new App();
    }

    private App()
    {
        //FRAME
        JFrame frame = new JFrame();
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Image Editor");
        frame.setVisible(true);
        frame.setLayout(new GridLayout(1, 2, 0, 0));
        frame.setLocationRelativeTo(null);

        //SOURCE PANEL
        JPanel sourcePanel = new JPanel();
        frame.add(sourcePanel);
        sourcePanel.setLayout(new GridLayout(2, 1, 0, 0));

        //SOURCE PANEL -> RIRST SOURCE PANEL
        JPanel firstSourceImageBorderPanel = new JPanel();
        sourcePanel.add(firstSourceImageBorderPanel);
        firstSourceImageBorderPanel.setLayout(new GridLayout(1, 1, 0, 0));
        JPanel firstSourceImageSlotPanel = new JPanel();
        firstSourceImageBorderPanel.add(firstSourceImageSlotPanel);
        firstSourceImageSlotPanel.setLayout(new GridBagLayout());
        JLabel firstSourceImageLabel = new JLabel();
        firstSourceImageSlotPanel.add(firstSourceImageLabel);

        //SOURCE PANEL -> SECOND SOURCE PANEL
        JPanel secondSourceImageBorderPanel = new JPanel();
        sourcePanel.add(secondSourceImageBorderPanel);
        secondSourceImageBorderPanel.setLayout(new GridLayout(1, 1, 0, 0));
        JPanel secondSourceImageSlotPanel = new JPanel();
        secondSourceImageBorderPanel.add(secondSourceImageSlotPanel);
        secondSourceImageSlotPanel.setLayout(new GridBagLayout());
        JLabel secondSourceImageLabel = new JLabel();
        secondSourceImageSlotPanel.add(secondSourceImageLabel);

        //RESULT PANEL
        JPanel resultPanel = new JPanel();
        frame.add(resultPanel);
        resultPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel topSpacePanel = new JPanel();
        topSpacePanel.setPreferredSize(new Dimension(resultPanel.getWidth(), resultPanel.getHeight() / 4));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.25;
        gbc.fill = GridBagConstraints.BOTH;
        resultPanel.add(topSpacePanel, gbc);
        JPanel resultImageBorderPanel = new JPanel();
        resultImageBorderPanel.setPreferredSize(new Dimension(resultPanel.getWidth(), resultPanel.getHeight() / 2));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        resultPanel.add(resultImageBorderPanel, gbc);
        resultImageBorderPanel.setLayout(new GridLayout(1, 1, 0, 0));
        JPanel resultImageSlotPanel = new JPanel();
        resultImageBorderPanel.add(resultImageSlotPanel);
        resultImageSlotPanel.setLayout(new GridBagLayout());
        JLabel resultImageLabel = new JLabel();
        resultImageSlotPanel.add(resultImageLabel);
        JPanel bottomSpacePanel = new JPanel();
        bottomSpacePanel.setPreferredSize(new Dimension(resultPanel.getWidth(), resultPanel.getHeight() / 4));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 0.25;
        gbc.fill = GridBagConstraints.BOTH;
        resultPanel.add(bottomSpacePanel, gbc);

        //MENU BAR
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        //FIRST SOURCE IMAGE MENU
        JMenu firstSourceImageMenu = new JMenu("First Image");
        menuBar.add(firstSourceImageMenu);
        JMenuItem loadFirstSourceImageMenuItem = new JMenuItem("Load");
        firstSourceImageMenu.add(loadFirstSourceImageMenuItem);
        JMenuItem clearFirstSourceImageMenuItem = new JMenuItem("Clear");
        firstSourceImageMenu.add(clearFirstSourceImageMenuItem);

        //SECOND SOURCE IMAGE MENU
        JMenu secondSourceImageMenu = new JMenu("Second Image");
        menuBar.add(secondSourceImageMenu);
        JMenuItem loadSecondSourceImageMenuItem = new JMenuItem("Load");
        secondSourceImageMenu.add(loadSecondSourceImageMenuItem);
        JMenuItem clearSecondSourceImageMenuItem = new JMenuItem("Clear");
        secondSourceImageMenu.add(clearSecondSourceImageMenuItem);
        
        //RESULT IMAGE MENU
        JMenu resultImageMenu = new JMenu("Result Image");
        menuBar.add(resultImageMenu);
        JMenuItem switchToFirstSourceImageMenuItem = new JMenuItem("Switch to first");
        resultImageMenu.add(switchToFirstSourceImageMenuItem);
        JMenuItem switchToSecondSourceImageMenuItem = new JMenuItem("Switch to second");
        resultImageMenu.add(switchToSecondSourceImageMenuItem);
        JMenuItem resetMenuItem = new JMenuItem("Reset");
        resultImageMenu.add(resetMenuItem);
        JMenuItem saveMenuItem = new JMenuItem("Save");
        resultImageMenu.add(saveMenuItem);

        //EDIT MENU
        JMenu EditMenu = new JMenu("Edit");
        menuBar.add(EditMenu);
        JMenuItem firstAddSecondMenuItem = new JMenuItem("first + second");
        EditMenu.add(firstAddSecondMenuItem);
        JMenuItem firstSubstractSecondMenuItem = new JMenuItem("first - second");
        EditMenu.add(firstSubstractSecondMenuItem);
        JMenuItem firstMultiplySecondMenuItem = new JMenuItem("first * second");
        EditMenu.add(firstMultiplySecondMenuItem);
        JMenuItem firstDivideSecondMenuItem = new JMenuItem("first / second");
        EditMenu.add(firstDivideSecondMenuItem);
        JMenuItem secondAddSecondMenuItem = new JMenuItem("second + first");
        EditMenu.add(secondAddSecondMenuItem);
        JMenuItem secondSubstractSecondMenuItem = new JMenuItem("second - first");
        EditMenu.add(secondSubstractSecondMenuItem);
        JMenuItem secondMultiplySecondMenuItem = new JMenuItem("second * first");
        EditMenu.add(secondMultiplySecondMenuItem);
        JMenuItem secondDivideSecondMenuItem = new JMenuItem("second / first");
        EditMenu.add(secondDivideSecondMenuItem);

        //SETTING GLOBAL VARIABLES
        this.firstSourceImageLabel = firstSourceImageLabel;
        this.secondSourceImageLabel = secondSourceImageLabel;
        this.resultImageLabel = resultImageLabel;
        this.resultImageMenu = resultImageMenu;
        this.EditMenu = EditMenu;

        //ADDING ACTION LISTENERS
        loadFirstSourceImageMenuItem.addActionListener(actionEvent->{this.onLoadFirstSourceImageMenuItemActionPerformed();});
        loadSecondSourceImageMenuItem.addActionListener(actionEvent->{this.onLoadSecondSourceImageMenuItemActionPerformed();});
        saveMenuItem.addActionListener(actionEvent ->{this.onSaveMenuItemActionPerformed();});
        clearFirstSourceImageMenuItem.addActionListener(actionEvent->{this.onClearFirstSourceImageMenuItemActionPerformed();});
        clearSecondSourceImageMenuItem.addActionListener(actionEvent->{this.onClearSecondSourceImageMenuItemActionPerformed();});
        switchToFirstSourceImageMenuItem.addActionListener(actionEvent->{this.onSwitchToFirstSourceImageMenuItemActionPerformed();});
        switchToSecondSourceImageMenuItem.addActionListener(actionEvent->{this.onSwitchToSecondSourceImageMenuItemActionPerformed();});
        resetMenuItem.addActionListener(actionEvent->{this.onResetMenuItemActionPerformed();});
        firstAddSecondMenuItem.addActionListener(actionEvent->{this.setResultImage(ImageEditor.add(this.getFirstSourceImage(), this.getSecondSourceImage()));});
        firstSubstractSecondMenuItem.addActionListener(actionEvent->{this.setResultImage(ImageEditor.substract(this.getFirstSourceImage(), this.getSecondSourceImage()));});
        firstMultiplySecondMenuItem.addActionListener(actionEvent->{this.setResultImage(ImageEditor.multiply(this.getFirstSourceImage(), this.getSecondSourceImage()));});
        firstDivideSecondMenuItem.addActionListener(actionEvent->{this.setResultImage(ImageEditor.divide(this.getFirstSourceImage(), this.getSecondSourceImage()));});
        secondAddSecondMenuItem.addActionListener(actionEvent->{this.setResultImage(ImageEditor.add(this.getSecondSourceImage(), this.getFirstSourceImage()));});
        secondSubstractSecondMenuItem.addActionListener(actionEvent->{this.setResultImage(ImageEditor.substract(this.getSecondSourceImage(), this.getFirstSourceImage()));});
        secondMultiplySecondMenuItem.addActionListener(actionEvent->{this.setResultImage(ImageEditor.multiply(this.getSecondSourceImage(), this.getFirstSourceImage()));});
        secondDivideSecondMenuItem.addActionListener(actionEvent->{this.setResultImage(ImageEditor.divide(this.getSecondSourceImage(), this.getFirstSourceImage()));});
       
        //SETTING COLORS
        firstSourceImageSlotPanel.setBackground(Color.LIGHT_GRAY);
        firstSourceImageBorderPanel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(frame.getBackground(), 10), BorderFactory.createLineBorder(Color.WHITE, 5)));
        secondSourceImageSlotPanel.setBackground(Color.LIGHT_GRAY);
        secondSourceImageBorderPanel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(frame.getBackground(), 10), BorderFactory.createLineBorder(Color.WHITE, 5)));
        resultImageSlotPanel.setBackground(Color.LIGHT_GRAY);
        resultImageBorderPanel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(frame.getBackground(), 10), BorderFactory.createLineBorder(Color.WHITE, 5)));

        //SETTING ALL IMAGES TO NULL
        this.setFirstSourceImage(null);
        this.setSecondSourceImage(null);
    }

    //ACTION LISTENERS
    private void onLoadFirstSourceImageMenuItemActionPerformed() 
    {
        try
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image file(.png, .bmp, .jpg)", "png", "bmp", "jpg");
            fileChooser.setFileFilter(filter);
            if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
            {
                File file = fileChooser.getSelectedFile();
                BufferedImage image = ImageIO.read(file);
                if((this.getSecondSourceImage() != null) && (image.getWidth() != this.getSecondSourceImage().getWidth()) && (image.getHeight() != this.getSecondSourceImage().getHeight()))
                {
                    JOptionPane.showMessageDialog(JFrame.getFrames()[0], "Both images must be same size");
                }
                else
                {
                    this.setFirstSourceImage(image);
                }
            }
        }
        catch(Exception exception){System.out.println(exception);}
    }

    private void onLoadSecondSourceImageMenuItemActionPerformed() 
    {
        try
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image file(.png, .bmp, .jpg)", "png", "bmp", "jpg");
            fileChooser.setFileFilter(filter);
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
            {
                File file = fileChooser.getSelectedFile();
                BufferedImage image = ImageIO.read(file);
                
                if((this.getFirstSourceImage() != null) && (image.getWidth() != this.getFirstSourceImage().getWidth()) && (image.getHeight() != this.getFirstSourceImage().getHeight()))
                {
                    JOptionPane.showMessageDialog(JFrame.getFrames()[0], "Both images must be same size");
                }
                else
                {
                    this.setSecondSourceImage(image);
                }
            }
        }
        catch(Exception exception){}
    }

    private void onSaveMenuItemActionPerformed() 
    {
        try
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image file(.png)", "png");
            fileChooser.setFileFilter(filter);
            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) 
            {
                File file = fileChooser.getSelectedFile();
                file = new File(file.toString() + ".png");
                BufferedImage image = this.getResultImage();
                ImageIO.write(image, "png", file);
            }
        }
        catch(Exception exception){}
    }

    private void onClearFirstSourceImageMenuItemActionPerformed() 
    {
        this.setFirstSourceImage(null);
    }

    private void onClearSecondSourceImageMenuItemActionPerformed() 
    {
        this.setSecondSourceImage(null);
    }

    private void onSwitchToFirstSourceImageMenuItemActionPerformed() 
    {
        this.setFirstSourceImage(this.getResultImage());
    }

    private void onSwitchToSecondSourceImageMenuItemActionPerformed() 
    {
        this.setSecondSourceImage(this.getResultImage());
    }

    private void onResetMenuItemActionPerformed()
    {
        this.setResultImage(null);
    }

    //OTHER
    private void setFirstSourceImage(BufferedImage imageParameter)
    {
        JLabel label;
        if(imageParameter == null)
        {
            label = new JLabel();
        }
        else
        {
            label = new JLabel(new ImageIcon(imageParameter));
        }
        Container container = this.firstSourceImageLabel.getParent();
        container.removeAll();
        container.add(label);
        container.revalidate();
        container.repaint();
        this.firstSourceImageLabel = label;

        this.setResultImage(null);

        if(imageParameter == null)
        {
            this.EditMenu.setEnabled(false);
        }
        else if(this.getSecondSourceImage() != null)
        {
            this.EditMenu.setEnabled(true);
        }
    }

    private void setSecondSourceImage(BufferedImage imageParameter)
    {
        JLabel label;
        if(imageParameter == null)
        {
            label = new JLabel();
        }
        else
        {
            label = new JLabel(new ImageIcon(imageParameter));
        }
        Container container = this.secondSourceImageLabel.getParent();
        container.removeAll();
        container.add(label);
        container.revalidate();
        container.repaint();
        this.secondSourceImageLabel = label;

        this.setResultImage(null);

        if(imageParameter == null)
        {
            this.EditMenu.setEnabled(false);
        }
        else if(this.getFirstSourceImage() != null)
        {
            this.EditMenu.setEnabled(true);
        }
    }

    private void setResultImage(BufferedImage imageParameter)
    {
        JLabel label;
        if(imageParameter == null)
        {
            label = new JLabel();
        }
        else
        {
            label = new JLabel(new ImageIcon(imageParameter));
        }
        Container container = this.resultImageLabel.getParent();
        container.removeAll();
        container.add(label);
        container.revalidate();
        container.repaint();
        this.resultImageLabel = label;

        if(imageParameter == null)
        {
            this.resultImageMenu.setEnabled(false);
        }
        else
        {
            this.resultImageMenu.setEnabled(true);
        }
    }

    private BufferedImage getFirstSourceImage()
    {
        ImageIcon imageIcon = (ImageIcon)this.firstSourceImageLabel.getIcon();
        if(imageIcon == null)
        {
            return null;
        }
        else
        {
            BufferedImage bufferedImage = (BufferedImage)imageIcon.getImage();
            return bufferedImage;
        }  
    }

    private BufferedImage getSecondSourceImage()
    {
        ImageIcon imageIcon = (ImageIcon)this.secondSourceImageLabel.getIcon();
        if(imageIcon == null)
        {
            return null;
        }
        else
        {
            BufferedImage bufferedImage = (BufferedImage)imageIcon.getImage();
            return bufferedImage;
        }  
    }

    private BufferedImage getResultImage()
    {
        ImageIcon imageIcon = (ImageIcon)this.resultImageLabel.getIcon();
        BufferedImage bufferedImage = (BufferedImage)imageIcon.getImage();
        return bufferedImage;
    }
}
