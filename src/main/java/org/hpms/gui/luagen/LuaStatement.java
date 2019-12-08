package org.hpms.gui.luagen;

import java.util.List;

public interface LuaStatement {

    String INDENTATION = "    ";

    String getCode();

    String getParentIndent();

    void setParentIndent(String indent);

    ValidationResult validate();

    class ValidationResult {

        private final boolean valid;
        private final List<String> reasons;

        public ValidationResult(boolean valid, List<String> reasons) {
            this.valid = valid;
            this.reasons = reasons;

        }
        public ValidationResult(boolean valid) {
            this(valid, null);
        }

        /**
         * Getter for property 'valid'.
         *
         * @return Value for property 'valid'.
         */
        public boolean isValid() {
            return valid;
        }

        /**
         * Getter for property 'reason'.
         *
         * @return Value for property 'reason'.
         */
        public List<String> getReasons() {
            return reasons;
        }
    }


}
