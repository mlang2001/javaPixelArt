import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Screen extends JPanel implements MouseListener, ActionListener
{
	private PixelArt[][] pixel, palat;
	private Color color;
	private JButton clearButton, saveButton;
	private BufferedImage myImage;
	
	public Screen()
	{
		this.setLayout(null);
		color = new Color(0, 0, 0);
		pixel = new PixelArt[16][16];
		palat = new PixelArt[10][4];
		for(int r = 0; r < pixel.length; r++)
		{
			for(int c = 0; c < pixel[r].length; c++)
			{
				pixel[r][c] = new PixelArt(255, 255, 255, c * 40 + 200, r * 40 + 30);
			}
		}
		for(int r = 0; r < palat.length - 1; r++)
		{
			for(int c = 0; c < palat[r].length; c++)
			{
				palat[r][c] = new PixelArt(255 - (c * 63), r * 28, c * 63, c * 40 + 20, r * 40 + 30);
			}
		}
		int r = 9;
		for(int c = 0; c < palat[r].length; c++)
		{
			if(c < 3)
			{
				palat[r][c] = new PixelArt(64 * c + 20, 64 * c + 20, 64 * c + 20, c * 40 + 20, r * 40 + 30);
			}
			if(c == 3)
			{
				palat[r][c] = new PixelArt(255, 255, 255, c * 40 + 20, r * 40 + 30);
			}
		}

		clearButton = new JButton("Clear");
		clearButton.setBounds(30,600,120,50);
		clearButton.addActionListener(this);
		this.add(clearButton);

		saveButton = new JButton("Save");
		saveButton.setBounds(30,550,120,50);
		saveButton.addActionListener(this);
		this.add(saveButton);

		addMouseListener(this);
		
		this.setFocusable(true);
	}

	public Dimension getPreferredSize() 
	{
		//Sets the size of the panel
		return new Dimension(850,700);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for(int r = 0; r < pixel.length; r++)
		{
			for(int c = 0; c < pixel[r].length; c++)
			{
				pixel[r][c].drawMe(g);
			}
		}
		for(int r = 0; r < palat.length; r++)
		{
			for(int c = 0; c < palat[r].length; c++)
			{
				palat[r][c].drawMe(g);
			}
		}

		if (myImage == null)
            myImage = (BufferedImage)(createImage(640, 640));

        Graphics gImage = myImage.createGraphics();
        for(int r = 0; r < pixel.length; r++)
		{
			for(int c = 0; c < pixel[r].length; c++)
			{
				gImage.setColor(pixel[r][c].getColor());
				gImage.fillRect(c * 40, r * 40, 40, 40);
				gImage.setColor(Color.BLACK);
				gImage.drawRect(c * 40, r * 40, 40, 40);
			}
		}
		g.drawImage(myImage, 200, 30, null);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == clearButton)
		{
			for(int r = 0; r < pixel.length; r++)
			{
				for(int c = 0; c < pixel[r].length; c++)
				{
					System.out.println(r + " : " + c);
					pixel[r][c].changeColor(Color.WHITE);
				}
			}
		}
		else if (e.getSource() == saveButton) {
            //Save the image
            if (myImage != null) {
                try {
                    File outputfile = new File("image.png");
                    ImageIO.write(myImage, "png", outputfile);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        }
		repaint();
	}

	public void mousePressed(MouseEvent e) 
	{
		for(int r = 0; r < pixel.length; r++)
		{
			for(int c = 0; c < pixel[r].length; c++)
			{
				if (e.getX() >= 200 + c * 40 && e.getX() <= 240 + c * 40 && e.getY() >= 30 + r * 40 && e.getY() <= 70 + r * 40) 
				{
					System.out.println(r + " : " + c);
					pixel[r][c].changeColor(color);
				}
			}
		}

		for(int r = 0; r < palat.length; r++)
		{
			for(int c = 0; c < palat[r].length; c++)
			{
				if (e.getX() >= 20 + c * 40 && e.getX() <= 60 + c * 40 && e.getY() >= 30 + r * 40 && e.getY() <= 70 + r * 40) 
				{
					System.out.println(r + " : " + c);
					color = palat[r][c].getColor();
				}
			}
		}

		repaint();
    }
 
    public void mouseReleased(MouseEvent e) {}
 
    public void mouseEntered(MouseEvent e) {}
 
    public void mouseExited(MouseEvent e) {}
 
    public void mouseClicked(MouseEvent e) {}
}