/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.supermercado;


import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PRACTICA MACRONNECT
 */
public class ArticulosService {

    public boolean registrar(DefaultTableModel tablaArticulos, String nombre, String cantidad, String precio){
        
        if(validar(nombre, cantidad, precio)){
          double total = Math.round((Integer.parseInt(cantidad) * Double.parseDouble(precio))*100)/100d;
          tablaArticulos.addRow(new Object[]{nombre, Integer.parseInt(cantidad), Double.parseDouble(precio), total});
          
          return true;
        }else{
          return false;
        } 
        
    }
    
    public boolean modificar(DefaultTableModel tablaArticulos, JTable jTable1, String nombre, String cantidad, String precio){
        
        if(validar(nombre, cantidad, precio)){
            tablaArticulos.setValueAt(nombre,jTable1.getSelectedRow(), 0);
            tablaArticulos.setValueAt(Integer.parseInt(cantidad),jTable1.getSelectedRow(), 1);
            tablaArticulos.setValueAt(Double.parseDouble(precio),jTable1.getSelectedRow(), 2);
            double total = Integer.parseInt(cantidad) * Double.parseDouble(precio);
            tablaArticulos.setValueAt(total,jTable1.getSelectedRow(), 3);
            
            return true;
        }else{
            return false;
        } 
    }
   
    public double calcularTotal(JTable jTable1){
           double totalGeneral = 0, 
                  t1 = 0;
           
            for (int i = 0; i < jTable1.getRowCount(); i++) {
               t1 = Double.parseDouble(jTable1.getValueAt(i, 3).toString());
               
               totalGeneral += Math.round(t1*100)/100d;
            }
            
           return(totalGeneral);
    }
    
     public int calcularTotalArticulos(JTable jTable1){
           int totalProductos = 0,
                   t2 = 0;
           
            for (int i = 0; i < jTable1.getRowCount(); i++) {
              
               t2 = Integer.parseInt(jTable1.getValueAt(i, 1).toString());
               totalProductos +=  t2;
            }                                               
          
           return(totalProductos);
    }  

     public boolean validarCampos(String cadena, Integer opcion){
         if(opcion == 0){
              return cadena.matches("^([a-zA-Z]+)(\\s[a-zA-Z]+)*$");
         }else if(opcion == 1){
             return cadena.matches("^-?[0-9]+$");
         }else{
              return cadena.matches("^\\d+(\\.\\d+)?$");
         }
     }
     
     public boolean validar (String nombre, String cantidad, String precio){
         
        if(nombre.isEmpty()){
            JOptionPane.showMessageDialog(null, "Favor de agregar el nombre del articulo");
            return false;
        }
        
        if(cantidad.isEmpty()){
            JOptionPane.showMessageDialog(null, "Favor de agregar una cantidad");
            //txtCantidad.requestFocus();
           
            return false;
        }
        
        if(precio.isEmpty()){
            JOptionPane.showMessageDialog(null, "Favor de agregar un precio");
           // txtPrecio.requestFocus();
           
            return false;
        }
        
        if(validarCampos(nombre,0) == false){
            JOptionPane.showMessageDialog(null, "Solo se aceptan letras en el campo nombre");
            return false;
        }
          
        if(validarCampos(cantidad,1) == false){
            JOptionPane.showMessageDialog(null, "Solo se aceptan numeros enteros en el campo cantidad");
            return false;
        }
         
        if(validarCampos(precio,2) == false){
            JOptionPane.showMessageDialog(null, "Solo se aceptan numeros decimales en el campo precio");
            return false;
        }
         
        if(Integer.parseInt(cantidad) <= 0){
            JOptionPane.showMessageDialog(null, "La cantidad de articulos debe ser mayor a 0");
            //txtCantidad.setText("");
            //txtCantidad.requestFocus();
           
            return false;
        }
        
        if(Double.parseDouble(precio) < 0.1){
            JOptionPane.showMessageDialog(null, "El precio del producto debe ser mayor a 0.1");
            //txtPrecio.setText("");
           // txtPrecio.requestFocus();
           
            return false;
        }
  
         return true;
     }
}