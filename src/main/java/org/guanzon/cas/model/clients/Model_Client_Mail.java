package org.guanzon.cas.model.clients;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import org.guanzon.appdriver.base.GRider;
import org.guanzon.appdriver.base.MiscUtil;
import org.guanzon.appdriver.base.SQLUtil;
import org.guanzon.appdriver.constant.EditMode;
import org.guanzon.appdriver.constant.Logical;
import org.guanzon.appdriver.constant.RecordStatus;
import org.guanzon.appdriver.iface.GEntity;
import org.json.simple.JSONObject;


/**
 *
 * @author Michael Cuison
 */
public class Model_Client_Mail implements GEntity{
    
    final String XML = "Model_Client_Mail.xml";
    Connection poConn;          //connection
    CachedRowSet poEntity;      //rowset
    String psMessage;           //warning, success or error message
    
    
    GRider poGRider;
    int pnEditMode;
    public JSONObject poJSON;
    
    public Model_Client_Mail(GRider poValue){
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

    /**
     * Gets the total number of column.
     * @return total number of column
     */
    @Override
    public int getColumnCount() {
        try {
            return poEntity.getMetaData().getColumnCount(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return -1;
    }


    /** 
     * this is your table name
     **/
    @Override
    public String getTable() {
        return "Client_eMail_Address";
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
     * Sets the Email ID of this record.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setEmailID(String fsValue){
        return setValue("sEmailIDx", fsValue);
    }
    
    /**
     * @return The Email ID of this record. 
     */
    public String getEmailID(){
        return (String) getValue("sEmailIDx");
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
     * Sets the email address.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setEmail(String fsValue){
        return setValue("sEMailAdd", fsValue);
    }
    
    /**
     * @return The email address.
     */
    public String getEmail(){
        return (String) getValue("sEMailAdd");
    }
    
    
     /**
     * Sets the email owner.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setOwner(String fsValue){
        return setValue("cOwnerxxx", fsValue);
    }
    
    /**
     * @return The email owner.
     */
    public String getOwner(){
        return (String) getValue("cOwnerxxx");
    }
    
     /**
     * Sets the email cPrimaryx.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setPrimary(String fsValue){
        return setValue("cPrimaryx", fsValue);
    }
    
    /**
     * @return The email cPrimaryx.
     */
    public String getPrimary(){
        return (String) getValue("cPrimaryx");
    }
    
    
     /**
     * Sets the email cRecdStat.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setRecordStat(String fsValue){
        return setValue("cRecdStat", fsValue);
    }
    
    /**
     * @return The email cRecdStat.
     */
    public String getRecordStat(){
        return (String) getValue("cRecdStat");
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

    private String getSQL(){
        return "SELECT" +
                    ", sClientID" +
                    ", nEntryNox" +
                    ", sEmailAdd" +
                    ", nPrior   ity" +
                " FROM " + getTable();
    }
    private void initialize(){
        
        try {
            poEntity = MiscUtil.xml2ResultSet(System.getProperty("sys.default.path.metadata") + XML, getTable());
            
            poEntity.last();
            poEntity.moveToInsertRow();

            MiscUtil.initRowSet(poEntity);  
            
            
            poEntity.updateString("sEmailIDx", MiscUtil.getNextCode(getTable(), "sEmailIDx", true, poConn, poGRider.getBranchCode()));
            poEntity.updateString("cPrimaryx", Logical.NO);
            poEntity.updateString("cOwnerxxx", Logical.NO);
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
        setEmailID(MiscUtil.getNextCode(getTable(), "sEmailIDx", true, poGRider.getConnection(), poGRider.getBranchCode()));
        
        poJSON = new JSONObject();
        poJSON.put("result", "success");
        return poJSON;
    }

    @Override
    public JSONObject openRecord(String fsValue) { pnEditMode = EditMode.UPDATE;
        poJSON = new JSONObject();

        String lsSQL = MiscUtil.makeSelect(this);
        lsSQL = MiscUtil.addCondition(lsSQL, "sEmailIDx = " + SQLUtil.toSQL(fsValue));

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
                setEmailID(MiscUtil.getNextCode(getTable(), "sEmailIDx", true, poGRider.getConnection(), poGRider.getBranchCode()));
                
                setModifiedDate(poGRider.getServerDate());
                lsSQL = MiscUtil.makeSQL(this);
                
                if (!lsSQL.isEmpty()){
                    if (poGRider.executeQuery(lsSQL, getTable(), poGRider.getBranchCode(), "") > 0){
                        poJSON.put("result", "success");
                        poJSON.put("sEmailIDx", getEmailID());
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
                Model_Client_Mail loOldEntity = new Model_Client_Mail(poGRider);
                
                setModifiedDate(poGRider.getServerDate());
                //replace with the primary key column info
                JSONObject loJSON = loOldEntity.openRecord(this.getEmailID());
                
                if ("success".equals((String) loJSON.get("result"))){
                    //replace the condition based on the primary key column of the record
                    lsSQL = MiscUtil.makeSQL(this, loOldEntity, "sEmailIDx = " + SQLUtil.toSQL(this.getEmailID()));
                    
                    if (!lsSQL.isEmpty()){
                        if (poGRider.executeQuery(lsSQL, getTable(), poGRider.getBranchCode(), "") > 0){
                            poJSON.put("result", "success");
                            poJSON.put("sEmailIDx", getEmailID());
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
//
//        poJSON =  new JSONObject();
//        try {
//            lsSQL = MiscUtil.rowset2SQL(poEntity, 
//                    getTable(),
//                    "",
//                    "");
//        
//            if (pnEditMode == EditMode.ADDNEW){           
//                lsSQL = MiscUtil.getNextCode(getTable(), "sEmailIDx", false, poGRider.getConnection(), "");
//                poEntity.updateObject("sEmailIDx", lsSQL);
//                poEntity.updateRow();
//
//                lsSQL = MiscUtil.rowset2SQL(poEntity, getTable(), "");
//            } else {            
//                lsSQL = MiscUtil.rowset2SQL(poEntity, 
//                                            getTable(), 
//                                            "", 
//                                            "sEmailIDx = " + SQLUtil.toSQL(poEntity.getString("sEmailIDx")));
//            }
//            
//            if (!lsSQL.equals("")){
//                if(poGRider.executeQuery(lsSQL, getTable(), "", "") == 0){
//                    if(!poGRider.getErrMsg().isEmpty()){ 
//                        poJSON.put("result", "error");
//                        poJSON.put("message", poGRider.getErrMsg());
//                        return poJSON;
//                    }
//                }else {
//                    poJSON.put("result", "error");
//                    poJSON.put("message", "No record updated");
//                    return poJSON;
//                }
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(Model_Client_Mobile.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return poJSON;
    }
    
    /**
     * Sets column value.
     * 
     * @param fnColumn - column index number
     * @param foValue - value
     * @return result as success/failed
     */
    @Override
    public JSONObject setValue(int fnColumn, Object foValue) {
        try {              
            poJSON = MiscUtil.validateColumnValue(System.getProperty("sys.default.path.metadata") + XML, MiscUtil.getColumnLabel(poEntity, fnColumn), foValue);
            if ("error".equals((String) poJSON.get("result"))) return poJSON;
            
            poEntity.updateObject(fnColumn, foValue);
            poEntity.updateRow();
            
            poJSON = new JSONObject();
            poJSON.put("result", "success");
            poJSON.put("value", getValue(fnColumn));
        } catch (SQLException e) {
            e.printStackTrace();
            poJSON.put("result", "error");
            poJSON.put("message", e.getMessage());
        }
        
        return poJSON;
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
