package com.gjr.wheelpickerbyrecyclerview.data;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.Index;

/**
 * Created by geng
 * on 2017/4/6.
 */
public class ProvinceBean extends RealmObject {


    /**
     * l1Id : 34
     * l1Jianpin : BJ
     * l1Name : 北京市
     * l1Pinyin : BeiJing
     * l2Id : 398
     * l2Jianpin : BJ
     * l2Name : 北京
     * l2Pinyin : beijing
     * l3Id : 3545
     * l3Jianpin :
     * l3Name : 东城区
     * l3Pinyin :
     */

    private int l1Id;
    @Ignore
    private String l1Jianpin;
    @Index
    private String l1Name;
    @Ignore
    private String l1Pinyin;
    private int l2Id;
    @Ignore
    private String l2Jianpin;
    @Index
    private String l2Name;
    @Ignore
    private String l2Pinyin;
    private int l3Id;
    @Ignore
    private String l3Jianpin;
    @Index
    private String l3Name;
    @Ignore
    private String l3Pinyin;

    public int getL1Id() {
        return l1Id;
    }

    public void setL1Id(int l1Id) {
        this.l1Id = l1Id;
    }

    public String getL1Jianpin() {
        return l1Jianpin;
    }

    public void setL1Jianpin(String l1Jianpin) {
        this.l1Jianpin = l1Jianpin;
    }

    public String getL1Name() {
        return l1Name;
    }

    public void setL1Name(String l1Name) {
        this.l1Name = l1Name;
    }

    public String getL1Pinyin() {
        return l1Pinyin;
    }

    public void setL1Pinyin(String l1Pinyin) {
        this.l1Pinyin = l1Pinyin;
    }

    public int getL2Id() {
        return l2Id;
    }

    public void setL2Id(int l2Id) {
        this.l2Id = l2Id;
    }

    public String getL2Jianpin() {
        return l2Jianpin;
    }

    public void setL2Jianpin(String l2Jianpin) {
        this.l2Jianpin = l2Jianpin;
    }

    public String getL2Name() {
        return l2Name;
    }

    public void setL2Name(String l2Name) {
        this.l2Name = l2Name;
    }

    public String getL2Pinyin() {
        return l2Pinyin;
    }

    public void setL2Pinyin(String l2Pinyin) {
        this.l2Pinyin = l2Pinyin;
    }

    public int getL3Id() {
        return l3Id;
    }

    public void setL3Id(int l3Id) {
        this.l3Id = l3Id;
    }

    public String getL3Jianpin() {
        return l3Jianpin;
    }

    public void setL3Jianpin(String l3Jianpin) {
        this.l3Jianpin = l3Jianpin;
    }

    public String getL3Name() {
        return l3Name;
    }

    public void setL3Name(String l3Name) {
        this.l3Name = l3Name;
    }

    public String getL3Pinyin() {
        return l3Pinyin;
    }

    public void setL3Pinyin(String l3Pinyin) {
        this.l3Pinyin = l3Pinyin;
    }
}
