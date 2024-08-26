package com.mycompany.supermercado;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David
 */
public class Ejemplo extends javax.swing.JFrame {

    private final ModeloProductos modeloProductos = new ModeloProductos();

    public Ejemplo() {
        initComponents();
        
        modeloProductos.addTableModelListener(this::onModeloProductosAlterado);
        
        tblPrincipal.setModel(modeloProductos);
        tblPrincipal.getColumnModel().getColumn(2).setCellRenderer(new DecimalesRenderer());
        tblPrincipal.getColumnModel().getColumn(3).setCellRenderer(new DecimalesRenderer());
        
        
        btnAgregar.addActionListener(this::onBotonAgregarClicked);
        btnEliminar.addActionListener(this::onBotonEliminarClicked);
    }

    private void onBotonAgregarClicked(ActionEvent evt) {
        Producto producto = new Producto();
        producto.nombre = "Producto";
        producto.cantidad = 1;
        producto.precio = 10.0;

        modeloProductos.agregar(producto);
    }

    private void onBotonEliminarClicked(ActionEvent evt) {
        int index = tblPrincipal.getSelectedRow();
        if (index > -1) {
            modeloProductos.eliminar(index);
        }
    }

    private void onModeloProductosAlterado(TableModelEvent evt) {
        switch (evt.getType()) {
            case TableModelEvent.UPDATE:

                int rowIndex = evt.getFirstRow();
                int colIndex = evt.getColumn();

                if (colIndex == 1) {

                    Producto producto = modeloProductos.getProducto(rowIndex);

                    Integer cantidadIngresada = producto.cantidad;
                    //Integer cantidadIngresada = (Integer) modeloProductos.getValueAt(evt.getFirstRow(), evt.getColumn());

                    if (cantidadIngresada <= 0) {
                        JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a cero");
                        modeloProductos.setValueAt(1, evt.getFirstRow(), 1);
//                        tblPrincipal.getCellEditor().stopCellEditing();
                    }
                }

//            case TableModelEvent.INSERT:
//            case TableModelEvent.DELETE:
                break;
        }

        actualizarTotales();
    }

    private void actualizarTotales() {

        Integer totalCantidad = 0;
        Double totalNeto = 0.0;

        for (int rowIndex = 0; rowIndex < modeloProductos.getRowCount(); rowIndex++) {

            Integer cantidad = (Integer) modeloProductos.getValueAt(rowIndex, 1);
            Double total = (Double) modeloProductos.getValueAt(rowIndex, 3);

            totalCantidad += cantidad;
            totalNeto += total;
        }

        lblTotalArticulos.setText(String.valueOf(totalCantidad));
        lblTotalNeto.setText(String.valueOf(totalNeto));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pnlSuperior = new javax.swing.JPanel();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        pnlCentral = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPrincipal = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblTotalArticulos = new javax.swing.JLabel();
        lblTotalNeto = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlSuperior.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        java.awt.GridBagLayout pnlSuperiorLayout = new java.awt.GridBagLayout();
        pnlSuperiorLayout.columnWidths = new int[] {0, 5, 0, 5, 0};
        pnlSuperiorLayout.rowHeights = new int[] {0, 5, 0, 5, 0};
        pnlSuperior.setLayout(pnlSuperiorLayout);

        btnAgregar.setText("Agregar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlSuperior.add(btnAgregar, gridBagConstraints);

        btnEliminar.setText("Eliminar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlSuperior.add(btnEliminar, gridBagConstraints);

        getContentPane().add(pnlSuperior, java.awt.BorderLayout.PAGE_START);

        pnlCentral.setLayout(new java.awt.BorderLayout());

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

        pnlCentral.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlCentral, java.awt.BorderLayout.CENTER);

        java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
        jPanel1Layout.columnWidths = new int[] {0, 5, 0};
        jPanel1Layout.rowHeights = new int[] {0, 5, 0};
        jPanel1.setLayout(jPanel1Layout);

        jLabel1.setText("Total articulos:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setText("Total neto:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel1.add(jLabel2, gridBagConstraints);

        lblTotalArticulos.setText("0.0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel1.add(lblTotalArticulos, gridBagConstraints);

        lblTotalNeto.setText("0.0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        jPanel1.add(lblTotalNeto, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ejemplo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ejemplo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ejemplo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ejemplo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ejemplo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTotalArticulos;
    private javax.swing.JLabel lblTotalNeto;
    private javax.swing.JPanel pnlCentral;
    private javax.swing.JPanel pnlSuperior;
    private javax.swing.JTable tblPrincipal;
    // End of variables declaration//GEN-END:variables

    private static class Producto {

        public String nombre;
        public Integer cantidad;
        public Double precio;

    }

    private static class ModeloProductos extends AbstractTableModel {

        private final List<Producto> productos = new ArrayList<>();

        @Override
        public int getRowCount() {
            return productos.size();
        }

        @Override
        public int getColumnCount() {
            return 4;
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return String.class;
                case 1:
                    return Integer.class;
                case 2:
                    return Double.class;
                case 3:
                    return Double.class;
            }
            return null;
        }

        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0:
                    return "Nombre";
                case 1:
                    return "Cantidad";
                case 2:
                    return "Precio";
                case 3:
                    return "Total";
            }
            return null;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {

            Producto producto = productos.get(rowIndex);

            switch (columnIndex) {
                case 0:
                    return producto.nombre;
                case 1:
                    return producto.cantidad;
                case 2:
                    return producto.precio;
                case 3:
                    return (producto.cantidad * producto.precio);
            }
            return null;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

            Producto producto = productos.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    producto.nombre = (String) aValue;
                    fireTableCellUpdated(rowIndex, columnIndex);
                    break;
                case 1:
                    producto.cantidad = (Integer) aValue;
                    fireTableCellUpdated(rowIndex, columnIndex);
                    fireTableCellUpdated(rowIndex, 3);
                    break;
                case 2:
                    producto.precio = (Double) aValue;
                    fireTableCellUpdated(rowIndex, columnIndex);
                    fireTableCellUpdated(rowIndex, 3);
                    break;
            }
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex != 3;
        }

        public void agregar(Producto producto) {
            productos.add(producto);
            fireTableRowsInserted(getRowCount(), getRowCount());
        }

        public void eliminar(int index) {
            productos.remove(index);
            fireTableRowsDeleted(index, index);
        }

        public Producto getProducto(int rowIndex) {
            return productos.get(rowIndex);
        }

    }

    private static class DecimalesRenderer extends DefaultTableCellRenderer {

        private final DecimalFormat formatter = new DecimalFormat("#.00");

        public DecimalesRenderer() {
            super.setHorizontalAlignment(RIGHT);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setValue(formatter.format(value));
            return this;
        }

    }
}
