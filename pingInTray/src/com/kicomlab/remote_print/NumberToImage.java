package com.kicomlab.remote_print;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class NumberToImage {

	public Image intToImage(String str_icon) {
		
		String stringOfPingValue = str_icon;
	    JLabel text = new JLabel(stringOfPingValue);
	    text.setSize(26,20);
	    text.setFont(new Font("MONOSPACED", Font.BOLD,25));
	    text.setBackground(Color.RED);
	    BufferedImage image = getImage(text);
	    return  image;
	}
	
	private BufferedImage getImage(JComponent c) {
	    Rectangle region = c.getBounds();
	    BufferedImage image = new BufferedImage(region.width, region.height, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g2d = image.createGraphics();
	    g2d.translate(-region.x, -region.y);
	    g2d.setColor(c.getBackground() );
	    g2d.fillRect(region.x, region.y, region.width, region.height);
	    c.paint(g2d);
	    g2d.dispose();
	    return image;
	    
	}

}

