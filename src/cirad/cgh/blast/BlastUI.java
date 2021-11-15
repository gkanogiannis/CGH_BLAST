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

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import javax.servlet.annotation.WebServlet;

import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.tree.xpath.XPathExpressionEngine;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.mapdb.DB;
import org.mapdb.HTreeMap;

import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;


import cirad.cgh.blast.beans.BlastInputBean;
import cirad.cgh.blast.beans.BlastResultBean;
import cirad.cgh.blast.beans.FeatureBean;
import cirad.cgh.blast.beans.ProteinBean;
import cirad.cgh.blast.listeners.SubmitClickListener;
import cirad.cgh.blast.view.BlastForm;
import cirad.cgh.blast.view.BlastResult;

//@Theme("tests-valo")
@Title("Blast")
//@PreserveOnRefresh
public class BlastUI extends UI {

	private static final long serialVersionUID = -6944614353050699370L;
	
	private XMLConfiguration config;
	private HorizontalSplitPanel hsplit;
	private BlastForm blastForm;
	private BlastResult blastResult;
	
	private BlastRunner blastRunner;
	
	private static DB mapdb;

	//@WebServlet(value = {"/app/*", "/VAADIN/*"}, asyncSupported = true)
	@WebServlet(value = {"/*"}, asyncSupported = true)
	@VaadinServletConfiguration(productionMode = true, ui = BlastUI.class)
	public static class BlastServlet extends VaadinServlet implements Serializable {
		private static final long serialVersionUID = 4514134119516822964L;
	}
	
	public XMLConfiguration getConfig(){
		return config;
	}
	
	private XMLConfiguration initConfig(){
		System.out.println("INIT");
		try {
			File base = this.getSession().getService().getBaseDirectory();
			File webinf = new File(base,"WEB-INF");
			File tmp = new File(base, "tmp");
			if(!tmp.exists()) tmp.mkdirs();
				
			XMLConfiguration config = new XMLConfiguration(new File(webinf,"config.xml"));
			
			config.setProperty("base", base.getAbsolutePath());
			
			config.setExpressionEngine(new XPathExpressionEngine());
			
			return config;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private void initCache() {
		Thread t =  new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					File f;
					if(config.getString("baseMapDB").length()>0)
						f = new File(config.getString("baseMapDB"));
					else
						f = new File(config.getString("base"),"feature_protein_transcript_cache.mapdb");
					
					mapdb = MapDBSingleton.getDB(f);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		t.start();	
	}

	@Override
	protected void init(VaadinRequest request) {
		config = initConfig();
		
		initCache();
		
		Panel mainPanel = new Panel("BLAST");
		
		hsplit = new HorizontalSplitPanel();
		//hsplit.setLocked(true);
		hsplit.setSplitPosition(33.0f, Sizeable.Unit.PERCENTAGE);
		mainPanel.setContent(hsplit);
		
		blastForm = new BlastForm(this, config);
		blastForm.setFormSubmitHandler(getSubmitClickListener(this));
		
		hsplit.setFirstComponent(blastForm);
		
		setTheme("tests-valo-reindeer");
		
		setContent(mainPanel);
	}
	
	@Override
    protected void refresh(VaadinRequest request) {
        System.out.println("refresh");
    }
	
	private SubmitClickListener<BlastInputBean> getSubmitClickListener(final BlastUI blastUI) {
		return new SubmitClickListener<BlastInputBean>() {

			@Override
			public void onSubmit(BlastInputBean blastInputBean) {
				if (((blastInputBean.getQueryFromArea() == null) || (blastInputBean.getQueryFromArea().length() < 1))
				 && ((blastInputBean.getQueryFromFile() == null) || (blastInputBean.getQueryFromFile().length() < 1))) {
					System.out.println("No query!");
					return;
				}
				
				blastResult = new BlastResult(config);
				blastResult.setVisible(false);
				hsplit.setSecondComponent(blastResult);
				
				blastForm.blastStart();
				
				blastRunner = new BlastRunner(blastUI, blastInputBean);
				Thread t = new Thread(blastRunner);
				t.start();
			}
			
		};
	}
	
	public void blastStop(){
		if(blastRunner!=null) blastRunner.blastStop();
		hsplit.setSecondComponent(null);
	}

	@SuppressWarnings("unchecked")
	public void blastEnd(String blastResultURLcc, BlastInputBean blastInputBean) {
		UUID uuid = UUID.randomUUID();
		try {
			String blastResultTxt = Utils.getTextFromURL(blastResultURLcc);
			if(blastResultTxt!=null) {
				String blastResultFileName = "tmp." + uuid + ".txt";
				File blastResultFile = new File(new File(new File(config.getString("base")), "tmp"), blastResultFileName);
				FileUtils.writeStringToFile(blastResultFile, blastResultTxt, Charsets.UTF_8);
				String blastResultURL = UI.getCurrent().getPage().getLocation().toString() + "tmp/" + blastResultFileName;
				System.out.println("blastResultURL:"+blastResultURL);
				
				//
				List<BlastResultBean> blastResultBeans = new ArrayList<BlastResultBean>();
				String database = blastInputBean.getDatabase();
				String methodSuffix = config.getString("databases/database[@id='" + database + "']/methodSuffix");
				String featureCacheString = config.getString("databases/database[@id='" + database + "']/feature_cache");
				String proteinCacheString = config.getString("databases/database[@id='" + database + "']/protein_cache");
				String tracksString = config.getString("databases/database[@id='" + database + "']/tracks");
				Method method = Utils.class.getMethod("parseBlastResult"+methodSuffix, 
						String.class, String.class, DB.class, String.class, String.class);
				blastResultBeans = (List<BlastResultBean>)method.invoke(null, blastResultTxt, 
						config.getString("baseJbrowseURL")+"?"+tracksString, mapdb, featureCacheString, proteinCacheString);
				
				
				List<String[]> links = new ArrayList<String[]>();
				String excelFileLink = createExcelFileLink(uuid, blastResultBeans);
				links.add(new String[]{"Download Blast result txt", blastResultURL});
				links.add(new String[]{"Download Excel result file", excelFileLink});
				if(Boolean.parseBoolean(config.getString("databases/database[@id='" + database + "']/@outfasta"))) {
					if(blastInputBean.isFastaGene()) {
						links.addAll(createGeneDownloadFileLink(uuid, blastResultBeans, (HTreeMap<String, FeatureBean>)mapdb.hashMap(featureCacheString).createOrOpen()));
					}
					if(blastInputBean.isFastaCDS()) {
						links.addAll(createCDSDownloadFileLink(uuid, blastResultBeans, (HTreeMap<String, FeatureBean>)mapdb.hashMap(featureCacheString).createOrOpen()));
					}
					if(blastInputBean.isFastaProt()) {
						links.addAll(createProteinDownloadFileLink(uuid, blastResultBeans, (HTreeMap<String, FeatureBean>)mapdb.hashMap(featureCacheString).createOrOpen(),
								(HTreeMap<String, ProteinBean>)mapdb.hashMap(proteinCacheString).createOrOpen()));
					}
				}
				
				blastResult.draw(uuid, blastInputBean, blastResultBeans, links);
				blastResult.setVisible(true);
			}
			
			blastForm.blastEnd();
		} 
		catch (Exception e) {
			e.printStackTrace();
			blastForm.blastEnd();
		}
	}
	
	private String createExcelFileLink(UUID uuid, List<BlastResultBean> blastResultBeans) {
		String excelURL = "";
		try {
			String excelFileName = "tmp." + uuid + ".xls";
			File excelFile = new File(new File(new File(config.getString("base")), "tmp"), excelFileName);
			excelURL = UI.getCurrent().getPage().getLocation().toString() + "tmp/" + excelFileName;
			FileOutputStream fileOut = new FileOutputStream(excelFile);
			
			Class<?> cl = BlastResultBean.class;
			BeanInfo info = Introspector.getBeanInfo(cl);  
	        PropertyDescriptor[] props = info.getPropertyDescriptors();
			String[] fieldNames = new String[props.length-1];
		    Method[] getterMethods = new Method[props.length-1];
		    int j=0;
		    for(int i=0; i<props.length; i++) {  
		    	PropertyDescriptor pd = props[i];
		    	if(pd.getDisplayName().equalsIgnoreCase("class")) continue;
		    	fieldNames[j] = pd.getDisplayName();
		    	getterMethods[j] = pd.getReadMethod();
		    	j++;
	        }
		    
		    Workbook wb = new HSSFWorkbook();
			Sheet sheet = wb.createSheet("BLAST Result");
			Row row = sheet.createRow((short)0);
		    for(int i=0; i<fieldNames.length; i++) {
		    	Cell cell = row.createCell(i);
			    cell.setCellValue(fieldNames[i]);
		    }
		    
		    j=1;
		    for(BlastResultBean blastResultBean : blastResultBeans){
		    	row = sheet.createRow((short)j);
		    	j++;
		    	 for(int i=0; i<fieldNames.length; i++) {
		    		 Cell cell = row.createCell(i);
		    		 cell.setCellValue(String.valueOf(getterMethods[i].invoke(blastResultBean, new Object[]{})));
		    	 }
		    }
		    
		    wb.close();
		    wb.write(fileOut);
		    fileOut.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return excelURL;
	}
	
	private List<String[]> createGeneDownloadFileLink(UUID uuid, List<BlastResultBean> blastResultBeans, HTreeMap<String, FeatureBean> cache) {
		List<String[]> link = new ArrayList<String[]>();
		try {
			HashSet<String> alreadyIn = new HashSet<String>();
			StringBuffer sequence = new StringBuffer();
			
			for(BlastResultBean blastResultBean : blastResultBeans){
				FeatureBean featureBean= cache.get(blastResultBean.getLocus());
				if(featureBean!=null){
					if(featureBean!=null && featureBean.getType().equalsIgnoreCase("gene") && featureBean.getId().length()>0 && featureBean.getSequence().length()>0) {
						if(alreadyIn.add(featureBean.getId()))
							sequence.append(">"+featureBean.getId()+"\n"+featureBean.getSequence()+"\n");
					}
					else if(featureBean!=null && !featureBean.getType().equalsIgnoreCase("gene")) {
						FeatureBean parentBean = cache.get(getGeneParent(featureBean.getId(), cache));
						if(parentBean!=null){
							if(parentBean!=null && parentBean.getId().length()>0 && parentBean.getSequence().length()>0) {
								if(alreadyIn.add(parentBean.getId()))
									sequence.append(">"+parentBean.getId()+"\n"+parentBean.getSequence()+"\n");
							}
						}
					}
				}
			}
			
			String fastaFileName = "tmp." + uuid + "."+"gene"+".fa";
			File fastaFile = new File(new File(new File(config.getString("base")), "tmp"), fastaFileName);
			FileUtils.writeStringToFile(fastaFile, sequence.toString(), Charsets.UTF_8);
			String fastaURL = UI.getCurrent().getPage().getLocation().toString() + "tmp/" + fastaFileName;
			//System.out.println(fastaURL);
			
			link.add(new String[]{"Download "+"gene"+" sequences fasta", fastaURL});
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return link;
	}
	
	private String getGeneParent(String feature, HTreeMap<String, FeatureBean> cache) {
		try {
			FeatureBean featureBean = cache.get(feature);
			if(featureBean!=null){
				if(featureBean!=null){
					if(featureBean.getType().equalsIgnoreCase("gene")) return featureBean.getId();
					
					FeatureBean parentBean = cache.get(featureBean.getParent());
					if(parentBean!=null){
						if(parentBean!=null && parentBean.getType().equalsIgnoreCase("gene")) {
							return parentBean.getId();
						}
						else if(parentBean!=null && !parentBean.getType().equalsIgnoreCase("gene")) {
							return getGeneParent(parentBean.getParent(), cache);
						}
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private List<String[]> createCDSDownloadFileLink(UUID uuid, List<BlastResultBean> blastResultBeans, HTreeMap<String, FeatureBean> cache) {
		List<String[]> link = new ArrayList<String[]>();
		try {
			HashSet<String> alreadyIn = new HashSet<String>();
			StringBuffer sequence = new StringBuffer();
			
			for(BlastResultBean blastResultBean : blastResultBeans){
				FeatureBean featureBean = cache.get(blastResultBean.getLocus());
				if(featureBean!=null){
					if(featureBean!=null && featureBean.getType().equalsIgnoreCase("CDS") && featureBean.getId().length()>0 && featureBean.getSequence().length()>0) {
						if(alreadyIn.add(featureBean.getId()))
							sequence.append(">"+featureBean.getId()+"\n"+featureBean.getSequence()+"\n");
					}
					else if(featureBean!=null && !featureBean.getType().equalsIgnoreCase("CDS")) {
						List<String> children = new ArrayList<String>();
						getCDSChildren(featureBean.getId(), cache, children);
						for(String child : children){
							FeatureBean childBean = cache.get(child);
							if(childBean!=null){
								if(childBean!=null && childBean.getId().length()>0 && childBean.getSequence().length()>0) {
									if(alreadyIn.add(childBean.getId()))
										sequence.append(">"+childBean.getId()+"\n"+childBean.getSequence()+"\n");
								}
							}
						}
					}
				}
			}
			
			String fastaFileName = "tmp." + uuid + "."+"CDS"+".fa";
			File fastaFile = new File(new File(new File(config.getString("base")), "tmp"), fastaFileName);
			FileUtils.writeStringToFile(fastaFile, sequence.toString(), Charsets.UTF_8);
			String fastaURL = UI.getCurrent().getPage().getLocation().toString() + "tmp/" + fastaFileName;
			//System.out.println(fastaURL);
			
			link.add(new String[]{"Download "+"CDS"+" sequences fasta", fastaURL});
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return link;
	}
	
	private void getCDSChildren(String feature, HTreeMap<String, FeatureBean> cache, List<String> children) {
		try {
			FeatureBean featureBean = cache.get(feature);
			if(featureBean!=null){
				if(featureBean!=null){
					if(featureBean.getType().equalsIgnoreCase("CDS")) return;
					
					for(String child : featureBean.getChildren()) {
						FeatureBean childBean = cache.get(child);
						if(childBean!=null){
							if(childBean!=null && childBean.getType().equalsIgnoreCase("CDS")) {
								children.add(childBean.getId());
							}
							else if(childBean!=null && !childBean.getType().equalsIgnoreCase("CDS")) {
								getCDSChildren(childBean.getId(), cache, children);
							}
						}
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private List<String[]> createProteinDownloadFileLink(UUID uuid, List<BlastResultBean> blastResultBeans, HTreeMap<String, FeatureBean> featureCache, HTreeMap<String, ProteinBean> proteinCache) {
		List<String[]> link = new ArrayList<String[]>();
		try {
			HashSet<String> alreadyIn = new HashSet<String>();
			StringBuffer sequence = new StringBuffer();
			
			for(BlastResultBean blastResultBean : blastResultBeans){
				FeatureBean featureBean = featureCache.get(blastResultBean.getLocus());
				if(featureBean!=null){
					if(featureBean!=null && !featureBean.getType().equalsIgnoreCase("mRNA")){
						ProteinBean parentBean = proteinCache.get(getmRNAParent(featureBean.getId(), featureCache));
						if(parentBean!=null) {
							if(parentBean!=null && parentBean.getSequence().length()>0) {
								if(alreadyIn.add(parentBean.getId()))
									sequence.append(">"+parentBean.getId()+"\n"+parentBean.getSequence()+"\n");
							}
						}
						List<String> children = new ArrayList<String>();
						getmRNAChildren(featureBean.getId(), featureCache, children);
						for(String child : children){
							ProteinBean pr = proteinCache.get(child.trim());
							if(pr!=null){
								if(pr!=null && pr.getSequence().length()>0) {
									if(alreadyIn.add(pr.getId()))
										sequence.append(">"+pr.getId()+"\n"+pr.getSequence()+"\n");
								}
							}
						}
					}
					else if(featureBean!=null && featureBean.getType().equalsIgnoreCase("mRNA")){
						ProteinBean pr = proteinCache.get(featureBean.getId());
						if(pr!=null) {
							if(pr!=null && pr.getSequence().length()>0) {
								if(alreadyIn.add(pr.getId()))
									sequence.append(">"+pr.getId()+"\n"+pr.getSequence()+"\n");
							}
						}
					}
				}
			}
			
			String fastaFileName = "tmp." + uuid + "."+"protein"+".fa";
			File fastaFile = new File(new File(new File(config.getString("base")), "tmp"), fastaFileName);
			FileUtils.writeStringToFile(fastaFile, sequence.toString(), Charsets.UTF_8);
			String fastaURL = UI.getCurrent().getPage().getLocation().toString() + "tmp/" + fastaFileName;
			//System.out.println(fastaURL);
			
			link.add(new String[]{"Download "+"protein"+" sequences fasta", fastaURL});
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return link;
	}
	
	private String getmRNAParent(String feature, HTreeMap<String, FeatureBean> cache) {
		try {
			FeatureBean featureBean = cache.get(feature);
			if(featureBean!=null){
				if(featureBean!=null){
					if(featureBean.getType().equalsIgnoreCase("mRNA")) return featureBean.getId();
					
					FeatureBean parentBean = cache.get(featureBean.getParent());
					if(parentBean!=null){
						if(parentBean!=null && parentBean.getType().equalsIgnoreCase("mRNA")) {
							return parentBean.getId();
						}
						else if(parentBean!=null && !parentBean.getType().equalsIgnoreCase("mRNA")) {
							return getmRNAParent(parentBean.getParent(), cache);
						}
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	private void getmRNAChildren(String feature, HTreeMap<String, FeatureBean> cache, List<String> children) {
		try {
			FeatureBean featureBean = cache.get(feature);
			if(featureBean!=null){
				if(featureBean!=null){
					if(featureBean.getType().equalsIgnoreCase("mRNA")) return;
					
					for(String child : featureBean.getChildren()) {
						FeatureBean childBean = cache.get(child);
						if(childBean!=null){
							if(childBean!=null && childBean.getType().equalsIgnoreCase("mRNA")) {
								children.add(childBean.getId());
							}
							else if(childBean!=null && !childBean.getType().equalsIgnoreCase("mRNA")) {
								getCDSChildren(childBean.getId(), cache, children);
							}
						}
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
