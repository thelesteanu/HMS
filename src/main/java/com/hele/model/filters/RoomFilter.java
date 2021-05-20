package com.hele.model.filters;

/**
 * Created by thelesteanu on 09.05.2021.
 */
public class RoomFilter {
    private Boolean smoking;
    private Boolean petFriendly;
    private Boolean reserved;

    public Boolean getSmoking() {
        return smoking;
    }

    public void setSmoking(Boolean smoking) {
        this.smoking = smoking;
    }

    public Boolean getPetFriendly() {
        return petFriendly;
    }

    public void setPetFriendly(Boolean petFriendly) {
        this.petFriendly = petFriendly;
    }

    public Boolean getReserved() {
        return reserved;
    }

    public void setReserved(Boolean reserved) {
        this.reserved = reserved;
    }
}
