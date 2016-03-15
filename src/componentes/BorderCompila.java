/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes;

import java.awt.Color;
import java.awt.Insets;

/**
 *
 * @author Nelson
 */
public class BorderCompila {
    private float thickness = 1.5f;
    private Insets insets = new Insets(1,1,1,1);
    private Color color = new Color(170,170,170);
    private Color focus_color = new Color(86,160,245);
    public BorderCompila(){}
    public BorderCompila(float thick, Insets inset, Color p_color){
        this.thickness = thick;
        this.insets = inset;
        this.color = p_color;
    }
    public BorderCompila(float thick, Insets inset, Color p_color, Color p_fcolor){
        this.thickness = thick;
        this.insets = inset;
        this.color = p_color;
        this.focus_color = p_fcolor;
    }
    public BorderCompila(Insets inset, Color p_color){
        this.insets = inset;
        this.color = p_color;
    }
    public BorderCompila(Insets inset, Color p_color, Color p_fcolor){
        this.insets = inset;
        this.color = p_color;
        this.focus_color = p_fcolor;
    }
    public BorderCompila(Color p_color){
        this.color = p_color;
    }
    public BorderCompila(Color p_color, Color p_fcolor){
        this.color = p_color;
        this.focus_color = p_fcolor;
    }
    public BorderCompila(Insets inset){
        this.insets = inset;
    }
    public BorderCompila(float thick, Insets inset){
        this.thickness = thick;
        this.insets = inset;
    }
    public BorderCompila(float thick, Color p_color, Insets inset){
        this.thickness = thick;
        this.color = p_color;
        this.insets = inset;
    }
    public float getThickness(){return this.thickness;}
    public Insets getInsets(){return this.insets;}
    public Color getColor(){return this.color;}
    public Color getColorFocus(){return this.focus_color;}
    
    public void setThickness(float thick)
    {
        this.thickness = thick;
    }
    public void setInsets(Insets i)
    {
        this.insets = i;
    }
    public void setColor(Color c)
    {
        this.color = c;
    }
    public void setColorFocus(Color c)
    {
        this.focus_color = c;
    }
}
