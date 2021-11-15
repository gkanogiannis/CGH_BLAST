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

//import java.util.concurrent.atomic.AtomicReference;

import cirad.cgh.blast.beans.BlastInputBean;

public class BlastRunner implements Runnable {
	
	//private final AtomicReference<Thread> currentThread = new AtomicReference<Thread>();

	private BlastUI blastUI;
	private BlastInputBean blastInputBean;
	
	private volatile boolean isStopped;

	public BlastRunner(BlastUI blastUI, BlastInputBean blastInputBean) {
		this.blastUI = blastUI;
		this.blastInputBean = blastInputBean;
		this.isStopped = true;
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void run() {
		//currentThread.set(Thread.currentThread());
		isStopped = false;
		try {
			int threads = 1;

			IBlastWebServiceCall blastCall = new BlastWebServiceCall(blastUI.getConfig()).makeBlastCall(blastInputBean, threads);
			
			while(!isStopped && blastCall.getStatusCode() != 8){
				try {
					Thread.currentThread().sleep(500L);
				} 
				catch (InterruptedException localInterruptedException) {
				}
			}	
			
			if(isStopped){
				return;
			}
			
			String blastResultURLcc = blastCall.getResultURL();
			System.out.println("blastResultURLcc:"+blastResultURLcc);
			
			blastUI.blastEnd(blastResultURLcc, blastInputBean);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void blastStop() {
		//currentThread.get().interrupt();
		isStopped = true;
	}
}
