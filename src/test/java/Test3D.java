import com.threed.jpct.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Test3D implements MouseMotionListener, MouseWheelListener, MouseListener {

    private final SimpleVector line;
    private float thingScale = 1f;
    private final World world;
    private final FrameBuffer buffer;
    private final Object3D thing;
    private final JFrame frame;
    private int lastMousePosX;
    private int lastMousePosY;
    private float distance = 50;
    private float dx;
    private float dy;


    public static void main(String[] args) {
        try {
            new Test3D().loop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Test3D() throws Exception {
        Logger.setLogLevel(Logger.ERROR);
        frame = new JFrame("Object Picking");
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.addMouseMotionListener(this);
        frame.addMouseWheelListener(this);
        frame.addMouseListener(this);
        world = new World();
        world.setAmbientLight(150, 150, 150);

        thing = loadModel("D:\\floor.obj", thingScale);
        thing.build();
        world.addObject(thing);
        world.getCamera().moveCamera(Camera.CAMERA_MOVEUP, distance);
        world.getCamera().rotateCameraX((float) Math.PI / 2f);
        line = new SimpleVector(dx, 0, dy);
        buffer = new FrameBuffer(800, 600, FrameBuffer.SAMPLINGMODE_NORMAL);

    }

    private void loop() throws Exception {

        while (frame.isShowing()) {

            PointerInfo pi = MouseInfo.getPointerInfo();
            Point p = pi.getLocation();
            int mouseX = frame.getMousePosition() == null ? 0 : frame.getMousePosition().x;
            int mouseY = frame.getMousePosition() == null ? 0 : frame.getMousePosition().y;

            if (lastMousePosX == mouseX && lastMousePosY == mouseY) {
                dx = 0;
                dy = 0;
            }

            line.set(dx, 0, dy);
            Matrix m = line.normalize().getRotationMatrix();
            m.rotateAxis(m.getXAxis(), (float) -Math.PI / 2f);
            world.getCamera().moveCamera(Camera.CAMERA_MOVEIN, distance);
            world.getCamera().rotateAxis(m.invert3x3().getXAxis(), distance * distance * line.length() / (500 * 500));
            world.getCamera().moveCamera(Camera.CAMERA_MOVEOUT, distance);

            lastMousePosX = mouseX;
            lastMousePosY = mouseY;

            buffer.clear(Color.BLACK);
            world.renderScene(buffer);
            world.drawWireframe(buffer, Color.WHITE);
            buffer.update();
            buffer.display(frame.getGraphics());
            Thread.sleep(10);
        }
        buffer.disableRenderer(IRenderer.RENDERER_OPENGL);
        buffer.dispose();
        frame.dispose();
        System.exit(0);
    }


    private Object3D loadModel(String filename, float scale) {
        Object3D[] model = Loader.loadOBJ(filename, null, scale);
        Object3D o3d = new Object3D(0);
        Object3D temp;
        for (Object3D object3D : model) {
            temp = object3D;
            temp.setCenter(SimpleVector.ORIGIN);
            temp.rotateX((float) (-0.5 * Math.PI));
            temp.rotateMesh();
            temp.setRotationMatrix(new Matrix());
            o3d = Object3D.mergeObjects(o3d, temp);
            o3d.build();
        }
        return o3d;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        dx = -(lastMousePosX - e.getX());
        dy = -(e.getY() - lastMousePosY);
    }


    public void mouseMoved(MouseEvent e) {


    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        boolean down = e.getWheelRotation() > 0;
        if (down) {
            thing.setScale(thing.getScale() - thing.getScale() / 2);
        } else {
            thing.setScale(thing.getScale() * 2);
        }
        System.out.println(thing.getScale());
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
