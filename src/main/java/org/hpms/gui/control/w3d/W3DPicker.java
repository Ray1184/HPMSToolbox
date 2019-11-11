package org.hpms.gui.control.w3d;

import com.threed.jpct.FrameBuffer;
import com.threed.jpct.Interact2D;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.World;

public class W3DPicker {

    private FrameBuffer frameBuffer;


    private SimpleVector ray;

    private SimpleVector norm;


    public W3DPicker(FrameBuffer frameBuffer) {
        this.frameBuffer = frameBuffer;
        ray = new SimpleVector();
        norm = new SimpleVector();
    }

    public int[] getPickingResult(World world, int mouseX, int mouseY) {
        Interact2D.reproject2D3D(world.getCamera(), frameBuffer, mouseX, mouseY, ray);
        if (ray != null) {
            norm = ray.normalize(norm);
            return Interact2D.pickPolygon(world.getVisibilityList(), norm);
        }
        return null;
    }

}
