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

import android.graphics.drawable.Drawable;

public class PkgInfo {
  private String app_name;
  private String pkg_name;
  private String versionName = "0.0";
  private int versionCode = 0;
  private Drawable icon;

  public PkgInfo() {
  }

  public String getApp_name() {
    return app_name;
  }

  public void setApp_name(String app_name) {
    this.app_name = app_name;
  }

  public String getPkg_name() {
    return pkg_name;
  }

  public void setPkg_name(String pkg_name) {
    this.pkg_name = pkg_name;
  }

  public Drawable getIcon() {
    return icon;
  }

  public void setIcon(Drawable icon) {
    this.icon = icon;
  }

  public int getVersionCode() {
    return versionCode;
  }

  public void setVersionCode(int versionCode) {
    this.versionCode = versionCode;
  }

  public String getVersionName() {
    return versionName;
  }

  public void setVersionName(String versionName) {
    this.versionName = versionName;
  }

  @Override public String toString() {
    super.toString();
    return "AppName : "
        + app_name
        + " | PackageName :"
        + pkg_name
        + "\nVersion :"
        + versionName
        + " | VersionCode :"
        + versionCode;
  }
}

