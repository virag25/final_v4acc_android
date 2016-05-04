package amigoinn.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;




public class MyPojotaskList
{

    @SerializedName("vtasksid")
    @Expose
    private Integer vtasksid;
    @SerializedName("vtaskname")
    @Expose
    private String vtaskname;
    @SerializedName("vtaskdetail")
    @Expose
    private String vtaskdetail;
    @SerializedName("datetime")
    @Expose
    private Datetime datetime;
    @SerializedName("vtaskstatus")
    @Expose
    private String vtaskstatus;

    /**
     *
     * @return
     * The vtasksid
     */
    public Integer getVtasksid() {
        return vtasksid;
    }

    /**
     *
     * @param vtasksid
     * The vtasksid
     */
    public void setVtasksid(Integer vtasksid) {
        this.vtasksid = vtasksid;
    }

    /**
     *
     * @return
     * The vtaskname
     */
    public String getVtaskname() {
        return vtaskname;
    }

    /**
     *
     * @param vtaskname
     * The vtaskname
     */
    public void setVtaskname(String vtaskname) {
        this.vtaskname = vtaskname;
    }

    /**
     *
     * @return
     * The vtaskdetail
     */
    public String getVtaskdetail() {
        return vtaskdetail;
    }

    /**
     *
     * @param vtaskdetail
     * The vtaskdetail
     */
    public void setVtaskdetail(String vtaskdetail) {
        this.vtaskdetail = vtaskdetail;
    }

    /**
     *
     * @return
     * The datetime
     */
    public Datetime getDatetime() {
        return datetime;
    }

    /**
     *
     * @param datetime
     * The datetime
     */
    public void setDatetime(Datetime datetime) {
        this.datetime = datetime;
    }

    /**
     *
     * @return
     * The vtaskstatus
     */
    public String getVtaskstatus() {
        return vtaskstatus;
    }

    /**
     *
     * @param vtaskstatus
     * The vtaskstatus
     */
    public void setVtaskstatus(String vtaskstatus) {
        this.vtaskstatus = vtaskstatus;
    }

}