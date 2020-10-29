package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Pet;

public interface IPetService {
	public boolean insertar(Pet pet);
	public boolean modificar(Pet pet);
	public void eliminar(int idPet);
	public Optional<Pet> listarId(int idPet);
	List<Pet> listar();
	List<Pet> buscarNombre (String namePet);
	List<Pet> buscarRace (String nameRace);
}
