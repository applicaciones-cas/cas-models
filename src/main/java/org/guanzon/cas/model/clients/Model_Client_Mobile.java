package org.guanzon.cas.model.clients;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import org.guanzon.appdriver.base.CommonUtils;
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
public class Model_Client_Mobile implements GEntity{
    
    final String XML = "Model_Client_Mobile.xml";
    Connection poConn;          //connection
    CachedRowSet poEntity;      //rowset
    String psMessage;           //warning, success or error message
    GRider poGRider;
    int pnEditMode;
    
    public JSONObject poJSON;
    public Model_Client_Mobile(GRider poValue){
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
        return "Client_Mobile";
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
    public JSONObject setMobileID(String fsValue){
        return setValue("sMobileID", fsValue);
    }
    
    /** 
     * @return The ID of this record. 
     */
    public String getMobileID(){
        return (String) getValue("sMobileID");
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
     * Sets the contact number.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setContactNo(String fsValue){
        return setValue("sMobileNo", fsValue);
    }
    
    /**
     * @return The contact number.
     */
    public String getContactNo(){
        return (String) getValue("sMobileNo");
    }
    
    /**
     * Sets the type of the contact number.<br>
     * 0 for Mobile No.<br>
     * 1 for Telephone No.<br>
     * 2 for Fax Machine No.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setContactType(String fsValue){
        return setValue("cMobileTp", fsValue);
    }
    
    /**
     * @return The type of  the contact number. 
     */
    public String getContactType(){
        return (String) getValue("cMobileTp");
    }
    
    /**
     * Sets the ownership of the contact number.<br>
     * 0 for Personal<br>
     * 1 for Company Issued<br>
     * 2 for Others
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setOwnership(String fsValue){
        return setValue("cOwnerxxx", fsValue);
    }
    
    /**
     * @return The ownership of the contact number. 
     */
    public String getOwnership(){
        return (String) getValue("cOwnerxxx");
    }
    
    /**
     * Sets the contact number as Primary
     * 
     * @param fbValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setPrimary(boolean fbValue){
        return setValue("cPrimaryx", fbValue ? "1" : "0");
    }
    
    /**
     * @return If the contact number set as Primary
     */
    public boolean isPrimary(){
        return ((String) getValue("cPrimaryx")).equals("1");
    }
    
    /**
     * Sets the priority number of the contact number.
     * 
     * @param fnValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setPriority(int fnValue){
        return setValue("nPriority", fnValue);
    }
    
    /**
     * 
     * @return The priority number of the contact number. 
     */
    public int getPriority(){
        return (int) getValue("nPriority");
    }
    
    /**
     * Sets if the contact number will be receiving marketing message.
     * 
     * @param fbValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setMarketingRecipient(boolean fbValue){
        return setValue("cIncdMktg", fbValue ? "1" : "0");
    }
    
    /**
     * @return If the contact number is receiving marketing message. 
     */
    public boolean isMarketingRecipient(){
        return ((String) getValue("cIncdMktg")).equals("1");
    }
    
    /**
     * Sets the network if the contact number. Use this method for records as Mobile numbers only.<br>
     * 0 for Globe Telecom<br>
     * 1 for Smart Communications<br>
     * 2 for Sun Cellular<br>
     * 3 for DITO Telecom
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setMobileNetwork(String fsValue){
        return setValue("cSubscrbr", fsValue);
    }
    
    /**
     * @return The network of the contact number. Use this method for records as Mobile numbers only. 
     */
    public String getMobileNetwork(){
        return (String) getValue("cSubscrbr");
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
                    "  sMobileID" +
                    ", sClientID" +
                    ", sMobileNo" +
                    ", cMobileTp" +
                    ", cOwnerxxx" +
                    ", cPrimaryx" +
                    ", nPriority" +
                    ", cIncdMktg" +
                    ", nUnreachx" +
                    ", dLastVeri" +
                    ", dInactive" +
                    ", nNoRetryx" +
                    ", cInvalidx" +
                    ", cConfirmd" +
                    ", dConfirmd" +
                    ", cSubscrbr" +
                    ", dHoldMktg" +
                    ", dMktgMsg1" +
                    ", dMktgMsg2" +
                    ", dMktgMsg3" +
                    ", cNewMobil" +
                    ", cRecdStat" +
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
//            setClientID(MiscUtil.getNextCode(getTable(), "sMobileID", true, poConn, poGRider.getBranchCode()));
//            System.out.println("sMobileID = " + getMobileID());
            poEntity.updateString("sMobileID", MiscUtil.getNextCode(getTable(), "sMobileID", true, poConn, poGRider.getBranchCode()));
            poEntity.updateString("nUnreachx", Logical.NO);
            poEntity.updateString("nNoRetryx", Logical.NO);
            poEntity.updateString("cMobileTp", Logical.NO);
            poEntity.updateString("cOwnerxxx", Logical.NO);
            poEntity.updateString("cPrimaryx", Logical.NO);
            poEntity.updateString("cRecdStat", Logical.YES);
            poEntity.updateString("dLastVeri", null);
            poEntity.updateString("dInactive", null);
            poEntity.updateString("dConfirmd", null);
            poEntity.updateString("dHoldMktg", null);
            poEntity.updateString("dMktgMsg1", null);
            poEntity.updateString("dMktgMsg2", null);
            poEntity.updateString("dMktgMsg3", null);
            
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
        
        
        poJSON = new JSONObject();
        poJSON.put("result", "success");
        return poJSON;
    }

    @Override
    public JSONObject openRecord(String fsValue) {
        poJSON = new JSONObject();

        String lsSQL = MiscUtil.makeSelect(this);
        lsSQL = MiscUtil.addCondition(lsSQL, "sMobileID = " + SQLUtil.toSQL(fsValue));

        System.out.println(lsSQL);
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
                setMobileID(MiscUtil.getNextCode(getTable(), "sMobileID", true, poGRider.getConnection(), poGRider.getBranchCode()));
                setModifiedDate(poGRider.getServerDate());
                
                setMobileNetwork(CommonUtils.classifyNetwork(getContactNo()));
                lsSQL = MiscUtil.makeSQL(this);
                
                if (!lsSQL.isEmpty()){
                    if (poGRider.executeQuery(lsSQL, getTable(), poGRider.getBranchCode(), "") > 0){
                        poJSON.put("result", "success");
                        poJSON.put("sMobileID", getMobileID());
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
                Model_Client_Mobile loOldEntity = new Model_Client_Mobile(poGRider);
                
                //replace with the primary key column info
                JSONObject loJSON = loOldEntity.openRecord(this.getMobileID());
                setModifiedDate(poGRider.getServerDate());
                setMobileNetwork(CommonUtils.classifyNetwork(getContactNo()));
                if ("success".equals((String) loJSON.get("result"))){
                    //replace the condition based on the primary key column of the record
                    lsSQL = MiscUtil.makeSQL(this, loOldEntity, "sMobileID = " + SQLUtil.toSQL(this.getMobileID()));
                    
                    if (!lsSQL.isEmpty()){
                        if (poGRider.executeQuery(lsSQL, getTable(), poGRider.getBranchCode(), "") > 0){
                            poJSON.put("result", "success");
                            poJSON.put("sMobileID", getMobileID());
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
//        
//        String lsSQL;
//        
//        poJSON =  new JSONObject();
//        try {
//            lsSQL = MiscUtil.rowset2SQL(poEntity, 
//                    getTable(),
//                    "",
//                    "");
//        
//            if (pnEditMode == EditMode.ADDNEW){           
//                lsSQL = MiscUtil.getNextCode(getTable(), "sMobileID", false, poGRider.getConnection(), "");
//                poEntity.updateObject("sMobileID", lsSQL);
//                poEntity.updateRow();
//
//                lsSQL = MiscUtil.rowset2SQL(poEntity, getTable(), "");
//            } else {            
//                lsSQL = MiscUtil.rowset2SQL(poEntity, 
//                                            getTable(), 
//                                            "", 
//                                            "sMobileID = " + SQLUtil.toSQL(poEntity.getString("sMobileID")));
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