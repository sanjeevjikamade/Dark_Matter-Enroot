package com.dark.matter.railapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Train {

@SerializedName("TrainNo")
@Expose
private String trainNo;
@SerializedName("TrainName")
@Expose
private String trainName;
@SerializedName("Source")
@Expose
private String source;
@SerializedName("ArrivalTime")
@Expose
private String arrivalTime;
@SerializedName("Destination")
@Expose
private String destination;
@SerializedName("DepartureTime")
@Expose
private String departureTime;

public String getTrainNo() {
return trainNo;
}

public void setTrainNo(String trainNo) {
this.trainNo = trainNo;
}

public String getTrainName() {
return trainName;
}

public void setTrainName(String trainName) {
this.trainName = trainName;
}

public String getSource() {
return source;
}

public void setSource(String source) {
this.source = source;
}

public String getArrivalTime() {
return arrivalTime;
}

public void setArrivalTime(String arrivalTime) {
this.arrivalTime = arrivalTime;
}

public String getDestination() {
return destination;
}

public void setDestination(String destination) {
this.destination = destination;
}

public String getDepartureTime() {
return departureTime;
}

public void setDepartureTime(String departureTime) {
this.departureTime = departureTime;
}

}