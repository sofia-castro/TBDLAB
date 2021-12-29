package cl.tbd.ejemplo1.repositories;
import cl.tbd.ejemplo1.models.Estado_Tarea;
import java.util.List;
public interface Estado_TareaRepository{

    public Estado_Tarea createEstado_Tarea(Estado_Tarea estado_tarea);
    public List<Estado_Tarea> getEstado_Tareas();  
    public Estado_Tarea getEstado_Tarea(Long id);
    public Estado_Tarea updateEstado_Tarea(Estado_Tarea estado_tarea, Long id);
    public boolean deleteEstado_Tarea(Long id);
}