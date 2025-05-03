package senati.rrhh.servicio;

import senati.rrhh.modelo.Empleado;
import senati.rrhh.modelo.Producto;

import java.util.List;

public interface IProductoServicio {
    public List<Producto> ListarProducto();
    public Producto buscarProdutoPorId(Integer idProducto);
    public Producto guardarProducto(Producto producto);
    public void eliminarProducto(Producto producto);
}
