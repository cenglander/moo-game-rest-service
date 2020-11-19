package moo.restservice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

	List<Player> findByName(String name);

	@Query("SELECT new moo.restservice.PlayerAverage(p.id, avg(r.result) as average) " 
	+"FROM Result r "
	+ "JOIN Player p ON r.player=p.id " 
	+ "GROUP BY Player.name ORDER BY average ASC")
	List<PlayerAverage> getPlayerAverageTopTen();

}
