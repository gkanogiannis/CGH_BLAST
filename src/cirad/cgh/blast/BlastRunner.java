/*
 *
 * CGH_BLAST cirad.cgh.blast.BlastRunner
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
