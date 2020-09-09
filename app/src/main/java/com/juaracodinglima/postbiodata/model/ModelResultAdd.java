
package com.juaracodinglima.postbiodata.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelResultAdd implements Serializable, Parcelable
{

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Creator<ModelResultAdd> CREATOR = new Creator<ModelResultAdd>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ModelResultAdd createFromParcel(Parcel in) {
            return new ModelResultAdd(in);
        }

        public ModelResultAdd[] newArray(int size) {
            return (new ModelResultAdd[size]);
        }

    }
    ;
    private final static long serialVersionUID = -7814012595275155966L;

    protected ModelResultAdd(Parcel in) {
        this.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public ModelResultAdd() {
    }

    /**
     * 
     * @param message
     * @param status
     */
    public ModelResultAdd(Boolean status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(message);
    }

    public int describeContents() {
        return  0;
    }

}
