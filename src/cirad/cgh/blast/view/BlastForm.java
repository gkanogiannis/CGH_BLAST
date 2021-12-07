/*
 *
 * CGH_BLAST cirad.cgh.blast.view.BlastForm
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

import cirad.cgh.blast.BlastUI;
import cirad.cgh.blast.beans.BlastInputBean;
import cirad.cgh.blast.listeners.SubmitClickListener;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitEvent;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.FieldGroup.CommitHandler;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.XMLConfiguration;
import org.vaadin.easyuploads.UploadField;

public class BlastForm extends VerticalLayout implements View {

	private static final long serialVersionUID = -6088821610236752623L;
	
	public static final String QUERYAREA = "queryFromArea";
	public static final String QUERYAREA_CAPTION = "Paste query(s)";
	public static final String QUERYFILE = "queryFromFile";
	public static final String QUERYFILE_CAPTION = "Upload query(s)";
	public static final String EXAMPLE_CAPTION = "Load example";
	
	public static final String PROGRAM = "program";
	public static final String PROGRAM_CAPTION = "Program";
	
	public static final String DATABASE = "database";
	public static final String DATABASE_CAPTION = "Database";
	
	public static final String EXPECT = "e";
	public static final String EXPECT_CAPTION = "Expect";
	public static final String FILTER = "filter";
	public static final String FILTER_CAPTION = "Filter for low complexity";
	public static final String ALIGNMENTS = "numAlign";
	public static final String ALIGNMENTS_CAPTION = "Alignments";
	
	public static final String GENE = "fastaGene";
	public static final String GENE_CAPTION = "Gene sequences. Does include intron sequences or UTRs";
	public static final String CDS = "fastaCDS";
	public static final String CDS_CAPTION = "CDS sequences. Does not include intron sequences or UTRs";
	public static final String PROT = "fastaProt";
	public static final String PROT_CAPTION = "Translated protein sequences";
	
	public static final String TEXT_NULL_REPRESENTATION = "";
	
	public static final String RESET_CAPTION = "Reset";
	public static final String SUBMIT_CAPTION = "BLAST";

	private XMLConfiguration config;
	
	private BlastUI blastUI;
	
	private BlastInputBean blastInputBean;
	
	private BeanFieldGroup<BlastInputBean> fieldGroup;
	
	private SubmitClickListener<BlastInputBean> submitClickListener;
	
	private Button submitButton;
	private Button resetButton;
	
	private ProgressBar progress;
	private Label status;
	
	public BlastForm(BlastUI blastUI, XMLConfiguration config) {
		this.blastUI = blastUI;
		this.config = config;
		setImmediate(true);

		initComponents();
	}

	@SuppressWarnings("unused")
	private void initComponents() {
		removeAllComponents();
		
		fieldGroup = new BeanFieldGroup<BlastInputBean>(BlastInputBean.class);
		blastInputBean = new BlastInputBean(config);
		fieldGroup.setItemDataSource(blastInputBean);
		
		FormLayout layout = new FormLayout();
		layout.setResponsive(true);
		layout.setMargin(true);
		layout.setWidth("100%");
		//layout.addStyleName("light");
		layout.setImmediate(true);
		addComponent(layout);
		
		
		Label section1;
		final TextArea queryArea;
		UploadField upload;
		//Button exampleButton;
		ClickLabel exampleLabel;
		final ComboBox program_combo;
		final ComboBox database_combo;
		Label section2;
		TextField e;
		CheckBox filter;
		TextField alignments;
		Label section3;
		final CheckBox outGene;
		final CheckBox outCds;
		final CheckBox outProt;
		Component buttonLayout;
		Component progressLayout;
		

		section1 = new Label("Input");
		section1.addStyleName("h3 colored");
		layout.addComponent(section1);
		
		exampleLabel = new  ClickLabel("<p style='text-decoration:underline;'>"+EXAMPLE_CAPTION+"</p>");
		layout.addComponent(exampleLabel);
		
		queryArea = getQueryArea(QUERYAREA_CAPTION, QUERYAREA);
		layout.addComponent(queryArea);

		upload = new UploadField();
		upload.setDisplayUpload(true);
		upload.setStorageMode(UploadField.StorageMode.MEMORY);
		upload.setCaption(QUERYFILE_CAPTION);
		upload.setFieldType(UploadField.FieldType.UTF8_STRING);
		upload.setWriteThrough(false);
		fieldGroup.bind(upload, QUERYFILE);
		layout.addComponent(upload);
		
		//exampleButton = getButton(EXAMPLE_CAPTION);
		//exampleButton.setStyleName("link"); 
		//exampleButton.addClickListener(getExampleClickListener());
		//layout.addComponent(exampleButton);

		program_combo = getComboBox(PROGRAM_CAPTION, PROGRAM, getProgramList());
		layout.addComponent(program_combo);
		database_combo = getComboBox(DATABASE_CAPTION, DATABASE, getDatabaseList("1"));
		layout.addComponent(database_combo);

		section2 = new Label("Parameters");
		section2.addStyleName("h3 colored");
		layout.addComponent(section2);
		e = getTextField(EXPECT_CAPTION, EXPECT, blastUI.getConfig().getString("defaultEvalue"), 10);
		layout.addComponent(e);
		filter = getCheckBox(FILTER_CAPTION, FILTER, false);
		//layout.addComponent(filter);
		alignments = getTextField(ALIGNMENTS_CAPTION, ALIGNMENTS, blastUI.getConfig().getString("defaultAlignments"), 10);
		layout.addComponent(alignments);

		section3 = new Label("Output");
		section3.addStyleName("h3 colored");
		layout.addComponent(section3);
		outGene = getCheckBox(GENE_CAPTION, GENE, false);
		layout.addComponent(outGene);
		outCds = getCheckBox(CDS_CAPTION, CDS, false);
		layout.addComponent(outCds);
		outProt = getCheckBox(PROT_CAPTION, PROT, false);
		layout.addComponent(outProt);
		outGene.setVisible(config.getList("output_sets/set[@database_id='1']").contains("gene"));
		outCds.setVisible(config.getList("output_sets/set[@database_id='1']").contains("cds"));
		outProt.setVisible(config.getList("output_sets/set[@database_id='1']").contains("protein"));

		layout.addComponent(new Label(""));

		buttonLayout = getSubmitLayout();
		layout.addComponent(buttonLayout);
		progressLayout = getProgressLayout();
		layout.addComponent(progressLayout);
		
		
		exampleLabel.addLayoutClickListener(new LayoutClickListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void layoutClick(LayoutClickEvent event) {
				String exampleId = config.getString("example_sets/set[@database_id='" + (String)database_combo.getValue() + "' and @program_id='" + (String)program_combo.getValue() + "']");
				String q = config.getString("databases/database[@id='" + (String)database_combo.getValue() + "']/example[@id='"+exampleId+"']");
				String header = q.substring(0, q.indexOf("\n")).trim();
				String seq = q.substring(q.indexOf("\n")).trim().replaceAll("\\s+", "");
				queryArea.setValue(header+"\n"+seq);
			}
		});
		
		program_combo.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void valueChange(ValueChangeEvent event) {
				database_combo.setContainerDataSource(getDatabaseList((String) program_combo.getValue()));
				database_combo.select(database_combo.getContainerDataSource().getItemIds().iterator().next());
				queryArea.clear();
			}
		});
		database_combo.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void valueChange(ValueChangeEvent event) {
				outGene.setVisible(config.getList("output_sets/set[@database_id='" + (String)database_combo.getValue() + "']").contains("gene"));
				outCds.setVisible(config.getList("output_sets/set[@database_id='" + (String)database_combo.getValue() + "']").contains("cds"));
				outProt.setVisible(config.getList("output_sets/set[@database_id='" + (String)database_combo.getValue() + "']").contains("protein"));
				outGene.setValue(false);
				outCds.setValue(false);
				outProt.setValue(false);
				queryArea.clear();
			}
		});
		

		initCommitHandler();
	}

	private void initCommitHandler() {
		fieldGroup.addCommitHandler(new CommitHandler() {
			private static final long serialVersionUID = 1L;

			@Override
			public void preCommit(CommitEvent commitEvent) throws CommitException {
				// Check for validations
				fieldGroup.isValid();
			}

			@Override
			public void postCommit(CommitEvent commitEvent) throws CommitException {
				if (submitClickListener != null && fieldGroup != null) {
					BlastInputBean bean = fieldGroup.getItemDataSource().getBean();
					submitClickListener.onSubmit(bean);
				}
			}
		});
	}

	private TextArea getQueryArea(String caption, String bindName) {
		//Label label = new Label("<p><b>html</b></p>",ContentMode.HTML);
		//label.setWidth(null);
		//layout.addComponent(label);
		//ExpandingTextArea textArea = new ExpandingTextArea(caption);
		
		TextArea textArea = new TextArea(caption);
		textArea.setImmediate(true);
		fieldGroup.bind(textArea, bindName);
		textArea.setValidationVisible(false);
		textArea.setNullRepresentation(TEXT_NULL_REPRESENTATION);
		textArea.setNullSettingAllowed(true);
		//textArea.setSizeFull();
		textArea.setWidth("100%");
		//textArea.setMaxRows(10);

		return textArea;
	}

	private TextField getTextField(String caption, String bindName, String defaultValue, int columns) {
		TextField textField = new TextField(caption);
		textField.setImmediate(true);
		fieldGroup.bind(textField, bindName);
		textField.setValidationVisible(false);
		textField.setNullRepresentation("");
		textField.setNullSettingAllowed(true);
		textField.setColumns(columns);
		textField.setValue(defaultValue);

		return textField;
	}

	private ComboBox getComboBox(String caption, String bindName, Container container) {
		ComboBox comboBox = new ComboBox(caption);
		comboBox.setItemCaptionMode(ItemCaptionMode.PROPERTY);
		comboBox.setItemCaptionPropertyId("name");
		comboBox.setContainerDataSource(container);
		comboBox.setImmediate(true);
		comboBox.setValidationVisible(false);
		comboBox.setNewItemsAllowed(false);
		comboBox.setFilteringMode(FilteringMode.CONTAINS);
		comboBox.setNullSelectionAllowed(false);
		fieldGroup.bind(comboBox, bindName);
		comboBox.setTextInputAllowed(false);
		comboBox.setWidth("50%");
		comboBox.select(container.getItemIds().iterator().next());

		return comboBox;
	}

	@SuppressWarnings("unchecked")
	private Container getProgramList() {
		IndexedContainer container = new IndexedContainer();
		container.addContainerProperty("id", String.class, "");
		container.addContainerProperty("name", String.class, "");
		List<String> ids = (List<String>)(Object)blastUI.getConfig().getList("programs/program/@id");
		List<String> names = (List<String>)(Object)blastUI.getConfig().getList("programs/program/name");
		for (int i = 0; i < ids.size(); i++) {
			String id = (String) ids.get(i);
			String name = (String) names.get(i);
			Item newItem = container.addItem(id);
			newItem.getItemProperty("id").setValue(id);
			newItem.getItemProperty("name").setValue(name);
		}
		return container;
	}

	@SuppressWarnings("unchecked")
	private Container getDatabaseList(String program_id) {
		IndexedContainer container = new IndexedContainer();
		container.addContainerProperty("id", String.class, "");
		container.addContainerProperty("name", String.class, "");
		List<String> ids = (List<String>)(Object)blastUI.getConfig().getList("database_sets/set[@program_id='" + program_id + "']");
		for (String id : ids) {
			String name = config.getString("databases/database[@id='" + id + "']/name");
			Item newItem = container.addItem(id);
			newItem.getItemProperty("id").setValue(id);
			newItem.getItemProperty("name").setValue(name);
		}
		return container;
	}

	private CheckBox getCheckBox(String caption, String bindName, boolean enabled) {
		CheckBox checkBox = new CheckBox(caption);
		checkBox.setImmediate(true);
		checkBox.setEnabled(enabled);
		fieldGroup.bind(checkBox, bindName);
		checkBox.setValidationVisible(false);
		return checkBox;
	}

	private Button getButton(String caption) {
		Button button = new Button(caption);
		button.setImmediate(true);
		button.setHtmlContentAllowed(true);
		return button;
	}

	private Component getSubmitLayout() {
		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setSpacing(true);

		submitButton = getButton(SUBMIT_CAPTION);
		submitButton.addClickListener(getSubmitButtonClickListener());
		buttonLayout.addComponent(submitButton);

		resetButton = getButton(RESET_CAPTION);
		resetButton.addClickListener(getResetClickListener());
		buttonLayout.addComponent(resetButton);

		return buttonLayout;
	}

	private Component getProgressLayout() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);

		status = new Label("");
		layout.addComponent(this.status);

		progress = new ProgressBar();
		progress.setEnabled(false);
		progress.setVisible(false);
		layout.addComponent(progress);

		return layout;
	}
	
	private ClickListener getResetClickListener() {
		return new Button.ClickListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				resetForm();
			}
		};
	}

	private ClickListener getSubmitButtonClickListener() {
		return new Button.ClickListener() {
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					fieldGroup.commit();
					if ((blastInputBean.getQueryFromArea() != null)
					 && (blastInputBean.getQueryFromArea().length() > 0)) {
						System.out.println("textarea");
						System.out.println(":" + blastInputBean.getQueryFromArea().length());
					} 
					else if ((blastInputBean.getQueryFromFile() != null)
						  && (blastInputBean.getQueryFromFile().length() > 0)) {
						System.out.println("file");
						System.out.println(":" + blastInputBean.getQueryFromFile().length());
					}
					UI.getCurrent().setPollInterval(200);
				} 
				catch (CommitException e) 
				{
					Map<Field<?>, InvalidValueException> invalidFields = e.getInvalidFields();
					for (Map.Entry<Field<?>, InvalidValueException> invalidField : invalidFields.entrySet()) {
						((AbstractField) invalidField.getKey()).setValidationVisible(true);
					}
					if (invalidFields.isEmpty()) {
						e.printStackTrace();
						Notification.show("BLASTing ERROR, Please try again");
					}
				}
			}
		};
	}

	public BlastInputBean getBlastInputBean() {
		return blastInputBean;
	}

	public void setFormSubmitHandler(SubmitClickListener<BlastInputBean> submitClickListener) {
		this.submitClickListener = submitClickListener;
	}

	public void resetForm() {
		blastStop();
	}
	
	public void blastStop() {
		blastUI.blastStop();
		initComponents();
	}

	public void blastStart() {
		Thread t = new Thread() {
			public void run() {
				// Update the UI thread-safely
				UI.getCurrent().access(new Runnable() {
					@Override
					public void run() {
						progress.setIndeterminate(true);
						progress.setVisible(true);
						progress.setEnabled(true);
						submitButton.setEnabled(false);
						status.setValue("BLASTing...");
					}
				});
			}
		};
		t.start();
	}

	public void blastEnd() {
		//System.out.println("BlastForm blastEnd()");
		Thread t = new Thread() {
			public void run() {
				// Update the UI thread-safely
				UI.getCurrent().access(new Runnable() {
					@Override
					public void run() {
						status.setValue("all done");
						progress.setIndeterminate(false);
						progress.setVisible(false);
						progress.setEnabled(false);
					}
				});
				
				// Show the "all done" for a while
				try {
					sleep(1500L);
				} 
				catch (InterruptedException localInterruptedException) {
				}
				
				// Update the UI thread-safely
				UI.getCurrent().access(new Runnable() {
					@Override
					public void run() {
						submitButton.setEnabled(true);
						status.setValue("");
						UI.getCurrent().setPollInterval(-1);
					}
				});
			}
		};
		t.start();
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}
	
	private class ClickLabel extends VerticalLayout {
		private static final long serialVersionUID = -5670313624234490349L;
		public ClickLabel(String value) {
	        Label label = new Label (value, ContentMode.HTML);
	        addComponent(label);    
	    }
	}
}
