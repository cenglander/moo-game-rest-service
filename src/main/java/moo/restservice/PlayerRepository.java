package moo.restservice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

	List<Player> findByName(String name);
//Första:
//	@Query("SELECT new moo.restservice.PlayerAverage(p.id, avg(r.result) as average) " 
//		+"FROM Result r "
//		+ "JOIN Player p ON r.player=p.id " 
//		+ "GROUP BY Player.id ORDER BY average ASC")
//	List<PlayerAverage> getPlayerAverageTopTen();
//	
	
	@Query("SELECT new moo.restservice.PlayerAverage (p.id, avg(r.result) as average) " 
		+ "FROM Player p "
		+ "LEFT JOIN p.results r "
		+ "GROUP BY p "
		+ "ORDER BY average ASC ")   
	List<PlayerAverage> getTopList();
//	
//	@Query(nativeQuery = true, value="SELECT players.name, avg(resultsmoo.result) as average " + 
//			" FROM resultsmoo " + 
//			" JOIN players on resultsmoo.player = players.id " + 
//			" GROUP BY players.name ORDER BY average ASC")
//	List<PlayerAverage> getPlayerAverageTopTen(); //funkar ej eftersom PlayerAverage-objekt inte skapas i SQLen
	
//	@Query(nativeQuery = true, value="SELECT avg(resultsmoo.result) as average " + 
//			" FROM resultsmoo " + 
//			" ORDER BY average ASC")
//	List<Double> getAllPlayersAverage();	
	
//	@Query(nativeQuery = true, value="SELECT players.name, avg(resultsmoo.result) as average " + 
//			" FROM resultsmoo " + 
//			" JOIN players on resultsmoo.player = players.id " + 
//			" GROUP BY players.name ORDER BY average ASC")
//	Map<Integer, Double> getTwoValues();

}
