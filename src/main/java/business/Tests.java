package business;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import data.Bundles;
import data.SiteClient;

/**
 * @author Diogo Antão
 *
 * 04-2022
 */

public class Tests {

	public static void main(String[] args) {
		// test here
		System.out.println("Finished"); //$NON-NLS-1$
	}
	
	public static void newTest() {
		// new test
	}
	
	@SuppressWarnings("nls")
	public static void jsonHarryPotter() {
		JSONArray genre = new JSONArray();
		genre.put("fantasy");
		
		JSONObject book = new JSONObject();
		book.put("title", "Harry Potter and the Deathly Hallows");
		book.put("author", "J. K. Rowling");
		book.put("year", 2007);
		book.put("publisher", "Bloomsbury");
		book.put("language", "english");
		book.put("author", "J. K. Rowling");
		book.put("genre", genre);
		book.put("pages", 607);
		
		JSONObject json = new JSONObject();
		json.put("book", book);
		
		System.out.println(json);
		System.out.println(json.toString(4));
	}
	
	public static void siteClientTest() {
		SiteClient siteClient = new SiteClient();
		System.out.println(siteClient);
		System.out.println(siteClient.getClientDs());
	}
	
	@SuppressWarnings("nls")
	public static void bundleTest() {
		ArrayList<String> arr = new ArrayList<>();
		
		arr.add(Bundles.class.getPackageName());
		arr.add(Bundles.class.getName());
		arr.add(Bundles.ColumnLabels.getBaseBundleName());
		arr.add(Bundles.Queries.getBaseBundleName());
		
		arr.add(Bundles.ColumnLabels.getString("productDs"));		
		arr.add(Bundles.Queries.getString("selectAllAvlProducts"));
		
		for(String s : arr) {
			System.out.println(s);
		}
	}
	
	public static void threadTest() {
		
		Thread t1 = new ThreadLocalTest();
		t1.start();
		
		Thread t2 = new ThreadLocalTest();
		t2.start();
		
		Thread t3 = new ThreadLocalTest();
		t3.start();
	}

}
