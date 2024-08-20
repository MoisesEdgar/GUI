/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.supermercado;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PRACTICA MACRONNECT
 */
public class ArticulosService {

    public void registrar(DefaultTableModel tablaArticulos, String nombre, Integer cantidad, Double precio){
          
        if(cantidad < 0){
            JOptionPane.showMessageDialog(null, "La cantidad de articulos debe ser mayor a 0");
            //txtCantidad.setText("");
            //txtCantidad.requestFocus();
            return;
        }
        
        if(precio < 0.1){
            JOptionPane.showMessageDialog(null, "El precio del producto no puede ser menor a 0.1");
            //txtPrecio.setText("");
           // txtPrecio.requestFocus();
            return;
        }
        
        double total = cantidad * precio;
        
       tablaArticulos.addRow(new Object[]{nombre, cantidad, precio,total});
    }
    
    public void modificar(DefaultTableModel tablaArticulos, JTable jTable1, String nombre, Integer cantidad, Double precio){
        tablaArticulos.setValueAt(nombre,jTable1.getSelectedRow(), 0);
        tablaArticulos.setValueAt(cantidad,jTable1.getSelectedRow(), 1);
        tablaArticulos.setValueAt(precio,jTable1.getSelectedRow(), 2);
        double total = cantidad * precio;
        tablaArticulos.setValueAt(total,jTable1.getSelectedRow(), 3);
    }
    
    public double calcularTotal(JTable jTable1){
           double totalGeneral = 0, 
                  t1 = 0;

            for (int i = 0; i < jTable1.getRowCount(); i++) {
               t1 = Double.parseDouble(jTable1.getValueAt(i, 3).toString());
               totalGeneral += t1;
            }
           return(totalGeneral);
    }
    
     public int calcularTotalArticulos(JTable jTable1){
           int totalArticulos = 0,
                   t2 = 0;
           
            for (int i = 0; i < jTable1.getRowCount(); i++) {
              
               t2 = Integer.parseInt(jTable1.getValueAt(i, 1).toString());
               totalArticulos +=  t2;
            }                                               
          
           return(totalArticulos);
    }  
}