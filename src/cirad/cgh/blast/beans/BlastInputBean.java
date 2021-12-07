/*
 *
 * CGH_BLAST cirad.cgh.blast.beans.BlastInputBean
 *
 * Copyright (C) 2021 Anestis Gkanogiannis <anestis@gkanogiannis.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 *
 */
package cirad.cgh.blast.beans;

import java.io.Serializable;
import org.apache.commons.configuration.XMLConfiguration;

public class BlastInputBean implements Serializable {

	private static final long serialVersionUID = 7557124949251507448L;

	@SuppressWarnings("unused")
	private XMLConfiguration config;
	
	//@NotNull
	//@NotBlank(message="Query cannot be empty")
	private String queryFromArea = "";
	private String queryFromFile = "";
	
	private String program = "";
	private String database = "";
	
	private String e = "1E-10";
	private boolean filter = true;
	private Integer numAlign = 10;
	
	private boolean fastaGene = false;
	private boolean fastaCDS = false;
	private boolean fastaProt = false;

	public BlastInputBean(XMLConfiguration config) {
		this.config = config;
	}

	@SuppressWarnings("unused")
	private BlastInputBean() {
	}

	public String getQueryFromArea() {
		return this.queryFromArea;
	}

	public void setQueryFromArea(String queryFromArea) {
		this.queryFromArea = queryFromArea;
	}

	public String getQueryFromFile() {
		return this.queryFromFile;
	}

	public void setQueryFromFile(String queryFromFile) {
		this.queryFromFile = queryFromFile;
	}

	public String getProgram() {
		return this.program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public String getDatabase() {
		return this.database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getE() {
		return this.e;
	}

	public void setE(String e) {
		this.e = e;
	}

	public boolean isFilter() {
		return this.filter;
	}

	public void setFilter(boolean filter) {
		this.filter = filter;
	}

	public Integer getNumAlign() {
		return this.numAlign;
	}

	public void setNumAlign(Integer numAlign) {
		this.numAlign = numAlign;
	}

	public boolean isFastaGene() {
		return this.fastaGene;
	}

	public void setFastaGene(boolean fastaGene) {
		this.fastaGene = fastaGene;
	}

	public boolean isFastaCDS() {
		return this.fastaCDS;
	}

	public void setFastaCDS(boolean fastaCDS) {
		this.fastaCDS = fastaCDS;
	}

	public boolean isFastaProt() {
		return this.fastaProt;
	}

	public void setFastaProt(boolean fastaProt) {
		this.fastaProt = fastaProt;
	}

}
