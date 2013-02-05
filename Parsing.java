import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Parsing {

	/**
	 * Fonction qui renvoye un objet JSONArray qui contient toutes les informations sur les genomes
	 * @return
	 * @throws Exception
	 */
	public static JSONArray parsing() throws Exception {
		URL imdbUrl = new URL(
				"http://www.ncbi.nlm.nih.gov/genome/browse/");
		URLConnection connection = imdbUrl.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));

		String inputLine;
		String tableau = "";
		JSONArray jsonarray = null;
		while ((inputLine = in.readLine()) != null) {
			
			if(inputLine.startsWith("projects = ["))
			{
				tableau = inputLine.substring(inputLine.indexOf('['),
						inputLine.lastIndexOf(']')+1);
				jsonarray = new JSONArray(tableau);
				return jsonarray;
			}
		}
		in.close();
		return jsonarray;
	}
	
	public static void main (String args[]) throws Exception
	{
		//http://www.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=nucleotide&rettype=gb&id=CP000828.1
		JSONArray list = parsing();
		ArrayList<Organism> orga = new ArrayList<Organism>();
		for(int i=0;i<list.length();i++)
		{
			JSONObject jso = (JSONObject) list.get(i);
			if(jso.get("status").toString().compareTo("Complete")==0||jso.get("status").toString().compareTo("Chromosomes")==0)
			{
				orga.add(new Organism(jso));
			}
		}
	}
}