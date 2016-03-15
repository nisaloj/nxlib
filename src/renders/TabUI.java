/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renders;

import basic.PanelTab;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JTabbedPane;
import static javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT;
import static javax.swing.SwingConstants.BOTTOM;
import static javax.swing.SwingConstants.TOP;

/**
 *
 * @author Nelson
 */
public class TabUI implements MouseListener, MouseMotionListener {

    private int closeX = 0;
    private int closeY = 0;
    private int meX = 0;
    private int meY = 0;
    private Rectangle rectangle = new Rectangle(0, 0, 8, 8);
    private PanelTab tabbedPane;
    private int selectedTab;
    private boolean sobreBoton = false;
    private boolean pressedBoton = false;
    private boolean enabled = true;
    private TabUI() {
    }

    public TabUI(PanelTab pane) {
        this.tabbedPane = pane;
        this.tabbedPane.addMouseMotionListener(this);
        this.tabbedPane.addMouseListener(this);
    }
    public void setEnabled(boolean b)
    {
        enabled = b;
    }
    public boolean getEnabled()
    {
        return this.enabled;
    }
    public void mouseEntered(MouseEvent me) {
    }

    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
        pressedBoton = true;
        this.tabbedPane.repaint();
    }

    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mouseDragged(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        pressedBoton = false;
        sobreBoton = sobreBoton(me.getX(), me.getY());
        if (sobreBoton == true && activar(selectedTab)) {
            boolean aux = false;
            if(selectedTab < this.tabbedPane.getTabCount() && this.tabbedPane.getTabCount() >= 1)
            {
                Component com = this.tabbedPane.getTabComponentAt(selectedTab);
                Closable cm = (Closable)com;
                aux = (cm == null)? false:cm.getClosable();
            }
            if ((this.selectedTab > -1 && selectedTab != 0 && aux == true)) {
                this.tabbedPane.removeTabAt(this.selectedTab);
                if(this.tabbedPane.getTabCount() > 2 )
                {
                    this.tabbedPane.setSelectedIndex(selectedTab-1);
                    selectedTab = 0;
                }
                else
                {
                    this.tabbedPane.setSelectedIndex(0);
                    selectedTab = 0;
                }
            }
        }
        this.tabbedPane.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        this.meX = me.getX();
        this.meY = me.getY();
        sobreBoton = sobreBoton(this.meX, this.meY);

        controlCursor();
        this.tabbedPane.repaint();
    }

    private void controlCursor() {
        boolean aux = false;
        if(selectedTab < this.tabbedPane.getTabCount() && this.tabbedPane.getTabCount() >= 1)
        {
            Component com = this.tabbedPane.getTabComponentAt(selectedTab);
            Closable cm = (Closable)com;
            aux = (cm == null)? false:cm.getClosable();
        }
        if (sobreBoton == true && aux == true && activar(selectedTab)) {
            this.tabbedPane.setCursor(new Cursor(12));
            /*if (this.selectedTab > -1) {
                this.tabbedPane.setToolTipTextAt(this.selectedTab, "Cerrar " + this.tabbedPane.getTitleAt(this.selectedTab));
            }*/
        } else {
            this.tabbedPane.setCursor(new Cursor(0));
            /*if (this.selectedTab > -1 && this.tabbedPane.getTabCount() > 0) {
                this.tabbedPane.setToolTipTextAt(this.selectedTab, "");
            }*/
        }
    }
    private boolean activar(int tab)
    {
        Rectangle bn = this.tabbedPane.getBoundsAt(tab);        
        int tam = (bn.x+bn.width)+22;
        if(this.tabbedPane.getTabLayoutPolicy() == SCROLL_TAB_LAYOUT)
        {
            if(this.tabbedPane.getTabPlacement() == TOP || this.tabbedPane.getTabPlacement() == BOTTOM)
            {
                if(tam >= this.tabbedPane.getWidth())
                {
                    return false;
                }
            }
        }
        return true;
    }
    public void paint(Graphics g) {
        int tabCount = this.tabbedPane.getTabCount();
        for (int j = 0; j < tabCount; j++) {
            
            
            boolean aux = false;
            if(j < this.tabbedPane.getTabCount())
            {
                Closable cm = (Closable) this.tabbedPane.getTabComponentAt(j);
                aux = cm.getClosable();
            }
            if (aux == true && activar(j)) {
                int x = this.tabbedPane.getBoundsAt(j).x + this.tabbedPane.getBoundsAt(j).width - 14;
                int y = this.tabbedPane.getBoundsAt(j).y+((this.tabbedPane.getBoundsAt(j).height) / 2)-3;
                if (selectedTab == j) 
                {                    
                    if (sobreBoton == true) {
                        if(pressedBoton == true)
                            drawClose(g, x, y, 2);
                        else
                            drawClose(g, x, y, 1);
                    } else {
                        drawClose(g, x, y, 0);
                    }
                }
                else {
                    drawClose(g, x, y, 0);
                }
            }
        }
    }

    private boolean sobreBoton(int x, int y) {
        boolean b = false;
        if(enabled == true)
        {
            int tabCount = this.tabbedPane.getTabCount();
            for (int j = 0; j < tabCount; j++) {
                if (this.tabbedPane.getBoundsAt(j).contains(x, y)) {
                    this.selectedTab = j;
                    Rectangle rect = new Rectangle();
                    rect.x = (this.tabbedPane.getBoundsAt(j).x + this.tabbedPane.getBoundsAt(j).width - 17);
                    rect.y = this.tabbedPane.getBoundsAt(j).y+((this.tabbedPane.getBoundsAt(j).height) / 2)-5;
                    rect.width = 14;
                    rect.height = 14;
                    b = rect.contains(x, y) && activar(j);
                }
            }
        }
        return b;
    }

    private void drawClose(Graphics g, int x, int y, int tipo) {
        Graphics2D g2 = (Graphics2D) g;
        if ((this.tabbedPane != null) && (this.tabbedPane.getTabCount() > 0)) {
            drawColored(g2, x, y, tipo);
        }
    }

    private void drawColored(Graphics2D g2, int x, int y, int tipo) {
        Color col = new Color(125, 125, 125);
        if (tipo == 1) {
            g2.setColor(new Color(226, 108, 90));
            g2.fillOval(x - 4, y - 4, 15, 15);
            col = new Color(245, 245, 245);
        }
        if (tipo == 2) {
            g2.setColor(new Color(196, 85, 65));
            g2.fillOval(x - 4, y - 4, 15, 15);
            col = new Color(245, 245, 245);
        }
        g2.setStroke(new BasicStroke(1.7F));
        g2.setColor(col);
        g2.drawLine(x, y, x + 7, y + 7);
        g2.drawLine(x + 7, y, x, y + 7);
    }
}
