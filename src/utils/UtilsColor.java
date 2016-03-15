/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.Color;

/**
 *
 * @author Nelson
 */
public class UtilsColor {
    public UtilsColor(){}
    public Color getMasObscuro(Color c, int intensidad)
    {
        Color res = c;
        intensidad = 255 * intensidad / 100;
        int red = c.getRed();
        int green = c.getGreen();
        int blue = c.getBlue();
        int al = c.getAlpha();
        res = new Color((red > intensidad)? red-intensidad:0,
                        (green > intensidad)? green-intensidad:0,
                        (blue > intensidad)? blue-intensidad:0, al);
        return res;
    }
    public Color getMasClaro(Color c, int intensidad)
    {
        Color res = c;
        intensidad = 255 * intensidad / 100;
        int red = c.getRed();
        int green = c.getGreen();
        int blue = c.getBlue();
        int al = c.getAlpha();
        res = new Color((red < 255)? red+intensidad:255,
                        (green < 255)? green+intensidad:255,
                        (blue < 255)? blue+intensidad:255,al);
        return res;
    }
}
