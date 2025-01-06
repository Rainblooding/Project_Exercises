package io.github.rainblooding.paidcar.car;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        PaidCarPark paidCarPark = new PaidCarPark(10, new BigDecimal("5"));
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("i)停车");
            System.out.println("o)驶出");
            System.out.println("e)退出");
            System.out.print("请选择操作：");
            String choice = scanner.next();

            switch (choice) {
                case "i":
                    System.out.print("请输入车牌：");
                    boolean flag = paidCarPark.driveIn(new PrivateCar(scanner.next()));
                    if (flag) {
                        System.out.println("停车成功！");
                    } else {
                        System.out.println("停车失败！");
                    }
                    break;
                case "o":
                    System.out.print("请输入车牌：");
                    Car car = new PrivateCar(scanner.next());
                    flag = paidCarPark.driveOut(car);
                    if (flag) {
                        System.out.println("驶出成功！");
                        Record record = paidCarPark.getRecord(car);
                        System.out.println("停车费用：" + record.getTotalPrice());
                        System.out.println("停车状态：" + record.getState());
                        System.out.println("停车时间：" + record.getDriverInTime() + " - " + record.getDriverOutTime());
                    } else {
                        System.out.println("驶出失败！");
                    }
                    break;
                case "e":
                    System.exit(0);
                    break;
                default:
                    System.out.println("无效的选择！");
                    break;
            }
        }
    }
}
