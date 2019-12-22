package com.example.navigation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*

/**
 * A simple [Fragment] subclass.
 */
class ExploreFragment : Fragment() {

    private var imageView1:ImageView? = null
    //var for image2=religious
    private var imageView2:ImageView? = null
    //imageview3=Beach
    private var imageView4:ImageView? = null
    //imageview5=nightlife
    private var imageView5:ImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v:View = inflater.inflate(R.layout.fragment_explore, main_frame, false)
        imageView1 = v.findViewById(R.id.imageView1) as ImageView;
        imageView1!!.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View) {
                var fragment:Fragment = LandmarkFragment();
                var fragmentManager:FragmentManager = getActivity()!!.getSupportFragmentManager();
                var fragmentTransaction:FragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_frame, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        })

        //fragment replace for religious category
        imageView2 = v.findViewById(R.id.imageView2) as ImageView;
        imageView2!!.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View) {
                var fragment:Fragment = ReligiousFragment();
                var fragmentManager:FragmentManager = getActivity()!!.getSupportFragmentManager();
                var fragmentTransaction:FragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_frame, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        })

        //fragment replace for Beach category

        //fragment replace for Fun&Leisure category
        imageView4 = v.findViewById(R.id.imageView4) as ImageView;
        imageView4!!.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View) {
                var fragment:Fragment = BeachFragment();
                var fragmentManager:FragmentManager = getActivity()!!.getSupportFragmentManager();
                var fragmentTransaction:FragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_frame, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        })
        imageView5 = v.findViewById(R.id.imageView5) as ImageView;
        imageView5!!.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View) {
                var fragment:Fragment = FunLeisureFragment();
                var fragmentManager:FragmentManager = getActivity()!!.getSupportFragmentManager();
                var fragmentTransaction:FragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_frame, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        })

        return v
    }


}
