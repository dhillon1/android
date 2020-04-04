package com.sd.project;

public class HistoryDoc {

    private String ip,city,country,loc,region,timestamp,timezone;

    public HistoryDoc(){}

    public HistoryDoc(String ip, String city, String country, String loc,  String region, String timestamp, String timezone){
        this.city = city;
        this.ip = ip;
        this.country = country;
        this.loc = loc;
        this.region = region;
        this.timestamp = timestamp;
        this.timezone   = timezone;
    }

    public String getIp() {
        return ip;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getLoc() {
        return loc;
    }

    public String getRegion() {
        return region;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}
