/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renders;

import botones.Boton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.QuadCurve2D;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import utils.UtilsColor;

/**
 *
 * @author Nelson
 */
public class PanelTabUI extends javax.swing.plaf.basic.BasicTabbedPaneUI{
    private static final Insets TAB_INSETS = new Insets(1, 0, 0, 0);
    private UtilsColor utils_color = new UtilsColor();
	/**
	 * The font to use for the selected tab
	 */
	private Font boldFont;
	private FontMetrics boldFontMetrics;
	private Color selectedColor;
	private Color unselectedColor;

	public PanelTabUI(){
            
        }
	public static PanelTabUI createUI(JComponent c)
	{
            return new PanelTabUI();
	}

    @Override
	protected void installDefaults()
	{
		super.installDefaults();
		tabAreaInsets.left = (calculateTabHeight(0, 0, tabPane.getFont().getSize()) / 4) + 1;
		selectedTabPadInsets = new Insets(0, 0, 0, 0);

		selectedColor = Color.WHITE;
		unselectedColor = utils_color.getMasObscuro(tabPane.getBackground(), 3);

		boldFont = tabPane.getFont().deriveFont(Font.BOLD);
		boldFontMetrics = tabPane.getFontMetrics(boldFont);
	}

	// ------------------------------------------------------------------------------------------------------------------
	//  Custom sizing methods
	// ------------------------------------------------------------------------------------------------------------------

    @Override
	public int getTabRunCount(JTabbedPane pane)
	{
		return 1;
	}

    @Override
	protected Insets getContentBorderInsets(int tabPlacement)
	{
		return TAB_INSETS;
	}

        @Override
	protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight)
	{
		int vHeight = fontHeight + 2;
		if (vHeight % 2 == 0)
		{
			vHeight += 1;
		}
		return vHeight+5;
	}

    @Override
	protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics)
	{
		return super.calculateTabWidth(tabPlacement, tabIndex, metrics);
	}

	// ------------------------------------------------------------------------------------------------------------------
	//  Custom painting methods
	// ------------------------------------------------------------------------------------------------------------------


	// ------------------------------------------------------------------------------------------------------------------
	//  Methods that we want to suppress the behaviour of the superclass
	// ------------------------------------------------------------------------------------------------------------------

    @Override
	protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected)
	{
		Polygon shape = new Polygon();

		shape.addPoint(x - (h / 5), y + h);
		shape.addPoint(x + (h / 5), y);
		shape.addPoint(x + w - (h / 5), y);
                
		if (isSelected || (tabIndex == (rects.length - 1)))
		{
			if (isSelected)
			{
				g.setColor(selectedColor);
			}
			else
			{
				g.setColor(unselectedColor);
			}
			shape.addPoint(x + w + (h / 5), y + h);
		}
		else
		{
			g.setColor(unselectedColor);
			shape.addPoint(x + w, y + (h / 2));
			shape.addPoint(x + w - (h / 5), y + h);
		}

		g.fillPolygon(shape);
	}

        @Override
	protected void paintTabBorder(Graphics gr, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected)
	{
                Graphics2D g = (Graphics2D)gr;
		g.setColor(darkShadow);
                int tam = 3;
                int tam2 = 2;
                Point p1 = new Point(x - (h / 5), y + h);
                Point p2 = new Point(x + (h / 5), y);
                Point p3 = new Point(x + w - (h / 5), y);
                Point p4 = new Point(x + w + (h / 5), y + h);
                
                /*g.drawLine(x - (h / 4), y + h, x + (h / 4), y);
		g.drawLine(x + (h / 4), y, x + w - (h / 4), y);
		g.drawLine(x + w - (h / 4), y, x + w + (h / 4), y + h);*/
		g.drawLine(p1.x, p1.y, p2.x-tam2, p2.y+tam);
		g.drawLine(p2.x+tam,p2.y, p3.x-tam,p3.y);
		g.drawLine(p3.x+tam2, p3.y+tam, p4.x, p4.y);
                
                
                QuadCurve2D c1 = new QuadCurve2D.Double(p2.x-tam2, p2.y+tam, p2.x, p2.y, p2.x+tam, p2.y);
                QuadCurve2D c2 = new QuadCurve2D.Double(p3.x-tam, p3.y, p3.x, p3.y, p3.x+tam2, p3.y+tam);
                //QuadCurve2D c3 = new QuadCurve2D.Double(p3p1b.x, p3p1b.y, p1.x, p1.y, p1p2a.x, p1p2a.y);*/
                g.draw(c1);
                g.draw(c2);
	}

    @Override
	protected void paintContentBorderTopEdge(Graphics g, int tabPlacement, int selectedIndex, int x, int y, int w, int h)
	{
		Rectangle selectedRect = selectedIndex < 0 ? null : getTabBounds(selectedIndex, calcRect);
		

	}

	protected void paintContentBorderRightEdge(Graphics g, int tabPlacement, int selectedIndex, int x, int y, int w, int h)
	{

	}

	protected void paintContentBorderLeftEdge(Graphics g, int tabPlacement, int selectedIndex, int x, int y, int w, int h)
	{

	}

	protected void paintContentBorderBottomEdge(Graphics g, int tabPlacement, int selectedIndex, int x, int y, int w, int h)
	{

	}

	protected void paintFocusIndicator(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect, boolean isSelected)
	{
		// Do nothing
	}

        

    @Override
	protected int getTabLabelShiftY(int tabPlacement, int tabIndex, boolean isSelected)
	{
		return 0;
	}
}