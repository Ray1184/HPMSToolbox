package org.hpms.gui.views;

import org.hpms.gui.data.ProjectModel;

public interface Labels {

    class PipelineTypeItem {
        private final String desc;

        private final ProjectModel.RoomModel.PipelineType pipelineType;

        public PipelineTypeItem(String desc, ProjectModel.RoomModel.PipelineType pipelineType) {
            this.desc = desc;
            this.pipelineType = pipelineType;
        }

        @Override
        public String toString() {
            return desc;
        }

        /**
         * Getter for property 'pipelineType'.
         *
         * @return Value for property 'pipelineType'.
         */
        public ProjectModel.RoomModel.PipelineType getPipelineType() {
            return pipelineType;
        }


    }

    class TriggerTypeItem {
        private final String desc;

        private final ProjectModel.RoomModel.Event.TriggerType triggerType;

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


    }
}
