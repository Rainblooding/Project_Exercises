package io.github.rainblooding._3D.object;

import io.github.rainblooding._3D.base._3DLine;
import io.github.rainblooding._3D.base._3DModel;
import io.github.rainblooding._3D.base._3DPoint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * obj文件加载器
 *
 * @author rainblooding
 */
public class ObjLoader {

    public static void loadObj(String filename, List<float[]> vertices, List<int[]> edges) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] tokens = line.trim().split("\\s+");
            if (tokens.length > 0) {
                if (tokens[0].equals("v")) {
                    // 解析顶点
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    float z = Float.parseFloat(tokens[3]);
                    vertices.add(new float[]{x, y, z});
                } else if (tokens[0].equals("f")) {
                    // 解析面并创建边
                    int[] faceVertices = new int[tokens.length - 1];
                    for (int i = 1; i < tokens.length; i++) {
                        String[] vertexData = tokens[i].split("/");
                        faceVertices[i - 1] = Integer.parseInt(vertexData[0]) - 1; // 1-based to 0-based index
                    }

                    // 为每一对连续的顶点生成边
                    for (int i = 0; i < faceVertices.length; i++) {
                        int start = faceVertices[i];
                        int end = faceVertices[(i + 1) % faceVertices.length]; // 最后一点与第一点连接
                        edges.add(new int[]{start, end});
                    }
                }
            }
        }
        reader.close();
    }

    /**
     * 根据文件加载模型
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public static _3DModel loadObj(String filename) throws IOException {
        // 获取顶点 和 连线
        List<float[]> vertices = new ArrayList<>();
        List<int[]> edges = new ArrayList<>();
        loadObj(filename, vertices, edges);

        // 封装起来
        List<_3DPoint> _3DVertices = new ArrayList<>();
        List<_3DLine> _3DEdges = new ArrayList<>();
        for (float[] vertex : vertices) {
            _3DVertices.add(new _3DPoint(vertex[0], vertex[1], vertex[2]));
        }
        for (int[] edge : edges) {
            _3DEdges.add(new _3DLine(_3DVertices.get(edge[0]), _3DVertices.get(edge[1])));
        }
        // 创建模型
        return new _3DModel(_3DVertices, _3DEdges);
    }
}
