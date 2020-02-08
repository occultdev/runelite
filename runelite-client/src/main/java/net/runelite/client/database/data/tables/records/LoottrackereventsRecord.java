/*
 * This file is generated by jOOQ.
 */
package net.runelite.client.database.data.tables.records;


import java.sql.Timestamp;
import java.util.UUID;
import javax.annotation.processing.Generated;

import net.runelite.client.database.data.tables.Loottrackerevents;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
		value = {
				"http://www.jooq.org",
				"jOOQ version:3.12.3"
		},
		comments = "This class is generated by jOOQ"
)
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class LoottrackereventsRecord extends UpdatableRecordImpl<LoottrackereventsRecord> implements Record4<UUID, String, String, Timestamp> {

	private static final long serialVersionUID = -1418522415;

	/**
	 * Setter for <code>PUBLIC.LOOTTRACKEREVENTS.UNIQUEID</code>.
	 */
	public void setUniqueid(UUID value) {
		set(0, value);
	}

	/**
	 * Getter for <code>PUBLIC.LOOTTRACKEREVENTS.UNIQUEID</code>.
	 */
	public UUID getUniqueid() {
		return (UUID) get(0);
	}

	/**
	 * Setter for <code>PUBLIC.LOOTTRACKEREVENTS.EVENTID</code>.
	 */
	public void setEventid(String value) {
		set(1, value);
	}

	/**
	 * Getter for <code>PUBLIC.LOOTTRACKEREVENTS.EVENTID</code>.
	 */
	public String getEventid() {
		return (String) get(1);
	}

	/**
	 * Setter for <code>PUBLIC.LOOTTRACKEREVENTS.TYPE</code>.
	 */
	public void setType(String value) {
		set(2, value);
	}

	/**
	 * Getter for <code>PUBLIC.LOOTTRACKEREVENTS.TYPE</code>.
	 */
	public String getType() {
		return (String) get(2);
	}

	/**
	 * Setter for <code>PUBLIC.LOOTTRACKEREVENTS.TIME</code>.
	 */
	public void setTime(Timestamp value) {
		set(3, value);
	}

	/**
	 * Getter for <code>PUBLIC.LOOTTRACKEREVENTS.TIME</code>.
	 */
	public Timestamp getTime() {
		return (Timestamp) get(3);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	@Override
	public Record1<UUID> key() {
		return (Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record4 type implementation
	// -------------------------------------------------------------------------

	@Override
	public Row4<UUID, String, String, Timestamp> fieldsRow() {
		return (Row4) super.fieldsRow();
	}

	@Override
	public Row4<UUID, String, String, Timestamp> valuesRow() {
		return (Row4) super.valuesRow();
	}

	@Override
	public Field<UUID> field1() {
		return Loottrackerevents.LOOTTRACKEREVENTS.UNIQUEID;
	}

	@Override
	public Field<String> field2() {
		return Loottrackerevents.LOOTTRACKEREVENTS.EVENTID;
	}

	@Override
	public Field<String> field3() {
		return Loottrackerevents.LOOTTRACKEREVENTS.TYPE;
	}

	@Override
	public Field<Timestamp> field4() {
		return Loottrackerevents.LOOTTRACKEREVENTS.TIME;
	}

	@Override
	public UUID component1() {
		return getUniqueid();
	}

	@Override
	public String component2() {
		return getEventid();
	}

	@Override
	public String component3() {
		return getType();
	}

	@Override
	public Timestamp component4() {
		return getTime();
	}

	@Override
	public UUID value1() {
		return getUniqueid();
	}

	@Override
	public String value2() {
		return getEventid();
	}

	@Override
	public String value3() {
		return getType();
	}

	@Override
	public Timestamp value4() {
		return getTime();
	}

	@Override
	public LoottrackereventsRecord value1(UUID value) {
		setUniqueid(value);
		return this;
	}

	@Override
	public LoottrackereventsRecord value2(String value) {
		setEventid(value);
		return this;
	}

	@Override
	public LoottrackereventsRecord value3(String value) {
		setType(value);
		return this;
	}

	@Override
	public LoottrackereventsRecord value4(Timestamp value) {
		setTime(value);
		return this;
	}

	@Override
	public LoottrackereventsRecord values(UUID value1, String value2, String value3, Timestamp value4) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached LoottrackereventsRecord
	 */
	public LoottrackereventsRecord() {
		super(Loottrackerevents.LOOTTRACKEREVENTS);
	}

	/**
	 * Create a detached, initialised LoottrackereventsRecord
	 */
	public LoottrackereventsRecord(UUID uniqueid, String eventid, String type, Timestamp time) {
		super(Loottrackerevents.LOOTTRACKEREVENTS);

		set(0, uniqueid);
		set(1, eventid);
		set(2, type);
		set(3, time);
	}
}
