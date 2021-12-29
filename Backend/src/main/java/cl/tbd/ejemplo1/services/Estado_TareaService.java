package cl.tbd.ejemplo1.services;
import cl.tbd.ejemplo1.repositories.Estado_TareaRepository;
import cl.tbd.ejemplo1.models.Estado_Tarea;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
public class Estado_TareaService {
    private final Estado_TareaRepository estado_tareaRepository;

    Estado_TareaService(Estado_TareaRepository estado_tareaRepository){
        this.estado_tareaRepository = estado_tareaRepository;
    }

    @PostMapping("/estado_tareas")

    @ResponseBody
    public Estado_Tarea createEstado_Tarea(Estado_Tarea estado_tarea){
        Estado_Tarea estado_tareaOut = estado_tareaRepository.createEstado_Tarea(estado_tarea);
        return estado_tareaOut;
    }

    @GetMapping("/estado_tareas")
    public List<Estado_Tarea> getEstado_Tareas() {
        return estado_tareaRepository.getEstado_Tareas();
    }

    @GetMapping("/estado_tareas/{id}")
    public Estado_Tarea getEstado_Tarea(@PathVariable Long id) {
        return estado_tareaRepository.getEstado_Tarea(id);
    }

    @PutMapping("/estado_tareas/{id}")
    public Estado_Tarea updateEstado_Tarea(@RequestBody Estado_Tarea request, @PathVariable Long id ){
        Estado_Tarea update = estado_tareaRepository.getEstado_Tarea(id);
        update.setEstado(request.getEstado());
        return estado_tareaRepository.updateEstado_Tarea(update,id);
    }

    @DeleteMapping("/estado_tareas/{id}")
    public boolean deleteEstado_Tarea(@PathVariable Long id){
        return estado_tareaRepository.deleteEstado_Tarea(id);
    }
}
