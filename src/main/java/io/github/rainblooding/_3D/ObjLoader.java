package io.github.rainblooding._3D;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

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
}
