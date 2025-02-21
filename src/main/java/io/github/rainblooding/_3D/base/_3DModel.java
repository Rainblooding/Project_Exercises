package io.github.rainblooding._3D.base;

import java.util.List;

/**
 * 3D模型
 *
 * @author rainblooding
 */
public class _3DModel implements Move{

    /**
     * 位置
     */
    private _3DPoint location;

    /**
     * 顶点集合
     */
    private List<_3DPoint> vertices;

    /**
     * 连线集合
     */
    private List<_3DLine> edges;

    public _3DModel(List<_3DPoint> vertices, List<_3DLine> edges) {
        this.location = new _3DPoint(0, 0, 0);
        this.vertices = vertices;
        this.edges = edges;
    }

    public List<_3DPoint> getVertices() {
        return vertices;
    }

    public List<_3DLine> getEdges() {
        return edges;
    }

    @Override
    public _3DPoint get3DLocation() {
        return location;
    }

    @Override
    public void set3DLocation(_3DPoint location) {
        this.location = location;
    }
}
