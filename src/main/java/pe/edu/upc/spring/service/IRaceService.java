package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Race;

public interface IRaceService {
	public boolean insertar(Race race);
	public boolean modificar(Race race);
	public void eliminar(int idRace);
	public Optional<Race> listarId(int idRace);
	List<Race> listar();
	List<Race> buscarNombre (String nameRace);
}
