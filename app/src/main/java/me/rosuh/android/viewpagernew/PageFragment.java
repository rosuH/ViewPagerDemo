package me.rosuh.android.viewpagernew;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class PageFragment extends Fragment {
    private static final String ARGS_IMAGE = "argsImage";
    private int[] mImages = new int[]{
            R.drawable.wangjingze,
            R.drawable.sorry,
            R.drawable.meat,
            R.drawable.gewala,
            R.drawable.boy

    };
    private CardView mCardView;
    private ImageView mImageView;

    public static Fragment newInstance(int index){
        Bundle args = new Bundle();
        args.putInt(ARGS_IMAGE, index);
        PageFragment pageFragment = new PageFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_pager_fragment, container, false);
        mImageView = view.findViewById(R.id.image_view_fragment);
        mCardView = view.findViewById(R.id.card_view);

        int index = getArguments().getInt(ARGS_IMAGE);

        Glide.with(this).load(mImages[index]).into(mImageView);

        return view;
    }
}
