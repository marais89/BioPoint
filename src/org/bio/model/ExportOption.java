package org.bio.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name="exportOption")
public class ExportOption  implements Serializable{

private Map<String,String> listfield;
private String selectedfield;
private List<String> format;
private boolean display;
private boolean display2;
private int size;
private Integer id ;
private Exporter exporter;

private String selectedFormat;
public ExportOption() {
	listfield = new HashMap<String, String>();
	listfield.put("Matricule", "Matricule");
	listfield.put("Nom", "Nom");
	listfield.put("Prenom", "Prenom");
	listfield.put("Jour", "Jour");
	listfield.put("Retard Total", "Retard Total");
	listfield.put("Retard 1", "Retard1");
	listfield.put("Retard 2", "Retard2");
	listfield.put("Présence", "Présence");
	listfield.put("Pause", "Pause");
	listfield.put("Entrée", "Entrée");
	listfield.put("Entrée Planifié", "Entrée Planifié");
	listfield.put("Sortie", "Sortie");
	listfield.put("Sortie Planifié", "Sortie Planifié");
	listfield.put("Congé", "Congé");
	listfield.put("Autorisation", "Autorisation");
	display = true;
	display2 = true;

selectedfield="none";
	format = new ArrayList<String>();

}
public void findFormat()
{

				
}
@Transient
public List<String> getFormat() {
	if((selectedfield.equals("Retard Total"))||(selectedfield.equals("Retard1"))||(selectedfield.equals("Retard2"))||(selectedfield.equals("Présence"))||(selectedfield.equals("Pause"))
			|(selectedfield.equals("Entrée"))||(selectedfield.equals("Entrée Planifié"))|(selectedfield.equals("Sortie"))||(selectedfield.equals("Sortie Planifié")))
			{
		format.clear();
		format.add("HH:mm:ss");
		format.add("HH-mm-ss");
		format.add("HH/mm/ss");
		format.add("HH,mm,ss");
		format.add("En Minutes");
		format.add("En Heures");
		format.add("En Secondes");
			}
	else if(selectedfield.equals("Jour"))
	{
			format.clear();
			format.add("aaaa-mm-jj");
			format.add("aaaa/mm/jj");
			format.add("aa-mm-jj");
			format.add("aa/mm/jj");
			format.add("jj-mm-aaaa");
			format.add("jj/mm/aaaa");

	}
	else {
		format.clear();
		format.add("En Majiscule");
		format.add("En Miniscule");
		format.add("En Capital lettre");
	
	}

	return format;
}
public void setFormat(List<String> format) {
	this.format = format;
}
public void switchDisplay()
{
	display=!display;
	

}
public void switchDisplay2()
{
	display2=!display2;
	

}

@Transient
public Map<String, String> getListfield() {
	return listfield;
}
public void setListfield(Map<String, String> listfield) {
	this.listfield = listfield;
}
@Column(name="fieldname",length=45)
public String getSelectedfield() {
	return selectedfield;
}
public void setSelectedfield(String selectedfield) {
	this.selectedfield = selectedfield;
}
@Transient
public boolean isDisplay() {
	return display;
}
public void setDisplay(boolean display) {
	this.display = display;
}
@Column(name="formatstyle",length=45)
public String getSelectedFormat() {
	return selectedFormat;
}
public void setSelectedFormat(String selectedFormat) {
	this.selectedFormat = selectedFormat;
}
@Transient
public int getSize() {
	if(format==null)
		return 0;
	else if(format.size()==0)
		return 0;
	else 
	
	return format.size();
}
public void setSize(int size) {
	this.size = size;
}
@Transient
public boolean isDisplay2() {
	return display2;
}
public void setDisplay2(boolean display2) {
	this.display2 = display2;
}
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name = "idoption", unique = true, nullable = false)
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "idexporter")
public Exporter getExporter() {
	return exporter;
}
public void setExporter(Exporter exporter) {
	this.exporter = exporter;
}



}
