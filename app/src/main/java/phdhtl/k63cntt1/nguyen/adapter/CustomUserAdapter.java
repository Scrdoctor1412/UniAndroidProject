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
import phdhtl.k63cntt1.nguyen.detailsActivity.UserDetailsActivity;
import phdhtl.k63cntt1.nguyen.helper.ConvertHelper;
import phdhtl.k63cntt1.nguyen.helper.DBHelper;
import phdhtl.k63cntt1.nguyen.model.User;

public class CustomUserAdapter extends ArrayAdapter<User> {
    private final Activity context;
    private ArrayList<User> userArrayList;

    private int idLayout;

    DBHelper dbh;

    public CustomUserAdapter(@NonNull Activity context, int idLayout, ArrayList<User> userArrayList) {
        super(context, idLayout, userArrayList);
        this.context = context;
        this.userArrayList = userArrayList;
        this.idLayout = idLayout;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(idLayout, null);
        User user = userArrayList.get(position);

        ImageView ivAvatar = convertView.findViewById(R.id.iv_avatar_user);

        TextView txtusername = convertView.findViewById(R.id.txtusername);
        TextView txtemail = convertView.findViewById(R.id.txtemail);
        TextView txtlevel = convertView.findViewById(R.id.txtleveluser);

        Button btnUpdate = convertView.findViewById(R.id.btn_update_user);
        Button btnDelete = convertView.findViewById(R.id.btn_delete_user);

        txtemail.setText(user.getEmail());
        txtusername.setText(user.getUserName());
        txtlevel.setText(user.getLevel() == 1 ? "Admin" : "Client");

        if(user.getImgdaidien() != ""){
            ivAvatar.setImageBitmap(ConvertHelper.StringToBitMap(user.getImgdaidien()));
        }else{
            ivAvatar.setImageResource(R.drawable.dummy_avatar);
        }

        dbh = new DBHelper(context);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userArrayList.remove(position);
                SQLiteDatabase db = dbh.getWritableDatabase();
                int n = db.delete("users", "mauser = ?", new String[]{user.getId()});
                String msg = "";
                if(n == 0){
                    msg = "No record was deleted!";
                }else{
                    msg = n + " record was deleted";
                }
                Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
                CustomUserAdapter.this.notifyDataSetChanged();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context, UserDetailsActivity.class);
                myIntent.putExtra("EventCode",2);
                myIntent.putExtra("mauser", user.getId());
                myIntent.putExtra("username", user.getUserName());
                myIntent.putExtra("email", user.getEmail());
                myIntent.putExtra("password", user.getPassWord());
                myIntent.putExtra("imgdaidien", user.getImgdaidien());
                myIntent.putExtra("level", user.getLevel());
                startActivityForResult(context, myIntent, 99,null);

            }
        });
        return convertView;
    }
}
