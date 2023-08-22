package com.indev.ruraldevelopment.Activity.searchablespinnerlibrary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.indev.ruraldevelopment.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ListAdapterSpinner extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    private List<mListString> worldpopulationlist = null;
    private ArrayList<mListString> arraylist;
    int type;


    public ListAdapterSpinner(Context context, List<mListString> worldpopulationlist, int type, String firstItem) {
        mContext = context;
        ArrayList<mListString> temp = new ArrayList<>();
        if (!TextUtils.isEmpty(firstItem)) {
            temp.add(new mListString(0, firstItem));
            temp.addAll(worldpopulationlist);
            this.worldpopulationlist = temp;
        } else {
            this.worldpopulationlist = worldpopulationlist;
        }
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<>();
        this.arraylist.addAll(worldpopulationlist);
        this.type = type;

    }


    public int indexOf(mListString objects) {
        return arraylist.indexOf(objects);
    }

    public int indexOf(int index) {
        return index;
    }

    @Override
    public int getCount() {
        return worldpopulationlist.size();
    }

    @Override
    public mListString getItem(int position) {
        return worldpopulationlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @SuppressLint("DefaultLocale")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            if (type == 1) {
                v = vi.inflate(R.layout.spinner_item_code_1, null);
            }
            else {
                v = vi.inflate(R.layout.spinner_item_code_1, null);
            }
            holder = new ViewHolder();
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        mListString p = getItem(position);
        if (p != null) {

            if (type == 1) {
                holder.nilai1 = (TextView) v.findViewById(R.id.spinnerHead);
            } else {
                holder.nilai1 = (TextView) v.findViewById(R.id.spinnerHead);
            }

            String val1, val2, val3, val4, valImage;
            val1 = p.getNilai1();
            val2 = p.getNilai2();
            val3 = p.getNilai3();
            val4 = p.getNilai4();
            valImage = p.getImageName();

            if (holder.nilai1 != null) {
                holder.nilai1.setText(val1);
            }
            if (holder.nilai2 != null) {
                holder.nilai2.setText(val2);
            }
            if (holder.nilai3 != null) {
                holder.nilai3.setText(val3);
            }
            if (holder.nilai4 != null) {
                holder.nilai4.setText(val4);
            }

        }


        return v;
    }

    public class ViewHolder {
        public TextView nilai1, nilai2 = null, nilai3 = null, nilai4 = null;
        public ImageView images = null;
    }

    // Filter Class
    public void filter(String charText, int filter) {
        charText = charText.toLowerCase(Locale.getDefault());
        worldpopulationlist.clear();
        if (charText.length() == 0) {
            worldpopulationlist.addAll(arraylist);
        } else {
            if (filter > type) filter = type;
            if (filter == 1) {
                for (mListString wp : arraylist) {
                    String nilai1 = "";
                    if (wp.getNilai1() != null) nilai1 = wp.getNilai1();
                    if (nilai1.toLowerCase(Locale.getDefault()).contains(charText)) {
                        worldpopulationlist.add(wp);
                    }

                }

            } else if (filter == 2) {
                for (mListString wp : arraylist) {
                    String nilai1 = "", nilai2 = "";
                    if (wp.getNilai1() != null) nilai1 = wp.getNilai1();
                    if (wp.getNilai3() != null) nilai2 = wp.getNilai2();
                    if (nilai1.toLowerCase(Locale.getDefault()).contains(charText) ||
                            nilai2.toLowerCase(Locale.getDefault()).contains(charText)
                            ) {
                        worldpopulationlist.add(wp);
                    }

                }

            } else if (filter == 3) {
                for (mListString wp : arraylist) {
                    String nilai1 = "", nilai2 = "", nilai3 = "";
                    if (wp.getNilai1() != null) nilai1 = wp.getNilai1();
                    if (wp.getNilai3() != null) nilai2 = wp.getNilai2();
                    if (wp.getNilai3() != null) nilai3 = wp.getNilai3();
                    if (nilai1.toLowerCase(Locale.getDefault()).contains(charText) ||
                            nilai2.toLowerCase(Locale.getDefault()).contains(charText) ||
                            nilai3.toLowerCase(Locale.getDefault()).contains(charText)
                            ) {
                        worldpopulationlist.add(wp);
                    }

                }

            } else if (filter == 4) {
                for (mListString wp : arraylist) {
                    String nilai1 = "", nilai2 = "", nilai3 = "", nilai4 = "";
                    if (wp.getNilai1() != null) nilai1 = wp.getNilai1();
                    if (wp.getNilai3() != null) nilai2 = wp.getNilai2();
                    if (wp.getNilai3() != null) nilai3 = wp.getNilai3();
                    if (wp.getNilai4() != null) nilai4 = wp.getNilai4();

                    if (nilai1.toLowerCase(Locale.getDefault()).contains(charText) ||
                            nilai2.toLowerCase(Locale.getDefault()).contains(charText) ||
                            nilai3.toLowerCase(Locale.getDefault()).contains(charText) ||
                            nilai4.toLowerCase(Locale.getDefault()).contains(charText)
                            ) {
                        worldpopulationlist.add(wp);
                    }
                }

            } else {
                for (mListString wp : arraylist) {
                    String nilai1 = "";
                    if (wp.getNilai1() != null) nilai1 = wp.getNilai1();
                    if (nilai1.toLowerCase(Locale.getDefault()).contains(charText)) {
                        worldpopulationlist.add(wp);
                    }

                }
            }

        }
        notifyDataSetChanged();
    }
}
