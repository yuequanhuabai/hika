package com.e.hika.i18npojo;

//@Data
public class Name {
    //    @JsonProperty("cn")
    private String cn;
    //    @JsonProperty("en")
    private String en;
    //    @JsonProperty("hk")
    private String hk;

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getHk() {
        return hk;
    }

    public void setHk(String hk) {
        this.hk = hk;
    }

    @Override
    public String toString() {
        return "Name{" +
                "cn='" + cn + '\'' +
                ", en='" + en + '\'' +
                ", hk='" + hk + '\'' +
                '}';
    }
}
