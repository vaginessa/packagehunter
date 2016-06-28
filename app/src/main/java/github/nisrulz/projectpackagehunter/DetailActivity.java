package github.nisrulz.projectpackagehunter;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import github.nisrulz.packagehunter.PackageHunter;

public class DetailActivity extends Activity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);

    String packageName = getIntent().getStringExtra("data");
    PackageHunter packageHunter = new PackageHunter(this);

    TextView txt_pkginfo_version = (TextView) findViewById(R.id.pkginfo_vname);
    TextView txt_pkginfo_versioncode = (TextView) findViewById(R.id.pkginfo_vc);
    TextView txt_pkginfo_appname = (TextView) findViewById(R.id.pkginfo_name);
    TextView txt_pkginfo_pkg = (TextView) findViewById(R.id.pkginfo_pkg);
    ImageView img_pkginfo_icon = (ImageView) findViewById(R.id.pkginfo_icn);

    TextView txt_permissions = (TextView) findViewById(R.id.txt_permissions);
    TextView txt_services = (TextView) findViewById(R.id.txt_services);
    TextView txt_features = (TextView) findViewById(R.id.txt_features);

    String[] permissions = packageHunter.getPermissionForPkg(packageName);
    String[] activities = packageHunter.getActivitiesForPkg(packageName);
    String[] services = packageHunter.getServicesForPkg(packageName);
    String[] configurations = packageHunter.getConfigurationsForPkg(packageName);
    String[] providers = packageHunter.getProvidersForPkg(packageName);
    String[] receivers = packageHunter.getReceiverForPkg(packageName);
    String[] features = packageHunter.getFeaturesForPkg(packageName);

    String version = packageHunter.getVersionForPkg(packageName);
    String versionCode = packageHunter.getVersionCodeForPkg(packageName);
    String appName = packageHunter.getAppNameForPkg(packageName);
    long firstInstallTime = packageHunter.getFirstInstallTimeForPkg(packageName);
    long lastUpdateTime = packageHunter.getLastUpdatedTimeForPkg(packageName);
    Drawable icon = packageHunter.getIconForPkg(packageName);

    img_pkginfo_icon.setImageDrawable(icon);
    txt_pkginfo_version.setText(version);
    txt_pkginfo_versioncode.setText(versionCode);
    txt_pkginfo_appname.setText(appName);
    txt_pkginfo_pkg.setText(packageName);

    if (permissions != null) {
      txt_permissions.setText(getStringFromArray(permissions));
    } else {
      txt_permissions.setVisibility(View.GONE);
    }

    if (services != null) {
      txt_services.setText(getStringFromArray(services));
    } else {
      txt_services.setVisibility(View.GONE);
    }

    if (features != null) {
      txt_features.setText(getStringFromArray(features));
    } else {
      txt_features.setVisibility(View.GONE);
    }
  }

  private String getStringFromArray(String[] stringArray) {
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < stringArray.length; i++) {
      stringBuilder.append(stringArray[i]).append("\n");
    }
    return stringBuilder.toString();
  }
}
