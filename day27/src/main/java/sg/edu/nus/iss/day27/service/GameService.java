package sg.edu.nus.iss.day27.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.day27.model.Comment;
import sg.edu.nus.iss.day27.model.Game;
import sg.edu.nus.iss.day27.repository.CommentRepo;
import sg.edu.nus.iss.day27.repository.GameRepo;

@Service
public class GameService {
    @Autowired
    GameRepo gameRepo;

    @Autowired
    CommentRepo commentRepo;

    public List<Game> getAllGames(int limit, int offset) {
        return gameRepo.getAllGames(limit, offset);
    }

    public List<Game> getAllGames2(int limit, int offset) {
        return gameRepo.getAllGames2(limit, offset);
    }

    public Game getGameById(int gid) {
        return gameRepo.getGameById(gid);
    }

    public String addComment(Comment comment) {
        String commentId = UUID.randomUUID().toString().substring(0, 8);
        comment.setCId(commentId);
        commentRepo.insertComment(comment);
        return commentId;
    }
}