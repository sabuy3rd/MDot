package biophics.mdot.Fragment;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.se.omapi.Session;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TimePicker;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import biophics.mdot.FireStore.FireStore_Insert;
import biophics.mdot.R;
import biophics.mdot.Utility.GetSession;

import static android.support.constraint.Constraints.TAG;
import static biophics.mdot.FireStore.FireStore_Read.Read_Config_Id;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ConfigFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ConfigFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConfigFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String getId;

    private OnFragmentInteractionListener mListener;

    public ConfigFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConfigFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConfigFragment newInstance(String param1, String param2) {
        ConfigFragment fragment = new ConfigFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_config, container, false);

        final TimePicker alarmTimePicker = v.findViewById(R.id.alarmTimePicker);
        //alarmTimePicker.setHour(GetSession.getValue("Time"));

        Log.w(TAG, "DOC::::" + GetSession.getValue("Time"));
        Log.w(TAG, "CONFIG_CHECK_1111::::" + GetSession.getValue("g_id"));
        ImageButton ibtn_add = v.findViewById(R.id.ibtn_add);
        ImageButton ibtn_cancle = v.findViewById(R.id.ibtn_cancle);
        ibtn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = 0;
                int minute = 0;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    hour = alarmTimePicker.getHour();
                    minute = alarmTimePicker.getMinute();
                }

                Log.w(TAG, "CONFIG_CHECK::::" + GetSession.getValue("g_id"));
                FireStore_Insert.Firestore_Update_Config(GetSession.getValue("g_id"), hour + "", minute + "");
                GoBack();
            }
        });
        ibtn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoBack();
            }
        });
        return v;
    }

    public void GoBack() {
        HomeFragment homeFragment = new HomeFragment();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).addToBackStack(null).commit();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
