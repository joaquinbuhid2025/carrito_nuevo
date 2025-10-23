import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
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

        // Definir columnas
        String[] columnas = {"ID", "Nombre", "Precio", "Stock", "Categoria", "URL_imagen"};

        // Crear modelo y llenarlo
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        for (producto p : productos) {
            Object[] fila = { p.getId(), p.getNombre(), p.getPrecio(), p.getStock(), p.getCategoria(), p.getURL_imagen() };
            modelo.addRow(fila);
        }

        JTable tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(100, 100, 800, 600);
        ventana.add(scroll);

        Runnable reload = () -> {
            java.util.List<producto> nuevos = new ArrayList<>(inv.getProductos());
            nuevos.sort(Comparator.comparing(producto::getNombre, String.CASE_INSENSITIVE_ORDER));
            modelo.setRowCount(0);
            for (producto p : nuevos) {
                Object[] fila = { p.getId(), p.getNombre(), p.getPrecio(), p.getStock(), p.getCategoria(), p.getURL_imagen() };
                modelo.addRow(fila);
            }
        };
        btnActualizar.addActionListener(e -> reload.run());

        ventana.setVisible(true);
    }
}
