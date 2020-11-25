package moo.restservice;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

	List<Player> findByName(String name);
		
	@Query("SELECT new moo.restservice.PlayerAverage (p.name, avg(r.result) as average) " 
		+ "FROM Player p "
		+ "JOIN p.results r "
		+ "GROUP BY p "
		+ "ORDER BY average ASC ")   
	List<PlayerAverage> getTopList(Pageable pageable);
	
	default List<PlayerAverage> getTopTen() {
		List<PlayerAverage> topTen = getTopList(PageRequest.of(0, 10));
		return topTen;
	}
	
//	@Query("SELECT new moo.restservice.PlayerAverage (p.id, avg(r.result) as average) " 
//			+ "FROM Player p "
//			+ "LEFT JOIN p.results r "
//			+ "GROUP BY p "
//			+ "ORDER BY average ASC ")   
//		List<PlayerAverage> getTopList(Pageable pageable);
	
	@Query(nativeQuery = true, value="SELECT avg(resultsmoo.result) as average " + 
			" FROM resultsmoo " + 
			" ORDER BY average ASC")
	List<Double> getAllPlayersAverage();

}

//Första:
//@Query("SELECT new moo.restservice.PlayerAverage(p.id, avg(r.result) as average) " 
//	+"FROM Result r "
//	+ "JOIN Player p ON r.player=p.id " 
//	+ "GROUP BY Player.id ORDER BY average ASC")
//List<PlayerAverage> getPlayerAverageTopTen();
//