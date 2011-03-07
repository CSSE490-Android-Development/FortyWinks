package com.fernferret.android.fortywinks;

import java.util.List;

public interface DBAdapter {

    /**
     * Determine whether or not an alarm exists in the application database based on its ID number
     * @param id The ID number of the alarm in question
     * @return True if the alarm exists, false otherwise
     */
    public boolean alarmExists(int id);
    
    /**
     * Delete an alarm and all connected follow-ups and attributes from the application database
     * 
     * Matching is based on alarm ID number.
     * 
     * @param a The alarm to be deleted from the application database
     */
    public void deleteAlarm(Alarm a);

    /**
     * Delete an alarm and all connected follow-ups and attributes from the application database
     * @param id The ID number of the alarm to be deleted from the application database
     */
    public void deleteAlarm(int id);

    /**
     * Save an alarm in the application database.
     * 
     * If the alarm does not exist in the application database already (and therefore currently has an ID number of -1), 
     * the alarm's ID number, as well as follow-ups and alarm times will be populated and returned, and a corresponding alarm
     * entry will be created in the database. If the alarm already exists in the database (as determined by checking its
     * ID number), the attributes on the alarm will be updated in the database entry. 
     * 
     * @param a The alarm to be saved in the database.
     * @return A modified alarm object with all of its database-dependent fields populated
     */
    public Alarm saveAlarm(Alarm a);

    /**
     * Retrieve an alarm object from the application database.
     * 
     * If the alarm does not exist in the application database, null will be returned.
     * 
     * @param id The ID number of the alarm to be retrieved
     * @return A populated alarm object corresponding to the relevant database entry, or null if the database entry does not exist.
     */
    public Alarm getAlarm(int id);

    /**
     * Retrieve the power nap, if it exists, from the application database
     * @return A populated alarm object representing the current power nap if it exists, or null if it doesn't.
     */
    public Alarm getPowerNap();

    /**
     * Retrieve a list of quick alarms and scheduled alarms, sorted together by their next alarm time.
     * @return The sorted list of alarm objects.
     */
    public List<Alarm> getQuikAlarmsAndScheduledAlarms();

    /**
     * Retrieve a list of quick alarms and scheduled alarms sorted by their next alarm time, preceded by the power nap, if it exists.
     * @return The sorted list of alarm objects, preceded by the power nap alarm object, if it exists.
     */
    public List<Alarm> getFullAlarmList();

    /**
     * Retrieve a list of quick alarms and scheduled alarms sorted by their next alarm time, preceded by the power nap, if it exists, 
     * limiting the length of the entire list to a specific number of items.
     * @param numItems The maximum length of the list of alarms returned.
     * @return The sorted list of alarm objects, limited to the size specified and preceded by the power nap alarm object, if it exists.
     */
    public List<Alarm> getFullAlarmList(int numItems);
    
    /**
     * Modify an existing list of alarms in place to reflect the current full alarm list, including the power nap, if it exists.
     * @param alarms The list of alarms to be modified.
     */
    public void updateFullAlarmList(List<Alarm> alarms);
    
    /**
     * Mark an alarm as active, or currently alarming, in the application database.
     * @param a The alarm to be marked as active.
     */
    public void setAlarmActive(Alarm a);
    
    /**
     * Mark an alarm as active by ID number in the application database.
     * @param id The ID number of the alarm to be marked as active.
     */
    public void setAlarmActive(int id);
    
    /**
     * Reset the application database, deleting all values and entries from it.
     */
    void resetDatabase();

}