package org.hpms.gui.views;

import org.hpms.gui.data.ProjectModel;

public interface Labels {

    class TriggerTypeItem {
        private final String desc;

        private ProjectModel.RoomModel.Event.TriggerType triggerType;

        public TriggerTypeItem(String desc, ProjectModel.RoomModel.Event.TriggerType triggerType) {
            this.desc = desc;
            this.triggerType = triggerType;
        }

        @Override
        public String toString() {
           return desc;
        }

        /**
         * Getter for property 'triggerType'.
         *
         * @return Value for property 'triggerType'.
         */
        public ProjectModel.RoomModel.Event.TriggerType getTriggerType() {
            return triggerType;
        }

        /**
         * Setter for property 'triggerType'.
         *
         * @param triggerType Value to set for property 'triggerType'.
         */
        public void setTriggerType(ProjectModel.RoomModel.Event.TriggerType triggerType) {
            this.triggerType = triggerType;
        }
    }
}
