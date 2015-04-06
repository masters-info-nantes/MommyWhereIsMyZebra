package com.zebra.mommywhereismyzebra;

import android.app.Activity;
import android.app.DialogFragment;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Switch;


public class Configurations extends DialogFragment {

    public interface ConfigChoisie {
        public void configChoisie(String nbPelures, int nbDernieresIages, String frequencePelures, boolean afficherImageFond, boolean droitier);
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button ok;
    Spinner nbPelures;
    EditText nbImages;
    Spinner frequencePelures;
    CheckBox afficherImage;
    Switch droitier;

    int nbPeluresVal;
    int nbDernieresIagesVal;
    int frequencePeluresVal;
    boolean afficherImageFondVal;
    boolean droitierVal;

    private ConfigChoisie mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Configurations.
     */
    // TODO: Rename and change types and number of parameters
    public static Configurations newInstance(String param1, String param2) {
        Configurations fragment = new Configurations();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Configurations() {
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

        View view = inflater.inflate(R.layout.fragment_configurations, container);

        nbPelures = (Spinner)view.findViewById(R.id.nbPel);
        nbImages = (EditText)view.findViewById(R.id.nbImg);
        frequencePelures = (Spinner)view.findViewById(R.id.freqPel);
        afficherImage = (CheckBox)view.findViewById(R.id.checkBox);
        droitier = (Switch)view.findViewById(R.id.droitier);

        this.droitier.setChecked(droitierVal);
        this.afficherImage.setChecked(afficherImageFondVal);
        this.frequencePelures.setSelection(0);
        this.nbImages.setText(String.valueOf(nbDernieresIagesVal));
        this.nbPelures.setSelection(0);

        ok = (Button)view.findViewById(R.id.ok_conf);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.configChoisie((String)nbPelures.getSelectedItem(), Integer.parseInt(nbImages.getText().toString()), (String)frequencePelures.getSelectedItem(), afficherImage.isChecked(), droitier.isChecked());
                dismiss();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
    public void setConf(int nbPelures, int nbDernieresIages, int frequencePelures, boolean afficherImageFond, boolean droitier){
        nbPeluresVal = nbPelures;
        nbDernieresIagesVal = nbDernieresIages;
        frequencePeluresVal = frequencePelures;
        afficherImageFondVal = afficherImageFond;
        droitierVal = droitier;
    }

    public void setConfigListener(ConfigChoisie listener) {
        mListener = listener;
    }


}
