package pe.edu.upc.spring.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.spring.model.Pet;
import pe.edu.upc.spring.model.Race;
import pe.edu.upc.spring.service.IPetService;
import pe.edu.upc.spring.service.IRaceService;

@Controller
@RequestMapping("/pet")
public class PetController {
	
	@Autowired
	private IPetService pService;
	@Autowired
	private IRaceService rService;
	
	@RequestMapping("/bienvenido")
	public String iPetBienvenido() {
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irPet(Map<String, Object> model) {
		model.put("listaMascotas", pService.listar());
		return "listPets";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("listaRazas", rService.listar());
		model.addAttribute("pet", new Pet());
		model.addAttribute("race", new Race());
		return "pet";
	}
	
	@RequestMapping("/registrar")
	public String registrar(
			@ModelAttribute @Valid Pet objPet,
			BindingResult binRes,
			Model model) throws ParseException {
		if(binRes.hasErrors()) {
			model.addAttribute("listaRazas", rService.listar());
			return "pet";
		}
		else {
			boolean flag = pService.insertar(objPet);
			if(flag) {
				return "redirect:/pet/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/pet/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(
			@PathVariable int id,
			Model model,
			RedirectAttributes objRedir
			) throws ParseException {
		Optional<Pet> objPet = pService.listarId(id);
		if (objPet == null) {
			objRedir.addAttribute("mensaje", "Ocurrió un error");
			return "redirect:/pet/listar";
		}
		else {
			model.addAttribute("listaRazas", rService.listar());
			if(objPet.isPresent())
				objPet.ifPresent(o -> model.addAttribute("pet", o));
			return "pet";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(
			Map<String, Object> model,
			@RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				pService.eliminar(id);
				model.put("listaMascotas", pService.listar());
			}
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrió un error");
			model.put("listaMascotas", pService.listar());
		}
		return "listPets";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaMascotas", pService.listar());
		return "listPets";
	}
	
	@RequestMapping("/listarId")
	public String listar(
			Map<String, Object> model,
			@ModelAttribute Pet pet)
		throws ParseException {
			pService.listarId(pet.getIdPet());
			return "listPets";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String,
			Object> model,
			@ModelAttribute Pet pet ) throws ParseException {
		List<Pet> listaMascotas;
		pet.setNamePet(pet.getNamePet());
		listaMascotas = pService.buscarNombre(pet.getNamePet());
		
		if (listaMascotas.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		model.put("listaMascotas", listaMascotas);				
		return "buscar";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("pet", new Pet());
		return "buscar";
	}
}


