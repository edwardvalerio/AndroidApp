package prizesnob.evmcstudios.com.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import prizesnob.evmcstudios.com.R;
import prizesnob.evmcstudios.com.Utilities.DownloadAndDisplayImage;
import prizesnob.evmcstudios.com.ZSchema.OfferItem;

/**
 * Created by edwardvalerio on 2/4/2018.
 */
public class OfferListAdapter extends ArrayAdapter<OfferItem> {

    private ArrayList<OfferItem> offerItem;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView Title;
        TextView Amount;
        TextView Description;
        ImageView Image;
    }

    public OfferListAdapter(ArrayList<OfferItem> data, Context context) {
        super(context, R.layout.prize_snob_offer_list_row_item, data);
        this.offerItem = data;
        this.mContext=context;

    }



    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        OfferItem currentOffer = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.prize_snob_offer_list_row_item, parent, false);
            viewHolder.Title = (TextView) convertView.findViewById(R.id.offer_title);
            viewHolder.Amount = (TextView) convertView.findViewById(R.id.point_info);
            viewHolder.Description = (TextView) convertView.findViewById(R.id.offer_description);
            viewHolder.Image = (ImageView) convertView.findViewById(R.id.offer_image);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }


        lastPosition = position;

        viewHolder.Title.setText(currentOffer.getTitle());
        viewHolder.Amount.setText(currentOffer.getAmount());
        viewHolder.Description.setText(currentOffer.getDescription());

       //   new DownloadAndDisplayImage(viewHolder.Image).execute(currentOffer.getImageUrl());

        Picasso.with(mContext).load(currentOffer.getImageUrl()).fit().centerCrop().into(viewHolder.Image);

        // Return the completed view to render on screen
        return convertView;
    }






}
