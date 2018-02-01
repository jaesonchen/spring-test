package com.asiainfo.service;

import java.io.Serializable;

/**
 * TODO
 * 
 * @author       zq
 * @date         2018年1月25日  下午5:33:15
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
public class Rule implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    private String ruleId;
    private String channelId;
    private String cycleId;
    private int frequency;
    public String getRuleId() {
        return ruleId;
    }
    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }
    public String getChannelId() {
        return channelId;
    }
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
    public String getCycleId() {
        return cycleId;
    }
    public void setCycleId(String cycleId) {
        this.cycleId = cycleId;
    }
    public int getFrequency() {
        return frequency;
    }
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
    @Override
    public String toString() {
        return "Rule [ruleId=" + ruleId + ", channelId=" + channelId + ", cycleId=" + cycleId + ", frequency="
                + frequency + "]";
    }
}
