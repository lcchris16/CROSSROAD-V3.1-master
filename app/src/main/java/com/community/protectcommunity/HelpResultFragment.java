package com.community.protectcommunity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Timer;
import java.util.TimerTask;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;

public class HelpResultFragment extends Fragment implements View.OnClickListener{
    private View helpResultFragment;
    private Button backToParentsButton;

    private SoundPool soundPool;//declare a SoundPool
    private int soundID;//Create an audio ID for a sound

    BackToMainScreenPopup popup;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        helpResultFragment = LayoutInflater.from(getActivity()).inflate(R.layout.help_result_fragment,
                container, false);
        //Initialize
        soundID = SoundUtil.initSound(this.getActivity());



        setView();

        return helpResultFragment;
    }

    public void setView () {
        //initialize the view

        backToParentsButton = (Button)helpResultFragment.findViewById(R.id.help_return_to_for_parents_screen_button);

        backToParentsButton.setOnClickListener(this);

        //set up the view
        //SharedPreferences sharedPref = getActivity().getSharedPreferences("username_gender_choice",Context.MODE_PRIVATE);
    }

    @Override
    public void onClick(View view) {
        Fragment nextFragment;
        FragmentManager fragmentManager = getFragmentManager();
        nextFragment = new HelpQuestionFourFragment();
        switch (view.getId()) {

            case R.id.help_return_to_for_parents_screen_button:
                SoundUtil.playSound(soundID);
                //might cause exception
                //mHomeWatcher.stopWatch();
                getParents();
                break;
            default:
                break;
        }
    }

    public void getParents(){
        Intent intent = new Intent(this.getContext(), ForParentsActivity.class);
        startActivity(intent);
        this.getActivity().finish();
    }

    public void getHome(){
        Intent intent = new Intent(this.getContext(), MainScreenActivity.class);
        this.getContext().startActivity(intent);
        this.getActivity().finish();
        //this.getContentView()
    }

    //initialize the return pop up window
    public void initPopupLayout() {
        popup = new BackToMainScreenPopup(this.getContext(), getActivity());
        popup.showPopupWindow();
    }

    public void onDestroy() {
        super.onDestroy();
        System.gc();
    }
}