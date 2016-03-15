/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contenedores;

import java.beans.BeanDescriptor;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;

/**
 *
 * @author Nelson
 */
public class ScrollSideBarBeanInfo {

    //beanDescriptor.setValue("containerDelegate", "getSideBar");

    public BeanDescriptor getBeanDescriptor() {
        BeanDescriptor returnDescriptor = new BeanDescriptor(ScrollSideBar.class);
        returnDescriptor.setValue("containerDelegate", "getSideBar");
        return returnDescriptor;
    }
}
