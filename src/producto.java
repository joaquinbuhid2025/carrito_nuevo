public class producto {
    private static int contador = -1;
    private int id;
    private String nombre;
    private double precio;
    private int stock;
    private String categoria;
    private String URL_imagen;

    public producto(String nombre, double precio, int stock, String categoria, String URL_imagen) {
        this.id = contador++;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
        this.URL_imagen = URL_imagen;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
    public String getCategoria() { return categoria; }
    public String getURL_imagen() { return URL_imagen; }

    public void setPrecio(double precio) { this.precio = precio; }
    public void setStock(int stock) { this.stock = stock; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public void setURL_imagen(String URL_imagen) { this.URL_imagen = URL_imagen; }

}
