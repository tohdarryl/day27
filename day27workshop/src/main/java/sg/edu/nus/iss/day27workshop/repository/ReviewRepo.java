package sg.edu.nus.iss.day27workshop.repository;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.day27workshop.model.EditedReview;
import sg.edu.nus.iss.day27workshop.model.Review;

import static sg.edu.nus.iss.day27workshop.Constants.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;


@Repository
public class ReviewRepo {
    
    @Autowired
    MongoTemplate template;

    // Task a
    public void insertReview(Review review){
        Document doc = review.toDocument();
        template.insert(doc, COLLECTION_REVIEWS);
    }
    // Task b
    public Review updateReviewById(String rId, String json) throws IOException{
        /* 
        ObjectId docId = new ObjectId(_id);
        Review r = template.findById(docId, Review.class, COLLECTION_REVIEWS);
        */
        
        Criteria c = Criteria.where(FIELD_RID).regex(rId,"i");
        Query q = Query.query(c);
        
        // Map data straight to Review.Class instead
        List<Review> list = template.find(q, Review.class, COLLECTION_REVIEWS);
        // List<Review> rList = list.stream()   
        //         .map(r -> Review.createB(r))
        //         .toList();
        Review r = list.get(0);
        
        if (r != null){
            EditedReview e = EditedReview.createEditedReview(json);
            // if List<EditedReview> not null, add EditedReview in;
            if(r.getEdited() != null){
            r.getEdited().add(e);
            // else create new list
            }else {
                List<EditedReview> ll = new LinkedList<>();
                ll.add(e);
                r.setEdited(ll);
            }

        Update updateOps = new Update()
            .push("edited").each(e);
            template.updateFirst(q,updateOps, COLLECTION_REVIEWS);

        }
        return r;
        
    }
    // Task C : use ObjectId to extract info
    public Review getReviewById(String _id){
        ObjectId docId = new ObjectId(_id);
        return template.findById(docId, Review.class, COLLECTION_REVIEWS);
    }

 
}
