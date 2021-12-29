package cl.tbd.ejemplo1.repositories;
import java.util.List;
import cl.tbd.ejemplo1.models.Tarea;

public interface TareaRepository{

    public Tarea createTarea(Tarea Tarea);
    public List<Tarea> getTareas();  
    public Tarea getTarea(Long id);
    public Tarea updateTarea(Tarea Tarea, Long id);
    public boolean deleteTarea(Long id);
}