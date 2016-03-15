/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.swing.JInternalFrame;
import renders.IFrameUI;

/**
 *
 * @author Nelson
 */
public class UtilsControls {
    
    public static void JIFUpdateUI(JInternalFrame ji)
    {
        ji.setUI(IFrameUI.createUI(ji));
    }
}
