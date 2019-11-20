package org.hpms.gui.utils;

import com.threed.jpct.Loader;
import com.threed.jpct.Matrix;
import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class GraphicsUtils {


    public static final String LINE_ID = "#GuideLine#";

    private static class CircularArray<T> {
        private final List<T> list;
        private int counter;

        public CircularArray() {
            list = new ArrayList<>();
            counter = 0;
        }

        public void insert(T object) {
            list.add(object);
        }

        public T next() {
            if (counter >= list.size()) {
                counter = 0;
            }
            return list.get(counter++);
        }
    }

    private static final CircularArray<Color> colorWheel = new CircularArray<>();

    static {
        colorWheel.insert(Color.RED);
        colorWheel.insert(Color.CYAN);
        colorWheel.insert(Color.GREEN);
        colorWheel.insert(Color.ORANGE);
        colorWheel.insert(Color.WHITE);
        colorWheel.insert(Color.BLUE);
        colorWheel.insert(Color.GRAY);
        colorWheel.insert(Color.MAGENTA);
        colorWheel.insert(Color.YELLOW);
        colorWheel.insert(Color.PINK);
    }

    public static Object3D loadModel(String filename, float scale) {
        return loadModel(filename, scale, null);
    }

    public static Object3D loadModel(String filename, float scale, Function<Object3D, Object3D> callback) {
        Object3D[] model = Loader.loadOBJ(filename, null, scale);
        Object3D o3d = new Object3D(0);
        Object3D temp;
        for (Object3D object3D : model) {
            temp = object3D;
            temp.setCenter(SimpleVector.ORIGIN);
            temp.rotateX((float) (-0.5 * Math.PI));
            temp.rotateMesh();
            temp.setRotationMatrix(new Matrix());
            if (callback != null) {
                temp = callback.apply(temp);
            }
            o3d = Object3D.mergeObjects(o3d, temp);
            o3d.build();
        }
        return o3d;
    }

    public static List<Object3D> loadModelExploded(String filename, float scale) {
        return loadModelExploded(filename, scale, null);
    }

    public static List<Object3D> loadModelExploded(String filename, float scale, Function<Object3D, Object3D> callback) {
        Object3D[] model = Loader.loadOBJ(filename, null, scale);
        List<Object3D> newModels = new ArrayList<>();
        for (Object3D object3D : model) {
            if (callback != null) {
                object3D = callback.apply(object3D);
            }
            object3D.setCenter(SimpleVector.ORIGIN);
            object3D.rotateX((float) (-0.5 * Math.PI));
            object3D.rotateMesh();
            object3D.setRotationMatrix(new Matrix());

            newModels.add(object3D);
        }
        return newModels;
    }

    public static Map<Integer, Object3D> loadModelExplodedAsMap(String filename, float scale) {
        return loadModelExplodedAsMap(filename, scale, null);
    }

    public static Map<Integer, Object3D> loadModelExplodedAsMap(String filename, float scale, Function<Object3D, Object3D> callback) {
        Object3D[] model = Loader.loadOBJ(filename, null, scale);
        Map<Integer, Object3D> newModels = new HashMap<>();
        for (Object3D object3D : model) {
            if (callback != null) {
                object3D = callback.apply(object3D);
            }
            object3D.setCenter(SimpleVector.ORIGIN);
            object3D.rotateX((float) (-0.5 * Math.PI));
            object3D.rotateMesh();
            object3D.setRotationMatrix(new Matrix());

            newModels.put(object3D.getID(), object3D);
        }
        return newModels;
    }


    public static Color nextColor() {
        return colorWheel.next();
    }

    public static Object3D createLine() {
        Object3D line = new Object3D(1);

        SimpleVector p1 = new SimpleVector(0, 0, 0);
        SimpleVector p2 = new SimpleVector(0, 1, 10);
        SimpleVector p3 = new SimpleVector(0, 0, 10);

        line.addTriangle(p1, p2, p3);

        line.addTriangle(p2, p1, p3);
        line.setUserObject(LINE_ID);
        line.build();
        return line;
    }
}
