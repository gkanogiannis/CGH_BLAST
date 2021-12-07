/*
 *
 * CGH_BLAST cirad.cgh.blast.beans.FeatureBean
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
import java.util.List;

public class FeatureBean implements Serializable {

	private static final long serialVersionUID = -7176619862316575630L;
	
	private String id = "";
	private String type = "";
	private String parent = "";
	private List<String> children;
	private String chromosome = "";
	private long start = 0L;
	private long end = 0L;
	private String strand = "";
	private String sequence = "";
	
	public FeatureBean(String id, String type, String parent, List<String> children, String chromosome, long start,
			long end, String strand, String sequence) {
		this.id = id;
		this.type = type;
		this.parent = parent;
		this.children = children;
		this.chromosome = chromosome;
		this.start = start;
		this.end = end;
		this.strand = strand;
		this.sequence = sequence;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public List<String> getChildren() {
		return children;
	}
	public void setChildren(List<String> children) {
		this.children = children;
	}
	public String getChromosome() {
		return chromosome;
	}
	public void setChromosome(String chromosome) {
		this.chromosome = chromosome;
	}
	public long getStart() {
		return start;
	}
	public void setStart(long start) {
		this.start = start;
	}
	public long getEnd() {
		return end;
	}
	public void setEnd(long end) {
		this.end = end;
	}
	public String getStrand() {
		return strand;
	}
	public void setStrand(String strand) {
		this.strand = strand;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	
	@Override
	public String toString() {
		return "FeatureBean [id=" + id + ", type=" + type + ", parent=" + parent + ", children=" + children
				+ ", chromosome=" + chromosome + ", start=" + start + ", end=" + end + ", strand=" + strand
				+ ", sequence=" + sequence + "]";
	}
	
}
