package com.example.navigation
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/**
 * A simple [Fragment] subclass.
 */
class ReligiousFragment : Fragment() {

    private var imageView1: ImageView? = null
    private var imageView2: ImageView? = null
    private var imageView3: ImageView? = null
    private var imageView4: ImageView? = null
    private var imageView5: ImageView? = null
    private var imageView6: ImageView? = null
    private var imageView7: ImageView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v:View = inflater.inflate(R.layout.fragment_religious, container, false)
        imageView1 = v.findViewById(R.id.imageView1) as ImageView;
        imageView1!!.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View) {
                var fragment:Fragment = hinduism();
                var fragmentManager: FragmentManager = getActivity()!!.getSupportFragmentManager();
                var fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_frame, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        })
        imageView2 = v.findViewById(R.id.imageView2) as ImageView;
        imageView2!!.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View) {
                var fragment:Fragment = buddhism();
                var fragmentManager: FragmentManager = getActivity()!!.getSupportFragmentManager();
                var fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_frame, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        })
        imageView3 = v.findViewById(R.id.imageView3) as ImageView;
        imageView3!!.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View) {
                var fragment:Fragment = christianity();
                var fragmentManager: FragmentManager = getActivity()!!.getSupportFragmentManager();
                var fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_frame, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        })
        imageView4 = v.findViewById(R.id.imageView4) as ImageView;
        imageView4!!.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View) {
                var fragment:Fragment = jainism();
                var fragmentManager: FragmentManager = getActivity()!!.getSupportFragmentManager();
                var fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_frame, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        })
        imageView5 = v.findViewById(R.id.imageView5) as ImageView;
        imageView5!!.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View) {
                var fragment:Fragment = sufism();
                var fragmentManager: FragmentManager = getActivity()!!.getSupportFragmentManager();
                var fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_frame, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        })
        imageView6 = v.findViewById(R.id.imageView6) as ImageView;
        imageView6!!.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View) {
                var fragment:Fragment = sikhism();
                var fragmentManager: FragmentManager = getActivity()!!.getSupportFragmentManager();
                var fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_frame, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        })
        imageView7 = v.findViewById(R.id.imageView7) as ImageView;
        imageView7!!.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View) {
                var fragment:Fragment = islam();
                var fragmentManager: FragmentManager = getActivity()!!.getSupportFragmentManager();
                var fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_frame, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        })

        return v

    }




}
