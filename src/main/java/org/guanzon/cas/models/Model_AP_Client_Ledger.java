package org.guanzon.cas.models;

import org.guanzon.cas.model.clients.*;
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
public class Model_AP_Client_Ledger implements GEntity{
    
    final String XML = "Model_AP_Client_Ledger.xml";
    Connection poConn;          //connection
    CachedRowSet poEntity;      //rowset
    String psMessage;           //warning, success or error message
    GRider poGRider;
    int pnEditMode;
    public JSONObject poJSON;
    public Model_AP_Client_Ledger(GRider poValue){
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
        return "AP_Client_Ledger";
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
    public JSONObject setClientID(String fsValue){
        return setValue("sClientID", fsValue);
    }
    
    /** 
     * @return The ID of this record. 
     */
    public String getClientID(){
        return (String) getValue("sClientID");
    }
    
    
    /**
     * Sets the number of ledger
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setLedgerNo(String fsValue){
        return setValue("nLedgerNo", fsValue);
    }
    
    /**
     * @return The number of ledger
     */
    public String getLedgerNo(){
        return (String) getValue("nLedgerNo");
    }
    
    /**
     * Sets the transaction date.<br>
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setTransactionDate(String fsValue){
        return setValue("dTransact", fsValue);
    }
    
    /**
     * @return The transaction date. 
     */
    public String getTransactionDate(){
        return (String) getValue("dTransact");
    }
    
    /**
     * Sets the Source Code .
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setSourceCode(String fsValue){
        return setValue("sSourceCd", fsValue);
    }
    
    /**
     * @return The Source Code. 
     */
    public String getSourceCode(){
        return (String) getValue("sSourceCd");
    }
    
    /**
     * Sets the Source Number .
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setSourceNo(String fsValue){
        return setValue("sSourceNo", fsValue);
    }
    
    /**
     * @return The Source Number. 
     */
    public String getSourceNo(){
        return (String) getValue("sSourceNo");
    }
    
    /**
     * Sets the Amount In .
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setAmountIn(String fsValue){
        return setValue("nAmountIn", fsValue);
    }
    
    /**
     * @return The Amount In. 
     */
    public Double getAmountIn(){
        return (Double) getValue("nAmountIn");
    }
    
    /**
     * Sets the Amount Out .
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setAmountOut(String fsValue){
        return setValue("nAmountOt", fsValue);
    }
    
    /**
     * @return The Amount Out. 
     */
    public Double getAmountOut(){
        return (Double) getValue("nAmountOt");
    }
    
    /**
     * Sets the date and time the record was posted.
     * 
     * @param fdValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setPostedDate(Date fdValue){
        return setValue("dPostedxx", fdValue);
    }
    
    /**
     * @return The date and time the record was posted.
     */
    public Date getPostedDate(){
        return (Date) getValue("dPostedxx");
    }
    
    /**
     * Sets the Available Balance .
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setAvailableBalance(String fsValue){
        return setValue("nABalance", fsValue);
    }
    
    /**
     * @return The Available Balance. 
     */
    public Double getAvailableBalance(){
        return (Double) getValue("nABalance");
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
                    "  sClientID" +
                    ", nLedgerNo" +
                    ", dTransact" +
                    ", sSourceCd" +
                    ", sSourceNo" +
                    ", nAmountIn" +
                    ", nAmountOt" +
                    ", dPostedxx" +
                    ", nABalance" +
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
//            poEntity.updateString("sSocialID", MiscUtil.getNextCode(getTable(), "sSocialID", true, poConn, poGRider.getBranchCode()));
            poEntity.updateString("sSourceCd", Logical.NO);
            poEntity.updateString("sSourceNo", Logical.YES);
            
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
        setClientID(MiscUtil.getNextCode(getTable(), "sClientID", true, poGRider.getConnection(), poGRider.getBranchCode()));
        
        poJSON = new JSONObject();
        poJSON.put("result", "success");
        return poJSON;
    }

    @Override
    public JSONObject openRecord(String fsValue) {
        poJSON = new JSONObject();

        String lsSQL = MiscUtil.makeSelect(this);
        lsSQL = MiscUtil.addCondition(lsSQL, "sClientID = " + SQLUtil.toSQL(fsValue));

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
                setClientID(MiscUtil.getNextCode(getTable(), "sClientID", true, poGRider.getConnection(), poGRider.getBranchCode()));
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
                Model_AP_Client_Ledger loOldEntity = new Model_AP_Client_Ledger(poGRider);
                
                //replace with the primary key column info
                JSONObject loJSON = loOldEntity.openRecord(this.getClientID());
                setModifiedDate(poGRider.getServerDate());
                
                if ("success".equals((String) loJSON.get("result"))){
                    //replace the condition based on the primary key column of the record
                    lsSQL = MiscUtil.makeSQL(this, loOldEntity, "sSocialID = " + SQLUtil.toSQL(this.getClientID()));
                    
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
