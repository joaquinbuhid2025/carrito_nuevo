import java.util.ArrayList;
public class inventario {
    private static final inventario INSTANCE = new inventario();
    private ArrayList<producto> productos;

    private inventario() {
        productos = new ArrayList<>();
        
        productos.add(new producto("Producto 1", 10.0, 10, "Categoria 1", ""));
        productos.add(new producto("Producto 2", 20.0, 20, "Categoria 2", ""));
        productos.add(new producto("Producto 3", 30.0, 30, "Categoria 3", ""));
    }

    public static inventario getInstance() { return INSTANCE; }

    public void mostrarProductos() {
        for (producto producto : productos) {
            System.out.println(producto);
        }
    }
    
    public void buscarProducto(String nombre) {
        for (producto producto : productos) {
            if (producto.getNombre().equals(nombre)) {
                System.out.println(producto);
                return;
            }
        }
        System.out.println("Producto no encontrado");
    }

    public void agregar_producto(producto producto){
        productos.add(producto);
    }

    public int mostrar_cantidad(){
        return productos.size();
    }

    public ArrayList<producto> getProductos() { return productos; }

    public producto getProductoPorId(int id) {
        for (producto p : productos) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public boolean borrarProductoPorId(int id) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId() == id) {
                productos.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean actualizarProducto(int id, String nombre, double precio, int stock, String categoria, String urlImagen) {
        producto p = getProductoPorId(id);
        if (p == null) return false;
        if (nombre != null) p.setNombre(nombre);
        p.setPrecio(precio);
        p.setStock(stock);
        if (categoria != null) p.setCategoria(categoria);
        p.setURL_imagen(urlImagen);
        return true;
    }
}