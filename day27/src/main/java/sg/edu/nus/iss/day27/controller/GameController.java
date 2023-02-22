package sg.edu.nus.iss.day27.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.iss.day27.model.Comment;
import sg.edu.nus.iss.day27.model.Game;
import sg.edu.nus.iss.day27.service.GameService;

@Controller
@RequestMapping("")
public class GameController {
    @Autowired
    GameService gameSvc;
    
    @GetMapping("/")
    public String getGames(@RequestParam(defaultValue = "30") int limit,  
                            @RequestParam(defaultValue = "0") int offset, Model model){
    List<Game> results = gameSvc.getAllGames(limit, offset);
    model.addAttribute("games", results);
    model.addAttribute("next", offset + limit);
    return "index";                           
    }
    //getGames: 2nd way
    @GetMapping("/game")
    public String getGames2(@RequestParam(defaultValue = "30") int limit,  
                            @RequestParam(defaultValue = "0") int offset, Model model){
    List<Game> results = gameSvc.getAllGames2(limit, offset);
    model.addAttribute("games", results);
    model.addAttribute("next", offset + limit);
    return "index";                           
    }

    @GetMapping("/game/{gid}")
    public String getGameById(@PathVariable int gid, Model model){
    Game result = gameSvc.getGameById(gid);

    if(result == null){
        return "notfound";
    }

    model.addAttribute("id", gid);
    model.addAttribute("game", result);
    
    return "gamedetails";                           
    }

    @PostMapping("/game/comment")
    public String postComment(@RequestBody MultiValueMap<String, String> form){
        Comment comment = Comment.create(form);
		String commentId = gameSvc.addComment(comment);
		System.out.printf(">>>> commentId: %s\n", commentId);
		return "redirect:/";
    }
}
