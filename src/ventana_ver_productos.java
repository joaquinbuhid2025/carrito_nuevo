import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.AbstractCellEditor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class ventana_ver_productos {
    public static void main(String[] args) {
        inventario inv = inventario.getInstance();
        JFrame ventana = new JFrame("Ver productos");
        ventana.setSize(1080, 1000);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(null);

        JLabel titulo = new JLabel("Ver productos");
        titulo.setBounds(440,60,200,30);
        titulo.setFont(new Font ("",Font.PLAIN,20));
        ventana.add(titulo);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(100, 60, 120, 30);
        ventana.add(btnActualizar);

        // Obtener y ordenar productos por nombre
        java.util.List<producto> productos = new ArrayList<>(inv.getProductos());
        productos.sort(Comparator.comparing(producto::getNombre, String.CASE_INSENSITIVE_ORDER));

        // Definir columnas con acciones
        String[] columnas = {"ID", "Nombre", "Precio", "Stock", "Categoria", "URL_imagen", "Editar", "Borrar", "Ver Producto"};

        // Crear modelo y llenarlo
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Habilitar edición solo en columnas de acción (Editar=6, Borrar=7, Ver=8)
                return column == 6 || column == 7 || column == 8;
            }
        };
        for (producto p : productos) {
            Object[] fila = { p.getId(), p.getNombre(), p.getPrecio(), p.getStock(), p.getCategoria(), p.getURL_imagen(), "Editar", "Borrar", "Ver Producto" };
            modelo.addRow(fila);
        }

        JTable tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(100, 100, 800, 600);
        ventana.add(scroll);

        JButton btnAgregar = new JButton("Agregar producto");
        btnAgregar.setBounds(100, 720, 160, 30);
        ventana.add(btnAgregar);
        btnAgregar.addActionListener(e -> ventana_agregar_producto.main(new String[]{}));

        Runnable reload = () -> {
            java.util.List<producto> nuevos = new ArrayList<>(inv.getProductos());
            nuevos.sort(Comparator.comparing(producto::getNombre, String.CASE_INSENSITIVE_ORDER));
            modelo.setRowCount(0);
            for (producto p : nuevos) {
                Object[] fila = { p.getId(), p.getNombre(), p.getPrecio(), p.getStock(), p.getCategoria(), p.getURL_imagen(), "Editar", "Borrar", "Ver Producto" };
                modelo.addRow(fila);
            }
        };
        btnActualizar.addActionListener(e -> reload.run());

        // Renderers y editores para botones
        class ButtonRenderer extends JButton implements TableCellRenderer {
            public ButtonRenderer(String text) { setText(text); setOpaque(true); }
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (value != null) setText(value.toString());
                if (isSelected) {
                    setForeground(table.getSelectionForeground());
                    setBackground(table.getSelectionBackground());
                } else {
                    setForeground(table.getForeground());
                    setBackground(UIManager.getColor("Button.background"));
                }
                return this;
            }
        }

        class ButtonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
            private JButton button;
            private int currentRow;
            private final int actionColumn; // 6=EDIT, 7=DELETE, 8=VIEW

            public ButtonEditor(String text, int actionColumn) {
                this.button = new JButton(text);
                this.actionColumn = actionColumn;
                this.button.addActionListener(this);
            }

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                this.currentRow = row;
                return button;
            }

            @Override
            public Object getCellEditorValue() { return null; }

            @Override
            public void actionPerformed(ActionEvent e) {
                int id = (int) modelo.getValueAt(currentRow, 0);
                if (actionColumn == 6) {
                    ventana_editar_producto.open(id);
                } else if (actionColumn == 7) {
                    int r = JOptionPane.showConfirmDialog(ventana, "¿Borrar producto ID " + id + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
                    if (r == JOptionPane.YES_OPTION) {
                        inv.borrarProductoPorId(id);
                        reload.run();
                    }
                } else if (actionColumn == 8) {
                    ventana_mostrar_producto.open(id);
                }
                fireEditingStopped();
            }
        }

        // Asignar columnas de botones (Editar=6, Borrar=7, Ver=8)
        tabla.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer("Editar"));
        tabla.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor("Editar", 6));

        tabla.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer("Borrar"));
        tabla.getColumnModel().getColumn(7).setCellEditor(new ButtonEditor("Borrar", 7));

        tabla.getColumnModel().getColumn(8).setCellRenderer(new ButtonRenderer("Ver Producto"));
        tabla.getColumnModel().getColumn(8).setCellEditor(new ButtonEditor("Ver Producto", 8));
        
        ventana.setVisible(true);
    }
}
