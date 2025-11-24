package com.example.w9_b3

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainScrollView)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupSuggestedApps()
        setupRecommendedApps()
        setupTopApps()
    }

    private fun setupSuggestedApps() {
        val suggestedApps = listOf(
            App("Liên Quân Mobile", "MOBA • Hành động", 4.3, "1.2 GB", R.drawable.thumb1),
            App("Free Fire", "Battle Royale • Bắn súng", 4.2, "890 MB", R.drawable.thumb2),
            App("Genshin Impact", "Nhập vai • Phiêu lưu", 4.6, "18 GB", R.drawable.thumb3),
            App("Tốc Chiến", "MOBA • Chiến thuật", 4.4, "1.5 GB", R.drawable.thumb4),
            App("PUBG Mobile VN", "Battle Royale", 4.5, "2.1 GB", R.drawable.thumb5),
            App("Liên Minh Huyền Thoại", "MOBA • Hành động", 4.3, "3.5 GB", R.drawable.thumb6),
            App("Võ Lâm Truyền Kỳ Mobile", "MMORPG", 4.4, "650 MB", R.drawable.thumb7),
            App("Tam Quốc Liên Minh", "Chiến thuật • Thẻ bài", 4.5, "580 MB", R.drawable.thumb8),
            App("Blade & Soul VN", "MMORPG • Nhập vai", 4.3, "1.8 GB", R.drawable.thumb9)
        )

        // Chia apps thành các cột, mỗi cột 3 apps
        val appsPerColumn = 3
        val columns = suggestedApps.chunked(appsPerColumn)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewSuggested)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = SuggestedColumnAdapter(columns) { app ->
            Log.d("MainActivity2", "Clicked on app: ${app.name}")
        }
    }

    private fun setupRecommendedApps() {
        val recommendedApps = listOf(
            App("Monkey Junior", "Học tiếng Anh cho trẻ em", 4.7, "125 MB", R.drawable.thumb10),
            App("ELSA Speak", "Luyện phát âm tiếng Anh", 4.6, "85 MB", R.drawable.thumb11),
            App("Duolingo", "Học ngoại ngữ", 4.5, "95 MB", R.drawable.thumb12),
            App("Vietjack", "Học tập THPT", 4.4, "45 MB", R.drawable.thumb13),
            App("Hoc10", "Học 24/7 online", 4.6, "68 MB", R.drawable.thumb14),
            App("Monkey Stories", "Đọc truyện tiếng Anh", 4.7, "110 MB", R.drawable.thumb15),
            App("VioEdu", "Học trực tuyến", 4.5, "72 MB", R.drawable.thumb16),
            App("Violet", "Tài liệu học tập", 4.3, "35 MB", R.drawable.thumb17),
            App("Tuyensinh247", "Ôn thi THPT QG", 4.5, "58 MB", R.drawable.thumb18),
            App("Cake", "Học tiếng Anh qua video", 4.6, "92 MB", R.drawable.thumb19)
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewRecommended)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val adapter = RecommendedAppRecyclerAdapter(recommendedApps) { app: App ->
            Log.d("MainActivity2", "Clicked on app: ${app.name}")
        }
        recyclerView.adapter = adapter
    }

    private fun setupTopApps() {
        val topApps = listOf(
            App("Zalo", "Mạng xã hội • Nhắn tin", 4.5, "120 MB", R.drawable.thumb20),
            App("TikTok", "Giải trí • Video ngắn", 4.4, "180 MB", R.drawable.thumb21),
            App("Shopee", "Mua sắm trực tuyến", 4.6, "95 MB", R.drawable.thumb22),
            App("Grab", "Gọi xe • Giao đồ ăn", 4.3, "110 MB", R.drawable.thumb23),
            App("VinID", "Tiện ích • Thanh toán", 4.2, "65 MB", R.drawable.thumb24),
            App("Momo", "Ví điện tử", 4.5, "85 MB", R.drawable.thumb25),
            App("Lazada", "Mua sắm online", 4.4, "100 MB", R.drawable.thumb26),
            App("Facebook", "Mạng xã hội", 4.1, "250 MB", R.drawable.thumb27),
            App("Messenger", "Nhắn tin", 4.2, "140 MB", R.drawable.thumb28),
            App("Banking Plus", "Ngân hàng • Tài chính", 4.4, "75 MB", R.drawable.thumb1)
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewTopApps)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = SuggestedAppRecyclerAdapter(topApps) { app: App ->
            Log.d("MainActivity2", "Clicked on app: ${app.name}")
        }
        recyclerView.adapter = adapter
    }
}
