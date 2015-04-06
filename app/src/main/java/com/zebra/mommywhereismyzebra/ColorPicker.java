package com.zebra.mommywhereismyzebra;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


public class ColorPicker extends DialogFragment {

    public interface CouleurChoisieListener {
        public void CouleurChoisieListener(int newColor);
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ZoneChoixCouleur choixCouleur;
    private ImageButton couleur;
    private Button annuler;
    private Button ok;

    private int couleurBase;

    private CouleurChoisieListener mListener;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static ColorPicker newInstance(String param1, String param2) {
        ColorPicker fragment = new ColorPicker();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ColorPicker() {
        // Required empty public constructor
    }

    public void setColor(int couleur){
        this.couleurBase = couleur;

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Dialog);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

       /* choixCouleur = (Square)getActivity().findViewById(R.id.choixCouleur);
        choixCouleur.setOnColorChangedListener(new Square.OnColorChangedListener() {
            @Override
            public void onColorChanged(int newColor) {
                couleur.setBackgroundColor(newColor);
            }
        });*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.picker_color, container);

        couleur = (ImageButton)view.findViewById(R.id.couleurSel);
        couleur.setBackgroundColor(couleurBase);

        annuler = (Button)view.findViewById(R.id.annuler2);
        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        ok = (Button)view.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.CouleurChoisieListener(choixCouleur.getColor());
                dismiss();
            }
        });

        choixCouleur = (ZoneChoixCouleur)view.findViewById(R.id.choixCouleur);
        choixCouleur.setOnColorChangedListener(new ZoneChoixCouleur.OnColorChangedListener() {
            @Override
            public void onColorChanged(int newColor) {
                couleur.setBackgroundColor(newColor);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    public void setCouleurChoisieListener(CouleurChoisieListener listener) {
        mListener = listener;
    }

}
