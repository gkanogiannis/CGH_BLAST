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

import cirad.cgh.blast.beans.FeatureBean;
import cirad.cgh.blast.beans.ProteinBean;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EhcacheCreateCaches {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		EhcacheCreateCaches ehcacheFeatures = new EhcacheCreateCaches();
		
		CacheManager cacheManager = CacheManager.newInstance("/Users/gkanogiannis/Documents/git/CassavaHubJavaTools/Blast/WebContent/WEB-INF/ehcache.xml");
		
		if(false){
			ehcacheFeatures.parseGFF3("/Users/gkanogiannis/Documents/git/CassavaHubJavaTools/cassava_ehcache_mapdb.backup/Mesculenta_305_v6.1.gene.gff3", cacheManager);
			ehcacheFeatures.parseFeatureFasta("/Users/gkanogiannis/Documents/git/CassavaHubJavaTools/cassava_ehcache_mapdb.backup/Mesculenta_305_v6.1.gene.fa", cacheManager);
			ehcacheFeatures.parseFeatureFasta("/Users/gkanogiannis/Documents/git/CassavaHubJavaTools/cassava_ehcache_mapdb.backup/Mesculenta_305_v6.1.mRNA.fa", cacheManager);
			ehcacheFeatures.parseFeatureFasta("/Users/gkanogiannis/Documents/git/CassavaHubJavaTools/cassava_ehcache_mapdb.backup/Mesculenta_305_v6.1.cds.fa", cacheManager);
				
			ehcacheFeatures.parseProteinTranscriptFasta("/Users/gkanogiannis/Documents/git/CassavaHubJavaTools/cassava_ehcache_mapdb.backup/Mesculenta_305_v6.1.protein.fa", "protein", cacheManager);
			ehcacheFeatures.parseProteinTranscriptFasta("/Users/gkanogiannis/Documents/git/CassavaHubJavaTools/cassava_ehcache_mapdb.backup/Mesculenta_305_v6.1.transcript.fa", "transcript", cacheManager);
		}
		
		ehcacheFeatures.printCache("feature_cache", cacheManager);
		ehcacheFeatures.printCache("protein_cache", cacheManager);
		ehcacheFeatures.printCache("transcript_cache", cacheManager);
		
		cacheManager.shutdown();
	}
	
	@SuppressWarnings("deprecation")
	public void printCache(String cacheName, CacheManager cacheManager) {
		try {
			Cache cache = cacheManager.getCache(cacheName);
			
			System.out.println(cache.getDiskStoreSize());
			System.out.println(cache.getMemoryStoreSize());
			
			long count = 0L;
			for(Object id : cache.getKeys()) {
				Element e = cache.get(id);
				System.out.println(e.getObjectKey() +" : " + e.getObjectValue());
				if(++count>5L) break;
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void parseGFF3(String filePath, CacheManager cacheManager) {
		try {
			Cache cache = cacheManager.getCache("feature_cache");
			
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
					
					Element e = cache.get(id);
					FeatureBean featureBean;
					if(e==null){
						featureBean = new FeatureBean(id, type, parent, new ArrayList<String>(), chromosome, start, end, strand, "");
						e = new Element (id, featureBean);
						cache.put(e);
					}
					else{
						FeatureBean temp = (FeatureBean) e.getObjectValue();
						featureBean = new FeatureBean(id, type, parent, temp.getChildren(), chromosome, start, end, strand, "");
						e = new Element(id, featureBean);
						cache.put(e);
					}
					
					Element p = cache.get(parent);
					if(!parent.equals("") && p==null){
						ArrayList<String> list = new ArrayList<String>();
						list.add(id);
						p = new Element(parent, new FeatureBean(parent, "", "", list, "", 0L, 0L, "", ""));
						cache.put(p);
					}
					else if(p!=null){
						FeatureBean parentBean = (FeatureBean) p.getObjectValue();
						parentBean.getChildren().add(id);
						p = new Element(parent, parentBean);
						cache.put(p);
					}
				
				}
			}
			
			br.close();
			
			cache.flush();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void parseFeatureFasta(String filePath, CacheManager cacheManager) {
		try {
			Cache cache = cacheManager.getCache("feature_cache");
			
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String line = null;
			//>Manes.01G000300.v6.1
			
			String id = "";
			StringBuffer sequenceBuffer = new StringBuffer();
			while((line=br.readLine())!=null){
				line = line.trim();
				if(line.startsWith(">")){
					if(!id.equalsIgnoreCase("")){
						Element e = cache.get(id);
						FeatureBean featureBean = (FeatureBean) e.getObjectValue();
						featureBean.setSequence(sequenceBuffer.toString());
						cache.put(new Element(id, featureBean));
						sequenceBuffer = new StringBuffer();
					}
					id = line.substring(1);
				}
				else{
					sequenceBuffer.append(line);
				}
			}
			Element e = cache.get(id);
			FeatureBean featureBean = (FeatureBean) e.getObjectValue();
			featureBean.setSequence(sequenceBuffer.toString());
			cache.put(new Element(id, featureBean));
		
			br.close();	
			
			cache.flush();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void parseProteinTranscriptFasta(String filePath, String type, CacheManager cacheManager) {
		try {
			Cache cache = null;
			
			if(type.equals("protein")){
				cache = cacheManager.getCache("protein_cache");
			}
			else if(type.equals("transcript")){
				cache = cacheManager.getCache("transcript_cache");
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
						cache.put(new Element(id, proteinBean));
						cache.put(new Element(locus, proteinBean));
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
			cache.put(new Element(id, proteinBean));
			cache.put(new Element(locus, proteinBean));
			
			br.close();	
			
			cache.flush();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
