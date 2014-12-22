package com.kicomlab.remote_print;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

/**
 * Created by KICOM on 2014-12-06.
 */

public class DrawPanel extends JPanel implements Printable {

    GeneralPath clipShape = new GeneralPath();

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        drawPage(g2);
    }

    public int print(Graphics g, PageFormat pf, int page)
            throws PrinterException
    {
        if (page >= 1) return Printable.NO_SUCH_PAGE;
        Graphics2D g2 = (Graphics2D)g;
        g2.translate(pf.getImageableX(), pf.getImageableY());
        g2.draw(new Rectangle2D.Double(0, 0,
                pf.getImageableWidth(), pf.getImageableHeight()));

        drawPage(g2);
        return Printable.PAGE_EXISTS;
    }

    /**
     This method draws the page both on the screen and the
     printer graphics context.
     @param g2 the graphics context
     */
    public void drawPage(Graphics2D g2)
    {
        Image img1 = Toolkit.getDefaultToolkit().getImage("bg.gif");
        g2.drawImage(img1, 0, 0, 600, 850, this);

        setText(g2, "22-20092213", 10, 474, 100, Font.PLAIN);
        setText(g2, "박동혁", 10, 205, 128, Font.PLAIN);
        setText(g2, "eaeao@naver.com", 10, 376, 128, Font.PLAIN);
        setText(g2, "컴퓨터공학과", 10, 205, 159, Font.PLAIN);
        setText(g2, "20092213", 10, 205, 181, Font.PLAIN);
        setText(g2, "22", 10, 448, 171, Font.PLAIN);

        setText(g2, "91", 10, 276, 211, Font.PLAIN);
        setText(g2, "01", 10, 330, 211, Font.PLAIN);
        setText(g2, "14", 10, 390, 211, Font.PLAIN);
        setText(g2, "양", 10, 433, 211, Font.PLAIN);

        setText(g2, "강원도 철원군 서면 와수 6리 4반 573-5", 10, 205, 242, Font.PLAIN);
        setText(g2, "033-458-0909", 10, 405, 242, Font.PLAIN);
        setText(g2, "경상북도 경산시 하양읍 부호리 경일대학교 7호관 419호", 10, 205, 273, Font.PLAIN);
        setText(g2, "010-4490-3208", 10, 405, 273, Font.PLAIN);

        g2.finalize();
    }

    private void setText(Graphics2D g2d, String txt, int fontsize, int x, int y, int style){
        Font font = new Font("바탕", style, fontsize);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics(font);
        g2d.drawString(txt, x, y);
    }
}


class DocFrame extends JFrame implements MouseListener {

    private DrawPanel canvas;

    public DocFrame() {
        setTitle("StreamPrintServiceTest");
        setSize(620, 900);
        addMouseListener(this);

        Container contentPane = getContentPane();
        canvas = new DrawPanel();
        contentPane.add(canvas, BorderLayout.CENTER);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        PointerInfo a = MouseInfo.getPointerInfo();
        Point point = new Point(a.getLocation());
        SwingUtilities.convertPointFromScreen(point, e.getComponent());
        int x=(int) point.getX() - 13;
        int y=(int) point.getY() - 22;
        System.out.print("X: "+x+"\n");
        System.out.print("Y: "+y+"\n\n");
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public DrawPanel getCanvas(){
        return canvas;
    }
}
