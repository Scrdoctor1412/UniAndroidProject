package phdhtl.k63cntt1.nguyen.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import phdhtl.k63cntt1.nguyen.R;
import phdhtl.k63cntt1.nguyen.helper.ConvertHelper;
import phdhtl.k63cntt1.nguyen.model.ChapterImage;

public class CustomChapterImageAdapter extends ArrayAdapter<ChapterImage> {
    private Activity context;
    private int idLayout;
    private ArrayList<ChapterImage> arrayList;

    public CustomChapterImageAdapter(Activity context, int idLayout, ArrayList<ChapterImage> arrayList) {
        super(context, idLayout, arrayList);
        this.context = context;
        this.idLayout = idLayout;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(idLayout, null);
        ChapterImage mychapterimg = arrayList.get(position);

        ImageView img = convertView.findViewById(R.id.imgchapter);
        img.setImageBitmap(ConvertHelper.StringToBitMap(mychapterimg.getHinhanh()));
        return convertView;
    }
}
