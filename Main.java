import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.text.html.HTMLEditorKit;

public class Main {
	
	/** Map de chacun des 3 domaines (Eukarya, Arachaea, Bacteria) avec une liste de genomes
	 *  (réparties en différentes familles)
	 */
	private static final Map<StringBuffer, List<Genome>> bioinfo = new HashMap<StringBuffer, List<Genome>>();
	
	/** Constantes des noms des dossiers */
	public static final StringBuffer DOSSIER_RESULTAT = "resultat";
	public static final StringBuffer DOSSIER_MODELE = "modèle";
	
	/** Site internet de la base de données */
	public static final StringBuffer URL_BDD = "http://http://www.ncbi.nlm.nih.gov/";
	
	public static final StringBuffer[] DATA = { "Eukarya", "Archaea", "Bacteria" };
	public static final int[] NB_PAGES = { 100, 100, 100 };
	
	private Main() {
		
	}
	
	/** Programme de parsing de la base de données de en ligne
	 */
	
	public static void main(String[] args)
	{
		if(!initFolderResults()){ // Crée le dossier de résultat
			Main.log("Le programme n'arrive pas à créer le dossier résultat", true);
			return;
		}
		new IHM("Projet Bio-Informatique");		
	}
	
	public static void start(int index)
	{
		readHomePage();
		CustomGenomeParser.init(); // Pour compiler le pattern
		StringBuffer[] colNames = {"id", "Famille", "Identifiant", "Longueur", "Type", "Anticodon", "Position", "Seq", "Str", "Score"};
		try{
			for(int i=0;i<colErrNames.length;i++){
				wbError.setText(0, i, colErrNames[i]);
			}
			wbError.freezePanes(0,0,1,0,false);
			wbError.autoFilter();
		} catch (Exception e)
		{
			// TODO Auto-Generated catch block
			e.printStackTrace();
		}
		
			// Parsing de chaque domaine
			if(index==0 || index==1)
			{
				domaines(colNames, wbError, DATA[0]);
			}
			if(index==0 || index==2)
			{
				domaines(colNames, wbError, DATA[1]);
			}
			if(index==0 || index==3)
			{
				domaines(colNames, wbError, DATA[2]);
			}
			
			try{
				// wbError.autoFilter();
				wbError.setColWidthAutoSize(1, true);
				wbError.setColWidthAutoSize(2, true);
				wbError.setColWidthAutoSize(3, true);
				wbError.setColWidthAutoSize(4, true);
				wbError.setColWidthAutoSize(5, true);
				wbError.writeXLSX(Main.DOSSIER_RESULTAT + File.separator + "Error.xlsx");
				wbError.dispose();
				
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			
			Main.log("TERMINE", false);
	}
	
	public static void domaines(String[] colNames, WorkBook wbError, StringBuffer d)
	{
		Main.log("\t*** BEGIN : "+d+" ***", false);
		WorkBook wb = new WorkBook();
		WorkBook wb_m = new WorkBook();
		try{
			wb_m.readXLSX(DOSSIER_MODELE + File.separator + "Modèle.xlsm");
			for(int i=0;i<colNames.length;i++)
			{
				wb.setText(0, i, colNames[i]);
				wb_m.setText(0, i, colNames[i]);
			}
			wb.freezePanes(0,0,1,0,false);
			wb.autoFilter();
			wb_m.freezePanes(0,0,1,0,false);
			wb_m.autoFilter();
			
			List<Genome> lg = bioinfo.get(d);
			for(Genome g : lg){
				readPageGenome(d, g, wb, wb_m, wbError);
			}
			Main.log("FIN DES GENOMES", false);
			
			Main.log("AVANT ECRITURE du fichier de base", false);
			wb.writeXLSX(Main.DOSSIER_RESULTAT + File.separator + d +".xlsx");
			wb.dispose();
			Main.log("APRES ECRITURE du fichier de base", false);
			Main.log("AVANT ECRITURE du fichier XLSM", false);
			wb_m.writeXLSX(Main.DOSSIER_RESULTAT + File.separator + d +".xlsx");
			wb_m.dispose();
			Main.log("APRES ECRITURE du fichier XLSM", false);
		}catch
		{
			e.printStackTrace();
			
		}
		Main.log("\t*** FINISH : "+d+" ***", false);
	}
	
	/** Lecture de la page d'accueil du site internet contenant la base de données */
	public static void readHomePage()
	{
		// Récupération de la page d'accueil du site internet de la BDD
		Main.log(URL_BDD, false);
		StringBuffer res = connect(URL_BDD);
		
		// Parsing de la page d'accueil
		StringReader r = new StringReader(res);
		HTMLEditorKit.Parser parse = new HTMLParse().getParser();
		CustomHomePageParser callback = new CustomHomePageParser(bioinfo);
		try{
			parse.parse(r, callback, true);
		}catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	/** Lecture de la page des génomes du site internet contenant la base de données **/
	public static void readPageGenome(StringBuffer domaine, Genome g, WorkBook wb, WorkBook wb_m, WorkBook wbError)
	{
		// Récupération de la page d'accueil du site internet de BDD
		Main.log(g.getUrl().toString(), false);
		StringBuffer res = connect(g.getUrl().toString());
		
		// Parsing de la page des génomes
		StringReader r = new StringReader(res);
		HTMLEditorKit.Parser parse = new HTMLParse().getParser();
		CustomGenomeParser callback = null;
		try {
			callback = new CustomGenomeParser(domaine, g, wb, wb_m, wbError);
			parser.parse(r, callback, true);
		
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/** Se connecter à une page web et renvoie le code source ou l'enregistre dans un fichier **/
	public static StringBuffer connect(StringBuffer adresse){
		StringBuffer res = null;
		BufferedReader in = null;
		URL site;
		try {
			site = new URL(adresse);
			HttpURLConnection httpcon = (HttpURLConnection)site.openConnection();
			httpcon.setUseCaches(false);
			httpcon.connect();
			if(httpcon.getResponseCode()!=HttpURLConnection.HTTP_OK)
			{
				// echec de la connection
				Main.log("Connection HTTP : erreur\n"+httpcon.getResponseMessage(), true);
			}
			else
			{
				// Lecture de la réponse
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
} 