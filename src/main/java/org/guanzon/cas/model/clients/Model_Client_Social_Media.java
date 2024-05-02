package org.guanzon.cas.model.clients;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;
import org.guanzon.appdriver.base.GRider;
import org.guanzon.appdriver.base.MiscUtil;
import org.guanzon.appdriver.base.SQLUtil;
import org.guanzon.appdriver.constant.EditMode;
import org.guanzon.appdriver.constant.Logical;
import org.guanzon.appdriver.iface.GEntity;
import org.json.simple.JSONObject;

/**
 *
 * @author Michael Cuison
 */
public class Model_Client_Social_Media implements GEntity{
    
    final String XML = "Model_Client_Social_Media.xml";
    Connection poConn;          //connection
    CachedRowSet poEntity;      //rowset
    String psMessage;           //warning, success or error message
    GRider poGRider;
    int pnEditMode;
    public JSONObject poJSON;
    public Model_Client_Social_Media(GRider poValue){
        if (poValue.getConnection() == null){
            System.err.println("Database connection is not set.");
            System.exit(1);
        }
        pnEditMode = EditMode.UNKNOWN;
        poGRider = poValue;
        poConn = poGRider.getConnection();
        initialize();
    }

    @Override
    public String getColumn(int fnCol) {
        try {
            return poEntity.getMetaData().getColumnLabel(fnCol); 
        } catch (SQLException e) {
        }
        return "";
    }

    @Override
    public int getColumn(String fsCol) {
        try {
            return MiscUtil.getColumnIndex(poEntity, fsCol);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int getColumnCount() {
        try {
            return poEntity.getMetaData().getColumnCount(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return -1;
    }

    @Override
    public String getTable() {
        return "Client_Social_Media";
    }

    @Override
    public Object getValue(int fnColumn) {
        try {
            return poEntity.getObject(fnColumn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object getValue(String fsColumn) {
        try {
            return poEntity.getObject(fsColumn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public void list() {
        Method[] methods = this.getClass().getMethods();
        
        System.out.println("List of public methods for class " + this.getClass().getName() + ":");
        for (Method method : methods) {
            System.out.println(method.getName());
        }
    }
    /**
     * Sets the ID of this record.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setSocialID(String fsValue){
        return setValue("sSocialID", fsValue);
    }
    
    /** 
     * @return The ID of this record. 
     */
    public String getSocialID(){
        return (String) getValue("sSocialID");
    }
    
    /**
     * Sets the Owner ID of this record.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setClientID(String fsValue){
        return setValue("sClientID", fsValue);
    }
    
    /**
     * @return The Owner ID of this record. 
     */
    public String getClientID(){
        return (String) getValue("sClientID");
    }
    
    /**
     * Sets the social account.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setSocialAccount(String fsValue){
        return setValue("sAccountx", fsValue);
    }
    
    /**
     * @return The social account.
     */
    public String getSocialAccount(){
        return (String) getValue("sAccountx");
    }
    
    /**
     * Sets the remarks.<br>
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setRemarks(String fsValue){
        return setValue("sRemarksx", fsValue);
    }
    
    /**
     * @return The remarks. 
     */
    public String getRemarks(){
        return (String) getValue("sRemarksx");
    }
    
    /**
     * Sets the social account type.<br>
     * 0 for Facebook<br>
     * 1 for Instagram<br>
     * 2 for Twitter
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setSocialTyp(String fsValue){
        return setValue("cSocialTp", fsValue);
    }
    
    /**
     * @return The social account type. 
     */
    public String getSocialTyp(){
        return (String) getValue("cSocialTp");
    }
    
    /**
     * Sets record as active.
     * 
     * @param fbValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setActive(boolean fbValue){
        return setValue("cRecdStat", fbValue ? "1" : "0");
    }
    
    /**
     * @return If record is active. 
     */
    public boolean isActive(){
        return ((String) getValue("cRecdStat")).equals("1");
    }
    
    /**
     * Sets the user encoded/updated the record.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setModifiedBy(String fsValue){
        return setValue("sModified", fsValue);
    }
    
    /**
     * @return The user encoded/updated the record 
     */
    public String getModifiedBy(){
        return (String) getValue("sModified");
    }
    
    /**
     * Sets the date and time the record was modified.
     * 
     * @param fdValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setModifiedDate(Date fdValue){
        return setValue("dModified", fdValue);
    }
    
    /**
     * @return The date and time the record was modified.
     */
    public Date getModifiedDate(){
        return (Date) getValue("dModified");
    }
    
    public String getMessage(){
        return psMessage;
    }
    
    private String getSQL(){
        return "SELECT" +
                    "  sSocialID" +
                    ", sClientID" +
                    ", sAccountx" +
                    ", sRemarksx" +
                    ", cSocialTp" +
                    ", cRecdStat" +
                    ", sModified" +
                    ", dModified" +
                " FROM " + getTable();
    }
    
    private void initialize(){
        
        try {
            poEntity = MiscUtil.xml2ResultSet(System.getProperty("sys.default.path.metadata") + XML, getTable());
            
            
            poEntity.last();
            poEntity.moveToInsertRow();

            MiscUtil.initRowSet(poEntity);
         //replace with the primary key column info
            poEntity.updateString("sSocialID", MiscUtil.getNextCode(getTable(), "sSocialID", true, poConn, poGRider.getBranchCode()));
            poEntity.updateString("cSocialTp", Logical.NO);
            poEntity.updateString("cRecdStat", Logical.YES);
            
            poEntity.insertRow();
            poEntity.moveToCurrentRow();

            poEntity.absolute(1);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }    
    

    @Override
    public JSONObject newRecord() {
        pnEditMode = EditMode.ADDNEW;
        
        //replace with the primary key column info
        setSocialID(MiscUtil.getNextCode(getTable(), "sSocialID", true, poGRider.getConnection(), poGRider.getBranchCode()));
        
        poJSON = new JSONObject();
        poJSON.put("result", "success");
        return poJSON;
    }

    @Override
    public JSONObject openRecord(String fsValue) {
        poJSON = new JSONObject();

        String lsSQL = MiscUtil.makeSelect(this);
        lsSQL = MiscUtil.addCondition(lsSQL, "sSocialID = " + SQLUtil.toSQL(fsValue));

        ResultSet loRS = poGRider.executeQuery(lsSQL);

        try {
            if (loRS.next()){
                for (int lnCtr = 1; lnCtr <= loRS.getMetaData().getColumnCount(); lnCtr++){
                    setValue(lnCtr, loRS.getObject(lnCtr));
                }

                pnEditMode = EditMode.UPDATE;

                poJSON.put("result", "success");
                poJSON.put("message", "Record loaded successfully.");
            } else {
                poJSON.put("result", "error");
                poJSON.put("message", "No record to load.");
            }
            MiscUtil.close(loRS);
        } catch (SQLException e) {
            poJSON.put("result", "error");
            poJSON.put("message", e.getMessage());
        }

        return poJSON;
    }

    @Override
    public JSONObject saveRecord() {
        poJSON = new JSONObject();
        
        if (pnEditMode == EditMode.ADDNEW || pnEditMode == EditMode.UPDATE){
            String lsSQL;
            if (pnEditMode == EditMode.ADDNEW){
                //replace with the primary key column info
                setSocialID(MiscUtil.getNextCode(getTable(), "sSocialID", true, poGRider.getConnection(), poGRider.getBranchCode()));
                setModifiedDate(poGRider.getServerDate());
                lsSQL = MiscUtil.makeSQL(this);
                
                if (!lsSQL.isEmpty()){
                    if (poGRider.executeQuery(lsSQL, getTable(), poGRider.getBranchCode(), "") > 0){
                        poJSON.put("result", "success");
                        poJSON.put("message", "Record saved successfully.");
                    } else {
                        poJSON.put("result", "error");
                        poJSON.put("message", poGRider.getErrMsg());
                    }
                } else {
                    poJSON.put("result", "error");
                    poJSON.put("message", "No record to save.");
                }
            } else {
                Model_Client_Social_Media loOldEntity = new Model_Client_Social_Media(poGRider);
                
                //replace with the primary key column info
                JSONObject loJSON = loOldEntity.openRecord(this.getSocialID());
                setModifiedDate(poGRider.getServerDate());
                
                if ("success".equals((String) loJSON.get("result"))){
                    //replace the condition based on the primary key column of the record
                    lsSQL = MiscUtil.makeSQL(this, loOldEntity, "sSocialID = " + SQLUtil.toSQL(this.getSocialID()));
                    
                    if (!lsSQL.isEmpty()){
                        if (poGRider.executeQuery(lsSQL, getTable(), poGRider.getBranchCode(), "") > 0){
                            poJSON.put("result", "success");
                            poJSON.put("message", "Record saved successfully.");
                        } else {
                            poJSON.put("result", "error");
                            poJSON.put("message", poGRider.getErrMsg());
                        }
                    } else {
                        poJSON.put("result", "error");
                        poJSON.put("continue", true);
                        poJSON.put("message", "No updates has been made.");
                    }
                } else {
                    poJSON.put("result", "error");
                    poJSON.put("message", "Record discrepancy. Unable to save record.");
                }
            }
        } else {
            poJSON.put("result", "error");
            poJSON.put("message", "Invalid update mode. Unable to save record.");
            return poJSON;
        }
        return poJSON;

    }

    @Override
    public JSONObject setValue(int lnColumn, Object foValue) {
        
            poJSON = new JSONObject();
        try {
            poEntity.updateObject(lnColumn, foValue);
            poEntity.updateRow();
            poJSON.put("result", "success");
            poJSON.put("value", getValue(lnColumn));
            return poJSON;
        } catch (SQLException e) {
            e.printStackTrace();
            psMessage = e.getMessage();
            poJSON.put("result", "error");
            poJSON.put("message", e.getMessage());
            return poJSON;
        }
    }

    @Override
    public JSONObject setValue(String string, Object foValue) {
        try {
            return setValue(MiscUtil.getColumnIndex(poEntity, string), foValue);
        } catch (SQLException ex) {
            
            poJSON = new JSONObject();
            poJSON.put("result", "error");
            poJSON.put("message", ex.getMessage());
            return poJSON;
            
        }
        
    }


    @Override
    public int getEditMode() {
        return pnEditMode;
    }
    
}
