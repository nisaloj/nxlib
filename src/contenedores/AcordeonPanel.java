/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contenedores;

import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.JPanel;

/**
 *
 * @author Nelson
 */
public class AcordeonPanel extends JPanel{
   private ArrayList filas;
    public class Fila{
        public String titulo;
        public String descripcion;
        public ArrayList filas = new ArrayList<>();
    }
    public void vaciar()
    {
        filas = new ArrayList<>();
        this.removeAll();
    }
    public AcordeonPanel(){
        filas = new ArrayList<>();
        setGroupLayout();
    }
    GroupLayout.Group horizontal_group;
    GroupLayout.SequentialGroup vertical_group;
    GroupLayout layout;
    public FilaAcordeon getFilaAcordeon(int pos)
    {
        return ((FilaAcordeon)filas.get(pos));
    }
    private void setGroupLayout()
    {
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
    public FilaAcordeon addFilaAcordeon(String titulo, String id)
    {
        FilaAcordeon fila = new FilaAcordeon();
        fila.setTitulo(titulo);
        this.add(fila);
        fila.setAltoBoton(alto_fila);
        filas.add(fila);
        horizontal_group.addComponent(fila, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        vertical_group = vertical_group
        .addComponent(fila, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        ;
        return fila;
    }
    public FilaAcordeon addFilaAcordeon(FilaAcordeon fila)
    {
        this.add(fila);
        fila.setAltoBoton(alto_fila);
        filas.add(fila);
        horizontal_group.addComponent(fila, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        vertical_group = vertical_group
        .addComponent(fila, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        ;
        return fila;
    }
    public void setAltoBoton(int alto)
    {
        alto_fila = alto;
        for(Object f : filas)
        {
            FilaAcordeon fila = (FilaAcordeon)f;
            fila.setAltoBoton(alto);
        }
    }
}

