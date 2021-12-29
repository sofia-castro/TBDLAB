package cl.tbd.ejemplo1.repositories;
import java.util.List;
import cl.tbd.ejemplo1.models.Ranking;
public interface RankingRepository{

    public Ranking createRanking(Ranking ranking);
    public List<Ranking> getRankings();  
    public Ranking getRanking(Long id);
    public Ranking updateRanking(Ranking ranking, Long id);
    public boolean deleteRanking(Long id);
}