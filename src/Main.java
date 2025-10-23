import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        inventario inv = inventario.getInstance();
        // Opcional: precarga o no de productos

        SwingUtilities.invokeLater(() -> {


            //ventana_agregar_producto.main(args);
            ventana_ver_productos.main(args);
        });
    }
}