package pe.edu.upc.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Pet;

@Repository
public interface IPetRepository extends JpaRepository<Pet, Integer> {
	@Query("from Pet p where p.namePet like %:namePet%")
	List<Pet> buscarNombre(@Param("namePet") String namePet);
	
	@Query("from Pet p where p.race.nameRace like %:nameRace%")
	List<Pet> buscarRace(@Param("nameRace") String nameRace);
	
	List<Pet> findByBirthDatePet(Date birthDatePet);
}
