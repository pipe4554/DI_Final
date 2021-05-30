package dao;

import models.Cliente;

import models.TipoDocumento;
import exceptions.WrongClientCreateException;
import exceptions.WrongOrderDayCreationException;
import java.util.*;

public class ClienteDAO implements DAO<Cliente> {

	List<Cliente> clientes;

	public ClienteDAO() {
		clientes = new ArrayList<>();
		clientes.add(new Cliente.ClienteBuilder(1).setTipoDocumentationn(TipoDocumento.DNI)
				.setDocumentation("123456789H").setNombre("Carlos").setApellido("Pérez").setTelefono("12345")
				.setEmail("carlosperez@gmail.com").setPassword("password").build());
		clientes.add(new Cliente.ClienteBuilder(2).setTipoDocumentationn(TipoDocumento.DNI)
				.setDocumentation("987654321Q").setNombre("Juan").setApellido("Pedro").setTelefono("54321")
				.setEmail("juanpedro@gmail.com").setPassword("juanpedro1243").build());
	}

	public List<Cliente> getAll() {
		return clientes;
	}

	public Cliente get(int cod_cliente) {
		return clientes.stream().filter(c -> c.getCod_cliente() == cod_cliente).findAny().orElse(null);
	}

	public void save(Cliente cliente) throws WrongClientCreateException {

		if (clientes.stream().anyMatch(c -> c.getCod_cliente() == cliente.getCod_cliente())) {
			throw new WrongClientCreateException("Codigo de cliente en uso. No se pudo agregar el cliente");
		} else if (clientes.stream().anyMatch(c -> c.getNombre().equals(cliente.getNombre())
				&& c.getApellido().equals(cliente.getApellido()) && c.getTelefono().equals(cliente.getTelefono()))) {
			throw new WrongClientCreateException("Duplicidad de datos. No se pudo agregar el cliente");
		} else {
			clientes.add(cliente);
		}
	}

	public void delete(int cod_cliente) {
		clientes.remove(clientes.stream().filter(c -> c.getCod_cliente() == cod_cliente).findAny().orElse(null));
	}
}
