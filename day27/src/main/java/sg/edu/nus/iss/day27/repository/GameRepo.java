package sg.edu.nus.iss.day27.repository;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.day27.model.Game;
import static sg.edu.nus.iss.day27.repository.Queries.*;

@Repository
public class GameRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    private static final String selectSQL="select * from game";
    private static final String selectSQL2="select gid, name from game limit ? offset ?";
    private static final String selectSQL3="select name, year, ranking, users_rated, url, image from game where gid = ?";
    private static final String selectSQL4="select * from game where gid = ?";

    public List<Game> getAllGames(int limit, int offset){
        return jdbcTemplate.query(selectSQL2, BeanPropertyRowMapper.newInstance(Game.class), limit, offset);
    }

    public List<Game> getAllGames2(int limit, int offset){
        SqlRowSet rs = jdbcTemplate.queryForRowSet(selectSQL2,limit,offset);
        List<Game> games = new LinkedList<>();

        while(rs.next())
            games.add(Game.createSummary(rs));

        return games;
    }
    
    public Game getGameById(int gid){
        SqlRowSet rs = jdbcTemplate.queryForRowSet(selectSQL4, gid);
        List<Game> games = new LinkedList<>();

        while(rs.next())
            games.add(Game.createGameUsingId(rs));

        return games.get(0);
        // return jdbcTemplate.queryForObject(selectSQL3, BeanPropertyRowMapper.newInstance(Game.class), gid);
    }
    
}
