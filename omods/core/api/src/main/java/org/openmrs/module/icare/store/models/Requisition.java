package org.openmrs.module.icare.store.models;

// Generated Oct 7, 2020 12:48:40 PM by Hibernate Tools 5.2.10.Final

import org.openmrs.BaseOpenmrsData;
import org.openmrs.Location;
import org.openmrs.module.icare.core.JSONConverter;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * StRequisition generated by hbm2java
 */
@Entity
@Table(name = "st_requisition")
public class Requisition extends BaseOpenmrsData implements java.io.Serializable, JSONConverter {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "requisition_id", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "requesting_location_id")
	private Location requestingLocation;
	
	@ManyToOne
	@JoinColumn(name = "requested_location_id")
	private Location requestedLocation;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "requisition")
	private List<RequisitionStatus> requisitionStatuses = new ArrayList<RequisitionStatus>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id.requisition", cascade = { CascadeType.PERSIST })
	private List<RequisitionItem> requisitionItems = new ArrayList<RequisitionItem>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "requisition")
	private List<Issue> issues = new ArrayList<Issue>(0);
	
	public static Requisition fromMap(Map<String, Object> requisitionMap) {
		
		Requisition requisition = new Requisition();
		
		Location requestedLocation = new Location();
		
		requestedLocation.setUuid(((Map) requisitionMap.get("requestedLocation")).get("uuid").toString());
		requisition.setRequestedLocation(requestedLocation);
		
		Location requestingLocation = new Location();
		requestingLocation.setUuid(((Map) requisitionMap.get("requestingLocation")).get("uuid").toString());
		requisition.setRequestingLocation(requestingLocation);
		
		return requisition;
		
	}
	
	public Location getRequestingLocation() {
		return this.requestingLocation;
	}
	
	public void setRequestingLocation(Location requestingLocation) {
		this.requestingLocation = requestingLocation;
	}
	
	public Location getRequestedLocation() {
		return this.requestedLocation;
	}
	
	public void setRequestedLocation(Location requestedLocation) {
		this.requestedLocation = requestedLocation;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public List<RequisitionStatus> getRequisitionStatuses() {
		return this.requisitionStatuses;
	}
	
	public void setRequisitionStatuses(List<RequisitionStatus> requisitionStatuses) {
		this.requisitionStatuses = requisitionStatuses;
	}
	
	public List<RequisitionItem> getRequisitionItems() {
		return requisitionItems;
	}
	
	public void setRequisitionItems(List<RequisitionItem> requisitionItems) {
		this.requisitionItems = requisitionItems;
	}
	
	public List<Issue> getIssues() {
		return issues;
	}
	
	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}
	
	public enum OrderByDirection {
		ASC, DESC;
	}
	
	public Map<String, Object> toMap() {
		
		Map<String, Object> requisitionObject = new HashMap<String, Object>();
		
		requisitionObject.put("uuid", this.getUuid());
		
		Map<String, Object> requestedLocationObject = new HashMap<String, Object>();
		if (this.getRequestedLocation() != null) {
			requestedLocationObject.put("uuid", this.getRequestedLocation().getUuid());
			requestedLocationObject.put("display", this.getRequestedLocation().getDisplayString());
		}
		requisitionObject.put("requestedLocation", requestedLocationObject);
		
		Map<String, Object> requestingLocationObject = new HashMap<String, Object>();
		if (this.getRequestingLocation() != null) {
			requestingLocationObject.put("uuid", this.getRequestingLocation().getUuid());
			requestingLocationObject.put("display", this.getRequestingLocation().getDisplayString());
		}
		requisitionObject.put("requestingLocation", requestingLocationObject);
		
		List<Map<String, Object>> requisitionStatuses = new ArrayList<Map<String, Object>>();
		for (RequisitionStatus requisitionStatus : this.getRequisitionStatuses()) {
			requisitionStatuses.add(requisitionStatus.toMap());
		}
		requisitionObject.put("requisitionStatuses", requisitionStatuses);
		
		List<Map<String, Object>> requisitionIssues = new ArrayList<Map<String, Object>>();
		for (Issue issue : this.getIssues()) {
			requisitionIssues.add(issue.toMap());
		}
		requisitionObject.put("issues", requisitionIssues);
		
		List<Map<String, Object>> requisitionItems = new ArrayList<Map<String, Object>>();
		for (RequisitionItem requisitionItem : this.getRequisitionItems()) {
			requisitionItems.add(requisitionItem.toMap());
		}
		requisitionObject.put("requisitionItems", requisitionItems);
		
		Map<String, Object> creatorObject = new HashMap<String, Object>();
		if (this.getCreator() != null) {
			creatorObject.put("uuid", this.getCreator().getUuid());
			creatorObject.put("display", this.getCreator().getDisplayString());
		}
		requisitionObject.put("creator", creatorObject);
		Date date = this.getDateCreated();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		requisitionObject.put("created", dateFormat.format(date));
		
		return requisitionObject;
	}
}
