package com.example.w9_b2

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        // 1. Tham chieu RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 2. Tao danh sach email
        val emailList = generateEmailList()

        // 3. Tao adapter va gan cho RecyclerView
        val adapter = ItemAdapter(emailList) { position ->
            // Xu ly khi click vao item email
            val email = emailList[position]
            // TODO: Mo chi tiet email hoac thuc hien hanh dong khac
            Log.v("TEST", "Clicked: ${email.title}")
            // Vi du: Toast.makeText(this, "Clicked: ${email.title}", Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = adapter

    }

    private fun generateEmailList(): List<ItemModel> {
        val senders = listOf(
            "Google", "Facebook", "LinkedIn", "Twitter", "Amazon",
            "Netflix", "Spotify", "Apple", "Microsoft", "GitHub",
            "Stack Overflow", "Medium", "Reddit", "Dropbox", "Slack",
            "Zoom", "Adobe", "PayPal", "Airbnb", "Uber"
        )

        val subjects = listOf(
            "Security Alert: New sign-in",
            "Weekly summary is ready",
            "5 new connection requests",
            "Trending in your timeline",
            "Your order has been shipped",
            "New releases this week",
            "Discover Weekly is here",
            "Apple ID sign-in alert",
            "Microsoft 365 update",
            "Pull request merged",
            "Someone answered your question",
            "Top stories for you",
            "Hot posts from communities",
            "Files ready to sync",
            "Team update: 3 new messages",
            "Meeting starts in 15 min",
            "Creative Cloud update",
            "Payment confirmation",
            "Reservation confirmed",
            "Trip coming up soon"
        )

        val previews = listOf(
            "We noticed a new sign-in to your account...",
            "Here's what happened this week...",
            "Expand your professional network...",
            "Check out what's trending...",
            "Great news! Your order is on its way...",
            "Discover new movies and TV shows...",
            "We made a playlist just for you...",
            "Your Apple ID was used on a new device...",
            "Your subscription will renew soon...",
            "Your pull request has been merged...",
            "You received a new answer...",
            "Your daily digest of curated stories...",
            "Hot posts from your favorite communities...",
            "Your files have been synced...",
            "Sarah posted: The new feature is live!...",
            "Meeting reminder: Weekly team sync...",
            "A new version is available...",
            "We received your payment. Thank you...",
            "Your reservation at Grand Hotel...",
            "Get ready for your trip..."
        )

        val times = listOf(
            "9:30 AM", "9:15 AM", "8:45 AM", "8:30 AM", "Yesterday",
            "Yesterday", "2 days ago", "2 days ago", "3 days ago", "3 days ago",
            "4 days ago", "4 days ago", "5 days ago", "5 days ago", "1 week ago",
            "1 week ago", "1 week ago", "2 weeks ago", "2 weeks ago", "3 weeks ago"
        )

        return senders.indices.map { i ->
            ItemModel(
                imageResource = resources.getIdentifier("thumb${i + 1}", "drawable", packageName),
                title = senders[i],
                content = "${subjects[i]} - ${previews[i]}",
                time = times[i]
            )
        }
    }
}