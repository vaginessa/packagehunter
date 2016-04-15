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

import java.util.ArrayList;
import java.util.List;

public class PackageHunter {


    private static final PackageHunter ourInstance = new PackageHunter();

    public static PackageHunter getInstance() {
        return ourInstance;
    }

    private PackageHunter() {
    }


    public ArrayList<PkgInfo> getListOfPackages(Context context, String packageName) {
        ArrayList<PkgInfo> list = new ArrayList<>();

        final PackageManager pm = context.getPackageManager();

        //get a list of installed packages.
        List<PackageInfo> serviceInfos = pm.getInstalledPackages(PackageManager.GET_SERVICES);
        for (PackageInfo serviceInfo : serviceInfos) {
            PkgInfo pkgInfo = new PkgInfo();
            if (serviceInfo.services != null) {
                for (ServiceInfo s : serviceInfo.services) {
                    if (s.name.contains(packageName)) {
                        ApplicationInfo ai;
                        try {
                            ai = pm.getApplicationInfo(s.packageName, 0);
                        } catch (final PackageManager.NameNotFoundException e) {
                            ai = null;
                        }
                        final String applicationName = (String) (ai != null ? pm.getApplicationLabel(ai) : "(unknown)");

                        pkgInfo.setApp_name(applicationName);
                        pkgInfo.setPkg_name(s.packageName);
                        pkgInfo.setService_name(s.name);

                        if (!list.contains(pkgInfo))
                            list.add(pkgInfo);
                    }
                }
            }
        }

        return list;

    }
}
