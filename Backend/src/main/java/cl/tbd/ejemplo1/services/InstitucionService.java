package cl.tbd.ejemplo1.services;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cl.tbd.ejemplo1.repositories.InstitucionRepository;
import cl.tbd.ejemplo1.models.Institucion;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



@CrossOrigin
@RestController
public class InstitucionService {
    
    private final InstitucionRepository institucionRepository;
    private final Gson gson;

    InstitucionService(InstitucionRepository institucionRepository){
        this.gson = new GsonBuilder().create();
        this.institucionRepository = institucionRepository;
    }


     @GetMapping("/instituciones/")
    ResponseEntity<String> getInstituciones(){
        List<Institucion> instituciones = institucionRepository.getInstituciones();
        return ResponseEntity.status(200).body("Estas son las instituc\n" + gson.toJson(instituciones));
    }

    @GetMapping("/instituciones/{id}")
    ResponseEntity<String> getInstitucion(@PathVariable Long id){
        Institucion institucion = institucionRepository.getInstitucion(id);
        if(institucion != null){
            return new ResponseEntity<>(gson.toJson(institucion),HttpStatus.OK);
        }
        return new ResponseEntity<>("No se encontro la institucion objetivo :(\n",HttpStatus.NOT_FOUND);
    }

    @PostMapping("/instituciones/create")
    @ResponseBody
    ResponseEntity<String> createInstitucion(@RequestBody String request){
        Institucion institucionOut = gson.fromJson(request,Institucion.class);
        if (institucionOut != null){
            institucionOut = institucionRepository.createInstitucion(institucionOut);
            return new ResponseEntity<>(gson.toJson(institucionOut),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }



    

    @PutMapping("/instituciones/{id}")
    ResponseEntity<String> updateInstitucion(@RequestBody Institucion request, @PathVariable Long id){
    Institucion institucionOut = institucionRepository.getInstitucion(id);
        if(institucionOut != null){
            if(request.getNombre_institucion() != null){
                institucionOut.setNombre_institucion(request.getNombre_institucion());
            }

            if(request.getDireccion_institucion() != null){
                institucionOut.setDireccion_institucion(request.getDireccion_institucion());
            }

            if(request.getDominio_institucion() != null){
                institucionOut.setDominio_institucion(request.getDominio_institucion());
            }
            if(request.getTelefono() != null){
                institucionOut.setTelefono_institucion (request.getTelefono());
            }
 
            institucionOut = institucionRepository.updateInstitucion(institucionOut, id);
            return new ResponseEntity<>(gson.toJson(institucionOut),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/instituciones/{id}")
    ResponseEntity<String> deleteInstitucion(@PathVariable Long id){
          if(institucionRepository.deleteInstitucion(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
