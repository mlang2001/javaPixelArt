import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Dimension;

public class PixelArt
{
	private int r, g, b, x, y;
	private Color color;
	public PixelArt(int r, int g, int b, int x, int y)
	{
		this.r = r;
		this.g = g;
		this.b = b;
		this.x = x;
		this.y = y;
		color = new Color(r, g, b);
	}
	public void drawMe(Graphics graph)
	{
		graph.setColor(color);
		graph.fillRect(x, y, 40, 40);
		graph.setColor(Color.BLACK);
		graph.drawRect(x, y, 40, 40);
	}
	public void changeColor(Color color)
	{
		this.color = color;
	}
	public Color getColor()
	{
		return color;
	}
}