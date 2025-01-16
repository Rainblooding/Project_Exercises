package io.github.rainblooding._3D.base;

/**
 * 移动的接口
 */
public interface Move extends _3DLocation {

    /**
     * 移动
     *
     * @param x
     * @param y
     * @param z
     */
    default void move(int x, int y, int z) {
        _3DPoint location = this.get3DLocation();
        _3DPoint newLocation = new _3DPoint(location.getX() + x, location.getY() + y, location.getZ() + z);
        this.set3DLocation(newLocation);
    }
}
