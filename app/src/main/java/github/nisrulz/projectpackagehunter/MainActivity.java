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
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import github.nisrulz.packagehunter.PackageHunter;
import github.nisrulz.packagehunter.PkgInfo;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> data;
    private ArrayAdapter<String> adapter;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lv = (ListView) findViewById(R.id.listview);
        data = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        lv.setAdapter(adapter);

        final EditText editText = (EditText) findViewById(R.id.editText);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editText.getText().toString().equals("")) {
                    data = getStringList(editText.getText().toString());
                    // update data in our adapter
                    adapter.clear();
                    adapter.addAll(data);
                    // fire the event
                    adapter.notifyDataSetChanged();
                } else {
                    Snackbar.make(lv, "Search Query EMPTY!", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    private ArrayList<String> getStringList(String querytext) {
        ArrayList<PkgInfo> pkglist = PackageHunter.getInstance().getListOfPackages(getApplicationContext(), querytext);
        ArrayList<String> dataStringlist = new ArrayList<>();
        for (PkgInfo pkgInfo : pkglist) {
            dataStringlist.add("App Name : " + pkgInfo.getApp_name() + "\nPackage Name : " + pkgInfo
                    .getPkg_name() + "\nApp Name : " + pkgInfo.getService_name());
        }

        return dataStringlist;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_clear:
                adapter.clear();
                adapter.notifyDataSetChanged();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
