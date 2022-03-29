package com.itrex.junit.service.allexensions;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

public class ConditionalExtension implements ExecutionCondition {
/*
property -"skip1" we can transfer
  */
    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {
        return System.getProperty("skip1")!=null
                ? ConditionEvaluationResult.disabled("test is skipped")
                : ConditionEvaluationResult.enabled("enabled by default");
    }
}
