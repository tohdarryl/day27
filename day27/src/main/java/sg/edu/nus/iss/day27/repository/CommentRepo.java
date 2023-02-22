package sg.edu.nus.iss.day27.repository;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.day27.model.Comment;

@Repository
public class CommentRepo {
    public static String COLLECTION_COMMENTS = "comments";

	@Autowired
	private MongoTemplate template;

	public void insertComment(Comment comment) {
		Document doc = comment.toDocument();
		template.insert(doc, COLLECTION_COMMENTS);
	}
}
