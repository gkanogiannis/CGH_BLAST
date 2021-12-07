/*
 *
 * CGH_BLAST ciat.cgh.blast.ws.tblastx.AppServicePortType
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

package ciat.cgh.blast.ws.tblastx;

public interface AppServicePortType extends java.rmi.Remote {
    public ciat.cgh.blast.ws.tblastx.types.AppMetadataType getAppMetadata(ciat.cgh.blast.ws.tblastx.types.AppMetadataInputType getAppMetadataInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.tblastx.types.FaultType;
    public ciat.cgh.blast.ws.tblastx.types.AppConfigType getAppConfig(ciat.cgh.blast.ws.tblastx.types.AppConfigInputType getAppConfigInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.tblastx.types.FaultType;
    public ciat.cgh.blast.ws.tblastx.types.SystemInfoType getSystemInfo(ciat.cgh.blast.ws.tblastx.types.SystemInfoInputType getSystemInfoInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.tblastx.types.FaultType;
    public ciat.cgh.blast.ws.tblastx.types.JobSubOutputType launchJob(ciat.cgh.blast.ws.tblastx.types.JobInputType launchJobInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.tblastx.types.FaultType;
    public ciat.cgh.blast.ws.tblastx.types.BlockingOutputType launchJobBlocking(ciat.cgh.blast.ws.tblastx.types.JobInputType launchJobBlockingInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.tblastx.types.FaultType;
    public ciat.cgh.blast.ws.tblastx.types.StatusOutputType queryStatus(java.lang.String queryStatusInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.tblastx.types.FaultType;
    public ciat.cgh.blast.ws.tblastx.types.JobStatisticsType getJobStatistics(java.lang.String getJobStatisticsInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.tblastx.types.FaultType;
    public ciat.cgh.blast.ws.tblastx.types.JobOutputType getOutputs(java.lang.String getOutputsInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.tblastx.types.FaultType;
    public byte[] getOutputAsBase64ByName(ciat.cgh.blast.ws.tblastx.types.OutputsByNameInputType getOutputAsBase64ByNameInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.tblastx.types.FaultType;
    public ciat.cgh.blast.ws.tblastx.types.StatusOutputType destroy(java.lang.String destroyInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.tblastx.types.FaultType;
}
