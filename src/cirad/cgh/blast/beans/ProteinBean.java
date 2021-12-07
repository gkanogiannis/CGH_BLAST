/*
 *
 * CGH_BLAST cirad.cgh.blast.beans.ProteinBean
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

public class ProteinBean implements Serializable {

	private static final long serialVersionUID = -3901874326760057008L;
	
	private String id = "";
	private String locus = "";
	private String type = "";
	private String sequence = "";	
	
	public ProteinBean(String id, String locus, String type, String sequence) {
		super();
		this.id = id;
		this.locus = locus;
		this.type = type;
		this.sequence = sequence;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getLocus() {
		return locus;
	}
	public void setLocus(String locus) {
		this.locus = locus;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	@Override
	public String toString() {
		return "ProteinBean [id=" + id + ", locus=" + locus + ", type=" + type + ", sequence=" + sequence + "]";
	}
	
}
