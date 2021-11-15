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

import java.io.File;

import org.mapdb.DB;
import org.mapdb.DBMaker;

public class MapDBSingleton {
	   private static DB mapdb = null;
	   
	   protected MapDBSingleton() {
	   }
	   
	   public static DB getDB(File f) {
	      if(mapdb == null) {
	    	 try {
	    		System.out.println("making db");
				mapdb = DBMaker.fileDB(f.getCanonicalPath()).fileLockDisable().readOnly().make();
			} 
	    	 catch (Exception e) {
				e.printStackTrace();
			}
	      }
	      return mapdb;
	   }
	}
