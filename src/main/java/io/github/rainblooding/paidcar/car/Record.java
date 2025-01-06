package io.github.rainblooding.paidcar.car;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 停车记录
 */
public class Record {

    public Record(Car car, BigDecimal price) {
        this.car = car;
        this.driverInTime = LocalDateTime.now();
        this.price = price;
        this.isPay = false;
        this.state = "停车中";
    }

    private Car car;

    /**
     * 停车时间
     */
    private LocalDateTime driverInTime;

    /**
     * 驶出时间
     */
    private LocalDateTime driverOutTime;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 总金额
     */
    private BigDecimal totalPrice;

    /**
     * 是否支付
     */
    private boolean isPay;

    /**
     * 状态
     */
    private String state;


    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public LocalDateTime getDriverInTime() {
        return driverInTime;
    }

    public void setDriverInTime(LocalDateTime driverInTime) {
        this.driverInTime = driverInTime;
    }

    public LocalDateTime getDriverOutTime() {
        return driverOutTime;
    }

    public void setDriverOutTime(LocalDateTime driverOutTime) {
        this.driverOutTime = driverOutTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isPay() {
        return isPay;
    }

    public void setPay(boolean pay) {
        isPay = pay;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
