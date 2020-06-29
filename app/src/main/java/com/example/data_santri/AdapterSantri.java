package com.example.data_santri;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterSantri extends RecyclerView.Adapter<AdapterSantri.ViewHolder> {

    private final Context context;
    private List<ModelSantri> santriModelList;
    private Bundle bundle;
    private String firstName;
    private DatabaseS santriDatabaseS;

    public AdapterSantri(Context context, List<ModelSantri> santriModelList) {
        this.context = context;
        this.santriModelList = santriModelList;
    }

    public AdapterSantri(ListS listS, Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.itemsantri, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        // memindahkan data yang dipilih ke dalam list
        final ModelSantri santriModel = santriModelList.get(position);

        // Menampilkan data ke layar
        holder.txtNameSiswa.setText(santriModel.getNama_siswa());

        // Mengambil huruf pertama
        String nama = santriModel.getNama_siswa();
        if (!nama.isEmpty()) {
            firstName = nama.substring(0, 1);
        } else {
            firstName = " ";
        }

        ColorGenerator generator = ColorGenerator.MATERIAL;
        // generate random color
        int color = generator.getRandomColor();

        TextDrawable drawable = TextDrawable.builder().buildRound(firstName, color);
        holder.imgView.setImageDrawable(drawable);

        // Membuat onclick icon overflow
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                // Buat object database
                santriDatabaseS = santriDatabaseS.createDatabase(context);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setMessage("Are you sure delete " + santriModel.getNama_siswa() + " ?");
                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        // Melakukan operasi delete data
                        santriDatabaseS.kelasDao().deleteSiswa(santriModel);

                        // Menghapus data yang telash di hapus pada ListS
                        santriModelList.remove(position);

                        // Memberitahu bahwa data sudah hilang
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(0, santriModelList.size());

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

            }
        });

    }

    @Override
    public int getItemCount() {
        return santriModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_view)
        ImageView imgView;
        @BindView(R.id.txt_name_siswa)
        TextView txtNameSiswa;
        @BindView(R.id.btnDelete)
        ImageButton btnDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
