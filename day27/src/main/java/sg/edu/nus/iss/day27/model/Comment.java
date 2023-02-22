package sg.edu.nus.iss.day27.model;

import org.bson.Document;
import org.springframework.util.MultiValueMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private String cId;
    private int gid;
    private String user;
    private int rating;
    private String cText;

    public Document toDocument() {
        Document doc = new Document();
        doc.put("c_id", getCId());
        doc.put("user", getUser());
        doc.put("rating", getRating());
        doc.put("c_text", getCText());
        return doc;
    }

    @Override
    public String toString() {
        return "Comment[commentId=%s, username=%s, rating=%d, comment=%s]"
                .formatted(cId, user, rating, cText);
    }

    public static Comment create(MultiValueMap<String, String> form) {
        Comment comment = new Comment();
        comment.setGid(Integer.parseInt(form.getFirst("gameId")));
        comment.setUser(form.getFirst("username"));
        comment.setRating(Integer.parseInt(form.getFirst("rating")));
        comment.setCText(form.getFirst("comment"));
        return comment;
    }
}