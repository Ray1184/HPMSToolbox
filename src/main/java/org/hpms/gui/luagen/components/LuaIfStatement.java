package org.hpms.gui.luagen.components;

import org.hpms.gui.luagen.LuaStatement;
import org.hpms.gui.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class LuaIfStatement implements LuaStatement {

    public static class LuaCondition implements LuaStatement {

        public static class LuaSingleCondition implements LuaStatement {

            private String parentIndent;

            private final List<LuaExpression> expressions;

            private final List<LuaBinaryOperator> binaryConnectors;


            public LuaSingleCondition(LuaExpression condition) {
                expressions = new ArrayList<>();
                expressions.add(condition);
                binaryConnectors = new ArrayList<>();
            }

            public LuaSingleCondition(List<LuaExpression> conditions, List<LuaBinaryOperator> binaryConnectors) {
                expressions = conditions;
                this.binaryConnectors = binaryConnectors;
            }

            public LuaSingleCondition() {
                binaryConnectors = new ArrayList<>();
                expressions = new ArrayList<>();
            }

            @Override
            public String getCode() {
                StringBuilder sb = new StringBuilder();
                int i = 0;
                for (LuaExpression expression : expressions) {
                    expression.setParentIndent("");
                    sb.append(expression.getCode());
                    if (i < binaryConnectors.size()) {
                        sb.append(" ")
                                .append(binaryConnectors.get(i++).getCode())
                                .append(" ");
                    }
                }

                return sb.toString();
            }

            @Override
            public String getParentIndent() {
                return parentIndent;
            }

            @Override
            public void setParentIndent(String indent) {
                parentIndent = indent;
            }

            @Override
            public ValidationResult validate() {
                if (expressions.size() - 1 != binaryConnectors.size()) {
                    return new ValidationResult(false, Utils.singletonList("Number of binary connectors must be one less then single conditions branches."));
                } else {
                    return new ValidationResult(true);
                }
            }
        }

        private String parentIndent;

        private List<LuaSingleCondition> singleConditions;

        private List<LuaBinaryOperator> binaryConnectors;

        public LuaCondition() {}

        public LuaCondition(LuaSingleCondition condition) {
            singleConditions = new ArrayList<>();
            singleConditions.add(condition);
            binaryConnectors = new ArrayList<>();
        }

        public LuaCondition(List<LuaSingleCondition> singleConditions, List<LuaBinaryOperator> binaryConnectors) {
            this.binaryConnectors = binaryConnectors;
            this.singleConditions = singleConditions;
        }

        @Override
        public String getCode() {
            StringBuilder sb = new StringBuilder();
            if (singleConditions.size() > 1) {
                sb.append("(");
            }
            int i = 0;
            for (LuaSingleCondition singleCondition : singleConditions) {
                sb.append(singleCondition.getCode());
                if (i < binaryConnectors.size()) {
                    sb.append(" ")
                            .append(binaryConnectors.get(i++).getCode())
                            .append(" ");
                }
            }
            if (singleConditions.size() > 1) {
                sb.append(")");
            }
            return sb.toString();
        }

        @Override
        public String getParentIndent() {
            return parentIndent;
        }

        @Override
        public void setParentIndent(String indent) {
            parentIndent = indent;
        }

        @Override
        public ValidationResult validate() {
            ValidationResult result = new ValidationResult(true);
            boolean valid = true;
            List<String> reasons = new ArrayList<>();
            for (LuaSingleCondition cond : singleConditions) {
                ValidationResult res = cond.validate();

                if (!res.isValid()) {
                    valid = false;
                    reasons.addAll(res.getReasons());
                }
            }
            if (!valid) {
                result = new ValidationResult(false, reasons);
            }
            if (singleConditions.size() - 1 != binaryConnectors.size()) {
                if (!valid) {
                    reasons.add("Number of binary connectors must be one less then single conditions branches.");
                    result = new ValidationResult(false, reasons);
                } else {
                    result = new ValidationResult(false, Utils.singletonList("Number of binary connectors must be one less then single conditions branches."));
                }
            }
            return result;
        }
    }

    private LuaCondition condition;

    private List<LuaStatement> actions;

    private LuaCondition elseCondition;

    private List<LuaStatement> elseActions;

    private String parentIndent;

    public LuaIfStatement() {}

    public LuaIfStatement(LuaCondition condition, List<LuaStatement> actions, LuaCondition elseCondition, List<LuaStatement> elseActions) {
        this.condition = condition;
        this.actions = actions;
        this.elseActions = elseActions;
        this.elseCondition = elseCondition;
    }

    public LuaIfStatement(LuaCondition condition, List<LuaStatement> actions, List<LuaStatement> elseActions) {
        this(condition, actions, null, elseActions);
    }

    public LuaIfStatement(LuaCondition condition, List<LuaStatement> actions) {
        this(condition, actions, null);
    }

    public LuaCondition getCondition() {
        return condition;
    }

    public void setCondition(LuaCondition condition) {
        this.condition = condition;
    }

    /**
     * Getter for property 'actions'.
     *
     * @return Value for property 'actions'.
     */
    public List<LuaStatement> getActions() {
        return actions;
    }

    /**
     * Setter for property 'actions'.
     *
     * @param actions Value to set for property 'actions'.
     */
    public void setActions(List<LuaStatement> actions) {
        this.actions = actions;
    }

    /**
     * Getter for property 'elseActions'.
     *
     * @return Value for property 'elseActions'.
     */
    public List<LuaStatement> getElseActions() {
        return elseActions;
    }

    /**
     * Setter for property 'elseActions'.
     *
     * @param elseActions Value to set for property 'elseActions'.
     */
    public void setElseActions(List<LuaStatement> elseActions) {
        this.elseActions = elseActions;
    }

    public LuaCondition getElseCondition() {
        return elseCondition;
    }

    public void setElseCondition(LuaCondition elseCondition) {
        this.elseCondition = elseCondition;
    }

    @Override
    public ValidationResult validate() {

        List<String> reasons = new ArrayList<>();
        boolean valid = true;
        if (actions != null) {
            for (LuaStatement stat : actions) {
                ValidationResult actionVal = stat.validate();
                if (!actionVal.isValid()) {
                    reasons.addAll(actionVal.getReasons());
                    valid = false;
                }
            }
        }
        if (elseActions != null) {
            for (LuaStatement stat : elseActions) {
                ValidationResult actionVal = stat.validate();
                if (!actionVal.isValid()) {
                    reasons.addAll(actionVal.getReasons());
                    valid = false;
                }
            }
        }
        ValidationResult res = condition.validate();

        ValidationResult res2 = new ValidationResult(true);
        if (elseCondition != null) {
            res2 = elseCondition.validate();
        }


        if (!res.isValid()) {
            reasons.addAll(res.getReasons());
        }

        if (!res2.isValid()) {
            reasons.addAll(res2.getReasons());
        }

        return new ValidationResult(res.isValid() && res2.isValid() && valid, reasons);
    }

    @Override
    public String getCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(parentIndent)
                .append("if ")
                .append(condition.getCode())
                .append(" then");
        if (actions != null) {
            for (LuaStatement action : actions) {
                action.setParentIndent(parentIndent + INDENTATION);
                sb.append("\n")
                        .append(action.getCode());
            }
        }

        if (elseActions != null) {
            if (!elseActions.isEmpty()) {
                sb.append(parentIndent);
                if (elseCondition != null) {
                    sb.append("elseif ")
                            .append("\n")
                            .append(elseCondition.getCode());
                } else {
                    sb.append("else\n");
                }
            }
            for (LuaStatement elseAction : elseActions) {
                elseAction.setParentIndent(parentIndent + INDENTATION);
                sb.append(elseAction.getCode());
            }
        }
        sb.append("\n")
                .append(parentIndent)
                .append("end");

        return sb.toString();
    }

    @Override
    public String getParentIndent() {
        return parentIndent;
    }

    @Override
    public void setParentIndent(String parentIndent) {
        this.parentIndent = parentIndent;
    }
}
