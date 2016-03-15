/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contenedores;

import botones.Boton;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JScrollPane;

/**
 *
 * @author Nelson
 */
public class ScrollSideBar extends JScrollPane{
    private SideBar sideBar = new SideBar();
    public ScrollSideBar(){
        
        sideBar.setName("sideBar");
        this.setViewportBorder(null);
        this.setViewportView(sideBar);
        this.setPreferredSize(new Dimension(120,250));
    }
    public SideBar getSideBar()
    {
        return this.sideBar;
    }
    @Override
    public Component add(Component c)
    {
        if(c.getName() != null)
        {
            if(c.getName().equals("sideBar"))
            {
                return super.add(c);
            }
        }
        if(sideBar != null)
            return sideBar.add(c);
        else return null;
    }
}

