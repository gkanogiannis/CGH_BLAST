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

import cirad.cgh.blast.beans.BlastResultBean;
import cirad.cgh.blast.beans.FeatureBean;
import cirad.cgh.blast.beans.ProteinBean;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.mapdb.DB;
import org.mapdb.HTreeMap;

public class Utils {

	public static String getTextFromFile(File file, Charset encoding) throws Exception {
		byte[] encoded = Files.readAllBytes(Paths.get(file.getAbsolutePath(), new String[0]));
		return new String(encoded, encoding);
	}

	public static String getTextFromURL(String url) throws Exception {
		if (url == null) {
			return null;
		}
		URL website = new URL(url);
		URLConnection connection = website.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

		StringBuilder response = new StringBuilder();
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine + "\n");
		}
		in.close();

		return response.toString();
	}
	
	public static List<BlastResultBean> parseBlastResultChromosome(String blastResult, String baseJbrowseURL, DB mapdb, String featureCacheString, String proteinCacheString) {
		try {
			//Cache cache = cacheManager.getCache(featureCacheString);
			
			List<BlastResultBean> blastResultBeans = new ArrayList<BlastResultBean>();
	        
			//String blastResult = Utils.getTextFromURL(blastResultURLcc);
			if(blastResult == null) return blastResultBeans;
			
			Scanner scanner = new Scanner(blastResult);
			while (scanner.hasNextLine()) {
				//# Fields: query id, query length, subject id, subject length, % identity, alignment length, q. start, q. end, 
				//			s. start, s. end, subject strand, evalue, bit score
				//# 3 hits found
				//Query01	1179	Chromosome01	34959721	100.00	1179	1	1179	1	1179	plus	0.0	 2178
				
				String line = scanner.nextLine().trim();
				if(line.startsWith("#")) continue;
				
				String[] data = line.split("\\s+");
				
				BlastResultBean blastResultBean = new BlastResultBean();
				blastResultBean.setqName(data[0].trim());
				blastResultBean.setSubject(data[2].trim());
				blastResultBean.setsLength(Long.valueOf(Long.parseLong(data[3].trim())));
				blastResultBean.setIdentity(Double.valueOf(Double.parseDouble(data[4].trim())));
				blastResultBean.setqLength(Long.valueOf(Long.parseLong(data[1].trim())));
				blastResultBean.setaLength(Long.valueOf(Long.parseLong(data[5].trim())));
				blastResultBean.setqStart(Long.valueOf(Long.parseLong(data[6].trim())));
				blastResultBean.setqEnd(Long.valueOf(Long.parseLong(data[7].trim())));
				blastResultBean.setsStart(Long.valueOf(Long.parseLong(data[8].trim())));
				blastResultBean.setsEnd(Long.valueOf(Long.parseLong(data[9].trim())));
				blastResultBean.setStrd(data[10].trim().equalsIgnoreCase("plus") ? "+" : "-");
				blastResultBean.setE(Double.valueOf(Double.parseDouble(data[11].trim())));
				blastResultBean.setBit(Double.valueOf(Double.parseDouble(data[12].trim())));
				
				//http://www.cassavagenome.org/cgh/jbrowse/index.html?tracks=ReferenceSequence-1,Genes-1&loc=Chromosome01:1291481..1294653
				String link = baseJbrowseURL + "&loc=";
				link += blastResultBean.getSubject() + ":";
				link += blastResultBean.getsStart() + ".." + blastResultBean.getsEnd();
				
				if (blastResultBean.getSubject().toLowerCase().startsWith("chr")) {
					blastResultBean.setLink(link);
				} 
				else {
					blastResultBean.setLink("");
				}
				blastResultBeans.add(blastResultBean);
			}
			scanner.close();
			
			return blastResultBeans;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<BlastResultBean> parseBlastResultGeneCDSmRNA(String blastResult, String baseJbrowseURL, DB mapdb, String featureCacheString, String proteinCacheString) {
		try {
			HTreeMap<String, FeatureBean> cache = (HTreeMap<String, FeatureBean>)mapdb.hashMap(featureCacheString).createOrOpen();
			
			
			List<BlastResultBean> blastResultBeans = new ArrayList<BlastResultBean>();
	        
			//String blastResult = Utils.getTextFromURL(blastResultURLcc);
			if(blastResult == null) return blastResultBeans;
			
			Scanner scanner = new Scanner(blastResult);
			while (scanner.hasNextLine()) {
				//# Fields: query id, query length, subject id, subject length, % identity, alignment length, q. start, q. end, s. start, s. end, subject strand, evalue, bit score
				//# 2 hits found
				//Chromosome01	1180	Manes.01G000100.v6.1	800	100.00	800	4	803	1	800	plus	0.0	 1478

				String line = scanner.nextLine().trim();
				if(line.startsWith("#")) continue;
				
				String[] data = line.split("\\s+");
				
				BlastResultBean blastResultBean = new BlastResultBean();
				blastResultBean.setqName(data[0].trim());
				blastResultBean.setLocus(data[2].trim());
				blastResultBean.setlStart(Long.valueOf(Long.parseLong(data[8].trim())));
				blastResultBean.setlEnd(Long.valueOf(Long.parseLong(data[9].trim())));
				blastResultBean.setlLength(Long.valueOf(Long.parseLong(data[3].trim())));
				blastResultBean.setIdentity(Double.valueOf(Double.parseDouble(data[4].trim())));
				blastResultBean.setqLength(Long.valueOf(Long.parseLong(data[1].trim())));
				blastResultBean.setaLength(Long.valueOf(Long.parseLong(data[5].trim())));
				blastResultBean.setqStart(Long.valueOf(Long.parseLong(data[6].trim())));
				blastResultBean.setqEnd(Long.valueOf(Long.parseLong(data[7].trim())));
				blastResultBean.setE(Double.valueOf(Double.parseDouble(data[11].trim())));
				blastResultBean.setBit(Double.valueOf(Double.parseDouble(data[12].trim())));
				
				FeatureBean featureBean = cache.get(blastResultBean.getLocus());
				if(featureBean!=null){
					if(featureBean!=null){
						blastResultBean.setlType(featureBean.getType());
						
						blastResultBean.setSubject(featureBean.getChromosome());
						
						blastResultBean.setsStart(featureBean.getStart() + Long.valueOf(Long.parseLong(data[8].trim()) - 1L));
						blastResultBean.setsEnd(featureBean.getStart() + Long.valueOf(Long.parseLong(data[9].trim())) - 1L);
						
						blastResultBean.setStrd(featureBean.getStrand());
					}
				}
				
				//http://www.cassavagenome.org/cgh/jbrowse/index.html?tracks=ReferenceSequence-1,Genes-1&loc=Chromosome01:1291481..1294653
				String link = baseJbrowseURL + "&loc=";
				link += blastResultBean.getSubject() + ":";
				link += blastResultBean.getsStart() + ".." + blastResultBean.getsEnd();
				
				if (blastResultBean.getSubject().toLowerCase().startsWith("chr")) {
					blastResultBean.setLink(link);
				} 
				else {
					blastResultBean.setLink("");
				}
				blastResultBeans.add(blastResultBean);
			}
			scanner.close();
			
			return blastResultBeans;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<BlastResultBean> parseBlastResultProtein(String blastResult, String baseJbrowseURL, DB mapdb, String featureCacheString, String proteinCacheString) {
		try {
			HTreeMap<String, FeatureBean> featureCache = (HTreeMap<String, FeatureBean>)mapdb.hashMap(featureCacheString).createOrOpen();
			HTreeMap<String, ProteinBean> proteinCache = (HTreeMap<String, ProteinBean>)mapdb.hashMap(proteinCacheString).createOrOpen();
			
			
			List<BlastResultBean> blastResultBeans = new ArrayList<BlastResultBean>();
	        
			//String blastResult = Utils.getTextFromURL(blastResultURLcc);
			if(blastResult == null) return blastResultBeans;
			
			Scanner scanner = new Scanner(blastResult);
			while (scanner.hasNextLine()) {
				//# Fields: query id, query length, subject id, subject length, % identity, alignment length, q. start, q. end, s. start, s. end, subject strand, evalue, bit score
				//# 2 hits found
				//Manes.S111100.1.p	931	Manes.S111100.1.p	930	100.00	930	1	930	1	930	N/A	0.0	 1902

				String line = scanner.nextLine().trim();
				if(line.startsWith("#")) continue;
				
				String[] data = line.split("\\s+");
				
				BlastResultBean blastResultBean = new BlastResultBean();
				blastResultBean.setqName(data[0].trim());
				blastResultBean.setProtein(data[2].trim());
				blastResultBean.setpStart(Long.valueOf(Long.parseLong(data[8].trim())));
				blastResultBean.setpEnd(Long.valueOf(Long.parseLong(data[9].trim())));
				blastResultBean.setpLength(Long.valueOf(Long.parseLong(data[3].trim())));
				blastResultBean.setIdentity(Double.valueOf(Double.parseDouble(data[4].trim())));
				blastResultBean.setqLength(Long.valueOf(Long.parseLong(data[1].trim())));
				blastResultBean.setaLength(Long.valueOf(Long.parseLong(data[5].trim())));
				blastResultBean.setqStart(Long.valueOf(Long.parseLong(data[6].trim())));
				blastResultBean.setqEnd(Long.valueOf(Long.parseLong(data[7].trim())));
				blastResultBean.setE(Double.valueOf(Double.parseDouble(data[11].trim())));
				blastResultBean.setBit(Double.valueOf(Double.parseDouble(data[12].trim())));
				
				ProteinBean proteinBean = proteinCache.get(blastResultBean.getProtein());
				if(proteinBean!=null){
					FeatureBean featureBean = featureCache.get(proteinBean.getLocus());
					if(featureBean!=null){
						blastResultBean.setLocus(featureBean.getId());
						blastResultBean.setlLength(Math.abs(featureBean.getEnd()-featureBean.getStart()) + 1L);
						blastResultBean.setlStart(blastResultBean.getpStart());
						blastResultBean.setlEnd(blastResultBean.getpEnd());
						blastResultBean.setlType(featureBean.getType());
							
							
						blastResultBean.setSubject(featureBean.getChromosome());
							
						blastResultBean.setsStart(featureBean.getStart() + Long.valueOf(Long.parseLong(data[8].trim()) - 1L));
						blastResultBean.setsEnd(featureBean.getStart() + Long.valueOf(Long.parseLong(data[9].trim())) - 1L);
							
						blastResultBean.setStrd(featureBean.getStrand());
					}
				}
				
				//http://www.cassavagenome.org/cgh/jbrowse/index.html?tracks=ReferenceSequence-1,Genes-1&loc=Chromosome01:1291481..1294653
				String link = baseJbrowseURL + "&loc=";
				link += blastResultBean.getSubject() + ":";
				link += blastResultBean.getsStart() + ".." + blastResultBean.getsEnd();
				
				if (blastResultBean.getSubject().toLowerCase().startsWith("chr")) {
					blastResultBean.setLink(link);
				} 
				else {
					blastResultBean.setLink("");
				}
				blastResultBeans.add(blastResultBean);
			}
			scanner.close();
			
			return blastResultBeans;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
