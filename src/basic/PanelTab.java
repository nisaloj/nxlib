/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic;

import botones.Boton;
import contenedores.PanelFondo;
import contenedores.VisorPagina;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import renders.Closable;
import renders.PanelTabUI;
import renders.TabUI;

/**
 *
 * @author Nelson
 */
public class PanelTab extends JTabbedPane {
    
    private TabUI closeUI = new TabUI(this);
    private ITabRenderer tabRenderer = new DefaultTabRenderer();
    private boolean closable;
    private boolean adder;
    JInternalFrame component_assigned = new JInternalFrame();
    
    private void inicializar()
    {            
        this.setPreferredSize(new Dimension(300,300));
        setClosable(false);
        setAdder(false);
        //setUI(CustomUI.createUI(this)); 
    }
    public PanelTab() {
        super();
        inicializar();
    }
    public void setComponentAssigned(JInternalFrame c)
    {
        this.component_assigned = c;
    }
    public PanelTab(int tabPlacement) {
        super(tabPlacement);
        inicializar();
    }

    
    public void setDatosAt(int index, String title, Icon icon, boolean closable) {
        this.renderer(title, null, index, closable);
    }
    public void setDatosAt(int index, String title, Icon icon) {
        Component c = this.getTabComponentAt(index);
        if(c instanceof Closable)
        {
            Closable clo = (Closable) c;
            this.renderer(title, icon, index, clo.getClosable());
        }else
        {
            this.renderer(title, icon, index);
        }
    }
    public void setDatosAt(int index, Icon icon) {
        Component c = this.getTabComponentAt(index);
        if(c instanceof Closable)
        {
            Closable clo = (Closable) c;
            this.renderer(clo.getText(), icon, index, clo.getClosable());
        }else
        {
            this.renderer("", icon, index);
        }
    }
    
    public PanelTab(int tabPlacement, int tabLayoutPolicy) {
        super(tabPlacement, tabLayoutPolicy);
        inicializar();
    }

    public ITabRenderer getTabRenderer() {
        return tabRenderer;
    }

    public void setTabRenderer(ITabRenderer tabRenderer) {
        this.tabRenderer = tabRenderer;
    }
    
    public void insertTab(String title, Component component, boolean b, int selected) {
        closPub = b;
        this.insertTab(title, null, component, title, selected);
    }

    @Override
    public void insertTab(String title, Icon icon, Component component, String tip, int index) {
        super.insertTab(title, icon, component, tip, index); //To change body of generated methods, choose Tools | Templates.
        renderer(title, icon, index, closPub);
        closPub = false;
    }
    
    
    @Override
    public void paint(Graphics g)
    {
      super.paint(g);
      if(getClosable() == true)
      {
        this.closeUI.paint(g);
      }
    }
    @Override
    public void addTab(String title, Component component) {
        if(this.getTabCount() <= 1 || getAdder() == false)
        {
            this.addTab(title, null, component, title);
        }
        else
        {
            insertTab(title, component, false, getTabCount()-1);
            setSelectedComponent(component);
        }
    }

    @Override
    public void setTabLayoutPolicy(int tabLayoutPolicy) {
        super.setTabLayoutPolicy(tabLayoutPolicy); //To change body of generated methods, choose Tools | Templates.
    }
    boolean closPub = false;
    public void addTab(String title, Component component, boolean closable) {
        if(this.getTabCount() <= 1 || getAdder() == false)
        {            
            closPub = closable;
            this.addTab(title, null, component, null);
        }
        else
        {
            insertTab(title, component, closable, getTabCount()-1);
            setSelectedComponent(component);
        }
    }

    @Override
    public void addTab(String title, Icon icon, Component component) {        
        if(this.getTabCount() <= 1 || getAdder() == false)
        {
            this.addTab(title, icon, component, null);
        }
        else
        {
            insertTab(title, component, false, getTabCount()-1);
            setSelectedComponent(component);
        }
    }
    private boolean agregado = false;
    @Override
    public void addTab(String title, Icon icon, Component component, String tip) {
        super.addTab(title, icon, component, tip);
        int tabIndex = getTabCount() - 1;
        if(tabIndex+1 >= 1 && agregado == false && getAdder() == true)
        {
            agregado = true;
            Closable cl = new Closable();
            addTab("+", cl ,false);
            getModel().addChangeListener(new ChangeListener() {
                private boolean ignore = false;

                @Override
                public void stateChanged(ChangeEvent e) {
                    if (!ignore && getTabCount() > 1) {
                        ignore = true;
                        try {
                            int selected = getSelectedIndex();
                            String title = getTitleAt(selected);
                            if ("+".equals(title)) {
                                VisorPagina pane = new VisorPagina();
                                pane.add(component_assigned);
                                pane.setBackground(Color.white);
                                insertTab("Nueva Ventana", pane, true, selected);
                                setSelectedComponent(pane);
                            }
                        } finally {
                            ignore = false;
                        }
                    }
                }
            });
        }
        this.renderer(title, icon, tabIndex, closPub);
    }
    private void renderer(String title, Icon icon, int index, boolean b)
    {        
        Component tab = tabRenderer.getTabRendererComponent(this, title, icon, index, b);        
        closPub = false;
        super.setTabComponentAt(index, tab);
    }
    private void renderer(String title, Icon icon, int index)
    {        
        Component tab = tabRenderer.getTabRendererComponent(this, title, icon, index);
        this.setTabComponentAt(index, tab);
    }
    public void setClosable(boolean b)
    {        
        closeUI.setEnabled(b);
        closable = b;
        repaint();
    }
    public boolean getClosable(){return closable;}
    public void setAdder(boolean b){
        adder = b;
    }
    public boolean getAdder(){return this.adder;}
}

    interface ITabRenderer {

        //public Component getTabRendererComponent(JTabbedPane tabbedPane, String text, Icon icon, int tabIndex);
        public Component getTabRendererComponent(JTabbedPane tabbedPane, String text, Icon icon, int tabIndex, boolean b);
        public Component getTabRendererComponent(JTabbedPane tabbedPane, String text, Icon icon, int tabIndex);
    }

    abstract class AbstractTabRenderer implements ITabRenderer {

        private String prototypeText = "";
        Icon prototypeIcon = UIManager.getIcon("OptionPane.informationIcon");
        private int horizontalTextAlignment = SwingConstants.LEFT;
        private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

        public AbstractTabRenderer() {
            super();
        }

        public void setPrototypeText(String text) {
            String oldText = this.prototypeText;
            this.prototypeText = text;
            firePropertyChange("prototypeText", oldText, text);
        }

        public String getPrototypeText() {
            return prototypeText;
        }

        public Icon getPrototypeIcon() {
            return prototypeIcon;
        }

        public void setPrototypeIcon(Icon icon) {
            Icon oldIcon = this.prototypeIcon;
            this.prototypeIcon = icon;
            firePropertyChange("prototypeIcon", oldIcon, icon);
        }

        public int getHorizontalTextAlignment() {
            return horizontalTextAlignment;
        }

        public void setHorizontalTextAlignment(int horizontalTextAlignment) {
            this.horizontalTextAlignment = horizontalTextAlignment;
        }

        public PropertyChangeListener[] getPropertyChangeListeners() {
            return propertyChangeSupport.getPropertyChangeListeners();
        }

        public PropertyChangeListener[] getPropertyChangeListeners(String propertyName) {
            return propertyChangeSupport.getPropertyChangeListeners(propertyName);
        }

        public void addPropertyChangeListener(PropertyChangeListener listener) {
            propertyChangeSupport.addPropertyChangeListener(listener);
        }

        public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
            propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
        }

        protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
            PropertyChangeListener[] listeners = getPropertyChangeListeners();
            for (int i = listeners.length - 1; i >= 0; i--) {
                listeners[i].propertyChange(new PropertyChangeEvent(this, propertyName, oldValue, newValue));
            }
        }
    }

    class ETabRenderer extends AbstractTabRenderer
    {
        /*@Override
        public Component getTabRendererComponent(JTabbedPane tabbedPane, String text, Icon icon, int tabIndex) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }*/
        
        @Override
        public Component getTabRendererComponent(JTabbedPane tabbedPane, String text, Icon icon, int tabIndex, boolean b) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        @Override
        public Component getTabRendererComponent(JTabbedPane tabbedPane, String text, Icon icon, int tabIndex) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    class DefaultTabRenderer extends ETabRenderer implements PropertyChangeListener {

        private Component prototypeComponent;

        public DefaultTabRenderer() {
            super();
            prototypeComponent = generateRendererComponent(getPrototypeText(), getPrototypeIcon(), getHorizontalTextAlignment(), false);
            prototypeComponent.setPreferredSize(new Dimension(95,20));
            addPropertyChangeListener(this);
        }
        private Closable datos(String text, Icon icon, int horizontalTabTextAlignmen)
        {
            Closable rendererComponent = new Closable(new GridBagLayout());
            rendererComponent.setOpaque(false);
            rendererComponent.setBackground(null);
            GridBagConstraints c = new GridBagConstraints();
            
            JLabel txt = new JLabel(text, horizontalTabTextAlignmen);
            if(text != null)
            {
                if(text.equals("+"))
                {
                    c.insets = new Insets(0, 1, 0, 0);
                    txt.setFont(new Font(txt.getFont().getFontName(), 1, txt.getFont().getSize()));
                }
                else c.insets = new Insets(0, 0, 0, 12);
            }
            
            c.fill = GridBagConstraints.HORIZONTAL;
            PanelFondo licon = new PanelFondo();
            licon.setImagenFondo(icon);
            licon.setBackground(null);
            licon.setOpaque(false);
            licon.setPreferredSize(new Dimension(13,13));
            if(((text != null)? (!text.equals("+")):true) == true) rendererComponent.add(licon, c);

            c.gridx = 1;
            c.weightx = 1;
                        
            txt.setHorizontalTextPosition(horizontalTabTextAlignmen);
            txt.setForeground(new Color(95,95,95));
            txt.setOpaque(false);
            rendererComponent.add(txt, c);
            return rendererComponent;
        }
        private Component generateRendererComponent(String text, Icon icon, int horizontalTabTextAlignmen, boolean b) {
           Closable rendererComponent = datos(text, icon, horizontalTabTextAlignmen);
           rendererComponent.setClosable(b);
            return rendererComponent;
        }
        private Component generateRendererComponent(String text, Icon icon, int horizontalTabTextAlignmen) {
            Component rendererComponent = datos(text, icon, horizontalTabTextAlignmen);
            return rendererComponent;
        }
        
        @Override
        public Component getTabRendererComponent(JTabbedPane tabbedPane, String text, Icon icon, int tabIndex, boolean b) {
            Closable rendererComponent = (Closable) generateRendererComponent(text, icon, getHorizontalTextAlignment(), b);
            rendererComponent.setClosable(b);
            rendererComponent.setText(text);
            rendererComponent.setIcon(icon);
            int prototypeWidth = prototypeComponent.getPreferredSize().width;
            int prototypeHeight = prototypeComponent.getPreferredSize().height;
            rendererComponent.setLocation(0,0);
            if(text != null)
            {
                if(text.equals("+"))
                    rendererComponent.setPreferredSize(new Dimension(15, prototypeHeight));
                else
                    rendererComponent.setPreferredSize(new Dimension(prototypeWidth, prototypeHeight));
            }
            else
            {
                rendererComponent.setPreferredSize(new Dimension(prototypeWidth, prototypeHeight));
                
            }
            return rendererComponent;
        }
        @Override
        public Component getTabRendererComponent(JTabbedPane tabbedPane, String text, Icon icon, int tabIndex) {
            Closable rendererComponent = (Closable) generateRendererComponent(text, icon, getHorizontalTextAlignment());
            rendererComponent.setText(text);
            rendererComponent.setIcon(icon);
            int prototypeWidth = prototypeComponent.getPreferredSize().width;
            int prototypeHeight = prototypeComponent.getPreferredSize().height;
            if(text != null)
            {
                if(text.equals("+"))
                    rendererComponent.setPreferredSize(new Dimension(15, prototypeHeight));
                else
                    rendererComponent.setPreferredSize(new Dimension(prototypeWidth, prototypeHeight));
            }
            else
            {
                rendererComponent.setPreferredSize(new Dimension(prototypeWidth, prototypeHeight));
                
            }
            return rendererComponent;
        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            String propertyName = evt.getPropertyName();
            if ("prototypeText".equals(propertyName) || "prototypeIcon".equals(propertyName)) {
                
                this.prototypeComponent = generateRendererComponent(getPrototypeText(), getPrototypeIcon(), getHorizontalTextAlignment(), false);
            }
        }
    }