package org.hpms.gui.data;

import org.hpms.gui.luagen.LuaStatement;
import org.hpms.gui.luagen.components.LuaFunctionDeclare;
import org.hpms.gui.luagen.components.LuaIfStatement;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProjectModel implements Serializable {

    public static final String DATA_DIR = "data";
    public static final String RESOURCES_DIR = DATA_DIR + File.separator + "resources";
    public static final String MODELS_DIR = RESOURCES_DIR + File.separator + "models";
    public static final String IMAGES_DIR = RESOURCES_DIR + File.separator + "textures";
    public static final String SHADERS_DIR = DATA_DIR + File.separator + "shaders";
    public static final String SCRIPTS_DIR = DATA_DIR + File.separator + "scripts";
    public static final String DEFAULT_SG_NAME = "____default____";

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public String getRuntimeName() {
        return runtimeName;
    }

    public void setRuntimeName(String runtimeName) {
        this.runtimeName = runtimeName;
    }

    public static class UserSettings implements Serializable {

        public UserSettings() {
        }

        float lastMapX;

        float lastMapY;

        float lastZoomFactor;

        public float getLastMapX() {
            return lastMapX;
        }

        public void setLastMapX(float lastMapX) {
            this.lastMapX = lastMapX;
        }

        public float getLastMapY() {
            return lastMapY;
        }

        public void setLastMapY(float lastMapY) {
            this.lastMapY = lastMapY;
        }

        public float getLastZoomFactor() {
            return lastZoomFactor;
        }

        public void setLastZoomFactor(float lastZoomFactor) {
            this.lastZoomFactor = lastZoomFactor;
        }
    }

    public static class ProjectPreferences implements Serializable {

        public ProjectPreferences() {
        }
    }

    public static class BuildSettings implements Serializable {

        public BuildSettings() {
        }

        private boolean explodeBuild;

        private boolean debug;

        private boolean cacheImages;

        private boolean cacheModels;

        public boolean isExplodeBuild() {
            return explodeBuild;
        }

        public void setExplodeBuild(boolean explodeBuild) {
            this.explodeBuild = explodeBuild;
        }

        public boolean isDebug() {
            return debug;
        }

        public void setDebug(boolean debug) {
            this.debug = debug;
        }

        public boolean isCacheImages() {
            return cacheImages;
        }

        public void setCacheImages(boolean cacheImages) {
            this.cacheImages = cacheImages;
        }

        public boolean isCacheModels() {
            return cacheModels;
        }

        public void setCacheModels(boolean cacheModels) {
            this.cacheModels = cacheModels;
        }
    }


    public static class RoomModel implements Serializable {

        public enum PipelineType {
            R25D,
            GUI,
            F3D
        }

        public static class Event implements Serializable {

            public enum TriggerType {
                INIT("setup"),
                LOOP("update"),
                CLEANUP("cleanup");

                private final String scriptPart;

                TriggerType(String scriptPart) {
                    this.scriptPart = scriptPart;
                }

                public String getScriptPart() {
                    return scriptPart;
                }
            }

            public static class ConditionAction implements Serializable {
                public ConditionAction() {
                }

                private LuaIfStatement ifStatement;

                public LuaIfStatement getIfStatement() {
                    return ifStatement;
                }

                public void setIfStatement(LuaIfStatement ifStatement) {
                    this.ifStatement = ifStatement;
                }
            }

            public static class Action implements Serializable {
                public Action() {
                    statementList = new ArrayList<>();
                }

                private List<LuaStatement> statementList;

                public List<LuaStatement> getStatementList() {
                    return statementList;
                }

                public void setStatementList(List<LuaStatement> statementList) {
                    this.statementList = statementList;
                }


            }

            private String name;


            private ConditionAction conditionAction;

            private Action action;

            private TriggerType triggerType;

            private int priority;

            public Event() {
                action = new Action();
                triggerType = TriggerType.INIT;
                priority = 0;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public ConditionAction getConditionAction() {
                return conditionAction;
            }

            public void setConditionAction(ConditionAction conditionAction) {
                this.conditionAction = conditionAction;
            }

            public Action getAction() {
                return action;
            }

            public void setAction(Action action) {
                this.action = action;
            }

            public TriggerType getTriggerType() {
                return triggerType;
            }

            public void setTriggerType(TriggerType triggerType) {
                this.triggerType = triggerType;
            }

            public int getPriority() {
                return priority;
            }

            public void setPriority(int priority) {
                this.priority = priority;
            }

        }

        public static class SectorGroup implements Serializable {
            public static class Sector implements Serializable {
                public static class PerimetralSide implements Serializable {
                    private int idx1;
                    private int idx2;

                    public PerimetralSide() {
                    }

                    public synchronized int getIdx1() {
                        return idx1;
                    }

                    public synchronized void setIdx1(int idx1) {
                        this.idx1 = idx1;
                    }

                    public synchronized int getIdx2() {
                        return idx2;
                    }

                    public synchronized void setIdx2(int idx2) {
                        this.idx2 = idx2;
                    }
                }

                private float x1, x2, x3, y1, y2, y3, z1, z2, z3;

                private String id;

                private String groupId;

                private List<PerimetralSide> sides;

                public Sector() {
                    sides = new ArrayList<>();
                }

                public synchronized List<PerimetralSide> getSides() {
                    return sides;
                }

                public synchronized void setSides(List<PerimetralSide> sides) {
                    this.sides = sides;
                }

                public synchronized float getX1() {
                    return x1;
                }

                public synchronized void setX1(float x1) {
                    this.x1 = x1;
                }

                public synchronized float getX2() {
                    return x2;
                }

                public synchronized void setX2(float x2) {
                    this.x2 = x2;
                }

                public synchronized float getX3() {
                    return x3;
                }

                public synchronized void setX3(float x3) {
                    this.x3 = x3;
                }

                public synchronized float getY1() {
                    return y1;
                }

                public synchronized void setY1(float y1) {
                    this.y1 = y1;
                }

                public synchronized float getY2() {
                    return y2;
                }

                public synchronized void setY2(float y2) {
                    this.y2 = y2;
                }

                public synchronized float getY3() {
                    return y3;
                }

                public synchronized void setY3(float y3) {
                    this.y3 = y3;
                }

                public synchronized float getZ1() {
                    return z1;
                }

                public synchronized void setZ1(float z1) {
                    this.z1 = z1;
                }

                public synchronized float getZ2() {
                    return z2;
                }

                public synchronized void setZ2(float z2) {
                    this.z2 = z2;
                }

                public synchronized float getZ3() {
                    return z3;
                }

                public synchronized void setZ3(float z3) {
                    this.z3 = z3;
                }

                /**
                 * Getter for property 'id'.
                 *
                 * @return Value for property 'id'.
                 */
                public synchronized String getId() {
                    return id;
                }

                /**
                 * Setter for property 'id'.
                 *
                 * @param id Value to set for property 'id'.
                 */
                public synchronized void setId(String id) {
                    this.id = id;
                }

                /**
                 * Getter for property 'groupId'.
                 *
                 * @return Value for property 'groupId'.
                 */
                public synchronized String getGroupId() {
                    return groupId;
                }

                /**
                 * Setter for property 'groupId'.
                 *
                 * @param groupId Value to set for property 'groupId'.
                 */
                public synchronized void setGroupId(String groupId) {
                    this.groupId = groupId;
                }

                @Override
                public boolean equals(Object o) {
                    if (this == o) return true;
                    if (o == null || getClass() != o.getClass()) return false;

                    Sector sector = (Sector) o;

                    if (Float.compare(sector.x1, x1) != 0) return false;
                    if (Float.compare(sector.x2, x2) != 0) return false;
                    if (Float.compare(sector.x3, x3) != 0) return false;
                    if (Float.compare(sector.y1, y1) != 0) return false;
                    if (Float.compare(sector.y2, y2) != 0) return false;
                    if (Float.compare(sector.y3, y3) != 0) return false;
                    if (Float.compare(sector.z1, z1) != 0) return false;
                    if (Float.compare(sector.z2, z2) != 0) return false;
                    return Float.compare(sector.z3, z3) == 0;
                }


            }

            private List<Sector> sectors;

            private String id;

            public SectorGroup() {
                sectors = new ArrayList<>();
            }

            public synchronized List<Sector> getSectors() {
                return sectors;
            }

            public synchronized void setSectors(List<Sector> sectors) {
                this.sectors = sectors;
            }

            public synchronized String getId() {
                return id;
            }

            public synchronized void setId(String id) {
                this.id = id;
            }
        }

        private String name;

        private Map<String, SectorGroup> sectorGroupById;

        private Map<String, Event> eventsById;

        private PipelineType pipelineType;


        public RoomModel() {
            eventsById = new LinkedHashMap<>();
            sectorGroupById = new LinkedHashMap<>();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public synchronized Map<String, SectorGroup> getSectorGroupById() {
            return sectorGroupById;
        }

        public synchronized void setSectorGroupById(Map<String, SectorGroup> sectorGroupById) {
            this.sectorGroupById = sectorGroupById;
        }

        public Map<String, Event> getEventsById() {
            return eventsById;
        }

        public void setEventsById(Map<String, Event> eventsById) {
            this.eventsById = eventsById;
        }


        public PipelineType getPipelineType() {
            return pipelineType;
        }

        public void setPipelineType(PipelineType pipelineType) {
            this.pipelineType = pipelineType;
        }
    }

    private UserSettings userSettings;

    private ProjectPreferences preferences;

    private BuildSettings settings;

    private Map<String, RoomModel> rooms;

    private Map<String, LuaFunctionDeclare> commonFunctions;

    private String firstRoom;

    private String projectPath;

    private String runtimeName;

    private String projectName;

    private String projectModelsPath;

    private String projectTexturesPath;

    private String projectFloorsPath;

    private String projectAudioPath;

    public ProjectModel() {
        rooms = new LinkedHashMap<>();
        userSettings = new UserSettings();
        preferences = new ProjectPreferences();
        settings = new BuildSettings();
        commonFunctions = new LinkedHashMap<>();
    }

    public UserSettings getUserSettings() {
        return userSettings;
    }

    public void setUserSettings(UserSettings userSettings) {
        this.userSettings = userSettings;
    }

    public ProjectPreferences getPreferences() {
        return preferences;
    }

    public void setPreferences(ProjectPreferences preferences) {
        this.preferences = preferences;
    }

    public BuildSettings getSettings() {
        return settings;
    }

    public void setSettings(BuildSettings settings) {
        this.settings = settings;
    }

    public Map<String, RoomModel> getRooms() {
        return rooms;
    }

    public void setRooms(Map<String, RoomModel> rooms) {
        this.rooms = rooms;
    }

    public String getFirstRoom() {
        return firstRoom;
    }

    public void setFirstRoom(String firstRoom) {
        this.firstRoom = firstRoom;
    }

    public Map<String, LuaFunctionDeclare> getCommonFunctions() {
        return commonFunctions;
    }

    public void setCommonFunctions(Map<String, LuaFunctionDeclare> commonFunctions) {
        this.commonFunctions = commonFunctions;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * Getter for property 'projectModelsPath'.
     *
     * @return Value for property 'projectModelsPath'.
     */
    public String getProjectModelsPath() {
        return projectModelsPath;
    }

    /**
     * Setter for property 'projectModelsPath'.
     *
     * @param projectModelsPath Value to set for property 'projectModelsPath'.
     */
    public void setProjectModelsPath(String projectModelsPath) {
        this.projectModelsPath = projectModelsPath;
    }

    /**
     * Getter for property 'projectTexturesPath'.
     *
     * @return Value for property 'projectTexturesPath'.
     */
    public String getProjectTexturesPath() {
        return projectTexturesPath;
    }

    /**
     * Setter for property 'projectTexturesPath'.
     *
     * @param projectTexturesPath Value to set for property 'projectTexturesPath'.
     */
    public void setProjectTexturesPath(String projectTexturesPath) {
        this.projectTexturesPath = projectTexturesPath;
    }

    /**
     * Getter for property 'projectFloorsPath'.
     *
     * @return Value for property 'projectFloorsPath'.
     */
    public String getProjectFloorsPath() {
        return projectFloorsPath;
    }

    /**
     * Setter for property 'projectFloorsPath'.
     *
     * @param projectFloorsPath Value to set for property 'projectFloorsPath'.
     */
    public void setProjectFloorsPath(String projectFloorsPath) {
        this.projectFloorsPath = projectFloorsPath;
    }

    /**
     * Getter for property 'projectAudioPath'.
     *
     * @return Value for property 'projectAudioPath'.
     */
    public String getProjectAudioPath() {
        return projectAudioPath;
    }

    /**
     * Setter for property 'projectAudioPath'.
     *
     * @param projectAudioPath Value to set for property 'projectAudioPath'.
     */
    public void setProjectAudioPath(String projectAudioPath) {
        this.projectAudioPath = projectAudioPath;
    }
}
