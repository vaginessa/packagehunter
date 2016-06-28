package github.nisrulz.projectpackagehunter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import github.nisrulz.packagehunter.PkgInfo;
import java.util.List;

class RVAdapter extends RecyclerView.Adapter<RVAdapter.ItemViewHolder> {

  private List<PkgInfo> dataList;

  public RVAdapter(List<PkgInfo> dataList) {
    this.dataList = dataList;
  }

  @Override public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);

    return new ItemViewHolder(view);
  }

  @Override public void onBindViewHolder(ItemViewHolder holder, int position) {
    holder.txt_appname.setText(dataList.get(position).getAppName());
    holder.txt_pkgname.setText(dataList.get(position).getPackageName());
    holder.txt_versioncode.setText(String.valueOf(dataList.get(position).getVersionCode()));
    holder.txt_version.setText(dataList.get(position).getVersionName());
    holder.icon.setImageDrawable(dataList.get(position).getIcon());
  }

  @Override public int getItemCount() {
    return dataList.size();
  }

  public class ItemViewHolder extends RecyclerView.ViewHolder {
    final TextView txt_appname;
    final TextView txt_pkgname;
    final TextView txt_versioncode;
    final TextView txt_version;
    final ImageView icon;

    public ItemViewHolder(View itemView) {
      super(itemView);
      txt_appname = (TextView) itemView.findViewById(R.id.pkginfo_name);
      txt_pkgname = (TextView) itemView.findViewById(R.id.pkginfo_pkg);
      txt_version = (TextView) itemView.findViewById(R.id.pkginfo_vname);
      txt_versioncode = (TextView) itemView.findViewById(R.id.pkginfo_vc);
      icon = (ImageView) itemView.findViewById(R.id.pkginfo_icn);
    }
  }

  public void updateWithNewListData(List<PkgInfo> newDataList) {
    dataList.clear();
    dataList = null;
    dataList = newDataList;
    notifyDataSetChanged();
  }
}
