package senati.rrhh.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senati.rrhh.modelo.Empleado;
import senati.rrhh.modelo.Producto;
import senati.rrhh.repositorio.EmpleadoRepositorio;
import senati.rrhh.repositorio.ProductoRepositorio;

import java.util.List;

@Service

public class ProductoServicio implements IProductoServicio{

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Override
    public List<Producto> ListarProducto() {
        return productoRepositorio.findAll();
    }

    @Override
    public Producto buscarProdutoPorId(Integer idProducto) {
        Producto producto = productoRepositorio.findById(idProducto).orElse(null);
        return producto;
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        return productoRepositorio.save(producto);
    }

    @Override
    public void eliminarProducto(Producto producto) {
        productoRepositorio.delete(producto);
    }
}
