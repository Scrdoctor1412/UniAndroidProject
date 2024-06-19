package phdhtl.k63cntt1.nguyen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import phdhtl.k63cntt1.nguyen.R;
import phdhtl.k63cntt1.nguyen.model.ListItem;

public class CustomTheLoaiMultiSpinner extends ArrayAdapter<ListItem> {
    private final List<ListItem> items;
    private final List<ListItem> selectedItems;
    private final boolean[] checkedItems;
    private OnItemSelectedListener onItemSelectedListener;

    public interface OnItemSelectedListener {
        void onItemSelected(List<ListItem> selectedItems, int pos);
    }

    public CustomTheLoaiMultiSpinner(Context context, List<ListItem> items, List<ListItem> selectedItems) {
        super(context, 0, items);
        this.items = items;
        this.selectedItems = selectedItems;
        this.checkedItems = new boolean[items.size()];

        for (int i = 0; i < items.size(); i++) {
            checkedItems[i] = selectedItems.contains(items.get(i));
        }
    }

    public void setOnItemSelectedListener(OnItemSelectedListener listener) {
        this.onItemSelectedListener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return createView(position, convertView, parent, false);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return createView(position, convertView, parent, true);
    }

    private View createView(int position, View convertView, ViewGroup parent, boolean isDropDown) {
        View view = convertView != null ? convertView : LayoutInflater.from(getContext()).inflate(isDropDown ? R.layout.custom_spinner_dropdown_item : R.layout.custom_non_select_dropdown, parent, false);

        TextView textView = view.findViewById(R.id.spin_txt);

        if (isDropDown) {
            CheckBox checkBox = view.findViewById(R.id.spinnerCheckbox);
            TextView itemName = view.findViewById(R.id.itemName);

            itemName.setText(items.get(position).getType().getTentl());
            checkBox.setOnCheckedChangeListener((compoundButton, isChecked) -> {
                checkedItems[position] = isChecked;
                if (isChecked) {
                    if (!selectedItems.contains(items.get(position))) {
                        selectedItems.add(items.get(position));
                    }
                } else {
                    selectedItems.remove(items.get(position));
                }
                notifyDataSetChanged();
                if (onItemSelectedListener != null) {
                    onItemSelectedListener.onItemSelected(selectedItems, position);
                }
            });
        } else {
            if (selectedItems.isEmpty()) {
                textView.setText("Select");
            } else {
                ArrayList<String> names = new ArrayList<>();
                for (ListItem selectedItem : getSelectedItems()) {
                    names.add(selectedItem.getType().getTentl());
                }
                textView.setText(names.toString().replace("[", "").replace("]", ""));
            }
        }

        return view;
    }

    private List<ListItem> getSelectedItems() {
        return selectedItems;
    }
}
