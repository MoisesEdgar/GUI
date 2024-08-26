package com.mycompany.supermercado;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Articulos extends javax.swing.JFrame {

    private final DefaultTableModel tablaArticulos = new DefaultTableModel();
    DefaultTableCellRenderer valoresNumericosRender = new DefaultTableCellRenderer();
    ArticulosService articulosService = new ArticulosService();
    Integer ir = 0;

    public Articulos() throws ParseException {
        initComponents();

        String encabesados[] = {"Nombre del articulo", "Cantidad", "Precio unitario", "Total"};
        tablaArticulos.setColumnIdentifiers(encabesados);
        tblPrincipal.setModel(tablaArticulos);

        valoresNumericosRender.setHorizontalAlignment(SwingConstants.RIGHT);
        tblPrincipal.getColumnModel().getColumn(1).setCellRenderer(valoresNumericosRender);
        tblPrincipal.getColumnModel().getColumn(2).setCellRenderer(valoresNumericosRender);
        tblPrincipal.getColumnModel().getColumn(3).setCellRenderer(valoresNumericosRender);

        btnAgregar.addActionListener(this::onBotonAgregarClicked);
        btnEliminar.addActionListener(this::onBotonEliminarClicked);
        tablaArticulos.addTableModelListener(this::onModeloProductosAlterado);
 
    }
    
    private void onBotonAgregarClicked(ActionEvent evt) {
        String nombre = getNombre();
        Integer cantidad = getCantidad();
        Double precio = getPrecio();

        if (validarVariables(nombre, cantidad, precio)) {
            if (validarArticulosRepetidos(nombre)) {
                tablaArticulos.addRow(new Object[]{nombre, cantidad, precio, Math.round(cantidad * precio * 100) / 100d});
                calcularTotales();
                limpiar();
            }
        }
    }

    private void onBotonEliminarClicked(ActionEvent evt) {
        int index = tblPrincipal.getSelectedRow();
        if (index > -1) {
            tablaArticulos.removeRow(tblPrincipal.getSelectedRow());
            calcularTotales();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para eliminar");
        }
    }

    private void onModeloProductosAlterado(TableModelEvent evt) {
        String nombre = getNombre();
        Integer cantidad = getCantidad();
        Double precio = getPrecio();

        switch (evt.getType()) {
            case TableModelEvent.UPDATE:
                int rowIndex = evt.getFirstRow();
                int colIndex = evt.getColumn();
                
             if (colIndex == 1 ) { 
                 
                     lblTotalNeto.setText(String.valueOf(tblPrincipal.getValueAt(rowIndex, 1)));
             }
  

              
            //case TableModelEvent.INSERT: 
            //case TableModelEvent.DELETE: 
        }
    }
    
    private String getNombre() {
        String nombre = txtNombre.getText();
        if (txtNombre.getText().isEmpty()) {
            return "";
        } else {
            return nombre;
        }
    }

    private Integer getCantidad() {
        if (txtCantidad.getText().isEmpty()) {
            return 0;
        } else {
            Integer cantidad = Integer.parseInt(txtCantidad.getText());
            return cantidad;
        }
    }

    private Double getPrecio() {
        if (txtPrecio.getText().isEmpty()) {
            return 0.0;
        } else {
            Double precio = Double.parseDouble(txtPrecio.getText());
            return precio;
        }
    }

    private boolean validarVariables(String nombre, Integer cantidad, Double precio) {

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Agregar nombre del articulo");
            txtNombre.requestFocus();
            return false;
        }

        if (txtCantidad.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Agregar la cantidad de articulos");
            txtCantidad.requestFocus();
            return false;
        }

        if (txtPrecio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Agregar el precio del articulo");
            txtPrecio.requestFocus();
            return false;
        }

        if (cantidad <= 0) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a 0");
            txtCantidad.requestFocus();
            txtCantidad.setText("");
            return false;
        }

        if (precio <= 0) {
            JOptionPane.showMessageDialog(this, "El precio debe ser mayor a 0");
            txtPrecio.requestFocus();
            txtPrecio.setText("");
            return false;
        }

        return true;
    }

    private boolean validarArticulosRepetidos(String nombre) {

        for (int i = 0; i < tblPrincipal.getRowCount(); i++) {
            if (nombre.equalsIgnoreCase((String) tblPrincipal.getValueAt(i, 0))) {
                JOptionPane.showMessageDialog(this, "El articulos ya esta registrado");
                txtNombre.requestFocus();
                txtNombre.setText("");
                return false;
            }
        }
        return true;
    }

    private void calcularTotales() {
        Integer totalCantidad = 0;
        Double totalNeto = 0.0;

        for (int i = 0; i < tblPrincipal.getRowCount(); i++) {

            Integer cantidad = (Integer) tblPrincipal.getValueAt(i, 1);
            Double total = (Double) tblPrincipal.getValueAt(i, 3);

            totalCantidad += cantidad;
            totalNeto += Math.round(total * 100) / 100d;
        }

        lblTotalArticulos.setText(String.valueOf(totalCantidad));
        lblTotalNeto.setText(String.valueOf(totalNeto));
    }

    private void limpiar() {
        txtNombre.setText("");
        txtCantidad.setText("");
        txtPrecio.setText("");
        txtNombre.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPrincipal = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblTotalArticulos = new javax.swing.JLabel();
        lblTotalNeto = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        tblPrincipal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblPrincipal);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        java.awt.GridBagLayout jPanel4Layout = new java.awt.GridBagLayout();
        jPanel4Layout.columnWidths = new int[] {0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0};
        jPanel4Layout.rowHeights = new int[] {0, 7, 0, 7, 0, 7, 0, 7, 0};
        jPanel4.setLayout(jPanel4Layout);

        jLabel1.setText("Nombre del articulo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel4.add(jLabel1, gridBagConstraints);

        jLabel2.setText("Cantida:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel4.add(jLabel2, gridBagConstraints);

        jLabel3.setText("Precio:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel4.add(jLabel3, gridBagConstraints);

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 100);
        jPanel4.add(txtNombre, gridBagConstraints);

        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 100);
        jPanel4.add(txtCantidad, gridBagConstraints);

        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 100);
        jPanel4.add(txtPrecio, gridBagConstraints);

        btnAgregar.setText("Agregar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 14;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        jPanel4.add(btnAgregar, gridBagConstraints);

        btnEliminar.setText("Elimimnar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 14;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 3);
        jPanel4.add(btnEliminar, gridBagConstraints);

        getContentPane().add(jPanel4, java.awt.BorderLayout.PAGE_START);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabel4.setText("Total articulos:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 3, 0);
        jPanel5.add(jLabel4, gridBagConstraints);

        jLabel5.setText("Total neto:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel5.add(jLabel5, gridBagConstraints);

        lblTotalArticulos.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel5.add(lblTotalArticulos, gridBagConstraints);

        lblTotalNeto.setText("0.0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 0);
        jPanel5.add(lblTotalNeto, gridBagConstraints);

        getContentPane().add(jPanel5, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        char c = evt.getKeyChar();
        if ((c < 'a' || c > 'z') && ((c < 'A' || c > 'Z'))) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyTyped
        char c = evt.getKeyChar();
        if ((c < '0' || c > '9') && (c != '.')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPrecioKeyTyped

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Articulos().setVisible(true);
                } catch (ParseException ex) {
                    Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTotalArticulos;
    private javax.swing.JLabel lblTotalNeto;
    private javax.swing.JTable tblPrincipal;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
