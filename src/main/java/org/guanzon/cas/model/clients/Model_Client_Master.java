package org.guanzon.cas.model.clients;

import com.google.gson.annotations.SerializedName;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
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
import org.guanzon.appdriver.agent.ShowDialogFX;
import org.guanzon.appdriver.base.CommonUtils;
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
public class Model_Client_Master implements GEntity{
    
    final String XML = "Model_Client_Master.xml";
    Connection poConn;          //connection
    CachedRowSet poEntity;      //rowset
    String psMessage;           //warning, success or error message
           //warning, success or error message
    GRider poGRider;
    int pnEditMode;
    public JSONObject poJSON;
    public Model_Client_Master(GRider poValue){
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
        return "Client_Master";
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

    /**
     * Gets the value of a column index name.
     *
     * @param fsColumn - column index name
     * @return object value
     */
    @Override
    public Object getValue(String fsColumn) {
        try {
            return poEntity.getObject(MiscUtil.getColumnIndex(poEntity, fsColumn));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
//    @Override
//    public Object getValue(int fnColumn) {
//        try {
//            return poEntity.getObject(fnColumn);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public Object getValue(String fsColumn) {
//        try {
//            return poEntity.getObject(fsColumn);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
    
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
     * Sets the client type of the record.<br>
     * 0 for Individual<br>
     * 1 for Institution
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setClientType(String fsValue){
        return setValue("cClientTp", fsValue);
    }
    
    /**
     * @return The client type of the record.
     */
    public String getClientType(){
        return (String) getValue("cClientTp");
    }
    
    /**
     * Sets the last name of the client.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setLastName(String fsValue){
        return setValue("sLastName", fsValue);
    }
    
    /**
     * @return The last name of the client.
     */
    public String getLastName(){
        return (String) getValue("sLastName");
    }
    
    /**
     * Sets the first name of the client.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setFirstName(String fsValue){
        return setValue("sFrstName", fsValue);
    }
    
    /**
     * @return The last name of the client.
     */
    public String getFirstName(){
        return (String) getValue("sFrstName");
    }
    
    /**
     * Sets the middle name of the client.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setMiddleName(String fsValue){
        return setValue("sMiddName", fsValue);
    }
    
    /**
     * @return The middle name of the client.
     */
    public String getMiddleName(){
        return (String) getValue("sMiddName");
    }
    
    /**
     * Sets the suffix name of the client.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setSuffixName(String fsValue){
        return setValue("sSuffixNm", fsValue);
    }
    
    /**
     * @return The suffix name of the client.
     */
    public String getSuffixName(){
        return (String) getValue("sSuffixNm");
    }
    
    /**
     * Sets the complete name of the client.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setFullName(String fsValue){
        return setValue("sCompnyNm", fsValue);
    }
    
    /**
     * @return The complete name of the client.
     */
    public String getFullName(){
        return (String) getValue("sCompnyNm");
    }
    
    /**
     * Sets the complete name of the client.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setMaidenName(String fsValue){
        return setValue("sMaidenNm", fsValue);
    }
    
    /**
     * @return The complete name of the client.
     */
    public String getMaidenName(){
        return (String) getValue("sMaidenNm");
    }
    
    /**
     * Set the sex of the client.<br>
     * 0 for Male
     * 1 for Female
     * 2 for LGBT
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setSex(String fsValue){
        return setValue("cGenderCd", fsValue);
    }
    
    /**
     * @return The sex of the client.
     */
//    public String getSex(){
//        return (String) getValue("cGenderCd");
//    }


    public String getSex(){
        Object value = getValue("cGenderCd");
        if (value instanceof Integer) {
            return String.valueOf(value);
        } else {
            return (String) value;
        }
    }
    /**
     * Set the civil status of the client.<br>
     * 0 for Single
     * 1 for Married
     * 2 for Separated
     * 3 for Widowed
     * 4 for Single Parent
     * 5 for Single Parent w/ Live-in Partner
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setCivilStatus(String fsValue){
        return setValue("cCvilStat", fsValue);
    }
    
    /**
     * @return The civil status of the client.
     */
    
    public String getCivilStatus(){
        Object value = getValue("cCvilStat"); // Assuming "civilStatus" is the field representing civil status
        if (value instanceof Integer) {
            return String.valueOf(value);
        } else {
            return (String) value;
        }
    }
//    public String getCivilStatus(){
//        return (String) getValue("cCvilStat");
//    }
    
    /**
     * Sets the citizenship ID of the client.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setCitizenshipID(String fsValue){
        return setValue("sCitizenx", fsValue);
    }
    
    /**
     * @return The citizenship ID of the client.
     */
    public String getCitizenshipID(){
        return (String) getValue("sCitizenx");
    }
    
    /**
     * Sets the citizenship name of the client.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setCitizenshipName(String fsValue){
        return setValue("xCitizenx", fsValue);
    }
    
    /**
     * @return The citizenship name of the client.
     */
    public String getCitizenshipName(){
        return (String) getValue("xCitizenx");
    }
    
    /**
     * Sets the date of birth of the client.
     * 
     * @param fdValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setBirthDate(Date fdValue){
        return setValue("dBirthDte", fdValue);
    }
    
    /**
     * @return The date of birth of the client.
     */
    public Date getBirthDate(){
        System.out.println("dBirthDte" + getValue("dBirthDte"));
        Date date = null;
        if(!getValue("dBirthDte").toString().isEmpty()){
            date = CommonUtils.toDate(getValue("dBirthDte").toString());
        }
        return date;
    }
    
    /**
     * Sets the birth place ID of the client.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setBirthPlaceID(String fsValue){
        return setValue("sBirthPlc", fsValue);
    }
    
    /**
     * @return The birth place ID of the client.
     */
    public String getBirthPlaceID(){
        return (String) getValue("sBirthPlc");
    }
    
    /**
     * Sets the birth place name of the client.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setBirthPlaceName(String fsValue){
        return setValue("xBirthPlc", fsValue);
    }
    
    /**
     * @return The birth place name of the client.
     */
    public String getBirthPlaceName(){
        return (String) getValue("xBirthPlc");
    }
    
    /**
     * Sets additional information for the client.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setAdditionalInfo(String fsValue){
        return setValue("sAddlInfo", fsValue);
    }
    
    /** 
     * @return The additional information for the client.
     */
    public String getAdditionalInfo(){
        return (String) getValue("sAddlInfo");
    }
    
    /**
     * Sets the spouse ID of the client.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setSpouseID(String fsValue){
        return setValue("sSpouseID", fsValue);
    }
    
    /**
     * @return The spouse ID of the client.
     */
    public String getSpouseID(){
        return (String) getValue("sSpouseID");
    }
    
    /**
     * Sets the spouse name of the client.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setSpouseName(String fsValue){
        return setValue("xSpouseNm", fsValue);
    }
    
    /**
     * @return The spouse name of the client.
     */
    public String getSpouseName(){
        return (String) getValue("xSpouseNm");
    }
    
    /**
     * Sets the tax identification number of the client.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setTaxIDNumber(String fsValue){
        return setValue("sTaxIDNox", fsValue);
    }
    
    /**
     * @return The tax identification number of the client.
     */
    public String getTaxIDNumber(){
        return (String) getValue("sTaxIDNox");
    }
    
    /**
     * Sets the LTO identification number of the client.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setLTOIDNumber(String fsValue){
        return setValue("sLTOIDxxx", fsValue);
    }
    
    /**
     * @return The LTO identification number of the client.
     */
    public String getLTOIDNumber(){
        return (String) getValue("sLTOIDxxx");
    }
    
    
    /**
     * Sets the Philippines national identification number of the client.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setPHNationalIDNumber(String fsValue){
        return setValue("sPHBNIDxx", fsValue);
    }
    
    /**
     * @return The Philippines national identification number of the client.
     */
    public String getPHNationalIDNumber(){
        return (String) getValue("sPHBNIDxx");
    }
    
    /**
     * Sets if the client has loan to the company.
     * 
     * @param fbValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setLoanReceivableClient(boolean fbValue){
        return setValue("cLRClient", fbValue ? "1" : "0");
    }
    
    /**
     * @return If the client has loan to the company.
     */
    public boolean isLoanReceivableClient(){
        return ((String) getValue("cLRClient")).equals("1");
    }
    
    /**
     * Sets if the client purchased motorcycle.
     * 
     * @param fbValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setMotorcycleSalesClient(boolean fbValue){
        return setValue("cMCClient", fbValue ? "1" : "0");
    }
    
    /**
     * @return If the client purchased motorcycle.
     */
    public boolean isMotorcycleSalesClient(){
        return ((String) getValue("cMCClient")).equals("1");
    }
    
    /**
     * Sets if the client is a job order customer.
     * 
     * @param fbValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setServiceCenterClient(boolean fbValue){
        return setValue("cSCClient", fbValue ? "1" : "0");
    }
    
    /**
     * @return If the client is a job order customer.
     */
    public boolean isServiceCenterClient(){
        return ((String) getValue("cSCClient")).equals("1");
    }
    
    /**
     * Sets if the client purchased spare parts.
     * 
     * @param fbValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setSparepartsSalesClient(boolean fbValue){
        return setValue("cSPClient", fbValue ? "1" : "0");
    }
    
    /**
     * @return If the client purchase spare parts.
     */
    public boolean isSparepartsSalesClient(){
        return ((String) getValue("cSPClient")).equals("1");
    }
    
    /**
     * Sets if the client purchased mobile phone.
     * 
     * @param fbValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setCellphoneSalesClient(boolean fbValue){
        return setValue("cCPClient", fbValue ? "1" : "0");
    }
    
    /**
     * @return If the client purchased mobile phone.
     */
    public boolean isCellphoneSalesClient(){
        return ((String) getValue("cCPClient")).equals("1");
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
                "  a.sClientID" +
                ", a.cClientTp" +
                ", a.sLastName" +
                ", a.sFrstName" +
                ", a.sMiddName" +
                ", a.sSuffixNm" +
                ", a.sMaidenNm" +
                ", a.sCompnyNm" +
                ", a.cGenderCd" +
                ", a.cCvilStat" +
                ", a.sCitizenx" +
                ", a.dBirthDte" +
                ", a.sBirthPlc" +
                ", a.sAddlInfo" +
                ", a.sSpouseID" +
                ", a.sTaxIDNox" +
                ", a.sLTOIDxxx" +
                ", a.sPHBNIDxx" +
                ", a.cLRClient" +
                ", a.cMCClient" +
                ", a.cSCClient" +
                ", a.cSPClient" +
                ", a.cCPClient" +
                ", a.cRecdStat" +
                ", a.sModified" +
                ", a.dModified" +
                ", CONCAT(b.sTownName, ', ', e.sProvName) AS xBirthPlc" +
                ", c.sCntryNme xCitizenx" +
                ", d.sCompnyNm xSpouseNm" +
                " FROM " + getTable() + " a" +
                            " LEFT JOIN TownCity b ON a.sBirthPlc = b.sTownIDxx" +
                            " LEFT JOIN Province e ON b.sProvIDxx = e.sProvIDxx" +
                            " LEFT JOIN Country c ON a.sCitizenx = c.sCntryCde" +
                            " LEFT JOIN Client_Master d ON a.sSpouseID = d.sClientID" +
                        " WHERE a.cRecdStat = '1'";
    }
    
    private void initialize(){
        
            
        
        try {
            
            poEntity = MiscUtil.xml2ResultSet(System.getProperty("sys.default.path.metadata") + XML, getTable());
            
            poEntity.last();
            poEntity.moveToInsertRow();

            MiscUtil.initRowSet(poEntity);      
            poEntity.updateString("cClientTp", Logical.NO);
            poEntity.updateString("cCvilStat", Logical.NO);
            poEntity.updateString("cLRClient", Logical.NO);
            poEntity.updateString("cMCClient", Logical.NO);
            poEntity.updateString("cSCClient", Logical.NO);
            poEntity.updateString("cSPClient", Logical.NO);
            poEntity.updateString("cCPClient", Logical.NO);
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
        setClientID(MiscUtil.getNextCode(getTable(), "sClientID", true, poConn, poGRider.getBranchCode()));
        
        poJSON = new JSONObject();
        poJSON.put("result", "success");
        return poJSON;
    }

    @Override
    public JSONObject openRecord(String fsValue) { pnEditMode = EditMode.UPDATE;
        poJSON = new JSONObject();

        String lsSQL = getSQL();
        lsSQL = MiscUtil.addCondition(getSQL(), "a.sClientID = " + SQLUtil.toSQL(fsValue));
        System.out.println(lsSQL);
        ResultSet loRS = poGRider.executeQuery(lsSQL);

        try {
            if (loRS.next()){
                for (int lnCtr = 1; lnCtr <= loRS.getMetaData().getColumnCount(); lnCtr++){
                    setValue(lnCtr, loRS.getObject(lnCtr));
                    System.out.println(loRS.getMetaData().getColumnLabel(lnCtr) + " = " + loRS.getString(lnCtr));
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
                setModifiedBy(poGRider.getUserID());
                
                lsSQL = MiscUtil.makeSQL(this, "xBirthPlc»xCitizenx»xSpouseNm");
                
                if (!lsSQL.isEmpty()){
                    if (poGRider.executeQuery(lsSQL, getTable(), poGRider.getBranchCode(), "") > 0){
                        poJSON.put("result", "success");
                        poJSON.put("sClientID", getClientID());
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
                Model_Client_Master loOldEntity = new Model_Client_Master(poGRider);
                
                //replace with the primary key column info
                JSONObject loJSON = loOldEntity.openRecord(this.getClientID());
                
                if ("success".equals((String) loJSON.get("result"))){
                    //replace the condition based on the primary key column of the record
                    setModifiedDate(poGRider.getServerDate());
                    setModifiedBy(poGRider.getUserID());
                    
                    lsSQL = MiscUtil.makeSQL(this, loOldEntity, "sClientID = " + SQLUtil.toSQL(this.getClientID()), "xBirthPlc»xCitizenx»xSpouseNm");
                    
                    if (!lsSQL.isEmpty()){
                        if (poGRider.executeQuery(lsSQL, getTable(), poGRider.getBranchCode(), "") > 0){
                            poJSON.put("result", "success");
                            poJSON.put("sClientID", getClientID());
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