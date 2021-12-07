/*
 *
 * CGH_BLAST cirad.cgh.blast.view.BlastResult
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
package cirad.cgh.blast.view;

import cirad.cgh.blast.beans.BlastInputBean;
import cirad.cgh.blast.beans.BlastResultBean;
import com.jensjansson.pagedtable.PagedTable;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.Align;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.VerticalLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.RoundRectangle2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.commons.configuration.XMLConfiguration;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class BlastResult extends VerticalLayout {
	
	private static final long serialVersionUID = -5394206154891584262L;
	
	private XMLConfiguration config;
	private List<BlastResultBean> blastResultBeans;
	List<String[]> links;

	public BlastResult(XMLConfiguration config) {
		this.config = config;
	}

	public void draw(UUID uuid, BlastInputBean blastInputBean, List<BlastResultBean> blastResultBeans, List<String[]> links) {
		this.blastResultBeans = blastResultBeans;
		this.links = links;
		
		if(this.blastResultBeans==null) this.blastResultBeans = new ArrayList<BlastResultBean>();

		//setSizeUndefined();
		setSizeFull();
		setSpacing(true);
		setMargin(true);
		setImmediate(true);
		
		try {
			drawImage(uuid);
			drawResultTable(blastInputBean);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void drawImage(UUID uuid) throws Exception {
		File base = new File(config.getString("base"));
		File tmp = new File(base, "tmp");
		if(!tmp.exists()) tmp.mkdirs();
		
		String filename = "tmp." + uuid + ".svg";
		File file = new File(tmp, filename);

		DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
		String svgNS = "http://www.w3.org/2000/svg";
		Document document = domImpl.createDocument(svgNS, "svg", null);

		SVGGraphics2D svg = new SVGGraphics2D(document);
		svg.setSVGCanvasSize(new Dimension(700, 300));

		ArrayList<Element> hits = drawChromosomes(700, 300, svg, document, svgNS);

		Element svgRoot = svg.getRoot();
		svgRoot.setAttributeNS(null, "viewBox", "0 0 " + 700 + " " + 300 + "");
		svgRoot.removeAttributeNS(null, "width");
		svgRoot.removeAttributeNS(null, "height");

		for (Element e : hits) {
			svgRoot.appendChild(e);
		}

		boolean useCSS = true;

		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		svg.stream(svgRoot, bw, useCSS, false);
		bw.flush();
		bw.close();

		FileResource res = new FileResource(file);
		Embedded object = new Embedded(null, res);
		object.setSizeFull();
		addComponent(object);
	}

	@SuppressWarnings("unchecked")
	private ArrayList<Element> drawChromosomes(int width, int height, SVGGraphics2D g2, Document document, String svgNS) {
		ArrayList<Element> hits = new ArrayList<Element>();

		List<String> chromosomeNames = (List<String>) (Object) this.config.getList("chromosomes/chromosome/@name");
		List<Long> chromosomeLengths = new ArrayList<Long>();
		
		for (String s : (List<String>) (Object) this.config.getList("chromosomes/chromosome/length")) {
			chromosomeLengths.add(Long.valueOf(s));
		}
		Long maxChromLength = (Long) Collections.max(chromosomeLengths);

		FontRenderContext frc = g2.getFontRenderContext();
		double sizeForEach = (double)width / chromosomeNames.size();
		
		for (int i = 0; i < chromosomeNames.size(); i++) {
			String chromosomeName = chromosomeNames.get(i);
			double chromosomeLength = (double)chromosomeLengths.get(i);

			g2.setPaint(Color.BLACK);
			Font font = new Font(g2.getFont().getFontName(), 1, 8);
			TextLayout tl = new TextLayout("chr" + chromosomeName.substring(chromosomeName.length() - 2), font, frc);
			float xLabel = (float) (i * sizeForEach);
			xLabel += (sizeForEach - tl.getBounds().getWidth()) / 2.0;
			tl.draw(g2, xLabel, tl.getAscent());

			double xChrom = i * sizeForEach;
			double yChrom = 25.0;
			double wChrom = sizeForEach * 0.33;
			xChrom += (sizeForEach - wChrom) / 2.0;
			double hChrom = (double)height * ((double)chromosomeLengths.get(i)/(double)maxChromLength) - yChrom;
			
			RoundRectangle2D.Double rect1 = new RoundRectangle2D.Double(xChrom, yChrom, wChrom, hChrom * 0.3, 20.0, 20.0);
			RoundRectangle2D.Double rect2 = new RoundRectangle2D.Double(xChrom, yChrom + hChrom * 0.3, wChrom, hChrom * 0.7, 20.0, 20.0);

			GradientPaint gradient = new GradientPaint((float)xChrom, (float)(yChrom), Color.black, 
													   (float)(xChrom+wChrom/2.0), (float)(yChrom), Color.orange, true);
			g2.setPaint(gradient);
			g2.fill(rect1);
			g2.draw(rect1);
			g2.fill(rect2);
			g2.draw(rect2);

			double pixelsPerNucl = hChrom / chromosomeLength;
			if(blastResultBeans!=null) {
				for (BlastResultBean blastResultBean : blastResultBeans) {
					if (blastResultBean.getSubject().equalsIgnoreCase(chromosomeName)) {
						long start = blastResultBean.getsStart().longValue();
						long end = blastResultBean.getsEnd().longValue();
						if (start > end) {
							long temp = start;
							start = end;
							end = temp;
						}
						double pixels = Math.abs(end - start) * pixelsPerNucl;
						if (pixels < 1.0) {
							pixels = 1.0;
						}
						double xHit = xChrom;
						double yHit = yChrom + start * pixelsPerNucl;
	
						Element a = document.createElementNS(svgNS, "a");
						a.setAttributeNS(null, "xlink:href", blastResultBean.getLink());
						a.setAttributeNS(null, "target", "_blank");
						Element rect = document.createElementNS(svgNS, "rect");
	
						rect.setAttributeNS(null, "x", String.valueOf(xHit));
						rect.setAttributeNS(null, "y", String.valueOf(yHit));
						rect.setAttributeNS(null, "width", String.valueOf(wChrom));
						rect.setAttributeNS(null, "height", String.valueOf(pixels));
						rect.setAttributeNS(null, "style", "fill:red; stroke:red; stroke-width:1;");
						Element title = document.createElementNS(null, "title");
						title.setTextContent(chromosomeName + ":" + blastResultBean.getsStart() + ".." + blastResultBean.getsEnd());
						rect.appendChild(title);
						a.appendChild(rect);
						hits.add(a);
					}
				}
			}
		}
		return hits;
	}

	private void drawResultTable(BlastInputBean blastInputBean) {
		//Table resultTable = new Table("BLAST result");
		PagedTable resultTable = new PagedTable();
		resultTable.setSizeFull();
		resultTable.setSelectable(true);
		resultTable.setMultiSelect(false);
		resultTable.setImmediate(true);

		BeanItemContainer<BlastResultBean> container = new BeanItemContainer<BlastResultBean>(BlastResultBean.class, blastResultBeans);

		resultTable.removeAllItems();
		resultTable.setResponsive(true);
		resultTable.setContainerDataSource(container);
		
		List<Object> columns = config.getList("databases/database[@id='" + blastInputBean.getDatabase() + "']/columns");
		resultTable.setVisibleColumns(columns.toArray(new Object[columns.size()]));
		
		resultTable.setColumnAlignment("qLength", Align.RIGHT);
		resultTable.setColumnAlignment("aLength", Align.RIGHT);
		resultTable.setColumnAlignment("qStart", Align.RIGHT);
		resultTable.setColumnAlignment("qEnd", Align.RIGHT);
		resultTable.setColumnAlignment("sStart", Align.RIGHT);
		resultTable.setColumnAlignment("sEnd", Align.RIGHT);
		resultTable.setColumnAlignment("lLength", Align.RIGHT);
		resultTable.setColumnAlignment("lStart", Align.RIGHT);
		resultTable.setColumnAlignment("lEnd", Align.RIGHT);
		resultTable.setColumnAlignment("strd", Align.CENTER);
		resultTable.setColumnAlignment("identity", Align.RIGHT);
		
		resultTable.addGeneratedColumn("link", new ColumnGenerator() {
			private static final long serialVersionUID = 7269732352173106256L;
			
			@Override
			public Component generateCell(Table source, Object itemId, Object columnId) {
				String link = ((BlastResultBean) itemId).getLink();
				if(link==null || link.equals(""))return null;
				link = "<a href=\'" + link + "\' target=\'_blank\'>JBrowse</a>";
				Label label = new Label(link, ContentMode.HTML);
				label.setSizeUndefined();
				return label;
			}
		});

		resultTable.setSelectable(false);

		//FormLayout layout = new FormLayout();
		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		layout.setMargin(true);
		layout.setSpacing(true);
		
		Label section1Label = new Label("Results (" + blastResultBeans.size() + ")");
		section1Label.addStyleName("colored");
		layout.addComponent(section1Label);
		
		for(String[] link : links){
			Link section1Down = new Link(link[0], new ExternalResource(link[1]), "_blank", 0, 0, null);
			section1Down.addStyleName("colored");
			layout.addComponent(section1Down);
		}
		
		layout.addComponent(resultTable);
		layout.addComponent(resultTable.createControls());
		layout.addStyleName("light");
		resultTable.setPageLength(10);
		resultTable.markAsDirty();

		addComponent(layout);
	}
}
