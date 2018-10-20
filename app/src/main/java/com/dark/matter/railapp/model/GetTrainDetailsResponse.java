package com.dark.matter.railapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetTrainDetailsResponse {


    public class Class {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("available")
        @Expose
        private String available;
        @SerializedName("code")
        @Expose
        private String code;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAvailable() {
            return available;
        }

        public void setAvailable(String available) {
            this.available = available;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

    }

    public class CurrentStation {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("lat")
        @Expose
        private Double lat;
        @SerializedName("code")
        @Expose
        private String code;
        @SerializedName("lng")
        @Expose
        private Double lng;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Double getLng() {
            return lng;
        }

        public void setLng(Double lng) {
            this.lng = lng;
        }

    }

    public class Day {

        @SerializedName("code")
        @Expose
        private String code;
        @SerializedName("runs")
        @Expose
        private String runs;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getRuns() {
            return runs;
        }

        public void setRuns(String runs) {
            this.runs = runs;
        }

    }

    @SerializedName("current_station")
    @Expose
    private CurrentStation currentStation;
    @SerializedName("route")
    @Expose
    private List<Route> route = null;
    @SerializedName("response_code")
    @Expose
    private Integer responseCode;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("train")
    @Expose
    private Train train;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("debit")
    @Expose
    private Integer debit;

    public CurrentStation getCurrentStation() {
        return currentStation;
    }

    public void setCurrentStation(CurrentStation currentStation) {
        this.currentStation = currentStation;
    }

    public List<Route> getRoute() {
        return route;
    }

    public void setRoute(List<Route> route) {
        this.route = route;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getDebit() {
        return debit;
    }

    public void setDebit(Integer debit) {
        this.debit = debit;
    }


    public class Route {

        @SerializedName("day")
        @Expose
        private Integer day;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("actarr")
        @Expose
        private String actarr;
        @SerializedName("actdep")
        @Expose
        private String actdep;
        @SerializedName("has_departed")
        @Expose
        private Boolean hasDeparted;
        @SerializedName("scharr")
        @Expose
        private String scharr;
        @SerializedName("scharr_date")
        @Expose
        private String scharrDate;
        @SerializedName("latemin")
        @Expose
        private Integer latemin;
        @SerializedName("distance")
        @Expose
        private Integer distance;
        @SerializedName("station")
        @Expose
        private Station station;
        @SerializedName("actarr_date")
        @Expose
        private String actarrDate;
        @SerializedName("schdep")
        @Expose
        private String schdep;
        @SerializedName("has_arrived")
        @Expose
        private Boolean hasArrived;

        public Integer getDay() {
            return day;
        }

        public void setDay(Integer day) {
            this.day = day;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getActarr() {
            return actarr;
        }

        public void setActarr(String actarr) {
            this.actarr = actarr;
        }

        public String getActdep() {
            return actdep;
        }

        public void setActdep(String actdep) {
            this.actdep = actdep;
        }

        public Boolean getHasDeparted() {
            return hasDeparted;
        }

        public void setHasDeparted(Boolean hasDeparted) {
            this.hasDeparted = hasDeparted;
        }

        public String getScharr() {
            return scharr;
        }

        public void setScharr(String scharr) {
            this.scharr = scharr;
        }

        public String getScharrDate() {
            return scharrDate;
        }

        public void setScharrDate(String scharrDate) {
            this.scharrDate = scharrDate;
        }

        public Integer getLatemin() {
            return latemin;
        }

        public void setLatemin(Integer latemin) {
            this.latemin = latemin;
        }

        public Integer getDistance() {
            return distance;
        }

        public void setDistance(Integer distance) {
            this.distance = distance;
        }

        public Station getStation() {
            return station;
        }

        public void setStation(Station station) {
            this.station = station;
        }

        public String getActarrDate() {
            return actarrDate;
        }

        public void setActarrDate(String actarrDate) {
            this.actarrDate = actarrDate;
        }

        public String getSchdep() {
            return schdep;
        }

        public void setSchdep(String schdep) {
            this.schdep = schdep;
        }

        public Boolean getHasArrived() {
            return hasArrived;
        }

        public void setHasArrived(Boolean hasArrived) {
            this.hasArrived = hasArrived;
        }

    }

    public class Station {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("lat")
        @Expose
        private Double lat;
        @SerializedName("code")
        @Expose
        private String code;
        @SerializedName("lng")
        @Expose
        private Double lng;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Double getLng() {
            return lng;
        }

        public void setLng(Double lng) {
            this.lng = lng;
        }

    }

    public class Train {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("classes")
        @Expose
        private List<Class> classes = null;
        @SerializedName("days")
        @Expose
        private List<Day> days = null;
        @SerializedName("number")
        @Expose
        private String number;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Class> getClasses() {
            return classes;
        }

        public void setClasses(List<Class> classes) {
            this.classes = classes;
        }

        public List<Day> getDays() {
            return days;
        }

        public void setDays(List<Day> days) {
            this.days = days;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

    }
}
