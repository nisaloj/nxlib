/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contenedores;

import botones.Boton;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.GroupLayout;

/**
 *
 * @author Nelson
 */
public class SideBar extends PanelFondo implements ComponentListener{
   
    public SideBar(){
        setOpaque(true);
        setBorder(null);
        setPreferredSize(new Dimension(120,250));
        setGroupLayout();     
        this.addComponentListener(this);
    }
    GroupLayout.Group horizontal_group;
    GroupLayout.SequentialGroup vertical_group;
    GroupLayout layout;
    private void setGroupLayout()
    {
        layout = new GroupLayout(this); 
        
        horizontal_group = layout.createParallelGroup(GroupLayout.Alignment.LEADING);        
        layout.setHorizontalGroup(horizontal_group);
        
        vertical_group = layout.createSequentialGroup();
        GroupLayout.Group group_padre_v = layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(GroupLayout.Alignment.TRAILING, vertical_group);
        
        layout.setVerticalGroup(
            group_padre_v
        );
        
        this.setLayout(layout);
    }
    
    @Override
    public void setLayout(LayoutManager mgr)
    {
        if(layout != null)
        {
        super.setLayout(layout);
        }
    }
    
    
    @Override
    public Component add(Component c)
    {
        super.add(c); 
        agregar(c);        
        return c;
    }
    @Override
    public void add(Component c, Object o)
    {
        super.add(c, o);
        agregar(c);
    }
    @Override
    public Component add(Component c, int index)
    {
        super.add(c, index);        
        agregar(c);
        return c;
    }
    @Override
    public Component add(String string, Component c)
    {
        super.add(string, c);        
        agregar(c);
        return c;
    }
    @Override
    public void add(Component c, Object o, int index)
    {
        super.add(c, o, index);
        agregar(c);
    }
    private Component agregar(Component c)
    {
       // add(c);
        horizontal_group.addComponent(c, javax.swing.GroupLayout.PREFERRED_SIZE, getWidth(), Short.MAX_VALUE);
        vertical_group.addComponent(c, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        ;
        revalidate();
        return c;       
    }

    @Override
    public void componentResized(ComponentEvent e) {
        
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        
    }

    @Override
    public void componentShown(ComponentEvent e) {
        
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        
    }
    
}