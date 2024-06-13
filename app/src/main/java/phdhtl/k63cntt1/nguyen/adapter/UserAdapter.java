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
import phdhtl.k63cntt1.nguyen.model.User;

public class UserAdapter extends ArrayAdapter<User> {
    private final Activity context;
    private ArrayList<User> userArrayList;

    public UserAdapter(@NonNull Activity context,  ArrayList<User> userArrayList) {
        super(context, R.layout.custom_user_row, userArrayList);
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View row = inflater.inflate(R.layout.custom_user_row, null, true);
        User user = userArrayList.get(position);
        TextView tvUsername = row.findViewById(R.id.tv_username);
        TextView tvEmail = row.findViewById(R.id.tv_email);
        ImageView ivAvatar = row.findViewById(R.id.iv_avatar_user);
        Button btnUpdate = row.findViewById(R.id.btn_update_user);
        Button btnDelete = row.findViewById(R.id.btn_delete_user);

        tvUsername.setText(user.getUserName());
        tvEmail.setText(user.getEmail());
        ivAvatar.setImageResource(R.drawable.dummy_avatar);
        return row;
    }
}
