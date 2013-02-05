import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Organism {

	int genome_id;
	int project_id;
	String project_acc;
	String bioproject_ids;
	int taxid;
	String name;
	float size;
	int chromosome_count;
	int organelle_count;
	int plasmid_count;
	String release_date;
	String modify_date;
	String gc_content;
	int scaffold_count;
	int contig_count;
	int proteins;
	int genes;
	String status;
	String host;
	String WGSPrefix;
	String assembly;
	int gene_link_count;
	String assembly_names = "";
	String assembly_ids  = "";
	String chromosome_accs  = "";
	String chromosome_rs_accs  = "";
	String chromosome_gb_accs  = "";
	String plasmid_accs  = "";
	String plasmid_rs_accs  = "";
	String plasmid_gb_accs  = "";
	String kingdom;
	String group;
	String subgroup;
	
	public Organism(JSONObject jsa) throws NumberFormatException, JSONException
	{
		
		this.genome_id = Integer.parseInt(jsa.get("genome_id").toString());
		this.project_id = Integer.parseInt(jsa.get("project_id").toString());
		this.project_acc = jsa.get("project_acc").toString();
		this.bioproject_ids = jsa.get("bioproject_ids").toString();
		this.taxid = Integer.parseInt(jsa.get("taxid").toString());
		this.name = jsa.get("name").toString();
		this.size = Float.parseFloat(jsa.get("size").toString());
		this.chromosome_count = Integer.parseInt(jsa.get("chromosome_count").toString());
		this.organelle_count = Integer.parseInt(jsa.get("organelle_count").toString());
		this.plasmid_count = Integer.parseInt(jsa.get("plasmid_count").toString());
		this.release_date = jsa.get("release_date").toString();
		this.modify_date = jsa.get("modify_date").toString();
		this.gc_content = jsa.get("gc_content").toString();
		this.scaffold_count = Integer.parseInt(jsa.get("scaffold_count").toString());
		this.contig_count = Integer.parseInt(jsa.get("contig_count").toString());
		this.proteins = Integer.parseInt(jsa.get("proteins").toString());
		this.genes = Integer.parseInt(jsa.get("genes").toString());
		this.status = jsa.get("status").toString();
		this.host = jsa.get("host").toString();
		this.WGSPrefix = jsa.get("WGSPrefix").toString();
		this.assembly = jsa.get("assembly").toString();
		this.gene_link_count = Integer.parseInt(jsa.get("gene_link_count").toString());
		this.assembly_names = jsa.get("assembly_names").toString();
		this.assembly_ids = jsa.get("assembly_ids").toString();
		if(jsa.has("chromosome_accs"))
		{
			this.chromosome_accs = jsa.get("chromosome_accs").toString();
		}
		if(jsa.has("chromosome_rs_accs"))
		{
			this.chromosome_rs_accs = jsa.get("chromosome_rs_accs").toString();
		}
		if(jsa.has("chromosome_gb_accs"))
		{
			this.chromosome_gb_accs = jsa.get("chromosome_gb_accs").toString();
		}
		if(jsa.has("plasmid_accs"))
		{
			this.plasmid_accs = jsa.get("plasmid_accs").toString();
		}
		if(jsa.has("plasmid_rs_accs"))
		{
			this.plasmid_rs_accs = jsa.get("plasmid_rs_accs").toString();
		}
		if(jsa.has("plasmid_gb_accs"))
		{
			this.plasmid_gb_accs = jsa.get("plasmid_gb_accs").toString();
		}
		this.kingdom = jsa.get("kingdom").toString();
		this.group = jsa.get("group").toString();
		this.subgroup = jsa.get("subgroup").toString();
	}
	
	public String toString()
	{
		return
		"\ngenome_id = "+this.genome_id
		+"\nproject_id = "+this.project_id
		+"\nproject_acc = "+this.project_acc
		+"\nbioproject_ids = "+this.bioproject_ids
		+"\ntaxid = "+this.taxid
		+"\nname = "+this.name
		+"\nsize = "+this.size
		+"\nchromosome_count = "+this.chromosome_count
		+"\norganelle_count = "+this.organelle_count
		+"\nplasmid_count = "+this.plasmid_count
		+"\nrelease_date = "+this.release_date
		+"\nmodify_date = "+this.modify_date
		+"\ngc_content = "+this.gc_content
		+"\nscaffold_count = "+this.scaffold_count
		+"\ncontig_count = "+this.contig_count
		+"\nproteins = "+this.proteins
		+"\ngenes = "+this.genes
		+"\nstatus = "+this.status
		+"\nhost = "+this.host
		+"\nWGSPrefix = "+this.WGSPrefix
		+"\nassembly = "+this.assembly
		+"\ngene_link_count = "+this.gene_link_count
		+"\nassembly_names = "+this.assembly_names.toString()
		+"\nassembly_ids = "+this.assembly_ids.toString()
		+"\nchromosome_accs = "+this.chromosome_accs.toString()
		+"\nchromosome_rs_accs = "+this.chromosome_rs_accs.toString()
		+"\nchromosome_gb_accs = "+this.chromosome_gb_accs.toString()
		+"\nplasmid_accs = "+this.plasmid_accs.toString()
		+"\nplasmid_rs_accs = "+this.plasmid_rs_accs.toString()
		+"\nplasmid_gb_accs = "+this.plasmid_gb_accs.toString()
		+"\nkingdom = "+this.kingdom
		+"\ngroup = "+this.group
		+"\nsubgroup = "+this.subgroup;
	}
	
}
