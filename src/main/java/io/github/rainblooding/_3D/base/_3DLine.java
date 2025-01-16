package io.github.rainblooding._3D.base;

/**
 * 三维世界的点
 */
public class _3DLine {

    private _3DPoint start;
    private _3DPoint end;

    public _3DLine(_3DPoint start, _3DPoint end) {
        this.start = start;
        this.end = end;
    }



    public _3DPoint getStart() {
        return start;
    }

    public _3DPoint getEnd() {
        return end;
    }
}
