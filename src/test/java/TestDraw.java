import com.bulenkov.darcula.DarculaLaf;
import org.hpms.gui.data.ProjectModel;
import org.hpms.gui.logic.ProjectManager;
import org.hpms.gui.views.BaseGui;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.hpms.gui.control.WorkAreaController.NORMALIZE_RATIO;
import static org.hpms.gui.data.ProjectModel.DEFAULT_SG_NAME;
import static org.hpms.gui.utils.ErrorManager.createReadOnlyJTextField;

public class TestDraw {

    private static Graphics g;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new DarculaLaf());
        } catch (UnsupportedLookAndFeelException e) {
            JOptionPane.showMessageDialog(null, createReadOnlyJTextField(e), "Error", JOptionPane.PLAIN_MESSAGE);

        }
        testDraw();
    }

    private static void testDraw() {
        ProjectManager.getInstance().buildEmptyProject();
        ProjectModel prj = ProjectManager.getInstance().getProjectModel();
        ProjectModel.RoomModel roomModel = new ProjectModel.RoomModel();
        roomModel.setName("TEST_ROOM");
        ProjectModel.RoomModel.SectorGroup sg = new ProjectModel.RoomModel.SectorGroup();
        sg.setId(DEFAULT_SG_NAME);
        ProjectModel.RoomModel.SectorGroup.Sector s = new ProjectModel.RoomModel.SectorGroup.Sector();
        s.setX1(10);
        s.setX2(7);
        s.setX3(13);
        s.setZ1(5);
        s.setZ2(10);
        s.setZ3(10);
        sg.setSectors(Collections.singletonList(s));
        Map<String, ProjectModel.RoomModel.SectorGroup> sgMap = new HashMap<>();
        sgMap.put(DEFAULT_SG_NAME, sg);
        roomModel.setSectorGroupById(sgMap);
        prj.setRooms(Collections.singletonList(roomModel));

        JPanel draw = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                TestDraw.g = g;
                TestDraw.update();
                /*
                int x[] = {100, 70, 130};
                int y[] = {50, 100, 100};
                g.drawPolygon(x, y, 3);*/
            }
        };

        JFrame frame = new JFrame("TestDraw");
        frame.setPreferredSize(new Dimension(600, 400));
        frame.add(draw);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void update() {

        ProjectModel project = ProjectManager.getInstance().getProjectModel();
        if (project == null) {
            return;
        }
        float x = project.getUserSettings().getLastMapX();
        float y = project.getUserSettings().getLastMapY();
        float zoom = project.getUserSettings().getLastZoomFactor();
        BaseGui gui = BaseGui.getInstance();
        String roomId = "TEST_ROOM";
        ProjectModel.RoomModel room = null;
        for (ProjectModel.RoomModel r : project.getRooms()) {
            if (r.getName().equals(roomId)) {
                room = r;
                break;
            }
        }
        for (Map.Entry<String, ProjectModel.RoomModel.SectorGroup> sectorGroup : room.getSectorGroupById().entrySet()) {
            if (sectorGroup.getKey().equals(DEFAULT_SG_NAME)) {
                ((Graphics2D) g).setStroke(new BasicStroke(1));
                g.setColor(Color.white);
                for (ProjectModel.RoomModel.SectorGroup.Sector sector : sectorGroup.getValue().getSectors()) {
                    g.drawPolygon(normalize3(new float[]{sector.getX1(), sector.getX2(), sector.getX3()}, x, zoom), normalize3(new float[]{sector.getZ1(), sector.getZ2(), sector.getZ3()}, y, zoom), 3);
                }
                g.dispose();
            }
        }



    }

    private static int[] normalize3(float[] floats, float offset, float zoom) {
        int[] vals = {
                (int) (floats[0] * NORMALIZE_RATIO * zoom) + (int) offset,
                (int) (floats[1] * NORMALIZE_RATIO * zoom) + (int) offset,
                (int) (floats[2] * NORMALIZE_RATIO * zoom) + (int) offset};

        System.out.println(vals[0] + "-" + vals[1] + "-" + vals[2]);

        return vals;
    }

}
