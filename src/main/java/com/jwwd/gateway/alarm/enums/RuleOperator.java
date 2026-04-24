package com.jwwd.gateway.alarm.enums;

import com.jwwd.gateway.common.enums.CodeEnum;

import java.math.BigDecimal;

public enum RuleOperator implements CodeEnum<String> {

    GT("GT", "greater than") {
        @Override
        public boolean matches(BigDecimal actual, BigDecimal expected) {
            return actual.compareTo(expected) > 0;
        }
    },
    GE("GE", "greater than or equal") {
        @Override
        public boolean matches(BigDecimal actual, BigDecimal expected) {
            return actual.compareTo(expected) >= 0;
        }
    },
    LT("LT", "less than") {
        @Override
        public boolean matches(BigDecimal actual, BigDecimal expected) {
            return actual.compareTo(expected) < 0;
        }
    },
    LE("LE", "less than or equal") {
        @Override
        public boolean matches(BigDecimal actual, BigDecimal expected) {
            return actual.compareTo(expected) <= 0;
        }
    },
    EQ("EQ", "equal") {
        @Override
        public boolean matches(BigDecimal actual, BigDecimal expected) {
            return actual.compareTo(expected) == 0;
        }
    };

    private final String code;
    private final String description;

    RuleOperator(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public abstract boolean matches(BigDecimal actual, BigDecimal expected);

    public static RuleOperator fromCode(String code) {
        for (RuleOperator operator : values()) {
            if (operator.code.equals(code)) {
                return operator;
            }
        }
        throw new IllegalArgumentException("Unsupported rule operator: " + code);
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
