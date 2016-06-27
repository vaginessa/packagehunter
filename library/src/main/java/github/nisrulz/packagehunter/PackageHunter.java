/*
 * Copyright (C) 2016 Nishant Srivastava
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package github.nisrulz.packagehunter;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class PackageHunter {

  private static final String TAG = "PackageHunter";

  private final Context context;
  private final PackageManager pm;

  public PackageHunter(Context context) {
    this.context = context;
    pm = context.getPackageManager();
  }

  public ArrayList<PkgInfo> getInstalledPackages() {
    ArrayList<PkgInfo> pkgInfoArrayList = new ArrayList<>();
    List<PackageInfo> packages = pm.getInstalledPackages(0);

    //get a list of installed packages.
    for (int i = 0; i < packages.size(); i++) {
      PackageInfo p = packages.get(i);
      if (!p.packageName.contains("com.android.")) {
        pkgInfoArrayList.add(getPkgInfoModel(p));
      }
    }
    return pkgInfoArrayList;
  }

  public ArrayList<PkgInfo> searchForPackageName(final String package_name) {
    String query = package_name.toLowerCase();
    ArrayList<PkgInfo> pkgInfoArrayList = new ArrayList<>();
    final PackageManager pm = context.getPackageManager();
    List<PackageInfo> packages = pm.getInstalledPackages(0);

    //get a pkgInfoArrayList of installed packages.
    for (int i = 0; i < packages.size(); i++) {
      PackageInfo p = packages.get(i);

      String pkgeName = p.packageName;
      if (pkgeName.toLowerCase().contains(query)) {
        pkgInfoArrayList.add(getPkgInfoModel(p));
      }
    }
    return pkgInfoArrayList;
  }

  public ArrayList<PkgInfo> searchForApplicationName(final String application_name) {
    String query = application_name.toLowerCase();
    ArrayList<PkgInfo> pkgInfoArrayList = new ArrayList<>();
    final PackageManager pm = context.getPackageManager();
    List<PackageInfo> packages = pm.getInstalledPackages(0);

    //get a list of installed packages.
    for (int i = 0; i < packages.size(); i++) {
      PackageInfo p = packages.get(i);

      String appname = p.applicationInfo.loadLabel(pm).toString();
      if (appname.toLowerCase().contains(query)) {
        pkgInfoArrayList.add(getPkgInfoModel(p));
      }
    }
    return pkgInfoArrayList;
  }

  public ArrayList<PkgInfo> searchForServices(final String service_name) {
    ArrayList<PkgInfo> pkgInfoArrayList = new ArrayList<>();

    final PackageManager pm = context.getPackageManager();

    //get a list of installed packages.
    List<PackageInfo> serviceInfos = pm.getInstalledPackages(PackageManager.GET_SERVICES);
    for (PackageInfo serviceInfo : serviceInfos) {
      PkgInfo pkgInfo = new PkgInfo();
      if (serviceInfo.services != null) {
        for (ServiceInfo s : serviceInfo.services) {
          if (s.name.contains(service_name)) {
            ApplicationInfo ai;
            try {
              ai = pm.getApplicationInfo(s.packageName, 0);
            } catch (final PackageManager.NameNotFoundException e) {
              ai = null;
            }
            final String applicationName =
                (String) (ai != null ? pm.getApplicationLabel(ai) : "(unknown)");

            pkgInfo.setApp_name(applicationName);
            pkgInfo.setPkg_name(s.packageName);
            if (!pkgInfoArrayList.contains(pkgInfo)) pkgInfoArrayList.add(pkgInfo);
          }
        }
      }
    }
    return pkgInfoArrayList;
  }

  private PkgInfo getPkgInfoModel(PackageInfo p) {
    PkgInfo newInfo = new PkgInfo();
    newInfo.setApp_name(p.applicationInfo.loadLabel(pm).toString());
    newInfo.setPkg_name(p.packageName);
    newInfo.setVersionCode(p.versionCode);
    newInfo.setVersionName(p.versionName);
    newInfo.setIcon(p.applicationInfo.loadIcon(pm));

    // Log Data
    Log.d(TAG, newInfo.toString());

    return newInfo;
  }

  //
  //public ArrayList<PkgInfo> searchForAppsWithPermission(final String permission) {
  //  ArrayList<PkgInfo> pkgInfoArrayList = new ArrayList<>();
  //
  //  PackageManager pm = context.getPackageManager();
  //  List packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
  //  for (ApplicationInfo applicationInfo : packages) {
  //    Log.d("test", "App: " + applicationInfo.name + " Package: " + applicationInfo.packageName);
  //    try {
  //      PackageInfo packageInfo = pm.getPackageInfo(applicationInfo.packageName, PackageManager.GET_PERMISSIONS);
  //      //Get Permissions
  //      String[] requestedPermissions = packageInfo.requestedPermissions;
  //      if(requestedPermissions != null) {
  //        for (int i = 0; i < requestedPermissions.length; i++) {
  //          Log.d("test", requestedPermissions[i]);
  //
  //          //////////////////////////////////////
  //          //////////////////////////////////////
  //          // Look for the desired permission here
  //          //////////////////////////////////////
  //          //////////////////////////////////////
  //        }
  //      }
  //    } catch (PackageManager.NameNotFoundException e) {
  //      e.printStackTrace();
  //    }
  //  }
  //  return pkgInfoArrayList;
  //}
}
