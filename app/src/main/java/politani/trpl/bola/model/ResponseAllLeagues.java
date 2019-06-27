package politani.trpl.bola.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class ResponseAllLeagues{

	@SerializedName("countrys")
	private List<League> countrys;

	public void setCountrys(List<League> countrys){
		this.countrys = countrys;
	}

	public List<League> getAllLeague(){
		return countrys;
	}

	@Override
 	public String toString(){
		return 
			"ResponseAllLeagues{" + 
			"countrys = '" + countrys + '\'' + 
			"}";
		}
}