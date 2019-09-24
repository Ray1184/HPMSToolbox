package org.hpms.gui.data;

import org.hpms.gui.luagen.LuaStatement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectModel implements Serializable {

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
    }

    public static class RoomModel implements Serializable {

        public enum PipelineType {
            R25D,
            GUI,
            F3D
        }

        public static class Event implements Serializable {

            public enum TriggerType {
                INIT,
                LOOP,
                CLOSING
            }

            public static class Condition implements Serializable {
                public Condition() {
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

            private PipelineType pipelineType;

            private List<Condition> conditions;

            private List<Action> actions;

            private TriggerType triggerType;

            private int priority;

            public Event() {
                conditions = new ArrayList<>();
                actions = new ArrayList<>();
                triggerType = TriggerType.INIT;
                priority = 0;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<Condition> getConditions() {
                return conditions;
            }

            public void setConditions(List<Condition> conditions) {
                this.conditions = conditions;
            }

            public List<Action> getActions() {
                return actions;
            }

            public void setActions(List<Action> actions) {
                this.actions = actions;
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

            public PipelineType getPipelineType() {
                return pipelineType;
            }

            public void setPipelineType(PipelineType pipelineType) {
                this.pipelineType = pipelineType;
            }
        }

        public static class SectorGroup implements Serializable {
            public static class Sector implements Serializable {
                public static class PerimetralSide implements Serializable {
                    private int idx1;
                    private int idx2;

                    public PerimetralSide() {
                    }

                    public int getIdx1() {
                        return idx1;
                    }

                    public void setIdx1(int idx1) {
                        this.idx1 = idx1;
                    }

                    public int getIdx2() {
                        return idx2;
                    }

                    public void setIdx2(int idx2) {
                        this.idx2 = idx2;
                    }
                }

                private float x1, x2, x3, y1, y2, y3, z1, z2, z3;

                private List<PerimetralSide> sides;

                public Sector() {
                    sides = new ArrayList<>();
                }

                public List<PerimetralSide> getSides() {
                    return sides;
                }

                public void setSides(List<PerimetralSide> sides) {
                    this.sides = sides;
                }

                public float getX1() {
                    return x1;
                }

                public void setX1(float x1) {
                    this.x1 = x1;
                }

                public float getX2() {
                    return x2;
                }

                public void setX2(float x2) {
                    this.x2 = x2;
                }

                public float getX3() {
                    return x3;
                }

                public void setX3(float x3) {
                    this.x3 = x3;
                }

                public float getY1() {
                    return y1;
                }

                public void setY1(float y1) {
                    this.y1 = y1;
                }

                public float getY2() {
                    return y2;
                }

                public void setY2(float y2) {
                    this.y2 = y2;
                }

                public float getY3() {
                    return y3;
                }

                public void setY3(float y3) {
                    this.y3 = y3;
                }

                public float getZ1() {
                    return z1;
                }

                public void setZ1(float z1) {
                    this.z1 = z1;
                }

                public float getZ2() {
                    return z2;
                }

                public void setZ2(float z2) {
                    this.z2 = z2;
                }

                public float getZ3() {
                    return z3;
                }

                public void setZ3(float z3) {
                    this.z3 = z3;
                }
            }

            private List<Sector> sectors;

            private String id;

            public SectorGroup() {
                sectors = new ArrayList<>();
            }

            public List<Sector> getSectors() {
                return sectors;
            }

            public void setSectors(List<Sector> sectors) {
                this.sectors = sectors;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }

        private String name;

        private Map<String, SectorGroup> sectorGroupById;

        private Map<String, Event> eventsById;


        public RoomModel() {
            eventsById = new HashMap<>();
            sectorGroupById = new HashMap<>();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Map<String, SectorGroup> getSectorGroupById() {
            return sectorGroupById;
        }

        public void setSectorGroupById(Map<String, SectorGroup> sectorGroupById) {
            this.sectorGroupById = sectorGroupById;
        }

        public Map<String, Event> getEventsById() {
            return eventsById;
        }

        public void setEventsById(Map<String, Event> eventsById) {
            this.eventsById = eventsById;
        }
    }

    private UserSettings userSettings;

    private ProjectPreferences preferences;

    private BuildSettings settings;

    private List<RoomModel> rooms;

    public ProjectModel() {
        rooms = new ArrayList<>();
        userSettings = new UserSettings();
        preferences = new ProjectPreferences();
        settings = new BuildSettings();
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

    public List<RoomModel> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomModel> rooms) {
        this.rooms = rooms;
    }
}
