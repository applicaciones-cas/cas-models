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
import org.guanzon.appdriver.agent.ShowDialogFX;
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
public class Model_Client_Address implements GEntity{
    
    
    final String XML = "Model_Client_Address.xml";
    Connection poConn;          //connection
    CachedRowSet poEntity;      //rowset
    String psMessage;           //warning, success or error message
    
    GRider poGRider;
    int pnEditMode;
    public JSONObject poJSON;
    
    public Model_Client_Address(GRider poValue){
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
        Method[] methods = this.getClass().getMethods();
        
        System.out.println("--------------------------------------------------------------------");
        System.out.println("LIST OF PUBLIC METHODS FOR " + this.getClass().getName() + ":");
        System.out.println("--------------------------------------------------------------------");
        for (Method method : methods) {
            System.out.println(method.getName());
        }
        
        try {
            int lnRow = poEntity.getMetaData().getColumnCount();
        
            System.out.println("--------------------------------------------------------------------");
            System.out.println("ENTITY COLUMN INFO");
            System.out.println("--------------------------------------------------------------------");
            System.out.println("Total number of columns: " + lnRow);
            System.out.println("--------------------------------------------------------------------");

            for (int lnCtr = 1; lnCtr <= lnRow; lnCtr++){
                System.out.println("Column index: " + (lnCtr) + " --> Label: " + poEntity.getMetaData().getColumnLabel(lnCtr));
                if (poEntity.getMetaData().getColumnType(lnCtr) == Types.CHAR ||
                    poEntity.getMetaData().getColumnType(lnCtr) == Types.VARCHAR){

                    System.out.println("Column index: " + (lnCtr) + " --> Size: " + poEntity.getMetaData().getColumnDisplaySize(lnCtr));
                }
            }
        } catch (SQLException e) {
        }
        
    }

    /** 
     * this is your table name
     **/
    @Override
    public String getTable() {
        return "Client_Address";
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


    
    /**
     * Sets the AddressID of this record.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setAddressID(String fsValue){
        return setValue("sAddrssID", fsValue);
    }
    
    /** 
     * @return The AddressID of this record. 
     */
    public String getAddressID(){
        return (String) getValue("sAddrssID");
    }
    
    /**
     * Sets the ClientID of this record.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setClientID(String fsValue){
        return setValue("sClientID", fsValue);
    }
    
    /** 
     * @return The ClientID of this record. 
     */
    public String getClientID(){
        return (String) getValue("sClientID");
    }
    /**
     * Sets the Entry no.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setEntryNo(int fsValue){
        return setValue("nEntryNox", fsValue);
    }
    
    /**
     * @return The Entry no.
     */
    public int getEntryNo(){
        return (int) getValue("nEntryNox");
    }
    /**
     * Sets the house no.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setHouseNo(String fsValue){
        return setValue("sHouseNox", fsValue);
    }
    
    /**
     * @return The house no.
     */
    public String getHouseNo(){
        return (String) getValue("sHouseNox");
    }
    
    /**
     * Sets the address.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setAddress(String fsValue){
        return setValue("sAddressx", fsValue);
    }
    
    /**
     * @return The address.
     */
    public String getAddress(){
        return (String) getValue("sAddressx");
    }
    /**
     * Sets the town id.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setTownID(String fsValue){
        return setValue("sTownIDxx", fsValue);
    }
    
    
    /**
     * @return The town id.
     */
    public String getTownID(){
        return (String) getValue("sTownIDxx");
    }
    
    /**
     * @return The town name.
     */
    public String getTownName(){
        return (String) getValue("xTownName");
    }
    /**
     * Sets the town name.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setTownName(String fsValue){
        return setValue("xTownName", fsValue);
    }
    /**
     * Sets the province id.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    
    
    /**
     * @return The province name.
     */
    public String getProvinceName(){
        return (String) getValue("xProvName");
    }
    /**
     * Sets the province name.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setProvinceName(String fsValue){
        return setValue("xProvName", fsValue);
    }
    /**
     * Sets the barangay id.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setBarangayID(String fsValue){
        return setValue("sBrgyIDxx", fsValue);
    }
    
    /**
     * @return The barangay id.
     */
    public String getBarangayID(){
        return (String) getValue("sBrgyIDxx");
    }
    
    /**
     * Sets the barangay name.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setBarangayName(String fsValue){
        return setValue("xBrgyName", fsValue);
    }
    
    /**
     * @return The barangay name.
     */
    public String getBarangayName(){
        return (String) getValue("xBrgyName");
    }
    
    /**
     * Sets the Entry no.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setPriority(int fsValue){
        return setValue("nPriority", fsValue);
    }
    
    /**
     * @return The Entry no.
     */
    public int getPriority(){
        return (int) getValue("nPriority");
    }
    
    /**
     * Sets the Latitude.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setLatitude(int fsValue){
        return setValue("nLatitude", fsValue);
    }
    
    /**
     * @return The Latitude.
     */
    public int getLatitude(){
        return (int) getValue("nLatitude");
    }
    /**
     * Sets the Latitude.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setLongitud(int fsValue){
        return setValue("nLongitud", fsValue);
    }
    
    /**
     * @return The Latitude.
     */
    public int getLongitud(){
        return (int) getValue("nLongitud");
    }
    
    /**
     * Sets the primary address.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setPrimary(String fsValue){
        return setValue("cPrimaryx", fsValue);
    }
    
    /**
     * @return The primary address.
     */
    public String getPrimary(){
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
            return "SELECT " +
                    "  a.sAddrssID, " +
                    "  a.sClientID, " +
                    "  a.sHouseNox, " +
                    "  a.sAddressx, " +
                    "  a.sBrgyIDxx, " +
                    "  a.sTownIDxx, " +
                    "  a.nLatitude, " +
                    "  a.nLongitud, " +
                    "  a.cPrimaryx, " +
                    "  a.cOfficexx, " +
                    "  a.cProvince, " +
                    "  a.cBillingx, " +
                    "  a.cShipping, " +
                    "  a.cCurrentx, " +
                    "  a.cLTMSAddx, " +
                    "  a.sSourceCd, " +
                    "  a.sReferNox, " +
                    "  a.cRecdStat, " +
                    "  a.dModified, " +
                    "  b.sTownName    xTownName, " +
                    "  d.sBrgyName    xBrgyName, " +
                    "  c.sProvName    xProvName " +
                    "FROM Client_Address a " +
                    "  LEFT JOIN TownCity b " +
                    "    ON a.sTownIDxx = b.sTownIDxx " +
                    "  LEFT JOIN Province c " +
                    "    ON b.sProvIDxx = c.sProvIDxx " +
                    "  LEFT JOIN Barangay d " +
                    "    ON a.sBrgyIDxx = d.sBrgyIDxx " +
                    "WHERE a.cRecdStat = '1'";
    }
    private void initialize(){
        
        try {
            System.out.println("path = " + System.getProperty("sys.default.path.metadata") + XML);
            poEntity = MiscUtil.xml2ResultSet(System.getProperty("sys.default.path.metadata") + XML, getTable());
            
            poEntity.last();
            poEntity.moveToInsertRow();

            MiscUtil.initRowSet(poEntity);      
            poEntity.updateString("cOfficexx", Logical.NO);
            poEntity.updateString("cProvince", Logical.NO);
            poEntity.updateString("cBillingx", Logical.NO);
            poEntity.updateString("cShipping", Logical.NO);
            poEntity.updateString("cCurrentx", Logical.NO);
            poEntity.updateString("cLTMSAddx", Logical.NO);
            poEntity.updateString("cPrimaryx", Logical.NO);
            poEntity.updateDouble("nLatitude", 0.0);
            poEntity.updateDouble("nLongitud", 0.0);
            poEntity.updateString("cRecdStat", RecordStatus.ACTIVE);
            
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
        setAddressID(MiscUtil.getNextCode(getTable(), "sAddrssID", true, poGRider.getConnection(), poGRider.getBranchCode()));
        
        poJSON = new JSONObject();
        poJSON.put("result", "success");
        poJSON.put("message", "New record success.");
        return poJSON;
    }

    @Override
    public JSONObject openRecord(String fsValue) {
        poJSON = new JSONObject();

//        String lsSQL = MiscUtil.makeSelect(this);
//        lsSQL = MiscUtil.addCondition(lsSQL, "sAddrssID = " + SQLUtil.toSQL(fsValue));


        String lsSQL = getSQL();
        lsSQL = MiscUtil.addCondition(getSQL(), "a.sAddrssID = " + SQLUtil.toSQL(fsValue));
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
                setAddressID(MiscUtil.getNextCode(getTable(), "sAddrssID", true, poGRider.getConnection(), poGRider.getBranchCode()));
                
                setModifiedDate(poGRider.getServerDate());
                lsSQL = MiscUtil.makeSQL(this, "xBrgyName»xTownName»xProvName");
                
                if (!lsSQL.isEmpty()){
                    if (poGRider.executeQuery(lsSQL, getTable(), poGRider.getBranchCode(), "") > 0){
                        poJSON.put("result", "success");
                        poJSON.put("sAddrssID", getAddressID());
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
                Model_Client_Address loOldEntity = new Model_Client_Address(poGRider);
                
                setModifiedDate(poGRider.getServerDate());
                //replace with the primary key column info
                JSONObject loJSON = loOldEntity.openRecord(this.getAddressID());
                
                if ("success".equals((String) loJSON.get("result"))){
                    //replace the condition based on the primary key column of the record
                    lsSQL = MiscUtil.makeSQL(this, loOldEntity, "sAddrssID = " + SQLUtil.toSQL(this.getAddressID()), "xBrgyName»xTownName»xProvName");
                    
                    if (!lsSQL.isEmpty()){
                        if (poGRider.executeQuery(lsSQL, getTable(), poGRider.getBranchCode(), "") > 0){
                            poJSON.put("result", "success");
                            poJSON.put("sAddrssID", getAddressID());
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
    public JSONObject setValue(int fnColumn, Object foValue) {
        try {              
            poJSON = MiscUtil.validateColumnValue(System.getProperty("sys.default.path.metadata") + XML, MiscUtil.getColumnLabel(poEntity, fnColumn), foValue);
            if ("error".equals((String) poJSON.get("result"))) return poJSON;
            
            poEntity.updateObject(fnColumn, foValue);
            poEntity.updateRow();
            
            poJSON = new JSONObject();
            poJSON.put("result", "success");
            poJSON.put("value", getValue(fnColumn));
            System.out.println("poJSON = " + poJSON);
            
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
    public JSONObject SearchBarangay(String fsValue, boolean fbByCode) {
        
        JSONObject loJSON;
        if (fbByCode){
            if (fsValue.equals((String) getBarangayID())) {
                loJSON = new JSONObject();
                loJSON.put("result", "success");
                loJSON.put("message", "Search barangay success.");
                return loJSON;
            }
        }else{
            if(getValue(21)!= null && !getValue(21).toString().trim().isEmpty()){
                if (fsValue.equals(getValue(21))){
                    loJSON = new JSONObject();
                    loJSON.put("result", "success");
                    loJSON.put("message", "Search barangay success.");
                    return loJSON;
                }
            }
        }
      String lsSQL = "SELECT " +
                            "  a.sBrgyIDxx" +
                            ", a.sBrgyName" +
                            ", b.sTownName" + 
                            ", b.sZippCode" +
                            ", c.sProvName" + 
                            ", c.sProvIDxx" +
                            ", b.sTownIDxx" +
                        " FROM Barangay a" + 
                            ", TownCity b" +
                            ", Province c" +
                        " WHERE a.sTownIDxx = b.sTownIDxx" + 
                            " AND b.sProvIDxx = c.sProvIDxx" + 
                            " AND a.cRecdStat = '1'" + 
                            " AND b.cRecdStat = '1'" + 
                            " AND c.cRecdStat = '1'" + 
                            " AND a.sTownIDxx = " + SQLUtil.toSQL(getTownID());
        
        if (fbByCode)
            lsSQL = MiscUtil.addCondition(lsSQL, "a.sBrgyIDxx = " + SQLUtil.toSQL(fsValue));
        else
            lsSQL = MiscUtil.addCondition(lsSQL, "a.sBrgyName LIKE " + SQLUtil.toSQL(fsValue + "%"));
       
            
            
            
        loJSON = ShowDialogFX.Search(poGRider, 
                            lsSQL, 
                            fsValue,
                            "ID»Barangay»Town»Province", 
                            "sBrgyIDxx»sBrgyName»sTownName»sProvName",
                            "sBrgyIDxx»sBrgyName»sTownName»sProvName",
                            fbByCode ? 0 : 1);
            
            if (loJSON != null) {
                setBarangayID((String) loJSON.get("sBrgyIDxx"));
                setBarangayName((String) loJSON.get("sBrgyName"));
//                setValue(13, (String) loJSON.get("sBrgyName"));
                
                loJSON.put("result", "success");
                loJSON.put("message", "Search barangay success.");
                return loJSON;
            }else {
                loJSON.put("result", "error");
                loJSON.put("message", "No record selected.");
                return loJSON;
            }
    }
    public JSONObject SearchTown(String fsValue, boolean fbByCode) {
        
        JSONObject loJSON;
        if (fbByCode){
            if (fsValue.equals((String) getTownID())) {
                loJSON = new JSONObject();
                loJSON.put("result", "success");
                loJSON.put("message", "Search town success.");
                return loJSON;
            }
        }else{
            
            String townProvince = getValue(20) + ", " + getValue(22);
            System.out.println("fsValue = " + fsValue);
            System.out.println("townProvince = " + townProvince);
            if (fsValue.equals(townProvince)){
                loJSON = new JSONObject();
                loJSON.put("result", "success");
                loJSON.put("message", "Search town success.");
                return loJSON;
            }
        }
        
       String lsSQL = "SELECT " +
                            "  a.sTownIDxx" +
                            ", a.sTownName" + 
                            ", a.sZippCode" +
                            ", b.sProvName" + 
                            ", b.sProvIDxx" +
                        " FROM TownCity a" +
                            ", Province b" +
                        " WHERE a.sProvIDxx = b.sProvIDxx" + 
                            " AND a.cRecdStat = '1'";
        
        if (fbByCode)
            lsSQL = MiscUtil.addCondition(lsSQL, "a.sTownIDxx = " + SQLUtil.toSQL(fsValue));
        else
            lsSQL = MiscUtil.addCondition(lsSQL, "a.sTownName LIKE " + SQLUtil.toSQL(fsValue + "%"));
       
            
      
        System.out.println("lsSQL Town = " + lsSQL);
        
        loJSON = ShowDialogFX.Search(poGRider, 
                            lsSQL, 
                            fsValue,
                            "ID»Town»Postal Code»Province", 
                            "sTownIDxx»sTownName»sZippCode»sProvName", 
                            "a.sTownIDxx»a.sTownName»a.sZippCode»b.sProvName", 
                            fbByCode ? 0 : 1);
        System.out.println("loJSON Town = " + loJSON);
            
            if (loJSON != null) {
                setTownID((String) loJSON.get("sTownIDxx"));
                setTownName((String) loJSON.get("sTownName"));
                setProvinceName((String) loJSON.get("sProvName"));
                setBarangayID("");
                setBarangayName("");
                loJSON.put("result", "success");
                loJSON.put("message", "Search town success.");
//                loJSON.put("message", "Search town success.");
                return loJSON;
            }else {
                loJSON.put("result", "error");
                loJSON.put("message", "No record selected.");
                return loJSON;
            }
    }
    

    @Override
    public int getEditMode() {
        return pnEditMode;
    }
    
    
    
}
