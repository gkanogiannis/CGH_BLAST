/*******************************************************************************
 * Copyright (C) 2016 <CIRAD>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3 as
 * published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Affero General Public License for more details.
 *
 * See <http://www.gnu.org/licenses/gpl-3.0.html> for details about
 * GNU Affero General Public License V3.
 *
 * Contributors:
 *     Anestis Gkanogiannis <ganoyan@gmail.com>
 *******************************************************************************/
package cirad.cgh.blast;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;

import cirad.cgh.blast.beans.FeatureBean;
import cirad.cgh.blast.beans.ProteinBean;

public class MapDBCreateCaches {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MapDBCreateCaches ehcacheFeatures = new MapDBCreateCaches();
		
		String base = args[0];
		
		DB db = DBMaker.fileDB(base+"feature_protein_transcript_cache.mapdb").make();

		if(true){
			ehcacheFeatures.parseGFF3(base+"Mesculenta_305_v6.1.gene.gff3", db);
			ehcacheFeatures.parseFeatureFasta(base+"Mesculenta_305_v6.1.gene.fa", db);
			ehcacheFeatures.parseFeatureFasta(base+"Mesculenta_305_v6.1.mRNA.fa", db);
			ehcacheFeatures.parseFeatureFasta(base+"Mesculenta_305_v6.1.cds.fa", db);
				
			ehcacheFeatures.parseProteinTranscriptFasta(base+"Mesculenta_305_v6.1.protein.fa", "protein", db);
			ehcacheFeatures.parseProteinTranscriptFasta(base+"Mesculenta_305_v6.1.transcript.fa", "transcript", db);
		}
		
		ehcacheFeatures.printCache("feature_cache", db);
		ehcacheFeatures.printCache("protein_cache", db);
		ehcacheFeatures.printCache("transcript_cache", db);
		
		db.close();
	}
	
	@SuppressWarnings("unchecked")
	public void printCache(String cacheName, DB db) {
		try {
			HTreeMap<String, Object> cache = (HTreeMap<String, Object>)db.hashMap(cacheName).createOrOpen();
			
			long count = 0L;
			for(String id : cache.getKeys()) {
				Object o = cache.get(id);
				System.out.println(id +" : " + o.toString());
				if(++count>5L) break;
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void parseGFF3(String filePath, DB db) {
		try {
			HTreeMap<String, FeatureBean> cache = (HTreeMap<String, FeatureBean>)db.hashMap("feature_cache").createOrOpen();
			
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String line = null;
			//Chromosome01 phytozomev10 gene   4  803 . + . ID=Manes.01G000100.v6.1;Name=Manes.01G000100
			//Chromosome01 phytozomev10 mRNA   4  803 . + . ID=Manes.01G000100.1.v6.1;Name=Manes.01G000100.1;pacid=32359689;longest=1;Parent=Manes.01G000100.v6.1
			//Chromosome01 phytozomev10  CDS 145  366 . + 0 ID=Manes.01G000100.1.v6.1.CDS.1;Parent=Manes.01G000100.1.v6.1;pacid=32359689
			
			while((line=br.readLine())!=null){
				if(!line.startsWith("#")){
					String[] data = line.split("\\s+");
					
					String chromosome = data[0].trim();
					String type = data[2].trim();
					long start = Long.parseLong(data[3]);
					long end = Long.parseLong(data[4]);
					String strand = data[6].trim();
					
					String tmp = data[8].trim();
					String id = "";
					String parent = "";
					for(String s : tmp.split(";")){
						if(s.startsWith("ID=")){
							id = s.substring(3);
						}
						else if(s.startsWith("Parent=")){
							parent = s.substring(7);
						}
					}
					
					if(id.length()<1) continue;
					
					FeatureBean featureBean = cache.get(id);
					if(featureBean==null){
						featureBean = new FeatureBean(id, type, parent, new ArrayList<String>(), chromosome, start, end, strand, "");
						cache.put(id, featureBean);
					}
					else{
						FeatureBean temp = new FeatureBean(id, type, parent, featureBean.getChildren(), chromosome, start, end, strand, "");
						cache.put(id, temp);
					}
					
					FeatureBean parentBean = cache.get(parent);
					if(!parent.equals("") && parentBean==null){
						ArrayList<String> list = new ArrayList<String>();
						list.add(id);
						cache.put(parent, new FeatureBean(parent, "", "", list, "", 0L, 0L, "", ""));
					}
					else if(parentBean!=null){
						parentBean.getChildren().add(id);
						cache.put(parent, parentBean);
					}
				
				}
			}
			
			br.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void parseFeatureFasta(String filePath, DB db) {
		try {
			HTreeMap<String, FeatureBean> cache = (HTreeMap<String, FeatureBean>)db.hashMap("feature_cache").createOrOpen();
			
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String line = null;
			//>Manes.01G000300.v6.1
			
			String id = "";
			StringBuffer sequenceBuffer = new StringBuffer();
			while((line=br.readLine())!=null){
				line = line.trim();
				if(line.startsWith(">")){
					if(!id.equalsIgnoreCase("")){
						FeatureBean featureBean = cache.get(id);
						featureBean.setSequence(sequenceBuffer.toString());
						cache.put(id, featureBean);
						sequenceBuffer = new StringBuffer();
					}
					id = line.substring(1);
				}
				else{
					sequenceBuffer.append(line);
				}
			}
			FeatureBean featureBean = cache.get(id);
			featureBean.setSequence(sequenceBuffer.toString());
			cache.put(id, featureBean);
		
			br.close();	
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void parseProteinTranscriptFasta(String filePath, String type, DB db) {
		try {
			HTreeMap<String, ProteinBean> cache = null;
			
			if(type.equals("protein")){
				cache = (HTreeMap<String, ProteinBean>)db.hashMap("protein_cache").createOrOpen();
			}
			else if(type.equals("transcript")){
				cache = (HTreeMap<String, ProteinBean>)db.hashMap("transcript_cache").createOrOpen();
			}
			else {
				return;
			}
					
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String line = null;
			
			//protein
			//>Manes.S054400.1.p pacid=32327518 transcript=Manes.S054400.1 locus=Manes.S054400 ID=Manes.S054400.1.v6.1 annot-version=v6.1
			//transcript
			//>Manes.S054400.1 pacid=32327518 locus=Manes.S054400 ID=Manes.S054400.1.v6.1 annot-version=v6.1
			
			String id = "";
			String locus = "";
			StringBuffer sequenceBuffer = new StringBuffer();
			while((line=br.readLine())!=null){
				line = line.trim();
				if(line.startsWith(">")){
					if(!id.equalsIgnoreCase("")){
						ProteinBean proteinBean = new ProteinBean(id, locus, type, sequenceBuffer.toString());
						cache.put(id, proteinBean);
						cache.put(locus, proteinBean);
						sequenceBuffer = new StringBuffer();
					}
					for(String s : line.split("\\s+")){
						if(s.startsWith(">")){
							id = s.substring(1);
						}
						else if(s.startsWith("ID=")){
							locus = s.substring(3);
						}
					}
				}
				else{
					sequenceBuffer.append(line);
				}
			}
			ProteinBean proteinBean = new ProteinBean(id, locus, type, sequenceBuffer.toString());
			cache.put(id, proteinBean);
			cache.put(locus, proteinBean);
			
			br.close();	
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
