import java.util.Iterator;
import java.util.Scanner;

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;

public class ConnectionManager {

	public static void main(String[] args) {
		

		
		MongoClient mongoClient = new MongoClient("localhost",27017);
		MongoDatabase db =  mongoClient.getDatabase("test");
		
		
		if(db != null)
		{
			System.out.println("Connection Established");
		}
		
		MongoCollection<Document> collection = db.getCollection("test");
		
		//insert
		Scanner sc = new Scanner(System.in);
		String username = sc.next();
		String password = sc.next();
		String email = sc.next();

		Document doc = new Document("name", username).append("password", password).append("email", email);
		collection.insertOne(doc);
	    
		//View
		
		FindIterable<Document> findValue = collection.find();
		Iterator<Document> itr = findValue.iterator();
		
		while(itr.hasNext())
		{
			Document doc1 = itr.next();
			System.out.println(doc1.getString("name")+" "+doc1.getString("email"));
		}
		
		//Find one
		Document doc2 = collection.find(new Document("name", "Ragavi")).first();
		System.out.println(doc2.getString("name")+" "+doc2.getString("email"));
		
		//update
		collection.updateOne(Filters.eq("name","Ragavi"),Updates.set("email", "ragavi2121@gmail.com"));
		System.out.println("Updated Successfully");
		
//		delete
		DeleteResult del = collection.deleteOne(Filters.eq("name","Naveen"));

		
		
		
	    
	}

}
