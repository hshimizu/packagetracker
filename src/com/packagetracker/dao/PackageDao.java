package com.packagetracker.dao;

import java.util.Iterator;
import java.util.LinkedList;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class PackageDao {

	private Session session = null;
	private ResultSet results = null;
	private LinkedList <TrackItem> resultsList;

	public PackageDao(String packageId) {
		readData(packageId);
	}

	protected void readData(String packageId) {
		session = CassandraStorage.getInstance();
		String queryText = "SELECT * FROM packagetracker.events WHERE package_id = '"+ packageId + "'";
		results = session.execute(queryText);
		resultsList = new LinkedList<TrackItem>();
		
		for (Row row : results) {
			TrackItem trackItem = new TrackItem();
			trackItem.setStatusTimestamp(row.getDate("status_timestamp").toString());
			trackItem.setLocation(row.getString("location"));
			trackItem.setNotes(row.getString("notes"));

			resultsList.add(trackItem);
		}
	}

	public Iterator <TrackItem> getResultsIterator() {
		return resultsList.iterator();
	}
}
