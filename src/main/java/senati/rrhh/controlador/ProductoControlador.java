package senati.rrhh.controlador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senati.rrhh.modelo.Producto;
import senati.rrhh.servicio.IProductoServicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//http://localhost:8080/rrhh-app
@RequestMapping("rrhh-app")
@CrossOrigin(value = "http://localhost:3000")

public class ProductoControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(ProductoControlador.class);

    @Autowired
    private IProductoServicio productoServicio;

    //http://localhost:8080/rrhh-app/productos
    @GetMapping("/productos")
    public List<Producto> obtenerProducto(){
        var productos = productoServicio.ListarProducto();
        productos.forEach(producto -> logger.info(producto.toString()));
        return productos;
    }
    @PostMapping("/productos")
    public Producto agregarProducto(@RequestBody Producto producto){
        return productoServicio.guardarProducto(producto);
    }
    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> buscarProductoPorId(@PathVariable Integer id){
        Producto producto = productoServicio.buscarProdutoPorId(id);
        if (producto == null) {
            return ResponseEntity.notFound().build();  // Devuelve un 404 si no se encuentra el empleado
        }
        return ResponseEntity.ok(producto);
    }
    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> actualizarProductos(@PathVariable Integer id ,
                                                       @RequestBody Producto productoUpdate){
        Producto producto = productoServicio.buscarProdutoPorId(id);
        producto.setNombre(productoUpdate.getNombre());
        producto.setDescripcion(productoUpdate.getDescripcion());
        producto.setCantidad(productoUpdate.getCantidad());
        producto.setPrecio(productoUpdate.getPrecio());
        producto.setCategoria(productoUpdate.getCategoria());

        productoServicio.guardarProducto(producto);
        return ResponseEntity.ok(producto);
    }

    @DeleteMapping("productos/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarProducto(@PathVariable Integer id){
        Producto producto = productoServicio.buscarProdutoPorId(id);
        productoServicio.eliminarProducto(producto);
        //imprimir mensaje de confirmacion
        Map<String , Boolean> respuesta = new HashMap<>();
        respuesta.put("Producto Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
