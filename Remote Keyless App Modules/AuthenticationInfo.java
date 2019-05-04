package com.example.kc.gpdriverless;

public class AuthenticationInfo {
    private static String hostNameStatic="boş";
    private static String hostIPStatic="boş";
    private static String passwordStatic="boş";
    private static String destXCoordinate="boş";
    private static String destYCoordinate="boş";

    public static String getHostNameStatic() { return hostNameStatic; }
    public static void setHostNameStatic(String comingHostName) { hostNameStatic=comingHostName; }

    public static String getHostIPStatic(){return hostIPStatic;}
    public static void setHostIPStatic(String comingHostIP){hostIPStatic=comingHostIP;}

    public static String getPasswordStatic() { return passwordStatic; }
    public static void setPasswordStatic(String comingPassword) { passwordStatic=comingPassword; }

    public static String getDestXCoordinate(){ return destXCoordinate; }
    public static void setDestXCoordinate(String comingDestXCoordinate){destXCoordinate=comingDestXCoordinate;}

    public static String getDestYCoordinate(){ return destYCoordinate;}
    public static void setDestYCoordinate(String comingDestYCoordinate){destYCoordinate=comingDestYCoordinate;}


}
