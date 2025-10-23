import javax.swing.*;
import java.awt.*;

public class ventana_mostrar_producto extends JFrame {

    public ventana_mostrar_producto(int productoId) {
        super("Ver producto");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(236, 236, 236));

        inventario inv = inventario.getInstance();
        producto p = inv.getProductoPorId(productoId);
        if (p == null) {
            JOptionPane.showMessageDialog(this, "Producto no encontrado (ID: " + productoId + ")");
            dispose();
            return;
        }

        // --- Título ---
        JLabel titulo = new JLabel("Ver producto", SwingConstants.CENTER);
        titulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        titulo.setBounds(0, 20, 600, 30);
        add(titulo);

        // --- Panel de detalles ---
        JPanel panelDetalles = new JPanel();
        panelDetalles.setLayout(new GridLayout(5, 2, 10, 10));
        panelDetalles.setBackground(Color.WHITE);
        panelDetalles.setBorder(BorderFactory.createTitledBorder("Detalles"));
        panelDetalles.setBounds(40, 70, 300, 200);

        Font labelFont = new Font("Tahoma", Font.PLAIN, 13);

        panelDetalles.add(new JLabel("Nombre:"));
        JLabel lblNombre = new JLabel(p.getNombre());
        lblNombre.setFont(labelFont);
        panelDetalles.add(lblNombre);

        panelDetalles.add(new JLabel("Precio:"));
        JLabel lblPrecio = new JLabel("$ " + p.getPrecio());
        lblPrecio.setFont(labelFont);
        panelDetalles.add(lblPrecio);

        panelDetalles.add(new JLabel("Stock:"));
        JLabel lblStock = new JLabel(String.valueOf(p.getStock()));
        lblStock.setFont(labelFont);
        panelDetalles.add(lblStock);

        panelDetalles.add(new JLabel("Categoría:"));
        JLabel lblCat = new JLabel(p.getCategoria());
        lblCat.setFont(labelFont);
        panelDetalles.add(lblCat);

        panelDetalles.add(new JLabel("URL Imagen:"));
        JLabel lblUrl = new JLabel(p.getURL_imagen());
        lblUrl.setFont(labelFont);
        panelDetalles.add(lblUrl);

        add(panelDetalles);

        // --- Contenedor de imagen ---
        JPanel panelImagen = new JPanel();
        panelImagen.setLayout(new BorderLayout());
        panelImagen.setBorder(BorderFactory.createTitledBorder("Imagen del producto"));
        panelImagen.setBackground(Color.WHITE);
        panelImagen.setBounds(360, 70, 200, 200);

        JLabel contImagen = new JLabel("", SwingConstants.CENTER);
        panelImagen.add(contImagen, BorderLayout.CENTER);

        String url = p.getURL_imagen();
        if (url != null && !url.isEmpty()) {
            try {
                ImageIcon icono = new ImageIcon(url);
                Image img = icono.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
                contImagen.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                contImagen.setText("Error al cargar");
            }
        } else {
            contImagen.setText("Sin imagen");
        }

        add(panelImagen);

        // --- Botón cerrar ---
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBounds(250, 400, 100, 30);
        btnCerrar.setFocusPainted(false);
        add(btnCerrar);
        btnCerrar.addActionListener(e -> dispose());
    }

    public static void open(int productoId) {
        SwingUtilities.invokeLater(() -> new ventana_mostrar_producto(productoId).setVisible(true));
    }
}
