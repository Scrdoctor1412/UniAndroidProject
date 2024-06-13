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
import java.util.Collection;

import phdhtl.k63cntt1.nguyen.R;
import phdhtl.k63cntt1.nguyen.model.Author;
import phdhtl.k63cntt1.nguyen.model.User;

public class AuthorAdapter extends ArrayAdapter<Author> {
    private final Activity context;
    private ArrayList<Author> authorArrayList;

    public AuthorAdapter(Activity context, ArrayList<Author> authorArrayList) {
        super(context, R.layout.custom_author_row, authorArrayList);
        this.context = context;
        this.authorArrayList = authorArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View row = inflater.inflate(R.layout.custom_author_row, null, true);
        Author author = authorArrayList.get(position);
        TextView tvAuthorName = row.findViewById(R.id.tv_author_name);
        ImageView ivAvatar = row.findViewById(R.id.iv_avatar_author);
        Button btnUpdate = row.findViewById(R.id.btn_update_author);
        Button btnDelete = row.findViewById(R.id.btn_delete_author);

        tvAuthorName.setText(author.getName());
        ivAvatar.setImageResource(R.drawable.dummy_avatar);
        return row;
    }
}
