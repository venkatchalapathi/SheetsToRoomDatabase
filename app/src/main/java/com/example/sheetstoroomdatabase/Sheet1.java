
package com.example.sheetstoroomdatabase;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class Sheet1 {

    @SerializedName("SNO")
    @Expose
    private String sNO;
    @SerializedName("STUDENT_NAME")
    @Expose
    private String sTUDENTNAME;
    @SerializedName("DATE_OF_BIRTH")
    @Expose
    private String dATEOFBIRTH;
    @SerializedName("ADDRESS")
    @Expose
    private String aDDRESS;
    @SerializedName("STUDENT_NO")
    @Expose
    private String sTUDENTNO;
    @SerializedName("EMAIL_ID")
    @Expose
    private String eMAILID;

    @PrimaryKey
    @NonNull
    @SerializedName("AADHAR_NO")
    @Expose
    private String aADHARNO;
    @SerializedName("IMAGE")
    @Expose
    private String iMAGE;

    public String getSNO() {
        return sNO;
    }

    public void setSNO(String sNO) {
        this.sNO = sNO;
    }

    public String getSTUDENTNAME() {
        return sTUDENTNAME;
    }

    public void setSTUDENTNAME(String sTUDENTNAME) {
        this.sTUDENTNAME = sTUDENTNAME;
    }

    public String getDATEOFBIRTH() {
        return dATEOFBIRTH;
    }

    public void setDATEOFBIRTH(String dATEOFBIRTH) {
        this.dATEOFBIRTH = dATEOFBIRTH;
    }

    public String getADDRESS() {
        return aDDRESS;
    }

    public void setADDRESS(String aDDRESS) {
        this.aDDRESS = aDDRESS;
    }

    public String getSTUDENTNO() {
        return sTUDENTNO;
    }

    public void setSTUDENTNO(String sTUDENTNO) {
        this.sTUDENTNO = sTUDENTNO;
    }

    public String getEMAILID() {
        return eMAILID;
    }

    public void setEMAILID(String eMAILID) {
        this.eMAILID = eMAILID;
    }

    public String getAADHARNO() {
        return aADHARNO;
    }

    public void setAADHARNO(String aADHARNO) {
        this.aADHARNO = aADHARNO;
    }

    public String getIMAGE() {
        return iMAGE;
    }

    public void setIMAGE(String iMAGE) {
        this.iMAGE = iMAGE;
    }

}
