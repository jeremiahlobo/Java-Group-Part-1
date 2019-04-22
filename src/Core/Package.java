package Core;

public class Package {

    int PackageId;
    String PkgName;
    String PkgStartDate;
    String PkgEndDate;
    String PkgDesc;
    double PkgBasePrice;
    double PkgAgencyCommission;

    public Package(int packageId, String pkgName, String pkgStartDate, String pkgEndDate, String pkgDesc, double pkgBasePrice, double pkgAgencyCommission) {
        PackageId = packageId;
        PkgName = pkgName;
        PkgStartDate = pkgStartDate;
        PkgEndDate = pkgEndDate;
        PkgDesc = pkgDesc;
        PkgBasePrice = pkgBasePrice;
        PkgAgencyCommission = pkgAgencyCommission;
    }

    public int getPackageId() {
        return PackageId;
    }

    public void setPackageId(int packageId) {
        PackageId = packageId;
    }

    public String getPkgName() {
        return PkgName;
    }

    public void setPkgName(String pkgName) {
        PkgName = pkgName;
    }

    public String getPkgStartDate() {
        return PkgStartDate;
    }

    public void setPkgStartDate(String pkgStartDate) {
        PkgStartDate = pkgStartDate;
    }

    public String getPkgEndDate() {
        return PkgEndDate;
    }

    public void setPkgEndDate(String pkgEndDate) {
        PkgEndDate = pkgEndDate;
    }

    public String getPkgDesc() {
        return PkgDesc;
    }

    public void setPkgDesc(String pkgDesc) {
        PkgDesc = pkgDesc;
    }

    public double getPkgBasePrice() {
        return PkgBasePrice;
    }

    public void setPkgBasePrice(double pkgBasePrice) {
        PkgBasePrice = pkgBasePrice;
    }

    public double getPkgAgencyCommission() {
        return PkgAgencyCommission;
    }

    public void setPkgAgencyCommission(double pkgAgencyCommission) {
        PkgAgencyCommission = pkgAgencyCommission;
    }

    @Override
    public String toString() {
        return
                PackageId +
                "   " + PkgName;

    }
}
