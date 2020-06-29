package com.example.data_santri;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.data_santri.R;
import com.example.data_santri.ListS;
import com.example.data_santri.Update;
import com.example.data_santri.Constant;
import com.example.data_santri.DatabaseS;
import com.example.data_santri.Model;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;



public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private final Context context;
    private final List<Model> kelasModelList;
    private DatabaseS santriDatabase;
    private Bundle bundle;

    public Adapter(Context context, List<Model> kelasModelList) {
        this.context = context;
        this.kelasModelList = kelasModelList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        // Memindahkan data di dalam list dengan index position ke dalam KelasModel
        final Model kelasModel = kelasModelList.get(position);

        holder.tvNamaWali.setText(kelasModel.getNama_wali());
        holder.tvNamaKelas.setText(kelasModel.getNama_kelas());
        holder.tvNoWali.setText(kelasModel.getNo_wali());
        holder.tvAlamatWali.setText(kelasModel.getAlamat_wali());

        ColorGenerator generator = ColorGenerator.MATERIAL;

        // generate random color
        int color = generator.getRandomColor();
        holder.cvKelas.setCardBackgroundColor(color);

        // Onlick pada itemview
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle = new Bundle();

                bundle.putInt(Constant.KEY_ID_KELAS, kelasModel.getId_kelas());
                context.startActivity(new Intent(context, ListS.class).putExtras(bundle));
            }
        });

        // Membuat onclick icon overflow
        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                santriDatabase = santriDatabase.createDatabase(context);

                PopupMenu popupMenu = new PopupMenu(context, view);

                popupMenu.inflate(R.menu.menu);

                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.delete:

                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                                alertDialogBuilder.setMessage("Are you sure delete " + kelasModel.getNama_kelas() + " ?");
                                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        santriDatabase.kelasDao().delete(kelasModel);

                                        kelasModelList.remove(position);

                                        notifyItemRemoved(position);
                                        notifyItemRangeChanged(0, kelasModelList.size());

                                        Toast.makeText(context, "Berhasil dihapus", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });

                                AlertDialog alertDialog = alertDialogBuilder.create();
                                alertDialog.show();

                                break;

                            case R.id.edit:

                                // Membuat object bundle
                                bundle = new Bundle();

                                // Mengisi bundle dengan data
                                bundle.putInt(Constant.KEY_ID_KELAS, kelasModel.getId_kelas());
                                bundle.putString(Constant.KEY_NAMA_KELAS, kelasModel.getNama_kelas());
                                bundle.putString(Constant.KEY_NAMA_WALI, kelasModel.getNama_wali());
                                bundle.putString(Constant.KEY_NO_WALI, kelasModel.getNo_wali());
                                bundle.putString(Constant.KEY_ALAMAT_WALI, kelasModel.getAlamat_wali());

                                // Berpindah halaman dengan membawa data
                                context.startActivity(new Intent(context, Update.class).putExtras(bundle));
                                break;
                        }
                        return true;
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return kelasModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvNamaKelas)
        TextView tvNamaKelas;
        @BindView(R.id.tvNamaWali)
        TextView tvNamaWali;
        @BindView(R.id.tvNoWali)
        TextView tvNoWali;
        @BindView(R.id.tvAlamatWali)
        TextView tvAlamatWali;
        @BindView(R.id.cvKelas)
        CardView cvKelas;
        @BindView(R.id.overflow)
        ImageButton overflow;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
