package com.rideshare.rider

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rideshare.rider.booking.BookingActivity

/**
 * MainActivity serves as the entry point for the Rider app.
 * It offers navigation to key modules (Booking, Map, Payment, User).
 */
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Example: Navigate to BookingActivity on button click
        findViewById(android.R.id.content).setOnClickListener {
            startActivity(Intent(this, BookingActivity::class.java))
        }
    }
}
