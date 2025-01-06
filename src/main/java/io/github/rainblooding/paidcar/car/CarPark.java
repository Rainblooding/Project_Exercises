package io.github.rainblooding.paidcar.car;

/**
 * 停车场
 */
public interface CarPark {

    /**
     * 获取车位
     *
     * @return
     */
    int getParkingSpace();

    /**
     * 获取空闲车位
     *
     * @return
     */
    int getFreeParkingSpace();


    /**
     * 驶入
     *
     * @param car
     * @return
     */
    boolean driveIn(Car car);

    /**
     * 驶出
     *
     * @param car
     * @return
     */
    boolean driveOut(Car car);
}
