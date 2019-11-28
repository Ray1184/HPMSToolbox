package org.hpms.gui.control.w3d;

import com.threed.jpct.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class W3DArea implements MouseMotionListener, MouseWheelListener, MouseListener {
    private final SimpleVector line;

    private FrameBuffer buffer;
    private final JComponent parent;

    private boolean repaint;
    private final W3DManager helper;
    private int currentButton;
    public float dx;
    public float dy;
    public int lastMousePosX;
    public int lastMousePosY;
    private boolean loopIntegrityCheck;



    public W3DArea(JComponent parent) {
        Logger.setLogLevel(Logger.ERROR);

        loopIntegrityCheck = true;
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
        World selectedWorld = new World();
        selectedWorld.setAmbientLight(150, 150, 150);

        World nonSelectedWorld = new World();
        nonSelectedWorld.setAmbientLight(150, 150, 150);

        buffer = new FrameBuffer(parent.getWidth(), parent.getHeight(), FrameBuffer.SAMPLINGMODE_NORMAL);

        helper = W3DManager.getInstance();
        helper.create(selectedWorld, nonSelectedWorld, buffer);


        line = new SimpleVector(dx, 0, dy);


    }

    public void loop() throws Exception {

        while (parent.isShowing()) {

            loopIntegrityCheck = false;
            W3DManager.TransformationHolder t = helper.getTransformation();
            if (helper.checkChangeCam()) {
                dx = 0;
                dy = 0;
                t.offX = 0;
                t.offY = 0;
            }
            loopIntegrityCheck = true;

            int mouseX;
            int mouseY;


            PointerInfo pi = MouseInfo.getPointerInfo();
            Point pl = parent.getLocationOnScreen();
            mouseX = pi.getLocation().x - pl.x;
            mouseY = pi.getLocation().y - pl.y;

            if (lastMousePosX == mouseX && lastMousePosY == mouseY) {

                dx = 0;
                dy = 0;
                t.offX = 0;
                t.offY = 0;
            }

            line.set(dx, 0, dy);
            Matrix m = line.normalize().getRotationMatrix();
            m.rotateAxis(m.getXAxis(), (float) -Math.PI / 2f);

            fixCamera(helper.getTransformation().selectedWorld, m);
            fixCamera(helper.getTransformation().nonSelectedWorld, m);

            lastMousePosX = mouseX;
            lastMousePosY = mouseY;

            helper.update(mouseX, mouseY);

            if (repaint) {
                buffer = new FrameBuffer(parent.getWidth(), parent.getHeight(), FrameBuffer.SAMPLINGMODE_NORMAL);
                helper.updateBuffer(buffer);
                repaint = false;
            }
            buffer.clear(Color.BLACK);
            helper.getTransformation().selectedWorld.renderScene(buffer);
            helper.getTransformation().selectedWorld.draw(buffer);

            helper.getTransformation().nonSelectedWorld.renderScene(buffer);
            helper.getTransformation().nonSelectedWorld.drawWireframe(buffer, helper.readOnlySectors() ? Color.GRAY : Color.GREEN);

            buffer.update();

            buffer.display(parent.getGraphics());


            Thread.sleep(10);
        }
        buffer.disableRenderer(IRenderer.RENDERER_OPENGL);
        buffer.dispose();
    }

    private void fixCamera(World world, Matrix m) {
        W3DManager.TransformationHolder t = helper.getTransformation();
        world.getCamera().moveCamera(Camera.CAMERA_MOVEIN, t.distance);
        world.getCamera().rotateAxis(m.invert3x3().getXAxis(), t.distance * t.distance * line.length() / (t.distance * t.distance * 100));
        world.getCamera().moveCamera(Camera.CAMERA_MOVEOUT, t.distance);
        world.getCamera().moveCamera(Camera.CAMERA_MOVELEFT, t.offX / (500 / (t.distance)));
        world.getCamera().moveCamera(Camera.CAMERA_MOVEDOWN, t.offY / (500 / (t.distance)));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!loopIntegrityCheck) {
            return;
        }
        if (helper != null) {
            helper.mouseClicked(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!loopIntegrityCheck) {
            return;
        }
        currentButton = e.getButton();
        if (helper != null) {
            helper.mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!loopIntegrityCheck) {
            return;
        }
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
        if (!loopIntegrityCheck) {
            return;
        }
        if (helper != null && helper.initialized()) {
            W3DManager.TransformationHolder t = helper.getTransformation();
            if (currentButton == MouseEvent.BUTTON2) {
                dx = -(lastMousePosX - e.getX());
                dy = -(e.getY() - lastMousePosY);
            } else if (currentButton == MouseEvent.BUTTON3) {
                t.offX = -(lastMousePosX - e.getX());
                t.offY = -(e.getY() - lastMousePosY);
            }
        }
    }


    @Override
    public void mouseMoved(MouseEvent e) {

    }


    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (!loopIntegrityCheck) {
            return;
        }
        int step = e.getWheelRotation() > 0 ? 3 : -3;
        if (helper != null && helper.initialized()) {
            W3DManager.TransformationHolder t = helper.getTransformation();
            if (step < 0 && t.distance <= 8) {
                t.distance = 8;
                return;
            }
            Matrix m = line.normalize().getRotationMatrix();
            m.rotateAxis(m.getXAxis(), (float) -Math.PI / 2f);
            helper.getTransformation().selectedWorld.getCamera().moveCamera(Camera.CAMERA_MOVEIN, t.distance);
            helper.getTransformation().selectedWorld.getCamera().rotateAxis(m.invert3x3().getXAxis(), t.distance * t.distance * line.length() / (t.distance * t.distance * 100));
            helper.getTransformation().selectedWorld.getCamera().moveCamera(Camera.CAMERA_MOVEOUT, t.distance + step);

            helper.getTransformation().nonSelectedWorld.getCamera().moveCamera(Camera.CAMERA_MOVEIN, t.distance);
            helper.getTransformation().nonSelectedWorld.getCamera().rotateAxis(m.invert3x3().getXAxis(), t.distance * t.distance * line.length() / (t.distance * t.distance * 100));
            helper.getTransformation().nonSelectedWorld.getCamera().moveCamera(Camera.CAMERA_MOVEOUT, t.distance + step);

            t.distance += step;

        }

    }

}
