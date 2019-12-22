package com.example.navigation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_beach.*
import kotlinx.android.synthetic.main.fragment_beach.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 */
class BeachFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        var insertUrl = "http://192.168.43.206:8080/Myapi/updatetable.php"
        val v = inflater.inflate(R.layout.fragment_beach, container, false)
        beachesapi().getplaces().enqueue(object : Callback<List<place>> {
            override fun onFailure(call: Call<List<place>>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()

            }

            override fun onResponse(call: Call<List<place>>, response: Response<List<place>>) {
                val places = response.body()
                places?.let {
                    places(it)

                }
            }
        })
        var f=v.fab
        f.setOnClickListener {
            var j = 0
            if (beachesAdapter.checkedplaces.size > 0) {
                do {
                    var checked = beachesAdapter.checkedplaces[j]
                    var sb = checked.name
                    val s = Sender(context, insertUrl, sb)
                    s.execute()
                    j++

                } while (j < beachesAdapter.checkedplaces.size)

            } else {
                Toast.makeText(context, "Please Check An Item First", Toast.LENGTH_SHORT).show()
            }


        }
        return v
    }

    private fun places(places:List<place>)
    {
        recyclerViewPlaces.layoutManager= LinearLayoutManager(context)
        recyclerViewPlaces.adapter=beachesAdapter(places)
    }
}


