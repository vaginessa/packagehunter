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
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class PackageHunter {

  private static final String TAG = "PackageHunter";

  private final PackageManager packageManager;
  private List<PackageInfo> installed_packages_list;

  // Flags
  public static final int APPLICATIONS = 0;
  public static final int PACKAGES = 1;
  public static final int PERMISSIONS = 2;
  public static final int SERVICES = 3;
  public static final int RECEIVERS = 4;

  public PackageHunter(Context context) {
    packageManager = context.getPackageManager();
  }

  public ArrayList<PkgInfo> getInstalledPackages() {
    return getAllPackageInfo(PACKAGES);
  }

  private ArrayList<PkgInfo> getAllPackageInfo(int flag) {
    ArrayList<PkgInfo> pkgInfoArrayList = new ArrayList<>();
    switch (flag) {
      case PACKAGES:
        installed_packages_list = packageManager.getInstalledPackages(0);
        break;
      case PERMISSIONS:
        installed_packages_list =
            packageManager.getInstalledPackages(PackageManager.GET_PERMISSIONS);
        break;
      case SERVICES:
        installed_packages_list = packageManager.getInstalledPackages(PackageManager.GET_SERVICES);
        break;
      case RECEIVERS:
        installed_packages_list = packageManager.getInstalledPackages(PackageManager.GET_RECEIVERS);
        break;
    }

    //get a list of installed packages.
    for (int i = 0; i < installed_packages_list.size(); i++) {
      PackageInfo packageInfo = installed_packages_list.get(i);
      if (!packageInfo.packageName.contains("com.android.")) {
        pkgInfoArrayList.add(getPkgInfoModel(packageInfo));
      }
    }
    return pkgInfoArrayList;
  }

  public ArrayList<PkgInfo> searchInList(String query, int flag) {
    String query_lowercase = query.toLowerCase();
    ArrayList<PkgInfo> pkgInfoArrayList = new ArrayList<>();
    getAllPackageInfo(flag);

    String constant_tocheck = null;
    PackageItemInfo[] packageItemInfoArray = null;

    for (int i = 0; i < installed_packages_list.size(); i++) {
      PackageInfo packageInfo = installed_packages_list.get(i);

      switch (flag) {
        case APPLICATIONS:
          constant_tocheck = packageInfo.applicationInfo.loadLabel(packageManager).toString();
          break;
        case PACKAGES:
          constant_tocheck = packageInfo.packageName;
          break;
        case PERMISSIONS:
          packageItemInfoArray = packageInfo.permissions;
          break;
        case SERVICES:
          packageItemInfoArray = packageInfo.services;
          break;
        case RECEIVERS:
          packageItemInfoArray = packageInfo.receivers;
          break;
      }

      if (packageItemInfoArray != null) {
        PackageItemInfo packageItemInfo = null;
        for (int j = 0; j < packageItemInfoArray.length; j++) {
          switch (flag) {
            case PERMISSIONS:
              packageItemInfo = installed_packages_list.get(i).permissions[j];
              break;
            case SERVICES:
              packageItemInfo = installed_packages_list.get(i).services[j];
              break;
            case RECEIVERS:
              packageItemInfo = installed_packages_list.get(i).receivers[j];
              break;
          }

          if (packageItemInfo != null && packageItemInfo.name.toLowerCase().contains(query)) {
            pkgInfoArrayList.add(getPkgInfoModel(packageInfo, packageItemInfo));
          }
        }
      }

      if (constant_tocheck != null && constant_tocheck.toLowerCase().contains(query_lowercase)) {
        pkgInfoArrayList.add(getPkgInfoModel(packageInfo));
      }
    }

    return pkgInfoArrayList;
  }

  private PkgInfo getPkgInfoModel(PackageInfo p) {
    PkgInfo newInfo = new PkgInfo();
    newInfo.setApp_name(p.applicationInfo.loadLabel(packageManager).toString());
    newInfo.setPkg_name(p.packageName);
    newInfo.setVersionCode(p.versionCode);
    newInfo.setVersionName(p.versionName);
    newInfo.setIcon(p.applicationInfo.loadIcon(packageManager));

    // Log Data
    Log.d(TAG, newInfo.toString());

    return newInfo;
  }

  private PkgInfo getPkgInfoModel(PackageInfo p, PackageItemInfo packageItemInfo) {
    PkgInfo newInfo = new PkgInfo();

    ApplicationInfo ai;
    try {
      ai = packageManager.getApplicationInfo(p.packageName, 0);
    } catch (final PackageManager.NameNotFoundException e) {
      ai = null;
    }
    final String applicationName =
        (String) (ai != null ? packageManager.getApplicationLabel(ai) : "(unknown)");

    newInfo.setApp_name(applicationName);
    newInfo.setPkg_name(packageItemInfo.packageName);
    newInfo.setVersionCode(p.versionCode);
    newInfo.setVersionName(p.versionName);
    newInfo.setIcon(p.applicationInfo.loadIcon(packageManager));

    // Log Data
    Log.d(TAG, newInfo.toString());

    return newInfo;
  }
}