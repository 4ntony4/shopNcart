package business;

import org.json.JSONObject;

/**
 * @author Diogo Ant�o
 *
 * 04-2022
 */

public interface ITransactable {
	
	public void execute();
	public JSONObject getResultJson();

}
