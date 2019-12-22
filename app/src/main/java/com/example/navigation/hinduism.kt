package com.example.navigation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_hinduism.*
import kotlinx.android.synthetic.main.fragment_hinduism.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class hinduism : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var insertUrl = "http://192.168.43.206:8080/Myapi/updatetable.php"
        val v=inflater.inflate(R.layout.fragment_hinduism, container, false)
        placesapi().getplaces().enqueue(object: Callback<List<place>> {
            override fun onFailure(call: Call<List<place>>, t: Throwable) {
                Toast.makeText(context,t.message, Toast.LENGTH_LONG).show()

            }

            override fun onResponse(call: Call<List<place>>, response: Response<List<place>>) {
                val places=response.body()
                places?.let {
                    places(it)

                }
            }
        })
        val fa=v.fab
        fa.setOnClickListener {

            var j = 0
            if (placesAdapter.checkedplaces.size > 0) {
                do {
                    var checked = placesAdapter.checkedplaces[j]
                    var sb = checked.name
                    val s = Sender(context, insertUrl, sb)
                    s.execute()
                    j++

                } while (j < placesAdapter.checkedplaces.size)

            } else {
                Toast.makeText(context, "Please Check An Item First", Toast.LENGTH_SHORT).show()
            }

        }

        return v

    }

    private fun places(places:List<place>)
    {
        recyclerViewPlaces.layoutManager= LinearLayoutManager(context)
        recyclerViewPlaces.adapter=placesAdapter(places)
    }
}

