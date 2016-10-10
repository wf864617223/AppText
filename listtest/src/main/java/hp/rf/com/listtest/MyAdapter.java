package hp.rf.com.listtest;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.x;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hp on 2016/5/23.
 */
public class MyAdapter extends BaseAdapter {

    Context context;
    private List<Picture.TngouBean> pictures;

    public MyAdapter(Context context, List<Picture.TngouBean> pictures) {
        this.context = context;
        this.pictures = pictures;
    }

    @Override
    public int getCount() {
        return pictures.size();
    }

    @Override
    public Object getItem(int i) {
        return pictures.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.pic_list, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String imgs = pictures.get(i).getImg();
        String imgUrl = "http://tnfs.tngou.net/img" + imgs;
        Log.i("info", "==imgUrls===>" + imgUrl);
        x.image().bind(viewHolder.img, imgUrl);
        viewHolder.title.setText(pictures.get(i).getTitle());
        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.img)
        ImageView img;
        @Bind(R.id.title)
        TextView title;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
