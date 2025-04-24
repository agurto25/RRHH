package senati.rrhh.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senati.rrhh.modelo.Clientes;
import senati.rrhh.repositorio.ClienteRrepositorio;

import java.util.List;
@Service

public class ClienteServicio implements IClienteServicio {
    @Autowired
    private ClienteRrepositorio clienteRrepositorio;

    @Override
    public List<Clientes> ListarCliente() {
        return clienteRrepositorio.findAll();
    }

    @Override
    public Clientes buscarClientePorId(Integer idCliente) {
        Clientes cliente = clienteRrepositorio.findById(idCliente).orElse(null);
        return cliente;
    }

    @Override
    public Clientes guardarCliente(Clientes cliente) {
        return clienteRrepositorio.save(cliente);
    }

    @Override
    public void eliminarCliente(Clientes cliente) {
        clienteRrepositorio.delete(cliente);

    }
}
