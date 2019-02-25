package com.example.linan;


        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import java.util.List;

/**
 * 换乘列表的适配器，服务于该列表
 */
public class HuanChengAdapter extends RecyclerView.Adapter<HuanChengAdapter.ViewHolder>{
    private List<huancheng> mhuanchenglist;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView fangan;
        TextView sstop;
        TextView midstop;
        TextView routefir;
        TextView routesec;
        TextView stopcount;

        public ViewHolder(View view){
            super(view);
            fangan = (TextView) view.findViewById(R.id.tv_hci_1);
            sstop = (TextView) view.findViewById(R.id.tv_hci_sstop);
            midstop = (TextView) view.findViewById(R.id.tv_hci_midstop);
            routefir = (TextView) view.findViewById(R.id.tv_hci_routefir);
            routesec = (TextView) view.findViewById(R.id.tv_hci_routersec);
            stopcount = (TextView) view.findViewById(R.id.tv_hci_stopcount);
        }
    }
    public HuanChengAdapter(List<huancheng> huanchengList){
        mhuanchenglist = huanchengList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.huancheng_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        huancheng huancheng = mhuanchenglist.get(position);
        holder.sstop.setText(huancheng.getStartstop());
        holder.midstop.setText(huancheng.getMidstop());
        holder.routefir.setText(huancheng.getRoutefir());
        holder.routesec.setText(huancheng.getRoutersec());
        holder.stopcount.setText("共"+String.valueOf(huancheng.getStopcount())+"站");
    }

    @Override
    public int getItemCount() {
        return mhuanchenglist.size();
    }
}
