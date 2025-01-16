package io.github.rainblooding._3D;

import io.github.rainblooding._3D.base.*;
import io.github.rainblooding._3D.object.ObjLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.io.IOException;

public class ObjViewer extends JPanel implements Move {

    /**
     * 视点位置
     */
    private _3DPoint viewpoint = new _3DPoint(-100, -100, -180);

    /**
     * 投影平面位置
     */
    private float SCREEN_Z = -60;

    _3DModel model;

    public ObjViewer() {
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()){
                    case KeyEvent.VK_UP:
                        move(0, -5 ,0);
                        break;
                    case KeyEvent.VK_DOWN:
                        move(0, 5 ,0);
                        break;
                    case KeyEvent.VK_RIGHT:
                        move(5, 0 ,0);
                        break;
                    case KeyEvent.VK_LEFT:
                        move(-5, 0 ,0);
                        break;
                    case KeyEvent.VK_0:
                        move(0, 0 ,5);
                        break;
                    case KeyEvent.VK_9:
                        move(0, 0 ,-5);
                        break;
                }
            }
        });

        try {
            model = ObjLoader.loadObj("E:\\item\\java\\Project_Exercises\\src\\main\\resources\\3D\\LibertStatue.obj");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Timer timer = new Timer(50, e -> {
            repaint();
        });
        timer.start();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // 获取屏幕的尺寸
        int width = this.getWidth();
        int height = this.getHeight();

        // 将背景填充为黑色
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, width, height);

        // 设置画笔为黑色
        g2d.setColor(Color.WHITE);

        for (_3DLine edge : model.getEdges()) {
            _2DPoint start2D = _3DTo2D(edge.getStart(), model.get3DLocation());
            _2DPoint end2D = _3DTo2D(edge.getEnd(), model.get3DLocation());

            if (start2D != null && end2D != null) {
                g2d.draw(this.getLine2D(start2D, end2D));
            }
        }
    }


    /**
     * 将3D点转换为2D点
     *
     * @param point
     * @param offset
     * @return
     */
    public _2DPoint _3DTo2D(_3DPoint point, _3DPoint offset) {
        float vx, vy, vz;
        vx = viewpoint.getX();
        vy = viewpoint.getY();
        vz = viewpoint.getZ();

        float x, y, z;
        x = point.getX() * 100 + offset.getX();
        y = point.getY() * 100 + offset.getY();
        z = point.getZ() * 100 + offset.getZ();

        int _2dx = (int) (vx + (((SCREEN_Z - vz) * (x - vx)) / (z - vz)));
        int _2dy = (int) (vy + (((SCREEN_Z - vz) * (y - vy)) / (z - vz)));
        return new _2DPoint(_2dx, _2dy);
    }

    /**
     * 获取线段
     *
     * @param pointStart
     * @param pointEnd
     * @return
     */
    public Line2D getLine2D(_2DPoint pointStart, _2DPoint pointEnd) {
        return new Line2D.Float(pointStart.getX(), pointStart.getY(), pointEnd.getX(), pointEnd.getY());
    }


    public void move(int x, int y, int z) {
        _3DPoint location = this.get3DLocation();
        _3DPoint newLocation = new _3DPoint(location.getX() + x, location.getY() + y, location.getZ() + z);
        this.set3DLocation(newLocation);
        System.out.printf("(%f,%f,%f)\n", newLocation.getX(), newLocation.getY(), newLocation.getZ());
    }


    @Override
    public void set3DLocation(_3DPoint location) {
        this.viewpoint = location;
    }

    @Override
    public _3DPoint get3DLocation() {
        return this.viewpoint;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("3D Rotating Model");
        ObjViewer viewer = new ObjViewer();

        frame.add(viewer);
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


        viewer.set3DLocation(new _3DPoint(viewer.getWidth() / 2, viewer.getHeight() / 2, -180));
        viewer.requestFocus();

    }

}
