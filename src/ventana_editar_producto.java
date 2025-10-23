import javax.swing.*;
import java.awt.*;

public class ventana_editar_producto extends JFrame {
    private final inventario inv;
    private final int productoId;

    public ventana_editar_producto(int productoId) {
        super("Editar producto");
        this.inv = inventario.getInstance();
        this.productoId = productoId;
        setSize(600, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        producto p = inv.getProductoPorId(productoId);
        if (p == null) {
            JOptionPane.showMessageDialog(this, "Producto no encontrado (ID: " + productoId + ")");
            dispose();
            return;
        }

        JLabel titulo = new JLabel("Editar producto ID: " + productoId);
        titulo.setBounds(200, 20, 250, 30);
        titulo.setFont(new Font("", Font.PLAIN, 18));
        add(titulo);

        JLabel etqNombre = new JLabel("Nombre");
        etqNombre.setBounds(60, 80, 120, 25);
        add(etqNombre);
        JTextField txtNombre = new JTextField(p.getNombre());
        txtNombre.setBounds(220, 80, 250, 25);
        add(txtNombre);

        JLabel etqPrecio = new JLabel("Precio");
        etqPrecio.setBounds(60, 130, 120, 25);
        add(etqPrecio);
        JTextField txtPrecio = new JTextField(String.valueOf(p.getPrecio()));
        txtPrecio.setBounds(220, 130, 250, 25);
        add(txtPrecio);

        JLabel etqStock = new JLabel("Stock");
        etqStock.setBounds(60, 180, 120, 25);
        add(etqStock);
        JTextField txtStock = new JTextField(String.valueOf(p.getStock()));
        txtStock.setBounds(220, 180, 250, 25);
        add(txtStock);

        JLabel etqCategoria = new JLabel("Categoria");
        etqCategoria.setBounds(60, 230, 120, 25);
        add(etqCategoria);
        JTextField txtCategoria = new JTextField(p.getCategoria());
        txtCategoria.setBounds(220, 230, 250, 25);
        add(txtCategoria);

        JLabel etqUrl = new JLabel("URL imagen");
        etqUrl.setBounds(60, 280, 120, 25);
        add(etqUrl);
        JTextField txtUrl = new JTextField(p.getURL_imagen());
        txtUrl.setBounds(220, 280, 250, 25);
        add(txtUrl);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(220, 340, 120, 30);
        add(btnGuardar);

        btnGuardar.addActionListener(e -> {
            double precio;
            int stock;
            try {
                precio = Double.parseDouble(txtPrecio.getText());
                stock = Integer.parseInt(txtStock.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ingrese precio/stock válido");
                return;
            }

            boolean ok = inv.actualizarProducto(
                productoId,
                txtNombre.getText(),
                precio,
                stock,
                txtCategoria.getText(),
                txtUrl.getText()
            );
            if (ok) {
                JOptionPane.showMessageDialog(this, "Producto actualizado");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo actualizar el producto");
            }
        });
    }

    public static void open(int productoId) {
        SwingUtilities.invokeLater(() -> new ventana_editar_producto(productoId).setVisible(true));
    }
}
