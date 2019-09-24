package org.hpms.gui.luagen.components;

import org.hpms.gui.luagen.LuaStatement;

import java.util.ArrayList;
import java.util.List;

public class LuaCondition implements LuaStatement {

    private LuaInstance left;

    private LuaInstance right;

    private LuaBinaryOperator operator;

    private List<LuaStatement> actions;

    private List<LuaStatement> elseActions;

    private String parentIndent;


    public LuaCondition(LuaInstance left, LuaInstance right, LuaBinaryOperator operator, List<LuaStatement> actions, List<LuaStatement> elseActions) {
        this.left = left;
        this.right = right;
        this.operator = operator;
        this.actions = actions;
        this.elseActions = elseActions;
    }

    public LuaCondition(LuaInstance left, LuaInstance right, LuaBinaryOperator operator, List<LuaStatement> actions) {
        this(left, right, operator, actions, null);
    }

    /**
     * Getter for property 'left'.
     *
     * @return Value for property 'left'.
     */
    public LuaInstance getLeft() {
        return left;
    }

    /**
     * Setter for property 'left'.
     *
     * @param left Value to set for property 'left'.
     */
    public void setLeft(LuaInstance left) {
        this.left = left;
    }

    /**
     * Getter for property 'right'.
     *
     * @return Value for property 'right'.
     */
    public LuaInstance getRight() {
        return right;
    }

    /**
     * Setter for property 'right'.
     *
     * @param right Value to set for property 'right'.
     */
    public void setRight(LuaInstance right) {
        this.right = right;
    }

    /**
     * Getter for property 'operator'.
     *
     * @return Value for property 'operator'.
     */
    public LuaBinaryOperator getOperator() {
        return operator;
    }

    /**
     * Setter for property 'operator'.
     *
     * @param operator Value to set for property 'operator'.
     */
    public void setOperator(LuaBinaryOperator operator) {
        this.operator = operator;
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
        boolean res1 = operator.allowedTypeOnLeft().contains(left.getType());
        boolean res2 = operator.allowedTypeOnRight().contains(right.getType());

        if (!res1) {
            reasons.add("Operator " + operator.name() + " doesn't allows " + left.getType().name() + " type as left operand.");
        }
        if (!res2) {
            reasons.add("Operator " + operator.name() + " doesn't allows " + right.getType().name() + " type as right operand.");
        }
        return new ValidationResult(res1 && res2 && valid, reasons);
    }

    @Override
    public String getCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(parentIndent)
                .append("if ")
                .append(left.getCode())
                .append(" ")
                .append(operator.getCode())
                .append(" ")
                .append(right.getCode())
                .append(" then\n");
        if (actions != null) {
            for (LuaStatement action : actions) {
                action.setParentIndent(parentIndent + INDENTATION);
                sb.append(action.getCode());
            }
        }

        if (elseActions != null) {
            if (!elseActions.isEmpty()) {
                sb.append(parentIndent)
                        .append("else\n");
            }
            for (LuaStatement elseAction : elseActions) {
                elseAction.setParentIndent(parentIndent + INDENTATION);
                sb.append(elseAction.getCode());
            }
        }

        sb.append(parentIndent)
                .append("end\n");

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
