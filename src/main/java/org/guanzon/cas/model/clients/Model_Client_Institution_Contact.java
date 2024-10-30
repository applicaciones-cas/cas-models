package org.guanzon.cas.model.clients;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
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
public class Model_Client_Institution_Contact implements GEntity{
    
    
    final String XML = "Model_Client_Institution_Contact.xml";
    Connection poConn;          //connection
    CachedRowSet poEntity;      //rowset
    String psMessage;           //warning, success or error message
    GRider poGRider;
    int pnEditMode;
    public JSONObject poJSON;
    
    public Model_Client_Institution_Contact(GRider poValue){
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
    public void list() { 
//        Method[] methods = this.getClass().getMethods();
//        
//        System.out.println("--------------------------------------------------------------------");
//        System.out.println("LIST OF PUBLIC METHODS FOR " + this.getClass().getName() + ":");
//        System.out.println("--------------------------------------------------------------------");
//        for (Method method : methods) {
//            System.out.println(method.getName());
//        }
//        
//        try {
//            int lnRow = poEntity.getMetaData().getColumnCount();
//        
//            System.out.println("--------------------------------------------------------------------");
//            System.out.println("ENTITY COLUMN INFO");
//            System.out.println("--------------------------------------------------------------------");
//            System.out.println("Total number of columns: " + lnRow);
//            System.out.println("--------------------------------------------------------------------");
//
//            for (int lnCtr = 1; lnCtr <= lnRow; lnCtr++){
//                System.out.println("Column index: " + (lnCtr) + " --> Label: " + poEntity.getMetaData().getColumnLabel(lnCtr));
//                if (poEntity.getMetaData().getColumnType(lnCtr) == Types.CHAR ||
//                    poEntity.getMetaData().getColumnType(lnCtr) == Types.VARCHAR){
//
//                    System.out.println("Column index: " + (lnCtr) + " --> Size: " + poEntity.getMetaData().getColumnDisplaySize(lnCtr));
//                }
//            }
//        } catch (SQLException e) {
//        }
//        
    }

    @Override
    public String getTable() {
        return "Client_Institution_Contact_Person";
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

    public String getMessage(){
        return psMessage;
    }
    /**
     * Sets the Contact ID of this record.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public boolean setContactID(String fsValue){
        setValue("sContctID", fsValue);
        return true;
    }
    
    /** 
     * @return The Contact ID of this record. 
     */
    public String getContactID(){
        return (String) getValue("sContctID");
    }
    
    /**
     * Sets the ID of this record.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public boolean setClientID(String fsValue){
        setValue("sClientID", fsValue);
        return true;
    }
    
    /** 
     * @return The ID of this record. 
     */
    public String getClientID(){
        return (String) getValue("sClientID");
    }
    /**
     * Sets the name of contact person of this record.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public boolean setContactPerson(String fsValue){
        setValue("sCPerson1", fsValue);
        return true;
    }
    
    /** 
     * @return The name of contact person of this record. 
     */
    public String getContactPerson(){
        return (String) getValue("sCPerson1");
    }
    /**
     * Sets the position of contact person of this record.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public boolean setContactPersonPosition(String fsValue){
        setValue("sCPPosit1", fsValue);
        return true;
    }
    
    /** 
     * @return The position of contact person of this record. 
     */
    public String getContactPersonPosition(){
        return (String) getValue("sCPPosit1");
    }
    /**
     * Sets the mobile no of contact person of this record.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public boolean setContactPersonMobileNo(String fsValue){
        setValue("sMobileNo", fsValue);
        return true;
    }
    
    /** 
     * @return The mobile no of contact person of this record. 
     */
    public String getContactPersonMobileNo(){
        return (String) getValue("sMobileNo");
    }
    /**
     * Sets the telephone no of contact person of this record.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public boolean setContactPersonTelphoneNo(String fsValue){
        setValue("sTelNoxxx", fsValue);
        return true;
    }
    
    /** 
     * @return The telephone no of contact person of this record. 
     */
    public String getContactPersonTelphoneNo(){
        return (String) getValue("sTelNoxxx");
    }
    /**
     * Sets the fax no of contact person of this record.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public boolean setContactPersonsFaxNo(String fsValue){
        setValue("sFaxNoxxx", fsValue);
        return true;
    }
    
    /** 
     * @return The Fax no of contact person of this record. 
     */
    public String getContactPersonsFaxNo(){
        return (String) getValue("sFaxNoxxx");
    }
    
    /**
     * Sets the email of contact person of this record.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public boolean setContactPersonsEmail(String fsValue){
        setValue("sEMailAdd", fsValue);
        return true;
    }
    
    /** 
     * @return The email of contact person of this record. 
     */
    public String getContactPersonsEmail(){
        return (String) getValue("sEMailAdd");
    }
    
    /**
     * Sets the account1 of contact person of this record.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public boolean setContactPersonsAcct1(String fsValue){
        setValue("sAccount1", fsValue);
        return true;
    }
    
    /** 
     * @return The account1 of contact person of this record. 
     */
    public String getContactPersonsAcct1(){
        return (String) getValue("sAccount1");
    }
    
    /**
     * Sets the account2 of contact person of this record.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public boolean setContactPersonsAcct2(String fsValue){
        setValue("sAccount2", fsValue);
        return true;
    }
    
    /** 
     * @return The account2 of contact person of this record. 
     */
    public String getContactPersonsAcct2(){
        return (String) getValue("sAccount2");
    }
    /**
     * Sets the account3 of contact person of this record.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public boolean setContactPersonsAcct3(String fsValue){
        setValue("sAccount3", fsValue);
        return true;
    }
    
    /** 
     * @return The account3 of contact person of this record. 
     */
    public String getContactPersonsAcct3(){
        return (String) getValue("sAccount3");
    }
    
    /**
     * Sets the Remarks for contact person of this record.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public boolean setContactPersonsRemarks(String fsValue){
        setValue("sRemarksx", fsValue);
        return true;
    }
    
    /** 
     * @return The Remarks for contact person of this record. 
     */
    public String getContactPersonsRemarks(){
        return (String) getValue("sRemarksx");
    }
    
    
    /**
     * Sets the contact person as Primary
     * 
     * @param fbValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setPrimary(boolean fbValue){
        return setValue("cPrimaryx", fbValue ? "1" : "0");
    }
    
    /**
     * @return If the contact person set as Primary
     */
    public String isPrimary(){
        return (String) getValue("cPrimaryx");
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
    
    private String getSQL(){
        return "SELECT" +
                    " sContctID" +
                    ", sClientID" +
                    ", sCPerson1" +
                    ", sCPPosit1" +
                    ", sMobileNo" +
                    ", sTelNoxxx" +
                    ", sFaxNoxxx" +
                    ", sEMailAdd" +
                    ", sAccount1" +
                    ", sAccount2" +
                    ", sAccount3" +
                    ", sRemarksx" +
                    ", IFNULL(cPrimaryx,'0') cPrimaryx" +
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
            
            list();
            poEntity.updateString("sContctID", MiscUtil.getNextCode(getTable(), "sContctID", true, poConn, poGRider.getBranchCode()));
            poEntity.updateString("cPrimaryx", Logical.NO);
//            setPrimary(false);
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
        setContactID(MiscUtil.getNextCode(getTable(), "sContctID", true, poGRider.getConnection(), poGRider.getBranchCode()));
        
        poJSON = new JSONObject();
        poJSON.put("result", "success");
        return poJSON;
    }

    @Override
    public JSONObject openRecord(String fsValue) {
        poJSON = new JSONObject();

//        String lsSQL = MiscUtil.makeSelect(this);
//        lsSQL = MiscUtil.addCondition(lsSQL, "sContctID = " + SQLUtil.toSQL(fsValue));


        String lsSQL = getSQL();
        lsSQL = MiscUtil.addCondition(getSQL(), "sContctID = " + SQLUtil.toSQL(fsValue));
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
                setContactID(MiscUtil.getNextCode(getTable(), "sContctID", true, poGRider.getConnection(), poGRider.getBranchCode()));
                
                setModifiedDate(poGRider.getServerDate());
                lsSQL = MiscUtil.makeSQL(this, "xBrgyName»xTownName»xProvName");
                
                if (!lsSQL.isEmpty()){
                    if (poGRider.executeQuery(lsSQL, getTable(), poGRider.getBranchCode(), "") > 0){
                        poJSON.put("result", "success");
                        poJSON.put("sContctID", getContactID());
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
                Model_Client_Institution_Contact loOldEntity = new Model_Client_Institution_Contact(poGRider);
                
                setModifiedDate(poGRider.getServerDate());
                //replace with the primary key column info
                JSONObject loJSON = loOldEntity.openRecord(this.getContactID());
                
                if ("success".equals((String) loJSON.get("result"))){
                    //replace the condition based on the primary key column of the record
                    lsSQL = MiscUtil.makeSQL(this, loOldEntity, "sContctID = " + SQLUtil.toSQL(this.getContactID()));
                    
                    if (!lsSQL.isEmpty()){
                        if (poGRider.executeQuery(lsSQL, getTable(), poGRider.getBranchCode(), "") > 0){
                            poJSON.put("result", "success");
                            poJSON.put("sContctID", getContactID());
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
//                lsSQL = MiscUtil.getNextCode(getTable(), "sContctID", false, poGRider.getConnection(), "");
//                poEntity.updateObject("sContctID", lsSQL);
//                poEntity.updateRow();
//
//                lsSQL = MiscUtil.rowset2SQL(poEntity, getTable(), "");
//            } else {            
//                lsSQL = MiscUtil.rowset2SQL(poEntity, 
//                                            getTable(), 
//                                            "", 
//                                            "sContctID = " + SQLUtil.toSQL(poEntity.getString("sContctID")));
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
