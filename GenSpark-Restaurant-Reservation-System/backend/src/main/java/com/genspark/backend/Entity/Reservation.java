package com.genspark.backend.Entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.*;
import java.util.stream.Stream;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id", nullable = false)
    private long resId;

    @Column(name="date", nullable = false)
    private String dateTime;

    @Column(nullable = false)
    private int numberOfGuests;

    private StatusType type;

    public Reservation(String dateTime, int numberOfGuests, StatusType type, String resName, String resNumber) {
        this.dateTime = dateTime;
        this.numberOfGuests = numberOfGuests;
        this.type = type;
        this.resName = resName;
        this.resNumber = resNumber;
    }

    @Column(name = "primaryName", nullable = false)
    private String resName;

    @Column(name = "phoneNumber", nullable = false)
    private String resNumber;

    public Reservation() {
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getResNumber() {
        return resNumber;
    }

    public void setResNumber(String resNumber) {
        this.resNumber = resNumber;
    }

    public long getResId() {
        return resId;
    }

    public void setResId(long resId) {
        this.resId = resId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public StatusType getType() {
        return type;
    }

    public void setType(StatusType type) {
        this.type = type;
    }

    public enum StatusType {
        TYPE1("PENDING"), TYPE2("CONFIRMED"),
        TYPE3("ARRIVED"), TYPE4("CANCELLED"), TYPE5("COMPLETED");

        private final String code;

        StatusType(String code){
            this.code = code;
        }

        @JsonCreator
        public static StatusType decode(final String code) {
            return Stream.of(StatusType.values()).filter(targetEnum -> targetEnum.code.equals(code)).findFirst().orElse(null);
        }

        @JsonValue
        public String getCode() {
            return code;
        }
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + resId +
                ", dateTime='" + dateTime + '\'' +
                ", numberOfGuests=" + numberOfGuests +
                ", type=" + type +
                '}';
    }
}
