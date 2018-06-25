package jcat;

public class JCat {
	public static void main(String[] args){
		try {
			DatabaseConnector db = new DatabaseConnector();
		} catch (Throwable error) {
			error.printStackTrace();
		}
		
	}

}