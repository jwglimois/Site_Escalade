package com.oc.sitejava.dto;

import com.oc.sitejava.entity.Voie;

public class LongueurDto {
	private Voie voie1;
	private Voie voie2;
	private Voie voie3;
	private Voie voie4;
	private Voie voie5;
	private Voie voie6;

	
	public LongueurDto() {
		super();
	}


	public LongueurDto(Voie voie1, Voie voie2, Voie voie3, Voie voie4, Voie voie5, Voie voie6) {
		super();
		this.voie1 = voie1;
		this.voie2 = voie2;
		this.voie3 = voie3;
		this.voie4 = voie4;
		this.voie5 = voie5;
		this.voie6 = voie6;
	}


	public Voie getVoie1() {
		return voie1;
	}

	public void setVoie1(Voie voie1) {
		this.voie1 = voie1;
	}

	public Voie getVoie2() {
		return voie2;
	}

	public void setVoie2(Voie voie2) {
		this.voie2 = voie2;
	}

	public Voie getVoie3() {
		return voie3;
	}

	public void setVoie3(Voie voie3) {
		this.voie3 = voie3;
	}

	public Voie getVoie4() {
		return voie4;
	}

	public void setVoie4(Voie voie4) {
		this.voie4 = voie4;
	}


	public Voie getVoie5() {
		return voie5;
	}



	public void setVoie5(Voie voie5) {
		this.voie5 = voie5;
	}




	public Voie getVoie6() {
		return voie6;
	}




	public void setVoie6(Voie voie6) {
		this.voie6 = voie6;
	}


}
