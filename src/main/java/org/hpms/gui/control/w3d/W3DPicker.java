package org.hpms.gui.control.w3d;

import com.threed.jpct.FrameBuffer;
import com.threed.jpct.Interact2D;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.World;

public class W3DPicker {

    private final SimpleVector ray;
    private FrameBuffer frameBuffer;
    private SimpleVector norm;


    public W3DPicker(FrameBuffer frameBuffer) {
        this.frameBuffer = frameBuffer;
        ray = new SimpleVector();
        norm = new SimpleVector();
    }

    public void updateFrameBuffer(FrameBuffer frameBuffer) {
        this.frameBuffer = frameBuffer;
    }

    public int[] getPickingResult(World world, int mouseX, int mouseY) {
        Interact2D.reproject2D3D(world.getCamera(), frameBuffer, mouseX, mouseY, ray);
        norm = ray.normalize(norm);
        return Interact2D.pickPolygon(world.getVisibilityList(), norm);
    }

}
