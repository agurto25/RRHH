package senati.rrhh.controlador;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senati.rrhh.modelo.Clientes;
import senati.rrhh.modelo.Empleado;
import senati.rrhh.servicio.IClienteServicio;
import senati.rrhh.servicio.IEmpleadoServicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//http://localhost:8080/rrhh-app
@RequestMapping("rrhh-app")
@CrossOrigin(value = "http://localhost:3000")

public class ClienteControlador {
    private final Logger logger =
            LoggerFactory.getLogger(ClienteControlador.class);
    @Autowired
    private IClienteServicio clienteServicio;

    //http://localhost:8080/rrhh-app/clientes
    @GetMapping("/clientes")
    public List<Clientes> obtenerClientes(){
        var clientes = clienteServicio.ListarCliente();
        clientes.forEach(cliente -> logger.info(clientes.toString()));
        return clientes;
    }
    @PostMapping("/clientes")
    public Clientes agregarClientes(@RequestBody Clientes clientes){
        return clienteServicio.guardarCliente(clientes);
    }
    @GetMapping("/clientes/{id}")
    public ResponseEntity<Clientes> buscarClientesId(@PathVariable Integer id){
        Clientes clientes = clienteServicio.buscarClientePorId(id);
        if (clientes == null) {
            return ResponseEntity.notFound().build();  // Devuelve un 404 si no se encuentra el empleado
        }
        return ResponseEntity.ok(clientes);
    }
    @PutMapping("/clientes/{id}")
    public ResponseEntity<Clientes> actualizarClientes(@PathVariable Integer id ,
                                                       @RequestBody Clientes clienteUpdate){
        Clientes clientes = clienteServicio.buscarClientePorId(id);
        clientes.setApellidos(clienteUpdate.getApellidos());
        clientes.setNombre(clienteUpdate.getNombre());
        clientes.setEmail(clienteUpdate.getEmail());
        clientes.setDireccion(clienteUpdate.getDireccion());
        clientes.setTelefono(clientes.getTelefono());

        clienteServicio.guardarCliente(clientes);
        return ResponseEntity.ok(clientes);
    }

    @DeleteMapping("clientes/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarCliente(@PathVariable Integer id){
        Clientes clientes = clienteServicio.buscarClientePorId(id);
        clienteServicio.eliminarCliente(clientes);
        //imprimir mensaje de confirmacion
        Map<String , Boolean> respuesta = new HashMap<>();
        respuesta.put("Cliente Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

}
