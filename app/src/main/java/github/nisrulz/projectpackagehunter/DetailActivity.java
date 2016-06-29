package github.nisrulz.projectpackagehunter;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import github.nisrulz.packagehunter.PackageHunter;
import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

  private RecyclerView rv;
  private ArrayList<ElementInfo> elementInfoArrayList;
  private RVDetailsAdapter adapter;

  private Toolbar toolbar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);

    toolbar = (Toolbar) findViewById(R.id.toolbar);
    if (toolbar != null) {
      setSupportActionBar(toolbar);
    }

    String packageName = getIntent().getStringExtra("data");
    PackageHunter packageHunter = new PackageHunter(this);

    String version = packageHunter.getVersionForPkg(packageName);
    String versionCode = packageHunter.getVersionCodeForPkg(packageName);
    String appName = packageHunter.getAppNameForPkg(packageName);
    long firstInstallTime = packageHunter.getFirstInstallTimeForPkg(packageName);
    long lastUpdateTime = packageHunter.getLastUpdatedTimeForPkg(packageName);
    Drawable icon = packageHunter.getIconForPkg(packageName);

    String[] permissions = packageHunter.getPermissionForPkg(packageName);
    String[] activities = packageHunter.getActivitiesForPkg(packageName);
    String[] services = packageHunter.getServicesForPkg(packageName);
    String[] configurations = packageHunter.getConfigurationsForPkg(packageName);
    String[] providers = packageHunter.getProvidersForPkg(packageName);
    String[] receivers = packageHunter.getReceiverForPkg(packageName);
    String[] features = packageHunter.getFeaturesForPkg(packageName);

    TextView txt_version = (TextView) findViewById(R.id.txtvw_vname);
    TextView txt_versioncode = (TextView) findViewById(R.id.txtvw_vc);
    TextView txt_appname = (TextView) findViewById(R.id.txtvw_appname);
    TextView txt_pkg = (TextView) findViewById(R.id.txtvw_pkgname);
    ImageView img_icon = (ImageView) findViewById(R.id.imgvw_icn);

    TextView txt_firsttime = (TextView) findViewById(R.id.txtvw_firsttime);
    TextView txt_lastupdated = (TextView) findViewById(R.id.txtvw_lastupdated);

    img_icon.setImageDrawable(icon);
    txt_version.setText("Version : " + version);
    txt_versioncode.setText("Version Code : " + versionCode);
    txt_pkg.setText(packageName);
    txt_firsttime.setText(String.valueOf(firstInstallTime));
    txt_lastupdated.setText(String.valueOf(lastUpdateTime));

    getSupportActionBar().setTitle(appName);

    rv = (RecyclerView) findViewById(R.id.rv_detaillist);
    elementInfoArrayList = new ArrayList<>();
    elementInfoArrayList.add(new ElementInfo("Permissions", permissions));
    elementInfoArrayList.add(new ElementInfo("Services", services));
    elementInfoArrayList.add(new ElementInfo("Activities", activities));
    elementInfoArrayList.add(new ElementInfo("Configurations", configurations));
    elementInfoArrayList.add(new ElementInfo("Providers", providers));
    elementInfoArrayList.add(new ElementInfo("Receivers", receivers));
    elementInfoArrayList.add(new ElementInfo("Features", features));

    adapter = new RVDetailsAdapter(elementInfoArrayList);
    rv.setHasFixedSize(true);
    rv.setLayoutManager(new LinearLayoutManager(this));
    rv.setAdapter(adapter);
  }
}
