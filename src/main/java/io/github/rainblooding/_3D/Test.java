package io.github.rainblooding._3D;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class Test extends JPanel {
    private float angleX = 0;
    private float angleY = 0;
    private float angleZ = 0;

    private final float[][] vertices = {
            {1, 1, 1}, {-1, 1, 1}, {-1, -1, 1}, {1, -1, 1}, // Base vertices
            {0, 0, 2} // Peak point
    };

    private final int[][] edges = {
            {0, 1}, {1, 2}, {2, 3}, {3, 0}, // Base edges
            {0, 4}, {1, 4}, {2, 4}, {3, 4}  // Side edges
    };

    public Test() {
        Timer timer = new Timer(50, e -> {
//            angleX += 2;
            angleY += 3;
//            angleZ += 1;
//            angleX %= 360;
            angleY %= 360;
//            angleZ %= 360;
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();
        float fov = 10.0f;

        float[][] rotatedVertices = rotateModel(vertices, angleX, angleY, angleZ);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, width, height);

        g2d.setColor(Color.WHITE);

        for (int[] edge : edges) {
            float[] start = rotatedVertices[edge[0]];
            float[] end = rotatedVertices[edge[1]];

            int[] start2D = projectPoint(start[0], start[1], start[2], fov, width, height);
            int[] end2D = projectPoint(end[0], end[1], end[2], fov, width, height);

            if (start2D != null && end2D != null) {
                g2d.draw(new Line2D.Float(start2D[0], start2D[1], end2D[0], end2D[1]));
            }
        }
    }

    private int[] projectPoint(float x, float y, float z, float fov, int screenWidth, int screenHeight) {
        float cameraDistance = 5.0f;
        z += cameraDistance;
        if (z <= 0) {
            return null; // 点位于摄像机后方
        }//透视投影
        float scale = (float) (Math.tan(Math.toRadians(fov / 2)) / z);
        int screenX = (int) (screenWidth / 2 + x / scale);
        int screenY = (int) (screenHeight / 2 - y / scale);
        return new int[]{screenX, screenY};
    }

    private float[][] rotateModel(float[][] model, float angleX, float angleY, float angleZ) {
        float[][] rotatedModel = new float[model.length][3];
        float cosX = (float) Math.cos(Math.toRadians(angleX));
        float sinX = (float) Math.sin(Math.toRadians(angleX));
        float cosY = (float) Math.cos(Math.toRadians(angleY));
        float sinY = (float) Math.sin(Math.toRadians(angleY));
        float cosZ = (float) Math.cos(Math.toRadians(angleZ));
        float sinZ = (float) Math.sin(Math.toRadians(angleZ));

        for (int i = 0; i < model.length; i++) {
            float x = model[i][0];
            float y = model[i][1];
            float z = model[i][2];

            // 绕 X 轴旋转
            float newY = cosX * y - sinX * z;
            float newZ = sinX * y + cosX * z;
            y = newY;
            z = newZ;

            // 绕 Y 轴旋转
            float newX = cosY * x + sinY * z;
            newZ = -sinY * x + cosY * z;
            x = newX;
            z = newZ;

            // 绕 Z 轴旋转
            newX = cosZ * x - sinZ * y;
            newY = sinZ * x + cosZ * y;
            x = newX;
            y = newY;

            rotatedModel[i][0] = x;
            rotatedModel[i][1] = y;
            rotatedModel[i][2] = z;
        }

        return rotatedModel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("3D Rotating Model");
        Test panel = new Test();

        frame.add(panel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}