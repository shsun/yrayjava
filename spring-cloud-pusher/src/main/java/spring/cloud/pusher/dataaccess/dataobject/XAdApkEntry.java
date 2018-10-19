package spring.cloud.pusher.dataaccess.dataobject;

public class XAdApkEntry {

    private int cacheStatus;
    private String pathRemote;
    private String pathLocal;

    private String packageName;
    private String versionCode;
    private String versionName;
    private String[] usesPermission;


    public XAdApkEntry() {
    }

    public int getCacheStatus() {
        return cacheStatus;
    }

    public void setCacheStatus(int cacheStatus) {
        this.cacheStatus = cacheStatus;
    }

    public String getPathRemote() {
        return pathRemote;
    }

    public void setPathRemote(String pathRemote) {
        this.pathRemote = pathRemote;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String[] getUsesPermission() {
        return usesPermission;
    }

    public void setUsesPermission(String[] usesPermission) {
        this.usesPermission = usesPermission;
    }

    public String getPathLocal() {
        return pathLocal;
    }

    public void setPathLocal(String pathLocal) {
        this.pathLocal = pathLocal;
    }
}
