package com.carouselview.panel;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

/**
 * Created by CarouselView on 7/7/16.
 */
public class ImagePanel extends BasePanel {

    //private ImageView mImageViewHolder;
    private  RoundedImageViewMarija mImageViewHolder;
   // private RoundedImageView mImageViewHolder;

    //Constructors
    public ImagePanel(Context context) {
        this(context, null);
    }

    public ImagePanel(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImagePanel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initImagePanel();
    }

    public void setImageResId(Uri resId) {
        //Picasso.with(getContext()).load(resId).resize(200, 200).
             //   centerCrop().into(mImageViewHolder);

        Picasso.with(getContext()).load(resId).into(mImageViewHolder);
    }


    /* ***************************************************************************** */
    /* ******************************** Utility API ******************************** */
    /* ***************************************************************************** */

    private void initImagePanel() {
      /*  mImageViewHolder = new ImageView(getContext());
        mImageViewHolder.setScaleType(ImageView.ScaleType.FIT_XY);
        mImageViewHolder.setBackgroundResource(R.drawable.img);//R.mipmap.ic_launcher
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mImageViewHolder.setLayoutParams(lp);
        mPanelContainer.addView(mImageViewHolder);
*/
/*

         mImageViewHolder = new RoundedImageView(getContext());
        mImageViewHolder.setScaleType(ImageView.ScaleType.CENTER_CROP);
       // mImageViewHolder.setCornerRadius((float) 10);
       // mImageViewHolder.setBorderWidth((float) 2);
       // mImageViewHolder.setBorderColor(Color.DKGRAY);
        //mImageViewHolder.mutateBackground(true);
        mImageViewHolder.setImageResource(R.drawable.iron_man);
        mImageViewHolder.setBackgroundResource(R.drawable.iron_man);

        mImageViewHolder.setOval(true);
        mImageViewHolder.setTileModeX(Shader.TileMode.REPEAT);
        mImageViewHolder.setTileModeY(Shader.TileMode.REPEAT);

        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mImageViewHolder.setLayoutParams(lp);
        mPanelContainer.addView(mImageViewHolder);*/



         mImageViewHolder = new RoundedImageViewMarija(getContext(),null);
       // mImageViewHolder.setLayoutParams(new ViewGroup.LayoutParams(
              //  ViewGroup.LayoutParams.MATCH_PARENT,
               // ViewGroup.LayoutParams.MATCH_PARENT));
        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(600,600);
        mImageViewHolder.setLayoutParams(parms);
        parms.gravity=Gravity.CENTER;
        mImageViewHolder.setLayoutParams(parms);

       // Picasso.with(getContext()).load(R.drawable.photo4).resize(200, 200).
               // centerCrop().into(mImageViewHolder);

        mPanelContainer.addView(mImageViewHolder);
    }


    public void setURI(Uri uri) {

      //  mImageViewHolder.setImageURI(Uri.parse(new File(uri).toString()));

    }
}