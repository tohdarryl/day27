package sg.edu.nus.iss.day27.model;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    private int gid;
    private String name;
    private int year;
    private int ranking;
    private int usersRated;
    private String url;
    private String image;

public static Game createSummary(SqlRowSet rs){
    Game game = new Game();
    game.setGid(rs.getInt("gid"));
    game.setName(rs.getString("name"));
    return game;
}

public static Game createGameUsingId(SqlRowSet rs){
    Game game = new Game();
    game.setGid(rs.getInt("gid"));
    game.setName(rs.getString("name"));
    game.setYear(rs.getInt("year"));
    game.setRanking(rs.getInt("ranking"));
    game.setUsersRated(rs.getInt("users_rated"));
    game.setUrl(rs.getString("url"));
    game.setImage(rs.getString("image"));
    return game;
}
}