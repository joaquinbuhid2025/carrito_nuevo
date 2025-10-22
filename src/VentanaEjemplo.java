import javax.swing.*;
import java.awt.*;
import java.io.File;

public class VentanaEjemplo {
    public static void main(String[] args) {
        inventario inventario = new inventario();
        JFrame ventana = new JFrame("Agregar producto");
        ventana.setSize(1080, 1000);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(null);

        JLabel titulo = new JLabel("Agregar producto");
        titulo.setBounds(440,60,200,30);
        titulo.setFont(new Font ("",Font.PLAIN,20));
        ventana.add(titulo);

        JLabel etq_nombre = new JLabel("Nombre del producto");
        etq_nombre.setBounds(100, 200, 200, 30);
        ventana.add(etq_nombre);
        JTextField txtNombre = new JTextField();
        txtNombre.setBounds(440, 200, 150, 25);
        ventana.add(txtNombre);

        JLabel etq_precio = new JLabel("Precio del producto");
        etq_precio.setBounds(100, 340, 200, 30);
        ventana.add(etq_precio);
        JTextField txtprecio = new JTextField();
        txtprecio.setBounds(440, 340, 150, 25);
        ventana.add(txtprecio);

        JLabel etq_categoria = new JLabel("Categoría del producto");
        etq_categoria.setBounds(100, 480, 200, 30);
        ventana.add(etq_categoria);
        JTextField txtcategoria = new JTextField();
        txtcategoria.setBounds(440, 480, 150, 25);
        ventana.add(txtcategoria);

        JLabel etq_stock = new JLabel("Stock inicial");
        etq_stock.setBounds(100, 600, 200, 30);
        ventana.add(etq_stock);
        JTextField txtstock = new JTextField();
        txtstock.setBounds(440, 600, 150, 25);
        ventana.add(txtstock);

        JLabel cont_imagen = new JLabel();
        cont_imagen.setBounds(680, 300, 200, 200);
        ventana.add(cont_imagen);

        final String[] rutaImagen = {null}; // 🔹 variable "global" dentro del main

        JButton boton_imagen = new JButton("Subir imagen");
        boton_imagen.setBounds(720, 520, 120, 30);
        ventana.add(boton_imagen);

        boton_imagen.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Seleccionar una imagen");
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "Imágenes (JPG, PNG, GIF)", "jpg", "jpeg", "png", "gif"
            ));

            int resultado = fileChooser.showOpenDialog(null);

            if (resultado == JFileChooser.APPROVE_OPTION) {
                File archivoSeleccionado = fileChooser.getSelectedFile();
                rutaImagen[0] = archivoSeleccionado.getAbsolutePath(); // 🔹 Guardamos en la variable global

                // Mostrar la imagen seleccionada
                ImageIcon icono = new ImageIcon(rutaImagen[0]);
                Image imagen = icono.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                cont_imagen.setIcon(new ImageIcon(imagen));

                System.out.println("Imagen seleccionada: " + rutaImagen[0]);
            }
        });

        JButton boton_agregar = new JButton("Agregar producto");
        boton_agregar.setBounds(440, 720, 180, 30);
        ventana.add(boton_agregar);

        boton_agregar.addActionListener(e -> {
            double precio = 0;
            int stock = 0;
            try {
                precio = Double.parseDouble(txtprecio.getText());
                stock = Integer.parseInt(txtstock.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(ventana, "Ingrese precio/stock válido");
                return;
            }

            producto nuevoProducto = new producto(
                txtNombre.getText(),
                precio,
                stock,
                txtcategoria.getText(),
                rutaImagen[0]
            );

            JOptionPane.showMessageDialog(ventana, "Producto agregado:\n" +
                    "Nombre: " + nuevoProducto.getNombre() + "\n" +
                    "Precio: " + nuevoProducto.getPrecio() + "\n" +
                    "Categoría: " + nuevoProducto.getCategoria() + "\n" +
                    "Stock: " + nuevoProducto.getStock() + "\n" +
                    "Imagen: " + nuevoProducto.getURL_imagen());
            
            inventario.agregar_producto(nuevoProducto);
        });

        ventana.setVisible(true);
    }
}
