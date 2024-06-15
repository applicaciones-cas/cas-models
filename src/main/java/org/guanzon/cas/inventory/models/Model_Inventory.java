/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.guanzon.cas.inventory.models;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import javax.sql.rowset.CachedRowSet;
import org.guanzon.appdriver.base.GRider;
import org.guanzon.appdriver.base.MiscUtil;
import org.guanzon.appdriver.base.SQLUtil;
import org.guanzon.appdriver.constant.EditMode;
import org.guanzon.appdriver.constant.RecordStatus;
import org.guanzon.appdriver.iface.GEntity;
import org.json.simple.JSONObject;

/**
 *
 * @author User
 */
public class Model_Inventory implements GEntity {

    final String XML = "Model_Inventory.xml";

    GRider poGRider;                //application driver
    CachedRowSet poEntity;          //rowset
    JSONObject poJSON;              //json container
    int pnEditMode;                 //edit mode

    /**
     * Entity constructor
     *
     * @param foValue - GhostRider Application Driver
     */
    public Model_Inventory(GRider foValue) {
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
        return "Inventory";
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
        try {
            poJSON = MiscUtil.validateColumnValue(System.getProperty("sys.default.path.metadata") + XML, MiscUtil.getColumnLabel(poEntity, fnColumn), foValue);
            if ("error".equals((String) poJSON.get("result"))) {
                return poJSON;
            }

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
        setStockID(MiscUtil.getNextCode(getTable(), "sStockIDx", true, poGRider.getConnection(), poGRider.getBranchCode()));
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
    @Override
    public JSONObject openRecord(String fsCondition) {
        poJSON = new JSONObject();

        String lsSQL = makeSelectSQL();

        //replace the condition based on the primary key column of the record
        lsSQL = MiscUtil.addCondition(lsSQL, " sStockIDx = " + SQLUtil.toSQL(fsCondition));

        ResultSet loRS = poGRider.executeQuery(lsSQL);

        try {
            if (loRS.next()) {
                for (int lnCtr = 1; lnCtr <= loRS.getMetaData().getColumnCount(); lnCtr++) {
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
                setStockID(MiscUtil.getNextCode(getTable(), "sStockIDx", false, poGRider.getConnection(), ""));

                lsSQL = makeSQL();

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
                Model_Inventory loOldEntity = new Model_Inventory(poGRider);

                //replace with the primary key column info
                JSONObject loJSON = loOldEntity.openRecord(this.getStockID());

                if ("success".equals((String) loJSON.get("result"))) {
                    //replace the condition based on the primary key column of the record
                    lsSQL = MiscUtil.makeSQL(this, loOldEntity, "sStockIDx = " + SQLUtil.toSQL(this.getStockID()),  "xCategNm1»xCategNm2»xCategNm3»xCategNm4»xBrandNme»xModelNme»xModelDsc»xColorNme»xMeasurNm»xInvTypNm»xSuperCde»xSuperDsc");

                    if (!lsSQL.isEmpty()) {
                        if (poGRider.executeQuery(lsSQL, getTable(), poGRider.getBranchCode(), "") > 0) {
                            poJSON.put("result", "success");
                            poJSON.put("message", "Record saved successfully.");
                        } else {
                            poJSON.put("result", "error");
                            poJSON.put("message", poGRider.getErrMsg());
                        }   
                    } else {
                        poJSON.put("result", "success");
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
     * Sets the sStockIDx of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setStockID(String fsValue) {
        return setValue("sStockIDx", fsValue);
    }

    /**
     * @return The sStockIDx of this record.
     */
    public String getStockID() {
        return (String) getValue("sStockIDx");
    }

    /**
     * Sets the sBarCodex of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setBarcode(String fsValue) {
        return setValue("sBarCodex", fsValue);
    }

    /**
     * @return The sBarCodex of this record.
     */
    public String getBarcode() {
        return (String) getValue("sBarCodex");
    }

    /**
     * Sets the sBriefDsc Code of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setBriefDescription(String fsValue) {
        return setValue("sBriefDsc", fsValue);
    }

    /**
     * @return The sBriefDsc Code of this record.
     */
    public String getBriefDescription() {
        return (String) getValue("sBriefDsc");
    }

    /**
     * Sets the sAltBarCd Code of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setAltBarcode(String fsValue) {
        return setValue("sAltBarCd", fsValue);
    }

    /**
     * @return The sAltBarCd Code of this record.
     */
    public String getAltBarcode() {
        return (String) getValue("sAltBarCd");
    }

    /**
     * Sets the sCategCd1 of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setCategCd1(String fsValue) {
        return setValue("sCategCd1", fsValue);
    }

    /**
     * @return The sCategCd1 of this record.
     */
    public String getCategCd1() {
        return (String) getValue("sCategCd1");
    }
    
    /**
     * Sets the sCategCd2 of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setCategCd2(String fsValue) {
        return setValue("sCategCd2", fsValue);
    }

    /**
     * @return The sCategCd2 of this record.
     */
    public String getCategCd2() {
        return (String) getValue("sCategCd2");
    }
    
    /**
     * Sets the sCategCd3 of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setCategCd23(String fsValue) {
        return setValue("sCategCd3", fsValue);
    }

    /**
     * @return The sCategCd3 of this record.
     */
    public String getCategCd3() {
        return (String) getValue("sCategCd3");
    }
    
    /**
     * Sets the sCategCd4 of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setCategCd4(String fsValue) {
        return setValue("sCategCd4", fsValue);
    }

    /**
     * @return The sCategCd4 of this record.
     */
    public String getCategCd4() {
        return (String) getValue("sCategCd4");
    }

    
    /**
     * @return The sBrandCde of this record.
     */
    public String getBrandCode() {
        return (String) getValue("sBrandCde");
    }
    
    /**
     * Sets the xBrandNme of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setBrandCode(String fsValue) {
        return setValue("sBrandCde", fsValue);
    }
    
    /**
     * @return The xBrandNme of this record.
     */
    public String getBranchName() {
        return (String) getValue("xBrandNme");
    }
    
    /**
     * Sets the sBrandCde of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject getBranchName(String fsValue) {
        return setValue("xBrandNme", fsValue);
    }

    /**
     * @return The sModelCde of this record.
     */
    public String getModelCode() {
        return (String) getValue("sModelCde");
    }
    
    /**
     * Sets the sModelCde of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setModelCode(String fsValue) {
        return setValue("sModelCde", fsValue);
    }


    /**
     * @return The sModelCde of this record.
     */
    public String getModelName() {
        return (String) getValue("xModelNme");
    }
    
    /**
     * Sets the sModelCde of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setModelName(String fsValue) {
        return setValue("xModelNme", fsValue);
    }
    
    
    /**
     * @return The sColorCde of this record.
     */
    public String getColorCode() {
        return (String) getValue("sColorCde");
    }
    
    /**
     * Sets the sColorCde of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setColorCode(String fsValue) {
        return setValue("sColorCde", fsValue);
    }


    /**
     * @return The sModelCde of this record.
     */
    public String getColorName() {
        return (String) getValue("xColorNme");
    }
    
    /**
     * Sets the sModelCde of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setColorName(String fsValue) {
        return setValue("xColorNme", fsValue);
    }
    
    
    
    /**
     * @return The sMeasurID of this record.
     */
    public String getMeasureID() {
        return (String) getValue("sMeasurID");
    }
    
    /**
     * Sets the sMeasurID of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setMeasureID(String fsValue) {
        return setValue("sMeasurID", fsValue);
    }


    /**
     * @return The xMeasurNm of this record.
     */
    public String getMeasureName() {
        return (String) getValue("xMeasurNm");
    }
    
    /**
     * Sets the xMeasurNm of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setMeasureName(String fsValue) {
        return setValue("xMeasurNm", fsValue);
    }
    
    
    /**
     * Sets the nUnitPrce .
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setUnitPrice(String fsValue){
        return setValue("nUnitPrce", fsValue);
    }
    
    /**
     * @return The nUnitPrce. 
     */
    public Double getUnitPrice(){
        return (Double) getValue("nUnitPrce");
    }
    
    
    /**
     * Sets the nSelPrice .
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setSelPrice(String fsValue){
        return setValue("nSelPrice", fsValue);
    }
    
    /**
     * @return The nSelPrice. 
     */
    public Double getSelPrice(){
        return (Double) getValue("nSelPrice");
    }
    
    
    /**
     * Sets the nDiscLev1 .
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setDiscountLvl1(String fsValue){
        return setValue("nDiscLev1", fsValue);
    }
    
    /**
     * @return The nDiscLev1. 
     */
    public Double getDiscountLevel1(){
        return (Double) getValue("nDiscLev1");
    }
    
    
    /**
     * Sets the nDiscLev2 .
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setDiscountLvl2(String fsValue){
        return setValue("nDiscLev2", fsValue);
    }
    
    /**
     * @return The nDiscLev2. 
     */
    public Double getDiscountLevel2(){
        return (Double) getValue("nDiscLev2");
    }
    
    
    /**
     * Sets the nDiscLev3 .
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setDiscountLevel3(String fsValue){
        return setValue("nDiscLev3", fsValue);
    }
    
    /**
     * @return The nDiscLev3. 
     */
    public Double getDiscountLevel3(){
        return (Double) getValue("nDiscLev3");
    }
    
    /**
     * Sets the nMinLevel .
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setMinLevel(String fsValue){
        return setValue("nMinLevel", fsValue);
    }
    
    /**
     * @return The nMinLevel. 
     */
    public Double getMinLevel(){
        return (Double) getValue("nMinLevel");
    }
    
    /**
     * Sets the nMaxLevel .
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setMaxLevel(String fsValue){
        return setValue("nMaxLevel", fsValue);
    }
    
    /**
     * @return The nMaxLevel. 
     */
    public Double getMaxLevel(){
        return (Double) getValue("nMaxLevel");
    }
    
   
    /**
    /**
     * Sets the cComboInv .
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setComboInv(String fsValue){
        return setValue("cComboInv", fsValue);
    }
    
    /**
     * @return The cComboInv. 
     */
    public String getComboInv(){
        return (String) getValue("cComboInv");
    }
   
    /**
    /**
     * Sets the cSerialze .
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setSerialze(String fsValue){
        return setValue("cSerialze", fsValue);
    }
    
    /**
     * @return The cSerialze. 
     */
    public String getSerialze(){
        return (String) getValue("cSerialze");
    }
    
    /**
    /**
     * Sets the cWthPromo .
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setWthPromo(String fsValue){
        return setValue("cWthPromo", fsValue);
    }
    
    /**
     * @return The cWthPromo. 
     */
    public String getWthPromo(){
        return (String) getValue("cWthPromo");
    }
    
    
    /**
    /**
     * Sets the cUnitType .
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setUnitType(String fsValue){
        return setValue("cUnitType", fsValue);
    }
    
    /**
     * @return The cUnitType. 
     */
    public String getUnitType(){
        return (String) getValue("cUnitType");
    }
    
    /**
    /**
     * Sets the cInvStatx .
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setInvStatx(String fsValue){
        return setValue("cInvStatx", fsValue);
    }
    
    /**
     * @return The cInvStatx. 
     */
    public String getInvStatx(){
        return (String) getValue("cInvStatx");
    }
    
    /**
    /**
     * Sets the nShlfLife .
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setShlfLife(String fsValue){
        return setValue("nShlfLife", fsValue);
    }
    
    /**
     * @return The nShlfLife. 
     */
    public Double getShlfLife(){
        return (Double) getValue("nShlfLife");
    }
    
    /**
    /**
     * Sets the sSupersed .
     * 
     * @param fsValue 
     * @return  True if the record assignment is successful.
     */
    public JSONObject setSupersed(String fsValue){
        return setValue("sSupersed", fsValue);
    }
    
    /**
     * @return The sSupersed. 
     */
    public String getSupersed(){
        return (String) getValue("sSupersed");
    }
    

    /**
     * Sets the Inventory RecdStat of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setRecdStat(String fsValue) {
        return setValue("cRecdStat", fsValue);
    }

    /**
     * @return The Category RecdStat of this record.
     */
    public String getRecdStat() {
        return (String) getValue("cRecdStat");
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
     * Sets the xInvTypNm.
     *
     * @param fdValue
     * @return result as success/failed
     */
    public JSONObject setInvTypNm(Date fdValue) {
        return setValue("xInvTypNm", fdValue);
    }

    /**
     * @return The xInvTypNm.
     */
    public String getInvTypNm() {
        return (String) getValue("xInvTypNm");
    }
    
    /**
     * Sets the xSuperCde.
     *
     * @param fdValue
     * @return result as success/failed
     */
    public JSONObject setSuperCde(Date fdValue) {
        return setValue("xSuperCde", fdValue);
    }

    /**
     * @return The xInvTypNm.
     */
    public String getSuperCde() {
        return (String) getValue("xSuperCde");
    }

    
    /**
     * Sets the xSuperDsc.
     *
     * @param fdValue
     * @return result as success/failed
     */
    public JSONObject setSuperDsc(Date fdValue) {
        return setValue("xSuperDsc", fdValue);
    }

    /**
     * @return The xSuperDsc.
     */
    public String getSuperDsc() {
        return (String) getValue("xSuperDsc");
    }

    /**
     * Gets the SQL statement for this entity.
     *
     * @return SQL Statement
     */
    public String makeSQL() {
        return MiscUtil.makeSQL(this, "xCategNm1»xCategNm2»xCategNm3»xCategNm4»xBrandNme»xModelNme»xModelDsc»xColorNme»xMeasurNm»xInvTypNm»xSuperCde»xSuperDsc");
    }

    /**
     * Gets the SQL Select statement for this entity.
     *
     * @return SelectSQL Statement
     */
    public String makeSelectSQL() {
        return MiscUtil.makeSelect(this, "xCategNm1»xCategNm2»xCategNm3»xCategNm4»xBrandNme»xModelNme»xModelDsc»xColorNme»xMeasurNm»xInvTypNm»xSuperCde»xSuperDsc");
    }

    private void initialize() {
        try {
            poEntity = MiscUtil.xml2ResultSet(System.getProperty("sys.default.path.metadata") + XML, getTable());

            poEntity.last();
            poEntity.moveToInsertRow();

            MiscUtil.initRowSet(poEntity);
            poEntity.updateString("cRecdStat", RecordStatus.ACTIVE);

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
