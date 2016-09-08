package github.nisrulz.projectpackagehunter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_about);
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();

    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    finish();
  }
}
