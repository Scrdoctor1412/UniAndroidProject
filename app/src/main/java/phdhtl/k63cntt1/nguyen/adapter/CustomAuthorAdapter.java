package phdhtl.k63cntt1.nguyen.adapter;

import static androidx.core.app.ActivityCompat.startActivityForResult;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import phdhtl.k63cntt1.nguyen.R;
import phdhtl.k63cntt1.nguyen.detailsActivity.AuthorDetailsActivity;
import phdhtl.k63cntt1.nguyen.helper.ConvertHelper;
import phdhtl.k63cntt1.nguyen.helper.DBHelper;
import phdhtl.k63cntt1.nguyen.model.Author;

public class CustomAuthorAdapter extends ArrayAdapter<Author> {
    private final Activity context;
    private ArrayList<Author> authorArrayList;
    private int idLayout;

    DBHelper dbh;
    public CustomAuthorAdapter(Activity context, int idLayout , ArrayList<Author> authorArrayList) {
        super(context, idLayout, authorArrayList);
        this.context = context;
        this.authorArrayList = authorArrayList;
        this.idLayout = idLayout;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(idLayout,null);
        Author author = authorArrayList.get(position);


        TextView txtgth = convertView.findViewById(R.id.txtgthtg);
        TextView txtmatg = convertView.findViewById(R.id.txtmatg);
        TextView txttentg = convertView.findViewById(R.id.txttentg);

        Button btnUpdate = convertView.findViewById(R.id.btn_update_author);
        Button btnDelete = convertView.findViewById(R.id.btn_delete_author);

        ImageView ivAvatar = convertView.findViewById(R.id.iv_avatar_author);
        if(author.getImgdaidien()!=""){
            ivAvatar.setImageBitmap(ConvertHelper.StringToBitMap(author.getImgdaidien()));
        }else{
            ivAvatar.setImageResource(R.drawable.author);
        }



        txtgth.setText(author.getIntroducing());
        txtmatg.setText(author.getId());
        txttentg.setText(author.getName());

        dbh = new DBHelper(context);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authorArrayList.remove(position);
                SQLiteDatabase db = dbh.getWritableDatabase();
                int n = db.delete("authors", "matacgia = ?", new String[]{author.getId()});
                String msg = "";
                if(n == 0){
                    msg = "No record was deleted!";
                }else{
                    msg = n + "record was deleted";
                }
                Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
                CustomAuthorAdapter.this.notifyDataSetChanged();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context, AuthorDetailsActivity.class);
                myIntent.putExtra("EventCode",2);
                myIntent.putExtra("matg",author.getId());
                myIntent.putExtra("tentg",author.getName());
                myIntent.putExtra("gthtg",author.getIntroducing());
                myIntent.putExtra("imgtg",author.getImgdaidien());

                startActivityForResult(context, myIntent, 99,null);
            }
        });

        return convertView;
    }
}
