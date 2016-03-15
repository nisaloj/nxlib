/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import renders.CabeceraRender;
import renders.CellRender;

/**
 *
 * @author Nelson
 */
public class Tabla extends JTable{
    
    private Color color_fselected =  new Color(218, 234 , 243);
    private Color color_foreground =   new Color( 0,0,0);
    private Color color_grid = new Color(221, 221, 221);
    private JTableHeader jtableHeader = this.getTableHeader();
    CabeceraRender cr = new CabeceraRender();
    CellRender cellR = new CellRender();
    public Tabla(){

        this.setAutoCreateColumnsFromModel(true);
        this.setAutoscrolls(true);
        /** propiedades para el header */
        jtableHeader = this.getTableHeader();
        jtableHeader.setDefaultRenderer(cr);
        this.setTableHeader(jtableHeader);
        setAltoCabecera(30);
        /** propiedades para las celdas */
        this.setSelectionBackground(color_fselected);
        this.setSelectionForeground(color_foreground);        
        this.setGridColor(color_grid);        
        this.setDefaultRenderer (Object.class, cellR);
        this.setRowHeight(22);
        this.setColoresAlternados(false);
        this.setColoresRows(new Color(254,254,254), new Color(245,245,245));
    }
    public void setAltoCabecera(int alto)
    {        
        cr.setAlto(alto);
    }
    public void setBackgroundCabecera(Color c)
    {
        cr.setBackground(c);
    }
    public void dibujarBordesCabecera(boolean b)
    {
        cr.dibujarBorde(b);
    }
    public void setColoresRows(Color c1, Color c2)
    {
        cellR.setColoresRows(c1, c2);
    }
    public void setColoresAlternados(boolean b)
    {
        cellR.setColoresAlternados(b);
    }
    @Override
    public void setFont(Font f)
    {
        
        if(this.isFontSet() == true)
        {
            cellR.setFont(f);
        }
        super.setFont(f);
    }
    public void setForegroundCabecera(Color c)
    {
        cr.setForeground(c);
    }
    @Override
    public void setForeground(Color c)
    {
        
        if(this.isForegroundSet() == true)
        {
            cellR.setForeground(c);
        }
        super.setForeground(c);
    }
}
