package org.elsys.netprog.rest;
 
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
 public class CarSub {
    public String carReg;
    public Boolean active;
    public String due;
    public String lastAction;
    private String zone;
    private Date dueDate;
    private Date lastActionDate;
   
    public CarSub(String carReg, String zone){
        this.carReg = carReg;
        this.active = true;
        this.zone = zone;
        this.lastActionDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss z d/M/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lastActionDate);
        calendar.add(Calendar.HOUR, 1);
        this.dueDate = calendar.getTime();
       
        this.due = sdf.format(this.dueDate);
        this.lastAction = sdf.format(this.lastActionDate);
    }
   
 
 
    public String getZone() {
        return zone;
    }
 
 
 
    public void setZone(String zone) {
        this.zone = zone;
    }
 
 
 
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((carReg == null) ? 0 : carReg.hashCode());
        result = prime * result + ((zone == null) ? 0 : zone.hashCode());
        return result;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CarSub other = (CarSub) obj;
        if (carReg == null) {
            if (other.carReg != null)
                return false;
        } else if (!carReg.equals(other.carReg))
            return false;
        if (zone == null) {
            if (other.zone != null)
                return false;
        } else if (!zone.equals(other.zone))
            return false;
        return true;
    }
 
 
}
