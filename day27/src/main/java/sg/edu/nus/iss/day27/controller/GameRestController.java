package sg.edu.nus.iss.day27.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.day27.model.Game;
import sg.edu.nus.iss.day27.service.GameService;

@RestController
@RequestMapping(path="", produces = MediaType.APPLICATION_JSON_VALUE)
public class GameRestController {

    @Autowired
    GameService gameSvc;
    
    //localhost:8080/games?limit=30&offset=0
    @GetMapping("/games")
    public ResponseEntity<List<Game>> getGames(@RequestParam(defaultValue = "30") int limit, @RequestParam(defaultValue = "0") int offset){
        List<Game> gList = gameSvc.getAllGames(limit, offset);
        
        if(gList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(gList, HttpStatus.OK);
        }
    }
}
