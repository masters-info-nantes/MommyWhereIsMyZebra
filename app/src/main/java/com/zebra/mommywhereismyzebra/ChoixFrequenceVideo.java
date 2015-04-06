package com.zebra.mommywhereismyzebra;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Permet d'ouvrir une fenetre de dialog une fois qu'une video a été prise
 * ou choisie. Cette fenetre permet de choisir la frequence de découpage de la video.
 */
public class ChoixFrequenceVideo extends DialogFragment {

    public interface VideoListener {
        public void VideoListener(int newColor);
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private VideoListener mListener;
    private Button ok;
    private Button annuler;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChoixFrequenceVideo.
     */
    // TODO: Rename and change types and number of parameters
    public static ChoixFrequenceVideo newInstance(String param1, String param2) {
        ChoixFrequenceVideo fragment = new ChoixFrequenceVideo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ChoixFrequenceVideo() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Dialog);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_choix_frequence_video, container);

        ok = (Button)view.findViewById(R.id.frequenceChoisie);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.VideoListener(0);
                dismiss();
            }
        });

        annuler = (Button)view.findViewById(R.id.annuler2);
        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }

    public void setVideoChoisieListener(VideoListener listener) {
        mListener = listener;
    }

}
