package com.carouselview;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.carousel.CarouselView;
import com.carouselview.panel.ImagePanel;
import com.carouselview.panel.ListLayoutPanel;
import com.carouselview.panel.RoundedImageViewMarija;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by binary on 3/8/16.
 */
public class CarouselFragment extends Fragment implements ListLayoutPanel.OnScrollListener {

    public static CarouselFragment newInstance() {
        Bundle args = new Bundle();

        CarouselFragment fragment = new CarouselFragment();
        fragment.setArguments(args);
        return fragment;
    }

    List<View> result;

    private CarouselView mCarouselView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View  mRoot =  inflater.inflate(R.layout.fragment_carousel, null, false);
        RoundedImageViewMarija r = (RoundedImageViewMarija) mRoot.findViewById(R.id.image);



        //r.setLayoutParams(new ViewGroup.LayoutParams(
              //  ViewGroup.LayoutParams.WRAP_CONTENT,
               // ViewGroup.LayoutParams.WRAP_CONTENT));
        Picasso.with(getContext()).load(R.drawable.tor).resize(100, 100).
                centerCrop().into(r);
        initStubItems();
        return mRoot;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCarouselView = (CarouselView) view.findViewById(R.id.carouselView);
        for (View stubItem : result) {
            mCarouselView.addView(stubItem);
        }

        mCarouselView.notifyDataSetChanged();
       // mCarouselView.setCarouselDrawingPanelsEnabled(true);

        view.findViewById(R.id.prev).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = mCarouselView.getSelectedItemPosition() == 0 ? mCarouselView.getCount() - 1 : mCarouselView.getSelectedItemPosition() - 1;
                mCarouselView.scrollToChild(position);
                mCarouselView.invalidate();
            }
        });

        view.findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = mCarouselView.getSelectedItemPosition() == mCarouselView.getCount() - 1 ? 0 : mCarouselView.getSelectedItemPosition() + 1;
                mCarouselView.scrollToChild(position);
                mCarouselView.invalidate();
            }
        });
    }

    // Stub items
    private List<View> initStubItems() {
         result = new ArrayList<>();


        ImagePanel b = new ImagePanel(getContext());
        b.setImageResId(R.drawable.photo4);

        result.add(b);
        b = new ImagePanel(getContext());
        b.setImageResId(R.drawable.tor);

        result.add(b);
        b = new ImagePanel(getContext());
        b.setImageResId(R.drawable.natasha);

        result.add(b);
        b = new ImagePanel(getContext());
        b.setImageResId(R.drawable.tor);

        result.add(b);
        b = new ImagePanel(getContext());
        b.setImageResId(R.drawable.tor);

        result.add(b);
        b = new ImagePanel(getContext());
        b.setImageResId(R.drawable.tor);

        result.add(b);
        b = new ImagePanel(getContext());
        b.setImageResId(R.drawable.tor);

        result.add(b);
        b = new ImagePanel(getContext());
        b.setImageResId(R.drawable.tor);

        result.add(b);
        b = new ImagePanel(getContext());
        b.setImageResId(R.drawable.tor);

        result.add(b);

       /* RoundedImageViewMarija rivm = new RoundedImageViewMarija(getContext(),null);
        //rivm.setLayoutParams(new ViewGroup.LayoutParams(
         // ViewGroup.LayoutParams.WRAP_CONTENT,
        // ViewGroup.LayoutParams.WRAP_CONTENT));
        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(100,100);
        rivm.setLayoutParams(parms);
        Picasso.with(getContext()).load(R.drawable.tor).resize(200, 200).
                centerCrop().into(rivm);

        // rivm.setImageBitmap(icon);

        result.add(rivm);*/

        return result;
    }

    @Override
    public void onScroll() {
        mCarouselView.invalidate();
    }
}
