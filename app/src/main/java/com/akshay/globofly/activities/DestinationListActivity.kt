package com.akshay.globofly.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.akshay.globofly.R
import com.akshay.globofly.helpers.DestinationAdapter
import com.akshay.globofly.helpers.SampleData
import com.akshay.globofly.models.Destination
import com.akshay.globofly.services.DestinationService
import com.akshay.globofly.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_destiny_list.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class DestinationListActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_destiny_list)

		setSupportActionBar(toolbar)
		toolbar.title = title

		fab.setOnClickListener {
			val intent = Intent(this@DestinationListActivity, DestinationCreateActivity::class.java)
			startActivity(intent)
		}
	}

	override fun onResume() {
		super.onResume()

		loadDestinations()
	}

	private fun loadDestinations() {

        // To be replaced by retrofit code
		//destiny_recycler_view.adapter = DestinationAdapter(SampleData.DESTINATIONS)

		val destinationService = ServiceBuilder.buildService(DestinationService::class.java)

		val requestCall = destinationService.getDestinationList()

		//the status code will be decide if your http response is a success or error
		requestCall.enqueue(object: retrofit2.Callback<List<Destination>>{
			override fun onResponse(call: Call<List<Destination>>, response: Response<List<Destination>>) {
				if (response.isSuccessful){
					//the status code will be range between 200-299
					val destination = response.body()!!
					destiny_recycler_view.adapter = DestinationAdapter(destination)
				}else{
					//the status code will be range between 300's,400's,500's
					Toast.makeText(this@DestinationListActivity,"Failed to retrive items", Toast.LENGTH_LONG).show()
				}
			}

			override fun onFailure(call: Call<List<Destination>>, t: Throwable) {
				Toast.makeText(this@DestinationListActivity,"Error Occurred " + t.toString(), Toast.LENGTH_LONG).show()
			}

		})

    }



}
