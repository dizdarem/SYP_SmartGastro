package bll;

import java.util.ArrayList;

public class TischBG {
	private int tischId;
	private ArrayList<BestellungGruppiert> arrBG;
	
	public TischBG(int tischId, ArrayList<BestellungGruppiert> arrBG) {
		super();
		this.tischId = tischId;
		this.arrBG = arrBG;
	}
	
	public TischBG() {
		super();
	}

	public int getTischId() {
		return tischId;
	}

	public void setTischId(int tischId) {
		this.tischId = tischId;
	}

	public ArrayList<BestellungGruppiert> getArrBG() {
		return arrBG;
	}

	public void setArrBG(ArrayList<BestellungGruppiert> arrBG) {
		this.arrBG = arrBG;
	}
	
	
}
