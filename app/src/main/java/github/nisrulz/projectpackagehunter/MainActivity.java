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
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import github.nisrulz.packagehunter.PackageHunter;
import github.nisrulz.packagehunter.PkgInfo;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

  private RecyclerView rv;
  private ArrayList<PkgInfo> pkgInfoArrayList;
  private RVAdapter adapter;

  private PackageHunter packageHunter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    packageHunter = new PackageHunter(this);

    rv = (RecyclerView) findViewById(R.id.rv_pkglist);
    pkgInfoArrayList = packageHunter.getInstalledPackages();

    adapter = new RVAdapter(pkgInfoArrayList);
    rv.hasFixedSize();
    rv.setLayoutManager(new LinearLayoutManager(this));
    rv.setAdapter(adapter);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_main, menu);
    MenuItem searchViewItem = menu.findItem(R.id.action_search);
    final SearchView searchViewAndroidActionBar =
        (SearchView) MenuItemCompat.getActionView(searchViewItem);
    searchViewAndroidActionBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override public boolean onQueryTextSubmit(String query) {
        searchViewAndroidActionBar.clearFocus();
        pkgInfoArrayList = packageHunter.searchInList(query, PackageHunter.APPLICATIONS);
        adapter.updateWithNewListData(pkgInfoArrayList);
        return true;
      }

      @Override public boolean onQueryTextChange(String query) {
        pkgInfoArrayList = packageHunter.searchInList(query, PackageHunter.SERVICES);
        adapter.updateWithNewListData(pkgInfoArrayList);
        return false;
      }
    });
    return super.onCreateOptionsMenu(menu);
  }
}
