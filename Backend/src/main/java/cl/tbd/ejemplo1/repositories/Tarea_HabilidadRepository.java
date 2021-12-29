package cl.tbd.ejemplo1.repositories;
import java.util.List;
import cl.tbd.ejemplo1.models.Tarea_Habilidad;

public interface Tarea_HabilidadRepository{

    public Tarea_Habilidad createTarea_Habilidad(Tarea_Habilidad tarea_habilidad);
    public List<Tarea_Habilidad> getAllTarea_Habilidad();  
    public Tarea_Habilidad getTarea_Habilidad(Long id);
    public Tarea_Habilidad updateTarea_Habilidad(Tarea_Habilidad tarea_habilidad, Long id);
    public boolean deleteTarea_Habilidad(Long id);
}