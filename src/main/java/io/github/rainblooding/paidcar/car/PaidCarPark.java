package io.github.rainblooding.paidcar.car;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 收费停车场
 */
public class PaidCarPark implements CarPark{

    private int parkingSpace;
    private int freeParkingSpace;
    private Map<String, Record> recordMap;
    private LinkedList<Record> history;
    private BigDecimal price;


    public PaidCarPark(int parkingSpace, BigDecimal price) {
        this.parkingSpace = parkingSpace;
        this.freeParkingSpace = parkingSpace;
        this.price = price;
        recordMap = new HashMap<>();
        history = new LinkedList<>();
    }


    @Override
    public int getParkingSpace() {
        return parkingSpace;
    }

    @Override
    public int getFreeParkingSpace() {
        return freeParkingSpace;
    }

    @Override
    public boolean driveIn(Car car) {
        if (freeParkingSpace > 0) {
            recordMap.put(car.getLicensePlateNumber(), new Record(car, price));
            return true;
        }
        System.out.println("车位不足！");
        return false;
    }

    @Override
    public boolean driveOut(Car car) {
        String licensePlateNumber = car.getLicensePlateNumber();
        if (recordMap.containsKey(licensePlateNumber)) {
            Record record = recordMap.get(licensePlateNumber);
            record.setDriverOutTime(LocalDateTime.now());
            record.setTotalPrice(record.getPrice().multiply(BigDecimal.valueOf(record.getDriverOutTime().getHour() - record.getDriverInTime().getHour())));
            history.add(record);
            recordMap.remove(licensePlateNumber);
            return true;
        }
        return false;
    }

    /**
     * 查看历史记录
     *
     * @param car
     * @return
     */
    public List<Record> getHistory(Car car){
        String licensePlateNumber = car.getLicensePlateNumber();
        List<Record> result = new ArrayList<>();
        for (Record record : history) {
            if (record.getCar().getLicensePlateNumber().equals(licensePlateNumber)) {
                result.add(record);
            }
        }
        if (recordMap.containsKey(licensePlateNumber)) {
            result.add(recordMap.get(licensePlateNumber));
        }
        return result;
    }

    /**
     * 获取订单
     *
     * @param car
     * @return
     */
    public Record getRecord(Car car){
        String licensePlateNumber = car.getLicensePlateNumber();
        if (recordMap.containsKey(licensePlateNumber)) {
            return recordMap.get(licensePlateNumber);
        }
        Iterator<Record> iterator = history.descendingIterator();
        while(iterator.hasNext()) {
            Record record = iterator.next();
            if (record.getCar().getLicensePlateNumber().equals(licensePlateNumber)) {
                return record;
            }
        }
        System.out.println("未找到订单！");
        return null;
    }
}
