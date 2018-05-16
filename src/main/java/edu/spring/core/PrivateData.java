package edu.spring.core;

public class PrivateData {
    private String userId;
    private String GPS;
    private String time;

    public PrivateData(String userId, String GPS, String time) {
        this.userId = userId;
        this.GPS = GPS;
        this.time = time;
    }

    public PrivateData(){
        ChainHelper chainHelper = new ChainHelper();
        userId = chainHelper.getId();
        GPS = chainHelper.getGPS();
        time = chainHelper.getTime();
    }

    public String getPrimaryData(){
        return (userId+" "+GPS+" "+time);
    }

    public String getHashedData(){
        return StringUtil.applySha256(getPrimaryData());
    }
}

