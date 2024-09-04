package com.example.m3_g3_assignment.model;

public class Orders {
    private int ordersId;
    private int userId;
    private int ordersQuantity;
    private String ordersStatus;

    public Orders() {
    }

    public Orders(int ordersId, int userId, int ordersQuantity, String ordersStatus) {
        this.ordersId = ordersId;
        this.userId = userId;
        this.ordersQuantity = ordersQuantity;
        this.ordersStatus = ordersStatus;
    }

    public int getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(int ordersId) {
        this.ordersId = ordersId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrdersQuantity() {
        return ordersQuantity;
    }

    public void setOrdersQuantity(int ordersQuantity) {
        this.ordersQuantity = ordersQuantity;
    }

    public String getOrdersStatus() {
        return ordersStatus;
    }

    public void setOrdersStatus(String ordersStatus) {
        this.ordersStatus = ordersStatus;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "ordersId=" + ordersId +
                ", userId=" + userId +
                ", ordersQuantity=" + ordersQuantity +
                ", ordersStatus='" + ordersStatus + '\'' +
                '}';
    }
}

