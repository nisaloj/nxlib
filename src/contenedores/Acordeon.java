/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contenedores;

import botones.Boton;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.JPanel;

/**
 *
 * @author Nelson
 */
public class Acordeon extends JPanel implements MouseListener, ComponentListener {

    private Boton boton_titulo;
    private AcordeonPanel acordeon = new AcordeonPanel();
    int gap = 2;

    public Boton getBotonTitulo() {
        return this.boton_titulo;
    }

    public void setDesplegable(boolean b) {
        boton_titulo.setDesplegable(b);
    }

    public Acordeon(String titulo) {
        boton_titulo = new Boton(titulo);
        boton_titulo.setForeground(Color.black);
        inicializar();
    }

    public Acordeon() {
        inicializar();
    }

    @Override
    public void setBackground(Color c) {
        if (boton_titulo != null) {
            boton_titulo.setBackground(c);
        }
        super.setBackground(c);
        invalidate();
    }

    private void inicializar() {
        boton_titulo.setDesplegable(true);
        add(boton_titulo);
        setBackground(Color.white);
        boton_titulo.addMouseListener(this);

        acordeon.setVisible(false);

        add(acordeon);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);

        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(boton_titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(gap, gap, gap)
                        .addComponent(acordeon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout.Group g = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(boton_titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(acordeon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                );

        layout.setVerticalGroup(
                g
        );
        this.setLayout(layout);
        this.addComponentListener(this);
        //setPreferredSize(new Dimension(200,30));
    }
    
    public void setAltoBoton(int alto_boton) {
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);

        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(boton_titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(acordeon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout.Group g = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        //.addComponent(boton_titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(boton_titulo, javax.swing.GroupLayout.PREFERRED_SIZE, (alto_boton > 0) ? alto_boton : 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(acordeon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                );

        layout.setVerticalGroup(
                g
        );
        this.setLayout(layout);
        this.repaint();
    }

    public AcordeonPanel getAcordeonPanel() {
        return this.acordeon;
    }

    public Acordeon addFilaAcordeon(Acordeon fila) {
        return acordeon.addFilaAcordeon(fila);
    }

    public void addActionListener(ActionListener al) {
        boton_titulo.addActionListener(al);
    }

    public void setTitulo(String titulo) {
        boton_titulo.setText(titulo);
        invalidate();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (boton_titulo.isSelected() == true) {
            acordeon.setVisible(true);
        } else {
            acordeon.setVisible(false);
        }
        repaint();
        //acordeon.setVisible(!acordeon.isVisible());
        //BoxLayout bl = new javax.swing.BoxLayout(getParent(), javax.swing.BoxLayout.Y_AXIS);
        //bl.
        //getParent().setLayout(bl);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        Graphics2D g2 = (Graphics2D)g;
        int stroke = 2;
        g2.setStroke(new BasicStroke(stroke));
        g2.setColor(new Color(0,0,0));
        g2.drawLine(getWidth(), getHeight(), getWidth(), getHeight());
    }
    
    class AcordeonPanel extends JPanel {

        private ArrayList filas;

        public class Fila {

            public String titulo;
            public String descripcion;
            public ArrayList filas = new ArrayList<>();
        }

        public void vaciar() {
            filas = new ArrayList<>();
            this.removeAll();
        }

        public AcordeonPanel() {
            filas = new ArrayList<>();
            setGroupLayout();
        }
        GroupLayout.Group horizontal_group;
        GroupLayout.SequentialGroup vertical_group;
        GroupLayout layout;

        public Acordeon getFilaAcordeon(int pos) {
            return ((Acordeon) filas.get(pos));
        }

        private void setGroupLayout() {
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
        private int alto_fila;

        public Acordeon addFilaAcordeon(String titulo, String id) {
            Acordeon fila = new Acordeon();
            fila.setTitulo(titulo);
            this.add(fila);
            fila.setAltoBoton(alto_fila);
            filas.add(fila);
            horizontal_group.addComponent(fila, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
            vertical_group = vertical_group
                    .addComponent(fila, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
            return fila;
        }

        public Acordeon addFilaAcordeon(Acordeon fila) {
            this.add(fila);
            fila.setAltoBoton(alto_fila);
            filas.add(fila);
            horizontal_group.addComponent(fila, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
            vertical_group = vertical_group
                    .addComponent(fila, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
            return fila;
        }

        public void setAltoBoton(int alto) {
            alto_fila = alto;
            for (Object f : filas) {
                Acordeon fila = (Acordeon) f;
                fila.setAltoBoton(alto);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void componentResized(ComponentEvent e) {
        
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void componentShown(ComponentEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
