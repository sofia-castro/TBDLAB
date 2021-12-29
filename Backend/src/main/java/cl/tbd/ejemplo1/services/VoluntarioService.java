package cl.tbd.ejemplo1.services;
import cl.tbd.ejemplo1.repositories.VoluntarioRepository;
import cl.tbd.ejemplo1.models.Voluntarios;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
public class VoluntarioService {

    private final VoluntarioRepository voluntarioRepository;
    private final Gson gson;

    VoluntarioService(VoluntarioRepository voluntarioRepository){
        this.gson = new GsonBuilder().create();
        this.voluntarioRepository = voluntarioRepository;
    }

    @GetMapping("/voluntarios/")
    ResponseEntity<String> getVoluntarios(){
        List<Voluntarios> Voluntarioses = voluntarioRepository.getAllVoluntarios() ;
        return new ResponseEntity<>(gson.toJson(Voluntarioses),HttpStatus.OK);
    }

    @GetMapping("/voluntarios/{id}")
    ResponseEntity<String> getVoluntarios(@PathVariable Long id){
        Voluntarios Voluntarios = voluntarioRepository.getVoluntario(id);
        if(Voluntarios != null){
            return new ResponseEntity<>(gson.toJson(Voluntarios),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/voluntarios/create")
    ResponseEntity<String> createVoluntarios(@RequestBody String request){
        Voluntarios VoluntariosOut = gson.fromJson(request,Voluntarios.class);
        if (VoluntariosOut != null){
            VoluntariosOut = voluntarioRepository.createVoluntario(VoluntariosOut);
            return new ResponseEntity<>(gson.toJson(VoluntariosOut),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/voluntarios/{id}")
    ResponseEntity<String> updateVoluntarios(@RequestBody Voluntarios request, @PathVariable Long id){
        Voluntarios VoluntariosOut = voluntarioRepository.getVoluntario(id);
        if(VoluntariosOut != null){
            if(request.getNombre() != null){
                VoluntariosOut.setNombre(request.getNombre());
            }

            if(request.getDisponibilidad() != null){
                VoluntariosOut.setDisponibilidad(request.getDisponibilidad());
            }
            if(request.getDireccion() != null){
                VoluntariosOut.setDireccion(request.getDireccion());
            }
            if(request.getCorreo_electronico() != null){
                VoluntariosOut.setCorreo_electronico(request.getCorreo_electronico());
            }
            if(request.getTelefono() != null){
                VoluntariosOut.setTelefono(request.getTelefono());
            }
            VoluntariosOut = voluntarioRepository.updateVoluntario(VoluntariosOut, id);
            return new ResponseEntity<>(gson.toJson(VoluntariosOut),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/voluntarios/{id}")
    ResponseEntity<String> deleteVoluntarios(@PathVariable Long id){
        if(voluntarioRepository.deleteVoluntario(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    
}
