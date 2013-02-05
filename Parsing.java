import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.JSONArray;

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
		char test;
		JSONArray jsonarray = null;
		while ((inputLine = in.readLine()) != null) {
			
			if(inputLine.startsWith("genomes = ["))
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
		JSONArray list = parsing();
		System.out.println(list.length());
	}
}