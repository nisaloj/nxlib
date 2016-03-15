/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contenedores;

import botones.Boton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import listeners.paginaListener;

/**
 *
 * @author Nelson
 */
public class NavPaginas extends JPanel{
    private Boton btn_paginaAnterior;
    private Boton btn_paginaSiguiente;
    private JLabel label_textoPag;
    private JLabel label_textode;
    private JSpinner spin_paginaActual;
    private JLabel lbl_cantidadPaginas;
    
    private int pagina_actual = 1;
    private int numero_paginas = 1;
    
    private ArrayList paginaListeners = new ArrayList<>();
    
    public void addpaginaListener(paginaListener vl)
    {
        paginaListeners.add(vl);
    }
    private void paginaActual(int nro)
    {
        Iterator<paginaListener> iter2 = paginaListeners.iterator();
        while(iter2.hasNext()){
            paginaListener p = iter2.next();
            p.paginaActual(nro);
        }
    }
    private void numeroPaginas(int nro)
    {
        Iterator<paginaListener> iter2 = paginaListeners.iterator();
        while(iter2.hasNext()){
            paginaListener p = iter2.next();
            p.numeroPaginas(nro);
        }
    }
    
    public NavPaginas()
    {
        this.setPreferredSize(new Dimension(250,40));        
        inicializar();
        setLayout();
        btn_paginaAnterior.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if(pagina_actual > 1)
                {
                    pagina_actual -= 1;
                    setPaginaActual(pagina_actual);
                }
            }
        });
        btn_paginaSiguiente.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if(pagina_actual < numero_paginas)
                {
                    pagina_actual += 1;
                    setPaginaActual(pagina_actual);
                }
            }
        });
    }
    private void inicializar()
    {
        btn_paginaAnterior = new Boton();
        btn_paginaSiguiente = new Boton();
        label_textoPag = new JLabel("Pag.");
        label_textode = new JLabel(" de");
        spin_paginaActual = new JSpinner();
        lbl_cantidadPaginas = new JLabel(""+numero_paginas);        
        btn_paginaAnterior.setBackground(null);
        btn_paginaAnterior.setTipoBoton(constantes.compila.TIPO_BOTON.ICONO_COMPLETO);
        btn_paginaAnterior.setIcono(new ImageIcon(getClass().getResource("/imagenes/appbar.chevrong.left.png")));
        btn_paginaSiguiente.setBackground(null);
        btn_paginaSiguiente.setTipoBoton(constantes.compila.TIPO_BOTON.ICONO_COMPLETO);        
        btn_paginaSiguiente.setIcono(new ImageIcon(getClass().getResource("/imagenes/appbar.chevrong.right.png")));
        spin_paginaActual.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(pagina_actual), Integer.valueOf(1), Integer.valueOf(numero_paginas), Integer.valueOf(1)));
        spin_paginaActual.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                setPaginaActual(Integer.valueOf(spin_paginaActual.getValue().toString()));
                paginaActual(pagina_actual);
            }
        });
        this.add(btn_paginaAnterior);
        this.add(btn_paginaSiguiente);
        this.add(label_textoPag);
        this.add(label_textode);
        this.add(spin_paginaActual);
        this.add(lbl_cantidadPaginas);
    }
    private void setLayout()
    {
        javax.swing.GroupLayout thisLayout = new javax.swing.GroupLayout(this);
        this.setLayout(thisLayout);
        thisLayout.setHorizontalGroup(
            thisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(thisLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(btn_paginaAnterior, 40,40,40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_textoPag)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spin_paginaActual, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_textode, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_cantidadPaginas, javax.swing.GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_paginaSiguiente, 40,40,40)
                .addGap(0, 0, 0))
        );
        thisLayout.setVerticalGroup(
            thisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(thisLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(thisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_paginaSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(thisLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(thisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_textoPag)
                            .addComponent(spin_paginaActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_textode)
                            .addComponent(lbl_cantidadPaginas)))
                    .addComponent(btn_paginaAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );
    }
    public void setNumeroPaginas(int numero_p)
    {
        if(numero_paginas > 0)
        {
            numero_paginas = numero_p;
            if(pagina_actual > numero_paginas) setPaginaActual(numero_paginas);
            spin_paginaActual.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(pagina_actual), Integer.valueOf(1), Integer.valueOf((numero_paginas > 0)? numero_paginas:1), Integer.valueOf(1)));
            lbl_cantidadPaginas.setText(""+numero_paginas);
            numeroPaginas(numero_paginas);
        }
    }
    public void setPaginaActual(int numero_p)
    {
        pagina_actual = numero_p;
        spin_paginaActual.setValue(pagina_actual);        
    }
    public void setForegroundAll(Color c)
    {
        label_textoPag.setForeground(c);
        label_textode.setForeground(c);
        lbl_cantidadPaginas.setForeground(c);
        spin_paginaActual.setForeground(c);
    }
    public void setIconoBotonAtras(Icon icono)
    {
        btn_paginaAnterior.setIcon(icono);
    }
    public void setIconoBotonAdelante(Icon icono)
    {           
        btn_paginaSiguiente.setIcon(icono);
    }
    public void setBackgrundButtons(Color c)
    {
        btn_paginaAnterior.setBackground(c);
        btn_paginaSiguiente.setBackground(c);
    }
    public int getPaginaActual(){return this.pagina_actual;}
    public int getNumeroPaginas(){return this.numero_paginas;}
}
