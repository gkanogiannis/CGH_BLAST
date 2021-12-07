/*
 *
 * CGH_BLAST ciat.cgh.blast.ws.blastn.AppServicePortType
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

package ciat.cgh.blast.ws.blastn;

public interface AppServicePortType extends java.rmi.Remote {
    public ciat.cgh.blast.ws.blastn.types.AppMetadataType getAppMetadata(ciat.cgh.blast.ws.blastn.types.AppMetadataInputType getAppMetadataInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastn.types.FaultType;
    public ciat.cgh.blast.ws.blastn.types.AppConfigType getAppConfig(ciat.cgh.blast.ws.blastn.types.AppConfigInputType getAppConfigInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastn.types.FaultType;
    public ciat.cgh.blast.ws.blastn.types.SystemInfoType getSystemInfo(ciat.cgh.blast.ws.blastn.types.SystemInfoInputType getSystemInfoInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastn.types.FaultType;
    public ciat.cgh.blast.ws.blastn.types.JobSubOutputType launchJob(ciat.cgh.blast.ws.blastn.types.JobInputType launchJobInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastn.types.FaultType;
    public ciat.cgh.blast.ws.blastn.types.BlockingOutputType launchJobBlocking(ciat.cgh.blast.ws.blastn.types.JobInputType launchJobBlockingInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastn.types.FaultType;
    public ciat.cgh.blast.ws.blastn.types.StatusOutputType queryStatus(java.lang.String queryStatusInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastn.types.FaultType;
    public ciat.cgh.blast.ws.blastn.types.JobStatisticsType getJobStatistics(java.lang.String getJobStatisticsInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastn.types.FaultType;
    public ciat.cgh.blast.ws.blastn.types.JobOutputType getOutputs(java.lang.String getOutputsInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastn.types.FaultType;
    public byte[] getOutputAsBase64ByName(ciat.cgh.blast.ws.blastn.types.OutputsByNameInputType getOutputAsBase64ByNameInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastn.types.FaultType;
    public ciat.cgh.blast.ws.blastn.types.StatusOutputType destroy(java.lang.String destroyInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastn.types.FaultType;
}
