package io.egen.enums;


public enum Rule {
    RULE1("engineRpm > readlineRpm","HIGH"),
    RULE2("fuelVolume < 10% of maxFuelVolume","MEDIUM"),
    RULE3("tire pressure of any tire < 32psi || > 36psi","LOW"),
    RULE4("engineCoolantLow = true || checkEngineLightOn = true","LOW");

    private String rule_description;
    private String priority;

    Rule(String rule_description, String priority) {
        this.rule_description = rule_description;
        this.priority = priority;
    }

    public String getRule_description(){
        return rule_description;
    }

    public String getPriority(){
        return priority;
    }
}
