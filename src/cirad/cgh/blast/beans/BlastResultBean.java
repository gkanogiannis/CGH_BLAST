/*
 *
 * CGH_BLAST cirad.cgh.blast.beans.BlastResultBean
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

public class BlastResultBean implements Serializable {

	private static final long serialVersionUID = -1219035662022249376L;
	
	private String qName = "";
	private Long qLength = 0L;
	private Long aLength = 0L;
	private Long qStart = 0L;
	private Long qEnd = 0L;
	private String locus = "";
	private Long lLength = 0L;
	private Long lStart = 0L;
	private Long lEnd = 0L;
	private String lType = "";
	private String subject = "";
	private Long sLength = 0L;
	private Long sStart = 0L;
	private Long sEnd = 0L;
	private String protein = "";
	private Long pLength = 0L;
	private Long pStart = 0L;
	private Long pEnd = 0L;
	private String strd = "";
	private Double identity = 0.0D;
	private Long cumulativeLength = 0L;
	private Double e = 0.0D;
	private Double bit = 0.0D;
	private String link = "";
	
	public String getqName() {
		return qName;
	}
	public void setqName(String qName) {
		this.qName = qName;
	}
	public Long getqLength() {
		return qLength;
	}
	public void setqLength(Long qLength) {
		this.qLength = qLength;
	}
	public Long getaLength() {
		return aLength;
	}
	public void setaLength(Long aLength) {
		this.aLength = aLength;
	}
	public Long getqStart() {
		return qStart;
	}
	public void setqStart(Long qStart) {
		this.qStart = qStart;
	}
	public Long getqEnd() {
		return qEnd;
	}
	public void setqEnd(Long qEnd) {
		this.qEnd = qEnd;
	}
	public String getLocus() {
		return locus;
	}
	public void setLocus(String locus) {
		this.locus = locus;
	}
	public Long getlLength() {
		return lLength;
	}
	public void setlLength(Long lLength) {
		this.lLength = lLength;
	}
	public Long getlStart() {
		return lStart;
	}
	public void setlStart(Long lStart) {
		this.lStart = lStart;
	}
	public Long getlEnd() {
		return lEnd;
	}
	public void setlEnd(Long lEnd) {
		this.lEnd = lEnd;
	}
	public String getlType() {
		return lType;
	}
	public void setlType(String lType) {
		this.lType = lType;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Long getsLength() {
		return sLength;
	}
	public void setsLength(Long sLength) {
		this.sLength = sLength;
	}
	public Long getsStart() {
		return sStart;
	}
	public void setsStart(Long sStart) {
		this.sStart = sStart;
	}
	public Long getsEnd() {
		return sEnd;
	}
	public void setsEnd(Long sEnd) {
		this.sEnd = sEnd;
	}
	public String getProtein() {
		return protein;
	}
	public void setProtein(String protein) {
		this.protein = protein;
	}
	public Long getpLength() {
		return pLength;
	}
	public void setpLength(Long pLength) {
		this.pLength = pLength;
	}
	public Long getpStart() {
		return pStart;
	}
	public void setpStart(Long pStart) {
		this.pStart = pStart;
	}
	public Long getpEnd() {
		return pEnd;
	}
	public void setpEnd(Long pEnd) {
		this.pEnd = pEnd;
	}
	public String getStrd() {
		return strd;
	}
	public void setStrd(String strd) {
		this.strd = strd;
	}
	public Double getIdentity() {
		return identity;
	}
	public void setIdentity(Double identity) {
		this.identity = identity;
	}
	public Long getCumulativeLength() {
		return cumulativeLength;
	}
	public void setCumulativeLength(Long cumulativeLength) {
		this.cumulativeLength = cumulativeLength;
	}
	public Double getE() {
		return e;
	}
	public void setE(Double e) {
		this.e = e;
	}
	public Double getBit() {
		return bit;
	}
	public void setBit(Double bit) {
		this.bit = bit;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	@Override
	public String toString() {
		return "BlastResultBean [qName=" + qName + ", qLength=" + qLength + ", aLength=" + aLength + ", qStart="
				+ qStart + ", qEnd=" + qEnd + ", locus=" + locus + ", lLength=" + lLength + ", lStart=" + lStart
				+ ", lEnd=" + lEnd+ ", lType=" + lType + ", subject=" + subject + ", sLength=" + sLength + ", sStart=" + sStart + ", sEnd="
				+ sEnd + ", protein=" + protein + ", pLength=" + pLength + ", pStart=" + pStart + ", pEnd=" + pEnd
				+ ", strd=" + strd + ", identity=" + identity + ", cumulativeLength=" + cumulativeLength + ", e=" + e
				+ ", bit=" + bit + ", link=" + link + "]";
	}

}
