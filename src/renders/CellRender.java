/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renders;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Nelson
 */
public class CellRender extends DefaultTableCellRenderer {

    private Font normal = new Font( "Arial",Font.PLAIN ,12 );
    private Color color1 = new Color(255,255,255);
    private Color color2 = new Color(249,249,249);
    private boolean coloresAlternados = false;
    
    @Override
    public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column )
    {    
        setEnabled(table == null || table.isEnabled());
        setBackground(Color.white);
        if(table != null) table.setFont(normal);
        
        if(coloresAlternados == true)
        {
            setBackground((row % 2 == 1)? this.color1:this.color2);
        }
        else
        {
            setBackground(this.color1);
        }
        
        super.getTableCellRendererComponent(table, value, selected, focused, row, column);

        return this;
    }
    public void setColoresRows(Color c1, Color c2)
    {
        color1 = c1;
        color2 = c2;
    }
    public void setColoresAlternados(boolean b)
    {
        coloresAlternados = b;
    }
}//--> fin clase