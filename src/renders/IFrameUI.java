/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renders;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import javax.swing.DesktopManager;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.InternalFrameUI;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.Dimension;
import javax.swing.border.AbstractBorder;
/**
 *
 * @author Nelson
 */
public class IFrameUI extends BasicInternalFrameUI {

    public static IFrameUI createUI(JComponent c) {
        return new IFrameUI((JInternalFrame) c);
    }

    public IFrameUI(JInternalFrame b) {
        super(b);
    }

    @Override
    public void installDefaults() {
        super.installDefaults();
        this.frame.setBorder(new AbstractBorder(){
            @Override 
            public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) { 
             
            } 
        });
    }    

    @Override
    protected LayoutManager createLayoutManager() {
        return new BasicInternalFrameUI.InternalFrameLayout() {
            @Override
            public void layoutContainer(Container c) {
                Insets i = IFrameUI.this.frame.getInsets();
                int cx = i.left;
                int cy = 0;
                int cw = IFrameUI.this.frame.getWidth() - i.left - i.right;
                int ch = IFrameUI.this.frame.getHeight() - i.bottom;

                if (getNorthPane() != null) {
                    Dimension size = getNorthPane().getPreferredSize();
                    // Ignore insets when placing the title pane 
                    getNorthPane().setBounds(0, 0, IFrameUI.this.frame.getWidth(), size.height);
                    cy += size.height;
                    ch -= size.height;
                }

                if (getSouthPane() != null) {
                    Dimension size = getSouthPane().getPreferredSize();
                    getSouthPane().setBounds(cx, IFrameUI.this.frame.getHeight() - i.bottom - size.height, cw, size.height);
                    ch -= size.height;
                }

                if (getWestPane() != null) {
                    Dimension size = getWestPane().getPreferredSize();
                    getWestPane().setBounds(cx, cy, size.width, ch);
                    cw -= size.width;
                    cx += size.width;
                }

                if (getEastPane() != null) {
                    Dimension size = getEastPane().getPreferredSize();
                    getEastPane().setBounds(cw - size.width, cy, size.width, ch);
                    cw -= size.width;
                }

                if (IFrameUI.this.frame.getRootPane() != null) {
                    IFrameUI.this.frame.getRootPane().setBounds(cx, cy, cw, ch);
                }
            }
        };
    }

    @Override
    protected JComponent createNorthPane(JInternalFrame w) {
        return null;
    }
}
