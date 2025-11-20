package com.app.database;

import com.app.config.AppSecretData;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import shadow.org.bson.Document;

public class DatabaseConnection {
	static String DB_USERNAME = AppSecretData.loadAppSecrets("MONGO_DB_USERNAME", "en", "US");
	static String DB_PASSWORD = AppSecretData.loadAppSecrets("MONGO_DB_PASSWORD", "en", "US");
	
        static String  connectionString = "mongodb+srv://"+DB_USERNAME+":"+DB_PASSWORD+"@vaibhav.a0kw4hu.mongodb.net/?appName=vaibhav";
        
        static ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        static MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();
        static MongoClient mongoClient = MongoClients.create(settings);
        static MongoDatabase database = mongoClient.getDatabase("my_project");
        static MongoCollection<Document> c = database.getCollection("users");
    	
        
        public static Document userLogin(String email) {
        	try {
    			Document userToBeLoggedIn = new Document("userEmail", email);	
    			Document user = c.find(userToBeLoggedIn).first(); //fetch
    			return user;
    		} catch (Exception e) {
    			return null;
    		}
        }
        
    	public static boolean verifyUser(String email) {
    		try {
    			Document userToBeVerified = new Document("userEmail", email);	
    			Document user = c.find(userToBeVerified).first(); //fetch
    			Document updatedUser = new Document("$set", new Document("isVerified", true)); //update
    			c.findOneAndUpdate(user, updatedUser);
    			return true;
    		} catch (Exception e) {
    			return false;
    		}
    	}
        
    	public static boolean insertUserData(String fName, String lName, long mobileNum, String userMail, String userPwd) {
    		try {
    			 c.insertOne(new Document("firstName", fName)
    						.append("lastName", lName)
    						.append("phone", mobileNum)
    						.append("userEmail", userMail)
    						.append("userPassword", userPwd)
    						.append("isVerified", false));
    			 return true;
    		} catch (Exception e) {
    			 return false;
    		}
    		 
    	}
    	
    }