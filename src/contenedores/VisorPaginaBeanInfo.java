/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contenedores;

import java.beans.*;

public class VisorPaginaBeanInfo extends SimpleBeanInfo {
  Class beanClass = VisorPagina.class;
  String iconColor16x16Filename = "imagenes/Visor16x.png";
  String iconColor32x32Filename = "imagenes/Visor32x.png";
  String iconMono16x16Filename;
  String iconMono32x32Filename;
  
  public VisorPaginaBeanInfo() {
  }

  @Override
  public PropertyDescriptor[] getPropertyDescriptors() {
    try  {      
      PropertyDescriptor _titulo = new PropertyDescriptor("titulo", beanClass, "getTitulo", "setTitulo");
      
      PropertyDescriptor _opaco = new PropertyDescriptor("ventanaTransparente", beanClass, "isVentanaTransparente", "setVentanaTransparente");
      
      PropertyDescriptor _internalFrame = new PropertyDescriptor("internalFrame", beanClass, "getInternalFrame", "setInternalFrame");
      
      PropertyDescriptor _fondo= new PropertyDescriptor("imagenFondo", beanClass, null, "setImagenFondo");
      
      PropertyDescriptor[] pds = new PropertyDescriptor[] {        
        _titulo,
        _opaco,
        _fondo,
        _internalFrame,
      };
      return pds;
    }
    catch (IntrospectionException ex) {
      ex.printStackTrace();
      return null;
    }
  }
  
  @Override
  public java.awt.Image getIcon(int iconKind) {
    switch (iconKind) {
    case BeanInfo.ICON_COLOR_16x16:
      return iconColor16x16Filename != null ? loadImage(iconColor16x16Filename) : null;
    case BeanInfo.ICON_COLOR_32x32:
      return iconColor32x32Filename != null ? loadImage(iconColor32x32Filename) : null;
    case BeanInfo.ICON_MONO_16x16:
      return iconMono16x16Filename != null ? loadImage(iconMono16x16Filename) : null;
    case BeanInfo.ICON_MONO_32x32:
      return iconMono32x32Filename != null ? loadImage(iconMono32x32Filename) : null;
    }
    return null;
  }
}
