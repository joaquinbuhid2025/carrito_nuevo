import java.util.ArrayList;
public class inventario {
    private ArrayList<producto> productos;

    public inventario() {
        productos = new ArrayList<>();
        
        productos.add(new producto("Producto 1", 10.0, 10, "Categoria 1", ""));
        productos.add(new producto("Producto 2", 20.0, 20, "Categoria 2", ""));
        productos.add(new producto("Producto 3", 30.0, 30, "Categoria 3", ""));
    }

    public void mostrarProductos() {
        for (producto producto : productos) {
            System.out.println(producto);
        }
    }
    
    public void buscarProducto(String nombre) {
        for (producto producto : productos) {
            if (producto.getNombre() == nombre) {
                System.out.println(producto);
                return;
            }
        }
        System.out.println("Producto no encontrado");
    }

    public void agregar_producto(producto producto){
        productos.add(producto);
    }

    public ArrayList<producto> getProductos() { return productos; }
}