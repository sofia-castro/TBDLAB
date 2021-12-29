package cl.tbd.ejemplo1.repositories;
import cl.tbd.ejemplo1.models.Vol_habilidad;
import java.util.List;

public interface Vol_HabilidadRepository{

    public Vol_habilidad createVol_habilidad(Vol_habilidad Vol_habilidad);
    public List<Vol_habilidad> getAllVol_habilidad();  
    public Vol_habilidad getVol_habilidad(Long id);
    public Vol_habilidad updateVol_habilidad(Vol_habilidad Vol_habilidad, Long id);
    public boolean deleteVol_habilidad(Long id);
}