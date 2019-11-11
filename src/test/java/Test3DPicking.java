import com.threed.jpct.*;
import com.threed.jpct.util.Light;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class Test3DPicking extends JFrame implements MouseMotionListener, MouseListener {

    private static final long serialVersionUID = 1L;
    private Graphics g = null;
    private FrameBuffer fb = null;
    private World world = null;
    private Object3D plane = null;
    private Object3D ramp = null;
    private Object3D player = null;
    private Object3D cube2 = null;
    private Object3D sphere = null;

    private int mouseX = 320;
    private int mouseY = 240;


    public Test3DPicking() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setSize(640, 480);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        addMouseMotionListener(this);
        addMouseListener(this);
        g = getGraphics();
    }


    @Override
    public void mouseMoved(MouseEvent m) {
        mouseX = m.getX();
        mouseY = m.getY();
    }


    @Override
    public void mouseDragged(MouseEvent m) {
        //
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        //
    }


    @Override
    public void mouseEntered(MouseEvent e) {
        //
    }


    @Override
    public void mouseExited(MouseEvent e) {
        //
    }


    @Override
    public void mousePressed(MouseEvent e) {
        //
    }


    @Override
    public void mouseReleased(MouseEvent e) {
        //
    }


    private void initStuff() {
        fb = new FrameBuffer(640, 480, FrameBuffer.SAMPLINGMODE_NORMAL);
        world = new World();
        fb.enableRenderer(IRenderer.RENDERER_SOFTWARE);


        ramp = Primitives.getCube(20);
        ramp.setAdditionalColor(Color.RED);
        plane = Primitives.getPlane(20, 10);
        plane.setAdditionalColor(Color.GREEN);
        sphere = Primitives.getSphere(30);
        sphere.setAdditionalColor(Color.CYAN);
        sphere.translate(-50, 10, 50);
        cube2 = Primitives.getCube(20);
        cube2.setAdditionalColor(Color.ORANGE);
        cube2.translate(60, -20, 60);

        plane.rotateX((float) Math.PI / 2f);
        ramp.rotateX((float) Math.PI / 2f);

        player = Primitives.getCone(3);
        player.rotateX((float) Math.PI / 2f);
        player.rotateMesh();
        player.clearRotation();

        plane.setCollisionMode(Object3D.COLLISION_CHECK_OTHERS);
        ramp.setCollisionMode(Object3D.COLLISION_CHECK_OTHERS);
        sphere.setCollisionMode(Object3D.COLLISION_CHECK_OTHERS);
        cube2.setCollisionMode(Object3D.COLLISION_CHECK_OTHERS);

        cube2.setBillboarding(true);

        world.addObject(plane);
        world.addObject(ramp);
        world.addObject(sphere);
        world.addObject(cube2);
        world.addObject(player);

        player.translate(-50, -10, -50);

        Light light = new Light(world);
        light.setPosition(new SimpleVector(0, -80, 0));
        light.setIntensity(40, 25, 22);

        world.setAmbientLight(10, 10, 10);
        world.buildAllObjects();
    }


    private void relocate() {
        SimpleVector pos = getWorldPosition();
        if (pos != null) {
            player.clearTranslation();
            player.translate(pos);
        }
    }


    private SimpleVector getWorldPosition() {
        SimpleVector pos = null;
        SimpleVector ray = Interact2D.reproject2D3DWS(world.getCamera(), fb, mouseX, mouseY);
        if (ray != null) {
            SimpleVector norm = ray.normalize(); // Just to be sure...

            float f = world.calcMinDistance(world.getCamera().getPosition(), norm, 1000);
            if (f != Object3D.COLLISION_NONE) {
                SimpleVector offset = new SimpleVector(norm);
                norm.scalarMul(f);
                norm = norm.calcSub(offset);
                pos = new SimpleVector(norm);
                pos.add(world.getCamera().getPosition());
            }
        }
        return pos;
    }


    private void doIt()
            throws Exception {
        Camera cam = world.getCamera();
        cam.moveCamera(Camera.CAMERA_MOVEOUT, 100);
        cam.moveCamera(Camera.CAMERA_MOVEUP, 160);
        cam.lookAt(plane.getTransformedCenter());

        while (true) {
            relocate();
            fb.clear();
            world.renderScene(fb);
            world.draw(fb);
            fb.update();
            fb.display(g);
            Thread.sleep(10);
        }
    }


    public static void main(String[] args)
            throws Exception {
        Test3DPicking cd = new Test3DPicking();
        cd.initStuff();
        cd.doIt();
    }
}