package org.guanzon.cas.model.clients.ap;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;
import org.guanzon.appdriver.base.CommonUtils;
import org.guanzon.appdriver.base.GRider;
import org.guanzon.appdriver.base.MiscUtil;
import org.guanzon.appdriver.base.SQLUtil;
import org.guanzon.appdriver.constant.RecordStatus;
import org.guanzon.appdriver.constant.EditMode;
import org.guanzon.appdriver.iface.GEntity;
import org.json.simple.JSONObject;

/**
 * @author Michael Cuison
 */
public class Model_AP_Client_Master implements GEntity {

    final String XML = "Model_AP_Client_Master.xml";

    GRider poGRider;                //application driver
    CachedRowSet poEntity;          //rowset
    JSONObject poJSON;              //json container
    int pnEditMode;                 //edit mode

    /**
     * Entity constructor
     *
     * @param foValue - GhostRider Application Driver
     */
    public Model_AP_Client_Master(GRider foValue) {
        if (foValue == null) {
            System.err.println("Application Driver is not set.");
            System.exit(1);
        }

        poGRider = foValue;

        initialize();
    }

    /**
     * Gets edit mode of the record
     *
     * @return edit mode
     */
    @Override
    public int getEditMode() {
        return pnEditMode;
    }

    /**
     * Gets the column index name.
     *
     * @param fnValue - column index number
     * @return column index name
     */
    @Override
    public String getColumn(int fnValue) {
        try {
            return poEntity.getMetaData().getColumnLabel(fnValue);
        } catch (SQLException e) {
        }
        return "";
    }

    /**
     * Gets the column index number.
     *
     * @param fsValue - column index name
     * @return column index number
     */
    @Override
    public int getColumn(String fsValue) {
        try {
            return MiscUtil.getColumnIndex(poEntity, fsValue);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Gets the total number of column.
     *
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
     * Gets the table name.
     *
     * @return table name
     */
    @Override
    public String getTable() {
        return "AP_Client_Master";
    }

    /**
     * Gets the value of a column index number.
     *
     * @param fnColumn - column index number
     * @return object value
     */
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

    /**
     * Sets column value.
     *
     * @param fnColumn - column index number
     * @param foValue - value
     * @return result as success/failed
     */
    @Override
    public JSONObject setValue(int fnColumn, Object foValue) {
        
            poJSON = new JSONObject();
        try{
            
//            poJSON = MiscUtil.validateColumnValue(System.getProperty("sys.default.path.metadata") + XML, MiscUtil.getColumnLabel(poEntity, fnColumn), foValue);
//            if ("error".equals((String) poJSON.get("result"))) {
//                return poJSON;
//            }

            poEntity.updateObject(fnColumn, foValue);
            poEntity.updateRow();

            poJSON = new JSONObject();
            poJSON.put("result", "success");
            poJSON.put(MiscUtil.getColumnLabel(poEntity, fnColumn), getValue(fnColumn));
        } catch (SQLException e) {
            e.printStackTrace();
            poJSON.put("result", "error");
            poJSON.put("message", e.getMessage());
        }

        return poJSON;
    }

    /**
     * Sets column value.
     *
     * @param fsColumn - column index name
     * @param foValue - value
     * @return result as success/failed
     */
    @Override
    public JSONObject setValue(String fsColumn, Object foValue) {
        poJSON = new JSONObject();

        try {
            return setValue(MiscUtil.getColumnIndex(poEntity, fsColumn), foValue);
        } catch (SQLException e) {
            e.printStackTrace();
            poJSON.put("result", "error");
            poJSON.put("message", e.getMessage());
        }
        return poJSON;
    }

    /**
     * Set the edit mode of the entity to new.
     *
     * @return result as success/failed
     */
    @Override
    public JSONObject newRecord() {
        pnEditMode = EditMode.ADDNEW;

        //replace with the primary key column info
        poJSON = new JSONObject();
        poJSON.put("result", "success");
        return poJSON;
    }

    /**
     * Opens a record.
     *
     * @param fsCondition - filter values
     * @return result as success/failed
     */
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
//    @Override
//    public JSONObject openRecord(String fsCondition) {
//        poJSON = new JSONObject();
//
////        String lsSQL = MiscUtil.makeSelect(this, "xClientNm»xAddressx»xCPerson1»xCPPosit1»xCategrNm»xTermName»xTaxIDNox»xMobileNo");
//        String lsSQL = makeSelectSQL();
//
//        //replace the condition based on the primary key column of the record
//        lsSQL = MiscUtil.addCondition(lsSQL, " sClientID = " + SQLUtil.toSQL(fsCondition));
//        System.out.println("lsSQL = " + lsSQL);
//        ResultSet loRS = poGRider.executeQuery(lsSQL);
//
//        try {
//            if (loRS.next()) {
//                for (int lnCtr = 1; lnCtr <= loRS.getMetaData().getColumnCount(); lnCtr++) {
//                    setValue(lnCtr, loRS.getObject(lnCtr));
//                }
//
//                pnEditMode = EditMode.UPDATE;
//
//                poJSON.put("result", "success");
//                poJSON.put("message", "Record loaded successfully.");
//            } else {
//                poJSON.put("result", "error");
//                poJSON.put("message", "No record to load.");
//            }
//        } catch (SQLException e) {
//            poJSON.put("result", "error");
//            poJSON.put("message", e.getMessage());
//        }
//
//        return poJSON;
//    }

    /**
     * Save the entity.
     *
     * @return result as success/failed
     */
    @Override
    public JSONObject saveRecord() {
        poJSON = new JSONObject();

        if (pnEditMode == EditMode.ADDNEW || pnEditMode == EditMode.UPDATE) {
            String lsSQL;
            if (pnEditMode == EditMode.ADDNEW) {
                //replace with the primary key column info

                setModifiedDate(poGRider.getServerDate());
//                lsSQL = makeSQL();

                lsSQL = MiscUtil.makeSQL(this,  "xClientNm»xAddressx»xCPerson1»xCPPosit1»xCategrNm»xTermName");
                if (!lsSQL.isEmpty()) {
                    if (poGRider.executeQuery(lsSQL, getTable(), poGRider.getBranchCode(), "") > 0) {
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
                Model_AP_Client_Master loOldEntity = new Model_AP_Client_Master(poGRider);

                //replace with the primary key column info
                JSONObject loJSON = loOldEntity.openRecord(this.getClientID());
                
                setModifiedDate(poGRider.getServerDate());
                if ("success".equals((String) loJSON.get("result"))) {
                    //replace the condition based on the primary key column of the record
                    lsSQL = MiscUtil.makeSQL(this, loOldEntity, "sClientID = " + SQLUtil.toSQL(this.getClientID()), "xClientNm»xAddressx»xCPerson1»xCPPosit1»xCategrNm»xTermName");

                    if (!lsSQL.isEmpty()) {
                        if (poGRider.executeQuery(lsSQL, getTable(), poGRider.getBranchCode(), "") > 0) {
                            poJSON.put("result", "success");
                            poJSON.put("message", "Record saved successfully.");
                        } else {
                            poJSON.put("result", "error");
                            poJSON.put("message", poGRider.getErrMsg());
                        }
                    } else {
                        poJSON.put("result", "error");
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

    
    /**
     * Prints all the public methods used<br>
     * and prints the column names of this entity.
     */
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

            for (int lnCtr = 1; lnCtr <= lnRow; lnCtr++) {
                System.out.println("Column index: " + (lnCtr) + " --> Label: " + poEntity.getMetaData().getColumnLabel(lnCtr));
                if (poEntity.getMetaData().getColumnType(lnCtr) == Types.CHAR
                        || poEntity.getMetaData().getColumnType(lnCtr) == Types.VARCHAR) {

                    System.out.println("Column index: " + (lnCtr) + " --> Size: " + poEntity.getMetaData().getColumnDisplaySize(lnCtr));
                }
            }
        } catch (SQLException e) {
        }

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
     * Sets the Address ID of this record.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setAddressID(String fsValue){
        return setValue("sAddrssID", fsValue);
    }
    
    /**
     * @return The Address ID of this record. 
     */
    public String getAddressID(){
        return (String) getValue("sAddrssID");
    }
    
    
    /**
     * Sets the Address ID of this record.
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setContctID(String fsValue){
        return setValue("sContctID", fsValue);
    }
    
    /**
     * @return The Address ID of this record. 
     */
    public String getContctID(){
        return (String) getValue("sContctID");
    }

    /**
     * Sets the sCategrCd of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setCategoryCode(String fsValue) {
        return setValue("sCategrCd", fsValue);
    }

    /**
     * @return The sCategrCd of this record.
     */
    public String getCategoryCode() {
        return (String) getValue("sCategrCd");
    }

    /**
     * Sets the Client Since of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setClientSince(Date fdValue) {
        return setValue("dCltSince", fdValue);
    }

    /**
     * @return The Client Since of this record.
     */
    public Date getClientSince() {
        System.out.println("dCltSince" + getValue("dCltSince"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        if(!getValue("dCltSince").toString().isEmpty()){
            try {
                date = formatter.parse(getValue("dCltSince").toString());
            } catch (ParseException ex) {
                Logger.getLogger(Model_AP_Client_Master.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return date;
    }

    /**
     * Sets the Beginning Date of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setBeginDate(Date fdValue) {
        return setValue("dCltSince", fdValue);
    }

    /**
     * @return The Beginning Date of this record.
     */
    public Date getBeginDate() {
        System.out.println("dBegDatex" + getValue("dBegDatex"));
        Date date = null;
        if(!getValue("dBegDatex").toString().isEmpty()){
            date = CommonUtils.toDate(getValue("dBegDatex").toString());
        }
        return date;
    }
    
    /**
     * Sets the Beginning Balance of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setBeginBal(String fdValue) {
        return setValue("nBegBalxx", fdValue);
    }

    /**
     * @return The Beginning Balance of this record.
     */
    public Object getBeginBal() {
        return (Object) getValue("nBegBalxx");
    }

    /**
     * Sets the Term of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setTerm(String fdValue) {
        return setValue("sTermIDxx", fdValue);
    }

    /**
     * @return The Discount of this record.
     */
    public Object getDiscount() {
        return (Object) getValue("nDiscount");
    }
      /**
     * Sets the Discount of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setDiscount(String fdValue) {
        return setValue("nDiscount", fdValue);
    }

    
    /**
     * @return The Credit Limit of this record.
     */
    public Object getCreditLimit() {
        return (Object) getValue("nCredLimt");
    }
      /**
     * Sets the Credit Limit of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setCreditLimit(String fdValue) {
        return setValue("nCredLimt", fdValue);
    }
    
    /**
     * @return The ABalance of this record.
     */
    public Object getABalance() {
        return (Object) getValue("nABalance");
    }
      /**
     * Sets the ABalance of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setABalance(String fdValue) {
        return setValue("nABalance", fdValue);
    }
    
    /**
     * @return The ABalance of this record.
     */
    public Object getOBalance() {
        return (Object) getValue("nOBalance");
    }
      /**
     * Sets the ABalance of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setOBalance(String fdValue) {
        return setValue("nOBalance", fdValue);
    }
    
    /**
     * @return The LedgerNo of this record.
     */
    public String getLedgerNo() {
        return (String) getValue("nLedgerNo");
    }
      /**
     * Sets the LedgerNo of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setLedgerNo(String fdValue) {
        return setValue("nLedgerNo", fdValue);
    }
    

    /**
     * @return The Term of this record.
     */
    public String getTerm() {
        return (String) getValue("sTermIDxx");
    }

    /**
     * @return The Client Name of this record. 
     */
    public String getClientName(){
        return (String) getValue("xClientNm");
    }
    
    
    /**
     * @return The Client Contact Person of this record. 
     */
    public String getContactPerson(){
        return (String) getValue("xCPerson1");
    }
    
    /**
     * @return The Client Contact Person of this record. 
     */
    public String getContactNo(){
        return (String) getValue("xMobileNo");
    }
    
    /**
     * @return The Client Contact Person Mobile No of this record. 
     */
    public String getContactPosition(){
        return (String) getValue("xCPerson1");
    }
    
    /**
     * @return The Client Tax ID no of this record. 
     */
    public String getTaxIDNumber(){
        return (String) getValue("xTaxIDNox");
    }
    
    /**
     * @return The Address of this record. 
     */
    public String getAddress(){
        return (String) getValue("xAddressx");
    }
    
    /**
     * @return The Address of this record. 
     */
    public String getCategoryName(){
        return (String) getValue("xCategrNm");
    }
    
    /**
     * @return The Term name of this record. 
     */
    public String getTermName(){
        return (String) getValue("xTermName");
    }
    
    /**
     * Sets the RecdStat of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setRecdStat(String fsValue) {
        return setValue("cRecdStat", fsValue);
    }
    /**
     * @return The RecdStat of this record.
     */
    public String getRecdStat() {
        return (String) getValue("cRecdStat");
    }
    
    /**
     * Sets the Vatable of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setVatable(String fsValue) {
        return setValue("cVatablex", fsValue);
    }
    /**
     * @return The Vatable of this record.
     */
    public String getVatable() {
        return (String) getValue("cVatablex");
    }

    /**
     * Sets record as active.
     *
     * @param fbValue
     * @return result as success/failed
     */
    public JSONObject setActive(boolean fbValue) {
        return setValue("cRecdStat", fbValue ? "1" : "0");
    }

    /**
     * @return If record is active.
     */
    public boolean isActive() {
        return ((String) getValue("cRecdStat")).equals("1");
        }

    /**
     * Sets the user encoded/updated the record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setModifiedBy(String fsValue) {
        return setValue("sModified", fsValue);
    }

    /**
     * @return The user encoded/updated the record
     */
    public String getModifiedBy() {
        return (String) getValue("sModified");
    }

    /**
     * Sets the date and time the record was modified.
     *
     * @param fdValue
     * @return result as success/failed
     */
    public JSONObject setModifiedDate(Date fdValue) {
        return setValue("dModified", fdValue);
    }

    /**
     * @return The date and time the record was modified.
     */
    public Date getModifiedDate() {
        return (Date) getValue("dModified");
    }

    /**
     * Gets the SQL statement for this entity.
     *
     * @return SQL Statement
     */
    public String makeSQL() {
        return MiscUtil.makeSQL(this, "xClientNm»xAddressx»xCPerson1»xCPPosit1»xCategrNm»xTermName»xTaxIDNox»xMobileNo");
    }

    /**
     * Gets the SQL Select statement for this entity.
     *
     * @return SelectSQL Statement
     */
    public String makeSelectSQL() {
        return MiscUtil.makeSelect(this,"xClientNm»xAddressx»xCPerson1»xCPPosit1»xCategrNm»xTermName»xTaxIDNox»xMobileNo");
    }
    private String getSQL(){
        return "SELECT" +
                        "  a.sClientID" +
                        ", a.sAddrssID" +
                        ", a.sContctID" +
                        ", a.sCategrCd" +
                        ", a.dCltSince" +
                        ", a.dBegDatex" +
                        ", a.nBegBalxx" +
                        ", a.sTermIDxx" +
                        ", a.nDiscount" +
                        ", a.nCredLimt" +
                        ", a.nABalance" +
                        ", a.nOBalance" +
                        ", a.nLedgerNo" +
                        ", a.cVatablex" +
                        ", a.cRecdStat" +
                        ", a.sModified" +
                        ", a.dModified" +
                        ", b.sCompnyNm xClientNm" +
                        ", TRIM(CONCAT(c.sHouseNox, ' ', c.sAddressx, ', ', g.sBrgyName, ' ', h.sTownName, ', ', i.sProvName)) xAddressx" +
                        ", d.sCPerson1 xCPerson1" +
                        ", d.sCPPosit1 xCPPosit1" +
                        ", e.sDescript xCategrNm" +
                        ", f.sDescript xTermName" +
                        ", b.sTaxIDNox xTaxIDNox" +
                        ", d.sMobileNo xMobileNo" +
                    " FROM AP_Client_Master a" +
                        " LEFT JOIN Client_Master b ON a.sClientID = b.sClientID" +
                        " LEFT JOIN Client_Address c" + 
                            " LEFT JOIN Barangay  g ON c.sBrgyIDxx = g.sBrgyIDxx" +
                            " LEFT JOIN TownCity h ON c.sTownIDxx = h.sTownIDxx" +
                            " LEFT JOIN Province i ON h.sProvIDxx = i.sProvIDxx" +
                        " ON a.sAddrssID = c.sAddrssID" +
                        " LEFT JOIN Client_Institution_Contact_Person d ON a.sContctID = d.sContctID" +
                        " LEFT JOIN Category e ON a.sCategrCd = e.sCategrCd" +
                        " LEFT JOIN Term f ON a.sTermIDxx = f.sTermCode";
    }

    private void initialize() {
        try {
            poEntity = MiscUtil.xml2ResultSet(System.getProperty("sys.default.path.metadata") + XML, getTable());

            poEntity.last();
            poEntity.moveToInsertRow();

            MiscUtil.initRowSet(poEntity);
            poEntity.updateDouble("nDiscount", 0.0);
            poEntity.updateDouble("nCredLimt", 0.0);
            poEntity.updateDouble("nABalance", 0.0);
            poEntity.updateDouble("nOBalance", 0.0);
            poEntity.updateDouble("nBegBalxx", 0.0);
            poEntity.updateInt("nLedgerNo", 0);
            poEntity.updateString("cVatablex", RecordStatus.INACTIVE);
            poEntity.updateString("cRecdStat", RecordStatus.INACTIVE);

            poEntity.insertRow();
            poEntity.moveToCurrentRow();

            poEntity.absolute(1);

            pnEditMode = EditMode.UNKNOWN;
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
