package io.github.rainblooding.paidcar.car;

/**
 * 私家车（其实想表达一般使用的小轿车和suv）
 */
public class PrivateCar implements Car {


    /**
     * 车牌号
     */
    private String licensePlateNumber;

    public PrivateCar(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    @Override
    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }
}
