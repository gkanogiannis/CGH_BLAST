/*******************************************************************************
 * Copyright (C) 2016 <ciat>
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

import cirad.cgh.blast.beans.BlastInputBean;

import java.io.Serializable;
import java.rmi.RemoteException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.xml.rpc.ServiceException;

import org.apache.commons.configuration.XMLConfiguration;

public class BlastWebServiceCall implements Serializable {

	private static final long serialVersionUID = -2639614023778185464L;
	
	private XMLConfiguration config;

	public BlastWebServiceCall(XMLConfiguration config) {
		this.config = config;
	}

	@SuppressWarnings("unused")
	public IBlastWebServiceCall makeBlastCall(BlastInputBean blastInputBean, int threads) throws Exception {
		String program = blastInputBean.getProgram();
		
		IBlastWebServiceCall blastCall = null;
		String jobID = null;
		
		switch (program) {
		case "1":
			blastCall = new BlastNCall(config);
			jobID = blastCall.makeBlastCall(blastInputBean, threads);
			break;
		case "2":
			blastCall = new BlastPCall(config);
			jobID = blastCall.makeBlastCall(blastInputBean, threads);
			break;
		case "3":
			blastCall = new BlastXCall(config);
			jobID = blastCall.makeBlastCall(blastInputBean, threads);
			break;
		case "4":
			blastCall = new TBlastNCall(config);
			jobID = blastCall.makeBlastCall(blastInputBean, threads);
			break;
		case "5":
			blastCall = new TBlastXCall(config);
			jobID = blastCall.makeBlastCall(blastInputBean, threads);
			break;	
		default:
			break;
		}
			
		return blastCall;
	}
	
}

interface IBlastWebServiceCall {
	public int getStatusCode();
	public String makeBlastCall(BlastInputBean blastInputBean, int threads);
	public String getResultURL();
}

class BlastNCall implements IBlastWebServiceCall {
	
	private XMLConfiguration config;
	
	private ciat.cgh.blast.ws.blastn.AppServicePortType servicePort;
	private String jobID = null;
	
	static {
	    HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier()
	        {
	            public boolean verify(String hostname, SSLSession session)
	            {
	                // ip address of the service URL(like.23.28.244.244)
	                //if (hostname.equals("172.22.55.12"))
	                //    return true;
	                //return false;
	            	
	            	return true;
	            }
	        });
	}
	
	public BlastNCall(XMLConfiguration config) {
		this.config = config;
	}
	
	public synchronized ciat.cgh.blast.ws.blastn.AppServicePortType getService() {
		if (servicePort == null) {
			try {
				servicePort = new ciat.cgh.blast.ws.blastn.AppServiceLocator().getAppServicePort();
			} 
			catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		return servicePort;
	}
	
	public synchronized int getStatusCode() {
		try {
			if (servicePort == null) {
				servicePort = getService();
			}
			return servicePort.queryStatus(jobID).getCode();
		} 
		catch (Exception e) {
			return -1;
		}
	}
	
	public synchronized String getResultURL() {
		if (servicePort == null) {
			servicePort = getService();
		}
		
		ciat.cgh.blast.ws.blastn.types.OutputFileType outFile = null;
		ciat.cgh.blast.ws.blastn.types.JobOutputType outputs = null;
		try {
			outputs = servicePort.getOutputs(jobID);
		} 
		catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
		
		for(ciat.cgh.blast.ws.blastn.types.OutputFileType tmp : outputs.getOutputFile()){
			if(tmp.getUrl().toString().endsWith("result.txt"))
				outFile = tmp;
		}
		
		if(outFile != null){
			return outFile.getUrl().toString();
		}
		else{
			return null;
		}
	}
	
	//Returns jobID
	@SuppressWarnings("unused")
	public synchronized String makeBlastCall(BlastInputBean blastInputBean, int threads) {
		if (servicePort == null) {
			servicePort = getService();
		}
		
		String query = "";
		if ((blastInputBean.getQueryFromArea() != null) && (blastInputBean.getQueryFromArea().length() > 0)) {
			query = blastInputBean.getQueryFromArea();
		} 
		else if ((blastInputBean.getQueryFromFile() != null) && (blastInputBean.getQueryFromFile().length() > 0)) {
			query = blastInputBean.getQueryFromFile();
		}
		String program = blastInputBean.getProgram();
		String database = blastInputBean.getDatabase();
		Double evalue = Double.valueOf(blastInputBean.getE());
		Integer alignements = blastInputBean.getNumAlign();
		Boolean filter = blastInputBean.isFilter();
		
		//servicePort = new ciat.cgh.blast.ws.blastn.AppServiceLocator().getAppServicePort();

		ciat.cgh.blast.ws.blastn.types.InputFileType inFileType0 = new ciat.cgh.blast.ws.blastn.types.InputFileType("context.txt", "blast".getBytes(), null, null);
		ciat.cgh.blast.ws.blastn.types.InputFileType inFileType1 = new ciat.cgh.blast.ws.blastn.types.InputFileType("query.txt", query.getBytes(), null, null);
		
		String outfmt = "\"7 qseqid qlen sseqid slen pident length qstart qend sstart send sstrand evalue bitscore\" ";
		
		StringBuilder arguments = new StringBuilder();
		String bank = this.config.getString("databases/database[@id='" + database + "']/bank");
		arguments.append(" -query query.txt -out result.txt -max_target_seqs " + alignements + " ");
		arguments.append(" -db \"" + bank + "\" ");
		//if(blastInputBean.isFilter()) arguments.append(" -dust ");
		arguments.append(" -num_threads " + threads + " -evalue " + evalue + " -outfmt " + outfmt);
		
		//System.out.println(arguments.toString());
		
		ciat.cgh.blast.ws.blastn.types.JobInputType jobIn = new ciat.cgh.blast.ws.blastn.types.JobInputType(
				arguments.toString(),//argList, 
				threads,//numProcs, 
				null,//userEmail, 
				null,//password, 
				null,//wallClockTime, 
				new ciat.cgh.blast.ws.blastn.types.InputFileType[]{inFileType0, inFileType1},//inputFile, 
				null,//extractInputs, 
				null);//sendNotification);
		
		
		//Non Blocking
		ciat.cgh.blast.ws.blastn.types.JobSubOutputType jobOut;
		try {
			jobOut = servicePort.launchJob(jobIn);
			jobID = jobOut.getJobID();
			return jobID;
		} 
		catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}

		
		
		/*
		//Blocking
		JobOutputType jobOut = servicePort.launchJobBlocking(jobIn).getJobOut();

		OutputFileType outFile = null;
		for(OutputFileType tmp : jobOut.getOutputFile()){
			if(tmp.getUrl().toString().endsWith("result.txt"))
				outFile = tmp;
		}
		
		if(outFile != null){
			//System.out.println(outFile.getUrl().toString());
			return outFile.getUrl().toString();
		}
		else{
			return null;
		}
		*/
	}
	
}


class BlastPCall implements IBlastWebServiceCall {
	
	private XMLConfiguration config;
	
	private ciat.cgh.blast.ws.blastp.AppServicePortType servicePort;
	private String jobID = null;
	
	static {
	    HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier()
	        {
	            public boolean verify(String hostname, SSLSession session)
	            {
	                // ip address of the service URL(like.23.28.244.244)
	                //if (hostname.equals("172.22.55.12"))
	                //    return true;
	                //return false;
	            	
	            	return true;
	            }
	        });
	}
	
	public BlastPCall(XMLConfiguration config) {
		this.config = config;
	}
	
	public synchronized ciat.cgh.blast.ws.blastp.AppServicePortType getService() {
		if (servicePort == null) {
			try {
				servicePort = new ciat.cgh.blast.ws.blastp.AppServiceLocator().getAppServicePort();
			} 
			catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		return servicePort;
	}
	
	public synchronized int getStatusCode() {
		try {
			if (servicePort == null) {
				servicePort = getService();
			}
			return servicePort.queryStatus(jobID).getCode();
		} 
		catch (Exception e) {
			return -1;
		}
	}
	
	public synchronized String getResultURL() {
		if (servicePort == null) {
			servicePort = getService();
		}
		
		ciat.cgh.blast.ws.blastp.types.OutputFileType outFile = null;
		ciat.cgh.blast.ws.blastp.types.JobOutputType outputs = null;
		try {
			outputs = servicePort.getOutputs(jobID);
		} 
		catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
		
		for(ciat.cgh.blast.ws.blastp.types.OutputFileType tmp : outputs.getOutputFile()){
			if(tmp.getUrl().toString().endsWith("result.txt"))
				outFile = tmp;
		}
		
		if(outFile != null){
			return outFile.getUrl().toString();
		}
		else{
			return null;
		}
	}
	
	//Returns jobID
	@SuppressWarnings("unused")
	public synchronized String makeBlastCall(BlastInputBean blastInputBean, int threads) {
		if (servicePort == null) {
			servicePort = getService();
		}
		
		String query = "";
		if ((blastInputBean.getQueryFromArea() != null) && (blastInputBean.getQueryFromArea().length() > 0)) {
			query = blastInputBean.getQueryFromArea();
		} 
		else if ((blastInputBean.getQueryFromFile() != null) && (blastInputBean.getQueryFromFile().length() > 0)) {
			query = blastInputBean.getQueryFromFile();
		}
		String program = blastInputBean.getProgram();
		String database = blastInputBean.getDatabase();
		Double evalue = Double.valueOf(blastInputBean.getE());
		Integer alignements = blastInputBean.getNumAlign();
		Boolean filter = blastInputBean.isFilter();
		
		//servicePort = new ciat.cgh.blast.ws.blastn.AppServiceLocator().getAppServicePort();

		ciat.cgh.blast.ws.blastp.types.InputFileType inFileType0 = new ciat.cgh.blast.ws.blastp.types.InputFileType("context.txt", "blast".getBytes(), null, null);
		ciat.cgh.blast.ws.blastp.types.InputFileType inFileType1 = new ciat.cgh.blast.ws.blastp.types.InputFileType("query.txt", query.getBytes(), null, null);
		
		String outfmt = "\"7 qseqid qlen sseqid slen pident length qstart qend sstart send sstrand evalue bitscore\" ";
		
		StringBuilder arguments = new StringBuilder();
		String bank = this.config.getString("databases/database[@id='" + database + "']/bank");
		arguments.append(" -query query.txt -out result.txt -max_target_seqs " + alignements + " ");
		arguments.append(" -db \"" + bank + "\" ");
		arguments.append(" -num_threads " + threads + " -evalue " + evalue + " -outfmt " + outfmt);
		
		//System.out.println(arguments.toString());
		
		ciat.cgh.blast.ws.blastp.types.JobInputType jobIn = new ciat.cgh.blast.ws.blastp.types.JobInputType(
				arguments.toString(),//argList, 
				threads,//numProcs, 
				null,//userEmail, 
				null,//password, 
				null,//wallClockTime, 
				new ciat.cgh.blast.ws.blastp.types.InputFileType[]{inFileType0, inFileType1},//inputFile, 
				null,//extractInputs, 
				null);//sendNotification);
		
		
		//Non Blocking
		ciat.cgh.blast.ws.blastp.types.JobSubOutputType jobOut;
		try {
			jobOut = servicePort.launchJob(jobIn);
			jobID = jobOut.getJobID();
			return jobID;
		} 
		catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}

class BlastXCall implements IBlastWebServiceCall {
	
	private XMLConfiguration config;
	
	private ciat.cgh.blast.ws.blastx.AppServicePortType servicePort;
	private String jobID = null;
	
	static {
	    HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier()
	        {
	            public boolean verify(String hostname, SSLSession session)
	            {
	                // ip address of the service URL(like.23.28.244.244)
	                //if (hostname.equals("172.22.55.12"))
	                //    return true;
	                //return false;
	            	
	            	return true;
	            }
	        });
	}
	
	public BlastXCall(XMLConfiguration config) {
		this.config = config;
	}
	
	public synchronized ciat.cgh.blast.ws.blastx.AppServicePortType getService() {
		if (servicePort == null) {
			try {
				servicePort = new ciat.cgh.blast.ws.blastx.AppServiceLocator().getAppServicePort();
			} 
			catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		return servicePort;
	}
	
	public synchronized int getStatusCode() {
		try {
			if (servicePort == null) {
				servicePort = getService();
			}
			return servicePort.queryStatus(jobID).getCode();
		} 
		catch (Exception e) {
			return -1;
		}
	}
	
	public synchronized String getResultURL() {
		if (servicePort == null) {
			servicePort = getService();
		}
		
		ciat.cgh.blast.ws.blastx.types.OutputFileType outFile = null;
		ciat.cgh.blast.ws.blastx.types.JobOutputType outputs = null;
		try {
			outputs = servicePort.getOutputs(jobID);
		} 
		catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
		
		for(ciat.cgh.blast.ws.blastx.types.OutputFileType tmp : outputs.getOutputFile()){
			if(tmp.getUrl().toString().endsWith("result.txt"))
				outFile = tmp;
		}
		
		if(outFile != null){
			return outFile.getUrl().toString();
		}
		else{
			return null;
		}
	}
	
	//Returns jobID
	@SuppressWarnings("unused")
	public synchronized String makeBlastCall(BlastInputBean blastInputBean, int threads) {
		if (servicePort == null) {
			servicePort = getService();
		}
		
		String query = "";
		if ((blastInputBean.getQueryFromArea() != null) && (blastInputBean.getQueryFromArea().length() > 0)) {
			query = blastInputBean.getQueryFromArea();
		} 
		else if ((blastInputBean.getQueryFromFile() != null) && (blastInputBean.getQueryFromFile().length() > 0)) {
			query = blastInputBean.getQueryFromFile();
		}
		String program = blastInputBean.getProgram();
		String database = blastInputBean.getDatabase();
		Double evalue = Double.valueOf(blastInputBean.getE());
		Integer alignements = blastInputBean.getNumAlign();
		Boolean filter = blastInputBean.isFilter();
		
		//servicePort = new ciat.cgh.blast.ws.blastn.AppServiceLocator().getAppServicePort();

		ciat.cgh.blast.ws.blastx.types.InputFileType inFileType0 = new ciat.cgh.blast.ws.blastx.types.InputFileType("context.txt", "blast".getBytes(), null, null);
		ciat.cgh.blast.ws.blastx.types.InputFileType inFileType1 = new ciat.cgh.blast.ws.blastx.types.InputFileType("query.txt", query.getBytes(), null, null);
		
		String outfmt = "\"7 qseqid qlen sseqid slen pident length qstart qend sstart send sstrand evalue bitscore\" ";
		
		StringBuilder arguments = new StringBuilder();
		String bank = this.config.getString("databases/database[@id='" + database + "']/bank");
		arguments.append(" -query query.txt -out result.txt -max_target_seqs " + alignements + " ");
		arguments.append(" -db \"" + bank + "\" ");
		arguments.append(" -num_threads " + threads + " -evalue " + evalue + " -outfmt " + outfmt);
		
		//System.out.println(arguments.toString());
		
		ciat.cgh.blast.ws.blastx.types.JobInputType jobIn = new ciat.cgh.blast.ws.blastx.types.JobInputType(
				arguments.toString(),//argList, 
				threads,//numProcs, 
				null,//userEmail, 
				null,//password, 
				null,//wallClockTime, 
				new ciat.cgh.blast.ws.blastx.types.InputFileType[]{inFileType0, inFileType1},//inputFile, 
				null,//extractInputs, 
				null);//sendNotification);
		
		
		//Non Blocking
		ciat.cgh.blast.ws.blastx.types.JobSubOutputType jobOut;
		try {
			jobOut = servicePort.launchJob(jobIn);
			jobID = jobOut.getJobID();
			return jobID;
		} 
		catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}


class TBlastNCall implements IBlastWebServiceCall {
	
	private XMLConfiguration config;
	
	private ciat.cgh.blast.ws.tblastn.AppServicePortType servicePort;
	private String jobID = null;
	
	static {
	    HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier()
	        {
	            public boolean verify(String hostname, SSLSession session)
	            {
	                // ip address of the service URL(like.23.28.244.244)
	                //if (hostname.equals("172.22.55.12"))
	                //    return true;
	                //return false;
	            	
	            	return true;
	            }
	        });
	}
	
	public TBlastNCall(XMLConfiguration config) {
		this.config = config;
	}
	
	public synchronized ciat.cgh.blast.ws.tblastn.AppServicePortType getService() {
		if (servicePort == null) {
			try {
				servicePort = new ciat.cgh.blast.ws.tblastn.AppServiceLocator().getAppServicePort();
			} 
			catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		return servicePort;
	}
	
	public synchronized int getStatusCode() {
		try {
			if (servicePort == null) {
				servicePort = getService();
			}
			return servicePort.queryStatus(jobID).getCode();
		} 
		catch (Exception e) {
			return -1;
		}
	}
	
	public synchronized String getResultURL() {
		if (servicePort == null) {
			servicePort = getService();
		}
		
		ciat.cgh.blast.ws.tblastn.types.OutputFileType outFile = null;
		ciat.cgh.blast.ws.tblastn.types.JobOutputType outputs = null;
		try {
			outputs = servicePort.getOutputs(jobID);
		} 
		catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
		
		for(ciat.cgh.blast.ws.tblastn.types.OutputFileType tmp : outputs.getOutputFile()){
			if(tmp.getUrl().toString().endsWith("result.txt"))
				outFile = tmp;
		}
		
		if(outFile != null){
			return outFile.getUrl().toString();
		}
		else{
			return null;
		}
	}
	
	//Returns jobID
	@SuppressWarnings("unused")
	public synchronized String makeBlastCall(BlastInputBean blastInputBean, int threads) {
		if (servicePort == null) {
			servicePort = getService();
		}
		
		String query = "";
		if ((blastInputBean.getQueryFromArea() != null) && (blastInputBean.getQueryFromArea().length() > 0)) {
			query = blastInputBean.getQueryFromArea();
		} 
		else if ((blastInputBean.getQueryFromFile() != null) && (blastInputBean.getQueryFromFile().length() > 0)) {
			query = blastInputBean.getQueryFromFile();
		}
		String program = blastInputBean.getProgram();
		String database = blastInputBean.getDatabase();
		Double evalue = Double.valueOf(blastInputBean.getE());
		Integer alignements = blastInputBean.getNumAlign();
		Boolean filter = blastInputBean.isFilter();
		
		//servicePort = new ciat.cgh.blast.ws.blastn.AppServiceLocator().getAppServicePort();

		ciat.cgh.blast.ws.tblastn.types.InputFileType inFileType0 = new ciat.cgh.blast.ws.tblastn.types.InputFileType("context.txt", "blast".getBytes(), null, null);
		ciat.cgh.blast.ws.tblastn.types.InputFileType inFileType1 = new ciat.cgh.blast.ws.tblastn.types.InputFileType("query.txt", query.getBytes(), null, null);
		
		String outfmt = "\"7 qseqid qlen sseqid slen pident length qstart qend sstart send sstrand evalue bitscore\" ";
		
		StringBuilder arguments = new StringBuilder();
		String bank = this.config.getString("databases/database[@id='" + database + "']/bank");
		arguments.append(" -query query.txt -out result.txt -max_target_seqs " + alignements + " ");
		arguments.append(" -db \"" + bank + "\" ");
		//if(blastInputBean.isFilter()) arguments.append(" -dust ");
		arguments.append(" -num_threads " + threads + " -evalue " + evalue + " -outfmt " + outfmt);
		
		//System.out.println(arguments.toString());
		
		ciat.cgh.blast.ws.tblastn.types.JobInputType jobIn = new ciat.cgh.blast.ws.tblastn.types.JobInputType(
				arguments.toString(),//argList, 
				threads,//numProcs, 
				null,//userEmail, 
				null,//password, 
				null,//wallClockTime, 
				new ciat.cgh.blast.ws.tblastn.types.InputFileType[]{inFileType0, inFileType1},//inputFile, 
				null,//extractInputs, 
				null);//sendNotification);
		
		
		//Non Blocking
		ciat.cgh.blast.ws.tblastn.types.JobSubOutputType jobOut;
		try {
			jobOut = servicePort.launchJob(jobIn);
			jobID = jobOut.getJobID();
			return jobID;
		} 
		catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}

	}
	
}


class TBlastXCall implements IBlastWebServiceCall {
	
	private XMLConfiguration config;
	
	private ciat.cgh.blast.ws.tblastx.AppServicePortType servicePort;
	private String jobID = null;
	
	static {
	    HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier()
	        {
	            public boolean verify(String hostname, SSLSession session)
	            {
	                // ip address of the service URL(like.23.28.244.244)
	                //if (hostname.equals("172.22.55.12"))
	                //    return true;
	                //return false;
	            	
	            	return true;
	            }
	        });
	}
	
	public TBlastXCall(XMLConfiguration config) {
		this.config = config;
	}
	
	public synchronized ciat.cgh.blast.ws.tblastx.AppServicePortType getService() {
		if (servicePort == null) {
			try {
				servicePort = new ciat.cgh.blast.ws.tblastx.AppServiceLocator().getAppServicePort();
			} 
			catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		return servicePort;
	}
	
	public synchronized int getStatusCode() {
		try {
			if (servicePort == null) {
				servicePort = getService();
			}
			return servicePort.queryStatus(jobID).getCode();
		} 
		catch (Exception e) {
			return -1;
		}
	}
	
	public synchronized String getResultURL() {
		if (servicePort == null) {
			servicePort = getService();
		}
		
		ciat.cgh.blast.ws.tblastx.types.OutputFileType outFile = null;
		ciat.cgh.blast.ws.tblastx.types.JobOutputType outputs = null;
		try {
			outputs = servicePort.getOutputs(jobID);
		} 
		catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
		
		for(ciat.cgh.blast.ws.tblastx.types.OutputFileType tmp : outputs.getOutputFile()){
			if(tmp.getUrl().toString().endsWith("result.txt"))
				outFile = tmp;
		}
		
		if(outFile != null){
			return outFile.getUrl().toString();
		}
		else{
			return null;
		}
	}
	
	//Returns jobID
	@SuppressWarnings("unused")
	public synchronized String makeBlastCall(BlastInputBean blastInputBean, int threads) {
		if (servicePort == null) {
			servicePort = getService();
		}
		
		String query = "";
		if ((blastInputBean.getQueryFromArea() != null) && (blastInputBean.getQueryFromArea().length() > 0)) {
			query = blastInputBean.getQueryFromArea();
		} 
		else if ((blastInputBean.getQueryFromFile() != null) && (blastInputBean.getQueryFromFile().length() > 0)) {
			query = blastInputBean.getQueryFromFile();
		}
		String program = blastInputBean.getProgram();
		String database = blastInputBean.getDatabase();
		Double evalue = Double.valueOf(blastInputBean.getE());
		Integer alignements = blastInputBean.getNumAlign();
		Boolean filter = blastInputBean.isFilter();
		
		//servicePort = new ciat.cgh.blast.ws.blastn.AppServiceLocator().getAppServicePort();

		ciat.cgh.blast.ws.tblastx.types.InputFileType inFileType0 = new ciat.cgh.blast.ws.tblastx.types.InputFileType("context.txt", "blast".getBytes(), null, null);
		ciat.cgh.blast.ws.tblastx.types.InputFileType inFileType1 = new ciat.cgh.blast.ws.tblastx.types.InputFileType("query.txt", query.getBytes(), null, null);
		
		String outfmt = "\"7 qseqid qlen sseqid slen pident length qstart qend sstart send sstrand evalue bitscore\" ";
		
		StringBuilder arguments = new StringBuilder();
		String bank = this.config.getString("databases/database[@id='" + database + "']/bank");
		arguments.append(" -query query.txt -out result.txt -max_target_seqs " + alignements + " ");
		arguments.append(" -db \"" + bank + "\" ");
		//if(blastInputBean.isFilter()) arguments.append(" -dust ");
		arguments.append(" -num_threads " + threads + " -evalue " + evalue + " -outfmt " + outfmt);
		
		//System.out.println(arguments.toString());
		
		ciat.cgh.blast.ws.tblastx.types.JobInputType jobIn = new ciat.cgh.blast.ws.tblastx.types.JobInputType(
				arguments.toString(),//argList, 
				threads,//numProcs, 
				null,//userEmail, 
				null,//password, 
				null,//wallClockTime, 
				new ciat.cgh.blast.ws.tblastx.types.InputFileType[]{inFileType0, inFileType1},//inputFile, 
				null,//extractInputs, 
				null);//sendNotification);
		
		
		//Non Blocking
		ciat.cgh.blast.ws.tblastx.types.JobSubOutputType jobOut;
		try {
			jobOut = servicePort.launchJob(jobIn);
			jobID = jobOut.getJobID();
			return jobID;
		} 
		catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}

	}
	
}
