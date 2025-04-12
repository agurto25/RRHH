package senati.rrhh.servicio;

import senati.rrhh.modelo.Clientes;
import senati.rrhh.modelo.Empleado;

import java.util.List;

public interface IClienteServicio {
    public List<Clientes> ListarCliente();
    public Clientes buscarClientePorId(Integer idCliente);
    public Clientes guardarCliente(Clientes cliente);
    public void eliminarEmpleado(Clientes cliente);
}
