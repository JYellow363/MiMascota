package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Pet;
import pe.edu.upc.spring.repository.IPetRepository;
import pe.edu.upc.spring.service.IPetService;

@Service
public class PetServiceImpl implements IPetService {

	@Autowired
	private IPetRepository dPet;

	@Override
	@Transactional
	public boolean insertar(Pet pet) {
		Pet objPet = dPet.save(pet);
		if(objPet == null) return false;
		else return true;
	}

	@Override
	@Transactional
	public boolean modificar(Pet pet) {
		boolean flag =  false;
		try {
			dPet.save(pet);
			flag = true;
		} catch(Exception ex) {
			System.out.println("Ocurrió un error");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idPet) {
		dPet.deleteById(idPet);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Pet> listarId(int idPet) {
		return dPet.findById(idPet);
	}

	@Override
	@Transactional
	public List<Pet> listar() {
		return dPet.findAll();
	}

	@Override
	@Transactional
	public List<Pet> buscarNombre(String namePet) {
		return dPet.buscarNombre(namePet);
	}

	@Override
	@Transactional
	public List<Pet> buscarRace(String nameRace) {
		return dPet.buscarNombre(nameRace);
	}
}
