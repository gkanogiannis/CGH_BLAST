/*
 *
 * CGH_BLAST ciat.cgh.blast.ws.tblastx.AppService
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

public interface AppService extends javax.xml.rpc.Service {
    public java.lang.String getAppServicePortAddress();

    public ciat.cgh.blast.ws.tblastx.AppServicePortType getAppServicePort() throws javax.xml.rpc.ServiceException;

    public ciat.cgh.blast.ws.tblastx.AppServicePortType getAppServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
