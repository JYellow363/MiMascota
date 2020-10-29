package pe.edu.upc.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "mascotas")
public class Pet implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPet;
	
	@NotEmpty(message = "No puede estar vacío")
	@NotBlank(message = "No puede estar vacío")
	@Column(name = "nombre_raza", length = 60, nullable = false)
	private String namePet;
	
	@NotNull
	@Past(message = "No puedes seleccionar un día que no existe")
	@Column(name = "fecha_nac_mascota")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDatePet;
	
	@ManyToOne
	@JoinColumn(name = "idRace", nullable = false)
	private Race race;

	public int getIdPet() {
		return idPet;
	}

	public void setIdPet(int idPet) {
		this.idPet = idPet;
	}

	public String getNamePet() {
		return namePet;
	}

	public void setNamePet(String namePet) {
		this.namePet = namePet;
	}

	public Date getBirthDatePet() {
		return birthDatePet;
	}

	public void setBirthDatePet(Date birthDatePet) {
		this.birthDatePet = birthDatePet;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}
	
	
}
