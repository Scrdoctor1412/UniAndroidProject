package phdhtl.k63cntt1.nguyen.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import phdhtl.k63cntt1.nguyen.R;
import phdhtl.k63cntt1.nguyen.helper.ConvertHelper;
import phdhtl.k63cntt1.nguyen.model.User;

public class UserAdapter extends ArrayAdapter<User> {
    private final Activity context;
    private ArrayList<User> userArrayList;

    private int idLayout;

    public UserAdapter(@NonNull Activity context,int idLayout,ArrayList<User> userArrayList) {
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
        Button btnUpdate = convertView.findViewById(R.id.btn_update_user);
        Button btnDelete = convertView.findViewById(R.id.btn_delete_user);
        TextView txtusername = convertView.findViewById(R.id.txtusrname);
        TextView txtemail = convertView.findViewById(R.id.txtemail);

        txtemail.setText(user.getEmail());
        txtusername.setText(user.getEmail());

        if(user.getImgdaidien() != ""){
            ivAvatar.setImageBitmap(ConvertHelper.StringToBitMap(user.getImgdaidien()));
        }else{
            ivAvatar.setImageResource(R.drawable.dummy_avatar);
        }
        return convertView;
    }
}
