package org.hpms.gui.control.w3d;

import com.threed.jpct.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class W3DArea implements MouseMotionListener, MouseWheelListener, MouseListener {
    private final SimpleVector line;
    private final World selectedWorld;
    private final World nonSelectedWorld;
    private FrameBuffer buffer;
    private final JComponent parent;
    private int lastMousePosX;
    private int lastMousePosY;
    private float distance;
    private float dx;
    private float dy;
    private float offX;
    private float offY;
    private boolean repaint;
    private final W3DManager helper;
    private int currentButton;

    public W3DArea(JComponent parent) {
        Logger.setLogLevel(Logger.ERROR);
        this.parent = parent;
        this.parent.setVisible(true);
        this.parent.addMouseMotionListener(this);
        this.parent.addMouseWheelListener(this);
        this.parent.addMouseListener(this);
        this.parent.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                repaint = true;

            }
        });
        selectedWorld = new World();
        selectedWorld.setAmbientLight(150, 150, 150);
        distance = 50;

        selectedWorld.getCamera().moveCamera(Camera.CAMERA_MOVEUP, distance);
        selectedWorld.getCamera().rotateCameraX((float) Math.PI / 2f);

        nonSelectedWorld = new World();
        nonSelectedWorld.setAmbientLight(150, 150, 150);


        nonSelectedWorld.getCamera().moveCamera(Camera.CAMERA_MOVEUP, distance);
        nonSelectedWorld.getCamera().rotateCameraX((float) Math.PI / 2f);

        line = new SimpleVector(dx, 0, dy);
        buffer = new FrameBuffer(parent.getWidth(), parent.getHeight(), FrameBuffer.SAMPLINGMODE_NORMAL);
        helper = W3DManager.getInstance();
        helper.create(selectedWorld, nonSelectedWorld, buffer);
    }

    public void loop() throws Exception {

        while (parent.isShowing()) {

            int mouseX;
            int mouseY;


            PointerInfo pi = MouseInfo.getPointerInfo();
            Point pl = parent.getLocationOnScreen();
            mouseX = pi.getLocation().x - pl.x;
            mouseY = pi.getLocation().y - pl.y;

            if (lastMousePosX == mouseX && lastMousePosY == mouseY) {
                dx = 0;
                dy = 0;
                offX = 0;
                offY = 0;
            }

            line.set(dx, 0, dy);
            Matrix m = line.normalize().getRotationMatrix();
            m.rotateAxis(m.getXAxis(), (float) -Math.PI / 2f);

            fixCamera(selectedWorld, m);
            fixCamera(nonSelectedWorld, m);

            lastMousePosX = mouseX;
            lastMousePosY = mouseY;

            helper.update(mouseX, mouseY);

            if (repaint) {
                buffer = new FrameBuffer(parent.getWidth(), parent.getHeight(), FrameBuffer.SAMPLINGMODE_NORMAL);
                repaint = false;
            }
            buffer.clear(Color.BLACK);
            selectedWorld.renderScene(buffer);
            selectedWorld.draw(buffer);

            nonSelectedWorld.renderScene(buffer);
            nonSelectedWorld.drawWireframe(buffer, Color.GREEN);

            buffer.update();
            buffer.display(parent.getGraphics());

            Thread.sleep(10);
        }
        buffer.disableRenderer(IRenderer.RENDERER_OPENGL);
        buffer.dispose();
    }

    private void fixCamera(World world, Matrix m) {
        world.getCamera().moveCamera(Camera.CAMERA_MOVEIN, distance);
        world.getCamera().rotateAxis(m.invert3x3().getXAxis(), distance * distance * line.length() / (distance * distance * 100));
        world.getCamera().moveCamera(Camera.CAMERA_MOVEOUT, distance);
        world.getCamera().moveCamera(Camera.CAMERA_MOVELEFT, offX / (500 / (distance)));
        world.getCamera().moveCamera(Camera.CAMERA_MOVEDOWN, offY / (500 / (distance)));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (helper != null) {
            helper.mouseClicked(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        currentButton = e.getButton();
        if (helper != null) {
            helper.mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        currentButton = MouseEvent.NOBUTTON;
        if (helper != null) {
            helper.mouseReleased(e);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (currentButton == MouseEvent.BUTTON2) {
            dx = -(lastMousePosX - e.getX());
            dy = -(e.getY() - lastMousePosY);
        } else if (currentButton == MouseEvent.BUTTON3) {
            offX = -(lastMousePosX - e.getX());
            offY = -(e.getY() - lastMousePosY);
        }
    }


    @Override
    public void mouseMoved(MouseEvent e) {

    }


    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int step = e.getWheelRotation() > 0 ? 3 : -3;
        if (helper != null && helper.initialized()) {
            if (step < 0 && distance <= 8) {
                distance = 8;
                return;
            }
            Matrix m = line.normalize().getRotationMatrix();
            m.rotateAxis(m.getXAxis(), (float) -Math.PI / 2f);
            selectedWorld.getCamera().moveCamera(Camera.CAMERA_MOVEIN, distance);
            selectedWorld.getCamera().rotateAxis(m.invert3x3().getXAxis(), distance * distance * line.length() / (distance * distance * 100));
            selectedWorld.getCamera().moveCamera(Camera.CAMERA_MOVEOUT, distance + step);

            nonSelectedWorld.getCamera().moveCamera(Camera.CAMERA_MOVEIN, distance);
            nonSelectedWorld.getCamera().rotateAxis(m.invert3x3().getXAxis(), distance * distance * line.length() / (distance * distance * 100));
            nonSelectedWorld.getCamera().moveCamera(Camera.CAMERA_MOVEOUT, distance + step);

            distance += step;

        }

    }

}
