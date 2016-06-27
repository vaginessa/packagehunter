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

package github.nisrulz.projectpackagehunter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import github.nisrulz.packagehunter.PackageHunter;
import github.nisrulz.packagehunter.PkgInfo;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

  RecyclerView rv;
  ArrayList<PkgInfo> data;
  RVAdapter adapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    PackageHunter packageHunter = new PackageHunter(this);

    rv = (RecyclerView) findViewById(R.id.rv_pkglist);
    data = packageHunter.getInstalledPackages();

    adapter = new RVAdapter(data);
    rv.hasFixedSize();
    rv.setLayoutManager(new LinearLayoutManager(this));
    rv.setAdapter(adapter);
  }
}
